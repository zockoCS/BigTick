package bigTick;

import bigTick.options.OptionManager;

public class Init
{
	static OptionManager om;
	
	public static void main(String[] args)
	{
		loadOptions();
	}

	private static void loadOptions()
	{
		om = new OptionManager();
	}
}
