package gbewd.cpu;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.register.Register;

public class Z80 extends Cpu {
	private Map<String, Register> registers = new HashMap<>();
	private boolean halted = false;
	
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

}
