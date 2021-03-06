package ch.zhaw.minipc.memory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeMap;

import ch.zhaw.minipc.commands.Command;

public interface IMemory {
	
	/**
	 * Schreibt alle Commands und die Parameter f�r das Ganze Assembler Programm in das Memory
	 * - Commands ab speicherzelle 100
	 * - Daten und Parameter ab speicherzelle 500
	 * Diese Adressen m�ssen umbedingt eingehalten werden.
	 * @param commandList
	 * @param paramList
	 */
	void initMemory(List<String> commandList,List<String> paramList);
	
	/**
	 * Gibt den Wert vom Memory zur�ck der an der angebenen
	 * Position gespeichert ist.
	 * @param position position im Memory von dem der Wert gelesen werden soll
	 */
	Command getCommandMemoryField(int position);
	
	/**
	 * Gibt die MemoryCell an der �begebenen Position zur�ck
	 * @param position
	 * @return MemoryCell an der �bergebenen Position
	 */
	MemoryCell getDataMemoryCell(int position);
	
	/**
	 * Schreibt den Wert ins Memory an der angebenen
	 * Position.
	 * @param position An dieser Position wird der Wert geschrieben
	 * @param cell Der Wert der geschrieben werden muss
	 */
	void setMemoryField(int position, MemoryCell cell);
	
	void addData(String data);
	
	int getCommandMemorySize();
	
	HashMap<Integer,Command> getCommandMemory();
	
	TreeMap<Integer,MemoryCell> getDataMemory();

}
