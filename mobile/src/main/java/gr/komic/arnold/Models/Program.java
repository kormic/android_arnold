package gr.komic.arnold.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;

public class Program implements Parcelable {
    long Id;
    String Title;
    ArrayList<Exercise> Exercises = new ArrayList<Exercise>();
    Date CreatedAt;
    Boolean IsCurrentProgram = false;

    public Program() {
        CreatedAt = new Date();
    }

    protected Program(Parcel in) {
        Id = in.readLong();
        Title = in.readString();
        Exercises = in.readArrayList(null);
        CreatedAt = new Date(in.readLong());
        IsCurrentProgram = in.readByte() != 0;
    }

    public static final Creator<Program> CREATOR = new Creator<Program>() {
        @Override
        public Program createFromParcel(Parcel in) {
            return new Program(in);
        }

        @Override
        public Program[] newArray(int size) {
            return new Program[size];
        }
    };

    public long getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public Boolean getIsCurrentProgram() {
        return IsCurrentProgram;
    }

    public ArrayList<Exercise> getExercises() { return Exercises; }

    public void setId(long id) {
        Id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setCreatedAt(Date createdAt) { CreatedAt = createdAt; }

    public void setIsCurrentProgram(Boolean isCurrentProgram) { IsCurrentProgram = isCurrentProgram; }

    public void addExerciseToProgram(Exercise exercise) {
        Exercises.add(exercise);
    }

    public void removeExerciseFromProgram(int exerciseid) { Exercises.remove(Exercises.indexOf(exerciseid)); }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(Id);
        dest.writeString(Title);
        dest.writeLong(CreatedAt.getTime());
        dest.writeList(Exercises);
        dest.writeByte((byte) (IsCurrentProgram ? 1 : 0));
    }
}
