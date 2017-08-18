package com.test.v1.stringarray;

public class SolStringArray {

	public static void main(String[] args) {

		SolStringArray solStringArray = new SolStringArray();
		// System.out.println("result:" + solStringArray.isUniqueCharsV1("abcdefgh"));
		// System.out.println("result:" + solStringArray.isUniqueCharsV2("abcdefghh"));
		// System.out.println("result:" + solStringArray.permutationV1("dog", "god"));
		// System.out.println("result:" + solStringArray.permutationV2("dog", "gad"));

		// char[] data = "a b c d ".toCharArray();
		// solStringArray.replaceSpace(data, 7);
		// System.out.println(new String(data));
		// String testStr = "aaaddnnfmms";
		// String testStr = "aaddnfffmmsssssssssds";
		// System.out.println("original: " + testStr + " Compress: " +
		// solStringArray.compressBadv3(testStr));

		// String testStr = "aabbcmcm mms";
		// System.out.println("original: " + testStr + " Compress: " +
		// solStringArray.isPermutationOfPalindrome(testStr));
		// System.out.println("original: " + testStr + " check: " +
		// solStringArray.isPermutationOfPalindromeV1(testStr));
		// String testStr = "abcbc";
		// System.out.println("original: " + testStr + " check: " +
		// solStringArray.isPermutationOfPalindromeV2(testStr));

		//String testStr1 = "abcbc";
		//String testStr2 = "abebc";
		//System.out.println("test1: " + testStr1 + "  testStr2=" + testStr2 + " check: "
			//	+ solStringArray.oneEditAway(testStr1, testStr2));

		
		
	}

	/* compress bad */
	public String compressBadv1(String str) {
		int count = 0;
		String temp = "";
		char c = '\0';
		boolean compress = false;
		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) == c) {
				++count;
			} else {
				if (count > 0) {
					temp = temp.concat(count + "");
				}
				count = 0;
				temp = temp.concat(str.charAt(i) + "");
				count++;
			}

			if (count > 1) {
				compress = true;
			}
			c = str.charAt(i);

		}

		temp = temp.concat(count + "");
		return compress ? temp : str;
	}

	public String compressBadv3(String str) {
		int newLength = countCompression(str);
		if (newLength >= str.length()) {
			return str;
		}

		int count = 0;
		char temp = str.charAt(0);
		StringBuilder tempStr = new StringBuilder(newLength);
		for (int i = 0; i < str.length(); i++) {

			if (temp == str.charAt(i)) {
				count++;
			} else {
				tempStr.append(temp + "" + count);
				count = 1;
				temp = str.charAt(i);
			}

		}
		return tempStr.append(temp + "" + count).toString();
	}

	public static int countCompression(String str) {

		int count = 0;
		int totalLength = 0;
		char temp = str.charAt(0);

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == temp) {
				count++;
			} else {
				totalLength += 1 + String.valueOf(count).length();
				temp = str.charAt(i);
				count = 1;
			}

		}
		totalLength += 1 + String.valueOf(count).length();
		return totalLength;
	}

	public void replaceSpace(char[] str, int length) {

		int newLength, i, spaceCount = 0;
		for (i = 0; i < length; i++) {
			if (str[i] == ' ') {
				spaceCount++;
			}
		}
		newLength = length + spaceCount * 2;
		str[newLength] = '\0';
		for (i = (length - 1); i >= 0; i--) {

			if (str[i] == ' ') {
				str[newLength - 1] = '0';
				str[newLength - 2] = '2';
				str[newLength - 3] = '%';
				newLength = newLength - 3;

			} else {
				str[newLength - 1] = str[i];
				newLength--;
			}

		}

	}

	/* unique string */
	public boolean isUniqueCharsV1(String str) {
		if (str.length() > 128) {
			return false;

		} else {

			boolean[] charSet = new boolean[128];
			for (int i = 0; i < str.length(); i++) {
				int val = str.charAt(i);

				if (charSet[val]) {
					return false;
				} else {

					charSet[val] = true;
				}
			}

		}
		return true;
	}

	public boolean isUniqueCharsV2(String str) {
		int checker = 0;

		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0) {
				return false;
			}
			checker = checker | (1 << val);
		}

		return true;

	}

	public String sortString(String s) {
		char[] content = s.toCharArray();
		java.util.Arrays.sort(content);
		return new String(content);
	}

	/* permutationV1 */
	public boolean permutationV1(String s, String t) {
		if (s.length() > t.length()) {
			return false;
		}
		return sortString(s).equals(sortString(s));

	}

	public boolean permutationV2(String s, String t) {
		int letters[] = new int[256];
		for (int i = 0; i < s.length(); i++) {
			int val = s.charAt(i);
			letters[val]++;
		}

		for (int i = 0; i < t.length(); i++) {
			int val = t.charAt(i);
			if (--letters[val] < 0) {
				return false;
			}
		}
		return true;
	}

	/* Palindrome Permutation v3 */

	public boolean isPermutationOfPalindrome(String phrase) {
		int[] charFrequencyTable = getCharFrequencyCounts(phrase);
		boolean oddFound = false;
		for (int count : charFrequencyTable) {
			if (count % 2 == 1) {
				if (oddFound) {
					return false;
				}
				oddFound = true;
			}
		}
		return true;

	}

	public boolean isPermutationOfPalindromeV1(String phrase) {
		int oddCounts = 0;
		int[] charFrequency = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
		for (char c : phrase.toCharArray()) {
			int val = getNumberValue(c);
			if (val != -1) {
				charFrequency[val]++;
				if (charFrequency[val] % 2 == 1) {
					oddCounts++;
				} else {
					oddCounts--;
				}
			}

		}
		return oddCounts <= 1;
	}

	public boolean isPermutationOfPalindromeV2(String phrase) {
		int charCount = 0;

		for (char c : phrase.toCharArray()) {
			int val = getNumberValue(c);
			if (val != -1) {
				charCount = toggole(charCount, val);
			}
		}
		int checkOdds = charCount & (charCount - 1);
		return (checkOdds == 0);

	}

	public int toggole(int charCount, int val) {
		return charCount ^ (1 << val);
	}

	public int[] getCharFrequencyCounts(String phrase) {
		int[] charFrequencyTable = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
		for (char c : phrase.toCharArray()) {
			int val = getNumberValue(c);
			if (val != -1) {
				charFrequencyTable[val]++;
			}

		}
		return charFrequencyTable;

	}

	public int getNumberValue(char c) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int A = Character.getNumericValue('A');
		int Z = Character.getNumericValue('Z');
		int val = Character.getNumericValue(c);
		if (val >= a && val <= z) {
			return val - a;
		} else if (val >= A && val <= Z) {
			return val - A;
		} else {
			return -1;
		}

	}

	public boolean oneEditAway(String frist, String second) {

		if (Math.abs((frist.length() - second.length())) > 1) {
			return false;
		}

		String s1 = frist.length() < second.length() ? frist : second;
		String s2 = frist.length() < second.length() ? second : frist;
		
		int index1 = 0, index2 = 0;
		boolean foundDifference = false;
		
		System.out.println(s1+ " " +s2);
		
		while (index2 < s2.length() && index1 < s1.length()) {
			
             if(s1.charAt(index1)!=s2.charAt(index2)) {
            	 if(foundDifference) {
            		 return false;
            	 }
            	 if(s1.length()==s2.length()) {
            		 index1++;
            	 }
            	 foundDifference=true;
             }else {
            	 
            	 index1++;
             }
             index2++;
		}
       return true;
	}

    public void rotateMatrix(int [][] matrix,int n) {
    	
    	for(int layer=0;layer<n/2;layer++) {
    		int first=layer;
    		int last = n-1-first;
    		for(int i=0;i<last;i++) {
    			int offset=i-first;
    			int top=matrix[first][i];
    			matrix[first][i]=matrix[last-offset][first];
    			matrix[last-offset][first]=matrix[last][last-offset];
    			matrix[last][last-offset]=matrix[i][last];
    			matrix[i][first]=top;
    		}
    		
    	}
    } 	
	
}
