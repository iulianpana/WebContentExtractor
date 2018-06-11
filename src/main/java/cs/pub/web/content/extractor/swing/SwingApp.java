package cs.pub.web.content.extractor.swing;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import cs.pub.web.content.extractor.crawler.MyCommandLineRunner;

@SpringBootApplication
@ComponentScan(basePackages = {"cs.pub.web.content.extractor"})
public class SwingApp extends JFrame {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	MyCommandLineRunner runner;

	public SwingApp() {

		initUI();
	}

	private void initUI() {
		
		JButton crawl = new JButton("Crawl");
		JButton quitButton = new JButton("Quit");

		quitButton.addActionListener((ActionEvent event) -> {
			System.exit(0);
		});
		
		crawl.addActionListener((ActionEvent event) -> {

			try {
				runner.run(new String[2]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

		createLayout(quitButton);
		createLayout(crawl);

		setTitle("WebContentExtractor");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)screenSize.getWidth(), (int) screenSize.getHeight() - 40 );
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void createLayout(JComponent... arg) {

		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);

		gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(arg[0]));

		gl.setVerticalGroup(gl.createSequentialGroup().addComponent(arg[0]));
	}

	public static void main(String[] args) {

		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(SwingApp.class).headless(false).run(args);
		
		EventQueue.invokeLater(() -> {
			SwingApp ex = ctx.getBean(SwingApp.class);
			ex.setVisible(true);
			
		});

		System.out.println("Started!");
	}
}