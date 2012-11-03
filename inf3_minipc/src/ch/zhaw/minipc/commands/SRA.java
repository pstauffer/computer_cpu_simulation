package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class SRA extends Command {

	public SRA() {
		this.setName("SRA");
		this.setOpCode("0000010100000000");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		String accuVal = akku.getBinValue();

        // Get last bit and set it as carry bit
        String carryBit = accuVal.substring(15);
        if (carryBit.equals("0")) {
            CPU.setCarryFlag(false);
        } else {
        	CPU.setCarryFlag(true);
        }

        int accuValInt = akku.getDezValue();

        // Signed right shift (/2)
        int accuShifted = accuValInt >> 1;

        akku.setDezValue(accuShifted);
		
		zaehler.incrementBefehlszaehler();
	}

	@Override
	public void updateOpCode() {

	}

}
