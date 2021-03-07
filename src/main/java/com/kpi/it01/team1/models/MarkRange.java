package com.kpi.it01.team1.models;

public class MarkRange {
    private final int minMark;
    private final int maxMark;

    public MarkRange(int minMark, int maxMark) {
        this.minMark = minMark;
        this.maxMark = maxMark;
    }

    public int getMinMark() {
        return minMark;
    }

    public int getMaxMark() {
        return maxMark;
    }
}
