package ch.zhaw.minipc.commands;

import ch.zhaw.minipc.component.IBefehlszaehler;
import ch.zhaw.minipc.memory.Akku;
import ch.zhaw.minipc.memory.IMemory;
import ch.zhaw.minipc.memory.Register;

public class ADD extends Command {
	
	public ADD(){
		this.setName("ADD");
	}
	
	@Override
	public void Excecute(Akku akku, Register register) {
		System.out.println(this.getName());
	}

}
