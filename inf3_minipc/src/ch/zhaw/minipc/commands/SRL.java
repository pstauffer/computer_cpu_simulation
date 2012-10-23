package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
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

		// calculate
		int result = value / 2;

		// checks for carry flag
		if (result >= this.MAX) {
			CPU.setCarryFlag(true);
		}
		if (result <= this.MIN) {
			CPU.setCarryFlag(true);
		}

		// set the new value
		akku.setDezValue(result);

	}

	@Override
	public void updateOpCode() {

	}

}
