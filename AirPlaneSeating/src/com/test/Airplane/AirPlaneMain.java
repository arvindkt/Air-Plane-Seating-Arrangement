package com.test.Airplane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.test.Airplane.model.Seats;
import com.test.Airplane.util.AirPlaneUtil;

/**
 * The Class AirPlaneMain.
 */
public class AirPlaneMain {

	/** The aisle seat. */
	static int AISLE_SEAT = -1;

	/** The window seat. */
	static int WINDOW_SEAT = -2;

	/** The middle seat. */
	static int MIDDLE_SEAT = -3;

	/** The max row length. */
	static int maxRowLength = Integer.MIN_VALUE;

	/** The passengers. */
	int passengers;

	/** The count. */
	static int count = 0;

	/** The seat list. */
	List<Seats> seatList = new LinkedList<>();

	/** The file writer. */
	static FileWriter fileWriter;

	/** The buffered writer. */
	static BufferedWriter bufferedWriter = null;

	/** The total no of seats. */
	int totalNoOfSeats;

	/**
	 * The main method to run the program.
	 * 
	 * @param args
	 *            the arguments
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		int[] arr[] = new [] int[5];
		System.out.println
		fileWriter = new FileWriter("output/output.txt");
		bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write("AirPlane Seating Arrangement\n");
		bufferedWriter.write("Input :\nSeats/Position :\t");
		AirPlaneMain airPlane = new AirPlaneMain();
		Scanner input = new Scanner(System.in);
		System.out.print("Enter AirPlane seats column count: \t");
		int seatColumnsCount = input.nextInt();
		for (int i = 0; i < seatColumnsCount; i++) {
			System.out.print("\nEnter seat row length :\t");
			int row = input.nextInt();
			System.out.print("\nEnter seat column length :\t");
			int column = input.nextInt();
			airPlane.seatList.add(new Seats(row, column));
			airPlane.totalNoOfSeats += row * column;
			bufferedWriter.write("[" + row + "," + column + "]\t");
		}
		System.out.print("\nEnter passengers length :\t");
		airPlane.passengers = input.nextInt();
		while (airPlane.passengers > airPlane.totalNoOfSeats) {
			System.out.println("Total no of passengers is greater than the total no of seats in the airplane."
			        + "Please enter valid no of passengers");
			System.out.print("\nEnter passengers length :\t");
			airPlane.passengers = input.nextInt();
		}
		bufferedWriter.write("\nTotal No. of Passengers :\t" + airPlane.passengers + "\n");
		airPlane.fillSeatInitialValues();
		airPlane.fillSeatWithPassengers(AISLE_SEAT);
		airPlane.fillSeatWithPassengers(WINDOW_SEAT);
		airPlane.fillSeatWithPassengers(MIDDLE_SEAT);
		String spaces = "";
		bufferedWriter.write("\nOutput :\n");
		bufferedWriter.write("\nTotal No. of AirPlane Seats:\t" + airPlane.totalNoOfSeats + "\n");
		bufferedWriter.write("\nAirplane Seats/Position:\n");
		for (int i = 0; i <= maxRowLength; i++) {
			airPlane.printAirPlaneSeats(i, spaces);
			spaces += "\t";
			bufferedWriter.write("\n");
		}
		System.out.println("Output will be printed into the text file...");
		bufferedWriter.write("\nAvailable AirPlane Seats:\t" + (airPlane.totalNoOfSeats - airPlane.passengers));
		bufferedWriter.flush();
	}

	/**
	 * output- Print air plane seats.
	 * 
	 * @throws IOException
	 */
	public void printAirPlaneSeats(int row, String spaces) throws IOException {

		for (int i = 0; i < seatList.size(); i++) {
			Seats seat = seatList.get(i);
			int[][] matrixValues = seat.getSeatValues();
			if (row < matrixValues.length) {
				for (int j = 0; j < matrixValues[row].length; j++) {
					if (matrixValues[row][j] > 0) {
						bufferedWriter.write(matrixValues[row][j] + " ");
					} else {
						bufferedWriter.write("X" + " ");
					}
				}
				bufferedWriter.write("\t");
			} else {
				bufferedWriter.write(spaces);
			}
		}
	}

	/**
	 * Fill seat with initial values.
	 */
	public void fillSeatInitialValues() {
		for (int index = 0; index < seatList.size(); index++) {
			Seats seat = seatList.get(index);
			int[][] seatValues = seat.getSeatValues();
			boolean isLeftWindowSeatAvailable = false;
			boolean isRightWindowSeatAvailable = false;
			if (index == 0) {
				isLeftWindowSeatAvailable = true;
			}
			if (index == (seatList.size() - 1)) {
				isRightWindowSeatAvailable = true;
			}
			for (int row = 0; row < seat.getRow(); row++) {
				if (row > maxRowLength) {
					maxRowLength = row;
				}
				for (int col = 0; col < seat.getColumn(); col++) {
					seatValues[row][col] =
					    AirPlaneUtil.getSeatValues(col, seat.getColumn(), isLeftWindowSeatAvailable,
					        isRightWindowSeatAvailable);
				}
			}
		}

	}

	/**
	 * Fill seat with passengers values based on priority.
	 * 
	 * @param priority
	 *            the priority
	 */
	public void fillSeatWithPassengers(int priority) {
		for (int i = 0; i <= maxRowLength; i++) {
			fillUtil(i, priority);
		}

	}

	/**
	 * Util function to fill seat with passengers values.
	 * 
	 * @param row
	 *            the row
	 * @param priority
	 *            the priority
	 */
	private void fillUtil(int row, int priority) {

		for (int i = 0; i < seatList.size(); i++) {
			Seats seat = seatList.get(i);
			int[][] matrixValues = seat.getSeatValues();
			if (row < matrixValues.length) { // check row is available or not
				for (int j = 0; j < matrixValues[row].length; j++) {
					if (count >= passengers) {
						break;
					}
					if (matrixValues[row][j] == priority) {
						matrixValues[row][j] = ++count;
					}
				}
			}
		}
	}
}