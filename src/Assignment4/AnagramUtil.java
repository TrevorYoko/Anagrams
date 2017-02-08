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
	 * @param str -- string to be sorted
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

	// This generic method sorts the input array using an insertion sort and
	// the input Comparator object.
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

	// This method returns true if the two input strings are anagrams of each other, otherwise returns false.
	public static boolean areAnagrams(String word1, String word2)
	{
		return sort(word1).equalsIgnoreCase(sort(word2));
	}

	// This method returns the largest group of anagrams in the input
	// array of words, in no particular order. It returns an empty array if
	// there are no anagrams in the input array.
	public static String[] getLargestAnagramGroup(String[] words)
	{
		String largestAnagramGroup[] = new String[0]; //Largest group initially of length 0
		
		int currentGroup = 1;  //Length of the current anagram group
		
		int startAnagramIndex = -1;   //Starting index of the current anagram group
		                              //-1 until anagram group has started
		
		for(int index = 0; index < words.length - 1; index ++)
		{
			if(areAnagrams(words[index], words[index + 1])) //If anagrams
			{
				//If the start index has not been initialized to a positive number
				if(startAnagramIndex < 0)
				{
					startAnagramIndex = index;
				}
				currentGroup ++;
			}
			else
			{ 	//If the group contains more than 1 element
				//and it is larger than the previous largest
				if(currentGroup > 1 && currentGroup > largestAnagramGroup.length)
				{
					largestAnagramGroup = new String[currentGroup];
					
					//Loop through and fill the array with correct values
					for(int pos = 0; pos < largestAnagramGroup.length; pos ++)
					{
						largestAnagramGroup[pos] = words[startAnagramIndex + pos];
					}
				}
				
				//Reset Group variables
				currentGroup = 1;
				startAnagramIndex = -1;
			}
		}
		
		
		return largestAnagramGroup;
	}

	// Behaves the same as the previous method, but reads the list of
	// words from the input filename. It is assumed that the file contains
	// one word per line. If the file does not exist or is empty, the method
	// returns an empty array because there are no anagrams.
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
		catch(FileNotFoundException e) {
			System.out.println("File not found");
			return new String[0];
		}
	}
}

