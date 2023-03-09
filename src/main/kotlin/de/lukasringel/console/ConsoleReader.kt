package de.lukasringel.console

import org.jline.reader.LineReaderBuilder
import org.jline.terminal.Terminal
import java.util.function.Consumer

class ConsoleReader private constructor(private val terminal: Terminal, private val inputFound: Consumer<String>) :
  Runnable {

  /**
   * This variable is used to stop the console reader
   * by setting it to false
   */
  private var listening = true;

  /**
   * This method starts the console reader
   */
  override fun run() {
    // create a line reader
    val lineReader = LineReaderBuilder.builder()
      .terminal(terminal)
      .build()

    // read lines until listening is false
    while (listening) {
      // read the console line
      val line = lineReader.readLine()

      // if we found something, we call the inputFound consumer
      if (line != null) {
        inputFound.accept(line)
      }
    }
  }

  /**
   * This method stops the console reader
   * by setting listening to false
   */
  fun stop() {
    listening = false
  }

  companion object {
    /**
     * This method creates a new console reader
     * and starts it in a new thread
     *
     * @param terminal - the terminal we use to read from
     * @param inputFound - the consumer we call when we found an input
     */
    fun start(terminal: Terminal, inputFound: Consumer<String>): ConsoleReader {
      val consoleReader = ConsoleReader(terminal, inputFound)
      Thread(consoleReader).start()
      return consoleReader
    }
  }
}