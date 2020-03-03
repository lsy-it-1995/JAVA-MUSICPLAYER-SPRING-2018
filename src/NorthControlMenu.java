import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class NorthControlMenu extends JMenuBar {

	public JMenuBar menu;

	public JMenu file;
	public JMenuItem filestolibrary, exit;

	public JMenu playback;
	public JMenuItem play, stop, next, previous;
	public FileNameExtensionFilter mediaFilter;

	public JMenu windows;
	public JMenuItem oneForAll, allForOne;
	
	public JFileChooser chooseFile;
	public String[] extensions;

	public NorthControlMenu()
	{
		initalization();
		createUI();

	}

	private void filemenu()
	{		
		chooseFile = new JFileChooser();

		file = new JMenu("File");
		filestolibrary = new JMenuItem("Add file(s) to library");
		exit = new JMenuItem("Exit");
	}
	
	private void playbackmenu()
	{
		playback = new JMenu("Play back");
		play = new JMenuItem("Play/Pause");
		stop = new JMenuItem("Stop");
		next = new JMenuItem("Next");
		previous = new JMenuItem("previous");

	}
	private void windowmenu()
	{
		windows = new JMenu("Window");
		oneForAll = new JMenuItem("One for all");
		allForOne = new JMenuItem("All for one");
	}
	
	private void initalization()
	{
		filemenu();
		playbackmenu();
		windowmenu();
		menu = new JMenuBar();
		extensions= new String[] {"Video", "mp4", "mkv", "mp3"};
		mediaFilter = new FileNameExtensionFilter("video files only",extensions);

	}
	public NorthControlMenu(int high, int width) {
		initalization();
		menu.setPreferredSize(new Dimension(high, width));
		createUI();
	}

	public void createUI()
	{
		//file
		menu.add(file);
		file.add(filestolibrary);
		file.add(exit);
		file.setMnemonic(KeyEvent.VK_E);
		filestolibrary.setMnemonic(KeyEvent.VK_E);	

		//playback

		menu.add(playback);
		playback.add(play);
		playback.add(stop);
		playback.add(next);
		playback.add(previous);
		playback.setMnemonic(KeyEvent.VK_E);
		play.setMnemonic(KeyEvent.VK_E);
		stop.setMnemonic(KeyEvent.VK_E);
		next.setMnemonic(KeyEvent.VK_E);
		previous.setMnemonic(KeyEvent.VK_E);
		
		//special
		menu.add(windows);
		windows.add(oneForAll);
		windows.add(allForOne);
		oneForAll.setMnemonic(KeyEvent.VK_E);
		allForOne.setMnemonic(KeyEvent.VK_E);
		this.add(menu);
	}
}
