package gbewd.cpu.operations;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import gbewd.cpu.operation.OperationExecution;
import gbewd.cpu.operation.add.ADC_Operation;
import gbewd.cpu.operation.add.ADD_Operation;
import gbewd.cpu.operation.add.AddRegistersToHLRegisters;
import gbewd.cpu.operation.add.MiscAddOperations;
import gbewd.cpu.operation.bit.BIT_Operation;
import gbewd.cpu.operation.bit.RES_Operation;
import gbewd.cpu.operation.bit.SET_Operation;
import gbewd.cpu.operation.cp.CP_Operation;
import gbewd.cpu.operation.daa.DAA_Operation;
import gbewd.cpu.operation.functions.AllFunctionOperations;
import gbewd.cpu.operation.incdec.DoubleIncDecOperation;
import gbewd.cpu.operation.incdec.INCDEC_Operation;
import gbewd.cpu.operation.interrupts.RST_Operation;
import gbewd.cpu.operation.jump.AllJumpOperations;
import gbewd.cpu.operation.load.generic.LDmr_Operation;
import gbewd.cpu.operation.load.generic.LDrm_Operation;
import gbewd.cpu.operation.load.generic.LDrr_Operation;
import gbewd.cpu.operation.load.special.LoadTwoRegistersFromPc;
import gbewd.cpu.operation.load.special.MiscellaneousLoadInstructions;
import gbewd.cpu.operation.logic.LogicOperation;
import gbewd.cpu.operation.rl_sl.RL_MiscOperations;
import gbewd.cpu.operation.rl_sl.RL_RR_Operation;
import gbewd.cpu.operation.rl_sl.SL_Operation;
import gbewd.cpu.operation.rl_sl.SR_Operation;
import gbewd.cpu.operation.special.AllSpecialOperations;
import gbewd.cpu.operation.stack.AllStackOperations;
import gbewd.cpu.operation.sub.SBC_Operation;
import gbewd.cpu.operation.sub.SUB_Operation;
import gbewd.cpu.operation.swap.Swap;
import gbewd.cpu.operation.undefined.UndefinedOperation;

public class AllOperations {
	private static Map<Integer, OperationExecution> allOperations = new HashMap<>();
	
	public static Map<Integer, OperationExecution> getAllOperations() {
		if (!allOperations.keySet().isEmpty()) {
			return allOperations;
		}
		
		Map<Integer, Integer> dupes = new HashMap<>();
		
		for (Entry<Integer, OperationExecution> entry : ADC_Operation.getAllADCr_xOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : ADD_Operation.getAllADD_Operations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : AddRegistersToHLRegisters.getAllAddRegistersToHLRegistersOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : MiscAddOperations.getAllMiscAddOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : BIT_Operation.getAllBIT_Operations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : RES_Operation.getAllRES_Operations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : SET_Operation.getAllSET_Operations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : CP_Operation.getAllCP_Operations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : DAA_Operation.getAllDAAOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : INCDEC_Operation.getAllINDEC_Operations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : LDmr_Operation.getAllLoadMemoryFromRegisterOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : LDrm_Operation.getAllLoadRegisterFromMemoryOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : LDrr_Operation.getAllLoadRegisterFromRegisterOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : LoadTwoRegistersFromPc.getAllLoadRegisterFromMemoryOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : MiscellaneousLoadInstructions.getAllMiscLoadOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : LogicOperation.getAllLogicOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : RL_MiscOperations.getAllMiscLoadOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : RL_RR_Operation.getAllRLOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : SL_Operation.getAllSLOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : SR_Operation.getAllSROperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : AllSpecialOperations.getAllSpecialOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : SBC_Operation.getAllSBC_Operations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : SUB_Operation.getAllSUB_Operations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : Swap.getAllSWAP_Operations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : AllStackOperations.getAllStackOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : DoubleIncDecOperation.getAllDoubleIncDecOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : AllJumpOperations.getAllJumpOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : AllFunctionOperations.getAllFunctionOperations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : UndefinedOperation.getAllUndefinedOperation().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
		for (Entry<Integer, OperationExecution> entry : RST_Operation.getAllRST_Operations().entrySet()) {
			allOperations.put(entry.getKey(), entry.getValue());
			dupes.put(entry.getKey(), dupes.get(entry.getKey()) == null ? 1 : dupes.get(entry.getKey()) + 1);
		}
		
//		System.out.println("All dupes:");
//		for (Entry<Integer, Integer> entry : dupes.entrySet()) {
//			if (entry.getValue() > 1) {
//				System.out.println(String.format("%x %d", entry.getKey(), entry.getValue()));
//			}
//		}
		return allOperations;
	}
}
