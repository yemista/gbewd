package gbewd.cpu.operation.load.special;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class MiscellaneousLoadInstructions {
	public static Map<Integer, OperationExecution> getAllMiscLoadOperations() {
		Map<Integer, OperationExecution> baseOperations = new HashMap<>();
		// LDAmm: function() { Z80._r.a=MMU.rb(MMU.rw(Z80._r.pc)); Z80._r.pc+=2; Z80._r.m=4; },
		baseOperations.put(0xFA, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				int address = mmu.readWord(pc.getValue());
				int value = mmu.readByte(address);
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				pc.setValue(pc.getValue() + 2);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(4);
			}
		});
		// LDSPnn: function() { Z80._r.sp=MMU.rw(Z80._r.pc); Z80._r.pc+=2; Z80._r.m=3; },
		baseOperations.put(0x31, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				int value = mmu.readWord(pc.getValue());
				Register sp = cpu.getRegisters().get(Z80.Registers.SP);
				pc.setValue(pc.getValue() + 2);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(3);
			}
		});
		// LDHLIA: function() { MMU.wb((Z80._r.h<<8)+Z80._r.l, Z80._r.a); Z80._r.l=(Z80._r.l+1)&255; if(!Z80._r.l) Z80._r.h=(Z80._r.h+1)&255; Z80._r.m=2; },
		baseOperations.put(0x22, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register h = cpu.getRegisters().get(Z80.Registers.H);
				Register l = cpu.getRegisters().get(Z80.Registers.L);
				int address = (h.getValue() << 8) + l.getValue();
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				int value = a.getValue();
				mmu.writeByte(address, value);
				l.setValue((l.getValue() + 1) & 255);
				if (l.getValue() == 0) {
					h.setValue((h.getValue() + 1) & 255);
				}
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(2);
			}
		});
		// LDAHLI: function() { Z80._r.a=MMU.rb((Z80._r.h<<8)+Z80._r.l); Z80._r.l=(Z80._r.l+1)&255; if(!Z80._r.l) Z80._r.h=(Z80._r.h+1)&255; Z80._r.m=2; },
		baseOperations.put(0x2A, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register h = cpu.getRegisters().get(Z80.Registers.H);
				Register l = cpu.getRegisters().get(Z80.Registers.L);
				int address = (h.getValue() << 8) + l.getValue();
				int value = mmu.readByte(address);
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				a.setValue(value);
				l.setValue((l.getValue() + 1) & 255);
				if (l.getValue() == 0) {
					h.setValue((h.getValue() + 1) & 255);
				}
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(2);
			}
		});
		// LDHLDA: function() { MMU.wb((Z80._r.h<<8)+Z80._r.l, Z80._r.a); Z80._r.l=(Z80._r.l-1)&255; if(Z80._r.l==255) Z80._r.h=(Z80._r.h-1)&255; Z80._r.m=2; },
		baseOperations.put(0x32, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register h = cpu.getRegisters().get(Z80.Registers.H);
				Register l = cpu.getRegisters().get(Z80.Registers.L);
				int address = (h.getValue() << 8) + l.getValue();
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				int value = a.getValue();
				mmu.writeByte(address, value);
				l.setValue((l.getValue() - 1) & 255);
				if (l.getValue() == 0) {
					h.setValue((h.getValue() - 1) & 255);
				}
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(2);
			}
		});
		// LDAHLD: function() { Z80._r.a=MMU.rb((Z80._r.h<<8)+Z80._r.l); Z80._r.l=(Z80._r.l-1)&255; if(Z80._r.l==255) Z80._r.h=(Z80._r.h-1)&255; Z80._r.m=2; },
		baseOperations.put(0x3A, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register h = cpu.getRegisters().get(Z80.Registers.H);
				Register l = cpu.getRegisters().get(Z80.Registers.L);
				int address = (h.getValue() << 8) + l.getValue();
				int value = mmu.readByte(address);
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				a.setValue(value);
				l.setValue((l.getValue() - 1) & 255);
				if (l.getValue() == 0) {
					h.setValue((h.getValue() - 1) & 255);
				}
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(2);
			}
		});
		// LDAIOn: function() { Z80._r.a=MMU.rb(0xFF00+MMU.rb(Z80._r.pc)); Z80._r.pc++; Z80._r.m=3; },
		baseOperations.put(0xF0, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				int address = mmu.readByte(pc.getValue());
				address += 0xFF00;
				int value = mmu.readByte(address);
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				a.setValue(value);
				pc.setValue(pc.getValue() + 1);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(3);
			}
		});
		// LDIOnA: function() { MMU.wb(0xFF00+MMU.rb(Z80._r.pc),Z80._r.a); Z80._r.pc++; Z80._r.m=3; },
		baseOperations.put(0xE0, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				int address = mmu.readByte(pc.getValue());
				address += 0xFF00;
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				int value = a.getValue();
				mmu.writeByte(address, value);
				pc.setValue(pc.getValue() + 1);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(3);
			}
		});
		// LDAIOC: function() { Z80._r.a=MMU.rb(0xFF00+Z80._r.c); Z80._r.m=2; },
		baseOperations.put(0xF2, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register c = cpu.getRegisters().get(Z80.Registers.C);
				int address = c.getValue();
				address += 0xFF00;
				int value = mmu.readByte(address);
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				a.setValue(value);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(2);
			}
		});
		// LDIOCA: function() { MMU.wb(0xFF00+Z80._r.c,Z80._r.a); Z80._r.m=2; },
		baseOperations.put(0xE2, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register c = cpu.getRegisters().get(Z80.Registers.C);
				int address = c.getValue();
				address += 0xFF00;
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				int value = a.getValue();
				mmu.writeByte(address, value);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(2);
			}
		});
		// LDHLSPn: function() { var i=MMU.rb(Z80._r.pc); if(i>127) i=-((~i+1)&255); Z80._r.pc++; i+=Z80._r.sp; Z80._r.h=(i>>8)&255; Z80._r.l=i&255; Z80._r.m=3; },
		baseOperations.put(0xF8, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				int value = mmu.readByte(pc.getValue());
				if (value > 127) {
					value = -((~value + 1) & 255);
				}
				pc.setValue(pc.getValue() + 1);
				Register sp = cpu.getRegisters().get(Z80.Registers.SP);
				value += sp.getValue();
				Register h = cpu.getRegisters().get(Z80.Registers.H);
				h.setValue((value >> 8) & 255);
				Register l = cpu.getRegisters().get(Z80.Registers.L);
				l.setValue(value & 255);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(3);
			}
		});
		return baseOperations;
	}
}
