package com.softices.trainingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.softices.trainingapp.DatabaseHelper;
import com.softices.trainingapp.R;
import com.softices.trainingapp.model.AppModel;

public class AccountDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivPressBack;
    TextView tvEmail, tvName, tvPassword, tvNumber;
    Button btnDeleteUser, btnUpdateUser;
    Toolbar toolbarProfile;
    DatabaseHelper databaseHelper;
    AppModel appModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);

        init();

        displayUserData();

        updateDataDisplay();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
//            case R.id.btn_delete_user:
//                deleteUser();
//                Toast.makeText(AccountDetailsActivity.this, "Your prifile has been delete.", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(AccountDetailsActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//                break;
            case R.id.btn_update_user:
                Intent intent = new Intent(AccountDetailsActivity.this, UpdateActivity.class);
                startActivity(intent);
                break;
        }
    }
    public void init(){
        toolbarProfile=findViewById(R.id.toolbar_user_profile);
        setSupportActionBar(toolbarProfile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tvEmail = findViewById(R.id.tv_email_profile);
        tvName = findViewById(R.id.tv_name_profile);
        tvPassword = findViewById(R.id.tv_password_profile);
        tvNumber = findViewById(R.id.tv_number_profile);
        btnDeleteUser = findViewById(R.id.btn_delete_user);
        btnUpdateUser = findViewById(R.id.btn_update_user);
//        btnDeleteUser.setOnClickListener(this);
        btnUpdateUser.setOnClickListener(this);
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
//    public void deleteUser() {
//        databaseHelper.deleteRecord(appModel);
//    }

    public void displayUserData(){
        appModel = databaseHelper.getRecord();
        tvName.setText(appModel.getUserName());
        tvEmail.setText(appModel.getUserEmail());
        tvPassword.setText(appModel.getUserPassword());
        tvNumber.setText(String.valueOf(appModel.getUserNumber()));
    }

    public void updateDataDisplay(){
        appModel = databaseHelper.getRecord();
        tvName.setText(appModel.getUserName());
        tvEmail.setText(appModel.getUserEmail());
        tvPassword.setText(appModel.getUserPassword());
        tvNumber.setText(String.valueOf(appModel.getUserNumber()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayUserData();
    }
}