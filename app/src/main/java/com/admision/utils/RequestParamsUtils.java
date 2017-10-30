package com.admision.utils;

import android.content.Context;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RequestParamsUtils {

    public static final String USER_ID = "user_id";
    public static final String SESSION_ID = "session_id";
    public static final String APIKEY = "APIKey";

    public static final String DEVICE_TYPE = "device_type";
    public static final String DEVICE_ID = "device_id";
    public static final String GCMKEY = "gcmkey";
    public static final String DEVICE_TOKEN = "device_token";

    public static final String USERNAME = "Username";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PAGE = "page";

    public static final String CUSTOMER = "customer";
    public static final String REFERENCE_AGENT = "reference_agent";
    public static final String TITLE_OPERATOR = "title_operator";
    public static final String TITLE_PAPER = "title_paper";
    public static final String TITLE_STATUS = "title_status";
    public static final String FRONT_PAGE_COLOR = "front_page_color";
    public static final String BACK_PAGE_COLOR = "back_page_color";
    public static final String INNER_OPERATOR = "inner_operator";
    public static final String INNER_PAPER = "inner_paper";
    public static final String INNER_STATUS = "inner_status";
    public static final String INNER_PAGE_COLOR = "inner_page_color";
    public static final String INNER_PAGE_4_COLOR_TYPE = "inner_page_4_color_type";
    public static final String LAMINATION_TYPE = "lamination_type";
    public static final String BINDING = "binding";
    public static final String QUANTITY = "quantity";
    public static final String PRICE = "price";
    public static final String SIZE = "size";
    public static final String PAGES = "pages";
    public static final String ADVANCE = "advance";
    public static final String TOTAL = "total";
    public static final String NOTE = "note";
    public static final String DELIVERY_DATE = "delivery_date";
    public static final String MEDIUM = "medium";
    public static final String STANDARD = "standard";
    public static final String WORK_TYPE = "work_type";


    public static RequestBody newRequestBody(Context context, String json) {
        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        return body;
    }

    public static FormBody.Builder newRequestFormBody(Context c) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.addEncoded(EMAIL, Utils.getPref(c, RequestParamsUtils.EMAIL, ""));

        return builder;
    }

    public static HttpUrl.Builder newRequestUrlBuilder(Context c, String url) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter(EMAIL, Utils.getPref(c, RequestParamsUtils.EMAIL, ""));

        return urlBuilder;

    }
}
