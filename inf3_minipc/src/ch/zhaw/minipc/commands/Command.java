package ch.zhaw.minipc.commands;

import ch.zhaw.minipc.component.*;
import ch.zhaw.minipc.memory.*;

public abstract class Command {
	
	private String name;
	
	public abstract void Excecute(Akku akku,Register register);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
