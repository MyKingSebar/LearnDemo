package com.wpanf.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by wangpanfeng@nationsky.com on 18/9/27.
 */

public class RAdapter extends RecyclerView.Adapter<RAdapter.VHolder> {

    public static final int ITEM_STRING = 1;

    private Context mContext;

    private List<DataBean> mDatas;

    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    public RAdapter(Context context, List<DataBean> beans) {
        mContext = context;
        mDatas = beans;
        inflater = LayoutInflater.from(context);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RAdapter.VHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        VHolder holder = null;
        switch (viewType) {
            case ITEM_STRING:
                holder = new VHolder(inflater.inflate(R.layout.recyclerview_item_string, null), viewType);
                break;
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(VHolder holder, int position) {
        final DataBean bean = mDatas.get(position);
        switch (bean.getType()) {
            case ITEM_STRING:
                holder.textView.setText(bean.getTitle());
                holder.dataBean = bean;
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getType();
    }

    class VHolder extends com.wpanf.recyclerview.VHolder {

        TextView textView;
        public TextView delete;

        DataBean dataBean;

        public VHolder(View itemView, int type) {
            super(itemView);

            if (null != onItemClickListener) {
                content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onItemClickListener.onItemClick(dataBean);
                    }
                });
            }

            switch (type) {
                case ITEM_STRING:
                    textView = (TextView) itemView.findViewById(R.id.recyclerview_item_string);
                    delete = (TextView) itemView.findViewById(R.id.recyclerview_item_delete);
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(mContext, "delete", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }
        }

        @Override
        public int getMenuWidth() {
            return 300;
        }
    }

    interface OnItemClickListener {
        void onItemClick(DataBean dataBean);
    }

}
