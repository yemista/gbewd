package gbewd.cpu.operation.rl_sl;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class SL_Operation extends BaseOperation {
	private Z80.Registers target;
	private int additionalValue;
	
	public SL_Operation(int opcode, int pcIncrement, int cycles, Z80.Registers target, int additionalValue) {
		super(opcode, pcIncrement, cycles);
		this.target = target;
		this.additionalValue = additionalValue;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register targetRegister = cpu.getRegisters().get(target.getName());
		int carryOut = 0;
		if ((targetRegister.getValue() & 0x80) != 0) {
			carryOut = 0x10;
		}
		targetRegister.setValue((targetRegister.getValue() << 1) + additionalValue);
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		if (targetRegister.getValue() != 0) {
			f.setValue(0);
		} else {
			f.setValue(0x80);
		}
		f.setValue((f.getValue() & 0xEF) + carryOut);
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllSLOperations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		// SLAr_n
		operations.put(0xCB20, new SL_Operation(0xCB20, 0, 2, Z80.Registers.B, 0));
		operations.put(0xCB21, new SL_Operation(0xCB21, 0, 2, Z80.Registers.C, 0));
		operations.put(0xCB22, new SL_Operation(0xCB22, 0, 2, Z80.Registers.D, 0));
		operations.put(0xCB23, new SL_Operation(0xCB23, 0, 2, Z80.Registers.E, 0));
		operations.put(0xCB24, new SL_Operation(0xCB24, 0, 2, Z80.Registers.H, 0));
		operations.put(0xCB25, new SL_Operation(0xCB25, 0, 2, Z80.Registers.L, 0));
		operations.put(0xCB27, new SL_Operation(0xCB27, 0, 2, Z80.Registers.A, 0));
		// SLLr_n
//		operations.put(0x00, new SL_Operation(0x00, 0, 2, Z80.Registers.B, 1));
//		operations.put(0x00, new SL_Operation(0x00, 0, 2, Z80.Registers.C, 1));
//		operations.put(0x00, new SL_Operation(0x00, 0, 2, Z80.Registers.D, 1));
//		operations.put(0x00, new SL_Operation(0x00, 0, 2, Z80.Registers.E, 1));
//		operations.put(0x00, new SL_Operation(0x00, 0, 2, Z80.Registers.H, 1));
//		operations.put(0x00, new SL_Operation(0x00, 0, 2, Z80.Registers.L, 1));
//		operations.put(0x00, new SL_Operation(0x00, 0, 2, Z80.Registers.A, 1));
		return operations;
	}
}
