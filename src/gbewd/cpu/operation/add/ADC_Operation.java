package gbewd.cpu.operation.add;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;
import gbewd.util.LoadValueFunction;
import gbewd.util.LoadValueFunctions;

// ADCr_b: function() { var a=Z80._r.a; Z80._r.a+=Z80._r.b; Z80._r.a+=(Z80._r.f&0x10)?1:0; Z80._r.f=(Z80._r.a>255)?0x10:0; Z80._r.a&=255; if(!Z80._r.a) 
//                      Z80._r.f|=0x80; if((Z80._r.a^Z80._r.b^a)&0x10) Z80._r.f|=0x20; Z80._r.m=1; },
public class ADC_Operation extends BaseOperation {
	private LoadValueFunction valueFunc;
	
	public ADC_Operation(int opcode, int pcIncrement, int cycles, LoadValueFunction valueFunc) {
		super(opcode, pcIncrement, cycles);
		this.valueFunc = valueFunc;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register a = cpu.getRegisters().get(Z80.Registers.A);
		int sourceValue = valueFunc.loadValue(cpu, mmu);
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		int aOrigValue = a.getValue();
		a.setValue(a.getValue() + sourceValue);
		int carryOver = (f.getValue() & 0x10) != 0 ? 1 : 0;
		a.setValue(a.getValue() + carryOver);
		if (a.getValue() > 255) {
			f.setValue(0x10);
		} else {
			f.setValue(0);
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
	
	public static Map<Integer, OperationExecution> getAllADCr_xOperations() {
		Map<Integer, OperationExecution> operations = new HashMap<>(); 
		operations.put(0x88, new ADC_Operation(0x88, 0, 1, LoadValueFunctions.loadFromB));
		operations.put(0x89, new ADC_Operation(0x89, 0, 1, LoadValueFunctions.loadFromC));
		operations.put(0x8A, new ADC_Operation(0x8A, 0, 1, LoadValueFunctions.loadFromD));
		operations.put(0x8B, new ADC_Operation(0x8B, 0, 1, LoadValueFunctions.loadFromE));
		operations.put(0x8C, new ADC_Operation(0x8C, 0, 1, LoadValueFunctions.loadFromH));
		operations.put(0x8D, new ADC_Operation(0x8D, 0, 1, LoadValueFunctions.loadFromL));
		operations.put(0x8F, new ADC_Operation(0x8F, 0, 1, LoadValueFunctions.loadFromA));
		operations.put(0x8E, new ADC_Operation(0x8E, 0, 2, LoadValueFunctions.loadByteFromMemoryHL));
		operations.put(0xCE, new ADC_Operation(0xCE, 1, 2, LoadValueFunctions.loadByteFromMemoryPC));
		return operations;
	}

}
