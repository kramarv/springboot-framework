package com.gem.matching.config.persistence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RetryListenerLogger extends RetryListenerSupport {

  @Override
  public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback,
      Throwable throwable) {
    log.error("Error detected - Retrying... " + context);
    throwable.printStackTrace();
    super.onError(context, callback, throwable);
  }
}
