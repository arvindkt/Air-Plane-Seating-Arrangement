package com.test.Airplane.model;

/**
 * The Class Seat.
 */
public class Seats {

	/** The row. */
	int row;

	/** The column. */
	int column;

	/** The seat values. */
	int seatValues[][];

	/**
	 * Gets the row.
	 * 
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Gets the column.
	 * 
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Gets the seat values.
	 * 
	 * @return the seat values
	 */
	public int[][] getSeatValues() {

		return seatValues;
	}

	/**
	 * Instantiates a new seats.
	 * 
	 * @param row
	 *            the row
	 * @param column
	 *            the column
	 */
	public Seats(int row, int column) {
		this.row = row;
		this.column = column;
		seatValues = new int[row][column];
	}

}
