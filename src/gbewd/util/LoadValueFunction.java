package gbewd.util;

import gbewd.cpu.Z80;
import gbewd.mmu.Mmu;

public interface LoadValueFunction {
	public int loadValue(Z80 cpu, Mmu mmu);
}
