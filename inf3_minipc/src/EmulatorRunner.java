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
        CodeReader blubb  = new CodeReader();
        System.out.println(blubb.readCodeFromFile("/Users/pascal/Downloads/blubb.txt").toString());
        System.out.println(blubb.readParameterFromFile("/Users/pascal/Downloads/blubb.txt").toString());
		List<String> codeList = new ArrayList<String>();
		List<String> paramList = new ArrayList<String>();
		cpu.init(codeList,paramList);
		cpu.startEmulator();
	}

}
