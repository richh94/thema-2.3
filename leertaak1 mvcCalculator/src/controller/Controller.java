package controller;

import java.awt.event.*;
import javax.swing.*;
import model.*;
import view.*;

public class Controller {
	
	private Calculator calc;
	private MainView view;

	public Controller(Calculator model, MainView view){
		calc = model;
		this.view = view;
		
		//add actionlisteners
		view.addAddAL(new AddAL());
		view.addDecAL(new DecAL());
		view.addHexAL(new HexAL());
		view.addBinAL(new BinAL());	
		view.addOcdAL(new OcdAL());	
		
		view.addClearAL(new ClearAL());
		view.addRatAL(new RatAL());
		view.addFloAL(new FloAL());	
		view.addFixAL(new FixAL());
		
		view.addPlusAL(new PlusAL());
		view.addMinusAL(new MinusAL());
		view.addDivideAL(new DivideAL());
		view.addMultiplyAL(new MultiplyAL());
	}
	class AddAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String input = "";
			input = view.getInput();
			if(input != null && !input.isEmpty()){
				try{
					calc.addOperand(input);
				}catch(FormatException fe){
					view.setField("Wrong operand!" + fe.getMessage());
				}
			}
			view.update();
		}
	}
	class DecAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setBase(new DecimalBase());
			view.update();
		}
	}
	class HexAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setBase(new HexBase());
			view.update();
		}
	}
	class BinAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setBase(new BinaryBase());
			view.update();
		}
	}
	class OcdAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setBase(new OctodadBase());
			view.update();
		}
	}
	
	
	class RatAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setFormat(new RationalFormat());
			view.update();
		}
	}
	class FixAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setFormat(new FixedPointFormat());
			view.update();
		}
	}
	class FloAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setFormat(new FloatingPointFormat());
			view.update();
		}
	}
	class ClearAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setFormat(new FixedPointFormat());
			calc.setBase(new DecimalBase());
			try{calc.addOperand("0");
			 	calc.addOperand("0");
			}catch(FormatException fe){ 
				view.setField("Wrong operand!" + fe.getMessage());
			}
			view.update();
		}
	}
	
	
	class DivideAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.divide();
			view.update();
		}
	}
	class MultiplyAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.multiply();
			view.update();
		}
	}
	class PlusAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.add();
			view.update();
		}
	}
	class MinusAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.subtract();
			view.update();
		}
	}
	
}
