package gbewd.util;

import gbewd.cpu.Z80;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class StoreValueFunctions {
	
	public static StoreValueFunction NOP = (Z80 cpu, Mmu mmu, int value) -> {
	};
	
	public static StoreValueFunction storeToB = (Z80 cpu, Mmu mmu, int value) -> {
		cpu.getRegisters().get(Z80.Registers.B).setValue(value);
	};
	
	public static StoreValueFunction storeToC = (Z80 cpu, Mmu mmu, int value) -> {
		cpu.getRegisters().get(Z80.Registers.C).setValue(value);
	};
	
	public static StoreValueFunction storeToD = (Z80 cpu, Mmu mmu, int value) -> {
		cpu.getRegisters().get(Z80.Registers.D).setValue(value);
	};
	
	public static StoreValueFunction storeToE = (Z80 cpu, Mmu mmu, int value) -> {
		cpu.getRegisters().get(Z80.Registers.E).setValue(value);
	};
	
	public static StoreValueFunction storeToH = (Z80 cpu, Mmu mmu, int value) -> {
		cpu.getRegisters().get(Z80.Registers.H).setValue(value);
	};
	
	public static StoreValueFunction storeToL = (Z80 cpu, Mmu mmu, int value) -> {
		cpu.getRegisters().get(Z80.Registers.L).setValue(value);
	};
	
	public static StoreValueFunction storeToA = (Z80 cpu, Mmu mmu, int value) -> {
		cpu.getRegisters().get(Z80.Registers.A).setValue(value);
	};
	
	public static StoreValueFunction storeByteToMemoryHL = (Z80 cpu, Mmu mmu, int value) -> {
		Register r1 = cpu.getRegisters().get(Z80.Registers.H);
		Register r2 = cpu.getRegisters().get(Z80.Registers.L);
		int address = (r1.getValue() << 8) + r2.getValue();
		mmu.writeByte(address, value);
	};
	
	
}
