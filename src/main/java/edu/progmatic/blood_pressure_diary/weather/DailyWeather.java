package edu.progmatic.blood_pressure_diary.weather;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DailyWeather {
    public static Map<String, Object> jsonToMap(String str) {
        return new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>() {}.getType());
    }

    public Map<String, Object> getData() {
        String API_KEY = "b673cf26eedffd2acf827d9f42b1883b";
        String LOCATION = "Budapest";
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=" + API_KEY + "&units=imperial";
        Map<String, Object> mainMap = new HashMap<>();
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            System.out.println("Eredmény: " + result);

            Map<String, Object> respMap = jsonToMap(result.toString());
            mainMap = jsonToMap(respMap.get("main").toString());

            /*
            System.out.println("Current Temperature: "+ mainMap.get("temp"));
            System.out.println("Current Humidity: "+ mainMap.get("humidity"));
            System.out.println("pressure: "+ mainMap.get("pressure"));*/
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return mainMap;
    }
}
