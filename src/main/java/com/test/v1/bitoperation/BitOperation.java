package com.test.v1.bitoperation;

public class BitOperation {

	public boolean getBit(int num, int i) {
		
		return ( (num& (1<<i))  !=0);
	}
	
	public static void main(String [] args) {
		BitOperation bitOps = new BitOperation();
		//System.out.println(bitOps.getBit(7, 2));
		
		//System.out.println(bitOps.clearBit(7, 0));
		
		//System.out.println(bitOps.clearMostSignificatBitToI(8, 2));
		
		System.out.println(bitOps.clearBitsIthrough0(8, 1));
		
		
		
	}
	
	public int clearBit(int num, int i) {
		
		int mask= ~(1<<i);
		return (num & mask);
	}
	
	public int clearMostSignificatBitToI(int num, int i) {
		int mask=(1<<i)-1;
		return (num&mask);
	}
	public int clearBitsIthrough0(int num, int i) {
		  int mask = ~((1 << (i+1)) - 1);
		 return num & mask;
	}
	
}
