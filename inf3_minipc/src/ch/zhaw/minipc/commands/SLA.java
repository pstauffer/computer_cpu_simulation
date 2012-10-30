package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;

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
		int accuValInt = Integer.parseInt(akku.getBinValue(), 2);

        // Get second bit and set it as carry bit
        String carryBit = accuVal.substring(1, 2);
        if (carryBit.equals("0")) {
            CPU.setCarryFlag(false);
            akku.setDezValue(accuValInt*2);
        } else {
            CPU.setCarryFlag(true);
            //TODO!!!!!
           // String bin = tools.convertToBin(accuValDec * 2, 16);
           // accu.setRegister(bin.replaceFirst("1", "0"));

        }

		int value = akku.getDezValue();

		// calculate
		int result = value * 2;

		// checks for carry flag
		if (result >= this.MAX) {
			CPU.setCarryFlag(true);
		}
		if (result <= this.MIN) {
			CPU.setCarryFlag(true);
		}
		
		zaehler.incrementBefehlszaehler();

		akku.setDezValue(result);

	}

	@Override
	public void updateOpCode() {
		// TODO Auto-generated method stub

	}

}
