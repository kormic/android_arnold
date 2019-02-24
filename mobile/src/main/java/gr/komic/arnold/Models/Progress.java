package gr.komic.arnold.Models;

public class Progress {
    private long Id;
    private String CreatedAt = "";
    private float Hips = 0;
    private float Neck = 0;
    private float Waist = 0;

    public Progress(String createdAt, float Neck, float Waist, float Hips) {
        this.CreatedAt = createdAt;
        this.Neck = Neck;
        this.Waist = Waist;
        this.Hips = Hips;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        this.CreatedAt = createdAt;
    }

    public float getNeck() {
        return Neck;
    }

    public void setNeck(float neck) {
        this.Neck = neck;
    }

    public float getWaist() {
        return Waist;
    }

    public void setWaist(float waist) {
        this.Waist = waist;
    }

    public float getHips() {
        return Hips;
    }

    public void setHips(float hips) {
        this.Hips = hips;
    }
}
