import java.util.ArrayList;
import java.util.List;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.io.CodeReader;


public class EmulatorRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CPU cpu = new CPU();
		//CodeReader reader = new CodeReader();
		//List<String> codeList = reader.readCodeFromFile("ch/zhaw/minipc/data/Testfile.txt");
		//List<String> paramList = reader.readParameterFromFile("ch/zhaw/minipc/data/Testfile.txt");
		List<String> codeList = new ArrayList<String>();
		List<String> paramList = new ArrayList<String>();
		cpu.init(codeList,paramList);
		cpu.startEmulator();
	}

}
