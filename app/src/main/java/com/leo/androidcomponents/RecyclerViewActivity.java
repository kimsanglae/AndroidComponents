package com.leo.androidcomponents;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener {
    private Context mContext;
    private RecyclerView rcc_album;
    private RecyclerViewAdapter mAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);
        mContext = this;
        init();
    }

    private void init() {
        rcc_album = (RecyclerView) findViewById(R.id.rcc_album);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 2);
        //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rcc_album.setLayoutManager(mLayoutManager);
        mAlbumAdapter = new RecyclerViewAdapter(mContext, getAlbumList());
        mAlbumAdapter.setOnItemClickListener(this);
        rcc_album.setAdapter(mAlbumAdapter);
    }

    @Override
    public void onItemClick(View view, AlbumVO albumVO) {
        Log.e("RecyclerVIew :: ", albumVO.toString());
    }

    private ArrayList<AlbumVO> getAlbumList() {
        ArrayList<AlbumVO> list_album = new ArrayList<>();
        Gson gson = new Gson();

        try {
            InputStream is = getAssets().open("album.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("album");

            int index = 0;

            while (index < jsonArray.length()) {
                AlbumVO albumVO = gson.fromJson(jsonArray.get(index).toString(), AlbumVO.class);
                list_album.add(albumVO);
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_album;
    }
}