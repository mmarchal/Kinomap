package com.example.testkinomap;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {

    String URL_DATAS = "http://preprod-api.kinomap.com/vehicle/";

    @GET("list?icon=1&lang=en-gb&forceStandard=1&outputFormat=json&appToken=f5FxX9Q08DQ9j31Hg5Jx8qHJDVeAWpyFAEwNFV6QF11Mjtl6pbKvMElkWuDk2aVL5MW5bhdQnVp0bIF22dr3hyDVKW9a9H7o32QfIXKOwIJXTCkzXAETCjB8")
    Call<Object> getInfos();

}
