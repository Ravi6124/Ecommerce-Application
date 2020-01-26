package com.example.ecom.productListActivity.models;

public class MerchantResponse {

    private String merchantId;
    private String email;
    private String firstName;
    private String lastName;
    private String contactNumber;
    //private String password;
    private String city;
    private String imageURL;
    private double merchantRating;
    private int numberOfMerchantRatings;
    int totalProductSold;

    @Override
    public String toString() {
        return "MerchantResponse{" +
                "merchantId='" + merchantId + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", city='" + city + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", merchantRating=" + merchantRating +
                ", numberOfMerchantRatings=" + numberOfMerchantRatings +
                ", totalProductSold=" + totalProductSold +
                '}';
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getMerchantRating() {
        return merchantRating;
    }

    public void setMerchantRating(double merchantRating) {
        this.merchantRating = merchantRating;
    }

    public int getNumberOfMerchantRatings() {
        return numberOfMerchantRatings;
    }

    public void setNumberOfMerchantRatings(int numberOfMerchantRatings) {
        this.numberOfMerchantRatings = numberOfMerchantRatings;
    }

    public int getTotalProductSold() {
        return totalProductSold;
    }

    public void setTotalProductSold(int totalProductSold) {
        this.totalProductSold = totalProductSold;
    }
}
