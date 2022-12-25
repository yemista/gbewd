package gbewd.cpu.operation.undefined;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.mmu.Mmu;

public class UndefinedOperation extends BaseOperation {

	public UndefinedOperation(int opcode, int pcIncrement, int cycles) {
		super(opcode, pcIncrement, cycles);
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		System.out.println("UNDEFINED OPERATION at " + cpu.getRegisters().get(Z80.Registers.PC).getValue());
		cpu.halt();
	}
	
	public static Map<Integer, OperationExecution> getAllUndefinedOperation() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		operations.put(0xD3, new UndefinedOperation(0xD3, 0, 1));
		operations.put(0xDB, new UndefinedOperation(0xDB, 0, 1));
		operations.put(0xDD, new UndefinedOperation(0xDD, 0, 1));
		operations.put(0xE3, new UndefinedOperation(0xE3, 0, 1));
		operations.put(0xE4, new UndefinedOperation(0xE4, 0, 1));
		operations.put(0xEB, new UndefinedOperation(0xEB, 0, 1));
		operations.put(0xEC, new UndefinedOperation(0xEC, 0, 1));
		operations.put(0xED, new UndefinedOperation(0xED, 0, 1));
		operations.put(0xF4, new UndefinedOperation(0xF4, 0, 1));
		operations.put(0xF9, new UndefinedOperation(0xF9, 0, 1));
		operations.put(0xFC, new UndefinedOperation(0xFC, 0, 1));
		operations.put(0xFD, new UndefinedOperation(0xFD, 0, 1));
		operations.put(0xCB26, new UndefinedOperation(0xCB26, 0, 1));
		operations.put(0xCB2E, new UndefinedOperation(0xCB2E, 0, 1));
		operations.put(0xCB36, new UndefinedOperation(0xCB36, 0, 1));
		operations.put(0xCB3E, new UndefinedOperation(0xCB3E, 0, 1));
		return operations;
	}
}
