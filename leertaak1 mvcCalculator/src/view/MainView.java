package view;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.*;

@SuppressWarnings("serial")
public class MainView extends JFrame{

	private JTextField field = new JTextField(30);
	private JTextField input = new JTextField(20);
	private JButton add = new JButton("Add");
	
	private String[] bases = {"dec", "hex", "bin", "ocd"};
	private JRadioButton[] basebuttons = new JRadioButton[4];
	private String[] formats = {"fixed", "rat", "float"};
	private JRadioButton[] formatbuttons = new JRadioButton[3];
	
	private JButton clear = new JButton("C");
	
	private JButton plus = new JButton("+");
	private JButton minus = new JButton("-");
	private JButton divide = new JButton("/");
	private JButton multiply = new JButton("*");
	
	private Calculator calc;	//model
	
	public MainView(Calculator calculator){
		
		calc = calculator;

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
		
		ButtonGroup basegroup = new ButtonGroup();
		for(int i=0; i<bases.length; i++){
			basebuttons[i] = new JRadioButton(bases[i]);
			basegroup.add(basebuttons[i]);
			buttonPanel.add(basebuttons[i]);
		}
		basebuttons[0].setSelected(true);
		
		buttonPanel.add(new JLabel("Format (C)"));
		
		ButtonGroup formatgroup = new ButtonGroup();
		for(int i=0; i<formats.length; i++){
			formatbuttons[i] = new JRadioButton(formats[i]);
			formatgroup.add(formatbuttons[i]);
			buttonPanel.add(formatbuttons[i]);
		}
		formatbuttons[0].setSelected(true);
		
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
		field.setText(
				calc.secondOperand()
			);
	}
	
	//action listeners
	public void addAddAL(ActionListener al){add.addActionListener(al);}
	
	public void addDecAL(ActionListener al){basebuttons[0].addActionListener(al);}
	public void addHexAL(ActionListener al){basebuttons[1].addActionListener(al);}
	public void addBinAL(ActionListener al){basebuttons[2].addActionListener(al);}
	public void addOcdAL(ActionListener al){basebuttons[3].addActionListener(al);}
	
	public void addFixAL(ActionListener al){formatbuttons[0].addActionListener(al);}
	public void addRatAL(ActionListener al){formatbuttons[1].addActionListener(al);}
	public void addFloAL(ActionListener al){formatbuttons[2].addActionListener(al);}
	public void addClearAL(ActionListener al){clear.addActionListener(al);}
	
	public void addPlusAL(ActionListener al){plus.addActionListener(al);}
	public void addMinusAL(ActionListener al){minus.addActionListener(al);}
	public void addDivideAL(ActionListener al){divide.addActionListener(al);}
	public void addMultiplyAL(ActionListener al){multiply.addActionListener(al);}
}
