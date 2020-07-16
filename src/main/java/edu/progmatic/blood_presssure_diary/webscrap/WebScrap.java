package edu.progmatic.blood_presssure_diary.webscrap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;


public class WebScrap {


    public static String getDataFromUrl(){
        Logger logger = LoggerFactory.getLogger(WebScrap.class);
        Document doc;
        String data = "";
        {
            try {
                doc = Jsoup.connect("https://sussfelnap.hu/orvosmeteorologia/").userAgent("chrome/83.0").get();
                Elements temp = doc.select("div.humanmetdaytext");
                for (Element element : temp) {
                     data = element.getElementsByTag("div").first().text();
                    System.out.println(data);
                }
                return data;
            } catch (IOException e) {
                logger.debug("HIBA OKA: "+e.getCause() + " " +e.getStackTrace());
            }
        }
        return data;
    }

    public static void main(String[] args) {
        System.out.println(getDataFromUrl());
    }
}

