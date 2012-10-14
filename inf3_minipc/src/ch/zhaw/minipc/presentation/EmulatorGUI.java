package ch.zhaw.minipc.presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class EmulatorGUI {

	private JFrame frame;

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
	}

}
