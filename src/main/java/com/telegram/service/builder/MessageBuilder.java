package com.telegram.service.builder;

import java.util.Collection;

@FunctionalInterface
public interface MessageBuilder<T> {
  String buildMessage(Collection<T> collection);
}
