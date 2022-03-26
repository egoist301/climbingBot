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

import lombok.Data;

@Data
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

  @PrePersist
  private void onCreate() {
    created = LocalDate.now();
    modified = LocalDate.now();
  }

  @PreUpdate
  private void onUpdate() {
    modified = LocalDate.now();
  }
}
