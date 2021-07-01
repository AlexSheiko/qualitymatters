package com.artemzin.qualitymatters.functional_tests;

import android.support.test.InstrumentationRegistry;
import androidx.annotation.NonNull;
import com.artemzin.qualitymatters.QualityMattersApp;

public class TestUtils {

    private TestUtils() {
        throw new IllegalStateException("No instances, please");
    }

    @NonNull
    public static QualityMattersApp app() {
        return (QualityMattersApp) InstrumentationRegistry.getTargetContext().getApplicationContext();
    }
}
