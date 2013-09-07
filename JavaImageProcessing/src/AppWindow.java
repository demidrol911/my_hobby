import javax.swing.JFrame;
import java.awt.GridBagLayout;

/**
 * The main application window
 */

public class AppWindow extends JFrame {
   
   public AppWindow() {
      super();
      
      this.setLayout(new GridBagLayout());
      
      this.setTitle("ImageProcessing");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(AppWindow.DEFAULT_WIDTH, AppWindow.DEFAULT_HEIGHT);
   }
   
   public static final int DEFAULT_WIDTH = 600;
   public static final int DEFAULT_HEIGHT = 500;
}
