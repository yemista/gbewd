package gbewd.cpu.operation.load.generic;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class LDmr_Operation extends BaseOperation {
	private Z80.Registers source;
	private Z80.Registers addressHigh;
	private Z80.Registers addressLow;
	
	public LDmr_Operation(int opcode, int pcIncrement, int cycles, Z80.Registers source, Z80.Registers addressHigh, Z80.Registers addressLow) {
		super(opcode, pcIncrement, cycles);
		this.source = source;
		this.addressHigh = addressHigh;
		this.addressLow = addressLow;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register highRegister = cpu.getRegisters().get(addressHigh); 
		Register lowRegister = cpu.getRegisters().get(addressHigh);
		int address = calcAddress(highRegister.getValue(), lowRegister.getValue());
		Register sourceRegister = cpu.getRegisters().get(source);
		int value = sourceRegister.getValue();
		mmu.writeByte(address, value);
		commonWork(cpu);		
	}
	
	public static Map<Integer, OperationExecution> getAllLoadMemoryFromRegisterOperations() {
		Map<Integer, OperationExecution> baseOperations = new HashMap<>();
		baseOperations.put(0x70, new LDmr_Operation(0x70, 0, 2, Z80.Registers.B, Z80.Registers.H, Z80.Registers.L));
		baseOperations.put(0x71, new LDmr_Operation(0x71, 0, 2, Z80.Registers.C, Z80.Registers.H, Z80.Registers.L));
		baseOperations.put(0x72, new LDmr_Operation(0x72, 0, 2, Z80.Registers.D, Z80.Registers.H, Z80.Registers.L));
		baseOperations.put(0x73, new LDmr_Operation(0x73, 0, 2, Z80.Registers.E, Z80.Registers.H, Z80.Registers.L));
		baseOperations.put(0x74, new LDmr_Operation(0x74, 0, 2, Z80.Registers.H, Z80.Registers.H, Z80.Registers.L));
		baseOperations.put(0x75, new LDmr_Operation(0x75, 0, 2, Z80.Registers.L, Z80.Registers.H, Z80.Registers.L));
		baseOperations.put(0x77, new LDmr_Operation(0x77, 0, 2, Z80.Registers.A, Z80.Registers.H, Z80.Registers.L));
		
		baseOperations.put(0x02, new LDmr_Operation(0x02, 0, 2, Z80.Registers.A, Z80.Registers.B, Z80.Registers.C));
		baseOperations.put(0x12, new LDmr_Operation(0x12, 0, 2, Z80.Registers.A, Z80.Registers.D, Z80.Registers.E));
		
		baseOperations.put(0x36, new LDmr_Operation(0x36, 1, 3, Z80.Registers.PC, Z80.Registers.H, Z80.Registers.L));
		
		baseOperations.put(0xEA, new LDmr_Operation(0xEA, 2, 4, Z80.Registers.A, Z80.Registers.PC, Z80.Registers.PC));
		return baseOperations;
	}

}
