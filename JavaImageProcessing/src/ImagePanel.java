import javax.swing.JComponent;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.RenderingHints;

/**
 * Сomponent for viewing an image
 */
 
public class ImagePanel extends JComponent {
	
   public ImagePanel() {
      super();
   }
   
   public ImagePanel(BufferedImage image) {
      super();
      this.image = image;
   }
   
   public void paint(Graphics g){
	  super.paint(g);
	  Graphics2D g2 = (Graphics2D)g;
	  g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	  g2.setColor(Color.GRAY);
	  g2.fillRect(10, 20, this.getWidth()-20, this.getHeight()-30);
	  if(image == null) {
		 String message  = "Please upload your image :)";
		 g2.setColor(Color.BLACK);
		 g2.setFont(new Font(g2.getFont().getFamily(), Font.BOLD, 20));
		 FontMetrics fm = g2.getFontMetrics();
         Rectangle2D r = fm.getStringBounds(message, g2);
         int x = (this.getWidth() - (int) r.getWidth())/2;
         int y = (this.getHeight() + 10 - (int) r.getHeight())/2 + fm.getAscent();
         g2.drawString(message, x, y);
	  }
   }
   
   private BufferedImage image;
}
