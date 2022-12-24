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
		operations.put(0x88, new SUB_Operation(0x88, 0, 1, LoadValueFunctions.loadFromB));
		operations.put(0x89, new SUB_Operation(0x89, 0, 1, LoadValueFunctions.loadFromC));
		operations.put(0x8A, new SUB_Operation(0x8A, 0, 1, LoadValueFunctions.loadFromD));
		operations.put(0x8B, new SUB_Operation(0x8B, 0, 1, LoadValueFunctions.loadFromE));
		operations.put(0x8C, new SUB_Operation(0x8C, 0, 1, LoadValueFunctions.loadFromH));
		operations.put(0x8D, new SUB_Operation(0x8D, 0, 1, LoadValueFunctions.loadFromL));
		operations.put(0x8F, new SUB_Operation(0x8F, 0, 1, LoadValueFunctions.loadFromA));
		operations.put(0x8E, new SUB_Operation(0x8E, 0, 2, LoadValueFunctions.loadByteFromMemoryHL));
		operations.put(0xCE, new SUB_Operation(0xCE, 1, 2, LoadValueFunctions.loadByteFromMemoryPC));
		return operations;
	}
}
