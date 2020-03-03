import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SplitPaneUI;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;


public class VideoPlayer extends JPanel{

	private JPanel panel;
	private SouthControlButtonPanel south;
	private NorthControlMenu north;
	private Map<JButton, Runnable> southButtons;
	private JPanel center;
	private Map<JMenuItem, Runnable> northMenus;
	private ActionListener presslisten;
	private MouseListener mouselisten;
	private EmbeddedMediaPlayerComponent media;
	private int currentVolumeValue;
	private FilesStore storage;
	private boolean mousePressed;


	public VideoPlayer()
	{
		initialization();
		createUI();
		combinationConnected();

		addlisteners();
	}

	public void initialization()
	{
		storage = new FilesStore();
		center = new JPanel();
		center.setLayout(new BorderLayout());
		center.setPreferredSize(new Dimension(800,800));

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		north = new NorthControlMenu(800,60);
		south = new SouthControlButtonPanel(800,60);
		currentVolumeValue = south.volume.getValue();
		southButtons = new LinkedHashMap<>();
		northMenus = new LinkedHashMap<>();
		mousePressed = false;
		media = new EmbeddedMediaPlayerComponent();
	}

	private void createUI()
	{
		center.add(media, BorderLayout.CENTER);
		panel.add(center, BorderLayout.CENTER);
		
		panel.add(north, BorderLayout.NORTH);
		panel.add(south,  BorderLayout.SOUTH);
		this.add(panel);	
	}
	
	//-------------------------------------------------------
	//-------------------------------------------------------
	//-------------------------------------------------------
	//					    2 in 1
	//-------------------------------------------------------
	//-------------------------------------------------------
	//-------------------------------------------------------

	private void combinationConnected()
	{
		northMenusConnected();
		SouthButtonsConnected();
	}

	//-------------------------------------------------------
	//-------------------------------------------------------
	//-------------------------------------------------------
	//					    LISTENERS
	//-------------------------------------------------------
	//-------------------------------------------------------
	//-------------------------------------------------------

	private void addlisteners()
	{
		volumelistener();
		presslistener();

		for (JButton button : southButtons.keySet())
			button.addActionListener(presslisten);
		for(JMenuItem menu: northMenus.keySet())
			menu.addActionListener(presslisten);
	}

	//-------------------------------------------------------

	private void volumelistener()
	{
		boolean debug = true;
		south.volume.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider input = (JSlider) e.getSource();
				currentVolumeValue = input.getValue();
				if(debug)
					System.out.println("currentVolumeValue: " + currentVolumeValue);
				if(currentVolumeValue==0)
				{
					if(debug)
						System.out.println("mute");
					media.getMediaPlayer().setVolume(0);
					south.IconChange("muteIcon(click).png",south.mute);

				}
				else
				{
					if(debug)
						System.out.println("unmute");
					south.IconChange("muteIcon.png",south.mute);

					media.getMediaPlayer().setVolume(currentVolumeValue);
				}
			}
		});
	}

	//-------------------------------------------------------

	private void presslistener()
	{
		presslisten = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object input = e.getSource();
				if(input instanceof JButton)
				{
					southButtons.get((JButton) input).run();
				}
				else if (input instanceof JMenuItem)
				{
					northMenus.get((JMenuItem) input).run();
				}
			}
		};
	}

	//-------------------------------------------------------
//	private void mouselistener()
//	{
//		mouselisten = new MouseListener() {
//			@Override
//			public void mouseReleased(MouseEvent e) {
//
//			}
//
//			@Override
//			public void mousePressed(MouseEvent e) {
//				JButton source = (JButton) e.getSource();
//				if(source.equals(south.forward))
//				{
//					//					media.getMediaPlayer().
//				}
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//
//			}
//		};
//	}

	//-------------------------------------------------------
	//-------------------------------------------------------
	//-------------------------------------------------------
	//						SOUTH
	//-------------------------------------------------------
	//-------------------------------------------------------
	//-------------------------------------------------------
	private void SouthButtonsConnected()
	{
		sPlay();
		sStop();
		sMuteUnmute();
		sNext();
		sPrevious();
		sBackward();
		sForward();
		sRepeat();
	}

	//-------------------------------------------------------


	//-------------------------------------------------------
	//-------------------------------------------------------
	private void sMuteUnmute()
	{
		boolean debug = true;
		if(debug)
		{
			System.out.println("sMuteUnmute");
			System.out.println("currentVolumeValue: " + currentVolumeValue);
		}
		southButtons.put(south.mute,new Runnable() {
			@Override
			public void run() {
				if(south.pressmute)
				{
					if(debug)
					{	
						System.out.println("unmute in smutunmute");
						System.out.println("currentVolumeValue: " + currentVolumeValue);
					}
					media.getMediaPlayer().setVolume(currentVolumeValue);
					south.IconChange("muteIcon.png",south.mute);
					south.pressmute = false;
				}
				else
				{

					if(debug)
					{
						System.out.println("mute in smutunmute");
						System.out.println("currentVolumeValue: " + currentVolumeValue);
					}
					south.IconChange("muteIcon(click).png",south.mute);
					media.getMediaPlayer().setVolume(0);
					south.pressmute = true;
				}
			}
		});
	}
	//-------------------------------------------------------

	private void sPlay()
	{
		boolean debug = true;
		if(debug)
			System.out.println("splay");
		southButtons.put(south.play, new Runnable() {
			@Override
			public void run() {
				playCommon();
			}

		});
	}
	//-------------------------------------------------------


	private void sStop()
	{
		boolean debug = true;
		if(debug)
			System.out.println("sStop");
		southButtons.put(south.stop, new Runnable() {
			@Override
			public void run() {
				stopCommon();
			}
		});
	}
	//-------------------------------------------------------

	private void play(String filename)
	{
		boolean debug = true;
		if(debug)
		{
			System.out.println("play string");
			System.out.println("currentVolumeValue: "  + currentVolumeValue);

		}
		sStop();
		media.getMediaPlayer().playMedia(filename);
		south.pressPlay = true;
		south.pressmute = false;
	}

	//-------------------------------------------------------

	private void sNext()
	{
		southButtons.put(south.nextfile,new Runnable() {

			@Override
			public void run() {
				nextCommon();
			}
		});
	}

	//-------------------------------------------------------

	private void sPrevious()
	{
		southButtons.put(south.previousfile,new Runnable() {

			@Override
			public void run() {
				previousCommon();				
			}
		});
	}

	//-------------------------------------------------------

	private void sBackward()
	{
		southButtons.put(south.backward,new Runnable() {

			@Override
			public void run() {
				media.getMediaPlayer().skip(-south.speedRate);		
			}
		});
	}

	//-------------------------------------------------------
	private void sForward()
	{
		southButtons.put(south.forward,new Runnable() {

			@Override
			public void run() {
				media.getMediaPlayer().skip(south.speedRate);		
			}
		});
	}

	//-------------------------------------------------------
	private void sRepeat()
	{
		southButtons.put(south.repeat,new Runnable() {

			@Override
			public void run() {
				media.getMediaPlayer().setRepeat(true);	
			}
		});
	}

	//-------------------------------------------------------
	//-------------------------------------------------------
	//-------------------------------------------------------




	//-------------------------------------------------------
	//-------------------------------------------------------
	//-------------------------------------------------------
	//						NORTH
	//-------------------------------------------------------
	//-------------------------------------------------------
	//-------------------------------------------------------
	
	private void northMenusConnected()
	{
		nOpenFile();
		nExit();
		nPlay();
		nStop();

		nNext();
		nPrevious();
	}

	//-------------------------------------------------------

	public void fileReceving()
	{
		boolean debug = false;
		if(debug)
			System.out.println("in file reading");

		north.chooseFile.setCurrentDirectory(new java.io.File("C:/"));
		north.chooseFile.addChoosableFileFilter(north.mediaFilter);
		north.chooseFile.setMultiSelectionEnabled(true);
		int check = north.chooseFile.showOpenDialog(north);

		if(check==JFileChooser.APPROVE_OPTION)
		{
			File[] files = north.chooseFile.getSelectedFiles();

			for(File file : files)
			{
				String fileName = file.getAbsolutePath().toLowerCase();
				for(int i = 0; i < north.extensions.length;i++)
				{
					if(fileName.endsWith(north.extensions[i]))
					{
						media.getMediaPlayer().prepareMedia(fileName);
						storage.add(file);
					}
				}
			}
			play(files[0].getAbsolutePath());
		}
		else
		{
			JOptionPane.showConfirmDialog(north, "Not a media files", "Nope!",JOptionPane.CANCEL_OPTION);
		}
	}
	
	//-------------------------------------------------------

	private void nOpenFile()
	{
		northMenus.put(north.filestolibrary, new Runnable() {
			@Override
			public void run() {
				fileReceving();
			}
		});
	}

	//-------------------------------------------------------

	private void nExit()
	{
		northMenus.put(north.exit, new Runnable() {

			@Override
			public void run() {
				System.exit(0);
			}
		});
	}
	
	//-------------------------------------------------------

	private void nPlay()
	{
		northMenus.put(north.play, new Runnable() {
			@Override
			public void run() {
				playCommon();
			}
		});
	}
	
	//-------------------------------------------------------

	private void nStop()
	{
		northMenus.put(north.stop,new Runnable() {
			
			@Override
			public void run() {
				stopCommon();
			}
		});
	}

	//-------------------------------------------------------

	private void nNext()
	{
		northMenus.put(north.next, new Runnable() {
			
			@Override
			public void run() {
				nextCommon();
			}
		});
	}
	
	//-------------------------------------------------------

	private void nPrevious()
	{
		northMenus.put(north.previous, new Runnable() {
			
			@Override
			public void run() {
				previousCommon();
			}
		});
	}	
	
	//-------------------------------------------------------
	private void nOneforall()
	{
		northMenus.put(north.allForOne, new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//-------------------------------------------------------
	//-------------------------------------------------------
	//-------------------------------------------------------
	//					     COMMON
	//-------------------------------------------------------
	//-------------------------------------------------------
	//-------------------------------------------------------

	private void playCommon()
	{
		if(south.pressPlay)
		{
			media.getMediaPlayer().pause();
			south.IconChange("pauseIcon(clicked).png",south.play);
			south.pressPlay = false;
		}
		else
		{
			south.IconChange("playIcon(clicked).png",south.play);

			media.getMediaPlayer().play();
			south.pressPlay = true;
		}
	}

	//-------------------------------------------------------

	private void stopCommon()
	{
		media.getMediaPlayer().stop();
	}
	
	//-------------------------------------------------------

	private void nextCommon()
	{
		String files = storage.nextFile();
		play(files);	
	}

	//-------------------------------------------------------

	private void previousCommon()
	{
		String files = storage.previousFile();
		play(files);
	}
}
