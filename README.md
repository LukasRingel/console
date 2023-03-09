<br />
<p align="center">
<a href="https://github.com/LukasRingel/console">
    <img src="logo.png" alt="Logo" width="80" height="80">
  </a>
<h3 align="center">console</h3>
<br>
  <p align="center">
    Create console commands and format your console output.
    <br />
    <br />
    <a href="https://github.com/LukasRingel/console/issues">Report Bug</a>
    ·
    <a href="https://github.com/LukasRingel/console/issues">Request Feature</a>
  </p>

## 💻 Built With

* []() <img src="https://www.vectorlogo.zone/logos/kotlin/kotlin-icon.svg" alt="kotlin" width="20" height="20"/> Kotlin
* []() <img src="https://www.vectorlogo.zone/logos/github/github-icon.svg" alt="spring" width="20" height="20"/> jline

## Usage

### Print to the console

```kotlin
// print an info message
Console.log("Hello World")

// print an error message
Console.error("Hello World")
```

### Custom command example

```kotlin
// tell the console which name the command should listen on and describe the command
@CommandDescription("demo", "How to create a command?")
class DemoCommand : Command {

  // overwrite the execute method with your command logic
  override fun execute(args: Array<String>) {
    Console.log("Your command logic goes here")
  }
}
```

### Register a command

```kotlin
// register a command
Console.registerCommand(DemoCommand())
```

## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request