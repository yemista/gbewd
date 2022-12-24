package gbewd.util;

import gbewd.cpu.Z80;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class LoadValueFunctions {
	public static LoadValueFunction loadFromB = (Z80 cpu, Mmu mmu) -> {
		Register r = cpu.getRegisters().get(Z80.Registers.B);
		return r.getValue();
	};
	
	public static LoadValueFunction loadFromC = (Z80 cpu, Mmu mmu) -> {
		Register r = cpu.getRegisters().get(Z80.Registers.C);
		return r.getValue();
	};
	
	public static LoadValueFunction loadFromD = (Z80 cpu, Mmu mmu) -> {
		Register r = cpu.getRegisters().get(Z80.Registers.D);
		return r.getValue();
	};
	
	public static LoadValueFunction loadFromE = (Z80 cpu, Mmu mmu) -> {
		Register r = cpu.getRegisters().get(Z80.Registers.E);
		return r.getValue();
	};
	
	public static LoadValueFunction loadFromH = (Z80 cpu, Mmu mmu) -> {
		Register r = cpu.getRegisters().get(Z80.Registers.H);
		return r.getValue();
	};
	
	public static LoadValueFunction loadFromL = (Z80 cpu, Mmu mmu) -> {
		Register r = cpu.getRegisters().get(Z80.Registers.L);
		return r.getValue();
	};
	
	public static LoadValueFunction loadFromA = (Z80 cpu, Mmu mmu) -> {
		Register r = cpu.getRegisters().get(Z80.Registers.A);
		return r.getValue();
	};
	
	public static LoadValueFunction loadFromF = (Z80 cpu, Mmu mmu) -> {
		Register r = cpu.getRegisters().get(Z80.Registers.F);
		return r.getValue();
	};
	
	public static LoadValueFunction loadByteFromMemoryHL = (Z80 cpu, Mmu mmu) -> {
		Register r1 = cpu.getRegisters().get(Z80.Registers.H);
		Register r2 = cpu.getRegisters().get(Z80.Registers.L);
		int address = (r1.getValue() << 8) + r2.getValue();
		return mmu.readByte(address);
	};
	
	public static LoadValueFunction loadByteFromMemoryPC = (Z80 cpu, Mmu mmu) -> {
		Register r1 = cpu.getRegisters().get(Z80.Registers.PC);
		int address = r1.getValue();
		return mmu.readByte(address);
	};
}
