package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;
import ch.zhaw.minipc.util.Tools;

public class SLA extends Command {

	public SLA() {
		this.setName("SLA");
		this.setOpCode("0000100000000000");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {
		
		String accuVal = akku.getBinValue();
		int accuValInt = akku.getDezValue();

        // Get second bit and set it as carry bit
        String carryBit = accuVal.substring(1, 2);
        if (carryBit.equals("0")) {
            CPU.setCarryFlag(false);
            akku.setDezValue(accuValInt*2);
        } else {
            CPU.setCarryFlag(true);
            String bin = Tools.convertToBin(accuValInt * 2, 16);
            akku.setDezValue(accuValInt * 2);
            akku.setBinValue((bin.replaceFirst("1", "0")));
            
        }
		
		zaehler.incrementBefehlszaehler();
	}

	@Override
	public void updateOpCode() {
		// TODO Auto-generated method stub

	}

}
