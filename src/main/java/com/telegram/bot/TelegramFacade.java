package com.telegram.bot;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@FunctionalInterface
public interface TelegramFacade {
  SendMessage handleUpdate(Update update);
}
