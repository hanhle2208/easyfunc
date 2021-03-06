package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import resource.JavaResource;


public class ParserUtil {
	
	/**
	 * Get text from file with specify selector
	 * @param filePath path to file which will use for get text
	 * @param selector selector for get text.Selector has syntax of {@link org.jsoup.select.Selector}
	 * @return plain text from selector
	 */
	public static String getText(String filePath,String selector) {
		Document document = Jsoup.parse(filePath);
		String text = document.select(selector).text();
		return text;
	}
	
	/**
	 * Get text from all file in a directory with specify selector
	 * @param directoryPath name of file which will use for get text
	 * @param selector selector for get text.Selector has syntax of {@link org.jsoup.select.Selector}
	 * @return plain text from selector
	 */
	public static List<String> getAllText(String directoryPath,String selector) {
		List<String> result = new ArrayList<String>();
		File file = new File(directoryPath);
		if(!file.isDirectory()){
			System.out.println("Must be directory");
			return null;
		}
		
		for (File f : file.listFiles()) {
			try {
				Document document = Jsoup.parse(f,"UTF-8");
				result.add(document.select(selector).text());
				System.out.println(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}
	/**
	 * Get the location of parsed result
	 * @return location of parsed result 
	 */
	public static String getOuputLocation(){
		return new JavaResource().getOutput();
	}
}
