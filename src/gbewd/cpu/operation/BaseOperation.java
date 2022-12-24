package gbewd.cpu.operation;

import gbewd.cpu.Z80;
import gbewd.cpu.register.Register;

public abstract class BaseOperation implements OperationExecution {
	protected final int opcode;
	protected final int pcIncrement;
	protected final int cycles;
	
	public BaseOperation(int opcode, int pcIncrement, int cycles) {
		this.opcode = opcode;
		this.pcIncrement = pcIncrement;
		this.cycles = cycles;
	}

	protected int calcAddress(int high, int low) {
		return (high << 8) + low;
	}
	
	protected void commonWork(Z80 cpu) {
		Register pc = cpu.getRegisters().get(Z80.Registers.PC);
		pc.setValue(pc.getValue() + pcIncrement);
		Register m = cpu.getRegisters().get(Z80.Registers.M);
		m.setValue(cycles);
	}
}
