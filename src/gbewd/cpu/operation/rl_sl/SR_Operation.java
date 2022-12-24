package gbewd.cpu.operation.rl_sl;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class SR_Operation extends BaseOperation {
	private Z80.Registers target;
	private int additionalValue;
	
	public SR_Operation(int opcode, int pcIncrement, int cycles, Z80.Registers target, int additionalValue) {
		super(opcode, pcIncrement, cycles);
		this.target = target;
		this.additionalValue = additionalValue;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register targetRegister = cpu.getRegisters().get(target.getName());
		int carryIn = targetRegister.getValue() & 0x80;
		int carryOut = 0;
		if ((targetRegister.getValue() & 0x80) != 0) {
			carryOut = 0x10;
		}
		targetRegister.setValue((targetRegister.getValue() >> 1) + additionalValue);
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		if (targetRegister.getValue() != 0) {
			f.setValue(0);
		} else {
			f.setValue(0x80);
		}
		f.setValue((f.getValue() & 0xEF) + carryOut);
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllSROperations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		// SRAr_n
		operations.put(0xCB28, new SL_Operation(0xCB20, 0, 2, Z80.Registers.B, 0));
		operations.put(0xCB29, new SL_Operation(0xCB21, 0, 2, Z80.Registers.C, 0));
		operations.put(0xCB2A, new SL_Operation(0xCB22, 0, 2, Z80.Registers.D, 0));
		operations.put(0xCB2B, new SL_Operation(0xCB23, 0, 2, Z80.Registers.E, 0));
		operations.put(0xCB2C, new SL_Operation(0xCB24, 0, 2, Z80.Registers.H, 0));
		operations.put(0xCB2D, new SL_Operation(0xCB25, 0, 2, Z80.Registers.L, 0));
		operations.put(0xCB2F, new SL_Operation(0xCB27, 0, 2, Z80.Registers.A, 0));
		// SRAr_n
		operations.put(0xCB38, new SL_Operation(0xCB38, 0, 2, Z80.Registers.B, 1));
		operations.put(0xCB39, new SL_Operation(0xCB39, 0, 2, Z80.Registers.C, 1));
		operations.put(0xCB3A, new SL_Operation(0xCB3A, 0, 2, Z80.Registers.D, 1));
		operations.put(0xCB3B, new SL_Operation(0xCB3B, 0, 2, Z80.Registers.E, 1));
		operations.put(0xCB3C, new SL_Operation(0xCB3C, 0, 2, Z80.Registers.H, 1));
		operations.put(0xCB3D, new SL_Operation(0xCB3D, 0, 2, Z80.Registers.L, 1));
		operations.put(0xCB3F, new SL_Operation(0xCB3F, 0, 2, Z80.Registers.A, 1));
		return operations;
	}
}
