package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class LWDD extends Command {

	public LWDD() {
		this.setName("LWDD");
		this.setOpCode("0100xxyyyyyyyyy");
	}

	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		String registerName = this.getRegisterName();
		String decAddr = this.getDecAddr();

		MemoryCell registerValue = registerList.get(registerName);
		MemoryCell cellValue = memory.getDataMemoryCell(Integer
				.parseInt(decAddr));

		registerValue = cellValue;

		registerList.put(registerName, registerValue);
	}

	public void updateOpCode() {

		this.replaceRegisterForOpcode();
		this.replaceAddrForOpcode();

	}

}
