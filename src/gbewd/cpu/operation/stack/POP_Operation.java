package gbewd.cpu.operation.stack;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class POP_Operation extends BaseOperation {
	private Z80.Registers r1;
	private Z80.Registers r2;
	
	public POP_Operation(int opcode, int pcIncrement, int cycles, Z80.Registers r1, Z80.Registers r2) {
		super(opcode, pcIncrement, cycles);
		this.r1 = r1;
		this.r2 = r2;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register sp = cpu.getRegisters().get(Z80.Registers.SP);
		Register register1 = cpu.getRegisters().get(r1);
		register1.setValue(mmu.readByte(sp.getValue()));
		sp.setValue(sp.getValue() + 1);
		Register register2 = cpu.getRegisters().get(r2);
		register2.setValue(mmu.readByte(sp.getValue()));
		sp.setValue(sp.getValue() + 1);
		commonWork(cpu);
	}
}
