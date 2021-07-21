package application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Apple extends Circle {
	private static int count = 0;
	private int chanceGolden = 0;
	protected boolean isGolden = false;
	public Apple()
	{
		count++;
		if (count > 10)
		{
			chanceGolden = (int)(Math.random() * 101);
		}
		if (chanceGolden > 90)
		{
			this.isGolden = true;
			this.setFill(Color.GOLD);
		}
		else
		{
			this.setFill(Color.RED);
		}
		int xPos = 20 + (int)(Math.random() * 20) * 20;
		int yPos = 60 + (int)(Math.random() * 20) * 20;
		this.setCenterX(xPos);
		this.setCenterY(yPos);
		this.setRadius(10);
	}
	public int pointValue()
	{
		if (this.isGolden)
		{
			return 3;
		}
		else return 1;
	}
	public static int getCount()
	{
		return count;
	}
	public static void setCount(int newCount)
	{
		count = newCount;
	}
}