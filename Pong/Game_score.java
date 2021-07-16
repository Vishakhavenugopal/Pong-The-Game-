package Pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Game_score 
{

	public static int GAME_WIDTH;
	public static int GAME_HEIGHT;
	public int player1;
	public int player2;
	
	public Game_score(int GAME_WIDTH, int GAME_HEIGHT)
	{
		Game_score.GAME_WIDTH = GAME_WIDTH;
		Game_score.GAME_HEIGHT = GAME_HEIGHT;
	}
	public void draw(Graphics g) 
	{
		g.setColor(Color.cyan);      // colour reffered from google
		g.setFont(new Font("ENGRAVERS MT",Font.PLAIN,60));       // font reffered from google
		
		g.drawLine(GAME_WIDTH/2, 0, GAME_WIDTH/2, GAME_HEIGHT);
		
		g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GAME_WIDTH/2)-100, 50);
		g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GAME_WIDTH/2)+30, 50);
	}
}
