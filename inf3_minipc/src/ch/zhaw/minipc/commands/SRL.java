package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class SRL extends Command {

	public SRL() {
		this.setName("SRL");
		this.setOpCode("0000100100000000");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		int value = akku.getDezValue();
		akku.setDezValue(value / 2);

	}

	@Override
	public void updateOpCode() {
		// TODO Auto-generated method stub

	}

}
