package gbewd.cpu.operation.special;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class MAPcb_Operation extends BaseOperation {

	public MAPcb_Operation(int opcode, int pcIncrement, int cycles) {
		super(opcode, pcIncrement, cycles);
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register pc = cpu.getRegisters().get(Z80.Registers.PC);
		pc.setValue(pc.getValue() + 1);
		// TODO: complete
		throw new RuntimeException();
	}
}
