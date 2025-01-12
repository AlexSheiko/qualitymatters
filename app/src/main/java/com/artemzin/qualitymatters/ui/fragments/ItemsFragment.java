package com.artemzin.qualitymatters.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.artemzin.qualitymatters.QualityMattersApp;
import com.artemzin.qualitymatters.R;
import com.artemzin.qualitymatters.api.entities.Item;
import com.artemzin.qualitymatters.models.AnalyticsModel;
import com.artemzin.qualitymatters.models.ItemsModel;
import com.artemzin.qualitymatters.models.QualityMattersImageLoader;
import com.artemzin.qualitymatters.performance.AnyThread;
import com.artemzin.qualitymatters.performance.AsyncJobsObserver;
import com.artemzin.qualitymatters.ui.adapters.ItemsAdapter;
import com.artemzin.qualitymatters.ui.adapters.VerticalSpaceItemDecoration;
import com.artemzin.qualitymatters.ui.presenters.ItemsPresenter;
import com.artemzin.qualitymatters.ui.presenters.ItemsPresenterConfiguration;
import com.artemzin.qualitymatters.ui.views.ItemsView;
import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;
import rx.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static androidx.recyclerview.widget.LinearLayoutManager.VERTICAL;

public class ItemsFragment extends BaseFragment implements ItemsView {
//    @BindView(R.id.items_loading_ui)
    View loadingUiView;

    //    @BindView(R.id.items_loading_error_ui)
    View errorUiView;

    //    @BindView(R.id.items_content_ui)
    RecyclerView contentUiRecyclerView;

    ItemsAdapter itemsAdapter;

    @Inject
    ItemsPresenter itemsPresenter;

    @Inject
    QualityMattersImageLoader networkBitmapClient;

    @SuppressWarnings("NullableProblems")
//    @NonNull
//    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QualityMattersApp.get(getContext()).applicationComponent().plus(new ItemsFragmentModule()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        unbinder = ButterKnife.bind(this, view);
        loadingUiView = view.findViewById(R.id.items_loading_ui);
        errorUiView = view.findViewById(R.id.items_loading_error_ui);
        contentUiRecyclerView = view.findViewById(R.id.items_content_ui);

        contentUiRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), VERTICAL, false));
        contentUiRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration((int) getResources().getDimension(R.dimen.list_item_vertical_space_between_items)));
        itemsAdapter = new ItemsAdapter(getActivity().getLayoutInflater(), networkBitmapClient);
        contentUiRecyclerView.setAdapter(itemsAdapter);
        itemsPresenter.bindView(this);
        itemsPresenter.reloadData();
    }

    @SuppressWarnings("ResourceType")
    // Lint does not understand that we shift execution on Main Thread.
    @Override
    @AnyThread
    public void showLoadingUi() {
        runOnUiThreadIfFragmentAlive(() -> {
            loadingUiView.setVisibility(VISIBLE);
            errorUiView.setVisibility(GONE);
            contentUiRecyclerView.setVisibility(GONE);
        });
    }

    @SuppressWarnings("ResourceType")
    // Lint does not understand that we shift execution on Main Thread.
    @Override
    @AnyThread
    public void showErrorUi(@NonNull Throwable error) {
        runOnUiThreadIfFragmentAlive(() -> {
            loadingUiView.setVisibility(GONE);
            errorUiView.setVisibility(VISIBLE);
            contentUiRecyclerView.setVisibility(GONE);
        });
    }

    @SuppressWarnings("ResourceType")
    // Lint does not understand that we shift execution on Main Thread.
    @Override
    @AnyThread
    public void showContentUi(@NonNull List<Item> items) {
        runOnUiThreadIfFragmentAlive(() -> {
            loadingUiView.setVisibility(GONE);
            errorUiView.setVisibility(GONE);
            contentUiRecyclerView.setVisibility(VISIBLE);

            if (itemsAdapter != null) {
                itemsAdapter.setData(items);
            }
        });
    }

    //    @OnClick(R.id.items_loading_error_try_again_button)
    void onTryAgainButtonClick() {
        itemsPresenter.reloadData();
    }

    @Override
    public void onDestroyView() {
        itemsPresenter.unbindView(this);

//        if (unbinder != null) {
//            unbinder.unbind();
//        }

        super.onDestroyView();
    }

    @Subcomponent(modules = ItemsFragmentModule.class)
    public interface ItemsFragmentComponent {
        void inject(@NonNull ItemsFragment itemsFragment);
    }

    @Module
    public static class ItemsFragmentModule {

        @Provides
        @NonNull
        public ItemsPresenter provideItemsPresenter(@NonNull ItemsModel itemsModel,
                                                    @NonNull AsyncJobsObserver asyncJobsObserver,
                                                    @NonNull AnalyticsModel analyticsModel) {
            return new ItemsPresenter(
                    ItemsPresenterConfiguration.builder().ioScheduler(Schedulers.io()).build(),
                    itemsModel,
                    asyncJobsObserver,
                    analyticsModel
            );
        }
    }
}
