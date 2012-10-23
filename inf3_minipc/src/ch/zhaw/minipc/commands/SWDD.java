package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class SWDD extends Command {

	public SWDD() {
		this.setName("SWDD");
		this.setOpCode("0110xxyyyyyyyyy");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		String registerName = this.getRegisterName();
		int memPosition = Integer.parseInt(this.getDecAddr());

		MemoryCell registerValue = registerList.get(registerName);

		// MemoryCell cellValue = memory.getDataMemoryCell(Integer
		// .parseInt(decAddr));

		memory.setMemoryField(memPosition, registerValue);

	}

	@Override
	public void updateOpCode() {

		this.replaceRegisterForOpcode();
		this.replaceAddrForOpcode();

	}

}
