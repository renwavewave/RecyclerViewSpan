package com.wavewave.recyclerviewspan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.wavewave.recyclerviewspan.adapter.TextAdapter;
import com.wavewave.recyclerviewspan.bean.ContentBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<ContentBean> contentBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.mRecyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

        contentBeanList.add(new ContentBean(1, "标题1(单选)"));
        contentBeanList.add(new ContentBean(2, "内容1", "标题1(单选)"));
        contentBeanList.add(new ContentBean(2, "内容2", "标题1(单选)"));
        contentBeanList.add(new ContentBean(2, "内容3", "标题1(单选)"));
        contentBeanList.add(new ContentBean(2, "内容4", "标题1(单选)"));


        contentBeanList.add(new ContentBean(1, "标题2(单选)"));
        contentBeanList.add(new ContentBean(2, "内容1", "标题2(单选)"));
        contentBeanList.add(new ContentBean(2, "内容2", "标题2(单选)"));
        contentBeanList.add(new ContentBean(2, "内容3", "标题2(单选)"));
        contentBeanList.add(new ContentBean(2, "内容4", "标题2(单选)"));
        contentBeanList.add(new ContentBean(2, "内容5", "标题2(单选)"));
        contentBeanList.add(new ContentBean(2, "内容6", "标题2(单选)"));

        contentBeanList.add(new ContentBean(1, "标题3(多选)"));
        contentBeanList.add(new ContentBean(3, "内容1", "标题3(多选)"));
        contentBeanList.add(new ContentBean(3, "内容2", "标题3(多选)"));
        contentBeanList.add(new ContentBean(3, "内容3", "标题3(多选)"));
        contentBeanList.add(new ContentBean(3, "内容4", "标题3(多选)"));
        contentBeanList.add(new ContentBean(3, "内容5", "标题3(多选)"));
        contentBeanList.add(new ContentBean(3, "内容6", "标题3(多选)"));
        contentBeanList.add(new ContentBean(3, "内容7", "标题3(多选)"));
        contentBeanList.add(new ContentBean(3, "内容8", "标题3(多选)"));


        final TextAdapter textAdapter = new TextAdapter(contentBeanList);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (textAdapter.getItemViewType(position)) {
                    // 返回的宽度为3， item满屏
                    case 1:
                        return 3;
                    case 2:
                        return 1;
                    case 3:
                        return 1;
                }
                return 0;
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mRecyclerView.setAdapter(textAdapter);
    }
}
