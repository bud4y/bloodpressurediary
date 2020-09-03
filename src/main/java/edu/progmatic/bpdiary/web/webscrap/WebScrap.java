package edu.progmatic.bpdiary.web.webscrap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class WebScrap {
    public String getMedicalMeteorologyDataFromUrl() {
        Logger logger = LoggerFactory.getLogger(WebScrap.class);
        Document doc;
        String data = "";
        {
            try {
                doc = Jsoup.connect("https://www.meteoklinika.hu/").userAgent("chrome/83.0").get();
                Elements temp = doc.select("div.field-content");
                return temp.get(0).text();
            } catch (IOException e) {
                logger.debug("HIBA OKA: " + e.getCause() + " " + e.getStackTrace());
            }
        }
        return data;
    }
}

