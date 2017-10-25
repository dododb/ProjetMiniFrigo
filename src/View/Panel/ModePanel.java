package View.Panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Controller.IController;
import Model.IModel;

public class ModePanel extends JPanel implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IModel model;
	private IController controller;
	int mode;
	
	public ModePanel(IModel model, IController controller, int mode)
	{
		this.addMouseListener(this);
        this.model = model;
        this.controller = controller;
        this.mode = mode;
	}
	

	public void paintComponent(Graphics g){
		g.drawImage(this.model.GetMode().GetImageMode(this.mode), 0, 0, this.getWidth(), this.getHeight(), this);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		this.controller.ModeChange(this.mode);
	}


	@Override
	public void mousePressed(MouseEvent e) {}


	@Override
	public void mouseReleased(MouseEvent e) {}


	@Override
	public void mouseEntered(MouseEvent e) {}


	@Override
	public void mouseExited(MouseEvent e) {}
}
