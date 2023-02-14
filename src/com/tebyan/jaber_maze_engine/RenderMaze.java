package com.tebyan.jaber_maze_engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import com.tebyan.jaber_maze_engine.cells.BlockCell;
import com.tebyan.jaber_maze_engine.cells.Cell;
import com.tebyan.jaber_maze_engine.cells.Cell.CellType;
import com.tebyan.jaber_maze_engine.cells.WalledCell;




public class RenderMaze extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Dimension size;
	Image mazeImage;
	boolean running;
	Thread thread;
	Cell[][] map;
	
	public RenderMaze(int width,int height) {
		size=new Dimension(width,height);
	}
	public void start() {
		thread=new Thread(this);
		this.running=true;
		thread.start();
	}
	public void stop() {
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread=null;
		running=false;
	}
	@Override
	public void run() {
		double startTime=System.nanoTime();
		double prevTime=System.nanoTime();
		int FPS=60;
		double optTime=1e9/FPS;
		double deltaTime=0;
		while(running) {
			deltaTime+=System.nanoTime()-prevTime;
			prevTime=System.nanoTime();
			while(deltaTime>=optTime) {
				tick();
				deltaTime-=optTime;
			}
			if(running) {
				render();
			}
			
		}
	}
	public void setMap(Cell[][] map) {
		this.map=map;
		Cell.cellSize=(size.height/map.length);
		if(map instanceof BlockCell[][]) {
			DrawBlockMazeOnImage();
		}else if(map instanceof WalledCell[][]){
			DrawWalledCellOnImage();
		}
		
	}
	synchronized void tick() {
	}
	synchronized void render() {
		
		BufferStrategy bf=getBufferStrategy();
		if(bf==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics2D g=(Graphics2D) bf.getDrawGraphics();
		int screenWidth=getParent().getWidth(),screenHeight=getParent().getHeight();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.fillRect(0, 0, screenWidth, screenHeight);
		g.setColor(Color.black);
		g.fillRect(screenWidth/2-size.width/2, screenHeight/2-size.height/2, size.width,size.height);

		
		
		
		g.setColor(Color.getHSBColor(.1f,.1f,.1f));
		

		g.drawImage(mazeImage, screenWidth/2-size.width/2, screenHeight/2-size.height/2, this);
		bf.show();
	}

	private void DrawBlockMazeOnImage() {

		BufferedImage image=new BufferedImage(size.width,size.height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g=(Graphics2D) image.getGraphics();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				
				BlockCell cell=(BlockCell) map[i][j];
				switch (cell.cellType) {
					case UNWALKABLE:
						cell.fillCell(g, Color.DARK_GRAY);
						break;
					case WALKABLE:
						cell.fillCell(g, Color.white);
						break;
					case ENTRANCE:
						cell.fillCell(g, Color.GREEN);
						break;
					case TARGET:
						cell.fillCell(g, Color.red);
						break;
					default:
						break;
				}
				cell.DrawOutline(g, Color.getHSBColor(.05f, .05f, .05f));
			}
			mazeImage=image;
			
		}
		
		
	}
	private void DrawWalledCellOnImage() {
		
		BufferedImage image=new BufferedImage(size.width*20,size.height*20,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g=(Graphics2D) image.getGraphics();
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				WalledCell cell=(WalledCell) map[i][j];
				cell.fillCell(g, Color.white);
				cell.DrawWalls(g, Color.red);
			}
			mazeImage=image;
			
		}

	}

}
