package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class BZD extends Command {

	public BZD() {
		this.setName("BZD");
		this.setOpCode("0011000yyyyyyyyyy");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {
		CPU.incI();
		if (akku.getDezValue() == 0) {
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
