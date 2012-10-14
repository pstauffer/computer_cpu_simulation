package ch.zhaw.minipc.component;

import java.util.HashMap;

import ch.zhaw.minipc.commands.Command;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class Befehlswerk implements IBefehlswerk{
	
	private IMemory memory;
	private MemoryCell akku;
	private IBefehlszaehler zaehler;
	private HashMap<String,MemoryCell> registerList;
	private HashMap<String,Command> commandList;
	
	public Befehlswerk(IMemory memory,MemoryCell akku,HashMap<String,MemoryCell> registerList,IBefehlszaehler zaehler){
		this.memory = memory;
		this.akku = akku;
		this.registerList = registerList;
		this.zaehler = zaehler;
	}


	public void excecuteCommand(Command newCommand) {

		newCommand.excecute(akku, registerList, zaehler,memory);
	}
	
	
}
