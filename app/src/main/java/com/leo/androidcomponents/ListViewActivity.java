package com.leo.androidcomponents;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    private Context mContext;
    private ArrayList<String> array_mountain;
    private ListView mListViewActivity;
    private ListViewAdapter mListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);
        this.mContext = getApplicationContext();
        mListViewActivity = (ListView) findViewById(R.id.list_mountain);

        // 데이터 생성
        array_mountain = new ArrayList<>();
        array_mountain.add("한라산");
        array_mountain.add("백두산");
        array_mountain.add("월출산");
        array_mountain.add("금강산");
        array_mountain.add("마니산");
        array_mountain.add("설악산");
        array_mountain.add("관악산");
        array_mountain.add("지리산");
        array_mountain.add("대둔산");
        array_mountain.add("도봉산");

        // 아답터 연결
        mListViewAdapter = new ListViewAdapter(mContext, array_mountain);
        mListViewActivity.setAdapter(mListViewAdapter);
    }

}
