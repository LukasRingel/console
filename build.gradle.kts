plugins {
  kotlin("jvm") version "1.8.0"
}

group = "de.lukasringel"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jline:jline:3.22.0")
}

kotlin {
  jvmToolchain(17)
}