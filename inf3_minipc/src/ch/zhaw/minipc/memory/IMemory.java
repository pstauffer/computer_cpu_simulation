package ch.zhaw.minipc.memory;

import java.util.List;

import ch.zhaw.minipc.commands.Command;

public interface IMemory {
	
	void initMemory(List<String> commandList,List<String> paramList);
	
	/**
	 * Gibt den Wert vom Memory zurück der an der angebenen
	 * Position gespeichert ist.
	 * @param position position im Memory von dem der Wert gelesen werden soll
	 */
	Command getMemoryField(int position);
	
	/**
	 * Schreibt den Wert ins Memory an der angebenen
	 * Position.
	 * @param position An dieser Position wird der Wert geschrieben
	 * @param data Der Wert der geschrieben werden muss
	 */
	void setMemoryField(int position, String data);
	
	

}
