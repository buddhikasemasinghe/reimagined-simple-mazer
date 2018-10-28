package com.assignments.buddhika.simplemazer.service;

import com.assignments.buddhika.simplemazer.common.MazeBoard;
import com.assignments.buddhika.simplemazer.exception.MazeSolverServiceException;
import com.assignments.buddhika.simplemazer.logic.MazeSolver;
import com.assignments.buddhika.simplemazer.logic.impl.BFSMazeSolver;
import com.assignments.buddhika.simplemazer.model.CellCoordinate;
import com.assignments.buddhika.simplemazer.model.CellType;
import com.assignments.buddhika.simplemazer.model.MazeCell;
import com.assignments.buddhika.simplemazer.model.VisitStatus;
import com.assignments.buddhika.simplemazer.util.MazeBoardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MazeSolverCommandLineService {

    private static String NEW_LINE_REG_EXP = "[\\r]?\\n";
    @Autowired
    private MazeBoard mazeBoard;

    public List<String> listMazeInputs(final String directoryPath) {
        List<String> fileNames = new ArrayList<>();
        File folder = new File(directoryPath);
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                fileNames.add(file.getAbsolutePath());
            }
        }
        return fileNames;
    }

    public String processFile(String filePath) {
        Path path = null;
        try {
            path = Paths.get(new File(filePath).toURI());
            Stream<String> lines = Files.lines(path);
            String data = lines.collect(Collectors.joining("\n"));
            lines.close();
            String inputTxt = data.trim();
            buildMazeBoard(inputTxt);
            MazeSolver mazeSolver = new BFSMazeSolver(this.mazeBoard, new MazeBoardUtil(this.mazeBoard));
            try {
                List<MazeCell> finalPath = mazeSolver.solveAndFindRoute();
                return mazeSolver.printMazePath(finalPath);

            } catch (MazeSolverServiceException e) {
                return e.getLocalizedMessage();
            }
        } catch (IOException e) {
            return "Error" + e.getLocalizedMessage();
        }
    }

    private void buildMazeBoard(final String inputMaze) {
        String[] mazeRows = inputMaze.split(NEW_LINE_REG_EXP);
        int noOfRows = mazeRows.length;
        int noOfColumns = mazeRows[0].length();

        this.mazeBoard.init(noOfColumns, noOfRows);
        for (int rowCount = 0; rowCount < noOfRows; rowCount++) {
            for (int colCount = 0; colCount < noOfColumns; colCount++) {
                CellType cellType = CellType.getByCharacter(mazeRows[rowCount].charAt(colCount));
                CellCoordinate cellCoordinate = new CellCoordinate(colCount, rowCount);
                this.mazeBoard.markCellType(cellCoordinate, cellType);
                switch (cellType) {
                    case START:
                        this.mazeBoard.setStartCell(new MazeCell(null, cellCoordinate));
                        break;
                    case FINISH:
                        this.mazeBoard.setEndCell(new MazeCell(null, cellCoordinate));
                        break;
                    default:
                        break;
                }
                this.mazeBoard.markCellVisitStatus(cellCoordinate, VisitStatus.NOT_VISITED);
            }
        }
    }

}
