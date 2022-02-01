# Overview

This is a command line interpretation of the popular game Wordle. Each round a random word is selected from the `words.txt` word bank and the Wordle board is displayed. The user then attempts to guess the random five-letter word. Green letters indicate a correct letter in the correct spot. Yellow indicates a correct letter in the wrong spot. No highlighting indicates an incorrect letter.

The number of rounds played is recorded in the `history.txt` file. When a round is won that is also recorded along with the winning word is 

This project exists as preparation for a foray into Android App development. I wanted to become more familiar with the Java language and used this Wordle CLI as a springboard project into the world of programming in Java.

[Software Demo Video](http://youtube.link.goes.here)

# Development Environment

I used the Java language with OpenJDK 17.0.2. I used native libraries to accomplish file IO and user input. I also used it for common data structures.

Technology:
* Java
* OpenJDK 17.0.2
* Visual Studio Code
* A Terminal that supports ANSI Escape Codes

Libraries:
* java.io.File
* java.nio.file
* java.util.Scanner
* java.util.ArrayList

# Useful Websites

The following sites helped me get up and running with this project and provided the dictionary words used as part of Wordle.
* [How to Run Java in VS-Code](https://www.youtube.com/watch?v=hEJp98x_MPQ)
* [Open Source English Words](https://github.com/dwyl/english-words/blob/master/words.txt)

# Future Work

* Prune the word bank contained in `words.txt` to remove less common words.
* Migrate from a CLI to a proper GUI.
* Provide feedback to the user about using letters they've already ruled out.
* Add in-game instructions on how to play.