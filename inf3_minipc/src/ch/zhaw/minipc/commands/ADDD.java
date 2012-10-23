package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;
import ch.zhaw.minipc.util.Tools;

public class ADDD extends Command {

	public ADDD() {
		this.setName("ADDD");
		this.setOpCode("1zzzzzzzzzzzzzz");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		int value = akku.getDezValue();
		int number = Integer.parseInt(this.getDecNumber());

		// calculate
		int result = value + number;

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

		// get the decimal number
		int number = Integer.parseInt(this.getParameter().split("#")[1]);

		// convert to a binary number
		String binNumber = Tools.convertToBin(number, 15);

		// replace the zzzzzzzzzzzzzz with the binary number
		this.setOpCode(this.getOpCode().replaceAll("zzzzzzzzzzzzzz", binNumber));

	}

}
