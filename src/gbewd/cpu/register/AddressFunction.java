package gbewd.cpu.register;

import gbewd.cpu.Z80;

public interface AddressFunction {
	int calcAddress(Z80 cpu, Z80.Registers high, Z80.Registers low);
}
