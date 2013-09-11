import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.RenderingHints;

/**
 * Сomponent for viewing an image
 */
 
@SuppressWarnings("serial")
public class ImagePanel extends JComponent {
	
	public ImagePanel() {
		super();
	}
	
	public ImagePanel(BufferedImage image) {
		this();
		this.image = image;
	}
   
	public void setImage(BufferedImage image) {
		this.image = image;
		this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
		this.revalidate();
		this.repaint();
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.GRAY);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(image == null) {
			String message  = "Please upload your image :)";
			g2.setColor(Color.BLACK);
			g2.setFont(new Font(g2.getFont().getFamily(), Font.BOLD, 20));
			FontMetrics fm = g2.getFontMetrics();
			Rectangle2D r = fm.getStringBounds(message, g2);
			int x = (this.getWidth()-(int)r.getWidth())/2;
			int y = (this.getHeight()-(int)r.getHeight())/2+fm.getAscent();
			g2.drawString(message, x, y);
		}
		else g2.drawImage(image, (this.getWidth()-image.getWidth())/2, (this.getHeight()-image.getHeight())/2, this);
	}
	
	private BufferedImage image;
}
