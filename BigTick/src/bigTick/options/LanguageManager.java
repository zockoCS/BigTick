package bigTick.options;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LanguageManager
{
	private Properties prop;
	
	public LanguageManager(String lang)
	{
		prop = new Properties();
		
		loadLang(lang);
	}

	protected void loadLang(String lang)
	{
		try
		{
			prop.load(new DataInputStream(new FileInputStream(new File("languages", lang + ".lang"))));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getLang(langs lang)
	{
		return prop.getProperty(lang.name());
	}
}
