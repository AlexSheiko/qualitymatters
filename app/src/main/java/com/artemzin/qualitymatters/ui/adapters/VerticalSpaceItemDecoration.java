package com.artemzin.qualitymatters.ui.adapters;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int verticalSpacePx;

    public VerticalSpaceItemDecoration(int verticalSpacePx) {
        this.verticalSpacePx = verticalSpacePx;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = verticalSpacePx;
        outRect.bottom = verticalSpacePx;
    }
}
