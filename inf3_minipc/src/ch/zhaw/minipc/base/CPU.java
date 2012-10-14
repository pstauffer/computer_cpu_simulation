package ch.zhaw.minipc.base;

import java.util.HashMap;
import java.util.List;

import ch.zhaw.minipc.commands.Command;
import ch.zhaw.minipc.component.Befehlswerk;
import ch.zhaw.minipc.component.Befehlszaehler;
import ch.zhaw.minipc.component.IBefehlswerk;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.Memory;
import ch.zhaw.minipc.memory.MemoryCell;

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
		
		paramList.add("-200");
		commandList.add("LWDD R1, #500");
		commandList.add("ADD R1");
		
		memory.initMemory(commandList, paramList);
	}
	
	
	public void startEmulator(){
			int i = 0;
			while(i < memory.getCommandMemorySize()){
				int position = counter.getPosition();
				
				Command command = memory.getCommandMemoryField(position);
				
				werk.excecuteCommand(command);
				counter.incrementBefehlszaehler();
				i++;
			}
					
	}
	

}
