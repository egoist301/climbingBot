package com.telegram.model;

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
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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
  @Column(name = "description")
  private String description;
  @Column(name = "count")
  private Integer count;
  @Column(name = "created")
  private LocalDate created;
  @Column(name = "modified")
  private LocalDate modified;
  @ManyToOne(cascade = CascadeType.ALL)
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
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
           Objects.equals(description, route.description) && Objects.equals(count, route.count) &&
           Objects.equals(created, route.created) && Objects.equals(modified, route.modified) &&
           Objects.equals(user, route.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, color, description, count, created, modified, user);
  }

  @Override
  public String toString() {
    return "Route{" +
           "id=" + id +
           ", color=" + color +
           ", description='" + description + '\'' +
           ", count=" + count +
           ", created=" + created +
           ", modified=" + modified +
           ", user=" + user +
           '}';
  }
}
