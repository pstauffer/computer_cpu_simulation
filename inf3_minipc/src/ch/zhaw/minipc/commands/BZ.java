package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class BZ extends Command {
	public BZ() {
		this.setName("BZ");
		this.setOpCode("0001xx1000000000");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {
		CPU.incI();
		if (akku.getDezValue() == 0) {
			String fullParameter = this.getParameter();
			MemoryCell register = registerList.get(fullParameter);
			int registerValue = register.getDezValue();
			zaehler.jumpToPosition(registerValue);
		}else{
			zaehler.incrementBefehlszaehler();
		}

	}

	@Override
	public void updateOpCode() {
		this.replaceRegisterForOpcode();
	}
}
