package ch.zhaw.minipc.base;

import java.util.HashMap;
import java.util.List;

import ch.zhaw.minipc.commands.Command;
import ch.zhaw.minipc.component.*;
import ch.zhaw.minipc.memory.*;

public class CPU {
	
	private MemoryCell akku;
	private IMemory memory;
	private IBefehlszaehler counter;
	private IBefehlswerk werk;
	private HashMap<String,MemoryCell> registerList;

	
	public void init(List<String> commandList,List<String> paramList){
		this.akku = new MemoryCell();
		this.memory = new Memory();
		this.registerList = new HashMap<String, MemoryCell>();
		
		this.registerList.put("R1", new MemoryCell());
		this.registerList.put("R2", new MemoryCell());
		this.registerList.put("R3", new MemoryCell());
		
		this.counter = new Befehlszaehler();
		
		this.werk = new Befehlswerk(memory, akku, registerList, counter);
		
		commandList.add("ADD R1");
		
		memory.initMemory(commandList, paramList);
	}
	
	
	public void startEmulator(){
		
			int position = counter.getPosition();
			
			Command command = memory.getCommandMemoryField(position);
			
			werk.excecuteCommand(command);
			
		
	}
	

}
