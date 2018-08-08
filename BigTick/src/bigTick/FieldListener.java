package bigTick;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FieldListener implements MouseListener
{
	private int id;

	public FieldListener(int i)
	{
		this.id = i;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if (!Init.isClicked)
		{
			Init.gui.changeField(id, Init.player);
			Init.fieldStates.put(String.valueOf(id), Init.player);
			Init.isClicked = true;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e){}

	@Override
	public void mouseExited(MouseEvent e){}

	@Override
	public void mousePressed(MouseEvent e){}

	@Override
	public void mouseReleased(MouseEvent e){}
}
