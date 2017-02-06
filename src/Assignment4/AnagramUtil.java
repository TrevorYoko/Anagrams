package Assignment4;

import java.util.Comparator;

public class AnagramUtil {

	// This method returns the sorted version of the input string. The
	// sorting must be accomplished using an insertion sort.  
	public static String sort(String word){
		return word;
		
	}

	// This generic method sorts the input array using an insertion sort and
	// the input Comparator object.
	public static <T> void insertionSort(T[] data, Comparator<? super T> compare){
		
	}

	// This method returns true if the two input strings are anagrams of each other, otherwise returns false.
	public static boolean areAnagrams(String word1, String word2){
		return false;
	}

	// This method returns the largest group of anagrams in the input
	// array of words, in no particular order. It returns an empty array if
	// there are no anagrams in the input array.
	public static String[] getLargestAnagramGroup(String[] wordList){
		return wordList;
		
	}

	// Behaves the same as the previous method, but reads the list of
	// words from the input filename. It is assumed that the file contains
	// one word per line. If the file does not exist or is empty, the method
	// returns an empty array because there are no anagrams.
	public static String[] getLargestAnagramGroup(String wordList){
		return null;
		
	}
		
}
