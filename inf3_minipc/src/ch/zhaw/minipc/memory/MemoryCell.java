package ch.zhaw.minipc.memory;

import ch.zhaw.minipc.util.Tools;


public class MemoryCell {
	private short dezValue;
	private String binValue;
	
	public MemoryCell(){};
	
	public MemoryCell(short value){
		this.dezValue = value;
		this.setBinValue(Tools.convertToBin(value, 16));
	}
	
	public short getDezValue() {
		return dezValue;
	}
	public void setDezValue(short i) {
		this.dezValue = i;
		this.setBinValue(Tools.convertToBin(i, 16));
	}
	public String getBinValue() {
		return binValue;
	}
	private void setBinValue(String binValue) {
		this.binValue = binValue;
	}
	
	
	
}
