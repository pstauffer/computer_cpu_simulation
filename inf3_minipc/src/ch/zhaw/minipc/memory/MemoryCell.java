package ch.zhaw.minipc.memory;

import ch.zhaw.minipc.util.Tools;


public class MemoryCell {
	private int dezValue;
	private String binValue = "0000000000000000";
	
	public MemoryCell(){};
	
	public MemoryCell(int value){
		this.dezValue = value;
		this.setBinValue(Tools.convertToBin(value, 16));
	}
	
	public int getDezValue() {
		return dezValue;
	}
	public void setDezValue(int i) {
		this.dezValue = i;
		this.setBinValue(Tools.convertToBin(i, 16));
	}
	public String getBinValue() {
		return binValue;
	}
	public void setBinValue(String binValue) {
		this.binValue = binValue;
	}
	
	
	
}
