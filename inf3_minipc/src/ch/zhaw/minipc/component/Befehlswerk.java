package ch.zhaw.minipc.component;

import java.util.HashMap;
import java.util.Map;

import ch.zhaw.minipc.commands.ADD;
import ch.zhaw.minipc.commands.Command;
import ch.zhaw.minipc.memory.*;

public class Befehlswerk implements IBefehlswerk{
	
	private IMemory memory;
	private Akku akku;
	private Register register;
	private IBefehlszaehler zaehler;
	private HashMap<String,Command> commandList;
	
	public Befehlswerk(IMemory memory,Akku akku,Register register,IBefehlszaehler zaehler){
		this.memory = memory;
		this.akku = akku;
		this.register = register;
		this.zaehler = zaehler;
	}


	public void excecuteCommand(Command newCommand) {

		newCommand.Excecute(akku, register);
	}
	
	
}
