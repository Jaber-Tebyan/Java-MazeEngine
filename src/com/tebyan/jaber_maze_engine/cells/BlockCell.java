package com.tebyan.jaber_maze_engine.cells;

import com.tebyan.jaber_maze_engine.cells.Cell.CellType;

public class BlockCell extends Cell {

	



	public BlockCell(int x,int y,CellType cellType) {
		super(x,y);
		this.cellType=cellType;
		// TODO Auto-generated constructor stub
	}

	public CellType cellType;



	
}
