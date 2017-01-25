package org.uiplus.bus.parser;

import android.os.AsyncTask;
import android.util.Log;

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

public class BusProvider extends AsyncTask implements BusStations {

    private final static String TAG = "BusProvider";


    public static List<Map<String, String>> getAllBusLineList() {
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


    @Override
    public List<String> getBusListByLine(String line) {
        try {
            Document document = Jsoup.connect("http://www.jnbus.com.cn/xianlu/XianLuList.jsp").get();


        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public List<String> getBusListByStation(String station) {
        return null;
    }

    @Override
    public List<String> getBusListByDestination(String origin, String destination) {
        return null;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return getAllBusLineList();
    }
}
