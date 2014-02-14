package controller;

import java.awt.event.*;
import model.*;
import view.*;

public class Controller {
	
	private Calculator calc;
	private MainView view;
	private ExtraView extra;

	public Controller(Calculator model, MainView view, ExtraView extra){
		calc = model;
		this.view = view;
		this.extra = extra;
		
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
	
	public void update(){
		view.update();
		extra.update();
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
			update();
		}
	}
	class DecAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setBase(new DecimalBase());
			update();
		}
	}
	class HexAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setBase(new HexBase());
			update();
		}
	}
	class BinAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setBase(new BinaryBase());
			update();
		}
	}
	class OcdAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setBase(new OctodadBase());
			update();
		}
	}
	
	
	class RatAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setFormat(new RationalFormat());
			update();
		}
	}
	class FixAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setFormat(new FixedPointFormat());
			update();
		}
	}
	class FloAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.setFormat(new FloatingPointFormat());
			update();
		}
	}
	class ClearAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.clear();
			update();
		}
	}
	
	
	class DivideAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.divide();
			update();
		}
	}
	class MultiplyAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.multiply();
			update();
		}
	}
	class PlusAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.add();
			update();
		}
	}
	class MinusAL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			calc.subtract();
			update();
		}
	}
	
}
