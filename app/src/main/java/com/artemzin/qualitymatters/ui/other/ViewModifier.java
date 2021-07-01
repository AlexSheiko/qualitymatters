package com.artemzin.qualitymatters.ui.other;

import android.view.View;
import androidx.annotation.NonNull;

/**
 * Simple function that modifies a {@link View} and returns modified one so the consumer should use modifier version.
 */
public interface ViewModifier {

    @NonNull
    <T extends View> T modify(@NonNull T view);
}
