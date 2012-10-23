package ch.zhaw.minipc.commands;

import java.util.HashMap;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.MemoryCell;
import ch.zhaw.minipc.util.Tools;

public abstract class Command {

	public final int MAX = 32768;
	public final int MIN = -32768;

	private String name;
	private String opCode;
	private String parameter;

	public abstract void excecute(MemoryCell akku,
			HashMap<String, MemoryCell> registerList, IBefehlszaehler zaehler,
			IMemory memory);

	public abstract void updateOpCode();

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public String getOpCode() {
		return opCode;
	}

	protected void setOpCode(String opCode) {
		this.opCode = opCode;
	}

	protected String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
		this.updateOpCode();
	}

	public String getFullCommand() {
		if (parameter == null) {
			return name;
		}
		return name + " " + parameter;

	}

	public String getRegisterName() {
		String fullParameter = this.getParameter();
		return fullParameter.split(",")[0];
	}

	public String getDecAddr() {
		String fullParameter = this.getParameter();
		return fullParameter.split("#")[1];
	}

	public void replaceRegisterForOpcode() {

		// parse the String for the numbers, needed for the switch-case
		int registerNumber = Integer.parseInt(this.getParameter().substring(1,
				2));

		switch (registerNumber) {
		case 0:
			// R0 is the akku => replace xx with 00
			this.setOpCode(this.getOpCode().replaceAll("xx", "00"));
		case 1:
			// R1 => replace xx with 01
			this.setOpCode(this.getOpCode().replaceAll("xx", "01"));
		case 2:
			// R2 => replace xx with 10
			this.setOpCode(this.getOpCode().replaceAll("xx", "10"));
		case 3:
			// R3 => replace xx with 11
			this.setOpCode(this.getOpCode().replaceAll("xx", "11"));
		}
	}

	public void replaceAddrForOpcode() {

		// get the decimal address
		int addr = Integer.parseInt(this.getParameter().split("#")[1]);

		// convert to a binary address
		String binAddr = Tools.convertToBin(addr, 10);

		// replace the yyyyyyyyy with the binary address
		this.setOpCode(this.getOpCode().replaceAll("yyyyyyyyy", binAddr));

	}

}
