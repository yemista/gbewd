package gbewd.cpu.operation.load.generic;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class LDrr_Operation extends BaseOperation {
	private Z80.Registers target;
	private Z80.Registers source; 
	
	public LDrr_Operation(int opcode, int pcIncrement, int cycles, Z80.Registers target, Z80.Registers source) {
		super(opcode, pcIncrement, cycles);
		this.target = target;
		this.source = source;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register targetRegister = cpu.getRegisters().get(target); 
		Register sourceRegister = cpu.getRegisters().get(source);
		int value = sourceRegister.getValue();
		targetRegister.setValue(value);
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllLoadRegisterFromRegisterOperations() {
		Map<Integer, OperationExecution> baseOperations = new HashMap<>();
		baseOperations.put(0x40, new LDrr_Operation(0x40, 0, 1, Z80.Registers.B, Z80.Registers.B));
		baseOperations.put(0x41, new LDrr_Operation(0x41, 0, 1, Z80.Registers.B, Z80.Registers.C));
		baseOperations.put(0x42, new LDrr_Operation(0x42, 0, 1, Z80.Registers.B, Z80.Registers.D));
		baseOperations.put(0x43, new LDrr_Operation(0x43, 0, 1, Z80.Registers.B, Z80.Registers.E));
		baseOperations.put(0x44, new LDrr_Operation(0x44, 0, 1, Z80.Registers.B, Z80.Registers.H));
		baseOperations.put(0x45, new LDrr_Operation(0x45, 0, 1, Z80.Registers.B, Z80.Registers.L));
		baseOperations.put(0x47, new LDrr_Operation(0x47, 0, 1, Z80.Registers.B, Z80.Registers.A));
		                                                      
		baseOperations.put(0x48, new LDrr_Operation(0x48, 0, 1, Z80.Registers.C, Z80.Registers.B));
		baseOperations.put(0x49, new LDrr_Operation(0x49, 0, 1, Z80.Registers.C, Z80.Registers.C));
		baseOperations.put(0x4A, new LDrr_Operation(0x4A, 0, 1, Z80.Registers.C, Z80.Registers.D));
		baseOperations.put(0x4B, new LDrr_Operation(0x4B, 0, 1, Z80.Registers.C, Z80.Registers.E));
		baseOperations.put(0x4C, new LDrr_Operation(0x4C, 0, 1, Z80.Registers.C, Z80.Registers.H));
		baseOperations.put(0x4D, new LDrr_Operation(0x4D, 0, 1, Z80.Registers.C, Z80.Registers.L));
		baseOperations.put(0x4F, new LDrr_Operation(0x4F, 0, 1, Z80.Registers.C, Z80.Registers.A));
		                                                      
		baseOperations.put(0x50, new LDrr_Operation(0x50, 0, 1, Z80.Registers.D, Z80.Registers.B));
		baseOperations.put(0x51, new LDrr_Operation(0x51, 0, 1, Z80.Registers.D, Z80.Registers.C));
		baseOperations.put(0x52, new LDrr_Operation(0x52, 0, 1, Z80.Registers.D, Z80.Registers.D));
		baseOperations.put(0x53, new LDrr_Operation(0x53, 0, 1, Z80.Registers.D, Z80.Registers.E));
		baseOperations.put(0x54, new LDrr_Operation(0x54, 0, 1, Z80.Registers.D, Z80.Registers.H));
		baseOperations.put(0x55, new LDrr_Operation(0x55, 0, 1, Z80.Registers.D, Z80.Registers.L));
		baseOperations.put(0x57, new LDrr_Operation(0x57, 0, 1, Z80.Registers.D, Z80.Registers.A));
		                                                      
		baseOperations.put(0x58, new LDrr_Operation(0x58, 0, 1, Z80.Registers.E, Z80.Registers.B));
		baseOperations.put(0x59, new LDrr_Operation(0x59, 0, 1, Z80.Registers.E, Z80.Registers.C));
		baseOperations.put(0x5A, new LDrr_Operation(0x5A, 0, 1, Z80.Registers.E, Z80.Registers.D));
		baseOperations.put(0x5B, new LDrr_Operation(0x5B, 0, 1, Z80.Registers.E, Z80.Registers.E));
		baseOperations.put(0x5C, new LDrr_Operation(0x5C, 0, 1, Z80.Registers.E, Z80.Registers.H));
		baseOperations.put(0x5D, new LDrr_Operation(0x5D, 0, 1, Z80.Registers.E, Z80.Registers.L));
		baseOperations.put(0x5F, new LDrr_Operation(0x5F, 0, 1, Z80.Registers.E, Z80.Registers.A));
		                                                      
		baseOperations.put(0x60, new LDrr_Operation(0x60, 0, 1, Z80.Registers.H, Z80.Registers.B));
		baseOperations.put(0x61, new LDrr_Operation(0x61, 0, 1, Z80.Registers.H, Z80.Registers.C));
		baseOperations.put(0x62, new LDrr_Operation(0x62, 0, 1, Z80.Registers.H, Z80.Registers.D));
		baseOperations.put(0x63, new LDrr_Operation(0x63, 0, 1, Z80.Registers.H, Z80.Registers.E));
		baseOperations.put(0x64, new LDrr_Operation(0x64, 0, 1, Z80.Registers.H, Z80.Registers.H));
		baseOperations.put(0x65, new LDrr_Operation(0x65, 0, 1, Z80.Registers.H, Z80.Registers.L));
		baseOperations.put(0x67, new LDrr_Operation(0x67, 0, 1, Z80.Registers.H, Z80.Registers.A));
		                                                      
		baseOperations.put(0x68, new LDrr_Operation(0x68, 0, 1, Z80.Registers.L, Z80.Registers.B));
		baseOperations.put(0x69, new LDrr_Operation(0x69, 0, 1, Z80.Registers.L, Z80.Registers.C));
		baseOperations.put(0x6A, new LDrr_Operation(0x6A, 0, 1, Z80.Registers.L, Z80.Registers.D));
		baseOperations.put(0x6B, new LDrr_Operation(0x6B, 0, 1, Z80.Registers.L, Z80.Registers.E));
		baseOperations.put(0x6C, new LDrr_Operation(0x6C, 0, 1, Z80.Registers.L, Z80.Registers.H));
		baseOperations.put(0x6D, new LDrr_Operation(0x6D, 0, 1, Z80.Registers.L, Z80.Registers.L));
		baseOperations.put(0x6F, new LDrr_Operation(0x6F, 0, 1, Z80.Registers.L, Z80.Registers.A));
		                                                      
		baseOperations.put(0x78, new LDrr_Operation(0x78, 0, 1, Z80.Registers.A, Z80.Registers.B));
		baseOperations.put(0x79, new LDrr_Operation(0x79, 0, 1, Z80.Registers.A, Z80.Registers.C));
		baseOperations.put(0x7A, new LDrr_Operation(0x7A, 0, 1, Z80.Registers.A, Z80.Registers.D));
		baseOperations.put(0x7B, new LDrr_Operation(0x7B, 0, 1, Z80.Registers.A, Z80.Registers.E));
		baseOperations.put(0x7C, new LDrr_Operation(0x7C, 0, 1, Z80.Registers.A, Z80.Registers.H));
		baseOperations.put(0x7D, new LDrr_Operation(0x7D, 0, 1, Z80.Registers.A, Z80.Registers.L));
		baseOperations.put(0x7F, new LDrr_Operation(0x7F, 0, 1, Z80.Registers.A, Z80.Registers.A));
		return baseOperations;
	}

}
