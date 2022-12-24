package gbewd.cpu.operation.daa;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class DAA_Operation extends BaseOperation {

	public DAA_Operation(int opcode, int pcIncrement, int cycles) {
		super(opcode, pcIncrement, cycles);
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register a = cpu.getRegisters().get(Z80.Registers.A);
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		int aOrigValue = a.getValue();
		if (((f.getValue() & 0x20) != 0) || ((a.getValue() & 15) > 9)) {
			a.setValue(a.getValue() + 6);
		}
		f.setValue(f.getValue() & 0xEF);
		if (((f.getValue() & 0x20) != 0) || aOrigValue > 0x99) {
			a.setValue(a.getValue() + 0x60);
			f.setValue(f.getValue() | 0x10);
		}
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllDAAOperations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		operations.put(0x27, new DAA_Operation(0x27, 0, 1));
		return operations;
	}

}
