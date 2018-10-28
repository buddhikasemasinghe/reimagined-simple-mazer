package com.assignments.buddhika.simplemazer.cli;

import com.assignments.buddhika.simplemazer.service.MazeSolverCommandLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;


@ShellComponent
public class MazeSolverCommandProcessor {

    @Autowired
    MazeSolverCommandLineService mazeSolverCommandLineService;

    @ShellMethod ("Enter directory path to choose a file - default /tmp/sample")
    public String chooseFolder(
            @ShellOption(defaultValue = ".") String filePath
    ) {
        return String.join("\r\n", mazeSolverCommandLineService.listMazeInputs(filePath));
    }

    @ShellMethod ("Enter path of the maze input file")
    public String chooseFile(
            @ShellOption(defaultValue = "/tmp/sample/sample1.txt") String filePath
    ) {
        return mazeSolverCommandLineService.processFile(filePath);
    }
}
