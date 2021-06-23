package com.example.animecatalogfinal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.AnimeViewer>{

    List<Anime> animeDetails;
    Context context;
    DataBaseOpenHelper dataBaseOpenHelper;
    SQLiteDatabase sqLiteDatabase;


    public RecyclerAdapter(List<Anime> animeDetails){
        this.animeDetails = animeDetails;
    }


    @Override
    public RecyclerAdapter.AnimeViewer onCreateViewHolder( ViewGroup parent, int viewType) {




        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View animeView = inflater.inflate(R.layout.anime_view, parent,false);
        AnimeViewer viewHolder = new AnimeViewer(animeView);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder( RecyclerAdapter.AnimeViewer holder, int position) {

        final Anime anime = animeDetails.get(position);

        holder.animeName.setText("Anime Name: "+anime.getAnimeName());
        holder.animeGenres.setText("Genres: "+anime.getAnimeGenres());
        holder.animeDate.setText("Date Aired: "+anime.getAnimeDate());
      //  holder.animeImage.setText("ANIME IMAGE: "+anime.getAnimeImage());
     //   holder.animeSynopsis.setText("ANIME SYNOPSIS: "+anime.getAnimeSynopsis());
        Glide.with(context).load(anime.getAnimeImage()).into(holder.animeImage);
    //    holder.animeID.setText("Anime ID: "+anime.getAnimeId());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
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

                        final  int aID = anime.getAnimeId();
                        dataBaseOpenHelper = new DataBaseOpenHelper(context);
                        sqLiteDatabase = dataBaseOpenHelper.getWritableDatabase();
                        sqLiteDatabase.delete(DatabaseInfo.ANIME_TABLE,DatabaseInfo.ANIME_ID+ " = " + aID,null);

                        notifyItemRangeChanged(position,animeDetails.size());
                        animeDetails.remove(position);
                        notifyItemRemoved(position);
                        sqLiteDatabase.close();
                        Toast.makeText(context.getApplicationContext(), "Anime Successfully Removed",Toast.LENGTH_SHORT).show();

                    }
                });

                builder.show();

            }


        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Update_anime.class);
                intent.putExtra("anime_ID",anime.getAnimeId());
                context.startActivity(intent);
            }
        });

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,View_anime.class);
                intent.putExtra("anime_ID",anime.getAnimeId());
                context.startActivity(intent);
            }
        });




     //   holder.animeCharacters.setText("ANIME CHARACTERS: "+anime.getAnimeCharacters());
    }




    @Override
    public int getItemCount() {
        return animeDetails.size();
    }


    public class AnimeViewer extends RecyclerView.ViewHolder{
        ImageView animeImage;
        TextView animeName;
        TextView animeGenres;
        TextView animeDate;
      //  TextView animeCharacters;
       // TextView animeSynopsis;
      TextView animeID;
      Button btnView;
      Button btnEdit;
      Button btnDelete;

        public AnimeViewer(View itemView) {
            super(itemView);


            animeName = itemView.findViewById(R.id.txtName);
            animeGenres = itemView.findViewById(R.id.txtGenres);
            animeDate = itemView.findViewById(R.id.txtDate);
        //    animeCharacters = itemView.findViewById(R.id.textCharacters);
            animeImage = itemView.findViewById(R.id.animeImage);
        //    animeSynopsis = itemView.findViewById(R.id.textSynopsis);
        //   animeID = itemView.findViewById(R.id.textID);
           btnView = itemView.findViewById(R.id.btnView);
           btnEdit = itemView.findViewById(R.id.btnEdit);
           btnDelete = itemView.findViewById(R.id.btnDelete);



        }


        public void deleteProduct(){

        }
    }



}
