package Assignment4;

import static org.junit.Assert.*;
import org.junit.Test;

public class AnagramUtilTest 
{
	@Test
	public void testSort()
	{
		String str1 = "abc";
		String str2 = "bca";
		
		String str3 = "abdffgkossv";
		String str4 = "asdfogbvkfs";

		//Test basic sort
		assertTrue(str1.equals(AnagramUtil.sort(str2))); 
		
		//Test the sorting a sorted string
		assertTrue(str1.equals(AnagramUtil.sort(str1))); 
		
		//Test for longer string
		assertTrue(str3.equals(AnagramUtil.sort(str4)));
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testInsertionSort()
	{
		//Regular case test
		String[] actual1 = {"dog", "frog", "cat"};
		String[] expected1 = {"cat", "dog", "frog"};
		AnagramUtil.insertionSort(actual1, new AnagramUtil.AnagramComparator());
		assertArrayEquals(expected1, actual1);
		
		//same letter, different sizes test
		String[] actual2 = {"aaa", "ab", "aa"};
		String[] expected2 = {"aa", "aaa", "ab"};
		AnagramUtil.insertionSort(actual2, new AnagramUtil.AnagramComparator());
		assertArrayEquals(expected2, actual2);
		
		//non-word letter tests
		String[] actual3 = {"abc","def","bsc"};
		String[] expected3 = {"abc","bsc","def"};
		AnagramUtil.insertionSort(actual3, new AnagramUtil.AnagramComparator());
		assertArrayEquals(expected3, actual3);
	}
	@Test
	public void testAreAnagrams()
	{
		//same word test
		String str1 = "herp";
		String str2 = "erhp";
		assertTrue(AnagramUtil.areAnagrams(str1,str2));
		assertTrue(AnagramUtil.areAnagrams(str2,str1));
		
		//same word with spaces test
		String str3 = "  derp";
		String str4 = "e rd p";
		assertTrue(AnagramUtil.areAnagrams(str3,str4));
		
		//different word test
		assertFalse(AnagramUtil.areAnagrams(str3,str1));
	}
	@Test
	public void testgetLargestAnagramGroupArrayString()
	{
		//normal case test
		String[] inital1 = {"cat","tac","herp", "derp", "murp","act", "lerp"};
		String[] expected1 = {"cat","tac","act"};
		String[] completed1 = AnagramUtil.getLargestAnagramGroup(inital1);
		System.out.println();
		assertArrayEquals(expected1, completed1); 
	
		//no two anagrams are alined test
		String[] inital2 = {"slip", "flip", "hit", "hurt", "iht", "lip", "tih"};
		String[] expected2 = {"hit","iht","tih"};
		String[] completed2 = AnagramUtil.getLargestAnagramGroup(inital2);
		assertArrayEquals(expected2, completed2);
		
		//empty array test
		String[] intial3 = {};
		String[] completed3 = AnagramUtil.getLargestAnagramGroup(intial3);
		assertArrayEquals(intial3, completed3);
		
		//all anagrams test
		String[] inital4 = {"hurt", "urth", "turh", "ruth"};
		String[] completed4 = AnagramUtil.getLargestAnagramGroup(inital4);
		assertArrayEquals(inital4, completed4);
	}
	
	@Test
	public void testgetLargestAnagramGroupFile()
	{
		//normal case test
		String fileName = "Resources/testWord1";
		String[] expected1 = {"cat", "act", "tac", "tca"};
		String[] completed1 = AnagramUtil.getLargestAnagramGroup(fileName);
		assertArrayEquals(expected1, completed1); 
	
		//same word test
		fileName = "Resources/testWord2";
		String[] expected2 = {"hit","iht","tih"};
		String[] completed2 = AnagramUtil.getLargestAnagramGroup(fileName);
		assertArrayEquals(expected2, completed2);
		
		//blank file test
		fileName = "Resources/testBlank";
		String[] intial3 = {};
		String[] completed3 = AnagramUtil.getLargestAnagramGroup(fileName);
		assertArrayEquals(intial3, completed3);
		
		//same anagram test
		String[] expected4 = {"hurt", "urth", "turh", "ruth"};
		fileName = "Resources/testWord4";
		String[] completed4 = AnagramUtil.getLargestAnagramGroup(fileName);
		assertArrayEquals(expected4, completed4);
	}
}
