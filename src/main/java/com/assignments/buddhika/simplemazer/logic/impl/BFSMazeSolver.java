package com.assignments.buddhika.simplemazer.logic.impl;

import com.assignments.buddhika.simplemazer.common.MazeBoard;
import com.assignments.buddhika.simplemazer.exception.MazeSolverServiceException;
import com.assignments.buddhika.simplemazer.logic.MazeSolver;
import com.assignments.buddhika.simplemazer.model.CellType;
import com.assignments.buddhika.simplemazer.model.MazeCell;
import com.assignments.buddhika.simplemazer.model.Movement;
import com.assignments.buddhika.simplemazer.model.VisitStatus;
import com.assignments.buddhika.simplemazer.util.MazeBoardUtil;
import javafx.scene.control.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

public class BFSMazeSolver implements MazeSolver {
    private MazeBoardUtil mazeBoardUtil;
    private MazeBoard mazeBoard;

    public BFSMazeSolver(final MazeBoard mazeBoard, final MazeBoardUtil mazeBoardUtil){
        this.mazeBoard = mazeBoard;
        this.mazeBoardUtil = mazeBoardUtil;
    }

    @Override
    public List<MazeCell> solveAndFindRoute() throws MazeSolverServiceException {
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
                this.mazeBoard.markCellVisitStatus(currentCell.cellCoordinate(), VisitStatus.VISITED);
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

    @Override
    public String printMazePath(final List<MazeCell> path) {
        CellType[][] clonedMazeBoard = Arrays.stream(this.mazeBoard.getMazeCell()).map(cellTypes -> Arrays.copyOf(cellTypes, cellTypes.length)).
                toArray(CellType[][]::new);

        for(MazeCell mazeCell: path){
            CellType cellType = this.mazeBoardUtil.findCellType(mazeCell);
            if(cellType == CellType.START || cellType == CellType.FINISH) {
                continue;
            }
            clonedMazeBoard[mazeCell.cellCoordinate().x()][mazeCell.cellCoordinate().y()] = CellType.ROUTE;
        }
        StringJoiner displayText = new StringJoiner("");
        for(int rowCount = 0; rowCount < this.mazeBoard.getWidth(); rowCount++){
            for(int colCount = 0; colCount < this.mazeBoard.getHeight(); colCount++){
                CellType cellType = clonedMazeBoard[rowCount][colCount];
                switch (cellType){
                    case START:
                        displayText.add(CellType.START.getCharacterType()+"");
                        break;
                    case FINISH:
                        displayText.add(CellType.FINISH.getCharacterType()+"");
                        break;
                    case EMPTY:
                        displayText.add(CellType.EMPTY.getCharacterType()+"");
                        break;
                    case ROUTE:
                        displayText.add(CellType.ROUTE.getCharacterType()+"");
                        break;
                    case WALL:
                        displayText.add(CellType.WALL.getCharacterType()+"");
                        break;
                        default:
                            break;
                }
            }
            displayText.add("\r\n");

        }
        return displayText.toString();
    }
}
