package com.assignments.buddhika.simplemazer.common;

import com.assignments.buddhika.simplemazer.model.CellCoordinate;
import com.assignments.buddhika.simplemazer.model.CellType;
import com.assignments.buddhika.simplemazer.model.MazeCell;
import com.assignments.buddhika.simplemazer.model.VisitStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
public class MazeBoard {

    @Getter
    private CellType[][] mazeCell;
    @Getter
    private VisitStatus[][] visitStatus;
    @Getter @Setter
    private MazeCell startCell;
    @Getter @Setter
    private MazeCell endCell;
    @Getter
    private int width;
    @Getter int height;

    /**
     *
     * @param width  Maximum x value
     * @param height Maximum y value
     */
    public void init(final int width, final int height){
        this.height = height;
        this.width = width;
        this.mazeCell = new CellType[width][height];
        this.visitStatus = new VisitStatus[width][height];
    }

    public void markCellVisitStatus(final CellCoordinate coordinate, final VisitStatus visitStatus){
        this.visitStatus[coordinate.x()][coordinate.y()] = visitStatus;
    }

    public void markCellType(final CellCoordinate cellCoordinate, final CellType cellType){
        this.mazeCell[cellCoordinate.x()][cellCoordinate.y()] = cellType;
    }
}
