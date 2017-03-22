package org.uiplus.bus;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.uiplus.bus.parser.BusProvider;
import org.uiplus.bus.parser.QueryTask;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by zhangyuanxin on 2017/1/27.
 */

public class BusLineActivity extends AppCompatActivity {

    TextView busline_tv_profile;
    ListView busline_lv_stations;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busline);

        busline_tv_profile = (TextView) findViewById(R.id.busline_tv_profile);

        busline_lv_stations = (ListView) findViewById(R.id.busline_lv_stations);
        adapter = new ArrayAdapter<String>(this, R.layout.busline_listview_item, R.id.busline_lv_item_tv_station);
        busline_lv_stations.setAdapter(adapter);
        syncTask();
    }

    private void syncTask() {
        try {
            Intent intent = getIntent();
            setTitle(intent.getStringExtra("line"));

            QueryTask queryTask = new QueryTask();
            AsyncTask asyncTask = queryTask.execute(BusProvider.class, "getBusLineById",
                    intent.getStringExtra("lineId"));
            Map<String, Object> map = (Map<String, Object>) asyncTask.get();

            busline_tv_profile.setText((String) map.get("profile"));
            adapter.addAll((List<String>) map.get("up"));
            adapter.notifyDataSetChanged();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
