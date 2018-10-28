package com.assignments.buddhika.simplemazer.cli;

import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellOption;


@ShellComponent
public class MazeSolverCommandProcessor {

    @ShellMethod ("Choose  ")
    public String choose(
            @ShellOption int option
    ) {
        return "You have chosen: to run as ";
    }

    @ShellMethod ("Enter Maze Size ( as maze-size 4 5).")
    public String enterSize(
            @ShellOption int width,
            @ShellOption int height
    ) {
        return "You have entered: "+ width +" and "+ height;
    }
}
