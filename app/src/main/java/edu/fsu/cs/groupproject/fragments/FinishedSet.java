package edu.fsu.cs.groupproject.fragments;

public class FinishedSet {

    int setNumber;
    int weight;
    int reps;

    public FinishedSet(int setNumber, int weight, int reps) {
        this.setNumber = setNumber;
        this.weight = weight;
        this.reps = reps;
    }

    // gets n sets

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int setNumber) {
        this.setNumber = setNumber;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
