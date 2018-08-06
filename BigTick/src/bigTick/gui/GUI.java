package bigTick.gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import bigTick.options.OptionManager;
import bigTick.options.langs;

public class GUI
{
	private JFrame frame;
	private CardLayout clContent;
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
		clContent = new CardLayout(0, 0);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setVisible(false);
		
		frame.getContentPane().setLayout(clContent);

		frame.getContentPane().add(ContentCards.MAINMENU.name(), getMainMenu());
		frame.getContentPane().add(ContentCards.OPTIONS.name(), getOptionsMenu());
	}

	private Component getMainMenu()
	{
		JPanel pMainMenu = new JPanel();
		pMainMenu.setSize(frame.getSize());
		pMainMenu.setLocation(0, 0);
		pMainMenu.setLayout(null);
		
		JButton btnOptions = new JButton(om.getLang(langs.BTN_OPTIONS));
		btnOptions.setSize(width / 5, height / 25);
		btnOptions.setLocation((width - btnOptions.getWidth()) / 2, height / 25 * 18);
		btnOptions.setFont(new Font("Monospace", Font.BOLD, (int) (btnOptions.getHeight() * 0.7)));
		btnOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				clContent.show(frame.getContentPane(), ContentCards.OPTIONS.name());
			}
		});
		pMainMenu.add(btnOptions);
		
		JButton btnClose = new JButton(om.getLang(langs.BTN_CLOSE));
		btnClose.setSize(width / 5, height / 25);
		btnClose.setLocation((width - btnClose.getWidth()) / 2, height / 25 * 20);
		btnClose.setFont(new Font("Monospace", Font.BOLD, (int) (btnClose.getHeight() * 0.7)));
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

	private Component getOptionsMenu()
	{
		JPanel pOptionsMenu = new JPanel();
		pOptionsMenu.setSize(frame.getSize());
		pOptionsMenu.setLocation(0, 0);
		pOptionsMenu.setLayout(null);
		
		JTabbedPane tpOptions = new JTabbedPane();
		tpOptions.setSize(width - (width / 20), height - (height / 10));
		tpOptions.setLocation((width - tpOptions.getWidth()) / 2, (height - tpOptions.getHeight()) / 5);
		tpOptions.setFont(new Font("Monospace", Font.BOLD, 30));
		
		JPanel pGeneral = new JPanel();

		tpOptions.addTab(om.getLang(langs.TAB_OPTIONS_GENERAL), pGeneral);
		
		JPanel pGraphics = new JPanel();
		
		tpOptions.addTab(om.getLang(langs.TAB_OPTIONS_GRAFICS), pGraphics);
		
		JPanel pSounds = new JPanel();
		
		tpOptions.addTab(om.getLang(langs.TAB_OPTIONS_SOUNDS), pSounds);
		
		JPanel pResources = new JPanel();
		
		tpOptions.addTab(om.getLang(langs.TAB_OPTIONS_RESOURCES), pResources);

		tpOptions.setSelectedIndex(0);
		
		pOptionsMenu.add(tpOptions);
		
		JButton btnBack = new JButton(om.getLang(langs.BTN_BACK));
		btnBack.setSize(width / 7, height / 20);
		btnBack.setLocation(width - btnBack.getWidth() - tpOptions.getX(), (tpOptions.getY() + tpOptions.getHeight()) + (height - tpOptions.getHeight() - tpOptions.getY() - btnBack.getHeight()) / 2);
		btnBack.setFont(new Font("Monospace", Font.BOLD, (int) (btnBack.getHeight() * 0.7)));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				clContent.show(frame.getContentPane(), ContentCards.MAINMENU.name());
			}
		});
		
		pOptionsMenu.add(btnBack);
		
		return pOptionsMenu;
	}

	public void visible()
	{
		if (frame.isVisible()) frame.setVisible(false);
		else frame.setVisible(true);
	}
}
