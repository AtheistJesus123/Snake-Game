package application;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

public class Snake extends Rectangle {
	List<Rectangle> body = new ArrayList<>();
	public Snake()
	{
		body.add(new Rectangle(190, 190, 20, 20));
		body.add(new Rectangle(190, 190, 20, 20));
		body.add(new Rectangle(190, 190, 20, 20));
	}
	public void makeLonger()
	{
		body.add(new Rectangle(190, 190, 20, 20));
	}
	public boolean hasCollided()
	{
		for (int i = 1; i < body.size(); i++)
		{
			if (body.get(0).getX() == body.get(i).getX() ||
					body.get(0).getY() == body.get(i).getY())
			{
				return true;
			}
		}
		return false;
	}
}