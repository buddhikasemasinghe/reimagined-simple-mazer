package com.assignments.buddhika.simplemazer.common;

import com.assignments.buddhika.simplemazer.model.CellCoordinate;
import com.assignments.buddhika.simplemazer.model.CellType;
import com.assignments.buddhika.simplemazer.model.MazeCell;
import com.assignments.buddhika.simplemazer.model.VisitStatus;
import lombok.Getter;
import org.springframework.stereotype.Component;


@Component
public class MazeBoard {

    @Getter
    private CellType[][] mazeCell;
    @Getter
    private VisitStatus[][] visitStatus;
    @Getter
    private MazeCell startCell;
    @Getter
    private MazeCell endCell;
    @Getter
    private int width;
    @Getter int height;

    /**
     *
     * @param width  Maximum x value
     * @param length Maximum y value
     */
    public void init(final int width, int length){
        this.height = length;
        this.width = width;
        this.mazeCell = new CellType[width][length];
        this.visitStatus = new VisitStatus[width][length];
    }

    public void markCellVisitStatus(final CellCoordinate coordinate, final VisitStatus visitStatus){
        this.visitStatus[coordinate.x()][coordinate.y()] = visitStatus;
    }

    public void markCellType(final CellCoordinate cellCoordinate, final CellType cellType){
        this.mazeCell[cellCoordinate.x()][cellCoordinate.y()] = cellType;
    }
}
