package ch.zhaw.minipc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeReader implements ICodeReader {

	@Override
	public List<String> readCodeFromFile(String path) {
		List<String> codeFromFile = new ArrayList<String>();
	    BufferedReader reader = null;
	    
	    try {
	      File file = new File(path);
	      reader = new BufferedReader(new FileReader(file));
	      String line = reader.readLine();
	      while (line != null) {
	    	  codeFromFile.add(line);
	        line = reader.readLine();
	      }
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
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
	      return codeFromFile;
	}

	@Override
	public List<String> readParameterFromFile(String path) {
	      File file = new File(path);
			List<String> parameterFromFile = new ArrayList<String>();
	      Scanner scanner;
		try {
			scanner = new Scanner(file);
		      while (scanner.hasNextLine()) {
		    	  String line = scanner.nextLine();

		    	  Scanner lineScanner = new Scanner(line);
		    	  lineScanner.useDelimiter("---");
		    	  while (lineScanner.hasNext()) {

		    	  String part = lineScanner.next();
		    	  parameterFromFile.add(part);
//		    	  System.out.print(part + " ");
		    	  }
		   }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return parameterFromFile;
	}

}
