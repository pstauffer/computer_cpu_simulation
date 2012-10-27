package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class BNZD extends Command {

	public BNZD() {
		this.setName("BNZD");
		this.setOpCode("001010yyyyyyyyyy");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		if (akku.getDezValue() != 0) {
			int addr = Integer.parseInt(this.getDecNumber());
			zaehler.jumpToPosition(addr);
		}else{
			zaehler.incrementBefehlszaehler();
		}

	}

	@Override
	public void updateOpCode() {
		this.replaceAddrForOpcode();
	}

}
