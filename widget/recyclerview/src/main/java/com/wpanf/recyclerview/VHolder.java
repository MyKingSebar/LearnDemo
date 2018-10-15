package com.wpanf.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by wangpanfeng@nationsky.com on 18/10/11.
 */
public abstract class VHolder extends RecyclerView.ViewHolder {

    public abstract int getMenuWidth();

    public RelativeLayout content;
    public LinearLayout menu;

    public VHolder(@NonNull View itemView) {
        super(itemView);

        content = (RelativeLayout) itemView.findViewById(R.id.recyclerview_item_content);
        menu = (LinearLayout) itemView.findViewById(R.id.recyclerview_item_menu);
    }
}
