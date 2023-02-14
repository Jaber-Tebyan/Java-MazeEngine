package com.tebyan.jaber_maze_engine.maze_generation;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import com.tebyan.jaber_maze_engine.cells.Cell;
import com.tebyan.jaber_maze_engine.cells.WalledCell;



public class DepthFirstSearchMazeGeneratorWalled extends MazeGenerator{
	
	public int row,column;
	public long delay;
	public DelayCallback callback;
	
	public static Cell[][] generateIterativeDepthFirstWalledMaze(int row,int column,long delay,DelayCallback callback){
		WalledCell[][] map=createFullyWalledMaze(row, column);
		Random random=new Random();
		Stack<WalledCell> stack=new Stack<>();  
		WalledCell currentCell=map[random.nextInt(row)][random.nextInt(column)];
		currentCell.visited=true;
		stack.push(currentCell);
		long timer=System.currentTimeMillis();
		while(!stack.isEmpty()) {
			
			if(System.currentTimeMillis()-timer>=delay) {
				timer=System.currentTimeMillis();
				currentCell=stack.pop();
				Cell[] temp= currentCell.getUnvisitedNeighbors(map);
				WalledCell[] neighbors= Arrays.copyOf(temp, temp.length, WalledCell[].class);
				if(neighbors.length>0) {
					stack.push(currentCell);
					WalledCell c=neighbors[random.nextInt(neighbors.length)];
					currentCell.removeWallAgainst(c);
					c.visited=true;
					currentCell=stack.push(c);
				}
				if(callback!=null)callback.callback(map,currentCell);
			}
			
			
		}
		return map;
	}
	public Cell[][] generateIterativeDepthFirstWalledMaze(){
		return generateIterativeDepthFirstWalledMaze(row, column, delay, callback);
	}

	@Override
	public Cell[][] generateMaze() {
		return generateIterativeDepthFirstWalledMaze();
		
	}
	public interface DelayCallback{
		void callback(Cell[][] map,Cell currentCell);
	}
}
