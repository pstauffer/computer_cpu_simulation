package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class ADD extends Command {
	
	public ADD(){
		this.setName("ADD");
		this.setOpCode("0000xx1110000000");
	}
	
	@Override
	public void excecute(MemoryCell akku,HashMap<String,MemoryCell> registerList,IBefehlszaehler zaehler, IMemory memory){
		
		short value =akku.getDezValue();
		MemoryCell register = registerList.get(this.getParameter());
		
		akku.setDezValue((short) (value + register.getDezValue()));
	}
	
	public void updateOpCode(){
		
		
	}

}
