package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class DEC extends Command {

	public DEC() {
		this.setName("DEC");
		this.setOpCode("00000100");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		int value = akku.getDezValue();
		akku.setDezValue(value - 1);
	}

	@Override
	public void updateOpCode() {
		// TODO Auto-generated method stub

	}

}
