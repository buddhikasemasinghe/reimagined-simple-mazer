package com.assignments.buddhika.simplemazer.logic.impl;

import com.assignments.buddhika.simplemazer.common.MazeBoard;
import com.assignments.buddhika.simplemazer.exception.MazeSolverServiceException;
import com.assignments.buddhika.simplemazer.model.CellType;
import com.assignments.buddhika.simplemazer.model.MazeCell;
import com.assignments.buddhika.simplemazer.util.MazeBoardUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BFSMazeSolverTest {

    @Mock
    private MazeBoard mazeBoard;
    @Mock
    private MazeBoardUtil mazeBoardUtil;

    BFSMazeSolver classToBeTested;

    @BeforeEach
    public void setUp() {
        classToBeTested = new BFSMazeSolver(mazeBoard, mazeBoardUtil);
    }

    @Test
    void whenRouteNotFound_ShouldThrowException() {

        assertThrows(MazeSolverServiceException.class, () ->
                classToBeTested.solveAndFindRoute());
    }

    @Test
    void whenRouteIsFound_shouldBackTrack() throws MazeSolverServiceException {
        when(mazeBoard.getStartCell()).thenReturn(mock(MazeCell.class));
        when(mazeBoardUtil.checkCellLocationValidity(mazeBoard.getStartCell())).thenReturn(true);
        when(mazeBoardUtil.hasItBeenAlreadyVisited(mazeBoard.getStartCell())).thenReturn(false);
        when(mazeBoardUtil.findCellType(mazeBoard.getStartCell())).thenReturn(CellType.FINISH);
        List<MazeCell> mazeCellList = new ArrayList<>();
        when(mazeBoardUtil.findBackTrackedPath(mazeBoard.getStartCell())).thenReturn(mazeCellList);

        Assertions.assertEquals(mazeCellList, classToBeTested.solveAndFindRoute());
    }

}