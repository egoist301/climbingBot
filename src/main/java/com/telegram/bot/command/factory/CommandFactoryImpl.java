package com.telegram.bot.command.factory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.telegram.bot.command.Command;
import com.telegram.bot.command.CommandType;
import com.telegram.bot.command.impl.CancelCommand;
import com.telegram.bot.command.impl.view.GetAllByColorCommand;
import com.telegram.bot.command.impl.view.GetAllByDateCommand;
import com.telegram.bot.command.impl.view.GetAllCommand;
import com.telegram.bot.command.impl.view.ViewRoutesCommand;
import com.telegram.bot.command.impl.view.color.GetAllByBlueCommand;
import com.telegram.bot.command.impl.view.color.GetAllByGreenCommand;
import com.telegram.bot.command.impl.view.color.GetAllByOrangeCommand;
import com.telegram.bot.command.impl.view.color.GetAllByPurpleCommand;
import com.telegram.bot.command.impl.view.color.GetAllByRedCommand;
import com.telegram.bot.command.impl.view.color.GetAllByWhiteCommand;
import com.telegram.bot.command.impl.view.color.GetAllByYellowCommand;
import com.telegram.bot.command.impl.work.AddCommand;
import com.telegram.bot.command.impl.work.DeleteCommand;
import com.telegram.bot.command.impl.work.EditCommand;
import com.telegram.bot.command.impl.work.WorkWithRouteCommand;
import com.telegram.service.RouteService;

@Component
public class CommandFactoryImpl implements CommandFactory {
  private static final Map<CommandType, Command> COMMANDS = new HashMap();
  private final RouteService routeService;

  public CommandFactoryImpl(RouteService routeService) {
    this.routeService = routeService;
  }

  @Override
  public Command getCommand(String command) {
    if (COMMANDS.isEmpty()) {
      COMMANDS.put(CommandType.VIEW_ROUTES, new ViewRoutesCommand());
      COMMANDS.put(CommandType.GET_ALL, new GetAllCommand(routeService));
      //COMMANDS.put(CommandType.GET_ALL_BY_DATE, new GetAllByDateCommand());
      COMMANDS.put(CommandType.GET_ALL_BY_COLOR, new GetAllByColorCommand());
      COMMANDS.put(CommandType.GET_ALL_BY_WHITE, new GetAllByWhiteCommand(routeService));
      COMMANDS.put(CommandType.GET_ALL_BY_YELLOW, new GetAllByYellowCommand(routeService));
      COMMANDS.put(CommandType.GET_ALL_BY_ORANGE, new GetAllByOrangeCommand(routeService));
      COMMANDS.put(CommandType.GET_ALL_BY_GREEN, new GetAllByGreenCommand(routeService));
      COMMANDS.put(CommandType.GET_ALL_BY_BLUE, new GetAllByBlueCommand(routeService));
      COMMANDS.put(CommandType.GET_ALL_BY_RED, new GetAllByRedCommand(routeService));
      COMMANDS.put(CommandType.GET_ALL_BY_PURPLE, new GetAllByPurpleCommand(routeService));
      COMMANDS.put(CommandType.WORK_WITH_ROUTE, new WorkWithRouteCommand());
      COMMANDS.put(CommandType.ADD, new AddCommand());
      //COMMANDS.put(CommandType.EDIT, new EditCommand());
      //COMMANDS.put(CommandType.DELETE, new DeleteCommand());
      COMMANDS.put(CommandType.CANCEL, new CancelCommand());
    }
    return COMMANDS.get(CommandType.valueOf(command));
  }
}
