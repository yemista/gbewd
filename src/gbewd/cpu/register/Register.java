package gbewd.cpu.register;

public abstract class Register {
	protected final int sizeMask;
	private int value;
	
	public Register(int sizeMask) {
		this.sizeMask = sizeMask;
	}
	
	public void setValue(int value) {
		this.value = value & sizeMask;		
	}
	
	public int getValue() {
		return value & sizeMask;
	}
}
