package com.wpanf.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.wpanf.base.BaseActivity;
import com.wpanf.recyclerview.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> {

    private RecyclerView recyclerView;
    private RAdapter adapter;
    LoadMoreWrapper loadMoreWrapper;
    private List<DataBean> beans = new ArrayList<>();
    private Handler handler = new Handler();

    @Override
    protected void initPresenter() {
        presenter = new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_recyclerview);

//        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                switch (mAdapter.getItemViewType(position)) {
//                    case HorOrganStructureRecycleViewAdapter.ITEM_VIEW_TYPE_USER:
//                        return 1;
//                    case HorOrganStructureRecycleViewAdapter.ITEM_VIEW_TYPE_HEADER:
//                        return 4;
//                    case HorOrganStructureRecycleViewAdapter.ITEM_VIEW_TYPE_DEPT:
//                        return 4;
//                    default:
//                        return 4;
//                }
//            }
//        });
        adapter = new RAdapter(this, beans);
        adapter.setOnItemClickListener(new RAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataBean bean) {
                Toast.makeText(MainActivity.this, bean.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        loadMoreWrapper = new LoadMoreWrapper(adapter);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                loadMoreWrapper.startLoad();
                presenter.getDatas(handler);
            }
        });
        recyclerView.setAdapter(loadMoreWrapper);

        DefaultItemTouchHelperCallBack defaultItemTouchHelperCallBack = new DefaultItemTouchHelperCallBack();
        defaultItemTouchHelperCallBack.setOnTouchHelperCallBackListener(new DefaultItemTouchHelperCallBack.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {

//                if (beans != null) {
//                    beans.remove(adapterPosition);
//                    adapter.notifyItemRemoved(adapterPosition);
//                }

            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                if (beans != null) {
                    // 更换数据源中的数据Item的位置
                    Collections.swap(beans, srcPosition, targetPosition);
                    // 更新UI中的Item的位置，主要是给用户看到交互效果
                    loadMoreWrapper.notifyItemMoved(srcPosition, targetPosition);
                    return true;
                }

                return false;
            }
        });
        IItemTouchHelper helper = new IItemTouchHelper(defaultItemTouchHelperCallBack);
        helper.attachToRecyclerView(recyclerView);

        presenter.getDatas(handler);
    }

    @Override
    public void dispatchMsg(Message msg) {
        super.dispatchMsg(msg);
        switch (msg.what) {
            case 1:
                beans.addAll((List<DataBean>) msg.obj);
                loadMoreWrapper.completeLoad();
                break;
        }
    }
}
