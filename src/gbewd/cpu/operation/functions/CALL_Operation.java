package gbewd.cpu.operation.functions;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;
import gbewd.util.ConditionFunction;

public class CALL_Operation extends BaseOperation {
	private ConditionFunction condFunc;

	public CALL_Operation(int opcode, int pcIncrement, int cycles, ConditionFunction condFunc) {
		super(opcode, pcIncrement, cycles);
		this.condFunc = condFunc;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		// DO NOT CALL commonWork(cpu) at the end of this function!
		Register m = cpu.getRegisters().get(Z80.Registers.M);
		m.setValue(3);
		if (condFunc.checkTrue(cpu, mmu)) {
			Register sp = cpu.getRegisters().get(Z80.Registers.SP);
			sp.setValue(sp.getValue() - 2);
			Register pc = cpu.getRegisters().get(Z80.Registers.PC);
			mmu.writeWord(sp.getValue(), pc.getValue() + 2);
			pc.setValue(mmu.readWord(pc.getValue()));
			m.setValue(m.getValue() + 2);
		}
	}

}
