package ch.zhaw.minipc.presentation;

import java.awt.EventQueue;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JButton;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.base.ReturnValues;
import ch.zhaw.minipc.base.RunModes;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

public class EmulatorGUI implements Observer{

	private JFrame frame;
	private EmulatorGUI emuGui;
	private RunModes mode;
	private JTextField txtResult;

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
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			//TODO Take path from filechooser	
			emuGui.startEmulation("");
			}
		});
		
		btnStart.setBounds(20, 29, 117, 29);
		frame.getContentPane().add(btnStart);
		
		txtResult = new JTextField();
		txtResult.setBounds(118, 138, 134, 28);
		frame.getContentPane().add(txtResult);
		txtResult.setColumns(10);
		
		this.emuGui = this;
        frame.setVisible(true);
	}
	
	public void startEmulation(String filepath){
		
		mode = RunModes.SLOW;
		CPU cpu = new CPU("./data/test_small.txt", mode);
		cpu.addObserver(emuGui);
		
		
		switch(mode){
			case AUTO:
				new Thread(cpu).start();
				break;
			case SLOW:
				new Thread(cpu).start();
				break;
			case STEP:
				cpu.startEmulator();
				break;
		}
		
		
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		ReturnValues returnSet = (ReturnValues)arg1;
		String resultText = Integer.toString(returnSet.getAkku().getDezValue());
		this.txtResult.setText(resultText);
		this.frame.repaint();
	}
}
