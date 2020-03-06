package project.Tokenization;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.hankcs.hanlp.HanLP;
import project.FileCharsetDetector;

public class Tokenization {

	public Tokenization(){
	}

	public ArrayList tokenization(String targetFile){
		ArrayList<String> content = read(targetFile);//read your file
		String a = HanLP.segment(String.valueOf(content)).toString();//apply HanLP to your txt
		String b = a.substring(1, a.length() - 1);
		String e = b.replaceAll("\r|\n","");
		String[] c = e.replace(" ", "").split(",");

		ArrayList d = new ArrayList<String>();
		for (int i = 0; i < c.length; i++) {
			if(!c[i].split("/")[0].equals(" ") && !c[i].split("/")[0].equals("\n") && !c[i].split("/")[0].equals(""))
				d.add(c[i].split("/")[0]);
		}
		
		ArrayList sw = readLine("stopwords.txt");
		int size = d.size();
		for(int i=0;i<size;i++) {
			for(int j=0;j<sw.size();j++) {
				if(d.get(i).equals(sw.get(j))) {
					d.remove(i);
					i--;
					size--;
					break;
				}
			}
		}
		return d;
	}

	private static ArrayList read(String path) {
		ArrayList<String> content = new ArrayList<>();
		FileCharsetDetector fcd = new FileCharsetDetector();
		try (BufferedReader r = new BufferedReader(new InputStreamReader(
				new FileInputStream(path), fcd.charSetDetect(path)))) {//find the charset and read file
			String line;
			while ((line = r.readLine()) != null) {
				content.add(line);
			}
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}
		return content;
	}

	private static ArrayList readLine(String path) {
		ArrayList a = new ArrayList<String>();
		FileCharsetDetector fcd = new FileCharsetDetector();
		try(BufferedReader r = new BufferedReader(new InputStreamReader(
				new FileInputStream(path), fcd.charSetDetect(path)))) {
			String s;
			while ((s = r.readLine()) != null) {
				a.add(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return a;
	}
}
