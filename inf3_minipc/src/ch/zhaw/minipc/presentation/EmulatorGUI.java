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
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class EmulatorGUI implements Observer{

	private JFrame frame;
	private EmulatorGUI emuGui;
	private RunModes mode;
	private JTextField txtResultDez;
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
	private JTextField txtRegDez1;
	private JTextField txtRegBin1;
	private JTextField txtRegDez2;
	private JTextField txtRegBin2;
	private JLabel lblRegister_2;
	private JTextField txtRegDez3;
	private JTextField txtRegBin3;
	private JLabel lblAkku;
	private JScrollPane scrollPaneMemory;
	private JTable tableDataMemory;
	private JTextField txtResultBin;

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
		frame.setBounds(100, 100, 920, 487);
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
        
		JScrollPane scrollPaneCommand = new JScrollPane(tableCommandMemory);
		scrollPaneCommand.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneCommand.setPreferredSize(new Dimension(310,150));
		frame.getContentPane().add(scrollPaneCommand, BorderLayout.WEST);
		
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
		
		
		txtCommandCounter = new JTextField();
		panelCommandTable.add(txtCommandCounter);
		txtCommandCounter.setEditable(false);
		txtCommandCounter.setColumns(10);
		
		panelRegister = new JPanel();
		frame.getContentPane().add(panelRegister, BorderLayout.CENTER);
		
		lblRegister = new JLabel("Register 1");
		lblRegister.setBounds(19, 11, 63, 16);
		
		txtRegDez1 = new JTextField();
		txtRegDez1.setEditable(false);
		txtRegDez1.setBounds(99, 5, 153, 28);
		txtRegDez1.setColumns(10);
		panelRegister.setLayout(null);
		panelRegister.add(lblRegister);
		panelRegister.add(txtRegDez1);
		
		txtRegBin1 = new JTextField();
		txtRegBin1.setEditable(false);
		txtRegBin1.setBounds(99, 34, 153, 28);
		panelRegister.add(txtRegBin1);
		txtRegBin1.setColumns(10);
		
		JLabel lblRegister_1 = new JLabel("Register 2");
		lblRegister_1.setBounds(19, 82, 63, 16);
		panelRegister.add(lblRegister_1);
		
		txtRegDez2 = new JTextField();
		txtRegDez2.setEditable(false);
		txtRegDez2.setBounds(99, 76, 153, 28);
		panelRegister.add(txtRegDez2);
		txtRegDez2.setColumns(10);
		
		txtRegBin2 = new JTextField();
		txtRegBin2.setEditable(false);
		txtRegBin2.setBounds(99, 105, 153, 28);
		panelRegister.add(txtRegBin2);
		txtRegBin2.setColumns(10);
		
		lblRegister_2 = new JLabel("Register 3");
		lblRegister_2.setBounds(19, 154, 63, 16);
		panelRegister.add(lblRegister_2);
		
		txtRegDez3 = new JTextField();
		txtRegDez3.setEditable(false);
		txtRegDez3.setBounds(99, 148, 153, 28);
		panelRegister.add(txtRegDez3);
		txtRegDez3.setColumns(10);
		
		txtRegBin3 = new JTextField();
		txtRegBin3.setEditable(false);
		txtRegBin3.setBounds(99, 177, 153, 28);
		panelRegister.add(txtRegBin3);
		txtRegBin3.setColumns(10);
		
		lblAkku = new JLabel("Akku");
		lblAkku.setBounds(19, 228, 61, 16);
		panelRegister.add(lblAkku);
		
		txtResultDez = new JTextField();
		txtResultDez.setBounds(99, 222, 153, 28);
		panelRegister.add(txtResultDez);
		txtResultDez.setEditable(false);
		txtResultDez.setColumns(10);
		
		txtResultBin = new JTextField();
		txtResultBin.setEditable(false);
		txtResultBin.setBounds(99, 250, 153, 28);
		panelRegister.add(txtResultBin);
		txtResultBin.setColumns(10);
		
        DefaultTableModel modelMemory = new DefaultTableModel();
        tableDataMemory = new JTable(modelMemory);
        tableDataMemory.setEnabled(false);
        tableDataMemory.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      
        modelMemory.addColumn("#");
        modelMemory.addColumn("Value Dez");
        modelMemory.addColumn("Value Bin");
        
        TableColumn colData = tableDataMemory.getColumnModel().getColumn(0);
        colData.setPreferredWidth(40);
        colData = tableDataMemory.getColumnModel().getColumn(1);
        colData.setPreferredWidth(80);
        colData = tableDataMemory.getColumnModel().getColumn(2);
        colData.setPreferredWidth(150);
        
		JScrollPane scrollPaneMemory = new JScrollPane(tableDataMemory);
		scrollPaneMemory.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneMemory.setPreferredSize(new Dimension(270,110));
		frame.getContentPane().add(scrollPaneMemory, BorderLayout.EAST);
		
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
	
	private void updateFields(ReturnValues returnSet){
		String resultTextDez = Integer.toString(returnSet.getAkku().getDezValue());
		this.txtResultDez.setText(resultTextDez);
		String resultTextBin = returnSet.getAkku().getBinValue();
		this.txtResultBin.setText(resultTextBin);
		//TODO WORKING POSITION
		String resultTextDez1 = Integer.toString(returnSet.getRegisterList().get("R1").getDezValue());
		this.txtRegDez1.setText(resultTextDez1);
		String resultTextBin1 = returnSet.getRegisterList().get("R1").getBinValue();
		this.txtResultBin.setText(resultTextBin1);
		
		String resultTextDez2 = Integer.toString(returnSet.getRegisterList().get("R2").getDezValue());
		this.txtRegDez2.setText(resultTextDez2);
		String resultTextBin2 = returnSet.getRegisterList().get("R2").getBinValue();
		this.txtResultBin.setText(resultTextBin2);
		
		String resultTextDez3 = Integer.toString(returnSet.getRegisterList().get("R3").getDezValue());
		this.txtRegDez3.setText(resultTextDez3);
		String resultTextBin3 = returnSet.getRegisterList().get("R3").getBinValue();
		this.txtResultBin.setText(resultTextBin3);
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		ReturnValues returnSet = (ReturnValues)arg1;
		this.updateFields(returnSet);
		
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
