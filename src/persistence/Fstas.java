package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JOptionPane;

import model.Fsta;

import static utils.Constants.EXTENTION_AB1;

/**
 * Collect all fsta-format sequences in one txt file
 * 
 * @author Natalia
 *
 */
public class Fstas
{
	private TreeMap<Integer, Fsta> fstas;
	
	public Fstas()
	{
		setFstas(new TreeMap<Integer, Fsta>());
	}

	public TreeMap<Integer, Fsta> getFstas()
	{
		return fstas;
	}

	public void setFstas(TreeMap<Integer, Fsta> fstas)
	{
		this.fstas = fstas;
	}
	
	/**
	 * Read all .fsta files from path directory and collect them into TreeMap fstas
	 * 
	 * @param path
	 */
	public void setFstas(String path)
	{
		try
		{
			Files.walk(Paths.get(path))
			.filter(p -> p.toString().contains(".fsta"))
			.forEach(file -> fstas.put(fstas.size(), this.fsta(file)));
					
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(null,  path + "is not correct path. \n" + e);
			e.printStackTrace();
		}
	}
	
	public void setFstas(File file)
	{
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(file);
			while(scanner.hasNext())
			{
				String[] f = scanner.nextLine().split("\t");
				fstas.put(fstas.size(), new Fsta(f[0], f[1]));
			}
		} catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,  file.toString() + "is not correct path. \n" + e);
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Writes list of sequences into sequences.txt file in path directory
	 * 
	 * @param path
	 */
	public void saveFstas(String path)
	{
		try
		{
			PrintWriter writer = new PrintWriter(path + "sequences.txt");
			fstas.values().forEach(fsta -> writer.println(fsta));
			if (writer != null)
			{
				writer.close();
			}

		} catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,  path + "is not correct path. \n" + e);
			e.printStackTrace();
		}
	}
	
	public void saveSequencesWithingPrimers(String path)
	{
		try
		{
			PrintWriter writer = new PrintWriter(path + "sequencesWithingPrimers.txt");
			fstas.values().forEach(fsta -> writer.println(fsta.getCuttedString()));
			if (writer != null)
			{
				writer.close();
			}

		} catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null,  path + "is not correct path. \n" + e);
			e.printStackTrace();
		}
	}
		
	private Fsta fsta(Path path)
	{
		Scanner scanner = null;
		String name = "", sequence = "";
		try
		{
			scanner = new Scanner(path);
			scanner.findInLine("[^>]*" + EXTENTION_AB1);
			name = scanner.match().group(0).replaceAll(EXTENTION_AB1, "");
			while(scanner.hasNext())
			{
				sequence += scanner.next();
			}
		} catch (IOException e)
		{
			JOptionPane.showMessageDialog(null,  "Error reading file: " + e);
			e.printStackTrace();
		} finally
		{
			scanner.close();
		}
		return new Fsta(name, sequence);
	}
	
	@Override
	public String toString()
	{
		StringBuilder s = new StringBuilder();
		fstas.values().stream().forEach(fsta -> s.append(fsta.getName() + "\t" + fsta.getSequence() + "\n"));
		return s.toString();
	}
}
