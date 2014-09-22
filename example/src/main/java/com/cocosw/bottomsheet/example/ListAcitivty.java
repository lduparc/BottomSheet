package com.cocosw.bottomsheet.example;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import com.cocosw.bottomsheet.BottomSheet;
import com.cocosw.query.CocoQuery;

/**
 * Project: gradle
 * Created by LiaoKai(soarcn) on 2014/9/22.
 */
public class ListAcitivty extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private CocoQuery q;
    private int action;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_main);
        action = getIntent().getFlags();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        q = new CocoQuery(this);
        setTitle(getIntent().getStringExtra("title"));
        String[] items = new String[]{"Janet Perkins","Mary Johnson","Peter Carlsson","Trevor Hansen","Aaron Bennett"};
        q.id(R.id.listView)
                .adapter(adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, items))
                .itemClicked(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        switch (action) {
            case 0:
                new BottomSheet.Builder(this).title("To "+adapter.getItem(position)).sheet(R.menu.list).listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListAcitivty.this.onClick(adapter.getItem(position),which);
                    }
                }).show();
                break;
            case 1:
                new BottomSheet.Builder(this).sheet(R.menu.noicon).listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ListAcitivty.this.onClick(adapter.getItem(position),which);
                    }
                }).show();
                break;
            case 2:

                break;
        }
    }

    public void onClick(String name, int which) {
        switch (which) {
            case R.id.share:
                q.toast("Share to " + name);
                break;
            case R.id.upload:
                q.toast("Upload for " + name);
                break;
            case R.id.call:
                q.toast("Call to " + name);
                break;
            case R.id.help:
                q.toast("Help me!");
                break;
        }
    }
}
