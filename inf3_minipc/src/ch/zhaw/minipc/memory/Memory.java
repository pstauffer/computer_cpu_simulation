package ch.zhaw.minipc.memory;

import java.util.LinkedHashMap;
import java.util.List;

import ch.zhaw.minipc.commands.Command;

public class Memory implements IMemory{

	private LinkedHashMap<Integer,Command> commandMemory;
	private LinkedHashMap<Integer,MemoryCell> dataMemory;
	private String packageName = "ch.zhaw.minipc.commands.";
	public static final int DATAMEMORYSTART = 500;
	public static final int COMMANDMEMORYSTART = 200;
	public static final int CELLSIZE = 2;
	
	public Memory(){
		this.commandMemory = new LinkedHashMap<Integer,Command>();
		this.dataMemory = new LinkedHashMap<Integer, MemoryCell>();
	}
	
	private void addCommand(Command newCommand){
		//Index des commandMemorys beginnt bei 100
		
		int index = (this.commandMemory.size() * this.CELLSIZE) + COMMANDMEMORYSTART;
		
		this.commandMemory.put(index,newCommand);
		
	}
	
	public void addData(String data){
		//Index des dataMemory beginnt bei 500
		
		int index = (this.dataMemory.size() * this.CELLSIZE) + this.DATAMEMORYSTART;
		
		this.dataMemory.put(index, new MemoryCell(Integer.parseInt(data)));
	}
	
	@Override
	public Command getCommandMemoryField(int position) {
		return commandMemory.get(new Integer(position));
	}
	
	@Override
	public MemoryCell getDataMemoryCell(int position) {
		return dataMemory.get(new Integer(position));
	}
	
	public void setMemoryField(int position, MemoryCell cell) {
		dataMemory.put(position, cell);
	}
	
	public int getCommandMemorySize() {
		return this.commandMemory.size();
	}
	
	public LinkedHashMap<Integer,Command> getCommandMemory(){
		return this.commandMemory;
	}

	public void initMemory(List<String> commandList, List<String> paramList) {
		
		for(String fullCommand : commandList){
			String[] fullCommandArray = fullCommand.split(" ");
			String commandName = fullCommand.split(" ")[0];
			String parameter = null;
			
			if(fullCommandArray.length>1){
				parameter = fullCommand.substring(commandName.length()+1);
			}
			
			commandName = packageName + commandName;
			try {
				Class cl = Class.forName(commandName);
				java.lang.reflect.Constructor co = cl.getConstructor(null);
				Command newCommand = (Command) co.newInstance(null);
				
				newCommand.setParameter(parameter);
				this.addCommand(newCommand);
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Befehl ist nicht/falsch implementiert");
			}
			
		}
		
		for(String param : paramList){
			this.addData(param);
		}
	}

	@Override
	public LinkedHashMap<Integer, MemoryCell> getDataMemory() {
		return this.dataMemory;
	}
	
}
