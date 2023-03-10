package gbewd.cpu.operation.swap;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class Swap extends BaseOperation implements OperationExecution {
	private Z80.Registers registerName;
	
	public Swap(int opcode, int pcIncrement, int cycles, Z80.Registers registerName) {
		super(opcode, pcIncrement, cycles);
		this.registerName = registerName;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register register = cpu.getRegisters().get(registerName);
		int value = register.getValue();
		value = ((value & 0x0F) << 4) | ((value & 0xF0) >> 4);
		register.setValue(value);
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		if (value == 0) {
			f.setValue(0);
		} else {
			f.setValue(0x80);
		}
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllSWAP_Operations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		operations.put(0xCB30, new Swap(0xCB30, 0, 1, Z80.Registers.B));
		operations.put(0xCB31, new Swap(0xCB31, 0, 1, Z80.Registers.C));
		operations.put(0xCB32, new Swap(0xCB32, 0, 1, Z80.Registers.D));
		operations.put(0xCB33, new Swap(0xCB33, 0, 1, Z80.Registers.E));
		operations.put(0xCB34, new Swap(0xCB34, 0, 1, Z80.Registers.H));
		operations.put(0xCB35, new Swap(0xCB35, 0, 1, Z80.Registers.L));
		operations.put(0xCB37, new Swap(0xCB37, 0, 1, Z80.Registers.A));
		return operations;
	}
}
