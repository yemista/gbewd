package gbewd.cpu.operation.bit;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;
import gbewd.util.LoadValueFunction;
import gbewd.util.LoadValueFunctions;

// BIT0b: function() { Z80._r.f&=0x1F; Z80._r.f|=0x20; Z80._r.f=(Z80._r.b&0x01)?0:0x80; Z80._r.m=2; },
public class BIT_Operation extends BaseOperation {
	private LoadValueFunction loadFunc;
	private int bitmask;

	public BIT_Operation(int opcode, int pcIncrement, int cycles, LoadValueFunction loadFunc, int bitmask) {
		super(opcode, pcIncrement, cycles);
		this.loadFunc = loadFunc;
		this.bitmask = bitmask;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		int value = loadFunc.loadValue(cpu, mmu);
		f.setValue(f.getValue() & 0x1F);
		f.setValue(f.getValue() | 0x20);
		if ((value & bitmask) != 0) {
			f.setValue(0);
		} else {
			f.setValue(0x80);
		}
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllBIT_Operations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		operations.put(0xCB40, new BIT_Operation(0xCB40, 0, 2, LoadValueFunctions.loadFromB, 0x01));
		operations.put(0xCB41, new BIT_Operation(0xCB41, 0, 2, LoadValueFunctions.loadFromC, 0x01));
		operations.put(0xCB42, new BIT_Operation(0xCB42, 0, 2, LoadValueFunctions.loadFromD, 0x01));
		operations.put(0xCB43, new BIT_Operation(0xCB43, 0, 2, LoadValueFunctions.loadFromE, 0x01));
		operations.put(0xCB44, new BIT_Operation(0xCB44, 0, 2, LoadValueFunctions.loadFromH, 0x01));
		operations.put(0xCB45, new BIT_Operation(0xCB45, 0, 2, LoadValueFunctions.loadFromL, 0x01));
		operations.put(0xCB47, new BIT_Operation(0xCB47, 0, 2, LoadValueFunctions.loadFromA, 0x01));
		operations.put(0xCB46, new BIT_Operation(0xCB46, 0, 3, LoadValueFunctions.loadByteFromMemoryHL, 0x01));
		
		operations.put(0xCB48, new BIT_Operation(0xCB48, 0, 2, LoadValueFunctions.loadFromB, 0x02));
		operations.put(0xCB49, new BIT_Operation(0xCB49, 0, 2, LoadValueFunctions.loadFromC, 0x02));
		operations.put(0xCB4A, new BIT_Operation(0xCB4A, 0, 2, LoadValueFunctions.loadFromD, 0x02));
		operations.put(0xCB4B, new BIT_Operation(0xCB4B, 0, 2, LoadValueFunctions.loadFromE, 0x02));
		operations.put(0xCB4C, new BIT_Operation(0xCB4C, 0, 2, LoadValueFunctions.loadFromH, 0x02));
		operations.put(0xCB4D, new BIT_Operation(0xCB4D, 0, 2, LoadValueFunctions.loadFromL, 0x02));
		operations.put(0xCB4F, new BIT_Operation(0xCB4F, 0, 2, LoadValueFunctions.loadFromA, 0x02));
		operations.put(0xCB4E, new BIT_Operation(0xCB4E, 0, 3, LoadValueFunctions.loadByteFromMemoryHL, 0x02));
		
		operations.put(0xCB50, new BIT_Operation(0xCB50, 0, 2, LoadValueFunctions.loadFromB, 0x04));
		operations.put(0xCB51, new BIT_Operation(0xCB51, 0, 2, LoadValueFunctions.loadFromC, 0x04));
		operations.put(0xCB52, new BIT_Operation(0xCB52, 0, 2, LoadValueFunctions.loadFromD, 0x04));
		operations.put(0xCB53, new BIT_Operation(0xCB53, 0, 2, LoadValueFunctions.loadFromE, 0x04));
		operations.put(0xCB54, new BIT_Operation(0xCB54, 0, 2, LoadValueFunctions.loadFromH, 0x04));
		operations.put(0xCB55, new BIT_Operation(0xCB55, 0, 2, LoadValueFunctions.loadFromL, 0x04));
		operations.put(0xCB57, new BIT_Operation(0xCB57, 0, 2, LoadValueFunctions.loadFromA, 0x04));
		operations.put(0xCB56, new BIT_Operation(0xCB56, 0, 3, LoadValueFunctions.loadByteFromMemoryHL, 0x04));
		
		operations.put(0xCB58, new BIT_Operation(0xCB58, 0, 2, LoadValueFunctions.loadFromB, 0x08));
		operations.put(0xCB59, new BIT_Operation(0xCB59, 0, 2, LoadValueFunctions.loadFromC, 0x08));
		operations.put(0xCB5A, new BIT_Operation(0xCB5A, 0, 2, LoadValueFunctions.loadFromD, 0x08));
		operations.put(0xCB5B, new BIT_Operation(0xCB5B, 0, 2, LoadValueFunctions.loadFromE, 0x08));
		operations.put(0xCB5C, new BIT_Operation(0xCB5C, 0, 2, LoadValueFunctions.loadFromH, 0x08));
		operations.put(0xCB5D, new BIT_Operation(0xCB5D, 0, 2, LoadValueFunctions.loadFromL, 0x08));
		operations.put(0xCB5F, new BIT_Operation(0xCB5F, 0, 2, LoadValueFunctions.loadFromA, 0x08));
		operations.put(0xCB5E, new BIT_Operation(0xCB5E, 0, 3, LoadValueFunctions.loadByteFromMemoryHL, 0x08));
		
		operations.put(0xCB60, new BIT_Operation(0xCB60, 0, 2, LoadValueFunctions.loadFromB, 0x10));
		operations.put(0xCB61, new BIT_Operation(0xCB61, 0, 2, LoadValueFunctions.loadFromC, 0x10));
		operations.put(0xCB62, new BIT_Operation(0xCB62, 0, 2, LoadValueFunctions.loadFromD, 0x10));
		operations.put(0xCB63, new BIT_Operation(0xCB63, 0, 2, LoadValueFunctions.loadFromE, 0x10));
		operations.put(0xCB64, new BIT_Operation(0xCB64, 0, 2, LoadValueFunctions.loadFromH, 0x10));
		operations.put(0xCB65, new BIT_Operation(0xCB65, 0, 2, LoadValueFunctions.loadFromL, 0x10));
		operations.put(0xCB67, new BIT_Operation(0xCB67, 0, 2, LoadValueFunctions.loadFromA, 0x10));
		operations.put(0xCB66, new BIT_Operation(0xCB66, 0, 3, LoadValueFunctions.loadByteFromMemoryHL, 0x10));
		
		operations.put(0xCB68, new BIT_Operation(0xCB68, 0, 2, LoadValueFunctions.loadFromB, 0x20));
		operations.put(0xCB69, new BIT_Operation(0xCB69, 0, 2, LoadValueFunctions.loadFromC, 0x20));
		operations.put(0xCB6A, new BIT_Operation(0xCB6A, 0, 2, LoadValueFunctions.loadFromD, 0x20));
		operations.put(0xCB6B, new BIT_Operation(0xCB6B, 0, 2, LoadValueFunctions.loadFromE, 0x20));
		operations.put(0xCB6C, new BIT_Operation(0xCB6C, 0, 2, LoadValueFunctions.loadFromH, 0x20));
		operations.put(0xCB6D, new BIT_Operation(0xCB6D, 0, 2, LoadValueFunctions.loadFromL, 0x20));
		operations.put(0xCB6F, new BIT_Operation(0xCB6F, 0, 2, LoadValueFunctions.loadFromA, 0x20));
		operations.put(0xCB6E, new BIT_Operation(0xCB6E, 0, 3, LoadValueFunctions.loadByteFromMemoryHL, 0x20));
		
		operations.put(0xCB70, new BIT_Operation(0xCB70, 0, 2, LoadValueFunctions.loadFromB, 0x40));
		operations.put(0xCB71, new BIT_Operation(0xCB71, 0, 2, LoadValueFunctions.loadFromC, 0x40));
		operations.put(0xCB72, new BIT_Operation(0xCB72, 0, 2, LoadValueFunctions.loadFromD, 0x40));
		operations.put(0xCB73, new BIT_Operation(0xCB73, 0, 2, LoadValueFunctions.loadFromE, 0x40));
		operations.put(0xCB74, new BIT_Operation(0xCB74, 0, 2, LoadValueFunctions.loadFromH, 0x40));
		operations.put(0xCB75, new BIT_Operation(0xCB75, 0, 2, LoadValueFunctions.loadFromL, 0x40));
		operations.put(0xCB77, new BIT_Operation(0xCB77, 0, 2, LoadValueFunctions.loadFromA, 0x40));
		operations.put(0xCB76, new BIT_Operation(0xCB76, 0, 3, LoadValueFunctions.loadByteFromMemoryHL, 0x40));
		
		operations.put(0xCB78, new BIT_Operation(0xCB78, 0, 2, LoadValueFunctions.loadFromB, 0x80));
		operations.put(0xCB79, new BIT_Operation(0xCB79, 0, 2, LoadValueFunctions.loadFromC, 0x80));
		operations.put(0xCB7A, new BIT_Operation(0xCB7A, 0, 2, LoadValueFunctions.loadFromD, 0x80));
		operations.put(0xCB7B, new BIT_Operation(0xCB7B, 0, 2, LoadValueFunctions.loadFromE, 0x80));
		operations.put(0xCB7C, new BIT_Operation(0xCB7C, 0, 2, LoadValueFunctions.loadFromH, 0x80));
		operations.put(0xCB7D, new BIT_Operation(0xCB7D, 0, 2, LoadValueFunctions.loadFromL, 0x80));
		operations.put(0xCB7F, new BIT_Operation(0xCB7F, 0, 2, LoadValueFunctions.loadFromA, 0x80));
		operations.put(0xCB7E, new BIT_Operation(0xCB7E, 0, 3, LoadValueFunctions.loadByteFromMemoryHL, 0x80));
		
		return operations;
	}
}
