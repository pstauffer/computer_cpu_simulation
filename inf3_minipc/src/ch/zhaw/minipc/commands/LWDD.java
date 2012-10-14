package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class LWDD extends Command{
	
	public LWDD(){
		this.setName("LWDD");
		this.setOpCode("");
	}

	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {
		
		String fullParameter = this.getParameter();
		
		String registerName = fullParameter.split(",")[0];
		String cellName = fullParameter.split("#")[1];
		
		MemoryCell register = registerList.get(registerName);
		MemoryCell cell = memory.getDataMemoryCell(Integer.parseInt(cellName));
		
		register = cell;
		
		registerList.put(registerName, register);
	}

	public void updateOpCode() {

	}

}
