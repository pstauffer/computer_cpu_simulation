package ch.zhaw.minipc.base;

import java.util.List;

import ch.zhaw.minipc.component.*;
import ch.zhaw.minipc.memory.*;

public class CPU {
	
	private Akku akku;
	private IMemory memory;
	private Register register;
	private IBefehlszaehler counter;
	
	public void init(List<String> codeList, Akku akku, IMemory memory,Register register,IBefehlszaehler pcounter){
		this.akku = akku;
		this.memory = memory;
		this.register = register;
		this.counter = counter;
	}
	
	

}
