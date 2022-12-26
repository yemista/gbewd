package gbewd.breakpoints.diagnostics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.operations.AllOperations;

public class Diagnostics {
	
	public static void operationDiagnostics() {
		Map<Integer, OperationExecution> allOps = AllOperations.getAllOperations();
		List<Integer> keySet = new ArrayList<>(allOps.keySet());
		Collections.sort(keySet);
		
		System.out.println("All instructions:");
		for (Integer key : keySet) {
			System.out.println(String.format("%x", key));
		}
		
		System.out.println("Missing instructions:");
		for (int i = 0; i < 256; ++i) {
			if (allOps.get(i) == null) {
				System.out.println(String.format("%x", i));
			}
		}
		for (int i = 0xCB00; i < (0xCB00 + 256); ++i) {
			if (allOps.get(i) == null) {
				System.out.println(String.format("%x",i));
			}
		}
		
		System.out.println("Total operations: " + keySet.size());
	}
}
