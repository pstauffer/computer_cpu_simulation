package ch.zhaw.minipc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CodeReader implements ICodeReader {

	@Override
	public List<String> readCodeFromFile(String path) {
	    BufferedReader reader = null;
		List<String> codeList = new ArrayList<String>();

	    try {
//	      InputStream file = ClassLoader.getSystemResourceAsStream(path);
//		  reader = new BufferedReader(new InputStreamReader(file));	   
	    	File file = new File(path);
	    	reader = new BufferedReader(new FileReader(file));
	      String line = reader.readLine();
	      
	      int counter = 0;
	      while (line != null) {
	    	  if(line.equals("--CODE--")) {
	    		  counter++;
	    	  }
	    	  if(line.equals("--PARAMETER--")) {
	    		  counter++;
	    	  }
	    	  if(counter == 1 && ! line.equals("")) {
	    	  codeList.add(line);
	    	  }
	    	  line = reader.readLine();
	    	  }
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        if (reader != null) {
	          reader.close();
	        }
	      } catch (IOException e) {
	        e.printStackTrace();
	        }     
	}
	    
	    codeList.remove(0);
	      return codeList;
	}

	@Override
	public List<String> readParameterFromFile(String path) {
	    BufferedReader reader = null;
		List<String> parameterList = new ArrayList<String>();

	    try {
//	      InputStream file = ClassLoader.getSystemResourceAsStream(path);
//		  reader = new BufferedReader(new InputStreamReader(file));	
	    	File file = new File(path);
	    	reader = new BufferedReader(new FileReader(file));
	      String line = reader.readLine();
	      
	      int counter = 0;
	      while (line != null) {
	    	  if(line.equals("--CODE--")) {
	    		  counter++;
	    	  }
	    	  if(line.equals("--PARAMETER--")) {
	    		  counter++;
	    	  }
	    	  if(counter == 2 && ! line.equals("")) {
	    	  parameterList.add(line);
	    	  }
	    	  line = reader.readLine();
	    	  }
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      try {
	        if (reader != null) {
	          reader.close();
	        }
	      } catch (IOException e) {
	        e.printStackTrace();
	        }     
	}
	    
	    parameterList.remove(0);
	      return parameterList;
	}

}
