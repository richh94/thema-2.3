import javax.swing.*;
import java.awt.*;

public class DobbelsteenMVC extends JApplet
{
	DobbelsteenModel model;             // het model
	TekstView tekstView;              	// view
	DobbelsteenView dobbelsteenView;  	// view
	StatistiekView statistiekView;		// view
	DobbelsteenController controller;   // Controller
	
	public void init()
	{
		resize(350,200);
        
		// Maak het model
		model = new DobbelsteenModel();
        
        // Maak de controller en geef hem het model
		controller = new DobbelsteenController(model);
        controller.setBackground(Color.cyan);
        getContentPane().add(controller,BorderLayout.NORTH);
        
        // Maak de views
        dobbelsteenView = new DobbelsteenView(Color.red);
        dobbelsteenView.setBackground(Color.black);
        getContentPane().add(dobbelsteenView,BorderLayout.CENTER);
        
        tekstView = new TekstView();
        tekstView.setBackground(Color.green);
        getContentPane().add(tekstView,BorderLayout.SOUTH);
        
        statistiekView = new StatistiekView();
        statistiekView.setBackground(Color.pink);
        getContentPane().add(statistiekView,BorderLayout.EAST);
        
        // Registreer de views bij het model
        model.addActionListener(tekstView);
        model.addActionListener(dobbelsteenView);
        model.addActionListener(statistiekView);
        
        // Eerste worp
        model.gooi();
	}
}
