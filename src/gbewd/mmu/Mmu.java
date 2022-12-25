package gbewd.mmu;

public class Mmu {
	private byte[] memory = new byte[0xFFFF];
	
	public int readByte(int address) {
		return memory[address] & 0xFF;
	}

	public void writeByte(int address, int fromValue) {
		memory[address] = (byte) (fromValue & 0xFF);
	}

	public int readWord(int address) {
		int value = (memory[address] + (memory[address + 1] << 8)) & 0xFFFF;
		return value;
	}

	public void writeWord(int address, int value) {
		memory[address] = (byte) (value & 0xFF);
		memory[address + 1] = (byte) ((value & 0xFF00) >> 8);
	}

}
