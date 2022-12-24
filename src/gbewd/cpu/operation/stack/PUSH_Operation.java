package gbewd.cpu.operation.stack;

import gbewd.cpu.Z80;
import gbewd.cpu.Z80.Registers;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class PUSH_Operation extends BaseOperation {
	private Z80.Registers r1;
	private Z80.Registers r2;

	public PUSH_Operation(int opcode, int pcIncrement, int cycles, Registers r1, Registers r2) {
		super(opcode, pcIncrement, cycles);
		this.r1 = r1;
		this.r2 = r2;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register sp = cpu.getRegisters().get(Z80.Registers.SP);
		sp.setValue(sp.getValue() - 1);
		Register register1 = cpu.getRegisters().get(r1);
		mmu.writeByte(sp.getValue(), register1.getValue());
		sp.setValue(sp.getValue() - 1);
		Register register2 = cpu.getRegisters().get(r2);
		mmu.writeByte(sp.getValue(), register2.getValue());
		commonWork(cpu);
	}

}
