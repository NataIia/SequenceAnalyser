package tests;

import javax.swing.JOptionPane;

import model.Fsta;

public class FstaTest
{
	public static void main(String[] args)
	{
		Fsta f = new Fsta("test", "CRGWRGRAYTTCGGAGCTGWGTTTTCAGCAGATCACGGATCCTGACGACTGACCATGCAGCGATATCGGSTTAAGCAGTTGTTCGCAGGTGGGGTAAAGTAGGTCTGGTGCGGCGTGCGAGGAGGGGATGCGTAACCTGCTCCCAGTCGACGGTCGCATAGGTACACTCTTTCCGGACGGGCTGGCAACACGCCGAAATCATGGCTTAAGTCAGGTATGRCTCTCCCTATAGTGAGTCGTATTAKAATTCKCTATCTTTCTARAAGATCTCCTACAATATTCTCAGCTGCCATGGAAAATCGATGTTCTTCTTTTATTCTCTCAAGATTTTCAGGCTGTATATTAAAACTTATATTAAGAACTATGCTAACCACCTCATCARGAACCGTTGTAGGTGGCGTGGGTTTTCTTGGCAATCGACTCTCATGAAAACTACGAGCTAMATATTCAATATGTTCCTCTTGACCAACTTTATTCTGCATTTTTTTTGAACGAGGTTTAGAGCAAGCTTCAGGAAACTGAGACAKGAATTTTATTAAAAATTTAAATTTTGAAGAAAGTTCARGGTTAATAGCATCCATTTTTTGCTTTGCAAGTTCCTCAGCATTTCWTAACAWAAGACGTCTCTTTTGACATGKTTTAAAGTTTAAAYCTCCTKGTGTGAAATTMTWATCCGCTCAYAAYTYCCWCACMTTAWACGAGYCCGGRARGMWTAAARTGYAAAGGCYTGGGKTGCCTWATGAGWGARCTWAACTCACWTTAATTGCGTTGCGSYKCACKGSCATTTGCTTTCCCAGTCGGGAAAACYTGGWCGTGGCCAGCTGCATT");
		System.out.println(f.findStartPrimer("GTCAGGTATGGCTCTCCC", 1));
		System.out.println(f.findEndPrimer("CACGGATCCTGACGACTGAC", 1));
		try
		{
			System.out.println(f.cutWithingPrimers("CACGGATCCTGACGACTGAC", 1, "AGCGAATTCTAATACGACTCACTATAGGGAGAGCCATACCTGAC", 3));
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,  "Error: " + e);
			e.printStackTrace();
		}
	}
}