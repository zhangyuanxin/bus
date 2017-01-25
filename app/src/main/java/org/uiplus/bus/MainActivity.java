package org.uiplus.bus;

import android.app.Activity;
import android.database.CursorJoiner;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.uiplus.bus.parser.BusLine;
import org.uiplus.bus.parser.BusProvider;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by Steven on 17/1/25.
 */

public class MainActivity extends Activity {

    ListView lv_all_lines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_search_all = (Button) findViewById(R.id.main_btn_search_all);
        lv_all_lines = (ListView) findViewById(R.id.main_lv_all_lines);

        btn_search_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BusProvider busProvider = new BusProvider();
                AsyncTask asyncTask = busProvider.execute();
                try {
                    List<Map<String, String>> busLineList = (List<Map<String, String>>) asyncTask.get();
                    Log.d("busline", busLineList.toString());

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


    }
}
