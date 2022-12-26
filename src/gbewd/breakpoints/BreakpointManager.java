package gbewd.breakpoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BreakpointManager {
	private Map<BreakpointType, List<Integer>> breakpointMap = new HashMap<>();
	private boolean breakpointsEnabled = false;
	
	public BreakpointManager() {
		for (BreakpointType type : BreakpointType.values()) {
			breakpointMap.put(type, new ArrayList<>());
		}
	}
	
	public List<Integer> getBreakpointsByType(BreakpointType type) {
		return breakpointMap.get(type);
	}

	public boolean breakpointsEnabled() {
		return breakpointsEnabled;
	}
	
	public void setEnableBreakpoints(boolean enabled) {
		breakpointsEnabled = enabled;
	}
	
	public boolean pcBreakpointHit(int pcValue) {
		List<Integer> cpuBreakpoints = getBreakpointsByType(BreakpointType.PC);
		return cpuBreakpoints.contains(Integer.valueOf(pcValue)) && breakpointsEnabled();
	}
	
	public boolean readMemoryBreakpointHit(int pcValue) {
		List<Integer> cpuBreakpoints = getBreakpointsByType(BreakpointType.READ_MEM);
		return cpuBreakpoints.contains(Integer.valueOf(pcValue)) && breakpointsEnabled();
	}
	
	public boolean writeMemoryBreakpointHit(int pcValue) {
		List<Integer> cpuBreakpoints = getBreakpointsByType(BreakpointType.WRITE_MEM);
		return cpuBreakpoints.contains(Integer.valueOf(pcValue)) && breakpointsEnabled();
	}
}
