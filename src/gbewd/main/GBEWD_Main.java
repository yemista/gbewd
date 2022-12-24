package gbewd.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.operations.AllOperations;

public class GBEWD_Main {
	public static void main(String[] args) {
		Map<Integer, OperationExecution> allOps = AllOperations.getAllOperations();
		List<Integer> keySet = new ArrayList<>(allOps.keySet());
		Collections.sort(keySet);
		
		for (Integer key : keySet) {
			System.out.println(String.format("%x", key));
		}
		
		System.out.println(keySet.size());
	}
}
