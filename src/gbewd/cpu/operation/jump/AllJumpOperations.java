package gbewd.cpu.operation.jump;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class AllJumpOperations {
	
	public static Map<Integer, OperationExecution> getAllJumpOperations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		// JPnn: function() { Z80._r.pc = MMU.rw(Z80._r.pc); Z80._r.m=3; },
		operations.put(0xC3, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				int value = mmu.readWord(pc.getValue());
				pc.setValue(value);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(3);
			}
		});
		// JPHL: function() { Z80._r.pc=(Z80._r.h<<8)+Z80._r.l; Z80._r.m=1; },
		operations.put(0xE9, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				Register h = cpu.getRegisters().get(Z80.Registers.H);
				Register l = cpu.getRegisters().get(Z80.Registers.L);
				int value = (h.getValue() << 8) + l.getValue();
				pc.setValue(value);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(1);
			}
		});
		// JPNZnn: function() { Z80._r.m=3; if((Z80._r.f&0x80)==0x00) { Z80._r.pc=MMU.rw(Z80._r.pc); Z80._r.m++; } else Z80._r.pc+=2; },
		operations.put(0xC2, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(3);
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				if ((f.getValue() & 0x80) == 0x00) {
					int value = mmu.readWord(pc.getValue());
					pc.setValue(value);
					m.setValue(m.getValue() + 1);
				} else {
					pc.setValue(pc.getValue() + 2);
				}
			}
		});
		// JPZnn: function()  { Z80._r.m=3; if((Z80._r.f&0x80)==0x80) { Z80._r.pc=MMU.rw(Z80._r.pc); Z80._r.m++; } else Z80._r.pc+=2; },
		operations.put(0xCA, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(3);
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				if ((f.getValue() & 0x80) == 0x80) {
					int value = mmu.readWord(pc.getValue());
					pc.setValue(value);
					m.setValue(m.getValue() + 1);
				} else {
					pc.setValue(pc.getValue() + 2);
				}
			}
		});
		// JPNCnn: function() { Z80._r.m=3; if((Z80._r.f&0x10)==0x00) { Z80._r.pc=MMU.rw(Z80._r.pc); Z80._r.m++; } else Z80._r.pc+=2; },
		operations.put(0xD2, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(3);
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				if ((f.getValue() & 0x10) == 0x00) {
					int value = mmu.readWord(pc.getValue());
					pc.setValue(value);
					m.setValue(m.getValue() + 1);
				} else {
					pc.setValue(pc.getValue() + 2);
				}
			}
		});
		//  JPCnn: function()  { Z80._r.m=3; if((Z80._r.f&0x10)==0x10) { Z80._r.pc=MMU.rw(Z80._r.pc); Z80._r.m++; } else Z80._r.pc+=2; },
		operations.put(0xDA, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(3);
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				if ((f.getValue() & 0x10) == 0x10) {
					int value = mmu.readWord(pc.getValue());
					pc.setValue(value);
					m.setValue(m.getValue() + 1);
				} else {
					pc.setValue(pc.getValue() + 2);
				}
			}
		});
		//  JRn: function() { var i=MMU.rb(Z80._r.pc); if(i>127) i=-((~i+1)&255); Z80._r.pc++; Z80._r.m=2; Z80._r.pc+=i; Z80._r.m++; },
		operations.put(0x18, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				int value = mmu.readWord(pc.getValue());
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				if (value > 127) {
					value = -((~value + 1) & 255);
				}
				pc.setValue(pc.getValue() + 1);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(2);
				pc.setValue(pc.getValue() + value);
				m.setValue(m.getValue() + 1);
				
			}
		});
		//  JRNZn: function() { var i=MMU.rb(Z80._r.pc); if(i>127) i=-((~i+1)&255); Z80._r.pc++; Z80._r.m=2; if((Z80._r.f&0x80)==0x00) { Z80._r.pc+=i; Z80._r.m++; } },
		operations.put(0x20, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				int value = mmu.readWord(pc.getValue());
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				if (value > 127) {
					value = -((~value + 1) & 255);
				}
				pc.setValue(pc.getValue() + 1);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(2);
				if ((f.getValue() & 0x80) == 0) {
					pc.setValue(pc.getValue() + value);
					m.setValue(m.getValue() + 1);
				}
			}
		});
		//  JRNCn: function() { var i=MMU.rb(Z80._r.pc); if(i>127) i=-((~i+1)&255); Z80._r.pc++; Z80._r.m=2; if((Z80._r.f&0x10)==0x00) { Z80._r.pc+=i; Z80._r.m++; } },
		operations.put(0x30, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				int value = mmu.readWord(pc.getValue());
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				if (value > 127) {
					value = -((~value + 1) & 255);
				}
				pc.setValue(pc.getValue() + 1);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(2);
				if ((f.getValue() & 0x10) == 0) {
					pc.setValue(pc.getValue() + value);
					m.setValue(m.getValue() + 1);
				}
			}
		});
		//  JRCn: function()  { var i=MMU.rb(Z80._r.pc); if(i>127) i=-((~i+1)&255); Z80._r.pc++; Z80._r.m=2; if((Z80._r.f&0x10)==0x10) { Z80._r.pc+=i; Z80._r.m++; } },
		operations.put(0x38, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				int value = mmu.readWord(pc.getValue());
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				if (value > 127) {
					value = -((~value + 1) & 255);
				}
				pc.setValue(pc.getValue() + 1);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(2);
				if ((f.getValue() & 0x10) == 0x10) {
					pc.setValue(pc.getValue() + value);
					m.setValue(m.getValue() + 1);
				}
			}
		});
		//  DJNZn: function() { var i=MMU.rb(Z80._r.pc); if(i>127) i=-((~i+1)&255); Z80._r.pc++; Z80._r.m=2; Z80._r.b--; if(Z80._r.b) { Z80._r.pc+=i; Z80._r.m++; } },
		operations.put(0x10, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				int value = mmu.readWord(pc.getValue());
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				if (value > 127) {
					value = -((~value + 1) & 255);
				}
				pc.setValue(pc.getValue() + 1);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(2);
				Register b = cpu.getRegisters().get(Z80.Registers.B);
				b.setValue(b.getValue() - 1);
				if (b.getValue() != 0) {
					pc.setValue(pc.getValue() + value);
					m.setValue(m.getValue() + 1);
				}
			}
		});
		//  DJNZn: function() { var i=MMU.rb(Z80._r.pc); if(i>127) i=-((~i+1)&255); Z80._r.pc++; Z80._r.m=2; Z80._r.b--; if(Z80._r.b) { Z80._r.pc+=i; Z80._r.m++; } },
		operations.put(0x28, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				int value = mmu.readByte(pc.getValue());
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				if (value > 127) {
					value = -((~value + 1) & 255);
				}
				pc.setValue(pc.getValue() + 1);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				if ((f.getValue() & 0x80) == 0x80) {
					pc.setValue(pc.getValue() + value);
					m.setValue(m.getValue() + 1);
				}
			}
		});
		return operations;
	}
}
