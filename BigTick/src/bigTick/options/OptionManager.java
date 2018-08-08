package bigTick.options;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class OptionManager
{
	private Properties prop;
	private final File optionFile = new File("options.cfg");
	private LanguageManager lm;
	
	public OptionManager()
	{
		prop = new Properties();
		
		try
		{
			prop.load(new DataInputStream(new FileInputStream(optionFile)));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		lm = new LanguageManager((String) this.getOption(options.LANGUAGE));
	}

	public Object getOption(options option)
	{
		return prop.get(option.name());
	}
	
	public void setOption(options option, Object value)
	{
		prop.put(option.name(), value);
		try
		{
			prop.store(new DataOutputStream(new FileOutputStream(optionFile)), null);
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
		return lm.getLang(lang);
	}
}
