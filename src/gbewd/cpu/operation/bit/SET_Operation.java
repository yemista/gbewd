package gbewd.cpu.operation.bit;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.mmu.Mmu;
import gbewd.util.LoadValueFunction;
import gbewd.util.LoadValueFunctions;
import gbewd.util.StoreValueFunction;
import gbewd.util.StoreValueFunctions;

public class SET_Operation extends BaseOperation {
	private LoadValueFunction loadFunc;
	private StoreValueFunction storeFunc;
	private int mask;
	
	public SET_Operation(int opcode, int pcIncrement, int cycles, LoadValueFunction loadFunc, StoreValueFunction storeFunc, int mask) {
		super(opcode, pcIncrement, cycles);
		this.loadFunc = loadFunc;
		this.mask = mask;
		this.storeFunc = storeFunc;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		int value = loadFunc.loadValue(cpu, mmu);
		value |= mask;
		storeFunc.storeValue(cpu, mmu, value);
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllSET_Operations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		operations.put(0xCBC0, new SET_Operation(0xCBC0, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0x01));
		operations.put(0xCBC1, new SET_Operation(0xCBC1, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0x01));
		operations.put(0xCBC2, new SET_Operation(0xCBC2, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0x01));
		operations.put(0xCBC3, new SET_Operation(0xCBC3, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0x01));
		operations.put(0xCBC4, new SET_Operation(0xCBC4, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0x01));
		operations.put(0xCBC5, new SET_Operation(0xCBC5, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0x01));
		operations.put(0xCBC7, new SET_Operation(0xCBC7, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0x01));
		operations.put(0xCBC6, new SET_Operation(0xCBC6, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0x01));
		
		operations.put(0xCBC8, new SET_Operation(0xCBC8, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0x02));
		operations.put(0xCBC9, new SET_Operation(0xCBC9, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0x02));
		operations.put(0xCBCA, new SET_Operation(0xCBCA, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0x02));
		operations.put(0xCBCB, new SET_Operation(0xCBCB, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0x02));
		operations.put(0xCBCC, new SET_Operation(0xCBCC, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0x02));
		operations.put(0xCBCD, new SET_Operation(0xCBCD, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0x02));
		operations.put(0xCBCF, new SET_Operation(0xCBCF, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0x02));
		operations.put(0xCBCE, new SET_Operation(0xCBCE, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0x02));
		
		operations.put(0xCBD0, new SET_Operation(0xCBD0, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0x04));
		operations.put(0xCBD1, new SET_Operation(0xCBD1, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0x04));
		operations.put(0xCBD2, new SET_Operation(0xCBD2, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0x04));
		operations.put(0xCBD3, new SET_Operation(0xCBD3, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0x04));
		operations.put(0xCBD4, new SET_Operation(0xCBD4, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0x04));
		operations.put(0xCBD5, new SET_Operation(0xCBD5, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0x04));
		operations.put(0xCBD7, new SET_Operation(0xCBD7, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0x04));
		operations.put(0xCBD6, new SET_Operation(0xCBD6, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0x04));
		                                             
		operations.put(0xCBD8, new SET_Operation(0xCBD8, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0x08));
		operations.put(0xCBD9, new SET_Operation(0xCBD9, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0x08));
		operations.put(0xCBDA, new SET_Operation(0xCBDA, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0x08));
		operations.put(0xCBDB, new SET_Operation(0xCBDB, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0x08));
		operations.put(0xCBDC, new SET_Operation(0xCBDC, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0x08));
		operations.put(0xCBDD, new SET_Operation(0xCBDD, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0x08));
		operations.put(0xCBDF, new SET_Operation(0xCBDF, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0x08));
		operations.put(0xCBDE, new SET_Operation(0xCBDE, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0x08));
		
		operations.put(0xCBE0, new SET_Operation(0xCBE0, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0x10));
		operations.put(0xCBE1, new SET_Operation(0xCBE1, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0x10));
		operations.put(0xCBE2, new SET_Operation(0xCBE2, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0x10));
		operations.put(0xCBE3, new SET_Operation(0xCBE3, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0x10));
		operations.put(0xCBE4, new SET_Operation(0xCBE4, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0x10));
		operations.put(0xCBE5, new SET_Operation(0xCBE5, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0x10));
		operations.put(0xCBE7, new SET_Operation(0xCBE7, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0x10));
		operations.put(0xCBE6, new SET_Operation(0xCBE6, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0x10));
		                                           
		operations.put(0xCBE8, new SET_Operation(0xCBE8, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0x20));
		operations.put(0xCBE9, new SET_Operation(0xCBE9, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0x20));
		operations.put(0xCBEA, new SET_Operation(0xCBEA, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0x20));
		operations.put(0xCBEB, new SET_Operation(0xCBEB, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0x20));
		operations.put(0xCBEC, new SET_Operation(0xCBEC, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0x20));
		operations.put(0xCBED, new SET_Operation(0xCBED, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0x20));
		operations.put(0xCBEF, new SET_Operation(0xCBEF, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0x20));
		operations.put(0xCBEE, new SET_Operation(0xCBEE, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0x20));
		
		operations.put(0xCBF0, new SET_Operation(0xCBF0, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0x40));
		operations.put(0xCBF1, new SET_Operation(0xCBF1, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0x40));
		operations.put(0xCBF2, new SET_Operation(0xCBF2, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0x40));
		operations.put(0xCBF3, new SET_Operation(0xCBF3, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0x40));
		operations.put(0xCBF4, new SET_Operation(0xCBF4, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0x40));
		operations.put(0xCBF5, new SET_Operation(0xCBF5, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0x40));
		operations.put(0xCBF7, new SET_Operation(0xCBF7, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0x40));
		operations.put(0xCBF6, new SET_Operation(0xCBF6, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0x40));
		                                            
		operations.put(0xCBF8, new SET_Operation(0xCBF8, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0x80));
		operations.put(0xCBF9, new SET_Operation(0xCBF9, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0x80));
		operations.put(0xCBFA, new SET_Operation(0xCBFA, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0x80));
		operations.put(0xCBFB, new SET_Operation(0xCBFB, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0x80));
		operations.put(0xCBFC, new SET_Operation(0xCBFC, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0x80));
		operations.put(0xCBFD, new SET_Operation(0xCBFD, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0x80));
		operations.put(0xCBFF, new SET_Operation(0xCBFF, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0x80));                
		operations.put(0xCBFE, new SET_Operation(0xCBFE, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0x80));
		
		return operations;
	}
}
