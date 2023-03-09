package de.lukasringel.console.commands

import de.lukasringel.console.Console
import de.lukasringel.console.command.Command
import de.lukasringel.console.command.CommandDescription

@CommandDescription("help", "Shows all available commands")
class HelpCommand : Command {
  override fun execute(args: Array<String>) {
    Console.commandHandler()?.getCommands()?.forEach(action = {
      Console.log(
        "${it.value.commandDescription()?.name}: " +
          "${it.value.commandDescription()?.description}"
      )
    })
  }
}