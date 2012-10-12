package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.Akku;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;
import ch.zhaw.minipc.memory.Register;

public class ADD extends Command {
	
	public ADD(){
		this.setName("ADD");
		this.setOpCode("10101010");
	}
	
	@Override
	public void Excecute(MemoryCell akku, HashMap<String,MemoryCell> registerList) {
		System.out.println(this.getName());
		System.out.println(this.getParameter());
	}

}
