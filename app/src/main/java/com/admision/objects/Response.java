package com.admision.objects;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("message")
    @Expose
    public String message;


}
