package com.assignments.buddhika.simplemazer.model;

public enum CellType {
    EMPTY(' '), WALL('#'), START('S'), FINISH('F');

     CellType(final char characterType){
        this.characterType =characterType;
    }

    private char characterType;
}
