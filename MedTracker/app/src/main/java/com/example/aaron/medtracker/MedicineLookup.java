import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;

public class MedicineLookup {
	
	
	
	public static void main(String args[]){
		System.out.println(getGeneric("advil"));
	}
	
	public static String getGeneric(String input) {
		
		String med = sanitizeMed(input); 
		
		Document doc = null;
		try {
			doc = Jsoup.connect("https://www.drugs.com/mtm/"+med + ".html").get();
		} catch (IOException e) {
			// TODO CAN'T ACCESS INTERNET (probably)
			e.printStackTrace();
		}
		
		String subtitle = doc.select("#content > div > p.drug-subtitle").text();
		if(subtitle == null){
			throw new IllegalArgumentException("Cannot find given drug.");
		}
		
		String generic = subtitle.replaceAll("Generic Name:", "").replaceAll("\\(.+\\)", "").replaceAll("Brand Name:.*", "").trim();
		
		
		// String generictext = generic.text();		
		return generic;//text;
	}
	
	
	private static String sanitizeMed(String input){
		String lower = input.toLowerCase();
		String urlmed = lower.replaceAll("\\s", "-");
		return urlmed;
	}
	
	
	
	public static String getInteractions(String[] meds){
		
		return "";
	}

}
