package de.lukasringel.console.command

class CommandHandler {
  /**
   * This map contains all registered commands
   * The key is the command name and the value is the command itself
   */
  private val commands = hashMapOf<String, Command>()

  /**
   * This method registers a command
   * We receive the command key from the CommandDescription annotation
   *
   * @param command - the command to register
   */
  fun registerCommand(command: Command) {
    command.commandDescription().let { commandDescription ->
      if (commandDescription == null) {
        throw IllegalArgumentException(
          "Command ${command.javaClass.name} does " +
            "not have a CommandDescription annotation"
        )
      }
      commands[command.commandDescription()?.name ?: "error"] = command
    }
  }

  /**
   * This method executes a command
   *
   * We split the command by spaces and use the first part as the command name
   * and the rest as the command arguments
   *
   * We then execute the command if it exists
   */
  fun executeCommand(command: String) {
    val args = command.split(" ")
    val commandName = args[0]
    val commandArgs = args.drop(1).toTypedArray()
    commands[commandName]?.execute(commandArgs)
  }

  /**
   * This method checks if a command exists
   *
   * @param command - the command to check
   */
  fun exists(command: String): Boolean {
    return commands.containsKey(command)
  }

  /**
   * This method returns all registered commands
   */
  fun getCommands(): Map<String, Command> {
    return commands
  }
}