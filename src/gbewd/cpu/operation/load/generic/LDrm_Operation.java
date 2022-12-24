package gbewd.cpu.operation.load.generic;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class LDrm_Operation extends BaseOperation {
	private Z80.Registers target;
	private Z80.Registers addressHigh;
	private Z80.Registers addressLow;

	public LDrm_Operation(int opcode, int pcIncrement, int cycles, Z80.Registers target, Z80.Registers addressHigh, Z80.Registers addressLow) {
		super(opcode, pcIncrement, cycles);
		this.target = target;
		this.addressHigh = addressHigh;
		this.addressLow = addressLow;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register highRegister = cpu.getRegisters().get(addressHigh); 
		Register lowRegister = cpu.getRegisters().get(addressHigh);
		int address = calcAddress(highRegister.getValue(), lowRegister.getValue());
		Register targetRegister = cpu.getRegisters().get(target);
		int value = mmu.readByte(address);
		targetRegister.setValue(value);
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllLoadRegisterFromMemoryOperations() {
		Map<Integer, OperationExecution> baseOperations = new HashMap<>();
		baseOperations.put(0x46, new LDrm_Operation(0x46, 0, 2, Z80.Registers.B, Z80.Registers.H, Z80.Registers.L));
		baseOperations.put(0x4E, new LDrm_Operation(0x4E, 0, 2, Z80.Registers.C, Z80.Registers.H, Z80.Registers.L));
		baseOperations.put(0x56, new LDrm_Operation(0x56, 0, 2, Z80.Registers.D, Z80.Registers.H, Z80.Registers.L));
		baseOperations.put(0x5E, new LDrm_Operation(0x5E, 0, 2, Z80.Registers.E, Z80.Registers.H, Z80.Registers.L));
		baseOperations.put(0x66, new LDrm_Operation(0x66, 0, 2, Z80.Registers.H, Z80.Registers.H, Z80.Registers.L));
		baseOperations.put(0x6E, new LDrm_Operation(0x6E, 0, 2, Z80.Registers.L, Z80.Registers.H, Z80.Registers.L));
		baseOperations.put(0x7E, new LDrm_Operation(0x7E, 0, 2, Z80.Registers.A, Z80.Registers.H, Z80.Registers.L));
		
		baseOperations.put(0x06, new LDrm_Operation(0x06, 1, 2, Z80.Registers.B, Z80.Registers.PC, Z80.Registers.PC));
		baseOperations.put(0x0E, new LDrm_Operation(0x0E, 1, 2, Z80.Registers.C, Z80.Registers.PC, Z80.Registers.PC));
		baseOperations.put(0x16, new LDrm_Operation(0x16, 1, 2, Z80.Registers.D, Z80.Registers.PC, Z80.Registers.PC));
		baseOperations.put(0x1E, new LDrm_Operation(0x1E, 1, 2, Z80.Registers.E, Z80.Registers.PC, Z80.Registers.PC));
		baseOperations.put(0x26, new LDrm_Operation(0x26, 1, 2, Z80.Registers.H, Z80.Registers.PC, Z80.Registers.PC));
		baseOperations.put(0x2E, new LDrm_Operation(0x2E, 1, 2, Z80.Registers.L, Z80.Registers.PC, Z80.Registers.PC));
		baseOperations.put(0x3E, new LDrm_Operation(0x3E, 1, 2, Z80.Registers.A, Z80.Registers.PC, Z80.Registers.PC));
		
		baseOperations.put(0x0A, new LDrm_Operation(0x0A, 0, 2, Z80.Registers.A, Z80.Registers.B, Z80.Registers.C));
		baseOperations.put(0x1A, new LDrm_Operation(0x1A, 0, 2, Z80.Registers.A, Z80.Registers.D, Z80.Registers.E));
		return baseOperations;
	}

}
