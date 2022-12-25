package gbewd.util;

import gbewd.cpu.Z80;
import gbewd.mmu.Mmu;

public interface ConditionFunction {

	public boolean checkTrue(Z80 cpu, Mmu mmu);
}
