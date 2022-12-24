package gbewd.cpu.operation.special;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

// SCF: function() { Z80._r.f|=0x10; Z80._r.m=1; },
public class SCF_Operation extends BaseOperation {

	public SCF_Operation(int opcode, int pcIncrement, int cycles) {
		super(opcode, pcIncrement, cycles);
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		f.setValue(f.getValue() | 0x10);
		commonWork(cpu);
	}

}
