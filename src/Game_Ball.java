package Pong;

import java.awt.*;
import java.util.*;

class Game_Ball extends Rectangle
{

	Random random;
	double xVelocity;
	int yVelocity;
	int initialSpeed = 2;
	
	Game_Ball(int x, int y, int width, int height)
	{
		super(x,y,width,height);
		random = new Random();
		
		double randomXDirection = random.nextInt(2);
		if(randomXDirection == 0)
			randomXDirection--;
		setDirection(randomXDirection*initialSpeed);
		
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0)
			randomYDirection--;
		setDirection(randomYDirection*initialSpeed);
		
	}
	
	public void setDirection(double randomXDirection) 
	{
		xVelocity = randomXDirection;
	}
	public void setDirection(int randomYDirection)
	{
		yVelocity = randomYDirection;
	}
	public void move()
	{
		x += (int)xVelocity;
		y += yVelocity;
	}
	public void draw(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}
}
