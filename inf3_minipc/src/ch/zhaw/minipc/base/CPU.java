package ch.zhaw.minipc.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import ch.zhaw.minipc.commands.Command;
import ch.zhaw.minipc.component.Befehlswerk;
import ch.zhaw.minipc.component.Befehlszaehler;
import ch.zhaw.minipc.component.IBefehlswerk;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.io.CodeReader;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.Memory;
import ch.zhaw.minipc.memory.MemoryCell;

public class CPU extends Observable{
	
	private MemoryCell akku;
	private IMemory memory;
	private IBefehlszaehler counter;
	private IBefehlswerk werk;
	private HashMap<String,MemoryCell> registerList;
	
	public CPU(String path){
        CodeReader reader  = new CodeReader();
        
		List<String> codeList = new ArrayList<String>();
		List<String> paramList = new ArrayList<String>();
        
		codeList = reader.readCodeFromFile(path);
		paramList = reader.readParameterFromFile(path);
		
		this.init(codeList, paramList);
	}

	
	public void init(List<String> commandList,List<String> paramList){
		this.akku = new MemoryCell();
		this.memory = new Memory();
		this.registerList = new HashMap<String, MemoryCell>();
		
		this.registerList.put("R1", new MemoryCell());
		this.registerList.put("R2", new MemoryCell());
		this.registerList.put("R3", new MemoryCell());
		
		this.counter = new Befehlszaehler();
		
		this.werk = new Befehlswerk(memory, akku, registerList, counter);
		
		memory.initMemory(commandList, paramList);
	}
	
	
	public void startEmulator(RunModes mode){
			int i = 0;
			while(i < memory.getCommandMemorySize()){
				int position = counter.getPosition();
				
				Command command = memory.getCommandMemoryField(position);
				
				werk.excecuteCommand(command);
				counter.incrementBefehlszaehler();
				i++;
				/*if(mode == RunModes.STEP){
					this.notifyObservers(arg0);
				}else if(mode == RunModes.SLOW){
					this.notifyObservers(arg0);
				}*/
			}
			
			//this.notifyObservers(arg);
			
			System.out.println(akku.getDezValue());		
	}
	

	

}
