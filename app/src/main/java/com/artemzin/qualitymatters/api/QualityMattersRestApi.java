package com.artemzin.qualitymatters.api;

import androidx.annotation.NonNull;
import com.artemzin.qualitymatters.api.entities.Item;
import retrofit2.http.GET;
import rx.Single;

import java.util.List;

public interface QualityMattersRestApi {

    @GET("items")
    @NonNull
    Single<List<Item>> items();
}
