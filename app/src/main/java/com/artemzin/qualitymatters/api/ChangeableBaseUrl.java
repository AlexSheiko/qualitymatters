package com.artemzin.qualitymatters.api;

import androidx.annotation.NonNull;
import okhttp3.HttpUrl;
import retrofit2.BaseUrl;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Such implementation allows us easily change base url in the integration and functional tests!
 */
public class ChangeableBaseUrl implements BaseUrl {

    @NonNull
    private final AtomicReference<HttpUrl> baseUrl;

    public ChangeableBaseUrl(@NonNull String baseUrl) {
        this.baseUrl = new AtomicReference<>(HttpUrl.parse(baseUrl));
    }

    public void setBaseUrl(@NonNull String baseUrl) {
        this.baseUrl.set(HttpUrl.parse(baseUrl));
    }

    @Override @NonNull
    public HttpUrl url() {
        return baseUrl.get();
    }
}
