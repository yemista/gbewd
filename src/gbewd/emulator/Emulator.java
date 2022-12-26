package gbewd.emulator;

import java.util.Map;

import gbewd.breakpoints.BreakpointException;
import gbewd.breakpoints.BreakpointManager;
import gbewd.breakpoints.BreakpointType;
import gbewd.cpu.Z80;
import gbewd.cpu.Z80.Registers;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.operations.AllOperations;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class Emulator {
	private Z80 cpu;
	private Mmu mmu;
	private Map<Integer, OperationExecution> allOperations = AllOperations.getAllOperations();
	private BreakpointManager breakpointManager = new BreakpointManager();
	
	public Emulator() {
		breakpointManager.getBreakpointsByType(BreakpointType.WRITE_MEM).add(0xff01);
		breakpointManager.getBreakpointsByType(BreakpointType.WRITE_MEM).add(0xff02);
		cpu = new Z80(breakpointManager);
		mmu = new Mmu(breakpointManager);
	}
	
	public void loadRom(String romFileName) {
		if (!romFileName.isEmpty()) {
			mmu.loadRom(romFileName);
		}
	}
	
	public void exec() {
		while (!cpu.isHalted()) {
			Register r = cpu.getRegisters().get(Registers.R);
			r.setValue((r.getValue() + 1) & 127);
			Register pc = cpu.getRegisters().get(Registers.PC);
			int pcValue = pc.getValue();
			String cpuDump = cpu.getRegisterDump();
			String memoryDump = mmu.getMemoryDump(pcValue, 4);
			System.out.println(String.format("%s%s", cpuDump, memoryDump));			
			
			if (breakpointManager.pcBreakpointHit(pcValue)) {
			
			}
			
			pc.setValue(pcValue + 1);
			int mem = mmu.loadByte(pcValue);
			OperationExecution operation = allOperations.get(mem);
			Mmu backupMmu = mmu.copyOf();
			Z80 backupCpu = cpu.copyOf();
			
			try {
				operation.doOperation(cpu, mmu);
			} catch(BreakpointException e) {
				mmu = backupMmu;
				cpu = backupCpu;
				breakpointManager.setEnableBreakpoints(false);
				
				try {
					operation.doOperation(cpu, mmu);
				} catch (BreakpointException e1) {
					e1.printStackTrace();
				}
				
				breakpointManager.setEnableBreakpoints(true);
			}
			
			cpu.incM();
		}
	}
}
