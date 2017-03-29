package com.test.v1.prob;

public class MaxSubArray {

	public static void main(String[] args) {
		Integer data[] = { 1, 2, -4, 4, 2, 2, -4, 5, 6, 8, -9 };
		System.out.println("MaxSubSum:" + maxSumSubArray(data));
	}

	static Integer maxSumSubArray(Integer[] data) {
		Integer maxSoFar = 0;
		Integer maxEndingHere = 0;

		for (int i = 0; i < data.length; i++) {
			Integer d = data[i];
			maxEndingHere = maxEndingHere + d;
			System.out.println("data " + maxEndingHere);
			if (maxEndingHere < 0) {
				System.out.println("total:0; Index : " + i);
				maxEndingHere = 0;
			}

			if (maxSoFar < maxEndingHere) {
				maxSoFar = maxEndingHere;
			}
		}

		return maxSoFar;
	}

}
