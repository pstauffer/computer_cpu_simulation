package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;
import ch.zhaw.minipc.util.Tools;

public class AND extends Command {

	public AND() {
		this.setName("AND");
		this.setOpCode("0000xx1000000000");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		String fullParameter = this.getParameter();
		MemoryCell register = registerList.get(fullParameter);
		String akkuValue = akku.getBinValue();
		String registerValue = register.getBinValue();

		String andValue = "";

		for (int count = 0; count < 16; count++) {

			String reg = registerValue.substring(count, count + 1);
			String akk = akkuValue.substring(count, count + 1);

			// and operation
			if (reg.equals("1") && akk.equals("1")) {
				andValue += "1";
			} else {
				andValue += "0";
			}

		}
		int andValueDec = Tools.convertToDec(andValue);

		// checks for carry flag
		if (andValueDec >= this.MAX) {
			CPU.setCarryFlag(true);
		}
		if (andValueDec <= this.MIN) {
			CPU.setCarryFlag(true);
		}
		
		zaehler.incrementBefehlszaehler();
		
		// set the new value
		akku.setDezValue(andValueDec);

	}

	@Override
	public void updateOpCode() {

		this.replaceRegisterForOpcode();

	}

}
