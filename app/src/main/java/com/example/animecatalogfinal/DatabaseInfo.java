package com.example.animecatalogfinal;

import android.provider.BaseColumns;

public class DatabaseInfo implements BaseColumns {

    private DatabaseInfo(){

    }

        public static final String ANIME_TABLE = "ANIME_TABLE";
        public static final String ANIME_ID = "ANIME_ID";
        public static final String ANIME_NAME = "ANIME_NAME";
        public static final String ANIME_GENRES = "ANIME_GENRES";
        public static final String ANIME_SYNOPSIS = "ANIME_SYNOPSIS";
        public static final String ANIME_DATE = "ANIME_DATE";
        public static final String ANIME_CHARACTERS = "ANIME_CHARACTERS";
        public static final String ANIME_IMAGE = "ANIME_IMAGE";

}
