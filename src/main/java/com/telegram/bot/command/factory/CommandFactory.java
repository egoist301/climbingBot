package com.telegram.bot.command.factory;

import com.telegram.bot.command.Command;

@FunctionalInterface
public interface CommandFactory {
  Command getCommand(String command);
}
