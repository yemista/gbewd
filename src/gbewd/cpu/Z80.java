package gbewd.cpu;

import java.util.HashMap;
import java.util.Map;

import gbewd.breakpoints.BreakpointManager;
import gbewd.cpu.register.Register;
import gbewd.cpu.register.Register16Bit;
import gbewd.cpu.register.Register8Bit;

public class Z80 extends Cpu {
	private Map<Registers, Register> registers = new HashMap<>();
	private Map<Registers, Integer> registersCopy = new HashMap<>();
	
	private boolean halted = false;
	
	private int ime = 0;
	
	private int m;
	private int i;
	private int r;
	
	private BreakpointManager breakpointManager;
	
	
	public Z80(BreakpointManager breakpointManager) {
		registers.put(Registers.R, new Register8Bit());
		registers.put(Registers.B, new Register8Bit());
		registers.put(Registers.C, new Register8Bit());
		registers.put(Registers.D, new Register8Bit());
		registers.put(Registers.E, new Register8Bit());
		registers.put(Registers.H, new Register8Bit());
		registers.put(Registers.L, new Register8Bit());
		registers.put(Registers.A, new Register8Bit());
		registers.put(Registers.F, new Register8Bit());
		registers.put(Registers.SP, new Register16Bit());
		registers.put(Registers.PC, new Register16Bit());
		reset();
		this.breakpointManager = breakpointManager;
	}
	
	public void reset() {
		for (Register register : registers.values()) {
			register.setValue(0);
		}
		r = 0;
		i = 0;
		m = 0;
		halted = false;
		ime = 1;
	}

	public enum Registers {
		A("A"),
		B("B"),
		C("C"),
		D("D"),
		E("E"),
		H("H"),
		L("L"),
		F("F"),
		PC("PC"),
		SP("SP"),
		M("M"),
		T("T"),
		R("R");
		
		Registers(String name) {
			this.name = name;
		}
		
		private String name;
		
		public String getName() {
			return name;
		}
	}

	@Override
	public Map<Registers, Register> getRegisters() {
		return registers;
	}

	public void halt() {
		this.halted = true;		
	}
	
	public boolean isHalted() {
		return halted;
	}

	public void setIME() {
		this.ime = 1;
	}

	public void rsv() {
		registersCopy.put(Registers.A, registers.get(Registers.A).getValue());
		registersCopy.put(Registers.B, registers.get(Registers.B).getValue());
		registersCopy.put(Registers.C, registers.get(Registers.C).getValue());
		registersCopy.put(Registers.D, registers.get(Registers.D).getValue());
		registersCopy.put(Registers.E, registers.get(Registers.E).getValue());
		registersCopy.put(Registers.H, registers.get(Registers.H).getValue());
		registersCopy.put(Registers.L, registers.get(Registers.L).getValue());
		registersCopy.put(Registers.F, registers.get(Registers.F).getValue());
	}
	
	public void rrs() {
		registers.get(Registers.A).setValue(registersCopy.get(Registers.A));
		registers.get(Registers.B).setValue(registersCopy.get(Registers.B));
		registers.get(Registers.C).setValue(registersCopy.get(Registers.C));
		registers.get(Registers.D).setValue(registersCopy.get(Registers.D));
		registers.get(Registers.E).setValue(registersCopy.get(Registers.E));
		registers.get(Registers.H).setValue(registersCopy.get(Registers.H));
		registers.get(Registers.L).setValue(registersCopy.get(Registers.L));
		registers.get(Registers.F).setValue(registersCopy.get(Registers.F));
	}

	public void clearIME() {
		this.ime = 0;
	}
	
	public void incM() {
		m += registers.get(Registers.M).getValue();
	}

	public Z80 copyOf() {
		Z80 copy = new Z80(breakpointManager);
		copy.ime = this.ime;
		copy.i = this.i;
		copy.m = this.m;
		copy.r = this.r;
		copy.halted = this.halted;
		for (Registers registerName : Registers.values()) {
			copy.registers.get(registerName).setValue(this.registers.get(registerName).getValue());
			copy.registersCopy.put(registerName, this.registersCopy.get(registerName));
		}
		return copy;
	}

	public String getRegisterDump() {
		String output = String.format("A: %x F: %x B: %x C: %x H: %x L: %x SP: %x PC: 00:%x ",
				registers.get(Registers.A).getValue(),
				registers.get(Registers.F).getValue(),
				registers.get(Registers.B).getValue(),
				registers.get(Registers.C).getValue(),
				registers.get(Registers.H).getValue(),
				registers.get(Registers.L).getValue(),
				registers.get(Registers.SP).getValue(),
				registers.get(Registers.PC).getValue());
		return output;
	}

}
