package View.Panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ModePanel extends JPanel implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pathFile;
	private Image img1;
	private Image img2;
	private Boolean status;
	
	public ModePanel(String p_pathFile, Boolean p_status)
	{
		this.status = p_status;
        addMouseListener(this);

		this.pathFile = p_pathFile;
		try 
		{
			this.img1 = ImageIO.read(new File(this.pathFile + "1.png"));
			this.img2 = ImageIO.read(new File(this.pathFile + "2.png"));
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
		System.out.println(this.pathFile);
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
