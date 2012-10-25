package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class BC extends Command {
	public BC() {
		this.setName("BC");
		this.setOpCode("0001xx1100000000");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		if (CPU.getCarryFlag() == true) {
			String fullParameter = this.getParameter();
			MemoryCell register = registerList.get(fullParameter);
			int registerValue = register.getDezValue();
			zaehler.jumpToPosition(registerValue);
		}

	}

	@Override
	public void updateOpCode() {
		this.replaceRegisterForOpcode();

	}
}
