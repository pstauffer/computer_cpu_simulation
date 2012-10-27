package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class BCD extends Command {

	public BCD() {
		this.setName("BCD");
		this.setOpCode("001110yyyyyyyyyy");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		if (CPU.getCarryFlag() == true) {
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
