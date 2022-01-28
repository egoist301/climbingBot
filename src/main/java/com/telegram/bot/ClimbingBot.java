package com.telegram.bot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class ClimbingBot extends TelegramLongPollingBot {
  @Value("${telegrambot.token}")
  private String token;
  @Value("${telegrambot.username}")
  private String botUserName;

  private final TelegramFacade telegramFacade;
  private static final Logger logger = LogManager.getLogger(ClimbingBot.class);

  @Autowired
  public ClimbingBot(TelegramFacade telegramFacade) {
    this.telegramFacade = telegramFacade;
  }

  @Override
  public void onUpdateReceived(Update update) {
    try {
      execute(telegramFacade.handleUpdate(update));
    } catch (TelegramApiException e) {
      logger.error("Error during sending message.", e);
    }
  }

  @Override
  public String getBotUsername() {
    return botUserName;
  }

  @Override
  public String getBotToken() {
    return token;
  }
}
