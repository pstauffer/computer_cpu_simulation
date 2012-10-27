package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class CLR extends Command {

	public CLR() {
		this.setName("CLR");
		this.setOpCode("0000xx1010000000");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		String fullParameter = this.getParameter();

		if (fullParameter.equals("R0")) {
			akku.setDezValue(0);
		} else {
			MemoryCell register = registerList.get(fullParameter);
			register.setDezValue(0);
		}
		
		zaehler.incrementBefehlszaehler();
		// set the carry flag
		CPU.setCarryFlag(false);

	}

	@Override
	public void updateOpCode() {

		this.replaceRegisterForOpcode();

	}

}
