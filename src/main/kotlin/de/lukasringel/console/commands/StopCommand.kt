package de.lukasringel.console.commands

import de.lukasringel.console.Console
import de.lukasringel.console.command.Command
import de.lukasringel.console.command.CommandDescription
import kotlin.system.exitProcess

@CommandDescription("stop", "Stops the server gracefully")
class StopCommand : Command {
  override fun execute(args: Array<String>) {
    Console.log("Going to stop the application...")
    exitProcess(1)
  }
}