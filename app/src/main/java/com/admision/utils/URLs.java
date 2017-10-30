package com.admision.utils;

public class URLs {


    private static String MAIN_URL = "http://kartum.in/apis";

    public static String GET_CHALLAN() {
        return MAIN_URL + "/get_vehicle_data.json";
    }

    public static String GET_COLLECTION_CENTER() {
        return MAIN_URL + "/get_collection_center_data.json";
    }

}
