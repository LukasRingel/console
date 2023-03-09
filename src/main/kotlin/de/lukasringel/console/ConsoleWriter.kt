package de.lukasringel.console

import org.jline.terminal.Terminal
import java.io.PrintStream
import java.time.Clock
import java.time.format.DateTimeFormatter
import java.util.logging.Level

class ConsoleWriter(private val terminal: Terminal, private val clock: Clock) {
  /**
   * We use this formatter to format the date
   * for better readability in the console
   */
  private val dateFormatter = DateTimeFormatter
    .ofPattern("HH:mm:ss.SSS")
    .withZone(clock.zone)

  init {
    // we need to set the output stream to the terminal
    System.setErr(PrintStream(terminal.output()))
    System.setOut(PrintStream(terminal.output()))
  }

  /**
   * This method formats the message and writes it to the console
   *
   * @param level - the level of the message
   * @param message - the message itself
   */
  fun write(level: Level, message: String) {
    writeToTerminal(formatMessage(level, message))
  }

  /**
   * This method writes a message to the console
   *
   * @param message - the message itself
   */
  private fun writeToTerminal(message: String) {
    terminal.writer().println(message)
    terminal.writer().flush()
  }

  /**
   * This method formats the message for better readability
   * Format: [LEVEL] DATE - MESSAGE
   *
   * @param level - the level of the message
   * @param message - the message itself
   */
  private fun formatMessage(level: Level, message: String): String {
    return "[${level.name}] ${dateFormatter.format(clock.instant())} - $message"
  }
}