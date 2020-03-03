import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class SouthControlButtonPanel extends JPanel {

	public JButton play, stop, nextfile, previousfile, mute, forward, backward, repeat;
	public JSlider volume;
	private JPanel panel;
	
	public JPanel timerpanel;
	public JSlider timerSlider;
	
	public boolean pressPlay = false;
	public boolean pressStop = false;
	public boolean pressNextFile = false;
	public boolean pressPreviousFile = false;
	public boolean pressbackward = false;
	public boolean pressforward = false;
	public boolean pressrewind = false;
	public boolean pressmute = false;
	public final int speedRate = 5000;
	
	public SouthControlButtonPanel(int high, int width)
	{
		initalization();
		panel.setPreferredSize(new Dimension(high, width));
		createUI();
		setDefaultIcon();
		wire();
	
	}
	private void initalization()
	{
		this.setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new GridLayout(1,9));
		timerpanel = new JPanel();
		timerSlider = new JSlider();
		play = new JButton();
		stop = new JButton();
		repeat = new JButton();
		nextfile = new JButton();
		previousfile = new JButton();
		mute = new JButton();
		forward = new JButton();
		backward = new JButton();
		volume = new JSlider(0,100,50);

	}
	private void createUI()
	{

		play.setActionCommand("play");

		
		stop.setActionCommand("stop");
		repeat.setActionCommand("repeat");
		nextfile.setActionCommand("next file");
		previousfile.setActionCommand("previous file");
		mute.setActionCommand("mute");
		forward.setActionCommand("forward");
		backward.setActionCommand("backward");

		volume.setOrientation(JSlider.VERTICAL);

		volume.setPaintLabels(true);

		panel.add(backward);
		panel.add(play);

		panel.add(forward);
		panel.add(stop);

		panel.add(previousfile);
		panel.add(nextfile);
		panel.add(repeat);

		panel.add(mute);

		panel.add(volume);
		
		this.add(timerpanel, BorderLayout.NORTH);
		this.add(panel, BorderLayout.SOUTH);
	}

	private void setDefaultIcon()
	{
		ImageIcon playicon = new ImageIcon("playIcon(clicked).png");
		play.setIcon(playicon);

		ImageIcon nextfileicon = new ImageIcon("forwardskipIcon.png");
		nextfile.setIcon(nextfileicon);

		ImageIcon previousfileicon = new ImageIcon("backwardskipIcon.png");
		previousfile.setIcon(previousfileicon);

		ImageIcon repeaticon = new ImageIcon("repeatIcon.png");
		repeat.setIcon(repeaticon);

		ImageIcon forwardicon = new ImageIcon("forwardIcon.png");
		forward.setIcon(forwardicon);

		ImageIcon backwardicon = new ImageIcon("backwardIcon.png");
		backward.setIcon(backwardicon);		

		ImageIcon stopicon = new ImageIcon("stopIcon.png");
		stop.setIcon(stopicon);

		ImageIcon muteicon = new ImageIcon("muteIcon.png");
		mute.setIcon(muteicon);

	}


	public void IconChange(String fileName, JButton pressButton)
	{
		ImageIcon Icon = new ImageIcon(fileName);
		pressButton.setIcon(Icon);
	}
	public void wire()
	{
//		ActionListener buttonsLis = new ActionListener() {
//
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JButton button = (JButton) e.getSource();
//
//				switch(button.getActionCommand())
//				{
//				case "play":
//				{		
//					boolean debug = true;
//
//					if(debug)
//						System.out.println("IN PLAY");
//
//					if(pressPlay)
//					{
//						if(debug)
//							System.out.println("is selected");
//						IconChange("pauseIcon.png",play);
//						pressPlay = false;
//						playrunnable();
//					}
//					else
//					{
//						if(debug)
//							System.out.println("is not selected");
//						IconChange("playIcon(clicked).png",play);
//						pressPlay = true;
//						pauserunnable();
//					}
//					break;
//				}
//
//				case "stop":
//				{
//					boolean debug = true;
//					if(debug)
//						System.out.println("IN STOP");
//					if(pressStop)
//					{
//						if(debug)
//							System.out.println("is selected");
//						IconChange("stopIcon(clicked).png",stop);
//						IconChange("playIcon(clicked).png",play);
//						pressStop = false;
//					}
//					else
//					{
//						if(debug)
//							System.out.println("not selected");
//						IconChange("stopIcon.png",stop);
//
//						pressStop = true;
//					}
//					//					mediaCenterPanel.mediaStop();
//					break;
//				}
//				case "next file":
//				{
//					boolean debug = true;
//
//					if(debug)
//						System.out.println("IN NEXTFILE");
//					if(pressNextFile)
//					{
//						if(debug)
//							System.out.println("is selected");
//						IconChange("forwardskipIcon(clicked).png",nextfile);
//						IconChange("playIcon(clicked).png",play);
//						IconChange("stopIcon.png",stop);
//
//						pressNextFile = false;
//					}
//					else
//					{
//						if(debug)
//							System.out.println("not selected");
//						IconChange("forwardskipIcon.png",nextfile);
//						pressNextFile = true;
//					}
//					//					mediaCenterPanel.mediaNextFile();
//					break;
//				}
//				case "previous file":
//				{
//					boolean debug = true;
//					if(debug)
//						System.out.println("IN PREVIOUS FILE");
//					if(pressPreviousFile)
//					{
//						if(debug)
//							System.out.println("is selected");
//						IconChange("backwardskipIcon(clicked).png",previousfile);
//						IconChange("playIcon(clicked).png",play);
//						IconChange("stopIcon.png",stop);
//						IconChange("forwardskipIcon.png",nextfile);
//
//						pressPreviousFile = false;
//					}
//					else
//					{
//						if(debug)
//							System.out.println("not selected");
//						IconChange("backwardskipIcon.png",previousfile);
//						pressPreviousFile = true;
//					}
//					//					mediaPreviousFile();
//					break;
//				}
//				case "backward":
//				{
//					boolean debug = true;
//					if(debug)
//						System.out.println("IN BACKWARD");
//					if(pressbackward)
//					{
//						if(debug)
//							System.out.println("is selected");
//						IconChange("backwardIcon(clicked).png",backward);
//						IconChange("playIcon(clicked).png",play);
//						IconChange("stopIcon.png",stop);
//						IconChange("forwardskipIcon.png",nextfile);
//
//						pressbackward = false;
//					}
//					else
//					{
//						if(debug)
//							System.out.println("not selected");
//						IconChange("backwardIcon.png",backward);
//						pressbackward = true;
//					}
//					break;
//				}
//				case "forward":
//				{
//					boolean debug = true;
//					if(debug)
//						System.out.println("IN FORWARD");
//					if(pressforward)
//					{
//						if(debug)
//							System.out.println("is selected");
//						IconChange("forwardIcon(clicked).png",forward);
//						IconChange("playIcon(clicked).png",play);
//						IconChange("stopIcon.png",stop);
//						IconChange("forwardskipIcon.png",nextfile);
//						IconChange("backwardIcon.png",backward);
//
//						pressforward = false;
//					}
//					else
//					{
//						if(debug)
//							System.out.println("not selected");
//						IconChange("forwardIcon.png",forward);
//						pressforward = true;
//					}
//					break;
//				}
//				case "repeat":
//				{
//					boolean debug = true;
//					if(debug)
//						System.out.println("IN REWIND");
//					if(pressrewind)
//					{
//						if(debug)
//							System.out.println("is selected");
//						IconChange("repeatIcon(clicked).png",repeat);
//						IconChange("playIcon(clicked).png",play);
//						IconChange("stopIcon.png",stop);
//						IconChange("forwardskipIcon.png",nextfile);
//						IconChange("backwardIcon.png",backward);
//						IconChange("forwardIcon.png",forward);
//
//						pressrewind = false;
//					}
//					else
//					{
//						if(debug)
//							System.out.println("not selected");
//						IconChange("repeatIcon.png",repeat);
//						pressrewind = true;
//					}
//					break;
//				}
//
//				default:
//					break;	
//				}
//			};
//		};

		javax.swing.event.ChangeListener sliderchange = new javax.swing.event.ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				boolean debug = true;
				JSlider value = (JSlider)arg0.getSource();
				if(debug)
					System.out.println("Value: " + value.getValue());
			}
		};
	
//		volume.addChangeListener(sliderchange);
//
//		play.addActionListener(buttonsLis);
//		stop.addActionListener(buttonsLis);
//		nextfile.addActionListener(buttonsLis);
//		previousfile.addActionListener(buttonsLis);
//		backward.addActionListener(buttonsLis);
//		forward.addActionListener(buttonsLis);
//		repeat.addActionListener(buttonsLis);

		repaint();
	}
}
