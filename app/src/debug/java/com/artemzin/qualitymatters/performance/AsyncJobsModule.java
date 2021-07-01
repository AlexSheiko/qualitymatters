package com.artemzin.qualitymatters.performance;

import androidx.annotation.NonNull;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AsyncJobsModule {

    @Provides
    @NonNull
    @Singleton
    public AsyncJobsObserver provideAsyncJobsObserver() {
        return new AsyncJobsObserverImpl();
    }
}
