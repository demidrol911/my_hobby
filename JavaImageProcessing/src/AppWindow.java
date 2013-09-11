import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

/**
 * The main application window
 */

@SuppressWarnings("serial")
public class AppWindow extends JFrame implements ActionListener {
	
	public AppWindow() {
		super();
		this.setLayout(new BorderLayout());
		Container content_pane = this.getContentPane();
		this.originalPanel = new ImagePanel();
		this.resultPanel = new ImagePanel();
		JPanel original_pane = new JPanel(new BorderLayout());
		JPanel result_pane = new JPanel(new BorderLayout());
		JSplitPane vertical_split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		vertical_split.setContinuousLayout(true);
		vertical_split.setDividerSize(10);
		vertical_split.setOneTouchExpandable(true);
		vertical_split.setTopComponent(original_pane);
		vertical_split.setBottomComponent(result_pane);
		
		original_pane.add(new JLabel("Original:"), BorderLayout.NORTH);
		original_pane.add(new JScrollPane(originalPanel), BorderLayout.CENTER);
		result_pane.add(new JLabel("Result:"), BorderLayout.NORTH);
		result_pane.add(new JScrollPane(resultPanel), BorderLayout.CENTER);
		content_pane.add(createToolbar(), BorderLayout.WEST);
		content_pane.add(vertical_split, BorderLayout.CENTER);
		this.setJMenuBar(createMenuBar());
		
		this.setTitle("ImageProcessing");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(AppWindow.DEFAULT_WIDTH, AppWindow.DEFAULT_HEIGHT);
		this.setVisible(true);
		vertical_split.setDividerLocation(0.5);
	}
	
	private JToolBar createToolbar() {
		JToolBar tool_bar = new JToolBar();
		return tool_bar;
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menu_bar = new JMenuBar();
		for(int i = 0; i < nameMenu.length; i++)
			if(!(nameMenu[i] == null || nameMenu[i].isEmpty())){
				JMenu menu = new JMenu(nameMenu[i]);
				for(int j = 0; i < nameMenuItem.length && j < nameMenuItem[i].length; j++)
					if(nameMenuItem[i][j] == null || nameMenuItem[i][j].isEmpty())menu.addSeparator();
					else {
						JMenuItem menu_item = new JMenuItem(nameMenuItem[i][j]);
						if(!(commandMenuItem[i][j] == null || commandMenuItem[i][j].isEmpty())){
							menu_item.setActionCommand(commandMenuItem[i][j]);
							menu_item.addActionListener(this);
						}
					menu.add(menu_item);
			}
			menu_bar.add(menu);
		}
		return menu_bar;
	}
   
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("load_image")){
			JFileChooser fileChooser = new JFileChooser();
			if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
				originalPanel.setImage(ImageOperation.loadImage(fileChooser.getSelectedFile()));
				resultPanel.setImage(ImageOperation.loadImage(fileChooser.getSelectedFile()));
			}
		}
		else if(cmd.equals("conv_image")){
			float[][] kernel = {{-0.1f, 0.2f, -0.1f},
					            {0.2f, 3, 0.2f},
					            {-0.1f, 0.2f, -0.1f}};
			resultPanel.setImage(ImageOperation.convolution(originalPanel.getImage(), kernel));
		}
	}
	
	public static final int DEFAULT_WIDTH = 700;
	public static final int DEFAULT_HEIGHT = 600;
	private static final String[] nameMenu = {"File", "Tools"};
	private static final String[][] nameMenuItem = {{"Load Image", "Save Image", null, "Exit"}, {"Convolution"}};
	private static final String[][] commandMenuItem = {{"load_image", "save_image", null, "exit"}, {"conv_image"}};
	private ImagePanel originalPanel;
	private ImagePanel resultPanel;
}
