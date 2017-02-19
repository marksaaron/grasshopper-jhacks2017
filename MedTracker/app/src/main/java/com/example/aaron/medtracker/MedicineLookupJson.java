import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.JsonNumber;
import javax.json.JsonString;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MedicineLookupJson {
	
	public static void main(String[] args){
		JsonObject test = null;
		try {
			test = getJSONobj(getIdURL("advil"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(getId("morphine"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		JsonObject inter = null;
		String adv = "";
		String asp = "";
		try {
			adv = getId("advil");
			asp = getId("aspirin");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String[] medlist = {adv,asp};
		System.out.println(asp);
		System.out.println(adv);
		String inturl = getInteractionsURL(medlist);
		System.out.println(inturl);
		try {
			System.out.println("Interactions: " + getInteractions(medlist));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		//System.out.println(inter);
		try {
			System.out.println(processInter(getJSONobj("https://rxnav.nlm.nih.gov/REST/interaction/list.json?rxcuis=207106+152923+656659")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static String processInter(JsonObject inter){
		String result = "";
		for(JsonValue i : inter.getJsonArray("fullInteractionTypeGroup")){
			JsonArray interactions = ((JsonObject)i).getJsonArray("fullInteractionType");
			for (JsonValue interaction : interactions){
				result += ((JsonObject)interaction).getJsonArray("interactionPair").getJsonObject(0).getJsonString("description") + "\n";
			}
		}
		
		return result;
	}
	
	public static String getId(String med) throws MalformedURLException, IOException{
		JsonObject test = getJSONobj(getIdURL(med));
		return ((JsonArray)((JsonObject)test.get("idGroup")).get("rxnormId")).get(0).toString();
	}


	public static String getIdURL(String med){
		return "https://rxnav.nlm.nih.gov/REST/Prescribe/rxcui.json?name=" + med;
	}

	
	public static String getInteractionsURL(String[] ids){
		String url = "https://rxnav.nlm.nih.gov/REST/interaction/list.json?rxcuis=";
		for(int i = 0 ; i < ids.length; i++){
			url += ids[i];
			url += (i < ids.length - 1 )? "+" : "";
		}
		return url;
	}

	public static JsonObject getJSONobj(String url) throws MalformedURLException, IOException{
		InputStream in = new URL(url).openStream();
		JsonReader jreader = Json.createReader(in);
		return jreader.readObject();
	}
	
	public static String getInteractions(String[] ids) throws MalformedURLException, IOException{
		return processInter(getJSONobj(getInteractionsURL(ids)));
	}
	
}
