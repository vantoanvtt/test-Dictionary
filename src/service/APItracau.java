package service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


public class APItracau {

    private String sentence = new String();
    public static String getData(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        String result1 = result.toString();
        JSONObject jsonObj = new JSONObject(result1);
        JSONArray json_arr = jsonObj.getJSONArray("sentences");
        String result2 = new String();
        for (int i = 0; i < json_arr.length(); i++) {
            JSONObject js = json_arr.getJSONObject(i);
            JSONObject js2 = js.getJSONObject("fields");
            String en = js2.getString("en");
            en = en.replace("<em>", "");
            en = en.replace("</em>", "");
            result2 = result2 + "******" + "\n" + js2.getString("vi")
                    + "\n" + en + "\n";
        }
        rd.close();
        return result2;
    }

    public APItracau(String word) throws Exception {
        this.sentence = getData("https://api.tracau.vn/WBBcwnwQpV89/s/" + word + "/en");
        System.out.println(sentence);
    }

    public String getSentence() {
        return sentence;
    }

}
