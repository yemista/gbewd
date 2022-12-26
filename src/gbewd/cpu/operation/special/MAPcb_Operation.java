package gbewd.cpu.operation.special;

import java.util.Map;

import gbewd.breakpoints.BreakpointException;
import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.operations.AllOperations;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class MAPcb_Operation extends BaseOperation {

	public MAPcb_Operation(int opcode, int pcIncrement, int cycles) {
		super(opcode, pcIncrement, cycles);
	}

	@Override
	public void doOperation(Z80 cpu, Mmu mmu) throws BreakpointException {
		Register pc = cpu.getRegisters().get(Z80.Registers.PC);
		int pcValue = pc.getValue();
		pc.setValue(pc.getValue() + 1);
		Map<Integer, OperationExecution> allOperation = AllOperations.getAllOperations();
		int cbOpcode = 0xCB00 + pcValue;
		
		if (allOperation.get(cbOpcode) == null) {
			System.out.println("NULL CB op");
			throw new RuntimeException();	
		}
		
		allOperation.get(cbOpcode).doOperation(cpu, mmu);
	}
}
