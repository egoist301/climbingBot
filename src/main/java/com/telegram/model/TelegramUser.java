package com.telegram.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class TelegramUser implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;
  @Column(name = "first_name", length = 20)
  private String firstName;
  @Column(name = "last_name", length = 20)
  private String lastName;
  @Column(name = "username", length = 20)
  private String userName;
  @Column(name = "chat_id")
  private Long chatId;
  @Column(name = "created")
  private LocalDate created;
  @Column(name = "modified")
  private LocalDate modified;
  @Column(name = "state")
  @Enumerated(EnumType.STRING)
  private BotState botState;
  @OneToMany(mappedBy = "user")
  private Set<Route> routes;

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
