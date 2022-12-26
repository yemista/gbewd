package gbewd.cpu;

import java.util.Map;

import gbewd.cpu.Z80.Registers;
import gbewd.cpu.register.Register;

public abstract class Cpu {
	
	public abstract Map<Registers, Register> getRegisters();
}
