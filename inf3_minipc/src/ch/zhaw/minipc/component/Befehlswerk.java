package ch.zhaw.minipc.component;

import java.util.HashMap;
import java.util.Map;

import ch.zhaw.minipc.commands.ADD;
import ch.zhaw.minipc.commands.Command;
import ch.zhaw.minipc.memory.*;

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

		newCommand.Excecute(akku, registerList);
	}
	
	
}
