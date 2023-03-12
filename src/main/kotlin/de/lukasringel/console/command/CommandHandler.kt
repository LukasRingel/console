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
   * This method executes a command if it exists
   */
  fun executeCommand(commandName: String, commandArgs: Array<String>) {
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