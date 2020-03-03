import java.awt.Dimension;

import javax.swing.JFrame;

import uk.co.caprica.vlcj.discovery.NativeDiscovery;

public class GaryVideoPlayer {

	public static void main(String[] str)
	{
		new NativeDiscovery().discover();

		int width = 800;
		int length = 800;

		JFrame frame = new JFrame();
		frame.setTitle("Video Player");
		frame.pack();
		frame.setVisible(true);
		frame.add(new VideoPlayer());
		frame.setMinimumSize(new Dimension(width, length));

		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

	}
}
