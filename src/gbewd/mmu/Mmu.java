package gbewd.mmu;

import java.io.File;
import java.io.FileInputStream;

import gbewd.breakpoints.BreakpointManager;

public class Mmu {
	private byte[] memory = new byte[0xFFFF];
	private BreakpointManager breakpointManager;
	
	public Mmu(BreakpointManager breakpointManager) {
		this.breakpointManager = breakpointManager;
	}

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

	public void loadRom(String romFileName) {
		File file = new File(romFileName);
		byte[] bytes = new byte[(int) file.length()];
		try(FileInputStream fis = new FileInputStream(file)) {
			fis.read(bytes);
		} catch(Exception e) {
			System.out.println("Exception thrown while loading " + romFileName);
			e.printStackTrace();
		}
		for (int i = 0; i < bytes.length && i < memory.length; ++i) {
			memory[i] = bytes[i];
		}
	}

	public Mmu copyOf() {
		Mmu copy = new Mmu(breakpointManager);
		for (int i = 0; i < 0xFFFF; ++i) {
			copy.memory[i] = memory[i];
		}
		return copy;
	}
	
	public String getMemoryDump(int start, int len) {
		String dump = "(";
		
		for (int i = 0; i < len; ++i) {
			dump += String.format("%x", loadByte(start + i));
			
			if (i != len - 1) {
				dump += " ";
			}
		}
		
		dump += ")";
		return dump;
	}

	// Same as readByte(int) but does not throw any exception if breakpoint is hit
	public int loadByte(int address) {
		return memory[address] & 0xFF;
	}

}
