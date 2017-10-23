package View.Panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ConsigneChangePanel extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pathFile;
	private Image img1;
	private Image img2;
	private Boolean more_less;
	private Boolean status;
	
	public ConsigneChangePanel(Boolean p_more_less, Boolean p_status)
	{
		this.pathFile = "D:/Exia CESI/A3/Projet/Projet PMF/";
		this.more_less = p_more_less;
		this.status = p_status;

        addMouseListener(this);
		try 
		{
			if(this.more_less)
			{
				this.img1 = ImageIO.read(new File(this.pathFile + "more1.png"));
				this.img2 = ImageIO.read(new File(this.pathFile + "more2.png"));
			}
			else
			{
				this.img1 = ImageIO.read(new File(this.pathFile + "less1.png"));
				this.img2 = ImageIO.read(new File(this.pathFile + "less2.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	public void paintComponent(Graphics g){
		if(this.status)	g.drawImage(this.img1, 0, 0, this.getWidth(), this.getHeight(), this);
		else g.drawImage(this.img2, 0, 0, this.getWidth(), this.getHeight(), this);

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(this.more_less);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
