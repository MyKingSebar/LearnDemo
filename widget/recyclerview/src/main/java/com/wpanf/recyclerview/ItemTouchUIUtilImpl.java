/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wpanf.recyclerview;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v7.recyclerview.R.id;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchUIUtil;
import android.view.View;

class ItemTouchUIUtilImpl implements ItemTouchUIUtil {
    static final ItemTouchUIUtil INSTANCE = new ItemTouchUIUtilImpl();

    ItemTouchUIUtilImpl() {
    }

    public void onDraw(Canvas c, RecyclerView recyclerView, View view, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (VERSION.SDK_INT >= 21 && isCurrentlyActive) {
            Object originalElevation = view.getTag(id.item_touch_helper_previous_elevation);
            if (originalElevation == null) {
                Object originalElevation1 = ViewCompat.getElevation(view);
                float newElevation = 1.0F + findMaxElevation(recyclerView, view);
                ViewCompat.setElevation(view, newElevation);
                view.setTag(id.item_touch_helper_previous_elevation, originalElevation1);
            }
        }

        view.setTranslationX(dX);
        view.setTranslationY(dY);
    }

    private static float findMaxElevation(RecyclerView recyclerView, View itemView) {
        int childCount = recyclerView.getChildCount();
        float max = 0.0F;

        for(int i = 0; i < childCount; ++i) {
            View child = recyclerView.getChildAt(i);
            if (child != itemView) {
                float elevation = ViewCompat.getElevation(child);
                if (elevation > max) {
                    max = elevation;
                }
            }
        }

        return max;
    }

    public void onDrawOver(Canvas c, RecyclerView recyclerView, View view, float dX, float dY, int actionState, boolean isCurrentlyActive) {
    }

    public void clearView(View view) {
        if (VERSION.SDK_INT >= 21) {
            Object tag = view.getTag(id.item_touch_helper_previous_elevation);
            if (tag != null && tag instanceof Float) {
                ViewCompat.setElevation(view, (Float)tag);
            }

            view.setTag(id.item_touch_helper_previous_elevation, (Object)null);
        }

        view.setTranslationX(0.0F);
        view.setTranslationY(0.0F);
    }

    public void onSelected(View view) {
    }
}
