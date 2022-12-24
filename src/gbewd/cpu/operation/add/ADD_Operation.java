package gbewd.cpu.operation.add;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class ADD_Operation extends BaseOperation {
	private Z80.Registers addend;
	private Z80.Registers target;
	
	public ADD_Operation(int opcode, int pcIncrement, int cycles, Z80.Registers addend) {
		super(opcode, pcIncrement, cycles);
		this.addend = addend;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register a = cpu.getRegisters().get(Z80.Registers.A);
		Register addendRegister = cpu.getRegisters().get(addend);
		int value = a.getValue() + addendRegister.getValue();
		a.setValue(value);
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		if (value > 255) {
			f.setValue(0x10);
		} else {
			f.setValue(0x00);
		}
		value = value & 255;
		if (value == 0) {
			f.setValue(f.getValue() | 0x80);
		}
		if (((a.getValue() ^ addendRegister.getValue() ^ value) & 0x10) != 0) {
			f.setValue(f.getValue() | 0x20);
		}
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllADD_Operations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		operations.put(0x80, new ADD_Operation(0x80, 0, 1, Z80.Registers.B));
		operations.put(0x81, new ADD_Operation(0x81, 0, 1, Z80.Registers.C));
		operations.put(0x82, new ADD_Operation(0x82, 0, 1, Z80.Registers.D));
		operations.put(0x83, new ADD_Operation(0x83, 0, 1, Z80.Registers.E));
		operations.put(0x84, new ADD_Operation(0x84, 0, 1, Z80.Registers.H));
		operations.put(0x85, new ADD_Operation(0x85, 0, 1, Z80.Registers.L));
		operations.put(0x87, new ADD_Operation(0x87, 0, 1, Z80.Registers.A));
		return operations;
	}

}
