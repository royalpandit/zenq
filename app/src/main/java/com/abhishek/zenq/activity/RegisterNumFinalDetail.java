package com.abhishek.zenq.activity;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.abhishek.zenq.R;
import com.abhishek.zenq.Utility.CommonUtils;
import com.abhishek.zenq.rest.ApiClient;
import com.abhishek.zenq.rest.ApiInterface;
import com.google.gson.JsonIOException;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterNumFinalDetail extends AppCompatActivity implements View.OnClickListener {
    AVLoadingIndicatorView bar;
    private static final String IMAGE_DIRECTORY = "/Zeiq";
    private int GALLERY = 1, CAMERA = 2;
    String filePath = "";
    String filePath1 = "";
    String isImageEdit = "0";
    String isDocEdit = "0";

    Button btn_next;
    ImageView userimage, idimage;
    String Str_editbuisnessname, Str_editfirstname, Str_editregemail, Str_editpassword, Str_editmobile,
            Str_editlandlanine,
            Str_editaddress,
            Str_editlocality, Str_editcountary, Str_editState, Str_editCity, Str_editpin, Str_editpaan, Str_editrole, StrUserType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_add_finalphoto);

        StrUserType = getIntent().getStringExtra("StrUserType");
        Str_editbuisnessname = getIntent().getStringExtra("Str_editbuisnessname");
        Str_editfirstname = getIntent().getStringExtra("Str_editfirstname");
        Str_editregemail = getIntent().getStringExtra("Str_editregemail");
        Log.e("regemail",""+Str_editregemail);
        Str_editpassword = getIntent().getStringExtra("Str_editpassword");
        Str_editmobile = getIntent().getStringExtra("Str_editmobile");
        Str_editlandlanine = getIntent().getStringExtra("Str_editlandlanine");
        Str_editaddress = getIntent().getStringExtra("Str_editaddress");
        Str_editlocality = getIntent().getStringExtra("Str_editlocality");
        Str_editcountary = getIntent().getStringExtra("Str_editcountary");
        Str_editState = getIntent().getStringExtra("Str_editState");
        Str_editCity = getIntent().getStringExtra("Str_editCity");
        Str_editpin = getIntent().getStringExtra("Str_editpin");
        Str_editpaan = getIntent().getStringExtra("Str_editpaan");
        Str_editrole = getIntent().getStringExtra("Str_editrole");
        requestMultiplePermissions();
        find();
    }

    public void find() {
        bar = findViewById(R.id.bar);
        btn_next = findViewById(R.id.btn_next);
        idimage = findViewById(R.id.idimage);
        userimage = findViewById(R.id.userimage);
        btn_next.setOnClickListener(this);
        idimage.setOnClickListener(this);
        userimage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        if (v == btn_next) {
            checkValidations();

        }
        if (v == userimage) {
            isImageEdit = "1";
            showPictureDialog();
        }
        if (v == idimage) {
            isImageEdit = "2";

            showPictureDialog();
        }

    }

    private void checkValidations() {
        if (userimage.getDrawable() == null) {
            Toast.makeText(getApplicationContext(), "Please put some image for User.", Toast.LENGTH_SHORT).show();

        } else if (idimage.getDrawable() == null) {
            Toast.makeText(getApplicationContext(), "Please put some image for Doc.", Toast.LENGTH_SHORT).show();

        } else
            Signup();
        //  Toast.makeText(getApplicationContext(),"Please put some image for menu.", Toast.LENGTH_SHORT).show();

    }

    private void Signup() {

        bar.setVisibility(View.VISIBLE);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);


        Log.e("filePath", "" + filePath);

        File file1 = new File(filePath1);
        Log.e("filePath", "" + file1);

        ///
        File file = new File(filePath);
        Log.e("filePathUserProof", "" + file);
        // Create a request body with file and image media type
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), fileReqBody);

        File file2 = new File(filePath1);
        Log.e("filePathIDProof", "" + file2);
        // Create a request body with file and image media type
        RequestBody fileReqBody2 = RequestBody.create(MediaType.parse("image/*"), file2);
        // Create MultipartBody.Part using file request-body,file name and part name
        MultipartBody.Part part2 = MultipartBody.Part.createFormData("image2", file2.getName(), fileReqBody2);


        RequestBody user_type = RequestBody.create(MediaType.parse("user_type"), StrUserType);
        RequestBody business_name = RequestBody.create(MediaType.parse("business_name"), Str_editbuisnessname);
        RequestBody name = RequestBody.create(MediaType.parse("name"), Str_editfirstname);
        RequestBody mobile = RequestBody.create(MediaType.parse("mobile"), Str_editmobile);
        RequestBody email = RequestBody.create(MediaType.parse("email"), Str_editregemail);
        RequestBody address = RequestBody.create(MediaType.parse("address"), Str_editaddress);
        RequestBody locatlity = RequestBody.create(MediaType.parse("locatlity"), Str_editlocality);
        RequestBody city = RequestBody.create(MediaType.parse("city"), Str_editCity);
        RequestBody state = RequestBody.create(MediaType.parse("state"), Str_editState);
        RequestBody country = RequestBody.create(MediaType.parse("country"), Str_editcountary);
        RequestBody pincode = RequestBody.create(MediaType.parse("pincode"), Str_editpin);
        RequestBody landline = RequestBody.create(MediaType.parse("landline"), Str_editlandlanine);
        RequestBody pancard = RequestBody.create(MediaType.parse("pancard"), Str_editpaan);
        RequestBody password = RequestBody.create(MediaType.parse("password"), Str_editpassword);
        RequestBody role = RequestBody.create(MediaType.parse("role"), Str_editrole);

        if (CommonUtils.isNetworkAvailable(getApplicationContext())) {
            Call<ResponseBody> call = apiInterface.Register(user_type, business_name, name,
                    mobile, email, address, locatlity, city, state, country, pincode, landline, pancard, password, role, part, part2);

            call.enqueue(new Callback<ResponseBody>() {

                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d("registerreesult1", response.body().toString());

                    bar.setVisibility(View.GONE);
                    Log.d("11111", "11111");

                    Log.d("2222", "2222");
                    try {
                        if (response.isSuccessful()) {
                            Log.d("22221", "22221");

                            String resturentResult = response.body().string();
                            Log.d("registesd",resturentResult);

                            JSONObject jsonObject = new JSONObject(resturentResult);

                            String status = jsonObject.optString("status");
                            String msg = jsonObject.optString("msg");
                            if (status.equals("1") ) {
                                Log.d("22223", "22223");

                                Toast.makeText(RegisterNumFinalDetail.this, msg, Toast.LENGTH_SHORT).show();

                               Intent i=new Intent(RegisterNumFinalDetail.this,LoginActivity.class);
startActivity(i);
                                finish();
                            } else {
                                Log.d("22210", "22210");

                                Toast.makeText(RegisterNumFinalDetail.this, msg, Toast.LENGTH_SHORT).show();

                            }
                        } else
                            Toast.makeText(RegisterNumFinalDetail.this, "Email Allreday Used", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                }
            });

        } else {
            bar.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
        }

    }


    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(RegisterNumFinalDetail.this);
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
   /* public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RegisterNumFinalDetail.this.RESULT_CANCELED) {
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
                    idimage.setImageBitmap(bitmap1);

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
            idimage.setImageBitmap(thumbnail1);

            filePath = saveImage(thumbnail);
            filePath = saveImage(thumbnail1);
            isImageEdit = "1";
            isDocEdit = "1";
        }
    }*/

    /*public String saveImage(Bitmap myBitmap) {
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
    }*/

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

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void requestMultiplePermissions() {
        Dexter.withActivity(RegisterNumFinalDetail.this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(RegisterNumFinalDetail.this, "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<com.karumi.dexter.listener.PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(RegisterNumFinalDetail.this, "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }





      /*  if(userimage.getDrawable() ==null)
            Toast.makeText(getApplicationContext(),"Please put some image for menu.", Toast.LENGTH_SHORT).show();
        }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RegisterNumFinalDetail.this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), contentURI);
                    if (isImageEdit.equals("1")) {
                        filePath = saveImage(bitmap);

                        userimage.setImageBitmap(bitmap);
                    } else if (isImageEdit.equals("2")) {
                        filePath1 = saveImage(bitmap);

                        idimage.setImageBitmap(bitmap);
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            try {
                if (isImageEdit.equals("1")) {
                    filePath = saveImage(thumbnail);

                    userimage.setImageBitmap(thumbnail);
                } else if (isImageEdit.equals("2")) {
                    filePath1 = saveImage(thumbnail);

                    idimage.setImageBitmap(thumbnail);
                }


            } catch (JsonIOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
            }


        }
    }
}
