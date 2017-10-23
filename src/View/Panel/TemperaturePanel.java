package View.Panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class TemperaturePanel  extends JPanel implements MouseListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	float temp = 25.0f;
	String desc;
	Boolean blue;
	
	public TemperaturePanel(String p_desc, Boolean p_blue)
	{
		this.desc = p_desc;
		this.blue = p_blue;
		addMouseListener(this);
	}
	

	public void paintComponent(Graphics g)
	{
		Color color1;
		Color color2;
		int up;
		int down;
		int fontS1;
		int fontS2;

		if(this.blue)
		{
			color1 = new Color(0, 129, 172);
			color2 = Color.WHITE;
			up = -60;
			down = 20;
			fontS1 = 20;
			fontS2 = 60;
		}
		else
		{
			color1 = Color.WHITE;
			color2 = new Color(0, 129, 172);
			up = -30;
			down = 10;
			fontS1 = 18;
			fontS2 = 40;
		}
		
		g.setColor(color1);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		 
		String text2 = temp + "°C";
		
		Font font1 = new Font("Roboto Light", Font.BOLD, fontS1);
		Font font2 = new Font("Roboto Light", Font.BOLD, fontS2);

	    g.setFont(font1);
	    g.setColor(color2);          
	    Graphics2D g2d = (Graphics2D) g;
        FontMetrics fm = g2d.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(this.desc, g2d);
        int x = (this.getWidth() - (int) r.getWidth()) / 2 ;
        int y = (this.getHeight() - (int) r.getHeight()) / 2 + fm.getAscent() + up;
        g.drawString(this.desc, x, y);
          
        
	    g.setFont(font2);
	    g.setColor(color2);
        FontMetrics fm2 = g2d.getFontMetrics();
        Rectangle2D r2 = fm2.getStringBounds(text2, g2d);
        int x2 = (this.getWidth() - (int) r2.getWidth()) / 2;
        int y2 = (this.getHeight() - (int) r2.getHeight()) / 2 + fm2.getAscent() + down;
        g.drawString(text2, x2, y2);
        
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
