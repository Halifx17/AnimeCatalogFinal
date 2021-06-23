package com.example.animecatalogfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class View_anime extends AppCompatActivity {



    TextView anime_name;
    TextView anime_genres;
    TextView anime_synopsis;
    TextView anime_date;
    TextView anime_characters;
    ImageView anime_image_url;


    Button btnHomeView;
    Button btnEditView;
    Button btnDeleteView;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_anime);

        dataBaseOpenHelper = new DataBaseOpenHelper(this);
        sqLiteDatabase = dataBaseOpenHelper.getWritableDatabase();

        anime_name = findViewById(R.id.editTextAnimeNameView);
        anime_genres = findViewById(R.id.editTextAnimeGenresView);
        anime_synopsis = findViewById(R.id.editTextAnimeSynopsisView);
        anime_date = findViewById(R.id.editTextAnimeDateView);
        anime_characters = findViewById(R.id.editTextAnimeCharactersView);
        anime_image_url = findViewById(R.id.editTextImageUrlView);

        btnHomeView = findViewById(R.id.btnViewView);
        btnEditView = findViewById(R.id.btnEditView);
        btnDeleteView = findViewById(R.id.btnDeleteView);


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




        rowId = getIntent().getIntExtra("anime_ID",-1);

        Cursor cursor = sqLiteDatabase.query(DatabaseInfo.ANIME_TABLE,null,DatabaseInfo.ANIME_ID + " = " + rowId,null,null,null,null);
        animeDetails = new ArrayList<Anime>();
        animeDetails.clear();

        if(cursor!=null && cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                anime_name.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.ANIME_NAME)));
                anime_genres.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.ANIME_GENRES)));
                anime_synopsis.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.ANIME_SYNOPSIS)));
                anime_date.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.ANIME_DATE)));
                anime_characters.setText(cursor.getString(cursor.getColumnIndex(DatabaseInfo.ANIME_CHARACTERS)));
                Glide.with(this).load(cursor.getString(cursor.getColumnIndex(DatabaseInfo.ANIME_IMAGE))).into(anime_image_url);


            }
        }else{
            Toast.makeText(this,"No Data Found",Toast.LENGTH_SHORT).show();

        }

        btnHomeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        btnEditView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Update_anime.class);
                intent.putExtra("anime_ID",rowId);
                startActivity(intent);
            }
        });

        btnDeleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(View_anime.this);
                builder.setTitle("DELETE ENTRY");
                builder.setMessage("Delete Anime Entry?");
                builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {




                    }
                });

                builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dataBaseOpenHelper = new DataBaseOpenHelper(View_anime.this);
                        sqLiteDatabase = dataBaseOpenHelper.getWritableDatabase();
                        sqLiteDatabase.delete(DatabaseInfo.ANIME_TABLE,DatabaseInfo.ANIME_ID+ " = " + rowId,null);
                        sqLiteDatabase.close();
                        Toast.makeText(getApplicationContext(), "Anime Successfully Removed",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();

                    }
                });

                builder.show();




            }
        });





    }

    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        super.onDestroy();
    }
}