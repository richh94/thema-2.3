package view;

import javax.swing.*;

import model.Calculator;
import java.awt.*;

public class ExtraView extends JFrame{
	
	private Calculator calc;	//model
	private JLabel text;
	
	public ExtraView(Calculator calculator){		
		calc = calculator;
	}
	public JPanel getPanel(){
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout(2,2));
		text = new JLabel(getLine());
		top.add(text);
		return top;
	}
	public void update(){
		text.setText(getLine());
	}
	public String getLine(){
		String line = "Operands: ";
		line += calc.countOperands();
		line += " ( ";
		line += calc.getOperands();
		line += ")";
		return line;
	}
}
