package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class SLL extends Command {

	public SLL() {
	this.setName("SLL");
	this.setOpCode("0000110000000000"); //muss auf 16 Bit aufgefuellt werden
	}
	
	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		int value = akku.getDezValue();
		akku.setDezValue(value * 2);
		
//		String extend = "0";
//		String carry = "1";
//		String value = akku.getBinValue();
//		value = value + extend;
//		value = value.substring(2,15);
//		value = carry + value;
		
	}

	@Override
	public void updateOpCode() {
		// TODO Auto-generated method stub
		
	}

	
}
