package gr.komic.arnold.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class ExerciseSet implements Parcelable {
    int Sequence;
    int Reps;
    float LastWeight;
    long ExerciseId;
    long ProgramId;

    public ExerciseSet(int reps, float lastWeight) {
        Reps = reps;
        this.LastWeight = lastWeight;
    }

    protected ExerciseSet(Parcel in) {
        Sequence = in.readInt();
        Reps = in.readInt();
        LastWeight = in.readFloat();
        ExerciseId = in.readLong();
        ProgramId = in.readLong();
    }

    public static final Creator<ExerciseSet> CREATOR = new Creator<ExerciseSet>() {
        @Override
        public ExerciseSet createFromParcel(Parcel in) {
            return new ExerciseSet(in);
        }

        @Override
        public ExerciseSet[] newArray(int size) {
            return new ExerciseSet[size];
        }
    };

    public int getSequence() {
        return this.Sequence;
    }

    public int getReps() {
        return Reps;
    }

    public float getLastWeight() {
        return LastWeight;
    }

    public long getExerciseId() { return ExerciseId; }

    public long getProgramId() { return  ProgramId; }

    public void setExerciseId(long exerciseId) { ExerciseId = exerciseId; }

    public void setProgramId(long programId) { ProgramId = programId; }

    public void setReps(int reps) {
        Reps = reps;
    }

    public void setSequence(int sequence) {
        this.Sequence = sequence;
    }

    public void setLastWeight(float lastWeight) {
        this.LastWeight = lastWeight;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(ExerciseId);
        dest.writeInt(Reps);
        dest.writeFloat(LastWeight);
        dest.writeInt(Sequence);
        dest.writeLong(ProgramId);
    }
}
