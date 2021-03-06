package com.assignments.buddhika.simplemazer.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true) @Getter
public class MazeCell {
    private final MazeCell parentCell;
    private final CellCoordinate cellCoordinate;
}
