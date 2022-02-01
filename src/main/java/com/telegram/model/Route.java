package com.telegram.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name = "route")
public class Route implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @Column(name = "color")
  @Enumerated(EnumType.STRING)
  public Color color;
  @Column(name = "attempt")
  private String attempt;
  @Column(name = "created")
  private LocalDate created;
  @Column(name = "modified")
  private LocalDate modified;
  @ManyToOne(cascade = CascadeType.MERGE)
  private TelegramUser user;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public String getAttempt() {
    return attempt;
  }

  public void setAttempt(String attempt) {
    this.attempt = attempt;
  }

  public LocalDate getCreated() {
    return created;
  }

  public void setCreated(LocalDate created) {
    this.created = created;
  }

  public LocalDate getModified() {
    return modified;
  }

  public void setModified(LocalDate modified) {
    this.modified = modified;
  }

  public TelegramUser getUser() {
    return user;
  }

  public void setUser(TelegramUser user) {
    this.user = user;
  }

  @PrePersist
  private void onCreate() {
    created = LocalDate.now();
    modified = LocalDate.now();
  }

  @PreUpdate
  private void onUpdate() {
    modified = LocalDate.now();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Route route = (Route) o;
    return Objects.equals(id, route.id) && color == route.color &&
        Objects.equals(attempt, route.attempt) && Objects.equals(created, route.created) &&
        Objects.equals(modified, route.modified) && Objects.equals(user, route.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, color, attempt, created, modified, user);
  }
}
