package com.artemzin.qualitymatters.other;

import androidx.annotation.NonNull;
import rx.Subscription;
import rx.functions.Action0;

import java.util.concurrent.atomic.AtomicBoolean;

public class DisposableSubscription implements Subscription {

    @NonNull
    private final AtomicBoolean unsubscribed = new AtomicBoolean(false);

    @NonNull
    private final Action0 disposeAction;

    public DisposableSubscription(@NonNull Action0 disposeAction) {
        this.disposeAction = disposeAction;
    }

    @Override
    public boolean isUnsubscribed() {
        return unsubscribed.get();
    }

    @Override
    public void unsubscribe() {
        if (unsubscribed.compareAndSet(false, true)) {
            disposeAction.call();
        }
    }
}
