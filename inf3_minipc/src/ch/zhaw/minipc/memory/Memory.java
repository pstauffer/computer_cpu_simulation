package ch.zhaw.minipc.memory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import ch.zhaw.minipc.commands.ADD;
import ch.zhaw.minipc.commands.Command;

public class Memory implements IMemory{

	private HashMap<Integer,Command> commandMemory;
	private String prefix = "ch.zhaw.minipc.commands.";
	
	public Memory(){
		this.commandMemory = new HashMap<Integer,Command>();
	}
	
	private void addCommand(Command newCommand){
		this.commandMemory.put(this.commandMemory.size()+100,newCommand);
	}
	
	@Override
	public Command getMemoryField(int position) {
		// TODO Auto-generated method stub
		return commandMemory.get(new Integer(position));
	}

	@Override
	public void setMemoryField(int position, String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initMemory(List<String> commandList, List<String> paramList) {
		// TODO Auto-generated method stub
		
		for(String command : commandList){
			command = command.split(" ")[0];
			command = prefix + command;
			try {
				Class cl = Class.forName(command);
				java.lang.reflect.Constructor co = cl.getConstructor(null);
				Command newCommand = (Command) co.newInstance(null);
				this.addCommand(newCommand);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
