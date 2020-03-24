package com.wavewave.recyclerviewspan.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wavewave.recyclerviewspan.R;
import com.wavewave.recyclerviewspan.bean.ContentBean;
import com.wavewave.recyclerviewspan.bean.DrawerStateResponse;

import java.util.HashMap;
import java.util.List;

/**
 * @author wavewave
 * @CreateDate: 2020-03-24 11:10
 * @Description:
 * @Version: 1.0
 */
public class TextAdapter extends RecyclerView.Adapter<TextAdapter.TextViewHolder> {
    private List<ContentBean> contentBeans;

    private HashMap<String, DrawerStateResponse<ContentBean>> drawerHashMap = new HashMap<>();

    public TextAdapter(List<ContentBean> contentBeans) {
        this.contentBeans = contentBeans;
    }

    @Override
    public int getItemViewType(int position) {
        if (contentBeans.get(position) != null) {
            return contentBeans.get(position).type;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_text, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final TextViewHolder holder, final int position) {
        if (holder != null) {
            switch (contentBeans.get(position).type) {
                case 1://标题
                    //在标题这记录
                    holder.mTextTitle.setTextColor(Color.parseColor("#748098"));
                    holder.mTextTitle.setBackgroundColor(Color.parseColor("#F8F8F8"));
                    drawerHashMap.put(contentBeans.get(position).content, new DrawerStateResponse<ContentBean>());
                    holder.mTextTitle.setBackgroundColor(Color.WHITE);
                    holder.mTextTitle.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                    break;
                case 2://单选
                    if (contentBeans.get(position).isSelect) {
                        holder.mTextTitle.setTextColor(Color.parseColor("#FFFFFF"));
                        holder.mTextTitle.setBackgroundColor(Color.parseColor("#62A5FE"));

                        DrawerStateResponse<ContentBean> drawerView = drawerHashMap.get(contentBeans.get(position).isTitle);
                        if (drawerView == null) {
                            drawerView = new DrawerStateResponse<>();
                        }
                        drawerView.oldData = contentBeans.get(position);
                        drawerView.oldTextView = holder.mTextTitle;

                    } else {
                        holder.mTextTitle.setTextColor(Color.parseColor("#748098"));
                        holder.mTextTitle.setBackgroundColor(Color.parseColor("#F8F8F8"));
                    }
                    holder.mTextTitle.setGravity(Gravity.CENTER);
                    holder.mTextTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DrawerStateResponse<ContentBean> drawerView = drawerHashMap.get(contentBeans.get(position).isTitle);
                            if (drawerView == null) {
                                drawerView = new DrawerStateResponse<>();
                            }
                            if (drawerView.oldData != null && drawerView.oldTextView != null) {
                                drawerView.oldData.isSelect = false;
                                drawerView.oldTextView.setTextColor(Color.parseColor("#748098"));
                                drawerView.oldTextView.setBackgroundColor(Color.parseColor("#F8F8F8"));
                            }
                            contentBeans.get(position).isSelect = true;

                            holder.mTextTitle.setTextColor(Color.parseColor("#FFFFFF"));
                            holder.mTextTitle.setBackgroundColor(Color.parseColor("#62A5FE"));

                            drawerView.oldData = contentBeans.get(position);
                            drawerView.oldTextView = holder.mTextTitle;
                            Log.d("RecyclerView", "选择了:" + contentBeans.get(position).content);
                        }
                    });
                    break;
                case 3://多选
                    if (contentBeans.get(position).isSelect) {
                        holder.mTextTitle.setTextColor(Color.parseColor("#FFFFFF"));
                        holder.mTextTitle.setBackgroundColor(Color.parseColor("#62A5FE"));
                    } else {
                        holder.mTextTitle.setTextColor(Color.parseColor("#748098"));
                        holder.mTextTitle.setBackgroundColor(Color.parseColor("#F8F8F8"));
                    }
                    holder.mTextTitle.setGravity(Gravity.CENTER);
                    holder.mTextTitle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            contentBeans.get(position).isSelect = !contentBeans.get(position).isSelect;
                            if (contentBeans.get(position).isSelect) {
                                holder.mTextTitle.setTextColor(Color.parseColor("#FFFFFF"));
                                holder.mTextTitle.setBackgroundColor(Color.parseColor("#62A5FE"));
                            } else {
                                holder.mTextTitle.setTextColor(Color.parseColor("#748098"));
                                holder.mTextTitle.setBackgroundColor(Color.parseColor("#F8F8F8"));
                            }
                            Log.d("RecyclerView", "多选:" + contentBeans.get(position).content + " 选中？" + contentBeans.get(position).isSelect);
                        }
                    });
                    break;
            }
            holder.mTextTitle.setText(contentBeans.get(position).content);
        }
    }

    @Override
    public int getItemCount() {
        return contentBeans == null ? 0 : contentBeans.size();
    }

    class TextViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTextTitle;

        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.mTextTitle);
        }
    }
}
