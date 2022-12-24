package gbewd.cpu.operation.sub;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;
import gbewd.util.LoadValueFunction;
import gbewd.util.LoadValueFunctions;

public class SBC_Operation extends BaseOperation {
	private LoadValueFunction valueFunc;
	
	public SBC_Operation(int opcode, int pcIncrement, int cycles, LoadValueFunction valueFunc) {
		super(opcode, pcIncrement, cycles);
		this.valueFunc = valueFunc;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register a = cpu.getRegisters().get(Z80.Registers.A);
		int sourceValue = valueFunc.loadValue(cpu, mmu);
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		int aOrigValue = a.getValue();
		a.setValue(a.getValue() - sourceValue);
		int carryOver = (f.getValue() & 0x10) != 0 ? 1 : 0;
		a.setValue(a.getValue() - carryOver);
		if (a.getValue() < 0) {
			f.setValue(0x50);
		} else {
			f.setValue(0x40);
		}
		a.setValue(a.getValue() & 255);
		if (a.getValue() == 0) {
			f.setValue(f.getValue() | 0x80);
		}
		if (((a.getValue() ^ sourceValue ^ aOrigValue) & 0x10) != 0) {
			f.setValue(f.getValue() | 0x20);
		}
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllSBC_Operations() {
		Map<Integer, OperationExecution> operations = new HashMap<>(); 
		operations.put(0x98, new SBC_Operation(0x98, 0, 1, LoadValueFunctions.loadFromB));
		operations.put(0x99, new SBC_Operation(0x99, 0, 1, LoadValueFunctions.loadFromC));
		operations.put(0x9A, new SBC_Operation(0x9A, 0, 1, LoadValueFunctions.loadFromD));
		operations.put(0x9B, new SBC_Operation(0x9B, 0, 1, LoadValueFunctions.loadFromE));
		operations.put(0x9C, new SBC_Operation(0x9C, 0, 1, LoadValueFunctions.loadFromH));
		operations.put(0x9D, new SBC_Operation(0x9D, 0, 1, LoadValueFunctions.loadFromL));
		operations.put(0x9F, new SBC_Operation(0x9F, 0, 1, LoadValueFunctions.loadFromA));
		operations.put(0x9E, new SBC_Operation(0x9E, 0, 2, LoadValueFunctions.loadByteFromMemoryHL));
		operations.put(0xDE, new SBC_Operation(0xDE, 1, 2, LoadValueFunctions.loadByteFromMemoryPC));
		return operations;
	}
}
