package com.example.memory;

public class GameLogic {
    private int numOfFlippedTiles;
    private int numOfMatchedTiles;
    private int indexOfPreviousTile;

    public GameLogic() {
        numOfFlippedTiles = 0;
        numOfMatchedTiles = 0;
        indexOfPreviousTile = 0;
    }


    public int getNumOfFlippedTiles() {
        return numOfFlippedTiles;
    }

    public void setNumOfFlippedTiles(int numOfFlippedTiles) {
        this.numOfFlippedTiles = numOfFlippedTiles;
    }

    public int getNumOfMatchedTiles() {
        return numOfMatchedTiles;
    }

    public void setNumOfMatchedTiles(int numOfMatchedTiles) {
        this.numOfMatchedTiles = numOfMatchedTiles;
    }

    public int getIndexOfPreviousTile() {
        return indexOfPreviousTile;
    }

    public void setIndexOfPreviousTile(int indexOfPreviousTile) {
        this.indexOfPreviousTile = indexOfPreviousTile;
    }
}