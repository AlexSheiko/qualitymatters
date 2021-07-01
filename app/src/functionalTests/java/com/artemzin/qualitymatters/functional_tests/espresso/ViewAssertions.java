package com.artemzin.qualitymatters.functional_tests.espresso;

import android.support.test.espresso.ViewAssertion;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewAssertions {

    private ViewAssertions() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static ViewAssertion recyclerViewShouldHaveItemsCount(int count) {
        return (view, noViewFoundException) -> {
            final RecyclerView recyclerView = (RecyclerView) view;
            final int actualCount = recyclerView.getAdapter().getItemCount();

            if (actualCount != count) {
                throw new AssertionError("RecyclerView has " + actualCount + " while expected " + count);
            }
        };
    }
}
