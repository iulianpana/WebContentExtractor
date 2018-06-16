package cs.pub.web.content.extractor.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "cs.pub.web.content.extractor" })
public class StartFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5800289181988937016L;
	
	private ConfigurationWindow config;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(StartFrame.class).headless(false).run(args);

		EventQueue.invokeLater(() -> {
			StartFrame ex = ctx.getBean(StartFrame.class);
			ex.setVisible(true);

		});

		System.out.println("Started!");

	}

	/**
	 * Create the frame.
	 */
	public StartFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenSize.getWidth(), (int) screenSize.getHeight() - 40 );
		setTitle("StartWindow");
		
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setConfig(new ConfigurationWindow());
				setVisible(false);
				getConfig().setVisible(true);
			}
		});
		
		try {
		    final Image backgroundImage = javax.imageio.ImageIO.read(new File("start.jpeg"));
		    setContentPane(new JPanel(new BorderLayout()) {
				private static final long serialVersionUID = -5899240922256609283L;
				@Override 
				public void paintComponent(Graphics g) {
		            g.drawImage(backgroundImage, 0, 0, null);
		        }
		    });
		    
		} catch (IOException e) {
		    throw new RuntimeException(e);
		}
		

		getContentPane().add(btnStart, BorderLayout.SOUTH);
	}

	public ConfigurationWindow getConfig() {
		return config;
	}

	public void setConfig(ConfigurationWindow config) {
		this.config = config;
	}

}
