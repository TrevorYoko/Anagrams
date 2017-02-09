package Assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class AnagramUtil 
{
	/**
	 * This method returns the sorted version of the input string. The
	 * sorting must be accomplished using an insertion sort.  
	 * 
	 * @param word -- string to be sorted
	 * @return String -- input string sorted alphabetically
	 */
	public static String sort(String word)
	{
		char strArray[] = word.toCharArray(); //Make an array of chars to simplify 
		String result = "";
		
		for(int pos = 1; pos < word.length(); pos ++)
		{
			char insertElement = strArray[pos]; //Element to insert
			
			int insertPosition = pos; //Position to start looking
			
			//Find the position where the element should be located
			while(insertPosition > 0 && strArray[insertPosition - 1] > insertElement)
			{
				strArray[insertPosition] = strArray[insertPosition - 1];
				insertPosition --;
			}
			
			strArray[insertPosition] = insertElement; //Insert Element
		}
		
		//Put the values of the array back into a string
		for(int index = 0; index < strArray.length; index ++)
		{
			result += strArray[index];
		}
		return result;
	}

	
	/**
	 * This Generic method sorts the input array through the process of insertion sort
	 * It determines the value of each of the through the comparator
	 * 
	 * @param  array   -- The input array full of non-null strings
	 * @param  compare -- The comparator that uses the "compareTo" function to determine the value of the strings
	 */
	public static <T> void insertionSort(T[] array, Comparator<? super T> compare)
	{
		for(int pos = 1; pos < array.length; pos ++)
		{
			T insertElement = array[pos]; //Element to insert
			
			int insertPosition = pos; //Position to start looking
			
			//Find the position where the element should be located
			while(insertPosition > 0 && compare.compare(array[insertPosition - 1], insertElement) > 0)
			{
				array[insertPosition] = array[insertPosition - 1];
				insertPosition --;
			}
			
			array[insertPosition] = insertElement; //Insert Element
		}
	}

	/**
	 * This method returns the sorted version of the input string. The
	 * sorting must be accomplished using an insertion sort.  
	 * 
	 * @param word1          -- string that is compared againist word2
	 * @param word2 	     -- string that is compared against word1 
	 * @return boolean value -- true if the words are anagrams, false if they are not
	 */
	public static boolean areAnagrams(String word1, String word2)
	{
		return sort(word1).equalsIgnoreCase(sort(word2));
	}

	/**
	 * This method finds and returns the largest group of one word anagrams from an array of strings
	 * 
	 * @param  words  -- array of valid words
	 */
	@SuppressWarnings("unchecked")
	public static String[] getLargestAnagramGroup(String[] words)
	{
		if(words.length == 0)
		{
			return new String[0];
		}
		//New clone of the inputted array
		String[] sortedArr = words.clone();
		//sorts each word in the array
		for(int i = 0; i < sortedArr.length; i++){
			sortedArr[i] = sort(sortedArr[i]);
		}
		//sorts the array through insertion sort
		insertionSort(sortedArr,new AnagramComparator());
		
		ArrayList<String> wordList = new ArrayList<String>();
		
		String groupString = sortedArr[0];
		String largestGroupString = sortedArr[0];
		
		int groupSize = 1;
		int largestGroupSize = 1;
		
		for(int index = 1; index < sortedArr.length; index ++)
		{
			if(groupString.equals(sortedArr[index]))
			{
				groupSize ++;
			}
			else
			{
				if(groupSize > largestGroupSize)
				{
					largestGroupString = groupString;
					largestGroupSize = groupSize;
				}
				
				groupString = sortedArr[index];
				groupSize = 1;
			}
		}
		
		for(int index = 0; index < words.length; index ++)
		{
			if(sort(words[index]).equals(largestGroupString))
			{
				wordList.add(words[index]);
			}
		}

		return wordList.toArray(new String[wordList.size()]);
	}

	/**
	 * This method finds and returns the largest group of one word anagrams from a given file 
	 * 
	 * @param  fileName  -- The name of a file from the resources folder
	 */
	public static String[] getLargestAnagramGroup(String fileName) 
	{
		try
		{
			//Finds the new File
			File testFile = new File(fileName);
			Scanner inputFile = new Scanner(testFile);
			ArrayList<String> words = new ArrayList<String>(); 
			//Copies the content of the file into an arrayList
			while (inputFile.hasNextLine())
			{ 
				String word = inputFile.nextLine();
				words.add(word); 
			}
			//Converts the arrayList to an array
			String wordArr[] = new String[words.size()];
			wordArr = words.toArray(wordArr);
			//Passes the array to the other getLargestAnagramGroup method
			return getLargestAnagramGroup(wordArr);
			
		}
		catch(FileNotFoundException e) 
		{
			System.out.println("File not found");
			return new String[0];
		}
	}
	//New Comparator Class
	static class AnagramComparator  implements Comparator 
	{
		/**
		 * This methods implements the compare class for our Anagram class
		 * 
		 * @param word1 -- An object that will be casted to a string and compared against word1
		 * @param word2 -- An object that will be casted to a string and compared against word1
		 */
		@Override
		public int compare(Object word1, Object word2) 
		{
			//if it is the same return 0
			if (word1.equals(word2))
			{
				return 0;
			}
			//if word2 equals null return -1
			if (word1.equals(null))
			{
				return -1;
			}
			//if word1 equals null return 1
			if (word2.equals(null))
			{
				return 1;
			}
			//return the compareTo of strings 
			return ((String) word1).compareTo((String)word2);
		}
	}
}



