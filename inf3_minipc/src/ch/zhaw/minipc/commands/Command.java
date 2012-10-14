package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public abstract class Command {
	
	private String name;
	private String opCode;
	private String parameter;
	
	public abstract void excecute(MemoryCell akku,HashMap<String,MemoryCell> registerList,IBefehlszaehler zaehler, IMemory memory);
	public abstract void updateOpCode(MemoryCell akku,HashMap<String,MemoryCell> registerList,IMemory memory);

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected String getOpCode() {
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
	}
	
}
