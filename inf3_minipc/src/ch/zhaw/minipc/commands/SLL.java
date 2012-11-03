package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

public class SLL extends Command {

	public SLL() {
		this.setName("SLL");
		this.setOpCode("0000110000000000"); // muss auf 16 Bit aufgefuellt
											// werden
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

        int accuValDec = akku.getDezValue();
        String accuVal = akku.getBinValue();

        // Get first bit and set it as carry bit
        String carryBit = accuVal.substring(0, 1);
        if (carryBit.equals("0")) {
            CPU.setCarryFlag(false);
        } else {
        	CPU.setCarryFlag(true);
        }

        int accuValInt = Integer.parseInt(akku.getBinValue(), 2);
        // left shift
        int accuShifted = accuValInt << 1;

        // Save new value
        akku.setDezValue(accuShifted);
        
		zaehler.incrementBefehlszaehler();

	}

	@Override
	public void updateOpCode() {

	}

}
