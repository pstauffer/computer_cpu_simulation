package ch.zhaw.minipc.component;

public class Befehlszaehler implements IBefehlszaehler{
	//Just for test purposes!!!!!!!!
	private int position = 100;
	private final int lenght = 1;

	@Override
	public void incrementBefehlszaehler() {
		position++;	
	}

	@Override
	public void decrementBefehlszaehler() {
		position--;
	}

	@Override
	public void jumpToPosition(int newPosition) {
	
	}

	@Override
	public int getPosition() {
		return position;
	}

}
