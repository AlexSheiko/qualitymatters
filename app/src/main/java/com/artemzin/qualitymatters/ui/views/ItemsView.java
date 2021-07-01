package com.artemzin.qualitymatters.ui.views;

import androidx.annotation.NonNull;
import com.artemzin.qualitymatters.api.entities.Item;
import com.artemzin.qualitymatters.performance.AnyThread;

import java.util.List;

/**
 * Main purpose of such interfaces — hide details of View implementation,
 * such as hundred methods of {@link androidx.fragment.app.Fragment}.
 */
public interface ItemsView {

    // Presenter does not know about Main Thread. It's a detail of View implementation!
    @AnyThread
    void showLoadingUi();

    @AnyThread
    void showErrorUi(@NonNull Throwable error);

    @AnyThread
    void showContentUi(@NonNull List<Item> items);
}
