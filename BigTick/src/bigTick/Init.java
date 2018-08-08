package bigTick;

import java.util.HashMap;

import bigTick.gui.GUI;
import bigTick.options.OptionManager;

public class Init
{
	private static OptionManager om;
	static GUI gui;
	static Thread game;
	static boolean isClicked = false;
	public static String player = "O";
	public static HashMap<String, String> fieldStates = new HashMap<>();
	
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
	
	public static void startSingleGame()
	{
		game = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 1; i <= 9; i++)
				{
					fieldStates.put(String.valueOf(i), "E");
				}
				
				String winner = "N";
				
				while (winner.equals("N"))
				{
					changePlayer();
					
					isClicked = false;
					
					waitingForClick();
					
					winner = checkWin();
				}
				
				gui.displayWin(winner);
			}
			
			private void waitingForClick()
			{
				while (!isClicked)
				{
					try
					{
						Thread.sleep(10);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}

			private String checkWin()
			{
				if (
					   fieldStates.get("1").equals("X") && fieldStates.get("2").equals("X") && fieldStates.get("3").equals("X")
					|| fieldStates.get("4").equals("X") && fieldStates.get("5").equals("X") && fieldStates.get("6").equals("X")
					|| fieldStates.get("7").equals("X") && fieldStates.get("8").equals("X") && fieldStates.get("9").equals("X")
					|| fieldStates.get("1").equals("X") && fieldStates.get("4").equals("X") && fieldStates.get("7").equals("X")
					|| fieldStates.get("2").equals("X") && fieldStates.get("5").equals("X") && fieldStates.get("8").equals("X")
					|| fieldStates.get("3").equals("X") && fieldStates.get("6").equals("X") && fieldStates.get("9").equals("X")
					|| fieldStates.get("1").equals("X") && fieldStates.get("5").equals("X") && fieldStates.get("9").equals("X")
					|| fieldStates.get("3").equals("X") && fieldStates.get("5").equals("X") && fieldStates.get("7").equals("X")
					)
				{
					return "X";
				}
				else if (
					   fieldStates.get("1").equals("O") && fieldStates.get("2").equals("O") && fieldStates.get("3").equals("O")
					|| fieldStates.get("4").equals("O") && fieldStates.get("5").equals("O") && fieldStates.get("6").equals("O")
					|| fieldStates.get("7").equals("O") && fieldStates.get("8").equals("O") && fieldStates.get("9").equals("O")
					|| fieldStates.get("1").equals("O") && fieldStates.get("4").equals("O") && fieldStates.get("7").equals("O")
					|| fieldStates.get("2").equals("O") && fieldStates.get("5").equals("O") && fieldStates.get("8").equals("O")
					|| fieldStates.get("3").equals("O") && fieldStates.get("6").equals("O") && fieldStates.get("9").equals("O")
					|| fieldStates.get("1").equals("O") && fieldStates.get("5").equals("O") && fieldStates.get("9").equals("O")
					|| fieldStates.get("3").equals("O") && fieldStates.get("5").equals("O") && fieldStates.get("7").equals("O")
					)
				{
					return "O";
				}
				else if (!fieldStates.values().contains("E"))
				{
					return "T";
				}
				else
				{
					return "N";
				}
			}

			private void changePlayer()
			{
				if (player.equals("X"))
				{
					player = "O";
					gui.changePlayerSingle("2");
				}
				else
				{
					player = "X";
					gui.changePlayerSingle("1");
				}
			}
		});
		game.start();
	}
}
