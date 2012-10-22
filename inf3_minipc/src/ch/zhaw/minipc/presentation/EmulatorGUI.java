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
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;

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
	private JPanel panelCommandTable;
	private JTable tableCommandMemory;
	private JComboBox comboBox;
	private JPanel panelRegister;
	private JLabel lblRegister;
	private JTextField textField;

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
		frame.setBounds(100, 100, 643, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
       
        
        DefaultTableModel model = new DefaultTableModel();
        tableCommandMemory = new JTable(model);
        tableCommandMemory.setEnabled(false);
        tableCommandMemory.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      
        model.addColumn("#");
        model.addColumn("Command");
        model.addColumn("Op-Code");
        
        TableColumn col = tableCommandMemory.getColumnModel().getColumn(0);
        col.setPreferredWidth(40);
        col = tableCommandMemory.getColumnModel().getColumn(1);
        col.setPreferredWidth(120);
        col = tableCommandMemory.getColumnModel().getColumn(2);
        col.setPreferredWidth(150);
        
		JScrollPane scrollPane = new JScrollPane(tableCommandMemory);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(310,150));
		frame.getContentPane().add(scrollPane, BorderLayout.WEST);
		
		panelCommandTable = new JPanel();
		panelCommandTable.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame.getContentPane().add(panelCommandTable, BorderLayout.NORTH);
		
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JComboBox cb = (JComboBox)arg0.getSource();
				mode = (RunModes) (cb.getSelectedItem());
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(RunModes.values()));
		panelCommandTable.add(comboBox);
		
		btnStart = new JButton("Start");		
		panelCommandTable.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				emuGui.startEmulation();
			}
		});
		btnStart.setEnabled(false);
		
		btnStep = new JButton("Step");
		panelCommandTable.add(btnStep);
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				synchronized (this) {
					cpu.proceed();
				}
			}
		});
		btnStep.setEnabled(false);
		
		txtResult = new JTextField();
		panelCommandTable.add(txtResult);
		txtResult.setEditable(false);
		txtResult.setColumns(10);
		
		
		txtCommandCounter = new JTextField();
		panelCommandTable.add(txtCommandCounter);
		txtCommandCounter.setEditable(false);
		txtCommandCounter.setColumns(10);
		
		panelRegister = new JPanel();
		frame.getContentPane().add(panelRegister, BorderLayout.CENTER);
		
		lblRegister = new JLabel("Register 1");
		panelRegister.add(lblRegister);
		
		textField = new JTextField();
		panelRegister.add(textField);
		textField.setColumns(10);
		
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
		
		//frame.pack();
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
			this.selectRow(returnSet.getProgramCounter());
			this.cpu.pause();
		}
		
		this.frame.repaint();
	}
}
