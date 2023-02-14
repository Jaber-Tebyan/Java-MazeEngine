package com.tebyan.jaber_maze_engine.maze_generation;

import java.util.Random;

import com.tebyan.jaber_maze_engine.cells.Cell;
import com.tebyan.jaber_maze_engine.cells.WalledCell;

public abstract class MazeGenerator {


	public static WalledCell[][] createFullyWalledMaze(int row, int column){
		WalledCell[][] map=new WalledCell[row][column];
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j]=new WalledCell(j,i);
			}
		}
		return map;
	}
	public static WalledCell[][] createWalledCells(int row,int column){
		WalledCell[][] map=new WalledCell[row][column];
		Random random=new Random();
		
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				int r=random.nextInt(2);
				WalledCell cell=new WalledCell(i,j);
				for (int k = 0; k < cell.walls.length; k++) {
					if(r==0) {
						cell.walls[k]=false;
					}else {
						cell.walls[k]=true;
					}
				}
				map[i][j]=cell;
				
			}
		}
		return map;
	}
	public abstract Cell[][] generateMaze();
}
