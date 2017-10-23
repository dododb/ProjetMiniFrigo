package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import Model.IModel;
import View.Panel.CanPanel;
import View.Panel.ConsigneChangePanel;
import View.Panel.ModePanel;
import View.Panel.TemperaturePanel;

public class View extends JFrame implements IView{
	JSplitPane stage1;
	JPanel consigneT;
	JPanel currentT;
	JSplitPane buttonsPM;
    JSplitPane stage2;
    JPanel buttonP;
    JPanel buttonM;
    JPanel can;
    JSplitPane stage3;
    JPanel canT;
    JPanel frigeT;
    JSplitPane split1;
    JSplitPane stage4;
    JPanel sleep;
    JPanel enable;
    JPanel disable;
    JSplitPane up;
    JSplitPane down;
    JSplitPane end;
    
	IModel model;
	public View(IModel model)
	{
		super();

		this.model = model;

		this.setTitle("Test");
		this.setSize(500,700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.draw();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println(this.model.getText());
		this.draw();
	}
	
	public Observer GetObserver()
	{
		return this;
	}
	
	private void draw()
	{
		/***************************************/
		
	    consigneT = new TemperaturePanel("Consigne", true);
	    currentT = new TemperaturePanel("Temperature canette", true);
	    stage1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, consigneT, currentT);
	    stage1.setEnabled( false );
	    stage1.setDividerLocation(240);
	    stage1.setDividerSize(3);
	    
	    /*************************************/

	    buttonP = new ConsigneChangePanel(false, true);
	    buttonP.setBackground(new Color(0, 129, 172));
	    buttonM = new ConsigneChangePanel(true, true);
	    buttonM.setBackground(new Color(0, 129, 172));
	    can = new CanPanel(true);
	    can.setBackground(new Color(0, 129, 172));
	    buttonsPM = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonP, buttonM);
	    buttonsPM.setEnabled( false );
	    buttonsPM.setDividerLocation(120);
	    buttonsPM.setDividerSize(3);
	    stage2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonsPM, can);
	    stage2.setEnabled( false );
	    stage2.setDividerLocation(240);
	    stage2.setDividerSize(3);
	    
	    /*************************************/	    
	    
	    canT = new TemperaturePanel("Frigo", false);
	    frigeT = new TemperaturePanel("Module Peltier", false);
	    stage3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, canT, frigeT);
	    stage3.setEnabled( false );
	    stage3.setDividerLocation(240);
	    stage3.setDividerSize(3);
	    
	    /*************************************/

	    sleep = new ModePanel("D:/Exia CESI/A3/Projet/Projet PMF/sleep", false);
	    enable = new ModePanel("D:/Exia CESI/A3/Projet/Projet PMF/cold", true);
	    enable.setBackground(new Color(0, 129, 172));
	    disable = new ModePanel("D:/Exia CESI/A3/Projet/Projet PMF/off", false);
	    disable.setBackground(new Color(0, 129, 172));
	    split1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sleep, enable);
	    split1.setEnabled( false );
	    split1.setDividerLocation(163);
	    split1.setDividerSize(0);
	    stage4 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, split1, disable);
	    stage4.setEnabled( false );
	    stage4.setDividerLocation(326);
	    stage4.setDividerSize(0);
	    
	    /*************************************/
		
		up = new JSplitPane(JSplitPane.VERTICAL_SPLIT, stage1, stage2);
		up.setEnabled( false );
	    up.setDividerLocation(250);
	    up.setDividerSize(0);
		down = new JSplitPane(JSplitPane.VERTICAL_SPLIT, stage3, stage4);
		down.setEnabled( false );
		down.setDividerLocation(150);
	    down.setDividerSize(0);
		end = new JSplitPane(JSplitPane.VERTICAL_SPLIT, up, down);
		end.setEnabled( false );
		end.setDividerLocation(350);
		end.setDividerSize(0);
	    
	    this.getContentPane().add(end, BorderLayout.CENTER);
	    this.setVisible(true);
	}
}
