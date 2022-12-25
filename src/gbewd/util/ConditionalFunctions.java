package gbewd.util;

import gbewd.cpu.Z80;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class ConditionalFunctions {
	public static ConditionFunction compareF_0x80To0x00 = (Z80 cpu, Mmu mmu) -> {
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		return (f.getValue() & 0x80) == 0x00;
	};
	
	public static ConditionFunction compareF_0x80To0x80 = (Z80 cpu, Mmu mmu) -> {
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		return (f.getValue() & 0x80) == 0x80;
	};
	
	public static ConditionFunction compareF_0x10To0x00 = (Z80 cpu, Mmu mmu) -> {
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		return (f.getValue() & 0x10) == 0x00;
	};
	
	public static ConditionFunction compareF_0x10To0x10 = (Z80 cpu, Mmu mmu) -> {
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		return (f.getValue() & 0x10) == 0x10;
	};
	
	public static ConditionFunction nop = (Z80 cpu, Mmu mmu) -> {
		return true;
	};
}
