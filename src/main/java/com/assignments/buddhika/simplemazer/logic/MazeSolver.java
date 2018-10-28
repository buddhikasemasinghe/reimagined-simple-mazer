package com.assignments.buddhika.simplemazer.logic;

import com.assignments.buddhika.simplemazer.exception.MazeSolverServiceException;
import com.assignments.buddhika.simplemazer.model.MazeBoard;
import com.assignments.buddhika.simplemazer.model.MazeCell;

import java.util.List;

public interface MazeSolver {
    List<MazeCell> findPossibleRoutes(final MazeBoard mazeBoard) throws MazeSolverServiceException;
}
