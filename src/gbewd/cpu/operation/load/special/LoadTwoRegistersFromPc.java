package gbewd.cpu.operation.load.special;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class LoadTwoRegistersFromPc extends BaseOperation {
	private Z80.Registers first;
	private Z80.Registers second;
	
	public LoadTwoRegistersFromPc(int opcode, int pcIncrement, int cycles, Z80.Registers first, Z80.Registers second) {
		super(opcode, pcIncrement, cycles);
		this.first = first;
		this.second = second;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register firstRegister = cpu.getRegisters().get(first);
		Register secondRegister = cpu.getRegisters().get(second);
		Register pc = cpu.getRegisters().get(Z80.Registers.PC);
		int firstValue = mmu.readByte(pc.getValue());
		int secondValue = mmu.readByte(pc.getValue() + 1);
		firstRegister.setValue(firstValue);
		secondRegister.setValue(secondValue);
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllLoadRegisterFromMemoryOperations() {
		Map<Integer, OperationExecution> baseOperations = new HashMap<>();
		baseOperations.put(0x01, new LoadTwoRegistersFromPc(0x01, 2, 3, Z80.Registers.C, Z80.Registers.B));
		baseOperations.put(0x11, new LoadTwoRegistersFromPc(0x11, 2, 3, Z80.Registers.E, Z80.Registers.D));
		baseOperations.put(0x21, new LoadTwoRegistersFromPc(0x21, 2, 3, Z80.Registers.L, Z80.Registers.H));
		return baseOperations;
	}

}
