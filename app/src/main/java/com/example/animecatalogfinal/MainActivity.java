package com.example.animecatalogfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DataBaseOpenHelper dataBaseOpenHelper;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Button btnAddAnime;


    List<Anime> animeDetails;
    SQLiteDatabase sqLiteDatabase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        btnAddAnime = findViewById(R.id.btnAddAnime);

        dataBaseOpenHelper = new DataBaseOpenHelper(this);
        sqLiteDatabase = dataBaseOpenHelper.getReadableDatabase();
        recyclerView = (RecyclerView) findViewById(R.id.recycler);


        animeDetails = new ArrayList<Anime>();
        animeDetails.clear();
        Cursor cursor = sqLiteDatabase.query(DatabaseInfo.ANIME_TABLE,null,null,null,null,null,null);


        if(cursor!=null && cursor.getCount()!=0){

            animeDetails.clear();
            while (cursor.moveToNext()){


                int animeID = cursor.getInt(0);
                String animeName = cursor.getString(1);
                String animeGenres = cursor.getString(2);
                String animeSynopsis = cursor.getString(3);
                String animeDate = cursor.getString(4);
                String animeCharacters = cursor.getString(5);
                String animeImage = cursor.getString(6);

                animeDetails.add(new Anime(animeID,animeName,animeGenres,animeDate,animeSynopsis,animeCharacters,animeImage));

            }

        }
        cursor.close();
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mAdapter = new RecyclerAdapter(animeDetails);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);





    }

    @Override
    protected void onDestroy() {
        sqLiteDatabase.close();
        super.onDestroy();
    }




    public void clickAddAnime(View view){
        startActivity(new Intent(this,Add_Anime.class));
    }

}


