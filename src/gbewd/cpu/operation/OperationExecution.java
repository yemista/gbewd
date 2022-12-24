package gbewd.cpu.operation;

import gbewd.cpu.Z80;
import gbewd.mmu.Mmu;

public interface OperationExecution {
	public void doOperation(Z80 cpu, Mmu mmu);
}
