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

public class INCDEC_Operation extends BaseOperation {
	private LoadValueFunction loadFunc;
	private StoreValueFunction storeFunc;
	private IncDecOperationType type;

	public INCDEC_Operation(int opcode, int pcIncrement, int cycles, LoadValueFunction loadFunc, StoreValueFunction storeFunc, IncDecOperationType type) {
		super(opcode, pcIncrement, cycles);
		this.loadFunc = loadFunc;
		this.storeFunc = storeFunc;
		this.type = type;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		int value = loadFunc.loadValue(cpu, mmu);
		switch (type) {
		case INC:
			value++;
			break;
		case DEC:
			value--;
			break;
		}
		storeFunc.storeValue(cpu, mmu, value);
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		if (value != 0) {
			f.setValue(0);
		} else {
			f.setValue(0x80);
		}
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllINDEC_Operations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		operations.put(0x04, new INCDEC_Operation(0x04, 0, 1, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, IncDecOperationType.INC));
		operations.put(0x0C, new INCDEC_Operation(0x0C, 0, 1, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, IncDecOperationType.INC));
		operations.put(0x14, new INCDEC_Operation(0x14, 0, 1, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, IncDecOperationType.INC));
		operations.put(0x1C, new INCDEC_Operation(0x1C, 0, 1, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, IncDecOperationType.INC));
		operations.put(0x24, new INCDEC_Operation(0x24, 0, 1, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, IncDecOperationType.INC));
		operations.put(0x2C, new INCDEC_Operation(0x2C, 0, 1, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, IncDecOperationType.INC));
		operations.put(0x3C, new INCDEC_Operation(0x3C, 0, 1, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, IncDecOperationType.INC));
		operations.put(0x34, new INCDEC_Operation(0x34, 0, 3, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, IncDecOperationType.INC));
		
		operations.put(0x05, new INCDEC_Operation(0x05, 0, 1, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, IncDecOperationType.DEC));
		operations.put(0x0D, new INCDEC_Operation(0x0D, 0, 1, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, IncDecOperationType.DEC));
		operations.put(0x15, new INCDEC_Operation(0x15, 0, 1, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, IncDecOperationType.DEC));
		operations.put(0x1D, new INCDEC_Operation(0x1D, 0, 1, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, IncDecOperationType.DEC));
		operations.put(0x25, new INCDEC_Operation(0x25, 0, 1, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, IncDecOperationType.DEC));
		operations.put(0x2D, new INCDEC_Operation(0x2D, 0, 1, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, IncDecOperationType.DEC));
		operations.put(0x3D, new INCDEC_Operation(0x3D, 0, 1, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, IncDecOperationType.DEC));
		operations.put(0x35, new INCDEC_Operation(0x35, 0, 3, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, IncDecOperationType.DEC));
		return operations;
	}

}
