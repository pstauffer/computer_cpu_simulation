package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class ADD extends Command {

	public ADD() {
		this.setName("ADD");
		this.setOpCode("0000xx1110000000");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		String fullParameter = this.getParameter();

		int value = akku.getDezValue();
		MemoryCell register = registerList.get(fullParameter);

		// calculate
		int result = value + register.getDezValue();
		
		// checks for carry flag
		if (result >= this.MAX) {
			CPU.setCarryFlag(true);
		}else if (result <= this.MIN) {
			CPU.setCarryFlag(true);
		}else{
			CPU.setCarryFlag(false);
		}
		
		zaehler.incrementBefehlszaehler();
		
		// set the new value
		akku.setDezValue(result);
	}

	public void updateOpCode() {

		this.replaceRegisterForOpcode();

	}
}
