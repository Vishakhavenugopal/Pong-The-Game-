package Pong;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Game_Frame extends JFrame
{	
		
	
		Game_Frame()
		{
			Game_Panel panel = new Game_Panel();
			this.add(panel);
			this.setTitle("Vishakha's Pong Game");
			this.setResizable(false);
			this.setBackground(Color.black);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.pack();
			this.setVisible(true);
			this.setLocationRelativeTo(null);
	}

}
