package de.lukasringel.console

import de.lukasringel.console.command.Command
import de.lukasringel.console.command.CommandHandler
import de.lukasringel.console.commands.HelpCommand
import de.lukasringel.console.commands.StopCommand
import org.jline.terminal.Terminal
import org.jline.terminal.TerminalBuilder
import java.nio.charset.StandardCharsets
import java.time.Clock
import java.util.logging.Level

object Console {
  /**
   * This is the terminal we use to write to the console
   * and read from the console
   *
   * We use jansi to support windows
   * We use system to support colors
   * We use UTF-8 as encoding
   */
  private var terminal: Terminal = TerminalBuilder.builder()
    .system(true)
    .jansi(true)
    .encoding(StandardCharsets.UTF_8)
    .build()

  /**
   * This is the command handler we use to handle commands
   */
  private var commandHandler: CommandHandler? = CommandHandler()

  /**
   * This is the console writer we use to write to the console
   */
  private var consoleWriter: ConsoleWriter? = null

  /**
   * This is the console reader we use to read from the console
   */
  private var consoleReader: ConsoleReader? = null


  init {
    // create the console writer and reader
    consoleWriter = ConsoleWriter(terminal, Clock.systemDefaultZone())
    consoleReader = ConsoleReader.start(terminal) { input -> handleCommandInput(input) }

    // register the shutdown hook
    Runtime.getRuntime().addShutdownHook(Thread { stopCommandReading() })

    // register build-in commands
    registerCommand(HelpCommand())
    registerCommand(StopCommand())
  }

  /**
   * Logs an info message to the console
   */
  fun log(message: String) {
    consoleWriter?.write(Level.INFO, message)
  }

  /**
   * Logs an error message to the console
   */
  fun error(message: String) {
    consoleWriter?.write(Level.WARNING, message)
  }

  /**
   * Registers a new command
   */
  fun registerCommand(command: Command) {
    commandHandler?.registerCommand(command)
  }

  /**
   * Stops the console reader
   */
  fun stopCommandReading() {
    consoleReader?.stop()
  }

  /**
   * Just a getter for the command handler
   */
  fun commandHandler(): CommandHandler? {
    return commandHandler
  }

  /**
   * This method executes the provided command
   */
  private fun handleCommandInput(input: String) {
    // check if we have a command handler for the input
    if (commandHandler?.exists(input) == false) {
      error("Command '$input' not found. Type 'help' for a list of all commands.")
      return
    }

    // execute the command
    commandHandler?.executeCommand(input)
  }
}