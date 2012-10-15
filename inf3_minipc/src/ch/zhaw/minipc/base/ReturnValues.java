package ch.zhaw.minipc.base;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlswerk;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class ReturnValues {
	private IMemory memory;
	private IBefehlszaehler counter;
	private HashMap<String,MemoryCell> registerList;
	private MemoryCell akku;
	private int programCounter;
	
	public ReturnValues(IMemory memory,IBefehlszaehler counter,HashMap<String,MemoryCell> registerList, MemoryCell akku, int programCounter){
		this.memory = memory;
		this.counter = counter;
		this.registerList = registerList;
		this.akku = akku;
	}
	
	public IMemory getMemory() {
		return memory;
	}
	public void setMemory(IMemory memory) {
		this.memory = memory;
	}
	public IBefehlszaehler getCounter() {
		return counter;
	}
	public void setCounter(IBefehlszaehler counter) {
		this.counter = counter;
	}
	public HashMap<String, MemoryCell> getRegisterList() {
		return registerList;
	}
	public void setRegisterList(HashMap<String, MemoryCell> registerList) {
		this.registerList = registerList;
	}
	public MemoryCell getAkku() {
		return akku;
	}
	public void setAkku(MemoryCell akku) {
		this.akku = akku;
	}
	public int getProgramCounter() {
		return programCounter;
	}
	public void setProgramCounter(int programCounter) {
		this.programCounter = programCounter;
	}

}
