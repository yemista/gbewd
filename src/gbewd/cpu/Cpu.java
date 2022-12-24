package gbewd.cpu;

import java.util.Map;

import gbewd.cpu.register.Register;

public abstract class Cpu {
	
	public abstract Map<String, Register> getRegisters();
	
	public abstract int getClockM();
	
	public abstract int getClockT();
}
