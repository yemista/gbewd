package gbewd.cpu;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.register.Register;

public class Z80 extends Cpu {
	private Map<Registers, Register> registers = new HashMap<>();
	private Map<Registers, Integer> registersCopy = new HashMap<>();
	private boolean halted = false;
	private int ime = 0;
	
	public Z80() {
		
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
		T("T");
		
		Registers(String name) {
			this.name = name;
		}
		
		private String name;
		
		public String getName() {
			return name;
		}
	}

	@Override
	public Map<String, Register> getRegisters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getClockM() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getClockT() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void halt() {
		this.halted = true;		
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

}
