package com.telegram.bot.command.factory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.telegram.bot.command.Command;
import com.telegram.bot.command.CommandType;
import com.telegram.bot.command.impl.CancelCommand;
import com.telegram.bot.command.impl.view.GetAllByColorCommand;
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
import com.telegram.bot.command.impl.work.WorkWithRouteCommand;
import com.telegram.service.RouteService;
import com.telegram.service.builder.RouteMessageBuilder;

@Component
public class CommandFactoryImpl implements CommandFactory {
  private static final Map<CommandType, Command> COMMANDS = new HashMap();
  private final RouteService routeService;
  private final RouteMessageBuilder routeMessageBuilder;

  public CommandFactoryImpl(RouteService routeService, RouteMessageBuilder routeMessageBuilder) {
    this.routeService = routeService;
    this.routeMessageBuilder = routeMessageBuilder;
  }

  @Override
  public Command getCommand(String command) {
    if (COMMANDS.isEmpty()) {
      COMMANDS.put(CommandType.VIEW_ROUTES, new ViewRoutesCommand());
      COMMANDS.put(CommandType.GET_ALL, new GetAllCommand(routeService, routeMessageBuilder));
      //COMMANDS.put(CommandType.GET_ALL_BY_DATE, new GetAllByDateCommand());
      COMMANDS.put(CommandType.GET_ALL_BY_COLOR, new GetAllByColorCommand());
      COMMANDS.put(CommandType.GET_ALL_BY_WHITE, new GetAllByWhiteCommand(routeService, routeMessageBuilder));
      COMMANDS.put(CommandType.GET_ALL_BY_YELLOW, new GetAllByYellowCommand(routeService, routeMessageBuilder));
      COMMANDS.put(CommandType.GET_ALL_BY_ORANGE, new GetAllByOrangeCommand(routeService, routeMessageBuilder));
      COMMANDS.put(CommandType.GET_ALL_BY_GREEN, new GetAllByGreenCommand(routeService, routeMessageBuilder));
      COMMANDS.put(CommandType.GET_ALL_BY_BLUE, new GetAllByBlueCommand(routeService, routeMessageBuilder));
      COMMANDS.put(CommandType.GET_ALL_BY_RED, new GetAllByRedCommand(routeService, routeMessageBuilder));
      COMMANDS.put(CommandType.GET_ALL_BY_PURPLE, new GetAllByPurpleCommand(routeService, routeMessageBuilder));
      COMMANDS.put(CommandType.WORK_WITH_ROUTE, new WorkWithRouteCommand());
      COMMANDS.put(CommandType.ADD, new AddCommand());
      //COMMANDS.put(CommandType.EDIT, new EditCommand());
      //COMMANDS.put(CommandType.DELETE, new DeleteCommand());
      COMMANDS.put(CommandType.CANCEL, new CancelCommand());
    }
    return COMMANDS.get(CommandType.valueOf(command));
  }
}
