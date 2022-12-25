package gbewd.cpu.operation.functions;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;
import gbewd.util.ConditionFunction;

public class RET_Operation extends BaseOperation {
	private ConditionFunction condFunc;

	public RET_Operation(int opcode, int pcIncrement, int cycles, ConditionFunction condFunc) {
		super(opcode, pcIncrement, cycles);
		this.condFunc = condFunc;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		// DO NOT CALL commonWork(cpu) at the end of this function!
		Register m = cpu.getRegisters().get(Z80.Registers.M);
		m.setValue(1);
		if (condFunc.checkTrue(cpu, mmu)) {
			Register sp = cpu.getRegisters().get(Z80.Registers.SP);
			Register pc = cpu.getRegisters().get(Z80.Registers.PC);
			pc.setValue(mmu.readWord(sp.getValue()));
			sp.setValue(sp.getValue() + 2);
			m.setValue(m.getValue() + 2);
		}
	}

}
