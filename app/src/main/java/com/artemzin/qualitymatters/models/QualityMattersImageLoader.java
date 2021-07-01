package com.artemzin.qualitymatters.models;

import android.widget.ImageView;
import androidx.annotation.NonNull;

public interface QualityMattersImageLoader {
    void downloadInto(@NonNull String url, @NonNull ImageView imageView);
}