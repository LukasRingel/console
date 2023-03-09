package de.lukasringel.console.command

interface Command {
  /**
   * Implement your command here
   */
  fun execute(args: Array<String>)

  /**
   * This method returns the command description
   * or null if the command does not have one
   * We use this to get the command name and description
   */
  fun commandDescription(): CommandDescription? {
    return this::class.java.getAnnotation(CommandDescription::class.java)
  }
}