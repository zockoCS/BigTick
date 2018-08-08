package bigTick.gui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bigTick.FieldListener;
import bigTick.options.OptionManager;
import bigTick.options.options;

@SuppressWarnings("serial")
public class FieldPanel extends JPanel
{
	private int iFieldMaxSize;
	private final int lineWidth = 2;
	private ImageIcon iconFieldEmptyScale;
	private ImageIcon iconFieldXScale;
	private ImageIcon iconFieldOScale;
	private JLabel field1;
	private JLabel field2;
	private JLabel field3;
	private JLabel field4;
	private JLabel field5;
	private JLabel field6;
	private JLabel field7;
	private JLabel field8;
	private JLabel field9;
	private Color lineColor;

	public FieldPanel(OptionManager om, int iFieldMaxSize, JPanel pGame)
	{
		this.iFieldMaxSize = iFieldMaxSize;
		lineColor = new Color(Integer.parseInt((String) om.getOption(options.LINECOLOR)));
		setLayout(null);
		setSize(iFieldMaxSize, iFieldMaxSize);
		setLocation((pGame.getWidth() - getWidth()) / 2, (pGame.getHeight() - getHeight()) / 2);
		drawLines();
		addFields();
	}
	
	private void addFields()
	{
		ImageIcon iconFieldEmpty = new ImageIcon("images/Empty.png");
		Image imgFieldEmpty = iconFieldEmpty.getImage().getScaledInstance(iFieldMaxSize / 3, iFieldMaxSize / 3, Image.SCALE_SMOOTH);
		iconFieldEmptyScale = new ImageIcon(imgFieldEmpty);
		
		ImageIcon iconFieldX = new ImageIcon("images/X.png");
		Image imgFieldX = iconFieldX.getImage().getScaledInstance(iFieldMaxSize / 3, iFieldMaxSize / 3, Image.SCALE_SMOOTH);
		iconFieldXScale = new ImageIcon(imgFieldX);
		
		ImageIcon iconFieldO = new ImageIcon("images/O.png");
		Image imgFieldO = iconFieldO.getImage().getScaledInstance(iFieldMaxSize / 3, iFieldMaxSize / 3, Image.SCALE_SMOOTH);
		iconFieldOScale = new ImageIcon(imgFieldO);
		
		field1 = new JLabel();
		field1.setSize(iFieldMaxSize / 3, iFieldMaxSize / 3);
		field1.setLocation(0, iFieldMaxSize / 3 * 2);
		field1.setIcon(iconFieldEmptyScale);
		field1.addMouseListener(new FieldListener(1));
		add(field1);
		
		field2 = new JLabel();
		field2.setSize(iFieldMaxSize / 3, iFieldMaxSize / 3);
		field2.setLocation(iFieldMaxSize / 3, iFieldMaxSize / 3 * 2);
		field2.setIcon(iconFieldEmptyScale);
		field2.addMouseListener(new FieldListener(2));
		add(field2);

		field3 = new JLabel();
		field3.setSize(iFieldMaxSize / 3, iFieldMaxSize / 3);
		field3.setLocation(iFieldMaxSize / 3 * 2, iFieldMaxSize / 3 * 2);
		field3.setIcon(iconFieldEmptyScale);
		field3.addMouseListener(new FieldListener(3));
		add(field3);

		field4 = new JLabel();
		field4.setSize(iFieldMaxSize / 3, iFieldMaxSize / 3);
		field4.setLocation(0, iFieldMaxSize / 3);
		field4.setIcon(iconFieldEmptyScale);
		field4.addMouseListener(new FieldListener(4));
		add(field4);

		field5 = new JLabel();
		field5.setSize(iFieldMaxSize / 3, iFieldMaxSize / 3);
		field5.setLocation(iFieldMaxSize / 3, iFieldMaxSize / 3);
		field5.setIcon(iconFieldEmptyScale);
		field5.addMouseListener(new FieldListener(5));
		add(field5);

		field6 = new JLabel();
		field6.setSize(iFieldMaxSize / 3, iFieldMaxSize / 3);
		field6.setLocation(iFieldMaxSize / 3 * 2, iFieldMaxSize / 3);
		field6.setIcon(iconFieldEmptyScale);
		field6.addMouseListener(new FieldListener(6));
		add(field6);

		field7 = new JLabel();
		field7.setSize(iFieldMaxSize / 3, iFieldMaxSize / 3);
		field7.setLocation(0, 0);
		field7.setIcon(iconFieldEmptyScale);
		field7.addMouseListener(new FieldListener(7));
		add(field7);
		
		field8 = new JLabel();
		field8.setSize(iFieldMaxSize / 3, iFieldMaxSize / 3);
		field8.setLocation(iFieldMaxSize / 3, 0);
		field8.setIcon(iconFieldEmptyScale);
		field8.addMouseListener(new FieldListener(8));
		add(field8);

		field9 = new JLabel();
		field9.setSize(iFieldMaxSize / 3, iFieldMaxSize / 3);
		field9.setLocation(iFieldMaxSize / 3 * 2, 0);
		field9.setIcon(iconFieldEmptyScale);
		field9.addMouseListener(new FieldListener(9));
		add(field9);
	}

	private void drawLines()
	{
		JLabel ho = new JLabel("");
		ho.setSize(iFieldMaxSize, lineWidth);
		ho.setLocation(0, iFieldMaxSize / 3);
		ho.setBackground(lineColor);
		ho.setOpaque(true);
		ho.setVisible(true);
		add(ho);
		
		JLabel hu = new JLabel("");
		hu.setSize(iFieldMaxSize, lineWidth);
		hu.setLocation(0, iFieldMaxSize / 3 * 2);
		hu.setBackground(lineColor);
		hu.setOpaque(true);
		add(hu);
		
		JLabel vl = new JLabel("");
		vl.setSize(lineWidth, iFieldMaxSize);
		vl.setLocation(iFieldMaxSize / 3, 0);
		vl.setBackground(lineColor);
		vl.setOpaque(true);
		add(vl);
		
		JLabel vr = new JLabel("");
		vr.setSize(lineWidth, iFieldMaxSize);
		vr.setLocation(iFieldMaxSize / 3 * 2, 0);
		vr.setBackground(lineColor);
		vr.setOpaque(true);
		add(vr);
	}
	
	public void changeField(int id, String player)
	{
		JLabel field = new JLabel();

		if (id == 0)
		{
			field1.setIcon(iconFieldEmptyScale);
			field2.setIcon(iconFieldEmptyScale);
			field3.setIcon(iconFieldEmptyScale);
			field4.setIcon(iconFieldEmptyScale);
			field5.setIcon(iconFieldEmptyScale);
			field6.setIcon(iconFieldEmptyScale);
			field7.setIcon(iconFieldEmptyScale);
			field8.setIcon(iconFieldEmptyScale);
			field9.setIcon(iconFieldEmptyScale);
		}
		else if (id == 1) field = field1;
		else if (id == 2) field = field2;
		else if (id == 3) field = field3;
		else if (id == 4) field = field4;
		else if (id == 5) field = field5;
		else if (id == 6) field = field6;
		else if (id == 7) field = field7;
		else if (id == 8) field = field8;
		else if (id == 9) field = field9;

		if (player.equals("X")) field.setIcon(iconFieldXScale);
		else if (player.equals("O")) field.setIcon(iconFieldOScale);
		else if (player.equals("E")) field.setIcon(iconFieldEmptyScale);
	}
}
