package org.uiplus.bus.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Steven on 17/1/24.
 */

public class BusProvider {

    private final static String TAG = "BusProvider";

    public BusProvider() {
    }

    public List<Map<String, String>> getAllBusLineList() {
        List<Map<String, String>> busLineList = new ArrayList<>();

        try {
            Document document = Jsoup.connect("http://www.jnbus.com.cn/xianlu/XianLuList.jsp").get();

            Elements pages = document.select(".pages_box li a");

            Document doc;
            Elements lines;
            Elements elements;
            Map<String, String> map;

            int lastPageNo = Integer.parseInt(pages.last().attr("href").split("=")[1]);
            for (int i = 0; i < lastPageNo; i++) {
                doc = Jsoup.connect("http://www.jnbus.com.cn/xianlu/XianLuList.jsp?pageno=" + i).get();
                lines = doc.select(".xianlucon .xltable tr");
                for (int j = 1; j < lines.size(); j++) {
                    elements = lines.get(j).select("td");

                    map = new HashMap();
                    map.put("line", elements.get(0).text());
                    map.put("origin", elements.get(1).text());
                    map.put("destination", elements.get(2).text());
                    map.put("runningTime", elements.get(3).text());
                    map.put("lineId", elements.get(4).select("a").attr("onclick").replace("showline('", "").replace("')", ""));

                    busLineList.add(map);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return busLineList;
    }

    public List<Map<String, String>> getBusLinesByName(String lineName) {
        List<Map<String, String>> list = new ArrayList<>();

        try {
            Document document = Jsoup.connect("http://www.jnbus.com.cn/xianlu/XianLu.jsp")
                    .data("xianlu", lineName).data("mudidiflag", "")
                    .ignoreContentType(false).postDataCharset("UTF-8").post();

            Elements elements = document.select(".duozhanitem");

            Map<String, String> map;
            for (Element element : elements) {
                map = new HashMap<>();

                map.put("line", element.text());
                map.put("lineId", element.attr("v"));
                list.add(map);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Map<String, Object> getBusLineById(String lineId) {
        Map<String, Object> map = new HashMap<>();
        try {

            Document document = Jsoup.connect("http://www.jnbus.com.cn/xianlu/GetLineInfo.jsp?id=" + lineId).get();

//            System.out.println(document);

            Elements uls = document.select("body td ul");

            List list;
            for (int i = 0; i < uls.size(); i++) {
                list = new ArrayList();
                for (Element li : uls.get(i).select("li")) {
                    list.add(li.text().replaceAll("\\d*\\. ", ""));
                }

                map.put(i == 0 ? "up" : "down", list);
            }

            document.select("table").remove();
            map.put("profile", document.getElementsByTag("body").text());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    public Map<String, List<Map<String, String>>> getBusLinesByStation(String station) {
        Map<String, List<Map<String, String>>> map = new HashMap<>();
        try {
            Document document = Jsoup.connect("http://www.jnbus.com.cn/xianlu/ZhanDian.jsp?zhandian=" + station).get();

            Elements titles = document.select(".tujing_title");
            Elements lines = document.select(".tujing_xianlu");

            List<Map<String, String>> list;
            Map<String, String> lineMap;
            for (int i = 0; i < titles.size(); i++) {
                list = new ArrayList<>();

                lineMap = new HashMap<>();
                for (Element line : lines.get(i).select("a")) {
                    lineMap.put("line", line.text());
                    lineMap.put("lineId", line.attr("onclick").replaceAll(".*'", "").replaceAll("'.*", ""));
                    list.add(lineMap);
                }

                map.put(titles.get(i).text(), list);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    public List<String> getBusListByDestination(String origin, String destination) {
        return null;
    }

}
