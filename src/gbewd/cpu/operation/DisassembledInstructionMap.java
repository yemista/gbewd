package gbewd.cpu.operation;

import java.util.HashMap;
import java.util.Map;

public class DisassembledInstructionMap {
	private static Map<Integer, String> disassembly = new HashMap<>();
	
	static {
		disassembly.put(0x00, "NOP");
		disassembly.put(0x01, "LD BC, u16");
		disassembly.put(0x02, "LD BC, (A)");
		disassembly.put(0x03, "INC BC");
		disassembly.put(0x04, "INC B");
		disassembly.put(0x05, "DEC B");
		disassembly.put(0x06, "LD B, u8");
		disassembly.put(0x07, "RLCA");
		disassembly.put(0x08, "LD (u16), SP");
		disassembly.put(0x09, "ADD HL, BC");
		disassembly.put(0x0A, "LD A, (BC)");
		disassembly.put(0x0B, "DEC BC");
		disassembly.put(0x0C, "INC C");
		disassembly.put(0x0D, "DEC C");
		disassembly.put(0x0E, "LD C, u8");
		disassembly.put(0x0F, "RRCA");
		disassembly.put(0x10, "");
		disassembly.put(0x11, "");
		disassembly.put(0x12, "");
		disassembly.put(0x13, "");
		disassembly.put(0x14, "");
		disassembly.put(0x15, "");
		disassembly.put(0x16, "");
		disassembly.put(0x17, "");
		disassembly.put(0x18, "");
		disassembly.put(0x19, "");
		disassembly.put(0x1A, "");
		disassembly.put(0x1B, "");
		disassembly.put(0x1C, "");
		disassembly.put(0x1D, "");
		disassembly.put(0x1E, "");
		disassembly.put(0x1F, "");
		disassembly.put(0x20, "");
		disassembly.put(0x21, "");
		disassembly.put(0x22, "");
		disassembly.put(0x23, "");
		disassembly.put(0x24, "");
		disassembly.put(0x25, "");
		disassembly.put(0x26, "");
		disassembly.put(0x27, "");
		disassembly.put(0x28, "");
		disassembly.put(0x29, "");
		disassembly.put(0x2A, "");
		disassembly.put(0x2B, "");
		disassembly.put(0x2C, "");
		disassembly.put(0x2D, "");
		disassembly.put(0x2E, "");
		disassembly.put(0x2F, "");
		disassembly.put(0x30, "");
		disassembly.put(0x31, "");
		disassembly.put(0x32, "");
		disassembly.put(0x33, "");
		disassembly.put(0x34, "");
		disassembly.put(0x35, "");
		disassembly.put(0x36, "");
		disassembly.put(0x37, "");
		disassembly.put(0x38, "");
		disassembly.put(0x39, "");
		disassembly.put(0x3A, "");
		disassembly.put(0x3B, "");
		disassembly.put(0x3C, "");
		disassembly.put(0x3D, "");
		disassembly.put(0x3E, "");
		disassembly.put(0x3F, "");
		disassembly.put(0x40, "");
		disassembly.put(0x41, "");
		disassembly.put(0x42, "");
		disassembly.put(0x43, "");
		disassembly.put(0x44, "");
		disassembly.put(0x45, "");
		disassembly.put(0x46, "");
		disassembly.put(0x47, "");
		disassembly.put(0x48, "");
		disassembly.put(0x49, "");
		disassembly.put(0x4A, "");
		disassembly.put(0x4B, "");
		disassembly.put(0x4C, "");
		disassembly.put(0x4D, "");
		disassembly.put(0x4E, "");
		disassembly.put(0x4F, "");
		disassembly.put(0x50, "");
		disassembly.put(0x51, "");
		disassembly.put(0x52, "");
		disassembly.put(0x53, "");
		disassembly.put(0x54, "");
		disassembly.put(0x55, "");
		disassembly.put(0x56, "");
		disassembly.put(0x57, "");
		disassembly.put(0x58, "");
		disassembly.put(0x59, "");
		disassembly.put(0x5A, "");
		disassembly.put(0x5B, "");
		disassembly.put(0x5C, "");
		disassembly.put(0x5D, "");
		disassembly.put(0x5E, "");
		disassembly.put(0x5F, "");
		disassembly.put(0x60, "");
		disassembly.put(0x61, "");
		disassembly.put(0x62, "");
		disassembly.put(0x63, "");
		disassembly.put(0x64, "");
		disassembly.put(0x65, "");
		disassembly.put(0x66, "");
		disassembly.put(0x67, "");
		disassembly.put(0x68, "");
		disassembly.put(0x69, "");
		disassembly.put(0x6A, "");
		disassembly.put(0x6B, "");
		disassembly.put(0x6C, "");
		disassembly.put(0x6D, "");
		disassembly.put(0x6E, "");
		disassembly.put(0x6F, "");
		disassembly.put(0x70, "");
		disassembly.put(0x71, "");
		disassembly.put(0x72, "");
		disassembly.put(0x73, "");
		disassembly.put(0x74, "");
		disassembly.put(0x75, "");
		disassembly.put(0x76, "");
		disassembly.put(0x77, "");
		disassembly.put(0x78, "");
		disassembly.put(0x79, "");
		disassembly.put(0x7A, "");
		disassembly.put(0x7B, "");
		disassembly.put(0x7C, "");
		disassembly.put(0x7D, "");
		disassembly.put(0x7E, "");
		disassembly.put(0x7F, "");
		disassembly.put(0x80, "");
		disassembly.put(0x81, "");
		disassembly.put(0x82, "");
		disassembly.put(0x83, "");
		disassembly.put(0x84, "");
		disassembly.put(0x85, "");
		disassembly.put(0x86, "");
		disassembly.put(0x87, "");
		disassembly.put(0x88, "");
		disassembly.put(0x89, "");
		disassembly.put(0x8A, "");
		disassembly.put(0x8B, "");
		disassembly.put(0x8C, "");
		disassembly.put(0x8D, "");
		disassembly.put(0x8E, "");
		disassembly.put(0x8F, "");
		disassembly.put(0x90, "");
		disassembly.put(0x91, "");
		disassembly.put(0x92, "");
		disassembly.put(0x93, "");
		disassembly.put(0x94, "");
		disassembly.put(0x95, "");
		disassembly.put(0x96, "");
		disassembly.put(0x97, "");
		disassembly.put(0x98, "");
		disassembly.put(0x99, "");
		disassembly.put(0x9A, "");
		disassembly.put(0x9B, "");
		disassembly.put(0x9C, "");
		disassembly.put(0x9D, "");
		disassembly.put(0x9E, "");
		disassembly.put(0x9F, "");
		disassembly.put(0xA0, "");
		disassembly.put(0xA1, "");
		disassembly.put(0xA2, "");
		disassembly.put(0xA3, "");
		disassembly.put(0xA4, "");
		disassembly.put(0xA5, "");
		disassembly.put(0xA6, "");
		disassembly.put(0xA7, "");
		disassembly.put(0xA8, "");
		disassembly.put(0xA9, "");
		disassembly.put(0xAA, "");
		disassembly.put(0xAB, "");
		disassembly.put(0xAC, "");
		disassembly.put(0xAD, "");
		disassembly.put(0xAE, "");
		disassembly.put(0xAF, "");
		disassembly.put(0xB0, "");
		disassembly.put(0xB1, "");
		disassembly.put(0xB2, "");
		disassembly.put(0xB3, "");
		disassembly.put(0xB4, "");
		disassembly.put(0xB5, "");
		disassembly.put(0xB6, "");
		disassembly.put(0xB7, "");
		disassembly.put(0xB8, "");
		disassembly.put(0xB9, "");
		disassembly.put(0xBA, "");
		disassembly.put(0xBB, "");
		disassembly.put(0xBC, "");
		disassembly.put(0xBD, "");
		disassembly.put(0xBE, "");
		disassembly.put(0xBF, "");
		disassembly.put(0xC0, "");
		disassembly.put(0xC1, "");
		disassembly.put(0xC2, "");
		disassembly.put(0xC3, "");
		disassembly.put(0xC4, "");
		disassembly.put(0xC5, "");
		disassembly.put(0xC6, "");
		disassembly.put(0xC7, "");
		disassembly.put(0xC8, "");
		disassembly.put(0xC9, "");
		disassembly.put(0xCA, "");
		disassembly.put(0xCB, "");
		disassembly.put(0xCC, "");
		disassembly.put(0xCD, "");
		disassembly.put(0xCE, "");
		disassembly.put(0xCF, "");
		disassembly.put(0xD0, "");
		disassembly.put(0xD1, "");
		disassembly.put(0xD2, "");
		disassembly.put(0xD3, "");
		disassembly.put(0xD4, "");
		disassembly.put(0xD5, "");
		disassembly.put(0xD6, "");
		disassembly.put(0xD7, "");
		disassembly.put(0xD8, "");
		disassembly.put(0xD9, "");
		disassembly.put(0xDA, "");
		disassembly.put(0xDB, "");
		disassembly.put(0xDC, "");
		disassembly.put(0xDD, "");
		disassembly.put(0xDE, "");
		disassembly.put(0xDF, "");
		disassembly.put(0xE0, "");
		disassembly.put(0xE1, "");
		disassembly.put(0xE2, "");
		disassembly.put(0xE3, "");
		disassembly.put(0xE4, "");
		disassembly.put(0xE5, "");
		disassembly.put(0xE6, "");
		disassembly.put(0xE7, "");
		disassembly.put(0xE8, "");
		disassembly.put(0xE9, "");
		disassembly.put(0xEA, "");
		disassembly.put(0xEB, "");
		disassembly.put(0xEC, "");
		disassembly.put(0xED, "");
		disassembly.put(0xEE, "");
		disassembly.put(0xEF, "");
		disassembly.put(0xF0, "");
		disassembly.put(0xF1, "");
		disassembly.put(0xF2, "");
		disassembly.put(0xF3, "");
		disassembly.put(0xF4, "");
		disassembly.put(0xF5, "");
		disassembly.put(0xF6, "");
		disassembly.put(0xF7, "");
		disassembly.put(0xF8, "");
		disassembly.put(0xF9, "");
		disassembly.put(0xFA, "");
		disassembly.put(0xFB, "");
		disassembly.put(0xFC, "");
		disassembly.put(0xFD, "");
		disassembly.put(0xFE, "");
		disassembly.put(0xFF, "");
	}
}
