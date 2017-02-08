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
	@SuppressWarnings("unchecked")
	public static String[] getLargestAnagramGroup(String[] words)
	{

		String[] sortedArr = words.clone();
		for(int i = 0; i < sortedArr.length; i++){
			sortedArr[i] = sort(sortedArr[i]);
		}
		insertionSort(sortedArr,new AnagramComparator());
		
		ArrayList<String> wordList = new ArrayList<String>();
		String keyword = "";
		for(int pos = 0; pos < sortedArr.length - 1; pos++)
		{
			if(sortedArr[pos].equals(sortedArr[pos + 1])){
				keyword = sortedArr[pos];
			}
		}
		for(int i = 0; i < words.length; i++){
			if(areAnagrams(words[i], keyword))
			{
				wordList.add(words[i]);
			}
		}
		return wordList.toArray(new String[wordList.size()]);
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
	//New Comparator Class
	static class AnagramComparator  implements Comparator {
		@Override
		public int compare(Object word1, Object word2) {
			//if it is the same return 0
			if (word1.equals(word2)) {
				return 0;
			}
			//if word2 equals null return -1
			if (word1.equals(null)) {
				return -1;
			}
			//if word1 equals null return 1
			if (word2.equals(null)) {
				return 1;
			}
			//return the compareTo of strings 
			return ((String) word1).compareTo((String)word2);
		}
	}
}



