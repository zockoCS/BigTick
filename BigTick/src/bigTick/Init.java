package bigTick;

import bigTick.gui.GUI;
import bigTick.options.OptionManager;

public class Init
{
	static OptionManager om;
	static GUI gui;
	
	public static void main(String[] args)
	{
		loadOptions();
		openGui();
	}

	private static void openGui()
	{
		gui = new GUI(om);
		gui.visible();
	}

	private static void loadOptions()
	{
		om = new OptionManager();
	}
}
