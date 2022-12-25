package gbewd.cpu.operation.special;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.operation.OperationExecution;

public class AllSpecialOperations {
	
	public static Map<Integer, OperationExecution> getAllSpecialOperations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		operations.put(0x3F, new CCF_Operation(0x3F, 0, 1));
		operations.put(0x37, new SCF_Operation(0x37, 0, 1));
		operations.put(0x2F, new CPL_Operation(0x2F, 0, 1));
		operations.put(0x76, new HALT_Operation(0x76, 0, 1));
		operations.put(0x00, new NOP_Operation(0x00, 0, 1));
		operations.put(0xF3, new DI_Operation(0xF3, 0, 1));
		operations.put(0xFB, new EI_Operation(0xFB, 0, 1));
		operations.put(0xCB, new MAPcb_Operation(0xCB, 0, 0));
		return operations;
	}
	
}
