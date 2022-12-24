package gbewd.cpu.operation.special;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

// CPL: function() { Z80._r.a ^= 255; Z80._r.f=Z80._r.a?0:0x80; Z80._r.m=1; },
public class CPL_Operation extends BaseOperation {

	public CPL_Operation(int opcode, int pcIncrement, int cycles) {
		super(opcode, pcIncrement, cycles);
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register a = cpu.getRegisters().get(Z80.Registers.A);
		a.setValue(a.getValue() ^ 255);
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		if (a.getValue() != 0) {
			f.setValue(0);
		} else {
			f.setValue(0x80);
		}
		commonWork(cpu);
	}

}
