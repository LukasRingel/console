plugins {
  kotlin("jvm") version "1.8.0"
  id("maven-publish")
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

publishing {
  publications {
    create<MavenPublication>("maven") {
      groupId = project.group.toString()
      artifactId = "console"
      version = project.version.toString()

      from(components["java"])
    }
  }
}