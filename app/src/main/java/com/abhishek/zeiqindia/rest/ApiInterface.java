package com.abhishek.zeiqindia.rest;


import com.abhishek.zeiqindia.Response.AddCityResponse;
import com.abhishek.zeiqindia.Response.AddPartyResponse;
import com.abhishek.zeiqindia.Response.AddStateResponse;
import com.abhishek.zeiqindia.Response.AddTripResponse;
import com.abhishek.zeiqindia.Response.AddTttripExpwithoutarray;
import com.abhishek.zeiqindia.Response.FleetOwnerAllBidResponse;
import com.abhishek.zeiqindia.Response.FleetOwnerDriverResponse;
import com.abhishek.zeiqindia.Response.FleetOwnerGetTripLoadResponse;
import com.abhishek.zeiqindia.Response.FleetOwnerMentianenceResponse;
import com.abhishek.zeiqindia.Response.FleetOwnerMyTripsResponse;
import com.abhishek.zeiqindia.Response.FleetOwnerTruckResponse;
import com.abhishek.zeiqindia.Response.FleetownerAddTripMOreResponse;
import com.abhishek.zeiqindia.Response.FleetownerGetTripResponse;
import com.abhishek.zeiqindia.Response.GepPartyResponse;
import com.abhishek.zeiqindia.Response.GetCityResponse;
import com.abhishek.zeiqindia.Response.GetStateResponse;
import com.abhishek.zeiqindia.Response.GetUSERKYCResponse;
import com.abhishek.zeiqindia.Response.KYCSubmitResponse;
import com.abhishek.zeiqindia.Response.LoginResponse;
import com.abhishek.zeiqindia.Response.OtpResponse;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {












    /*
      http://webtecnoworld.com/zeiq/api/fleet_addtripadvance.php
      http://webtecnoworld.com/phovio/Api/fleet_getcity.php?state_id=1
     * http://webtecnoworld.com/phovio/Api/fleet_addstate.php?state_name=up&state_code=12
     * http://webtecnoworld.com/phovio/Api/fleet_addcity.php?state_name=up&state_code=12&state_id=1&city_name=Etawa
     * http://webtecnoworld.com/phovio/Api/fleet_getcity.php?state_id=1
     * */
    // @POST("userlogin.php?")
    @POST("fleet_userlogin.php?")
    Call<LoginResponse> Login(@Body RequestBody requestBody);


    @POST("fleet_sendsms.php")
    Call<OtpResponse> sendsms(@Body RequestBody requestBody);

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

    @Multipart
    @POST("fleet_usersignup.php")
    Call<ResponseBody> Register(@Part("user_type") RequestBody user_type, @Part("business_name") RequestBody business_name,
                                @Part("name") RequestBody name, @Part("mobile") RequestBody mobile,
                                @Part("email") RequestBody email, @Part("address") RequestBody address,
                                @Part("locatlity") RequestBody locatlity, @Part("city") RequestBody city,
                                @Part("state") RequestBody state, @Part("country") RequestBody country,
                                @Part("pincode") RequestBody pincode,
                                @Part("landline") RequestBody landline, @Part("pancard") RequestBody pancard,
                                @Part("password") RequestBody password, @Part("role") RequestBody role,
                                @Part MultipartBody.Part file, @Part MultipartBody.Part file2);


    @POST("fleet_getallbid.php")
    Call<FleetOwnerAllBidResponse> FleetGetAllBid(@Body RequestBody requestBody);

    @POST("fleet_getmytrip.php?")
    Call<FleetOwnerMyTripsResponse> FleetGetMyTrip(@Body RequestBody requestBody);

    @POST("fleet_gettruck.php?")
    Call<FleetOwnerTruckResponse> FleetGetAllTruck(@Body RequestBody requestBody);
    @POST("fleet_getdriver.php?")
    Call<FleetOwnerDriverResponse> FleetGetAllDriver(@Body RequestBody requestBody);
    @POST("fleet_servicevendor.php?")
    Call<FleetOwnerMentianenceResponse> FleetServiceVendor(@Body RequestBody requestBody);
    @POST("fleet_addtrip.php?")
    Call<AddTripResponse> Fleet_AddTrip(@Body RequestBody requestBody);
    @POST("fleet_getparty.php?")
    Call<GepPartyResponse> Fleet_GetParty(@Body RequestBody requestBody);
    @POST("fleet_addparty.php?")
    Call<AddPartyResponse> Fleet_AddParty(@Body RequestBody requestBody);
    @POST("fleet_changetripstatus.php?")
    Call<AddPartyResponse> Fleet_ChangeTrip(@Body RequestBody requestBody);
    @POST("fleet_addtripadvance.php?")
    Call<AddPartyResponse> Fleet_AddAdvanceTrip(@Body RequestBody requestBody);
    @POST("fleet_gettripload.php?")
    Call<FleetownerGetTripResponse> Fleet_GetTripLoad(@Body RequestBody requestBody);

    @POST("fleet_addmoreload.php?")
    Call<FleetownerAddTripMOreResponse> Fleet_GetMoreLoad(@Body RequestBody requestBody);

    @POST("fleet_gettripexpenses.php?")
    Call<FleetOwnerGetTripLoadResponse> Fleet_GetTripeExpense(@Body RequestBody requestBody);
    @POST("fleet_getcountexpansce.php?")
    Call<AddTttripExpwithoutarray> Fleet_GetTripeLoadExp(@Body RequestBody requestBody);
}