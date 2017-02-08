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
}
