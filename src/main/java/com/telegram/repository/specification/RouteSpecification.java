package com.telegram.repository.specification;

import com.telegram.model.Color;
import com.telegram.model.Route;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public final class RouteSpecification {
  private RouteSpecification() {
  }

  public static Specification<Route> findByColor(Color color) {
    return (root, query, builder) -> builder.equal(root.get("color"), color);
  }

  public static Specification<Route> findByUserId(Long userId) {
    return (root, query, builder) -> builder.equal(root.get("user_id"), userId);
  }

  public static Specification<Route> findByCreated(LocalDate created) {
    return (root, query, builder) -> builder.between(root.get("created"), created, created);
  }
}
