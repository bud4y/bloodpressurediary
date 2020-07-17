package edu.progmatic.blood_presssure_diary.webscrap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Configuration
public class WebScrap {
    public static String getDataFromUrl() {
        Logger logger = LoggerFactory.getLogger(WebScrap.class);
        Document doc;
        String data = "";
        {
            try {
                doc = Jsoup.connect("https://sussfelnap.hu/orvosmeteorologia/").userAgent("chrome/83.0").get();
                Elements temp = doc.select("div.humanmetdaytext");
                return temp.get(0).text();
            } catch (IOException e) {
                logger.debug("HIBA OKA: " + e.getCause() + " " + e.getStackTrace());
            }
        }
        return data;
    }
}

