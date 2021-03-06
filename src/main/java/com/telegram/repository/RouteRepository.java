package com.telegram.repository;

import com.telegram.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RouteRepository
    extends JpaRepository<Route, Long>, JpaSpecificationExecutor<Route> {
}
