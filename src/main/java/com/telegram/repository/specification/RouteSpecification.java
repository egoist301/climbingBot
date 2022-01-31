package com.telegram.repository.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.telegram.model.Color;
import com.telegram.model.Route;
import com.telegram.model.TelegramUser;

public final class RouteSpecification {
  private RouteSpecification() {
  }

  public static Specification<Route> findByColor(Color color) {
    return (root, query, builder) -> builder.equal(root.get("color"), color);
  }

  public static Specification<Route> findByUserId(TelegramUser user) {
    return (root, query, builder) -> builder.equal(root.get("user"), user);
  }

  public static Specification<Route> findByCreated(LocalDate created) {
    return (root, query, builder) -> builder.between(root.get("created"), created, created);
  }

  public static Specification<Route> exists() {
    return (root, query, builder) -> builder.isNotNull(root.get("id"));
  }
}
