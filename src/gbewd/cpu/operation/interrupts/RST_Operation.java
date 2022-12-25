package gbewd.cpu.operation.interrupts;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class RST_Operation extends BaseOperation {
	private int interruptNo;
	
	public RST_Operation(int opcode, int pcIncrement, int cycles, int interruptNo) {
		super(opcode, pcIncrement, cycles);
		this.interruptNo = interruptNo;
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		cpu.rsv();
		Register sp = cpu.getRegisters().get(Z80.Registers.SP);
		Register pc = cpu.getRegisters().get(Z80.Registers.PC);
		sp.setValue(sp.getValue() - 2);
		mmu.writeWord(sp.getValue(), pc.getValue());
		pc.setValue(interruptNo);
		Register m = cpu.getRegisters().get(Z80.Registers.M);	
		commonWork(cpu);
	}
		
	public static Map<Integer, OperationExecution> getAllRST_Operations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		operations.put(0xC7, new RST_Operation(0x00, 0, 3, 0x00));
		operations.put(0xCF, new RST_Operation(0x00, 0, 3, 0x08));
		operations.put(0xD7, new RST_Operation(0x00, 0, 3, 0x10));
		operations.put(0xDF, new RST_Operation(0x00, 0, 3, 0x18));
		operations.put(0xE7, new RST_Operation(0x00, 0, 3, 0x20));
		operations.put(0xEF, new RST_Operation(0x00, 0, 3, 0x28));
		operations.put(0xF7, new RST_Operation(0x00, 0, 3, 0x30));
		operations.put(0xFF, new RST_Operation(0x00, 0, 3, 0x38));
		return operations;
	}
}
