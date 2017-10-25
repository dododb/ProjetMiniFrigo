package View.Panel;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

import Controller.IController;
import Model.IModel;

public class ConsigneChangePanel extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IModel model;
	private IController controller;
	private Boolean more_less;
	
	public ConsigneChangePanel(IModel model, IController controller, Boolean p_more_less)
	{
		this.addMouseListener(this);
        this.model = model;
        this.controller = controller;
		this.more_less = p_more_less;
	}
	

	public void paintComponent(Graphics g){
		g.drawImage(this.model.GetChangeConsigne().GetImage(this.more_less), 0, 0, this.getWidth(), this.getHeight(), this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		this.controller.PressChangeConsigne(this.more_less);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.controller.ClickChangeConsigne(this.more_less);
		this.controller.PressChangeConsigne(this.more_less);
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
