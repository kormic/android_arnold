package gr.komic.arnold.helpers;

import android.content.Context;

import gr.komic.arnold.R;

public class SpinnersHelper {

    public static String[] getGenderValues(Context context) {
        String[] genderValues = new String[2];
        genderValues[0] = context.getString(R.string.man);
        genderValues[1] = context.getString(R.string.woman);
        return genderValues;
    }

    public static Integer[] getAgeValues() {
        Integer[] ages = new Integer[100];
        for(int i = 0; i < ages.length; i++) {
            ages[i] = i + 1;
        }
        return ages;
    }

    public static Integer[] getWeightValues() {
        Integer[] weights = new Integer[200];
        for(int i = 0; i < weights.length; i++) {
            weights[i] = i + 1;
        }
        return weights;
    }

    public static Integer[] getHeightValues() {
        Integer[] heights = new Integer[250];
        for(int i = 0; i < heights.length; i++) {
            heights[i] = i + 1;
        }
        return heights;
    }

    public static Integer[] getWorkoutsPerWeekValues() {
        Integer[] workoutsPerWeekValues = new Integer[7];
        for(int i = 0; i < workoutsPerWeekValues.length; i++) {
            workoutsPerWeekValues[i] = i + 1;
        }
        return workoutsPerWeekValues;
    }
}
