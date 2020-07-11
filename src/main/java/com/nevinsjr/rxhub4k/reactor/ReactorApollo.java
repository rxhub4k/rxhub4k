package com.nevinsjr.rxhub4k.reactor;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;
import io.reactivex.rxjava3.annotations.CheckReturnValue;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;
import static com.apollographql.apollo.api.internal.Utils.checkNotNull;

/**
 * API for conversion of Apollo calls to Reactor types.  Follows
 * the conventions of the apollo-android library.
 */
public class ReactorApollo {
    private ReactorApollo() {
        throw new AssertionError("This class cannot be instantiated");
    }

    @NotNull
    @CheckReturnValue
    public static <T> Flux<Response<T>> from(@NotNull final ApolloCall<T> call) {
        checkNotNull(call, "call == null");

        return Flux.create(sink -> call.enqueue(new ApolloCall.Callback<T>() {
            @Override
            public void onResponse(@NotNull Response<T> response) {
                sink.next(response);
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {
                sink.error(e);
            }

            @Override
            public void onStatusEvent(@NotNull ApolloCall.StatusEvent event) {
                if (event == ApolloCall.StatusEvent.COMPLETED && !sink.isCancelled()) {
                    sink.complete();
                }
            }
        }));
    }
}
