package com.telegram.repository;

import com.telegram.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {
  TelegramUser findByChatId(Long chatId);
}
