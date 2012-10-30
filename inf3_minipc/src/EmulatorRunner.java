import ch.zhaw.minipc.presentation.EmulatorGUI;

public class EmulatorRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Test without GUI
		// CPU cpu = new CPU("./data/test_small.txt",runModes.AUTO);
		// cpu.startAutoEmulator();
		new EmulatorGUI();
	}

}
