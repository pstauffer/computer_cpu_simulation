package ch.zhaw.minipc.base;

import java.util.List;

import ch.zhaw.minipc.commands.Command;
import ch.zhaw.minipc.component.*;
import ch.zhaw.minipc.memory.*;

public class CPU {
	
	private Akku akku;
	private IMemory memory;
	private Register register;
	private IBefehlszaehler counter;
	private IBefehlswerk werk;

	
	public void init(List<String> commandList,List<String> paramList){
		this.akku = new Akku();
		this.memory = new Memory();
		this.register = new Register();
		this.counter = new Befehlszaehler();
		this.werk = new Befehlswerk(memory, akku, register, counter);
		
		commandList.add("ADD");
		
		memory.initMemory(commandList, paramList);
	}
	
	
	public void startEmulator(){
		
			int position = counter.getPosition();
			
			Command command = memory.getMemoryField(position);
			
			werk.excecuteCommand(command);
			
		
	}
	

}
