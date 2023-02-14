package com.tebyan.jaber_maze_engine;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;



public class RenderFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5842020997607369009L;

	public RenderFrame(int width,int height,boolean fullScreen) {
		
		setTitle("Jaber Maze");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		Dimension screenDimension=Toolkit.getDefaultToolkit().getScreenSize();
		
		
		if(fullScreen) {
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			setUndecorated(true);
		}
		else{setLocation(screenDimension.width/2-width/2,screenDimension.height/2-height/2);}
		pack();
		setSize(width,height);
	}
}
