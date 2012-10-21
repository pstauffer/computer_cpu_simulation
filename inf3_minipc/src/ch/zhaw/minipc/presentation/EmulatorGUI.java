package ch.zhaw.minipc.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.base.ReturnValues;
import ch.zhaw.minipc.base.RunModes;

public class EmulatorGUI implements Observer{

	private JFrame frame;
	private EmulatorGUI emuGui;
	private RunModes mode;
	private JTextField txtResult;
	private Thread emuThread;
	private CPU cpu;
	
	private JButton btnStep;
	private JButton btnStart;
	private JTable tableCommandMemory;
	private JTextField txtCommandCounter;

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
		
		btnStart = new JButton("Start");		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emuGui.startEmulation();
			}
		});
		btnStart.setBounds(6, 6, 117, 29);
		btnStart.setEnabled(false);
		frame.getContentPane().add(btnStart);
		
		txtResult = new JTextField();
		txtResult.setBounds(247, 5, 134, 28);
		txtResult.setEditable(false);
		frame.getContentPane().add(txtResult);
		txtResult.setColumns(10);
		
		btnStep = new JButton("Step");
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (this) {
					cpu.proceed();
				}
			}
		});
		btnStep.setBounds(118, 6, 117, 29);
		btnStep.setEnabled(false);
		frame.getContentPane().add(btnStep);
		
		tableCommandMemory = new JTable();
		tableCommandMemory.setRowSelectionAllowed(false);
		tableCommandMemory.setBounds(22, 78, 159, 252);
		frame.getContentPane().add(tableCommandMemory);
		
		txtCommandCounter = new JTextField();
		txtCommandCounter.setEditable(false);
		txtCommandCounter.setColumns(10);
		txtCommandCounter.setBounds(16, 38, 134, 28);
		frame.getContentPane().add(txtCommandCounter);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		//Build the first menu.
		JMenu menu = new JMenu("Emulator");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("Initialize");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initCPU();	
			}
		});
		menu.add(menuItem);
		
		this.emuGui = this;
        frame.setVisible(true);
	}
	
	public void initCPU(){
	    JFileChooser chooser = new JFileChooser("./data");
	    int returnVal = chooser.showOpenDialog(this.frame);
	    
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	this.cpu = new CPU(chooser.getSelectedFile().getPath());
			cpu.addObserver(emuGui);
			
			btnStart.setEnabled(true);
	    }
	}
	
	public void startEmulation(){
		mode = RunModes.AUTO;
		
		cpu.setRunMode(mode);
		
		switch(mode){
			case AUTO:
				new Thread(cpu).start();
				break;
			case SLOW:
				new Thread(cpu).start();
				break;
			case STEP:
				this.emuThread = new Thread(cpu);
				this.emuThread.start();
				break;
		}	
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		ReturnValues returnSet = (ReturnValues)arg1;
		String resultText = Integer.toString(returnSet.getAkku().getDezValue());
		this.txtResult.setText(resultText);
		
		
		this.frame.repaint();
		
		if(mode==RunModes.AUTO||mode==RunModes.SLOW){
			
		}else if(mode == RunModes.STEP){
			this.cpu.pause();
		}
	}
}
