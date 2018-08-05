package bigTick.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bigTick.options.OptionManager;
import bigTick.options.langs;
import bigTick.options.options;

public class GUI
{
	private JFrame frame;
	private OptionManager om;
	private final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	private final int height = Toolkit.getDefaultToolkit().getScreenSize().height;

	public GUI(OptionManager om)
	{
		this.om = om;
		init();
	}

	private void init()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(false);
		
		frame.add(getMainMenu());
	}

	private Component getMainMenu()
	{
		JPanel pMainMenu = new JPanel();
		pMainMenu.setSize(frame.getSize());
		pMainMenu.setLocation(0, 0);
		pMainMenu.setLayout(null);
		
		JButton btnClose = new JButton(om.getLang(langs.BTN_CLOSE));
		btnClose.setSize(width / 5, height / 25);
		btnClose.setLocation((width - btnClose.getWidth()) / 2, height / 25 * 20);
		btnClose.setFont(new Font("Monospace", Font.BOLD, (int) (btnClose.getHeight() * 0.8)));
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		pMainMenu.add(btnClose);
		
		return pMainMenu;
	}

	public void visible()
	{
		if (frame.isVisible()) frame.setVisible(false);
		else frame.setVisible(true);
	}
}
