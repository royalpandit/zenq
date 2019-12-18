package com.abhishek.zenq.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.abhishek.zenq.Bean.StateBean;
import com.abhishek.zenq.R;
import com.abhishek.zenq.Response.GetStateResponse;
import com.abhishek.zenq.Utility.CommonUtils;
import com.abhishek.zenq.rest.ApiClient;
import com.abhishek.zenq.rest.ApiInterface;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener , View.OnClickListener {
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    List<StateBean> stateBeanList;
    StateBean stateBean;
    private static final String IMAGE_DIRECTORY = "/Zeiq";
    private int GALLERY = 1, CAMERA = 2;
    String filePath = "";
    String filePath1 = "";

    private String[] categories = {"Fleet Owner", "Commision Agent", "Booking Agent","Commision Agent"};
    String select_Categories;
String Str_editfirstname,Str_editbuisnessname,Str_editlastname,Str_editregemail,Str_editmobile,
        Str_editpassword, Str_editaddress,Str_editlandline,STr_editlocality,Str_editPan,
        Str_editpin,Str_editDistrict,Str_editstate,Str_editdob,Str_editcountary,Str_editcity;
AVLoadingIndicatorView avi;
ImageView userimage,userdocument,back;
     EditText editfirstname,editregemail,editmobile,editbuisnessname,editlandlanine,
             editpassword,editaddress,editlocality,editpin,editpaan,editdob,editcountary;
    String isImageEdit = "0";
    String isDocEdit = "0";
String Str_countary="INDIA";

    Button btn_sign;
    String StrUserType;

    Spinner spinner_state,spinner_city,spinner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        requestMultiplePermissions();

        find();

        getState();
    }
    public void find(){
        back=findViewById(R.id.back);
        avi=findViewById(R.id.avi);
        userimage=findViewById(R.id.userimage);
        userdocument=findViewById(R.id.userdocument);
        editbuisnessname=findViewById(R.id.editbuisnessname);
        editlandlanine=findViewById(R.id.editlandlanine);
        editlocality=findViewById(R.id.editlocality);
        editpaan=findViewById(R.id.editpaan);
        editfirstname=findViewById(R.id.editfirstname);
         editregemail=findViewById(R.id.editregemail);
        editmobile=findViewById(R.id.editmobile);
        editpassword=findViewById(R.id.editpassword);
         editaddress=findViewById(R.id.editaddress);
        editpin=findViewById(R.id.editpin);
        editcountary=findViewById(R.id.editcountary);
        spinner_state=findViewById(R.id.spinner_state);
        spinner_city=findViewById(R.id.spinner_city);

        editdob=findViewById(R.id.editdob);
        btn_sign=findViewById(R.id.btn_sign);
        btn_sign.setOnClickListener(this);
        userdocument.setOnClickListener(this);
        userimage.setOnClickListener(this);


        spinner=findViewById(R.id.spinner);
         spinner_state.setOnItemSelectedListener(this);
        spinner_city.setOnItemSelectedListener(this);
        back.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categories);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(aa);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       // select_Categories = categories[position];
        StrUserType = String.valueOf(spinner.getSelectedItemPosition());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        if (v==btn_sign){




            Str_editbuisnessname=editbuisnessname.getText().toString().trim();
            Str_editlandline=editlandlanine.getText().toString().trim();
            Str_editfirstname = editfirstname.getText().toString().trim();
             Str_editregemail = editregemail.getText().toString().trim();
            Str_editmobile = editmobile.getText().toString().trim();
            Str_editpassword = editpassword.getText().toString().trim();
             Str_editaddress = editaddress.getText().toString().trim();
            Str_editpin = editpin.getText().toString().trim();
             Str_editdob = editdob.getText().toString().trim();
            STr_editlocality=editlocality.getText().toString().trim();
            Str_editPan=editpaan.getText().toString().trim();
            if (Str_editbuisnessname.equals("") || Str_editbuisnessname.isEmpty()) {
                Toast.makeText(this, "Please Enter Buisness Name", Toast.LENGTH_SHORT).show();
            }
             else if (Str_editfirstname.equals("") || Str_editfirstname.isEmpty()) {
                Toast.makeText(this, "Please Enter First Name", Toast.LENGTH_SHORT).show();
            } else if (Str_editmobile.equals("") || Str_editmobile.isEmpty()) {
                Toast.makeText(this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
            } else if (Str_editpassword.equals("") || Str_editpassword.isEmpty()) {
                Toast.makeText(this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
            } else if (Str_editlandline.equals("") || Str_editlandline.isEmpty()) {
                Toast.makeText(this, "Please Enter Landline Name", Toast.LENGTH_SHORT).show();
            } else if (Str_editaddress.equals("") || Str_editaddress.isEmpty()) {
                Toast.makeText(this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
            }  else if (Str_editdob.equals("") || Str_editdob.isEmpty()) {
                Toast.makeText(this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
            } else if (!Str_editregemail.matches(emailPattern)) {

                Toast.makeText(this, "Invalid Email.", Toast.LENGTH_SHORT).show();
            } else if (userimage.getDrawable() == null )
                Toast.makeText(getApplicationContext(), "Please put some image for User", Toast.LENGTH_SHORT).show();
            else if (userdocument.getDrawable() == null )
                Toast.makeText(getApplicationContext(), "Please put some image for Document", Toast.LENGTH_SHORT).show();
            else {
                Signup();
            }


        }if (v==userdocument){
            showPictureDialog();
        }if (v==userimage){
            showPictureDialog();
        }if (v==back){
            onBackPressed();
        }

    }
    private void Signup() {

        avi.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        if(isImageEdit.equals("0")&&isDocEdit.equals("0")){
            Bitmap bitmap = ((BitmapDrawable) userimage.getDrawable()).getBitmap();
            Bitmap bitmap1 = ((BitmapDrawable) userdocument.getDrawable()).getBitmap();

            filePath = saveImage(bitmap);
            filePath = saveImage(bitmap1);

            Log.e("filePath", ""+filePath);
        }
        MultipartBody.Part part = null;
        //if(filePath!=null && isImageEdit.equals("1")) {
        //Create a file object using file path
        File file = new File(filePath);
        Log.e("filePath", ""+filePath);
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        part = MultipartBody.Part.createFormData("image", file.getName(), fileReqBody);


        RequestBody image = RequestBody.create(MediaType.parse("image"), isImageEdit);
        RequestBody image2 = RequestBody.create(MediaType.parse("image2"), isDocEdit);
        RequestBody user_type = RequestBody.create(MediaType.parse("user_type"),StrUserType);
        RequestBody business_name = RequestBody.create(MediaType.parse("business_name"), Str_editbuisnessname);
        RequestBody name = RequestBody.create(MediaType.parse("name"), Str_editfirstname);
        RequestBody mobile = RequestBody.create(MediaType.parse("mobile"), Str_editmobile);
        RequestBody email = RequestBody.create(MediaType.parse("email"), Str_editregemail);
        RequestBody address = RequestBody.create(MediaType.parse("address"), Str_editaddress);
        RequestBody locatlity = RequestBody.create(MediaType.parse("locatlity"), STr_editlocality);
        RequestBody city = RequestBody.create(MediaType.parse("city"), StrUserType);
        RequestBody state = RequestBody.create(MediaType.parse("state"), StrUserType);
        RequestBody country = RequestBody.create(MediaType.parse("country"), Str_countary);
        RequestBody pincode = RequestBody.create(MediaType.parse("pincode"), Str_editpin);
        RequestBody landline = RequestBody.create(MediaType.parse("landline"), Str_editlandline);
        RequestBody pancard = RequestBody.create(MediaType.parse("pancard"), Str_editPan);
        RequestBody password = RequestBody.create(MediaType.parse("password"), Str_editpassword);
        RequestBody role = RequestBody.create(MediaType.parse("role"), Str_editpassword);

    /*    FormBody.Builder builder = ApiClient.createBuilder(new String[]{"image", "image2", "user_type","business_name","name","mobile",
                        "email","address","locatlity","city","state","country","pincode","landline","pancard","password","role"},
                new String[]{Str_editfirstname, Str_editfirstname, Str_editfirstname});*/
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {
            Call<ResponseBody> call = apiInterface.Register(image,image2,user_type,business_name,name,
                    mobile,email,address,locatlity,city,state,country,pincode,landline,pancard,password,role,part);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    avi.setVisibility(View.GONE);
                    Log.d("11111", "11111");
                    if (response.isSuccessful()) {
                        Log.d("2222", "2222");
                        try {
                            if (response.isSuccessful() ) {
                                Toast.makeText(RegisterActivity.this, "User Added successfully.", Toast.LENGTH_SHORT).show();
                            /*if (response.body().getInfo().getUser_type().equals("0") && response.body().getInfo().getIs_kyc().equals("1")) {
                             //   LoginBean loginBean = response.body().getInfo();
                           //     AppPreferences.SaveUserdetail(LoginActivity.this, loginBean);
                               // startActivity(new Intent(LoginActivity.this, MainActivity.class));

                            } else {
                                Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }*/

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    avi.setVisibility(View.GONE);
                }
            });

        } else {
            avi.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }

    }
    private void getState() {
        avi.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        FormBody.Builder builder = ApiClient.createBuilder(new String[]{}, new
                String[]{});
        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {
            Call<GetStateResponse> call = apiInterface.FleetGetState(builder.build());
            call.enqueue(new Callback<GetStateResponse>() {
                @Override
                public void onResponse(Call<GetStateResponse> call, Response<GetStateResponse> response) {
                    avi.setVisibility(View.GONE);

                    if (response.isSuccessful() && response.body().getStatus().equals("1")  && response.body().getMsg().equals("Successfully")) {
                        stateBeanList = new ArrayList<>();

                        for (int i = 0; i < response.body().getInfo().size(); i++) {
                            stateBean = new StateBean();


                            stateBean.setId(response.body().getInfo().get(i).getId());
                            stateBean.setState_code(response.body().getInfo().get(i).getState_code());
                            stateBean.setState_name(response.body().getInfo().get(i).getState_name());
                            stateBeanList.add(stateBean);
                        }
                        Log.e("userOrderHistoryBean", "" + stateBeanList.size());
                        ArrayAdapter arrayspin=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,stateBeanList);
                        arrayspin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner_state.setAdapter(arrayspin);
                    } else {
                        Log.e("userOrderHistory1ize", "" + "else");
                    }
                }

                @Override
                public void onFailure(Call<GetStateResponse> call, Throwable t) {
                    avi.setVisibility(View.GONE);
                }
            });
        } else {
            avi.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }
    }



















    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(RegisterActivity.this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }





    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RegisterActivity.this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), contentURI);
                    Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), contentURI);

                    filePath = saveImage(bitmap);
                    filePath = saveImage(bitmap1);

                    userimage.setImageBitmap(bitmap);
                    userdocument.setImageBitmap(bitmap1);

                    isImageEdit = "1";
                    isDocEdit = "1";
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            Bitmap thumbnail1 = (Bitmap) data.getExtras().get("data");

            userimage.setImageBitmap(thumbnail);
            userdocument.setImageBitmap(thumbnail1);

            filePath = saveImage(thumbnail);
            filePath = saveImage(thumbnail1);
            isImageEdit = "1";
            isDocEdit = "1";
        }
    }




    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(getApplicationContext(),
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());

            //uploadToServer(f.getAbsolutePath(),myBitmap);  // uploading umage to server

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }



    private void requestMultiplePermissions() {
        Dexter.withActivity(RegisterActivity.this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }


}
