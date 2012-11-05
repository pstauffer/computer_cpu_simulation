package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class INC extends Command {

	public INC() {
		this.setName("INC");
		this.setOpCode("0000000100000000");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {
		CPU.incI();
		int value = akku.getDezValue();

		// calculate
		int result = value + 1;

		// checks for carry flag
		if (result >= this.MAX) {
			CPU.setCarryFlag(true);
		}
		
		zaehler.incrementBefehlszaehler();
		
		// set the new value
		akku.setDezValue(result);

	}

	@Override
	public void updateOpCode() {
		// TODO Auto-generated method stub

	}

}
