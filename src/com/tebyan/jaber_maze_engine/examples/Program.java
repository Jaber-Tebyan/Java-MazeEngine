package com.tebyan.jaber_maze_engine.examples;

import com.tebyan.jaber_maze_engine.RenderFrame;
import com.tebyan.jaber_maze_engine.RenderMaze;
import com.tebyan.jaber_maze_engine.cells.Cell;
import com.tebyan.jaber_maze_engine.maze_generation.DepthFirstSearchMazeGeneratorWalled;
import com.tebyan.jaber_maze_engine.maze_generation.DepthFirstSearchMazeGeneratorWalled.DelayCallback;

public class Program {
	public static void main(String[] args) {
		RenderFrame frame=new RenderFrame(800, 600, true);
		RenderMaze maze=new RenderMaze(800,800);
		frame.add(maze);
		maze.start();
		
		frame.setVisible(true);
		
		Cell[][] map=DepthFirstSearchMazeGeneratorWalled.generateIterativeDepthFirstWalledMaze(10, 10, 0, new DelayCallback() {
			
			@Override
			public void callback(Cell[][] map, Cell currentCell) {
				maze.setMap(map);
				
			}
		});
		
		
	}
}
