package gr.komic.arnold.Models;

public class MyGymInfo {
    private String name = "";
    private String address = "";
    private String phone = "";
    private float rating = 0;
    private String website = "";
    private String registrationDateString = "";
    private float subscription = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getRegistrationDateString() {
        return registrationDateString;
    }

    public void setRegistrationDateString(String registrationDateString) {
        this.registrationDateString = registrationDateString;
    }

    public float getSubscription() {
        return subscription;
    }

    public void setSubscription(float subscription) {
        this.subscription = subscription;
    }
}
