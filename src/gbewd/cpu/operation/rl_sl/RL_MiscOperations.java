package gbewd.cpu.operation.rl_sl;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class RL_MiscOperations {

	public static Map<Integer, OperationExecution> getAllMiscLoadOperations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		// RLA: function() { var ci=Z80._r.f&0x10?1:0; var co=Z80._r.a&0x80?0x10:0; Z80._r.a=(Z80._r.a<<1)+ci; Z80._r.a&=255; Z80._r.f=(Z80._r.f&0xEF)+co; Z80._r.m=1; },
		operations.put(0x17, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				int carryIn = 0;
				if ((f.getValue() & 0x10) != 0) {
					carryIn = 1;
				}
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				int carryOut = 0;
				if ((a.getValue() & 0x80) != 0) {
					carryOut = 0x10;
				}
				a.setValue((a.getValue() << 1) + carryIn);
				f.setValue((f.getValue() & 0xEF) + carryOut);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(1);
			}
		});
		// RLCA: function() { var ci=Z80._r.a&0x80?1:0; var co=Z80._r.a&0x80?0x10:0; Z80._r.a=(Z80._r.a<<1)+ci; Z80._r.a&=255; Z80._r.f=(Z80._r.f&0xEF)+co; Z80._r.m=1; },
		operations.put(0x1F, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				int carryIn = 0;
				if ((a.getValue() & 0x80) != 0) {
					carryIn = 1;
				}
				int carryOut = 0;
				if ((a.getValue() & 0x80) != 0) {
					carryOut = 0x10;
				}
				a.setValue((a.getValue() << 1) + carryIn);
				f.setValue((f.getValue() & 0xEF) + carryOut);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(1);
			}
		});
		// RRA: function() { var ci=Z80._r.f&0x10?0x80:0; var co=Z80._r.a&1?0x10:0; Z80._r.a=(Z80._r.a>>1)+ci; Z80._r.a&=255; Z80._r.f=(Z80._r.f&0xEF)+co; Z80._r.m=1; },
		operations.put(0x0F, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				int carryIn = 0;
				if ((f.getValue() & 0x10) != 0) {
					carryIn = 0x80;
				}
				int carryOut = 0;
				if ((a.getValue() & 1) != 0) {
					carryOut = 0x10;
				}
				a.setValue((a.getValue() >> 1) + carryIn);
				f.setValue((f.getValue() & 0xEF) + carryOut);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(1);
			}
		});
		// RRCA: function() { var ci=Z80._r.a&1?0x80:0; var co=Z80._r.a&1?0x10:0; Z80._r.a=(Z80._r.a>>1)+ci; Z80._r.a&=255; Z80._r.f=(Z80._r.f&0xEF)+co; Z80._r.m=1;
		operations.put(0x00, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				Register f = cpu.getRegisters().get(Z80.Registers.F);
				Register a = cpu.getRegisters().get(Z80.Registers.A);
				int carryIn = 0;
				if ((a.getValue() & 1) != 0) {
					carryIn = 0x80;
				}
				int carryOut = 0;
				if ((a.getValue() & 1) != 0) {
					carryOut = 0x10;
				}
				a.setValue((a.getValue() >> 1) + carryIn);
				f.setValue((f.getValue() & 0xEF) + carryOut);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(1);
			}
		});
		return operations;
	}
}
