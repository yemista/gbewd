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

public class SUB_Operation extends BaseOperation {
	private LoadValueFunction valueFunc;
	
	public SUB_Operation(int opcode, int pcIncrement, int cycles, LoadValueFunction valueFunc) {
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
	
	public static Map<Integer, OperationExecution> getAllSUB_Operations() {
		Map<Integer, OperationExecution> operations = new HashMap<>(); 
		operations.put(0x90, new SUB_Operation(0x90, 0, 1, LoadValueFunctions.loadFromB));
		operations.put(0x91, new SUB_Operation(0x91, 0, 1, LoadValueFunctions.loadFromC));
		operations.put(0x92, new SUB_Operation(0x92, 0, 1, LoadValueFunctions.loadFromD));
		operations.put(0x93, new SUB_Operation(0x93, 0, 1, LoadValueFunctions.loadFromE));
		operations.put(0x94, new SUB_Operation(0x94, 0, 1, LoadValueFunctions.loadFromH));
		operations.put(0x95, new SUB_Operation(0x95, 0, 1, LoadValueFunctions.loadFromL));
		operations.put(0x97, new SUB_Operation(0x97, 0, 1, LoadValueFunctions.loadFromA));
		operations.put(0x96, new SUB_Operation(0x96, 0, 2, LoadValueFunctions.loadByteFromMemoryHL));
		operations.put(0xD6, new SUB_Operation(0xD6, 1, 2, LoadValueFunctions.loadByteFromMemoryPC));
		return operations;
	}
}
