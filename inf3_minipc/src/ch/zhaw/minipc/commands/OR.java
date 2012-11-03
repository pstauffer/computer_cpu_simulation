package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;
import ch.zhaw.minipc.util.Tools;

public class OR extends Command {

	public OR() {
		this.setName("OR");
		this.setOpCode("0000xx1100000000");
	}

	@Override
	public void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory) {

		String fullParameter = this.getParameter();
		MemoryCell register = registerList.get(fullParameter);
		String akkuValue = akku.getBinValue();
		String registerValue = register.getBinValue();

		int accuValInt = akku.getDezValue();
		int regValInt = register.getDezValue();

		int shifted = accuValInt ^ regValInt;

		akku.setDezValue(shifted);
		
		

		/*
		 * String orValue = "";
		 * 
		 * for (int count = 0; count < 16; count++) {
		 * 
		 * String reg = registerValue.substring(count, count + 1); String akk =
		 * akkuValue.substring(count, count + 1);
		 * 
		 * // or operation if (reg.equals("1") || akk.equals("1")) { orValue +=
		 * "1"; } else { orValue += "0"; }
		 * 
		 * }
		 * 
		 * int orValueDec = Tools.convertToDec(orValue);
		 * 
		 * // checks for carry flag if (orValueDec >= this.MAX) {
		 * CPU.setCarryFlag(true); } if (orValueDec <= this.MIN) {
		 * CPU.setCarryFlag(true); }
		 */

		zaehler.incrementBefehlszaehler();

	}

	@Override
	public void updateOpCode() {

		this.replaceRegisterForOpcode();

	}

}
