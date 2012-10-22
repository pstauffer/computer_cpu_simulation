package ch.zhaw.minipc.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import ch.zhaw.minipc.base.CPU;
import ch.zhaw.minipc.base.ReturnValues;
import ch.zhaw.minipc.base.RunModes;
import ch.zhaw.minipc.commands.Command;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;

public class EmulatorGUI implements Observer{

	private JFrame frame;
	private EmulatorGUI emuGui;
	private RunModes mode;
	private JTextField txtResult;
	private Thread emuThread;
	private CPU cpu;
	
	private JButton btnStep;
	private JButton btnStart;
	private JTextField txtCommandCounter;
	private JPanel panel;
	private JTable tableCommandMemory;

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
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
       
        
        DefaultTableModel model = new DefaultTableModel();
        tableCommandMemory = new JTable(model);
        tableCommandMemory.setEnabled(false);
        
        model.addColumn("#");
        model.addColumn("Command");
        model.addColumn("Op-Code");
		frame.getContentPane().add(new JScrollPane(tableCommandMemory), BorderLayout.WEST);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btnStart = new JButton("Start");		
		panel.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emuGui.startEmulation();
			}
		});
		btnStart.setEnabled(false);
		
		btnStep = new JButton("Step");
		panel.add(btnStep);
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (this) {
					cpu.proceed();
				}
			}
		});
		btnStep.setEnabled(false);
		
		
		txtCommandCounter = new JTextField();
		panel.add(txtCommandCounter);
		txtCommandCounter.setEditable(false);
		txtCommandCounter.setColumns(10);
		
		txtResult = new JTextField();
		panel.add(txtResult);
		txtResult.setEditable(false);
		txtResult.setColumns(10);
		
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
	    ReturnValues returnSet = this.cpu.getInitialConifg();
	    updateTable(returnSet);
	    
	}
	
	public void startEmulation(){
		mode = RunModes.SLOW;
		
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
	
	private void updateTable(ReturnValues returnSet){
		Map<Integer,Command> map = returnSet.getMemory().getCommandMemory();
		DefaultTableModel model = (DefaultTableModel) this.tableCommandMemory.getModel();
		for( Map.Entry<Integer,Command> entry : map.entrySet() )
		{
		  int cellNumber = entry.getKey();
		  Command command = entry.getValue();
		 
		  model.addRow(new Object[]{cellNumber, command.getFullCommand(), command.getOpCode()});
		}
	}
	
	private void selectRow(int position){
		ListSelectionModel selectionModel = tableCommandMemory.getSelectionModel();
		selectionModel.clearSelection();
		selectionModel.addSelectionInterval(position,position);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		ReturnValues returnSet = (ReturnValues)arg1;
		String resultText = Integer.toString(returnSet.getAkku().getDezValue());
		this.txtResult.setText(resultText);
		
		if(mode==RunModes.AUTO){
			
		}else if(mode ==RunModes.SLOW){
			//returnSet.getMemory().getCommandMemoryField()
			this.selectRow(returnSet.getProgramCounter());
		}
		else if(mode == RunModes.STEP){
			this.cpu.pause();
		}
		
		this.frame.repaint();
	}
}
