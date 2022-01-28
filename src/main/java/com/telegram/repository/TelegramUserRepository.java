package com.telegram.repository;

import com.telegram.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
  Optional<TelegramUser> findByChatId(Long chatId);
}
