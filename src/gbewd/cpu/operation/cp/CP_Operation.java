package gbewd.cpu.operation.cp;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.operation.sub.SBC_Operation;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;
import gbewd.util.LoadValueFunction;
import gbewd.util.LoadValueFunctions;

public class CP_Operation extends BaseOperation {
	private LoadValueFunction valueFunc;
	
	public CP_Operation(int opcode, int pcIncrement, int cycles, LoadValueFunction valueFunc) {
		super(opcode, pcIncrement, cycles);
		this.valueFunc = valueFunc;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register a = cpu.getRegisters().get(Z80.Registers.A);
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		int value = a.getValue();
		int opValue = valueFunc.loadValue(cpu, mmu);
		value -= opValue;
		if (value < 0) {
			f.setValue(0x50);
		} else {
			f.setValue(0x40);
		}
		value &= 255;
		if (value != 0) {
			f.setValue(f.getValue() | 0x80);
		}
		if (((a.getValue() ^ opValue ^ value) & 0x10) != 0) {
			f.setValue(f.getValue() | 0x20);
		}
		commonWork(cpu);
	}

	public static Map<Integer, OperationExecution> getAllCP_Operations() {
		Map<Integer, OperationExecution> operations = new HashMap<>(); 
		operations.put(0xB8, new SBC_Operation(0xB8, 0, 1, LoadValueFunctions.loadFromB));
		operations.put(0xB9, new SBC_Operation(0xB9, 0, 1, LoadValueFunctions.loadFromC));
		operations.put(0xBA, new SBC_Operation(0xBA, 0, 1, LoadValueFunctions.loadFromD));
		operations.put(0x9B, new SBC_Operation(0x9B, 0, 1, LoadValueFunctions.loadFromE));
		operations.put(0xBC, new SBC_Operation(0xBC, 0, 1, LoadValueFunctions.loadFromH));
		operations.put(0xBD, new SBC_Operation(0xBD, 0, 1, LoadValueFunctions.loadFromL));
		operations.put(0xBF, new SBC_Operation(0xBF, 0, 1, LoadValueFunctions.loadFromA));
		operations.put(0xBE, new SBC_Operation(0xBE, 0, 2, LoadValueFunctions.loadByteFromMemoryHL));
		operations.put(0xFE, new SBC_Operation(0xFE, 1, 2, LoadValueFunctions.loadByteFromMemoryPC));
		return operations;
	}
}
