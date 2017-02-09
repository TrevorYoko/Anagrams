package Assignment4;

public class Main 
{
	public static void main(String args[])
	{
		String file1 = "Resources/words";
		String file2 = "Resources/moderate_word_list";
		String file3 = "Resources/words_english";

		//Find the largest group in a small list
		System.out.println("Small Word List");
		for(String str : AnagramUtil.getLargestAnagramGroup(file1))
		{
			System.out.println(str);
		}
		System.out.println();

		//Find the largest group in a small list
		System.out.println("Medium Word List");
		for(String str : AnagramUtil.getLargestAnagramGroup(file2))
		{
			System.out.println(str);
		}
		System.out.println();
		
		//Find the largest group in a small list
		System.out.println("English  Language");
		for(String str : AnagramUtil.getLargestAnagramGroup(file3))
		{
			System.out.println(str);
		}

	}
}
