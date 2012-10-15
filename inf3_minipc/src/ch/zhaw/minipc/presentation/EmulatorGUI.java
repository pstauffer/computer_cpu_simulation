package ch.zhaw.minipc.presentation;

import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JButton;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.base.RunModes;

public class EmulatorGUI implements Observer{

	private JFrame frame;
	private EmulatorGUI emuGui;

	/**
	 * Create the application.
	 */
	public EmulatorGUI() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 558, 396);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(20, 29, 117, 29);
		frame.getContentPane().add(btnStart);
	}
	
	private void startEmulation(String filepath){
		
		CPU cpu = new CPU("./data/test_small.txt");
		cpu.addObserver(emuGui);
		RunModes mode = RunModes.AUTO;
		
		switch(mode){
			case AUTO:
				cpu.startEmulator(RunModes.AUTO);
			case SLOW:
				cpu.startEmulator(RunModes.SLOW);
			case STEP:
				cpu.startEmulator(RunModes.STEP);
		}
		
		
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
			
	}
}
