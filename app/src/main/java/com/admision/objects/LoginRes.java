package com.admision.objects;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRes {

    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Datum data;

    public class Datum {
        @SerializedName("user_id")
        @Expose
        public int userId;
        @SerializedName("username")
        @Expose
        public String username;
        @SerializedName("email")
        @Expose
        public String email;
        @SerializedName("first_name")
        @Expose
        public String firstName;
        @SerializedName("last_name")
        @Expose
        public String lastName;
        @SerializedName("mls_id")
        @Expose
        public String mlsId;
        @SerializedName("license_number")
        @Expose
        public String licenseNumber;
        @SerializedName("mobile_number")
        @Expose
        public String mobileNumber;
        @SerializedName("office_number")
        @Expose
        public String officeNumber;
        @SerializedName("home_address1")
        @Expose
        public String homeAddress1;
        @SerializedName("home_address2")
        @Expose
        public String homeAddress2;
        @SerializedName("home_city")
        @Expose
        public String homeCity;
        @SerializedName("home_state")
        @Expose
        public String homeState;
        @SerializedName("home_zipcode")
        @Expose
        public String homeZipcode;
        @SerializedName("office_address1")
        @Expose
        public String officeAddress1;
        @SerializedName("office_address2")
        @Expose
        public String officeAddress2;
        @SerializedName("office_city")
        @Expose
        public String officeCity;
        @SerializedName("office_state")
        @Expose
        public String officeState;
        @SerializedName("office_zipcode")
        @Expose
        public String officeZipcode;
        @SerializedName("brokerage")
        @Expose
        public String brokerage;
        @SerializedName("homes_sold_12months")
        @Expose
        public int homesSold;
        @SerializedName("showed_up_on_time")
        @Expose
        public String showedUpOnTime;
        @SerializedName("qualified")
        @Expose
        public int qualified;
        @SerializedName("top_10")
        @Expose
        public int top10;
        @SerializedName("real_estate_site")
        @Expose
        public String realEstateSite;
        @SerializedName("listed_home")
        @Expose
        public String listedHome;
        @SerializedName("total")
        @Expose
        public String total;
        @SerializedName("composite_score")
        @Expose
        public String compositeScore;
        @SerializedName("average_home_price")
        @Expose
        public String averageHomePrice;
        @SerializedName("monthly_budget")
        @Expose
        public String monthlyBudget;
        @SerializedName("sites_used")
        @Expose
        public String sitesUsed;
        @SerializedName("company_name")
        @Expose
        public String companyName;
        @SerializedName("comments")
        @Expose
        public String comments;
        @SerializedName("profile_picture")
        @Expose
        public String profilePicture;
        @SerializedName("session_id")
        @Expose
        public String sessionId;

    }

    public class SitesUsed {

    }

}
