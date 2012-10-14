package ch.zhaw.minipc.memory;


public class MemoryCell {
	private short dezValue;
	private String binValue;
	
	public MemoryCell(){};
	
	public MemoryCell(short value){
		this.dezValue = value;
		this.binValue = Integer.toBinaryString(value);
	}
	
	public short getDezValue() {
		return dezValue;
	}
	public void setDezValue(short i) {
		this.dezValue = i;
	}
	public String getBinValue() {
		return binValue;
	}
	public void setBinValue(String binValue) {
		this.binValue = binValue;
	}
	
	
	
}
