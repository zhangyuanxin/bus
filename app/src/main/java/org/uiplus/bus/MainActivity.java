package org.uiplus.bus;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.CursorJoiner;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.helper.StringUtil;
import org.uiplus.bus.parser.BusLine;
import org.uiplus.bus.parser.BusProvider;
import org.uiplus.bus.parser.QueryTask;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Steven on 17/1/25.
 */

public class MainActivity extends AppCompatActivity {

    ListView lv_all_lines;
    EditText main_et_search;
    List<Map<String, String>> busLineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_et_search = (EditText) findViewById(R.id.main_et_search);

        ImageView main_iv_search_all = (ImageView) findViewById(R.id.main_iv_search_all);
        lv_all_lines = (ListView) findViewById(R.id.main_lv_all_lines);


        main_iv_search_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    QueryTask queryTask = new QueryTask();
                    String search = main_et_search.getText().toString();
                    if (StringUtil.isBlank(search)) {
                        AsyncTask asyncTask = queryTask.execute(BusProvider.class, "getAllBusLineList");
                        busLineList = (List<Map<String, String>>) asyncTask.get();
                    } else {
                        AsyncTask asyncTask = queryTask.execute(BusProvider.class, "getBusLinesByName", search);
                        busLineList = (List<Map<String, String>>) asyncTask.get();
                    }

                    SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, busLineList,
                            R.layout.main_listview_item, new String[]{"line", "origin", "destination"},
                            new int[]{R.id.main_lv_item_tv_line, R.id.main_lv_item_tv_origin, R.id.main_lv_item_tv_destination});

                    lv_all_lines.setAdapter(adapter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        lv_all_lines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    QueryTask queryTask = new QueryTask();
                    Map<String, String> map = busLineList.get(position);
                    String lineId = map.get("lineId");
                    String line = map.get("line");
                    Intent intent = new Intent(MainActivity.this, BusLineActivity.class);
                    intent.putExtra("line", line);
                    intent.putExtra("lineId", lineId);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        ActionBar actionBar = getActionBar();

    }
}
