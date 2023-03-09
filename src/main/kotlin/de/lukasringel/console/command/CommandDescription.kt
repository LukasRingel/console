package de.lukasringel.console.command

/**
 * This annotation is used to add a description to a command
 *
 * @param name - the name of the command
 * @param description - the description of the command
 */

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class CommandDescription(val name: String, val description: String)
