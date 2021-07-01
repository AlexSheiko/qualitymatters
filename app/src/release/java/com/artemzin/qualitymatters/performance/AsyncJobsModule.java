package com.artemzin.qualitymatters.performance;

import androidx.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AsyncJobsModule {

    @Provides @NonNull @Singleton
    public AsyncJobsObserver provideAsyncJobsObserver() {
        return new NoOpAsyncJobsObserver();
    }
}
