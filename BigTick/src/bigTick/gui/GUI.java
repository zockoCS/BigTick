package bigTick.gui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

import bigTick.Init;
import bigTick.options.OptionManager;
import bigTick.options.langs;
import bigTick.options.options;

public class GUI
{
	private JFrame frame;
	private CardLayout clContent;
	private OptionManager om;
	private final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	private final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	private JLabel lSinglePlayerChange;
	private JLabel lSingleTie;
	private JLabel lSinglePlayerWin;
	private FieldPanel pField;
	
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
		frame.getContentPane().add(ContentCards.START.name(), getStartMenu());
		frame.getContentPane().add(ContentCards.GAME_SINGLE.name(), getGameSingle());
		frame.getContentPane().add(ContentCards.GAME_MULTI.name(), getGameMulti());
	}

	private Component getMainMenu()
	{
		JPanel pMainMenu = new JPanel();
		pMainMenu.setSize(frame.getSize());
		pMainMenu.setLocation(0, 0);
		pMainMenu.setLayout(null);
		
		JButton btnStart = new JButton(om.getLang(langs.BTN_START));
		btnStart.setSize(width / 5, height / 25);
		btnStart.setLocation((width - btnStart.getWidth()) / 2, height / 25 * 16);
		btnStart.setFont(new Font("Monospace", Font.BOLD, (int) (btnStart.getHeight() * 0.7)));
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				clContent.show(frame.getContentPane(), ContentCards.START.name());
			}
		});
		pMainMenu.add(btnStart);
		
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
		pGeneral.setSize(tpOptions.getWidth(), 1080);
		pGeneral.setLocation(0, 0);
		pGeneral.setLayout(null);
		int iGeneral = 0;
		
		JComboBox<String> cbLangs = new JComboBox<>();
		
		for (File langFile : new File("languages").listFiles())
		{
			cbLangs.addItem(langFile.getName().replace(".lang", ""));
		}
		
		cbLangs.setSelectedItem(om.getOption(options.LANGUAGE));

		addOption(pGeneral, iGeneral, om.getLang(langs.NAME_OPTIONS_LANGUAGE), cbLangs);
		
		cbLangs.setFont(new Font("Monospace", Font.PLAIN, (int) (cbLangs.getHeight() * 0.7)));
		
		JScrollPane spGeneral = new JScrollPane();
		spGeneral.setSize(tpOptions.getWidth(), tpOptions.getHeight());
		spGeneral.setBorder(null);
		spGeneral.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spGeneral.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spGeneral.setWheelScrollingEnabled(true);
		spGeneral.setViewportView(pGeneral);

		tpOptions.addTab(om.getLang(langs.TAB_OPTIONS_GENERAL), spGeneral);
		
		JPanel pGraphics = new JPanel();
		
		tpOptions.addTab(om.getLang(langs.TAB_OPTIONS_GRAFICS), pGraphics);
		
		JPanel pSounds = new JPanel();
		
		tpOptions.addTab(om.getLang(langs.TAB_OPTIONS_SOUNDS), pSounds);
		
		JPanel pResources = new JPanel();
		
		tpOptions.addTab(om.getLang(langs.TAB_OPTIONS_RESOURCES), pResources);

		tpOptions.setSelectedIndex(0);
		
		pOptionsMenu.add(tpOptions);
		
		JButton btnApply = new JButton(om.getLang(langs.BTN_APPLY));
		btnApply.setSize(width / 7, height / 20);
		btnApply.setLocation(tpOptions.getX(), (tpOptions.getY() + tpOptions.getHeight()) + (height - tpOptions.getHeight() - tpOptions.getY() - btnApply.getHeight()) / 2);
		btnApply.setFont(new Font("Monospace", Font.BOLD, (int) (btnApply.getHeight() * 0.7)));
		btnApply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				om.setOption(options.LANGUAGE, cbLangs.getSelectedItem());
			}
		});
		
		pOptionsMenu.add(btnApply);
		
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

	private Component getStartMenu()
	{
		JPanel pStartMenu = new JPanel();
		pStartMenu.setSize(frame.getSize());
		pStartMenu.setLocation(0, 0);
		pStartMenu.setLayout(null);
		
		JButton btnOneVsOne = new JButton();
		btnOneVsOne.setSize((int) (width / 3 * 0.9), (int) (height * 0.85));
		btnOneVsOne.setLocation((width - (3 * btnOneVsOne.getWidth())) / 4, (int) (height * 0.05));
		
		ImageIcon iconOneVsOne = new ImageIcon("images/1vs1.png");
		Image imgOneVsOne = iconOneVsOne.getImage().getScaledInstance(btnOneVsOne.getWidth(), btnOneVsOne.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iconOneVsOneScale = new ImageIcon(imgOneVsOne);
		
		btnOneVsOne.setIcon(iconOneVsOneScale);
		btnOneVsOne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				clContent.show(frame.getContentPane(), ContentCards.GAME_SINGLE.name());
				Init.startSingleGame();
			}
		});
		pStartMenu.add(btnOneVsOne);
		
		JButton btnOneVsCom = new JButton("1vsCom");
		btnOneVsCom.setSize((int) (width / 3 * 0.9), (int) (height * 0.85));
		btnOneVsCom.setLocation(((width - (3 * btnOneVsCom.getWidth())) / 4) * 2 + btnOneVsOne.getWidth(), (int) (height * 0.05));
		
		ImageIcon iconOneVsCom = new ImageIcon("images/1vsCom.png");
		Image imgOneVsCom = iconOneVsCom.getImage().getScaledInstance(btnOneVsCom.getWidth(), btnOneVsCom.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iconOneVsComScale = new ImageIcon(imgOneVsCom);
		
		btnOneVsCom.setIcon(iconOneVsComScale);
		btnOneVsCom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				clContent.show(frame.getContentPane(), ContentCards.GAME_SINGLE.name());
			}
		});
		pStartMenu.add(btnOneVsCom);
		
		JButton btnOneVsOneOnline = new JButton("1vs1Online");
		btnOneVsOneOnline.setSize((int) (width / 3 * 0.9), (int) (height * 0.85));
		btnOneVsOneOnline.setLocation(((width - (3 * btnOneVsOneOnline.getWidth())) / 4) * 3 + btnOneVsOne.getWidth() + btnOneVsCom.getWidth(), (int) (height * 0.05));
		
		ImageIcon iconOneVsOneOnline = new ImageIcon("images/1vs1Online.png");
		Image imgOneVsOneOnline = iconOneVsOneOnline.getImage().getScaledInstance(btnOneVsOneOnline.getWidth(), btnOneVsOneOnline.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon iconOneVsOneOnlineScale = new ImageIcon(imgOneVsOneOnline);
		
		btnOneVsOneOnline.setIcon(iconOneVsOneOnlineScale);
		btnOneVsOneOnline.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				clContent.show(frame.getContentPane(), ContentCards.GAME_MULTI.name());
			}
		});
		pStartMenu.add(btnOneVsOneOnline);
		
		JButton btnBack = new JButton(om.getLang(langs.BTN_BACK));
		btnBack.setSize(width / 7, height / 20);
		btnBack.setLocation(width - btnBack.getWidth() - btnOneVsOne.getX(), height - btnBack.getHeight() - (height - btnBack.getHeight() - btnOneVsOneOnline.getY() - btnOneVsOneOnline.getHeight()) / 2);
		btnBack.setFont(new Font("Monospace", Font.BOLD, (int) (btnBack.getHeight() * 0.7)));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				clContent.show(frame.getContentPane(), ContentCards.MAINMENU.name());
			}
		});
		
		pStartMenu.add(btnBack);
		
		return pStartMenu;
	}

	private Component getGameSingle()
	{
		JPanel pGameSingle = new JPanel();
		pGameSingle.setSize(frame.getSize());
		pGameSingle.setLocation(0, 0);
		pGameSingle.setLayout(null);

		int iFieldMaxSize = (int) (getFieldMaxSize(pGameSingle) * 0.8);
		
		lSinglePlayerChange = new JLabel(om.getLang(langs.PLAYER_CHANGE).replace("<player>", "?"), JLabel.CENTER);
		lSinglePlayerChange.setSize(iFieldMaxSize, (int) (iFieldMaxSize * 0.1));
		lSinglePlayerChange.setLocation((pGameSingle.getWidth() - lSinglePlayerChange.getWidth()) / 2, (pGameSingle.getHeight() - lSinglePlayerChange.getHeight()) / 2);
		lSinglePlayerChange.setFont(new Font("Monospace", Font.BOLD, (int) (lSinglePlayerChange.getHeight() * 0.8)));
		lSinglePlayerChange.setVisible(false);
		
		pGameSingle.add(lSinglePlayerChange);

		lSinglePlayerWin = new JLabel(om.getLang(langs.GAME_PLAYER_WON).replace("<player>", "?"), JLabel.CENTER);
		lSinglePlayerWin.setSize(iFieldMaxSize, (int) (iFieldMaxSize * 0.1));
		lSinglePlayerWin.setLocation((pGameSingle.getWidth() - lSinglePlayerWin.getWidth()) / 2, (pGameSingle.getHeight() - lSinglePlayerWin.getHeight()) / 2);
		lSinglePlayerWin.setFont(new Font("Monospace", Font.BOLD, (int) (lSinglePlayerWin.getHeight() * 0.8)));
		lSinglePlayerWin.setVisible(false);
		
		pGameSingle.add(lSinglePlayerWin);
		
		lSingleTie = new JLabel(om.getLang(langs.GAME_TIE), JLabel.CENTER);
		lSingleTie.setSize(iFieldMaxSize, (int) (iFieldMaxSize * 0.1));
		lSingleTie.setLocation((pGameSingle.getWidth() - lSingleTie.getWidth()) / 2, (pGameSingle.getHeight() - lSingleTie.getHeight()) / 2);
		lSingleTie.setFont(new Font("Monospace", Font.BOLD, (int) (lSingleTie.getHeight() * 0.8)));
		lSingleTie.setVisible(false);
		
		pGameSingle.add(lSingleTie);
		
		pField = new FieldPanel(om, iFieldMaxSize, pGameSingle);
		
		pGameSingle.add(pField);
		
		JButton btnBack = new JButton(om.getLang(langs.BTN_BACK));
		btnBack.setSize(width / 7, height / 20);
		btnBack.setLocation(width - btnBack.getWidth() - (width / 40), height - btnBack.getHeight() - (height / 40));
		btnBack.setFont(new Font("Monospace", Font.BOLD, (int) (btnBack.getHeight() * 0.7)));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				clContent.show(frame.getContentPane(), ContentCards.MAINMENU.name());
				
				for (int i = 1; i <= 9; i++)
				{
					Init.fieldStates.replace(String.valueOf(i), "E");
				}

				pField.changeField(0, "");
			}
		});
		
		pGameSingle.add(btnBack);
		
		return pGameSingle;
	}

	private Component getGameMulti()
	{
		JPanel pGameMulti = new JPanel();
		pGameMulti.setSize(frame.getSize());
		pGameMulti.setLocation(0, 0);
		pGameMulti.setLayout(null);
		
		return pGameMulti;
	}
	
	public void changePlayerSingle(String playername)
	{
		lSinglePlayerChange.setText(om.getLang(langs.PLAYER_CHANGE).replace("<player>", playername));
		lSinglePlayerChange.setVisible(true);
		try
		{
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		lSinglePlayerChange.setVisible(false);
	}
	
	public void displayWin(String winner)
	{
		if (winner.equals("T"))
		{
			lSingleTie.setVisible(true);
			try
			{
				Thread.sleep(3000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			lSingleTie.setVisible(false);
		}
		else if (winner.equals("X"))
		{
			lSinglePlayerWin.setText(om.getLang(langs.GAME_PLAYER_WON).replace("<player>", "1"));
			lSinglePlayerWin.setVisible(true);
			try
			{
				Thread.sleep(3000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			lSinglePlayerWin.setVisible(false);
		}
		else if (winner.equals("O"))
		{
			lSinglePlayerWin.setText(om.getLang(langs.GAME_PLAYER_WON).replace("<player>", "2"));
			lSinglePlayerWin.setVisible(true);
			try
			{
				Thread.sleep(3000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			lSinglePlayerWin.setVisible(false);
		}
		
		clContent.show(frame.getContentPane(), ContentCards.START.name());
	}

	private int getFieldMaxSize(JPanel pGame)
	{
		if (pGame.getWidth() < pGame.getHeight()) return pGame.getWidth();
		else return pGame.getHeight();
	}

	private void addOption(JPanel pParent, int iParent, String name, Component value)
	{
		JPanel pOption = new JPanel();
		pOption.setLayout(null);
		pOption.setSize(pParent.getWidth(), pParent.getHeight() / 30);
		pOption.setLocation(10, 10 + iParent * pOption.getHeight());
		
		JLabel lName = new JLabel(name);
		lName.setSize((int) (pOption.getWidth() * 0.8), pOption.getHeight());
		lName.setLocation(0, 0);
		lName.setFont(new Font("Monospace", Font.PLAIN, (int) (lName.getHeight() * 0.7)));
		
		pOption.add(lName);
		
		value.setSize((int) (pOption.getWidth() * 0.2), pOption.getHeight());
		value.setLocation(pOption.getWidth() - value.getWidth() - (pOption.getWidth() / 50), 0);
		
		pOption.add(value);
		
		pParent.add(pOption);
		iParent++;
	}

	public void visible()
	{
		if (frame.isVisible()) frame.setVisible(false);
		else frame.setVisible(true);
	}

	public void changeField(int id, String player)
	{
		pField.changeField(id, player);
	}
}
