package help;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileHelp {

	public static void createFile(String fileName) throws IOException {
		File file = new File(fileName + ".txt");
		file.createNewFile();
	}
	
	public static void writeFile(String fileName, String text) throws IOException{
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".txt", true)));
	    out.println(text);
	    out.close();
	}
	
	public static void writeArray(String fileName, String text[]) throws IOException {
		
		PrintWriter writer = new PrintWriter(fileName + ".txt");
		for(int i = 0; i < text.length; i++) {
			writer.println(text[i]);
		}
		writer.close();
	}
	
	public static String readFile(String fileName, int  lineNumber) throws IOException {
		
		String output = null;
		
		BufferedReader r = new BufferedReader(new FileReader(fileName + ".txt"));
		for(int i = 0; i < lineNumber + 1; i++)
		{
			output = r.readLine();
			
		}

		return output;
	}
	
	public static void clearFile(String fileName) throws IOException {
		FileWriter fwOb = new FileWriter(fileName + ".txt", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
	}
	
	
}
