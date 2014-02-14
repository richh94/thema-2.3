import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
    
public class StatistiekView extends JPanel implements ActionListener
{
    DobbelsteenModel d;
    int[] counters = new int[6];
    JLabel[] labels = new JLabel[7];
    
	public StatistiekView()
	{
	    this.setLayout(new GridLayout(7,2));
  
	    //initialize grid, first the totals, second the rest of the grid
	    this.add(new JLabel("Totaal:"));
	    labels[0] = new JLabel("0 keer");
    	this.add(labels[0]);
    	
	    for(int x = 1; x <= 6; x = x+1) {
	    	this.add(new JLabel(x + ":"));
	    	labels[x] = new JLabel("0 keer");
	    	this.add(labels[x]);
	    	counters[x-1] = 0;
	    }
	}
	
	public void actionPerformed( ActionEvent e )
	{
	    d = (DobbelsteenModel) e.getSource();
	    counters[d.getWaarde()-1]++;
    	int total = 0;
    	
	    //time to update
	    for(int x = 1; x <= 6; x = x+1) {
	    	labels[x].setText(counters[x-1] + " keer");
	    	labels[x].repaint();
	    	total += counters[x-1];
	    }
	    labels[0].setText(total + " keer");
    	labels[0].repaint();	    
	}
	
	public Dimension getPreferredSize()
	{
	    return new Dimension(150,50);
	} 
}
