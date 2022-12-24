package gbewd.cpu.operation.add;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class MiscAddOperations {
	public static Map<Integer, OperationExecution> getAllMiscAddOperations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		// ADDHL: function() { var a=Z80._r.a; var m=MMU.rb((Z80._r.h<<8)+Z80._r.l); Z80._r.a+=m; Z80._r.f=(Z80._r.a>255)?0x10:0; Z80._r.a&=255; 
		//      if(!Z80._r.a) Z80._r.f|=0x80; if((Z80._r.a^a^m)&0x10) Z80._r.f|=0x20; Z80._r.m=2; },
		operations.put(0x86, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				int aOrig = a.getValue();
				Register h = cpu.getRegisters().get(Z80.Registers.H);
				Register l = cpu.getRegisters().get(Z80.Registers.L);
				int address = (h.getValue() << 8) + l.getValue();
				int value = mmu.readByte(address);
				a.setValue(a.getValue() + value);
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				if (a.getValue() > 255) {
					f.setValue(0x10);
				} else {
					f.setValue(0);
				}
				a.setValue(a.getValue() & 255);
				if (a.getValue() != 0) {
					f.setValue(f.getValue() | 0x80);
				}
				if (((a.getValue() ^ aOrig ^ value) & 0x10) != 0) {
					f.setValue(f.getValue() | 0x20);
				}
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(2);
			}
		});
		// ADDn: function() { var a=Z80._r.a; var m=MMU.rb(Z80._r.pc); Z80._r.a+=m; Z80._r.pc++; Z80._r.f=(Z80._r.a>255)?0x10:0; Z80._r.a&=255; 
		//     if(!Z80._r.a) Z80._r.f|=0x80; if((Z80._r.a^a^m)&0x10) Z80._r.f|=0x20; Z80._r.m=2; },
		operations.put(0xC6, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				int aOrig = a.getValue();
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				int address = pc.getValue();
				int value = mmu.readByte(address);
				a.setValue(a.getValue() + value);
				pc.setValue(pc.getValue() + 1);
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				if (a.getValue() > 255) {
					f.setValue(0x10);
				} else {
					f.setValue(0);
				}
				a.setValue(a.getValue() & 255);
				if (a.getValue() != 0) {
					f.setValue(f.getValue() | 0x80);
				}
				if (((a.getValue() ^ aOrig ^ value) & 0x10) != 0) {
					f.setValue(f.getValue() | 0x20);
				}
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(2);
			}
		});
		// ADDSPn: function() { var i=MMU.rb(Z80._r.pc); if(i>127) i=-((~i+1)&255); Z80._r.pc++; Z80._r.sp+=i; Z80._r.m=4; },
		operations.put(0xE8, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				int address = pc.getValue();
				int value = mmu.readByte(address);
				if (value > 127) {
					value = -((~value + 1) & 255);
				}
				pc.setValue(pc.getValue() + 1);
				Register sp = cpu.getRegisters().get(Z80.Registers.SP);
				sp.setValue(sp.getValue() + value);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(4);
			}
		});
		return operations;
	}
}
