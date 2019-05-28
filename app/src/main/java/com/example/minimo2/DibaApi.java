package com.example.minimo2;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DibaApi {

    @GET("\"pag-ini/{numinici}/pag-fi/{numfinal}\"")
    Call<Museums> getData(@Path("numinici") int pagini, @Path("numfinal") int pagfi);

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://do.diba.cat/api/dataset/museus/format/json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
