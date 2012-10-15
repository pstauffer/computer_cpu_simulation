import java.util.ArrayList;
import java.util.List;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.base.RunModes;
import ch.zhaw.minipc.io.CodeReader;
import ch.zhaw.minipc.presentation.EmulatorGUI;


public class EmulatorRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Test without GUI
		//CPU cpu = new CPU("./data/test_small.txt");
		//cpu.startEmulator(RunModes.AUTO);
		new EmulatorGUI();
	}

}
