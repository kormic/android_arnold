package gr.komic.arnold.Models;

public class Set {
    int Reps;
    float lastWeight;

    public Set(int reps, float lastWeight) {
        Reps = reps;
        this.lastWeight = lastWeight;
    }

    public int getReps() {
        return Reps;
    }

    public void setReps(int reps) {
        Reps = reps;
    }

    public float getLastWeight() {
        return lastWeight;
    }

    public void setLastWeight(float lastWeight) {
        this.lastWeight = lastWeight;
    }
}
