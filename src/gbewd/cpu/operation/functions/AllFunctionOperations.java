package gbewd.cpu.operation.functions;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;
import gbewd.util.ConditionalFunctions;

public class AllFunctionOperations {
	public static Map<Integer, OperationExecution> getAllFunctionOperations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		operations.put(0xCD, new CALL_Operation(0xCD, 0, 0, ConditionalFunctions.nop));
		operations.put(0xC4, new CALL_Operation(0xC4, 0, 0, ConditionalFunctions.compareF_0x80To0x00));
		operations.put(0xCC, new CALL_Operation(0xCC, 0, 0, ConditionalFunctions.compareF_0x80To0x80));
		operations.put(0xD4, new CALL_Operation(0xD4, 0, 0, ConditionalFunctions.compareF_0x10To0x00));
		operations.put(0xDC, new CALL_Operation(0xDC, 0, 0, ConditionalFunctions.compareF_0x10To0x10));
		
		operations.put(0xC9, new RET_Operation(0xC9, 0, 0, ConditionalFunctions.nop));
		operations.put(0xC0, new RET_Operation(0xC0, 0, 0, ConditionalFunctions.compareF_0x80To0x00));
		operations.put(0xC8, new RET_Operation(0xC8, 0, 0, ConditionalFunctions.compareF_0x80To0x80));
		operations.put(0xD0, new RET_Operation(0xD0, 0, 0, ConditionalFunctions.compareF_0x10To0x00));
		operations.put(0xD8, new RET_Operation(0xD8, 0, 0, ConditionalFunctions.compareF_0x10To0x10));
		
		// RETI
		operations.put(0xD9, new OperationExecution() {
			@Override
			public void doOperation(Z80 cpu, Mmu mmu) {
				cpu.setIME();
				cpu.rrs();
				Register sp = cpu.getRegisters().get(Z80.Registers.SP);
				Register pc = cpu.getRegisters().get(Z80.Registers.PC);
				pc.setValue(mmu.readWord(sp.getValue()));
				sp.setValue(sp.getValue() + 2);
				Register m = cpu.getRegisters().get(Z80.Registers.M);
				m.setValue(3);
			}
		});
		return operations;
	}
}
