import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Container;

/**
 * The main application window
 */

public class AppWindow extends JFrame {
   
   public AppWindow() {
      super();
      
      this.setLayout(new BorderLayout());
      Container content_pane = this.getContentPane();
      this.originalPanel = new ImagePanel();
      this.resultPanel = new ImagePanel();
      JPanel center_pane = new JPanel(new GridLayout(2, 1));
      this.originalPanel.setBorder(BorderFactory.createTitledBorder("Original:"));
      this.resultPanel.setBorder(BorderFactory.createTitledBorder("Result:"));
      
      center_pane.add(this.originalPanel);
      center_pane.add(this.resultPanel);
      content_pane.add(createToolbar(), BorderLayout.WEST);
      content_pane.add(center_pane, BorderLayout.CENTER);
      this.setJMenuBar(createMenuBar());
      
      this.setTitle("ImageProcessing");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(AppWindow.DEFAULT_WIDTH, AppWindow.DEFAULT_HEIGHT);
   }
   
   private JToolBar createToolbar() {
	  JToolBar tool_bar = new JToolBar();
	  return tool_bar;
   }
   
   private JMenuBar createMenuBar() {
	  JMenuBar menu_bar = new JMenuBar();
	  for(int i = 0; i < nameMenu.length; i++) {
	     if(!(nameMenu[i] == null || nameMenu[i].isEmpty())){
			JMenu menu = new JMenu(nameMenu[i]);
			for(int j = 0; j < nameMenuItem[i].length; j++)
			   if(nameMenuItem[i][j] == null || nameMenuItem[i][j].isEmpty())menu.addSeparator();
			   else {
				  JMenuItem menu_item = new JMenuItem(nameMenuItem[i][j]);
				  menu.add(menu_item);
			   }
			menu_bar.add(menu);
		 }
	  }
	  return menu_bar;
   }
   
   public static final int DEFAULT_WIDTH = 600;
   public static final int DEFAULT_HEIGHT = 500;
   private static final String[] nameMenu = {"File"};
   private static final String[][] nameMenuItem = {{"Load Image", "Save Image", null, "Exit"}};
   private ImagePanel originalPanel;
   private ImagePanel resultPanel;
}
