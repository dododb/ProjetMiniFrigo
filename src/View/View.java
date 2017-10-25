package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import Controller.IController;
import Model.IModel;
import View.Panel.CanPanel;
import View.Panel.ConsigneChangePanel;
import View.Panel.ModePanel;
import View.Panel.TemperaturePanel;

public class View extends JFrame implements IView {
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
	IController controller;
	
	public View(IModel model, IController controller)
	{
		super();

		this.model = model;
		this.controller = controller;
		
		this.setTitle("Pimp My Fridge");
		this.setSize(500,700);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try 
		{
			Image img = ImageIO.read(new File("Projet PMF/cold2.png"));
			this.setIconImage(img);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.draw();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		this.draw();
	}
	
	public Observer GetObserver()
	{
		return this;
	}
	
	private void draw()
	{
		/***************************************/
		
	    consigneT = new TemperaturePanel(this.model.GettempConsigne(), true);
	    currentT = new TemperaturePanel(this.model.GettempCan(), true);
	    stage1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, consigneT, currentT);
	    stage1.setEnabled( false );
	    stage1.setDividerLocation(240);
	    stage1.setDividerSize(3);
	    
	    /*************************************/

	    buttonP = new ConsigneChangePanel(this.model,this.controller, true);
	    buttonM = new ConsigneChangePanel(this.model, this.controller, false);
	    can = new CanPanel(this.model, this.controller);
	    buttonsPM = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonP, buttonM);
	    buttonsPM.setEnabled( false );
	    buttonsPM.setDividerLocation(120);
	    buttonsPM.setDividerSize(3);
	    stage2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, buttonsPM, can);
	    stage2.setEnabled( false );
	    stage2.setDividerLocation(240);
	    stage2.setDividerSize(3);
	    
	    /*************************************/	    
	    
	    canT = new TemperaturePanel(this.model.GettempFridge(), false);
	    frigeT = new TemperaturePanel(this.model.GettempModule(), false);
	    stage3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, canT, frigeT);
	    stage3.setEnabled( false );
	    stage3.setDividerLocation(240);
	    stage3.setDividerSize(3);
	    
	    /*************************************/

	    sleep = new ModePanel(this.model, this.controller, 2);
	    enable = new ModePanel(this.model, this.controller, 1);
	    disable = new ModePanel(this.model, this.controller, 0);
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
