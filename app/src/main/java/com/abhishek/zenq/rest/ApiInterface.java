package com.abhishek.zenq.rest;


import com.abhishek.zenq.Response.AddCityResponse;
import com.abhishek.zenq.Response.AddStateResponse;
import com.abhishek.zenq.Response.GetCityResponse;
import com.abhishek.zenq.Response.GetStateResponse;
import com.abhishek.zenq.Response.GetUSERKYCResponse;
import com.abhishek.zenq.Response.KYCSubmitResponse;
import com.abhishek.zenq.Response.LoginResponse;
import com.abhishek.zenq.Response.RegisterResponse;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    /*    http://webtecnoworld.com/phovio/Api/fleet_getcity.php?state_id=1
    * http://webtecnoworld.com/phovio/Api/fleet_addstate.php?state_name=up&state_code=12
    * http://webtecnoworld.com/phovio/Api/fleet_addcity.php?state_name=up&state_code=12&state_id=1&city_name=Etawa
    * http://webtecnoworld.com/phovio/Api/fleet_getcity.php?state_id=1
    * */
    // @POST("userlogin.php?")
    @POST("fleet_userlogin.php?")
    Call<LoginResponse> Login(@Body RequestBody requestBody);
    @POST("fleet_getuser.php")
    Call<GetUSERKYCResponse> GetUserKYC(@Body RequestBody requestBody);
    @POST("fleet_userkyc.php?")
    Call<KYCSubmitResponse> FleetUserKYC(@Body RequestBody requestBody);
    @POST("fleet_getstate.php")
    Call<GetStateResponse> FleetGetState(@Body RequestBody requestBody);
    @POST("fleet_addstate.php?")
    Call<AddStateResponse> FleetAddState(@Body RequestBody requestBody);
    @POST("fleet_getcity.php?")

    Call<GetCityResponse> FleetGetCity(@Body RequestBody requestBody);
    @POST("fleet_addcity.php")
    Call<AddCityResponse> FleetAddCity(@Body RequestBody requestBody);
/*    @POST("fleet_usersignup.php")
    Call<RegisterResponse> Register(@Body RequestBody requestBody);*/
    @Multipart
    @POST("fleet_usersignup.php")
    Call<ResponseBody> Register(@Part("image") RequestBody image, @Part("image2") RequestBody image2,
                                @Part("user_type") RequestBody user_type, @Part("business_name") RequestBody business_name,
                                @Part("name") RequestBody name, @Part("mobile") RequestBody mobile,
                                @Part("email") RequestBody email, @Part("address") RequestBody address,
                                @Part("locatlity") RequestBody locatlity, @Part("city") RequestBody city,
                                @Part("state") RequestBody state, @Part("country") RequestBody country,
                                @Part("pincode") RequestBody pincode,
                                @Part("landline") RequestBody landline, @Part("pancard") RequestBody pancard,
                                @Part("password") RequestBody password, @Part("role") RequestBody role,
                                @Part MultipartBody.Part file);

    /*

    @POST("token.php?")
    Call<ResponseBody> Token(@Body RequestBody requestBody);

    @POST("order_details.php?")
    Call<Modal_Order_Response> OrderDetails(@Body RequestBody requestBody);

    @POST("verify_otp")
    Call<ResponseBody> Verify_otp(@Body RequestBody requestBody);

    @POST("forget")
    Call<ResponseBody> Forget_Pass(@Body RequestBody requestBody);

    @POST("order_complete.php")
    Call<AcceptResponse> OrderComplete(@Body RequestBody requestBody);
*/
    @POST("restaurant_addfav.php?")
    Call<ResponseBody> RestaurantADD_Fav(@Body RequestBody requestBody);

    @POST("restaurant_addcart.php?")
    Call<ResponseBody> Restaurant_add_Cart(@Body RequestBody requestBody);

    @POST("restaurant_getcart.php?")
    Call<ResponseBody> Restaurent_Get_Cart(@Body RequestBody requestBody);

    @POST("bookorder.php?")
    Call<ResponseBody> Book_ORder(@Body RequestBody requestBody);

    @POST("update-profile")
    Call<ResponseBody> Update_profile(@Body RequestBody requestBody);

    @POST("update-password")
    Call<ResponseBody> Update_password(@Body RequestBody requestBody);

    @POST("users-redeam")
    Call<ResponseBody> Users_redeam(@Body RequestBody requestBody);

    @POST("users-redeam-history")
    Call<ResponseBody> Users_Redeam_History(@Body RequestBody requestBody);

    @POST("groupusers")
    Call<ResponseBody> Groupusers(@Body RequestBody requestBody);

    @POST("addgroup")
    Call<ResponseBody> Addgroup(@Body RequestBody requestBody);

}
