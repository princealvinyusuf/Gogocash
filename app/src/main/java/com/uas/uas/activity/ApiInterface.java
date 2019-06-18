package com.uas.uas.activity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("get_notes.php")
    Call<List<Notes>> getNotes();

    @FormUrlEncoded
    @POST("add_notes.php")
    Call<Notes> insertPet(
            @Field("key") String key,
            @Field("name") String name,
            @Field("deskripsi") String deskripsi,
            @Field("date") String date,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_notes.php")
    Call<Notes> updatePet(
            @Field("key") String key,
            @Field("id") int id,
            @Field("name") String name,
            @Field("deskripsi") String deskripsi,
            @Field("date") String date,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("delete_notes.php")
    Call<Notes> deletePet(
            @Field("key") String key,
            @Field("id") int id,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_love.php")
    Call<Notes> updateLove(
            @Field("key") String key,
            @Field("id") int id,
            @Field("love") boolean love);

}
