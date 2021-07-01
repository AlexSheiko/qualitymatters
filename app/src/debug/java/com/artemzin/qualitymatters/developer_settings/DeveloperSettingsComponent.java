package com.artemzin.qualitymatters.developer_settings;

import androidx.annotation.NonNull;
import com.artemzin.qualitymatters.ui.fragments.DeveloperSettingsFragment;
import dagger.Subcomponent;

@Subcomponent
public interface DeveloperSettingsComponent {
    void inject(@NonNull DeveloperSettingsFragment developerSettingsFragment);
}
