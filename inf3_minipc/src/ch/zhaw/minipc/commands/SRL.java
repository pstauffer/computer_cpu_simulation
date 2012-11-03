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

		int accuValInt = Integer.parseInt(akku.getBinValue(), 2);
		// left shift
		int accuShifted = accuValInt >> 1;

		akku.setDezValue(accuShifted);

		// Get first bit and set it as carry bit
		String carryBit = akku.getBinValue().substring(15);
		if (carryBit.equals("0")) {
			CPU.setCarryFlag(false);
		} else {
			CPU.setCarryFlag(true);
		}

		zaehler.incrementBefehlszaehler();

	}

	@Override
	public void updateOpCode() {

	}

}
