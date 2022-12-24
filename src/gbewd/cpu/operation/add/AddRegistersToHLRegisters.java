package gbewd.cpu.operation.add;

import java.util.HashMap;
import java.util.Map;

import gbewd.cpu.Z80;
import gbewd.cpu.operation.BaseOperation;
import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.register.Register;
import gbewd.mmu.Mmu;

public class AddRegistersToHLRegisters extends BaseOperation {
	private Z80.Registers bigAddend;
	private Z80.Registers smallAddend;
	
	public AddRegistersToHLRegisters(int opcode, int pcIncrement, int cycles, Z80.Registers bigAddend, Z80.Registers smallAddend) {
		super(opcode, pcIncrement, cycles);
		this.bigAddend = bigAddend;
		this.smallAddend = smallAddend;
	}
	
	@Override
	public void doOperation(Z80 cpu, Mmu mmu) {
		Register h = cpu.getRegisters().get(Z80.Registers.H);
		Register l = cpu.getRegisters().get(Z80.Registers.L);
		int hl = (h.getValue() << 8) + l.getValue();
		Register bigAddendRegister = cpu.getRegisters().get(bigAddend);
		Register smallAddendRegister = cpu.getRegisters().get(smallAddend);
		int value = ((h.getValue() << 8) & 0xFF00) + smallAddendRegister.getValue();
		Register f = cpu.getRegisters().get(Z80.Registers.F);
		if (hl > 65535) {
			f.setValue(f.getValue() | 0x10);
		} else {
			f.setValue(f.getValue() & 0xEF);
		}
		h.setValue((hl >> 8) & 255);
		l.setValue(hl & 255);
		commonWork(cpu);
	}
	
	public static Map<Integer, OperationExecution> getAllAddRegistersToHLRegistersOperations() {
		Map<Integer, OperationExecution> operations = new HashMap<>();
		operations.put(0x09, new AddRegistersToHLRegisters(0x09, 0, 3, Z80.Registers.B, Z80.Registers.C));
		operations.put(0x19, new AddRegistersToHLRegisters(0x19, 0, 3, Z80.Registers.D, Z80.Registers.E));
		operations.put(0x29, new AddRegistersToHLRegisters(0x29, 0, 3, Z80.Registers.H, Z80.Registers.L));
		operations.put(0x39, new AddRegistersToHLRegisters(0x39, 0, 3, Z80.Registers.SP, Z80.Registers.SP));
		return operations;
	}
}
