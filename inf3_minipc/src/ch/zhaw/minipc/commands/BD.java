package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class BD extends Command {

	public BD() {
		this.setName("BD");
		this.setOpCode("001000yyyyyyyyyy");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		int addr = Integer.parseInt(this.getDecNumber());
		zaehler.jumpToPosition(addr);

	}

	@Override
	public void updateOpCode() {
		this.replaceAddrForOpcode();
	}

}
