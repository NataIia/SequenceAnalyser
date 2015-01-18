package tests;

import static utils.Constants.PROJECT_PATH;

import java.io.File;

import javax.swing.JOptionPane;

import model.Fsta;
import persistence.Fstas;

public class FstasTest
{

	public static void main(String[] args)
	{
		String project = "sequencing" + File.separator
						+ "20150107" + File.separator
						+ "sequences" + File.separator
						+ "exported" + File.separator;
		Fstas f = new Fstas();
		f.setFstas(new File(PROJECT_PATH + project + "sequences.txt"));
		
		for(Fsta fs : f.getFstas().values())
		{
			try
			{
				System.out.println(fs.getName());
//				System.out.println(fs.cutWithingPrimers("CACGGATCCTGACGACTGAC", 5, "AGCGAATTCTAATACGACTCACTATAGGGAGAGCCATACCTGAC", 3));
				System.out.println(fs.cutWithingPrimers("GACGACTGAC", 1, "CATACCTGAC", 1));

			} catch (Exception e)
			{
				JOptionPane.showMessageDialog(null,  "Error: " + e);
				e.printStackTrace();
			}
		}
		f.saveSequencesWithingPrimers(PROJECT_PATH + project);
	}
}
