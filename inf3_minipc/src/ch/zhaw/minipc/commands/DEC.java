package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class DEC extends Command {

	public DEC() {
		this.setName("DEC");
		this.setOpCode("0000010000000000");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		int value = akku.getDezValue();

		// calculate
		int result = value - 1;

		if (result <= this.MIN) {
			CPU.setCarryFlag(true);
		}
		
		zaehler.incrementBefehlszaehler();
		
		// set the new value
		akku.setDezValue(result);

	}

	@Override
	public void updateOpCode() {

	}

}
