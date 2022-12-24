package gbewd.util;

import gbewd.cpu.Z80;
import gbewd.mmu.Mmu;

public interface StoreValueFunction {
	public void storeValue(Z80 cpu, Mmu mmu, int value);
}
