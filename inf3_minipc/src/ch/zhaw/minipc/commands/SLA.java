package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class SLA extends Command {

	public SLA() {
		this.setName("SLA");
		this.setOpCode("0000100000000000");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		short value = akku.getDezValue();
		akku.setDezValue((short) (value * 2));

	}

	@Override
	public void updateOpCode() {
		// TODO Auto-generated method stub

	}

}
