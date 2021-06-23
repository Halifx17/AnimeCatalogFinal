package com.example.animecatalogfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Update_anime extends AppCompatActivity {

    EditText anime_name;
    EditText anime_genres;
    EditText anime_synopsis;
  //EditText anime_date;
    EditText anime_characters;
    EditText anime_image_url;


    String db_anime_name;
    String db_anime_genres;
    String db_anime_synopsis;
    String db_anime_date;
    String db_anime_characters;
    String db_anime_image_url;

    List<Anime> animeDetails;
    DataBaseOpenHelper dataBaseOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    int rowId = 0;

    Button btnDate;



    private DatePickerDialog.OnDateSetListener setListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_anime);

        dataBaseOpenHelper = new DataBaseOpenHelper(this);
        sqLiteDatabase = dataBaseOpenHelper.getWritableDatabase();

        anime_name = findViewById(R.id.editTextAnimeNameUpdate);
        anime_genres = findViewById(R.id.editTextAnimeGenresUpdate);
        anime_synopsis = findViewById(R.id.editTextAnimeSynopsisUpdate);
        btnDate = findViewById(R.id.btnDateUpdate);
        anime_characters = findViewById(R.id.editTextAnimeCharactersUpdate);
        anime_image_url = findViewById(R.id.editTextImageUrlUpdate);


        anime_synopsis.setMovementMethod(new ScrollingMovementMethod());

        anime_synopsis.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                anime_synopsis.getParent().requestDisallowInterceptTouchEvent(false);

                return false;
            }
        });

        anime_synopsis.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                anime_synopsis.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        anime_genres.setMovementMethod(new ScrollingMovementMethod());

        anime_genres.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                anime_genres.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        anime_genres.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                anime_genres.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        anime_characters.setMovementMethod(new ScrollingMovementMethod());

        anime_characters.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                anime_characters.getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });
        anime_characters.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                anime_characters.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


        rowId = getIntent().getIntExtra("anime_ID",-1);

        Cursor cursor = sqLiteDatabase.query(DatabaseInfo.ANIME_TABLE,null,DatabaseInfo.ANIME_ID + " = " + rowId,null,null,null,null);
        animeDetails = new ArrayList<Anime>();
        animeDetails.clear();

        if(cursor!=null && cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                anime_name.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.ANIME_NAME)));
                anime_genres.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.ANIME_GENRES)));
                anime_synopsis.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.ANIME_SYNOPSIS)));
                btnDate.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.ANIME_DATE)));
                anime_characters.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.ANIME_CHARACTERS)));
                anime_image_url.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.ANIME_IMAGE)));


            }
        }else{
                Toast.makeText(this,"No Data Found",Toast.LENGTH_SHORT).show();

            }


        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Update_anime.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });



        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = month +"-"+dayOfMonth+"-"+year;
                btnDate.setText(date);
            }
        };





        }

        public void clickUpdate(View view){
        db_anime_name = anime_name.getText().toString();
        db_anime_genres = anime_genres.getText().toString();
        db_anime_synopsis = anime_synopsis.getText().toString();
        db_anime_date = btnDate.getText().toString();
        db_anime_characters = anime_characters.getText().toString();
        db_anime_image_url = anime_image_url.getText().toString();

        if(TextUtils.isEmpty(db_anime_name)||
                TextUtils.isEmpty(db_anime_genres)||
                TextUtils.isEmpty(db_anime_synopsis)||
                TextUtils.isEmpty(db_anime_date)||
                TextUtils.isEmpty(db_anime_characters)||
                TextUtils.isEmpty(db_anime_image_url)){
            Toast.makeText(this,"Check Empty Fields",Toast.LENGTH_SHORT).show();
        }else{
            ContentValues cv = new ContentValues();
            cv.put(DatabaseInfo.ANIME_NAME,db_anime_name);
            cv.put(DatabaseInfo.ANIME_GENRES,db_anime_genres);
            cv.put(DatabaseInfo.ANIME_SYNOPSIS, db_anime_synopsis);
            cv.put(DatabaseInfo.ANIME_DATE,db_anime_date);
            cv.put(DatabaseInfo.ANIME_CHARACTERS,db_anime_characters);
            cv.put(DatabaseInfo.ANIME_IMAGE,db_anime_image_url);

            int updateID = sqLiteDatabase.update(DatabaseInfo.ANIME_TABLE,cv,DatabaseInfo.ANIME_ID + " = " +rowId,null);

            if(updateID != -1){
                Toast.makeText(this,"Update Successful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainActivity.class));
                finish();
            }else{
                Toast.makeText(this,"Failed to Update!",Toast.LENGTH_SHORT).show();
            }

        }

        }

    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        super.onDestroy();
    }
}
