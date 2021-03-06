package com.softices.trainingapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.softices.trainingapp.R;
import com.softices.trainingapp.database.DatabaseHelper;
import com.softices.trainingapp.model.AppModel;

import java.io.IOException;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG="UpdateActivity";

    EditText edtName, edtMail, edtNumber, edtPassword;
    ImageView ivUserProfileImage;
    Toolbar toolbarUpdateUser;
    Button btnsavechange;
    DatabaseHelper databaseHelper;
    AppModel appModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        init();
        loadImageFromDB();
        appModel = databaseHelper.getRecord();
        setUserData();
    }

    @Override
    public void onClick(View v) {
        updateUser();
        Toast.makeText(UpdateActivity.this, "Data saved...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AccountDetailsActivity.class);
        startActivity(intent);
        finish();
    }

    Boolean loadImageFromDB(){
        try{
            byte[] bytes=databaseHelper.getImagefromDB();
            databaseHelper.close();

            //set image into iMage View from Database
            ivUserProfileImage.setImageBitmap(ImageUtiliti.getImage(bytes));
            return true;
        }catch (Exception e) {
            Log.e(TAG,"LoadImagefromDB" +e.getLocalizedMessage());
            databaseHelper.close();
            return false;
        }
    }


    public void updateUser() {
        boolean updateRecord = databaseHelper.updateData
                (edtName.getText().toString(),
                        edtMail.getText().toString(),
                        edtNumber.getText().toString(),
                        edtPassword.getText().toString(),
                        ivUserProfileImage.getImageMatrix().toString().getBytes());

        if (updateRecord == true) {
            Toast.makeText(UpdateActivity.this, "Data Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(UpdateActivity.this, "Data is not Updated", Toast.LENGTH_SHORT).show();
        }
    }

    public void setUserData() {
        databaseHelper.close();
        //old information of User
        edtName.setText(appModel.getUserName());
        edtMail.setText(appModel.getUserEmail());
        edtNumber.setText(String.valueOf(appModel.getUserNumber()));
        edtPassword.setText(appModel.getUserPassword());
    }

    public void init() {
        edtName = findViewById(R.id.edt_update_name);
        edtMail = findViewById(R.id.edt_update_email);
        edtNumber = findViewById(R.id.edt_update_number);
        edtPassword = findViewById(R.id.edt_update_password);
        btnsavechange = findViewById(R.id.btn_update_savechange);
        toolbarUpdateUser = findViewById(R.id.toolbar_update_user);

        setSupportActionBar(toolbarUpdateUser);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        btnsavechange.setOnClickListener(this);
        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}