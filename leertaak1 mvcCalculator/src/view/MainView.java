package view;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.*;

public class MainView extends JFrame implements ActionListener{

	private JTextField field = new JTextField(30);
	private JTextField input = new JTextField(20);
	private JButton add = new JButton("Add");
	
	private JButton dec = new JButton("Dec");
	private JButton hex = new JButton("Hex");
	private JButton bin = new JButton("Bin");
	private JButton ocd = new JButton("Ocd");
	
	private JButton rat = new JButton("Rat");
	private JButton fix = new JButton("Fixed");
	private JButton flo = new JButton("Float");
	private JButton clear = new JButton("C");
	
	private JButton plus = new JButton("+");
	private JButton minus = new JButton("-");
	private JButton divide = new JButton("/");
	private JButton multiply = new JButton("*");
	
	private Calculator calc;	//model
	
	public MainView(Calculator calculator){
		
		calc = calculator;
		
		JFrame frame = new JFrame();
		this.setSize(400, 150);
		this.setTitle("J&R's awesome calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout(2,2));
		
		field.setEditable(false);
		top.add(field, BorderLayout.NORTH);
		top.add(input, BorderLayout.CENTER);
		top.add(add, BorderLayout.EAST);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 5));
		buttonPanel.add(new JLabel("Base"));
		buttonPanel.add(dec);
		buttonPanel.add(hex);
		buttonPanel.add(bin);
		buttonPanel.add(ocd);
		
		buttonPanel.add(new JLabel("Format (C)"));
		buttonPanel.add(rat);
		buttonPanel.add(fix);
		buttonPanel.add(flo);
		buttonPanel.add(clear);
		
		buttonPanel.add(new JLabel("Calculate"));
		buttonPanel.add(plus);
		buttonPanel.add(minus);
		buttonPanel.add(divide);
		buttonPanel.add(multiply);
		
		top.add(buttonPanel, BorderLayout.SOUTH);
		this.add(top);
	}
	
	//getters/setters voor input/output
	public String getInput(){
		String temp = input.getText();
		input.setText("");
		return temp;
	}
	public void setField(String value){
		field.setText(value);
	}
	public void update(){
		field.setText("["+
				calc.getBase().getName()+"]["+
				calc.getFormat().getName()+"] "+
				calc.firstOperand()+", "+
				calc.secondOperand()+""
			);
	}
	
	//action listeners
	public void addAddAL(ActionListener al){add.addActionListener(al);}
	
	public void addDecAL(ActionListener al){dec.addActionListener(al);}
	public void addHexAL(ActionListener al){hex.addActionListener(al);}
	public void addBinAL(ActionListener al){bin.addActionListener(al);}
	public void addOcdAL(ActionListener al){ocd.addActionListener(al);}
	
	public void addRatAL(ActionListener al){rat.addActionListener(al);}
	public void addFixAL(ActionListener al){fix.addActionListener(al);}
	public void addFloAL(ActionListener al){flo.addActionListener(al);}
	public void addClearAL(ActionListener al){clear.addActionListener(al);}
	
	public void addPlusAL(ActionListener al){plus.addActionListener(al);}
	public void addMinusAL(ActionListener al){minus.addActionListener(al);}
	public void addDivideAL(ActionListener al){divide.addActionListener(al);}
	public void addMultiplyAL(ActionListener al){multiply.addActionListener(al);}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//null
	}
}
