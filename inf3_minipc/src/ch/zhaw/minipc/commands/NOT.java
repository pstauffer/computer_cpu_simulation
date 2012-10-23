package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;
import ch.zhaw.minipc.util.Tools;

public class NOT extends Command {

	public NOT() {
		this.setName("NOT");
		this.setOpCode("0000000010000000");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		String akkuValue = akku.getBinValue();

		String notValue = "";

		for (int count = 0; count < 16; count++) {
			String akk = akkuValue.substring(count, count + 1);

			// not operation
			if (akk.equals("1")) {
				notValue += "0";
			} else {
				notValue += "1";

			}

		}

		int notValueDec = Tools.convertToDec(notValue);

		// checks for carry flag
		if (notValueDec >= this.MAX) {
			CPU.setCarryFlag(true);
		}
		if (notValueDec <= this.MIN) {
			CPU.setCarryFlag(true);
		}

		// set the new value
		akku.setDezValue(notValueDec);

	}

	@Override
	public void updateOpCode() {

	}

}
