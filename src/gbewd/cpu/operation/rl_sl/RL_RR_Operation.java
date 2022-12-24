package gbewd.cpu.operation.rl_sl;

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

public class RL_RR_Operation extends BaseOperation {
	private LoadValueFunction carryInValueFunc;
	private LoadValueFunction carryOutValueFunc; 
	private StoreValueFunction carryOutStorefunc;
	private RL_OperationType type;
	private int carryInMask;
	
	public RL_RR_Operation(int opcode, int pcIncrement, int cycles, LoadValueFunction carryInValueFunc, int carryInMask, LoadValueFunction carryOutValueFunc, 
			StoreValueFunction carryOutStorefunc, RL_OperationType type) {
		super(opcode, pcIncrement, cycles);
		this.carryInValueFunc = carryInValueFunc;
		this.carryOutValueFunc = carryOutValueFunc;
		this.carryOutStorefunc = carryOutStorefunc;
		this.type = type;
		this.carryInMask = carryInMask;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		int carryIn = 0;
		int carryInRegisterValue = carryInValueFunc.loadValue(cpu, mmu);
		if ((carryInRegisterValue & carryInMask) != 0) {
			carryIn = 1;
		}
		int carryOutValue = carryOutValueFunc.loadValue(cpu, mmu);
		int carryOut = (carryOutValue & 0x80) != 0 ? 0x10 : 0;
		int carryOutRegisterValue = 0;
		switch (type) {
		case LEFT:
			carryOutRegisterValue = (carryOutValue << 1) + carryIn;
			break;
		case RIGHT:
			carryOutRegisterValue = (carryOutValue >> 1) + carryIn;
			break;
		}
		carryOutRegisterValue &= 255;
		carryOutStorefunc.storeValue(cpu, mmu, carryOutRegisterValue);
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		if (carryOutValueFunc.loadValue(cpu, mmu) != 0) {
			f.setValue(0);
		} else {
			f.setValue(0x80);
		}
		f.setValue((f.getValue() & 0xEF) + carryOut);
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllRLOperations() {
		Map<Integer, OperationExecution> operations = new HashMap<>(); 
		// Rlr_n
		operations.put(0xCB10, new RL_RR_Operation(0xCB10, 0, 2, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, RL_OperationType.LEFT));
		operations.put(0xCB11, new RL_RR_Operation(0xCB11, 0, 2, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, RL_OperationType.LEFT));
		operations.put(0xCB12, new RL_RR_Operation(0xCB12, 0, 2, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, RL_OperationType.LEFT));
		operations.put(0xCB13, new RL_RR_Operation(0xCB13, 0, 2, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, RL_OperationType.LEFT));
		operations.put(0xCB14, new RL_RR_Operation(0xCB14, 0, 2, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, RL_OperationType.LEFT));
		operations.put(0xCB15, new RL_RR_Operation(0xCB15, 0, 2, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, RL_OperationType.LEFT));
		operations.put(0xCB17, new RL_RR_Operation(0xCB17, 0, 2, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, RL_OperationType.LEFT));
		// RLHL
		operations.put(0xCB16, new RL_RR_Operation(0xCB16, 0, 4, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, RL_OperationType.LEFT));
		// RLCr_n
		operations.put(0xCB00, new RL_RR_Operation(0xCB00, 0, 2, LoadValueFunctions.loadFromB, 0x80, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, RL_OperationType.LEFT));
		operations.put(0xCB01, new RL_RR_Operation(0xCB01, 0, 2, LoadValueFunctions.loadFromC, 0x80, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, RL_OperationType.LEFT));
		operations.put(0xCB02, new RL_RR_Operation(0xCB02, 0, 2, LoadValueFunctions.loadFromD, 0x80, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, RL_OperationType.LEFT));
		operations.put(0xCB03, new RL_RR_Operation(0xCB03, 0, 2, LoadValueFunctions.loadFromE, 0x80, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, RL_OperationType.LEFT));
		operations.put(0xCB04, new RL_RR_Operation(0xCB04, 0, 2, LoadValueFunctions.loadFromH, 0x80, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, RL_OperationType.LEFT));
		operations.put(0xCB05, new RL_RR_Operation(0xCB05, 0, 2, LoadValueFunctions.loadFromL, 0x80, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, RL_OperationType.LEFT));
		operations.put(0xCB07, new RL_RR_Operation(0xCB07, 0, 2, LoadValueFunctions.loadFromA, 0x80, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, RL_OperationType.LEFT));
		// RLCHL
		operations.put(0xCB06, new RL_RR_Operation(0xCB06, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, 0x80, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, RL_OperationType.LEFT));
		//RRr_n
		operations.put(0xCB18, new RL_RR_Operation(0xCB18, 0, 2, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, RL_OperationType.RIGHT));
		operations.put(0xCB19, new RL_RR_Operation(0xCB19, 0, 2, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, RL_OperationType.RIGHT));
		operations.put(0xCB1A, new RL_RR_Operation(0xCB1A, 0, 2, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, RL_OperationType.RIGHT));
		operations.put(0xCB1B, new RL_RR_Operation(0xCB1B, 0, 2, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, RL_OperationType.RIGHT));
		operations.put(0xCB1C, new RL_RR_Operation(0xCB1C, 0, 2, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, RL_OperationType.RIGHT));
		operations.put(0xCB1D, new RL_RR_Operation(0xCB1D, 0, 2, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, RL_OperationType.RIGHT));
		operations.put(0xCB1F, new RL_RR_Operation(0xCB1F, 0, 2, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, RL_OperationType.RIGHT));
		// RRHL
		operations.put(0xCB1E, new RL_RR_Operation(0xCB1E, 0, 4, LoadValueFunctions.loadFromF, 0x10, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, RL_OperationType.RIGHT));
		// RRCr_n
		operations.put(0xCB08, new RL_RR_Operation(0xCB08, 0, 2, LoadValueFunctions.loadFromB, 1, LoadValueFunctions.loadFromB, StoreValueFunctions.storeToB, RL_OperationType.RIGHT));
		operations.put(0xCB09, new RL_RR_Operation(0xCB09, 0, 2, LoadValueFunctions.loadFromC, 1, LoadValueFunctions.loadFromC, StoreValueFunctions.storeToC, RL_OperationType.RIGHT));
		operations.put(0xCB0A, new RL_RR_Operation(0xCB0A, 0, 2, LoadValueFunctions.loadFromD, 1, LoadValueFunctions.loadFromD, StoreValueFunctions.storeToD, RL_OperationType.RIGHT));
		operations.put(0xCB0B, new RL_RR_Operation(0xCB0B, 0, 2, LoadValueFunctions.loadFromE, 1, LoadValueFunctions.loadFromE, StoreValueFunctions.storeToE, RL_OperationType.RIGHT));
		operations.put(0xCB0C, new RL_RR_Operation(0xCB0C, 0, 2, LoadValueFunctions.loadFromH, 1, LoadValueFunctions.loadFromH, StoreValueFunctions.storeToH, RL_OperationType.RIGHT));
		operations.put(0xCB0D, new RL_RR_Operation(0xCB0D, 0, 2, LoadValueFunctions.loadFromL, 1, LoadValueFunctions.loadFromL, StoreValueFunctions.storeToL, RL_OperationType.RIGHT));
		operations.put(0xCB0F, new RL_RR_Operation(0xCB0F, 0, 2, LoadValueFunctions.loadFromA, 1, LoadValueFunctions.loadFromA, StoreValueFunctions.storeToA, RL_OperationType.RIGHT));
		// RRCHL
		operations.put(0xCB0E, new RL_RR_Operation(0xCB0E, 0, 4, LoadValueFunctions.loadByteFromMemoryHL, 1, LoadValueFunctions.loadByteFromMemoryHL, StoreValueFunctions.storeByteToMemoryHL, RL_OperationType.RIGHT));
		return operations;
	}

}
