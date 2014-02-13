package controller;

import java.awt.event.ActionEvent;
import model.*;
import view.*;

public class Controller {
	
	private Calculator calc;
	private MainView view;

	public Controller(Calculator model, MainView view){
		calc = model;
		this.view = view;
		
		/*
		public void actionPerformed(ActionEvent e){
		 if(e.getSource() == )
		}*/
	}
}
