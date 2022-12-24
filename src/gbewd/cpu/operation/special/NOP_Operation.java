package gbewd.cpu.operation.special;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.mmu.Mmu;

public class NOP_Operation extends BaseOperation {

	public NOP_Operation(int opcode, int pcIncrement, int cycles) {
		super(opcode, pcIncrement, cycles);
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		commonWork(cpu);
	}

}
