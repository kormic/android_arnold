package gr.komic.arnold.helpers;

public class SpinnersHelper {

    public static Integer[] getAgeValues() {
        Integer[] ages = new Integer[88];
        for(int i = 0; i < ages.length; i++) {
            ages[i] = 13 + i;
        }
        return ages;
    }

    public static Integer[] getWeightValues() {
        Integer[] weights = new Integer[161];
        for(int i = 0; i < weights.length; i++) {
            weights[i] = 40 + i;
        }
        return weights;
    }

    public static Integer[] getHeightValues() {
        Integer[] heights = new Integer[121];
        for(int i = 0; i < heights.length; i++) {
            heights[i] = 130 + i;
        }
        return heights;
    }
}
