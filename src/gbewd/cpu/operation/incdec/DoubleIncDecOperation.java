package gbewd.cpu.operation.incdec;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;
import gbewd.util.LoadValueFunction;
import gbewd.util.LoadValueFunctions;
import gbewd.util.StoreValueFunction;
import gbewd.util.StoreValueFunctions;

public class DoubleIncDecOperation extends BaseOperation {
	private LoadValueFunction loadFunc1;
	private StoreValueFunction storeFunc1;
	private LoadValueFunction loadFunc2;
	private StoreValueFunction storeFunc2;
	private IncDecOperationType type;

	public DoubleIncDecOperation(int opcode, int pcIncrement, int cycles, LoadValueFunction loadFunc1, StoreValueFunction storeFunc1, 
			LoadValueFunction loadFunc2, StoreValueFunction storeFunc2, IncDecOperationType type) {
		super(opcode, pcIncrement, cycles);
		this.loadFunc1 = loadFunc1;
		this.storeFunc1 = storeFunc1;
		this.loadFunc2 = loadFunc2;
		this.storeFunc2 = storeFunc2;
		this.type = type;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		int value1 = loadFunc1.loadValue(cpu, mmu);
		switch (type) {
		case INC:
			value1 += 1;
			break;
		case DEC:
			value1 -= 1;
			break;
		}
		storeFunc1.storeValue(cpu, mmu, value1);
		if (type == IncDecOperationType.INC && loadFunc1.loadValue(cpu, mmu) != 0) {
			int value2 = loadFunc2.loadValue(cpu, mmu);
			value2 += 1;
			storeFunc2.storeValue(cpu, mmu, value2);
		}
		if (type == IncDecOperationType.DEC && loadFunc1.loadValue(cpu, mmu) == 255) {
			int value2 = loadFunc2.loadValue(cpu, mmu);
			value2 -= 1;
			storeFunc2.storeValue(cpu, mmu, value2);
		}
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllDoubleIncDecOperations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		operations.put(0x03, new DoubleIncDecOperation(0x03, 0, 1, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, IncDecOperationType.INC));
		operations.put(0x13, new DoubleIncDecOperation(0x13, 0, 1, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, IncDecOperationType.INC));
		operations.put(0x23, new DoubleIncDecOperation(0x23, 0, 1, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, IncDecOperationType.INC));
		operations.put(0x33, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register sp = cpu.getRegisters().get(Z80.Registers.SP);
				sp.setValue(sp.getValue() + 1);
			}
		});
		
		operations.put(0x0B, new DoubleIncDecOperation(0x0B, 0, 1, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, IncDecOperationType.DEC));
		operations.put(0x1B, new DoubleIncDecOperation(0x1B, 0, 1, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, IncDecOperationType.DEC));
		operations.put(0x2B, new DoubleIncDecOperation(0x2B, 0, 1, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, IncDecOperationType.DEC));
		operations.put(0x3B, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register sp = cpu.getRegisters().get(Z80.Registers.SP);
				sp.setValue(sp.getValue() - 1);
			}
		});
		return operations;
	}
}
