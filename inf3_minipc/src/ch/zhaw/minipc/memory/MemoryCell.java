package ch.zhaw.minipc.memory;

import ch.zhaw.minipc.commands.Command;

public class MemoryCell {
	private Command command;

	private String value;
	
	public Command getCommand() {
		return command;
	}
	public void setCommand(Command command) {
		this.command = command;
	}
}
