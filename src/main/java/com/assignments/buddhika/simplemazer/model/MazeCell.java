package com.assignments.buddhika.simplemazer.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@RequiredArgsConstructor
@Accessors(fluent = true) @Getter
public class MazeCell {
    MazeCell parentCell;
    int x;
    int y;
}
