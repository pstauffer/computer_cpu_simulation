package ch.zhaw.minipc.component;

import ch.zhaw.minipc.memory.Memory;

public class Befehlszaehler implements IBefehlszaehler{
	//Just for test purposes!!!!!!!!
	private int position = Memory.COMMANDMEMORYSTART;
	private final int lenght = Memory.CELLSIZE;

	@Override
	public void incrementBefehlszaehler() {
		position+=lenght;	
	}

	@Override
	public void decrementBefehlszaehler() {
		position-=lenght;
	}

	@Override
	public void jumpToPosition(int newPosition) {
		position = newPosition;
	}

	@Override
	public int getPosition() {
		return position;
	}

}
