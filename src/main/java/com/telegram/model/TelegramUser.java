package com.telegram.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

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
  @OneToMany(mappedBy = "user")
  private Set<Route> routes;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getChatId() {
    return chatId;
  }

  public void setChatId(Long chatId) {
    this.chatId = chatId;
  }

  public Set<Route> getRoutes() {
    return routes;
  }

  public void setRoutes(Set<Route> routes) {
    this.routes = routes;
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
    TelegramUser user = (TelegramUser) o;
    return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) &&
           Objects.equals(lastName, user.lastName) && Objects.equals(userName, user.userName) &&
           Objects.equals(chatId, user.chatId) && Objects.equals(routes, user.routes) &&
           Objects.equals(created, user.created) && Objects.equals(modified, user.modified);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, userName, chatId, routes, created, modified);
  }

  @Override
  public String toString() {
    return "User{" +
           "id=" + id +
           ", firstName='" + firstName + '\'' +
           ", lastName='" + lastName + '\'' +
           ", userName='" + userName + '\'' +
           ", chatId=" + chatId +
           ", routes=" + routes +
           '}';
  }
}
