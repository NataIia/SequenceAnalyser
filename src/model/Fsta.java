package model;

import utils.Functions;

/**
 * Class creates object contains sequence code and sequence
 * 
 * @author Natalia
 *
 */
public class Fsta
{
	private String name, sequence, sequenceWithingPrimers;

	public Fsta(){}
	
	public Fsta (String name, String sequence)
	{
		this.name = name;
		this.sequence = sequence;
	}
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSequence()
	{
		return sequence;
	}

	public void setSequence(String sequence)
	{
		this.sequence = sequence;
	}
	
	public String getSequenceWithingPrimers()
	{
		return sequenceWithingPrimers;
	}

	public void setSequenceWithingPrimers(String sequenceWithingPrimers)
	{
		this.sequenceWithingPrimers = sequenceWithingPrimers;
	}

	/**
	 * Find the end position of the primer with given hamming distance
	 * Negative means no primer sequence found with given hamming distance
	 * 
	 * @param primer
	 * @param hammingDistance
	 * @return
	 */
	public int findEndPrimer(String primer, int hammingDistance)
	{
		for(int i = 0; i < sequence.length() - primer.length(); i++)
		{
			if(Functions.hammingDistance(sequence.substring(i, i+primer.length()), primer) <= hammingDistance)
				return i + primer.length();
		}
		return -1;
	}
	
	/**
	 * Find the start position of the primer with given hamming distance
	 * 
	 * @param primer
	 * @param hammingDistance
	 * @return
	 */
	public int findStartPrimer(String primer, int hammingDistance)
	{
		for(int i = 0; i < sequence.length() - primer.length(); i++)
		{
			if(Functions.hammingDistance(sequence.substring(i, i+primer.length()), primer) <= hammingDistance)
				return i;
		}
		return -1;
	}
	public String cutWithingPrimers(String forward, int maxHammingDistanceForward, String reverse, int maxHammingDistanceReverse) throws Exception
	{
		int hammingDistanceForward = 0;
		int hammingDistanceReverse = 0;
		
		int forwardPosition = -1;	
		int reversePosition = -1;
		
		
		do{	
			forwardPosition = this.findEndPrimer(forward, hammingDistanceForward);
			reversePosition = this.findStartPrimer(Functions.complement(reverse), hammingDistanceReverse);
			if(forwardPosition < 0)
				forwardPosition = this.findStartPrimer(Functions.complement(forward), maxHammingDistanceForward);
			if(reversePosition < 0)
				reversePosition = this.findEndPrimer(reverse, maxHammingDistanceReverse);
		}while
			(forwardPosition < 0 || reversePosition < 0 || 
			hammingDistanceForward >= maxHammingDistanceForward || 
			hammingDistanceReverse >= maxHammingDistanceReverse);
		

		
		if (forwardPosition < 0 || reversePosition < 0)
			throw new Exception("No primers withing given Hamming distance are found");
		
			sequenceWithingPrimers = forwardPosition < reversePosition ? 
									sequence.substring(forwardPosition, reversePosition) :
									sequence.substring(reversePosition, forwardPosition);
		
		return sequenceWithingPrimers;
	}
	
	public String getCuttedString()
	{
		return this.name + "\t" + this.sequenceWithingPrimers;
	}
	
	public String toString()
	{
		return this.getName() + "\t" + this.getSequence();
	}
}
