package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public abstract class Command {
	
	public final int MAX = 32768;
	public final int MIN = -32768;
	
	private String name;
	private String opCode;
	private String parameter;
	
	public abstract void excecute(MemoryCell akku,HashMap<String,MemoryCell> registerList,IBefehlszaehler zaehler, IMemory memory);
	public abstract void updateOpCode();

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getOpCode() {
		return opCode;
	}

	protected void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	protected String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
		this.updateOpCode();
	}
	
	public String getFullCommand(){
		if(parameter == null){
			return name;
		}
		return name + " " + parameter;
		
	}
	
}
