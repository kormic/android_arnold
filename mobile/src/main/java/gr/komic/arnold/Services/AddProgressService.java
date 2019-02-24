package gr.komic.arnold.Services;

public class AddProgressService {

    public double calculateFatPercentageForMen(int waist, int neck, int height) {
        double percentage;
        percentage = 86.010 * Math.log10(waist - neck) - 70.041 * Math.log10(height) + 30.30;

        return percentage;
    }

    public double calculateFatPercentageForWomen(int waist, int hips, int neck, int height) {
        double percentage;
        percentage = 163.205 * Math.log10(waist + hips - neck) - 97.684 * Math.log10(height) - 104.912;

        return percentage;
    }
}
