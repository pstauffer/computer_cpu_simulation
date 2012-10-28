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
		MemoryCell registerValue = new MemoryCell();
		
		if(registerName.equals("R0")){
			registerValue.setBinValue(akku.getBinValue());
			registerValue.setDezValue(akku.getDezValue());
		}else{
			registerValue.setBinValue(registerList.get(registerName).getBinValue());
			registerValue.setDezValue(registerList.get(registerName).getDezValue());
		}
		
		memory.setMemoryField(memPosition, registerValue);
		
		zaehler.incrementBefehlszaehler();

	}

	@Override
	public void updateOpCode() {

		this.replaceRegisterForOpcode();
		this.replaceAddrForOpcode();

	}

}
