package com.blq.zzc.project;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.blq.zzc.project.List.ViewHolderAdapter;
import com.blq.zzc.project.View.MyListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initList();
        initFloadButton();
    }
    private FloatingActionButton floatingActionButton;
    private FloatingActionButton floatingActionButton2;
    private int count;
    private void initFloadButton() {
        floatingActionButton= (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                viewHolderAdapter.notifyDataSetInvalidated();
                listView.smoothScrollToPosition(1);
            }
        });
        floatingActionButton2= (FloatingActionButton) findViewById(R.id.floatingActionButton_add);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count=list.size()+1;
                list.add(String.valueOf(count));
                viewHolderAdapter.notifyDataSetInvalidated();
                listView.smoothScrollToPosition(count);
            }
        });
    }

    private MyListView listView;
    private List<String>list=new ArrayList<>();
    private ViewHolderAdapter viewHolderAdapter;
    private void initList() {
        for (int i=0;i<=99;i++){
            list.add("1");
        }
        viewHolderAdapter=new ViewHolderAdapter(list,this);
        listView= (MyListView) findViewById(R.id.listView);
        listView.setAdapter(viewHolderAdapter);
        listView.setSelection(99);
        listView.setEmptyView(findViewById(R.id.imageView_empty));
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:{
                        System.out.println("ACTION_DOWN");
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        System.out.println("ACTION_UP");
                        break;
                    }
                    case MotionEvent.ACTION_MOVE:{
                        System.out.println("ACTION_MOVE");
                        break;
                    }
                }
                return false;
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int i) {
                switch (i){
                    case SCROLL_STATE_TOUCH_SCROLL:{
                        System.out.println("SCROLL_STATE_TOUCH_SCROLL");
                        break;
                    }
                    case SCROLL_STATE_IDLE:{
                        System.out.println("SCROLL_STATE_IDLE");
                        break;
                    }
                    case SCROLL_STATE_FLING:{
                        System.out.println("SCROLL_STATE_FLING");
                        break;
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int i, int i1, int i2) {
                
            }
        });
    }
}
