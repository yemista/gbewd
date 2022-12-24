package gbewd.cpu.operation.stack;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.OperationExecution;

public class AllStackOperations {
	
	public static Map<Integer, OperationExecution> getAllStackOperations() {
		Map<Integer, OperationExecution> operations = new HashMap<>(); 
		operations.put(0xC5, new PUSH_Operation(0xC5, 0, 3, Z80.Registers.B, Z80.Registers.C));
		operations.put(0xD5, new PUSH_Operation(0xD5, 0, 3, Z80.Registers.D, Z80.Registers.E));
		operations.put(0xE5, new PUSH_Operation(0xE5, 0, 3, Z80.Registers.H, Z80.Registers.L));
		operations.put(0xF5, new PUSH_Operation(0xF5, 0, 3, Z80.Registers.A, Z80.Registers.F));
		
		operations.put(0xC1, new POP_Operation(0xC1, 0, 3, Z80.Registers.C, Z80.Registers.B));
		operations.put(0xD1, new POP_Operation(0xD1, 0, 3, Z80.Registers.E, Z80.Registers.D));
		operations.put(0xE1, new POP_Operation(0xE1, 0, 3, Z80.Registers.L, Z80.Registers.H));
		operations.put(0xF1, new POP_Operation(0xF1, 0, 3, Z80.Registers.F, Z80.Registers.A));
		return operations;
	}
}
