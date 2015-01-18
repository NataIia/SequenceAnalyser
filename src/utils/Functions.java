package utils;

/**
 * Class contains common used static functions
 * 
 * @author Natalia
 *
 */
public class Functions
{
	public static String complement(String sequence)
	{
		StringBuilder buffer = new StringBuilder(sequence.replaceAll("A", "1")
				.replaceAll("T", "A")
				.replaceAll("1", "T")
				.replaceAll("C", "2")
				.replaceAll("G", "C")
				.replaceAll("2", "G"));
		return buffer.reverse().toString();
	}
	
	/**
	 * Compute the Hamming distance between the two strings <code>sequence1</code> and
	 * <code>sequence2</code>. The two strings to be computed must be of equal length
	 * and the Hamming distance is defined to be the number of positions where
	 * the characters are different.
	 * 
	 * @return The Hamming distance
	 */
	public static int hammingDistance(String sequence1, String sequence2)
	{
		// check preconditions
		if (sequence1 == null || sequence2 == null || sequence1.length() != sequence2.length()) 
			throw new IllegalArgumentException();

		// compute hamming distance
		int distance = 0;
		for (int i = 0; i < sequence1.length(); i++) 
		{
			if (sequence1.charAt(i) != sequence2.charAt(i)) 
			{
				distance++;
			}
		}
		return distance;
	}
}
