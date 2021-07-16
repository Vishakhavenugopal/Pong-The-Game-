package Pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class Game_Panel extends JPanel implements Runnable
{

	static final int GAME_WIDTH = 1000;
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5));
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	Thread thread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Game_Ball ball;
	Game_score score;
	
	Game_Panel()
	{
		newPaddles();
		newBall();
		score = new Game_score(GAME_WIDTH,GAME_HEIGHT);
		this.setFocusable(true);
		this.addKeyListener(new kl());
		this.setPreferredSize(SCREEN_SIZE);
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void newBall() 
	{
		random = new Random();
		ball = new Game_Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
	}
	public void newPaddles() 
	{
		paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
	}
	public void paint(Graphics g) 
	{
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);
	}
	public void draw(Graphics g)
	{
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
		Toolkit.getDefaultToolkit().sync(); // reference stackoverflow

	}
	public void move()
	{
		paddle1.move();
		paddle2.move();
		ball.move();
	}
	public void checkCollision() 
	{
		
		if(ball.y <=0) 
		{
			ball.setDirection(-ball.yVelocity);
		}
		
		if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) 
		{
			ball.setDirection(-ball.yVelocity);
		}
		
		if(ball.intersects(paddle1)) 
		{
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; 
			if(ball.yVelocity>0)
				ball.yVelocity++; 
			else
				ball.yVelocity--;
			ball.setDirection(ball.xVelocity);
			ball.setDirection(ball.yVelocity);
		}
		
		if(ball.intersects(paddle2)) 
		{
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++;
			if(ball.yVelocity>0)
				ball.yVelocity++;
			else
				ball.yVelocity--;
			ball.setDirection(-ball.xVelocity);
			ball.setDirection(ball.yVelocity);
		}
		
		if(paddle1.y<=0)
			paddle1.y=0;
		
		if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
		
		if(paddle2.y<=0)
			paddle2.y=0;
		
		if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))
			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
		
		if(ball.x <=0) 
		{
			score.player2++;
			newPaddles();
			newBall();
			System.out.println("Player 2: "+score.player2);
		}
		
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) 
		{
			score.player1++;
			newPaddles();
			newBall();
			System.out.println("Player 1: "+score.player1);
		}
	}
	public void run() 
	{
		try
		{
		while(true)
		{
			Thread.sleep(13);
			move();
			checkCollision();
			repaint();
		}
		}
		catch(InterruptedException e)
		{
			System.out.println(e.getMessage());
		}
	}
	public class kl extends KeyAdapter
	{
		public void keyPressed(KeyEvent e) 
		{
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) 
		{
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}
