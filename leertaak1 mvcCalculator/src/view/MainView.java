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
	
	private JButton rat = new JButton("Rational");
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
		
		buttonPanel.add(new JLabel("Format"));
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
		
		//panel.add(input, BorderLayout.EAST);
		
		
		/*this.add(dec);
		dec.addActionListener(this);
		this.add(hex);
		hex.addActionListener(this);
		this.add(bin);
		bin.addActionListener(this);
		this.add(ocd);
		ocd.addActionListener(this);*/

	}
	
	public void actionPerformed( ActionEvent e )
	{
		/*if(e.getSource() == dec){
			field.setText("decimal");
		}*/
	}
	
}
