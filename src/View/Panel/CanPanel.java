package View.Panel;

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
import View.IView;

public class CanPanel extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IModel model;
	private IController controller;
	
	public CanPanel(IModel model, IController controller)
	{
		this.addMouseListener(this);
        this.model = model;
        this.controller = controller;
	}
	

	public void paintComponent(Graphics g){
		g.drawImage(this.model.GetCan().GetCan(), 0, 0, this.getWidth(), this.getHeight(), this);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		this.controller.ClickCan();
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
