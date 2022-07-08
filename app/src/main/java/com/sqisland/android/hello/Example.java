package com.sqisland.android.hello;

import java.io.IOException;

import android.annotation.TargetApi;
import android.os.Build;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Example {
    final static OkHttpClient client = new OkHttpClient();

    public static void test(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    String response = makeRequest("https://paragor.ru");
                    System.out.println(response);
                    String response2 = makeRequest("https://echo.paragor.ru");
                    System.out.println(response2);
                } catch (Exception exception) {
                    System.out.println("Произошла ошибка");
                    exception.printStackTrace();
                }
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            String makeRequest(String url) throws IOException {
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    return response.body().string();
                }
            }
        });

        thread.start();
    }
}