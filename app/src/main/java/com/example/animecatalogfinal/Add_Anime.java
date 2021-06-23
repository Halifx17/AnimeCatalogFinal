package com.example.animecatalogfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
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

import java.util.Calendar;

public class Add_Anime extends AppCompatActivity {

    DataBaseOpenHelper dataBaseOpenHelper;
    SQLiteDatabase sqLiteDatabase;

    String db_anime_name;
    String db_anime_genres;
    String db_anime_synopsis;
    String db_anime_date;
    String db_anime_characters;
    String db_anime_image_url;

    Button add_anime;
    Button btnDate;





    EditText anime_name;
    EditText anime_genres;
    EditText anime_synopsis;
    EditText anime_characters;
    EditText anime_image_url;



    private DatePickerDialog.OnDateSetListener setListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_anime);


        add_anime = findViewById(R.id.AddAnime);


        dataBaseOpenHelper = new DataBaseOpenHelper(this);
        sqLiteDatabase = dataBaseOpenHelper.getWritableDatabase();

        anime_name = findViewById(R.id.editTextAnimeName);
        anime_genres = findViewById(R.id.editTextAnimeGenres);
        anime_synopsis = findViewById(R.id.editTextAnimeSynopsis);
        btnDate = findViewById(R.id.btnDate);
        anime_characters = findViewById(R.id.editTextAnimeCharacters);
        anime_image_url = findViewById(R.id.editTextImageUrl);


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




        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_Anime.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
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








    public void clickAdd(View view){
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

            Toast.makeText(this, "Check the Empty Fields",Toast.LENGTH_SHORT).show();
        }else{
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseInfo.ANIME_ID,(byte []) null);
            contentValues.put(DatabaseInfo.ANIME_NAME,db_anime_name);
            contentValues.put(DatabaseInfo.ANIME_GENRES,db_anime_genres);
            contentValues.put(DatabaseInfo.ANIME_SYNOPSIS,db_anime_synopsis);
            contentValues.put(DatabaseInfo.ANIME_DATE,db_anime_date);
            contentValues.put(DatabaseInfo.ANIME_CHARACTERS,db_anime_characters);
            contentValues.put(DatabaseInfo.ANIME_IMAGE,db_anime_image_url);

            long rowId = sqLiteDatabase.insert(DatabaseInfo.ANIME_TABLE,null,contentValues);

            if (rowId != -1){

                Toast.makeText(this,"Added",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainActivity.class));
                finish();
            }else {
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        super.onDestroy();
    }
}