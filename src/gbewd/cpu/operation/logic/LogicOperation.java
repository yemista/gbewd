package gbewd.cpu.operation.logic;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;
import gbewd.util.LoadValueFunction;
import gbewd.util.LoadValueFunctions;

public class LogicOperation extends BaseOperation {
	private LoadValueFunction loadFunc;
	private LogicOperationType logicOperation;
	
	public LogicOperation(int opcode, int pcIncrement, int cycles, LoadValueFunction loadFunc, LogicOperationType logicOperation) {
		super(opcode, pcIncrement, cycles);
		this.loadFunc = loadFunc;
		this.logicOperation = logicOperation;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register a = cpu.getRegisters().get(Z80.Registers.A);
		int value = loadFunc.loadValue(cpu, mmu);
		switch (logicOperation) {
		case AND:
			a.setValue(a.getValue() & value);
			break;
		case OR:
			a.setValue(a.getValue() | value);
			break;
		case XOR:
			a.setValue(a.getValue() ^ value);
			break;
		}
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		if (a.getValue() != 0) {
			f.setValue(0);
		} else {
			f.setValue(0x80);
		}
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllLogicOperations() {
		Map<Integer, OperationExecution> operations = new HashMap<>(); 
		operations.put(0xA0, new LogicOperation(0xA0, 0, 1, LoadValueFunctions.loadFromB, LogicOperationType.AND));
		operations.put(0xA1, new LogicOperation(0xA1, 0, 1, LoadValueFunctions.loadFromC, LogicOperationType.AND));
		operations.put(0xA2, new LogicOperation(0xA2, 0, 1, LoadValueFunctions.loadFromD, LogicOperationType.AND));
		operations.put(0xA3, new LogicOperation(0xA3, 0, 1, LoadValueFunctions.loadFromE, LogicOperationType.AND));
		operations.put(0xA4, new LogicOperation(0xA4, 0, 1, LoadValueFunctions.loadFromH, LogicOperationType.AND));
		operations.put(0xA5, new LogicOperation(0xA5, 0, 1, LoadValueFunctions.loadFromL, LogicOperationType.AND));
		operations.put(0xA7, new LogicOperation(0xA7, 0, 1, LoadValueFunctions.loadFromA, LogicOperationType.AND));
		operations.put(0xA6, new LogicOperation(0xA6, 0, 2, LoadValueFunctions.loadByteFromMemoryHL, LogicOperationType.AND));
		operations.put(0xE6, new LogicOperation(0xE6, 1, 2, LoadValueFunctions.loadByteFromMemoryPC, LogicOperationType.AND));
		                                                                                                     
		operations.put(0xB0, new LogicOperation(0xB0, 0, 1, LoadValueFunctions.loadFromB, LogicOperationType.OR));
		operations.put(0xB1, new LogicOperation(0xB1, 0, 1, LoadValueFunctions.loadFromC, LogicOperationType.OR));
		operations.put(0xB2, new LogicOperation(0xB2, 0, 1, LoadValueFunctions.loadFromD, LogicOperationType.OR));
		operations.put(0xB3, new LogicOperation(0xB3, 0, 1, LoadValueFunctions.loadFromE, LogicOperationType.OR));
		operations.put(0xB4, new LogicOperation(0xB4, 0, 1, LoadValueFunctions.loadFromH, LogicOperationType.OR));
		operations.put(0xB5, new LogicOperation(0xB5, 0, 1, LoadValueFunctions.loadFromL, LogicOperationType.OR));
		operations.put(0xB7, new LogicOperation(0xB7, 0, 1, LoadValueFunctions.loadFromA, LogicOperationType.OR));
		operations.put(0xB6, new LogicOperation(0xB6, 0, 2, LoadValueFunctions.loadByteFromMemoryHL, LogicOperationType.OR));
		operations.put(0xF6, new LogicOperation(0xF6, 1, 2, LoadValueFunctions.loadByteFromMemoryPC, LogicOperationType.OR));
		
		operations.put(0xA8, new LogicOperation(0xA8, 0, 1, LoadValueFunctions.loadFromB, LogicOperationType.XOR));
		operations.put(0xA9, new LogicOperation(0xA9, 0, 1, LoadValueFunctions.loadFromC, LogicOperationType.XOR));
		operations.put(0xAA, new LogicOperation(0xAA, 0, 1, LoadValueFunctions.loadFromD, LogicOperationType.XOR));
		operations.put(0xAB, new LogicOperation(0xAB, 0, 1, LoadValueFunctions.loadFromE, LogicOperationType.XOR));
		operations.put(0xAC, new LogicOperation(0xAC, 0, 1, LoadValueFunctions.loadFromH, LogicOperationType.XOR));
		operations.put(0xAD, new LogicOperation(0xAD, 0, 1, LoadValueFunctions.loadFromL, LogicOperationType.XOR));
		operations.put(0xAF, new LogicOperation(0xAF, 0, 1, LoadValueFunctions.loadFromA, LogicOperationType.XOR));
		operations.put(0xAE, new LogicOperation(0xAE, 0, 2, LoadValueFunctions.loadByteFromMemoryHL, LogicOperationType.XOR));
		operations.put(0xEE, new LogicOperation(0xEE, 1, 2, LoadValueFunctions.loadByteFromMemoryPC, LogicOperationType.XOR));
		return operations;
	}
}
