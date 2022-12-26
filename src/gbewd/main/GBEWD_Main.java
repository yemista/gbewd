package gbewd.main;

import gbewd.emulator.Emulator;

public class GBEWD_Main {
	
	public static void main(String[] args) {
		Emulator emulator = new Emulator();
		
		if (args.length == 1) {
			emulator.loadRom(args[0]);
		}
		
		emulator.exec();
	}
}
