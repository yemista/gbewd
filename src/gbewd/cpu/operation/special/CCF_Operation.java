package gbewd.cpu.operation.special;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

// CCF: function() { var ci=Z80._r.f&0x10?0:0x10; Z80._r.f=(Z80._r.f&0xEF)+ci; Z80._r.m=1; },
public class CCF_Operation extends BaseOperation {

	public CCF_Operation(int opcode, int pcIncrement, int cycles) {
		super(opcode, pcIncrement, cycles);
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		int carryIn = 0;
		if ((f.getValue() & 0x10) != 0) {
			carryIn = 0x10;
		}
		f.setValue((f.getValue() & 0xEF) + carryIn);
		commonWork(cpu);
	}

}
