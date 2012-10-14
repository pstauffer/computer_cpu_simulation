package ch.zhaw.minipc.memory;


public class MemoryCell {
	private int dezValue;
	private int binValue;
	
	public MemoryCell(){};
	
	public MemoryCell(int value){
		this.dezValue = value;
	}
	
	public int getDezValue() {
		return dezValue;
	}
	public void setDezValue(int dezValue) {
		this.dezValue = dezValue;
	}
	public int getBinValue() {
		return binValue;
	}
	public void setBinValue(int binValue) {
		this.binValue = binValue;
	}
	
	
	
}
