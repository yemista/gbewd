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

public class RES_Operation extends BaseOperation {
	private LoadValueFunction loadFunc;
	private StoreValueFunction storeFunc;
	private int mask;
	
	public RES_Operation(int opcode, int pcIncrement, int cycles, LoadValueFunction loadFunc, StoreValueFunction storeFunc, int mask) {
		super(opcode, pcIncrement, cycles);
		this.loadFunc = loadFunc;
		this.mask = mask;
		this.storeFunc = storeFunc;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		int value = loadFunc.loadValue(cpu, mmu);
		value &= mask;
		storeFunc.storeValue(cpu, mmu, value);
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllRES_Operations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		operations.put(0xCB80, new RES_Operation(0xCB80, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0xFE));
		operations.put(0xCB81, new RES_Operation(0xCB81, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0xFE));
		operations.put(0xCB82, new RES_Operation(0xCB82, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0xFE));
		operations.put(0xCB83, new RES_Operation(0xCB83, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0xFE));
		operations.put(0xCB84, new RES_Operation(0xCB84, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0xFE));
		operations.put(0xCB85, new RES_Operation(0xCB85, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0xFE));
		operations.put(0xCB87, new RES_Operation(0xCB87, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0xFE));
		operations.put(0xCB86, new RES_Operation(0xCB86, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0xFE));
		
		operations.put(0xCB88, new RES_Operation(0xCB88, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0xFD));
		operations.put(0xCB89, new RES_Operation(0xCB89, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0xFD));
		operations.put(0xCB8A, new RES_Operation(0xCB8A, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0xFD));
		operations.put(0xCB8B, new RES_Operation(0xCB8B, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0xFD));
		operations.put(0xCB8C, new RES_Operation(0xCB8C, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0xFD));
		operations.put(0xCB8D, new RES_Operation(0xCB8D, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0xFD));
		operations.put(0xCB8F, new RES_Operation(0xCB8F, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0xFD));
		operations.put(0xCB8E, new RES_Operation(0xCB8E, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0xFD));
		
		operations.put(0xCB90, new RES_Operation(0xCB90, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0xFB));
		operations.put(0xCB91, new RES_Operation(0xCB91, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0xFB));
		operations.put(0xCB92, new RES_Operation(0xCB92, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0xFB));
		operations.put(0xCB93, new RES_Operation(0xCB93, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0xFB));
		operations.put(0xCB94, new RES_Operation(0xCB94, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0xFB));
		operations.put(0xCB95, new RES_Operation(0xCB95, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0xFB));
		operations.put(0xCB97, new RES_Operation(0xCB97, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0xFB));
		operations.put(0xCB96, new RES_Operation(0xCB96, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0xFB));
		
		operations.put(0xCB98, new RES_Operation(0xCB98, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0xF7));
		operations.put(0xCB99, new RES_Operation(0xCB99, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0xF7));
		operations.put(0xCB9A, new RES_Operation(0xCB9A, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0xF7));
		operations.put(0xCB9B, new RES_Operation(0xCB9B, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0xF7));
		operations.put(0xCB9C, new RES_Operation(0xCB9C, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0xF7));
		operations.put(0xCB9D, new RES_Operation(0xCB9D, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0xF7));
		operations.put(0xCB9F, new RES_Operation(0xCB9F, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0xF7));
		operations.put(0xCB9E, new RES_Operation(0xCB9E, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0xF7));
		
		operations.put(0xCBA0, new RES_Operation(0xCBA0, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0xEF));
		operations.put(0xCBA1, new RES_Operation(0xCBA1, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0xEF));
		operations.put(0xCBA2, new RES_Operation(0xCBA2, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0xEF));
		operations.put(0xCBA3, new RES_Operation(0xCBA3, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0xEF));
		operations.put(0xCBA4, new RES_Operation(0xCBA4, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0xEF));
		operations.put(0xCBA5, new RES_Operation(0xCBA5, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0xEF));
		operations.put(0xCBA7, new RES_Operation(0xCBA7, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0xEF));
		operations.put(0xCBA6, new RES_Operation(0xCBA6, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0xEF));
		
		operations.put(0xCBA8, new RES_Operation(0xCBA8, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0xDF));
		operations.put(0xCBA9, new RES_Operation(0xCBA9, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0xDF));
		operations.put(0xCBAA, new RES_Operation(0xCBAA, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0xDF));
		operations.put(0xCBAB, new RES_Operation(0xCBAB, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0xDF));
		operations.put(0xCBAC, new RES_Operation(0xCBAC, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0xDF));
		operations.put(0xCBAD, new RES_Operation(0xCBAD, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0xDF));
		operations.put(0xCBAF, new RES_Operation(0xCBAF, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0xDF));
		operations.put(0xCBAE, new RES_Operation(0xCBAE, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0xDF));
		                                               
		operations.put(0xCBB0, new RES_Operation(0xCBB0, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0xBF));
		operations.put(0xCBB1, new RES_Operation(0xCBB1, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0xBF));
		operations.put(0xCBB2, new RES_Operation(0xCBB2, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0xBF));
		operations.put(0xCBB3, new RES_Operation(0xCBB3, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0xBF));
		operations.put(0xCBB4, new RES_Operation(0xCBB4, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0xBF));
		operations.put(0xCBB5, new RES_Operation(0xCBB5, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0xBF));
		operations.put(0xCBB7, new RES_Operation(0xCBB7, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0xBF));
		operations.put(0xCBB6, new RES_Operation(0xCBB6, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0xBF));
		                                            
		operations.put(0xCBB8, new RES_Operation(0xCBB8, 0, 2, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, 0x7F));
		operations.put(0xCBB9, new RES_Operation(0xCBB9, 0, 2, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, 0x7F));
		operations.put(0xCBBA, new RES_Operation(0xCBBA, 0, 2, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, 0x7F));
		operations.put(0xCBBB, new RES_Operation(0xCBBB, 0, 2, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, 0x7F));
		operations.put(0xCBBC, new RES_Operation(0xCBBC, 0, 2, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, 0x7F));
		operations.put(0xCBBD, new RES_Operation(0xCBBD, 0, 2, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, 0x7F));
		operations.put(0xCBBF, new RES_Operation(0xCBBF, 0, 2, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, 0x7F));                
		operations.put(0xCBBE, new RES_Operation(0xCBBE, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, 0x7F));
		
		return operations;
	}

}
