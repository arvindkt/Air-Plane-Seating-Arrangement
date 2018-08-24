package com.test.Airplane.util;

/**
 * The Class AirPlaneUtil.
 */
public class AirPlaneUtil {

	/** The aisle seat. */
	static int AISLE_SEAT = -1;

	/** The window seat. */
	static int WINDOW_SEAT = -2;

	/** The middle seat. */
	static int MIDDLE_SEAT = -3;

	/**
	 * Gets the seat values.
	 * 
	 * @param j
	 *            the j
	 * @param columnLength
	 *            the column length
	 * @param isLeftWindowSeatAvailable
	 *            the is left window seat available
	 * @param isRightWindowSeatAvailable
	 *            the is right window seat available
	 * @return the airplane seat values
	 */
	public static int getSeatValues(int j, int columnLength, boolean isLeftWindowSeatAvailable,
	    boolean isRightWindowSeatAvailable) {
		if (j == 0) {
			if (isLeftWindowSeatAvailable) {
				return WINDOW_SEAT;
			}
			return AISLE_SEAT;
		}
		if (j == (columnLength - 1)) {
			if (isRightWindowSeatAvailable) {
				return WINDOW_SEAT;
			}
			return AISLE_SEAT;
		}
		return MIDDLE_SEAT;
	}

}
