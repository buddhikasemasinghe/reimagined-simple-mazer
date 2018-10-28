package com.assignments.buddhika.simplemazer.logic.impl;

import com.assignments.buddhika.simplemazer.common.MazeBoard;
import com.assignments.buddhika.simplemazer.exception.MazeSolverServiceException;
import com.assignments.buddhika.simplemazer.logic.MazeSolver;
import com.assignments.buddhika.simplemazer.model.CellType;
import com.assignments.buddhika.simplemazer.model.MazeCell;
import com.assignments.buddhika.simplemazer.model.Movement;
import com.assignments.buddhika.simplemazer.model.VisitStatus;
import com.assignments.buddhika.simplemazer.util.MazeBoardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class BFSMazeSolver implements MazeSolver {
    private MazeBoardUtil mazeBoardUtil;

    public BFSMazeSolver(final MazeBoardUtil mazeBoardUtil){
        this.mazeBoardUtil = mazeBoardUtil;
    }

    @Override
    public List<MazeCell> solveAndFindRoute(MazeBoard mazeBoard) throws MazeSolverServiceException {
        final MazeCell startPoint = mazeBoard.getStartCell();
        LinkedList<MazeCell> cellTobeVisited = new LinkedList<>();
        //Starting the journey
        cellTobeVisited.add(startPoint);
        while(!cellTobeVisited.isEmpty()){
            final MazeCell currentCell = cellTobeVisited.remove();
            if(!mazeBoardUtil.checkCellLocationValidity(currentCell)){
               continue;
            }
            if(mazeBoardUtil.hasItBeenAlreadyVisited(currentCell)){
                continue;
            }
            CellType currentCellType = mazeBoardUtil.findCellType(currentCell);
            if(currentCellType == CellType.WALL){
                mazeBoard.markCellVisitStatus(currentCell.cellCoordinate(), VisitStatus.VISITED);
                continue;
            }

            if(currentCellType == CellType.FINISH){
               return mazeBoardUtil.findBackTrackedPath(currentCell);
            }

            // Check possible routes from current location
            for(Movement movement: Movement.values()){
                MazeCell nextCell = mazeBoardUtil.findLocationAfterMovement(currentCell, movement);
                cellTobeVisited.add(nextCell);
                mazeBoard.markCellVisitStatus(currentCell.cellCoordinate(), VisitStatus.VISITED);
            }

        }
        throw new MazeSolverServiceException("Can not find a route from start to finish");
    }
}
