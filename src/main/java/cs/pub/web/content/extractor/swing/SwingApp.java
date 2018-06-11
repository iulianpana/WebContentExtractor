package cs.pub.web.content.extractor.swing;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import cs.pub.web.content.extractor.crawler.MyCommandLineRunner;

@SpringBootApplication
public class SwingApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyCommandLineRunner runner = new MyCommandLineRunner();

	public SwingApp() {

		initUI();
	}

	private void initUI() {
		JButton crawl = new JButton("Crawl");
		JButton quitButton = new JButton("Quit");

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

		setTitle("Quit button");
		setLocationRelativeTo(crawl);
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