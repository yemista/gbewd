package gbewd.cpu.operation.special;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

// NEG: function() { Z80._r.a=0-Z80._r.a; Z80._r.f=(Z80._r.a<0)?0x10:0; Z80._r.a&=255; if(!Z80._r.a) Z80._r.f|=0x80; Z80._r.m=2; },
public class NEG_Operation extends BaseOperation {

	public NEG_Operation(int opcode, int pcIncrement, int cycles) {
		super(opcode, pcIncrement, cycles);
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register a = cpu.getRegisters().get(Z80.Registers.A);
		a.setValue(0 - a.getValue());
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		if (a.getValue() < 0) {
			f.setValue(0x10);
		} else {
			f.setValue(0);
		}
		if (a.getValue() == 0) {
			f.setValue(f.getValue() | 0x80);
		}
		commonWork(cpu);
	}

}
