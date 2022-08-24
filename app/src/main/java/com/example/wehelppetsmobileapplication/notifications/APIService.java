package com.example.wehelppetsmobileapplication.notifications;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key= AAAATlPK6rs:APA91bFckKclp-S4mVonQBD2k5uylnDFc-ouE6Mz8-wGi5NvG2hr7jhDlcCVoYMZWrY5uFUehZDpjagWh4c4WIs40wmKyn6m4VNh-2kzsTIZpetn2ehld2Qv-Hk1l4Z-A2m7YHxJzr_E"
    })

    @POST("fcm/send")
    Call<Response> sendNotification(@Body Sender body);
}
