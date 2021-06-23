package com.example.animecatalogfinal;

public class Anime {

    private int animeId;
    private String animeName;
    private String animeGenres;
    private String animeDate;
    private String animeCharacters;
    private String animeImage;
    private String animeSynopsis;

    @Override
    public String toString() {
        return "Anime{" +
                "animeId=" + animeId +
                ", animeName='" + animeName + '\'' +
                ", animeGenres='" + animeGenres + '\'' +
                ", animeDate='" + animeDate + '\'' +
                ", animeCharacters='" + animeCharacters + '\'' +
                ", animeImage='" + animeImage + '\'' +
                ", animeSynopsis='" + animeSynopsis + '\'' +
                '}';
    }

    public Anime(int animeId, String animeName, String animeGenres, String animeDate,String animeSynopsis,String animeCharacters, String animeImage) {
        this.animeId = animeId;
        this.animeName = animeName;
        this.animeGenres = animeGenres;
        this.animeDate = animeDate;
        this.animeCharacters = animeCharacters;
        this.animeImage = animeImage;
        this.animeSynopsis = animeSynopsis;
    }

    public Anime(){

    }

    public String getAnimeSynopsis() {
        return animeSynopsis;
    }

    public void setAnimeSynopsis(String animeSynopsis) {
        this.animeSynopsis = animeSynopsis;
    }

    public int getAnimeId() {
        return animeId;
    }

    public void setAnimeId(int animeId) {
        this.animeId = animeId;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getAnimeGenres() {
        return animeGenres;
    }

    public void setAnimeGenres(String animeGenres) {
        this.animeGenres = animeGenres;
    }

    public String getAnimeDate() {
        return animeDate;
    }

    public void setAnimeDate(String animeDate) {
        this.animeDate = animeDate;
    }

    public String getAnimeCharacters() {
        return animeCharacters;
    }

    public void setAnimeCharacters(String animeCharacters) {
        this.animeCharacters = animeCharacters;
    }

    public String getAnimeImage() {
        return animeImage;
    }

    public void setAnimeImage(String animeImage) {
        this.animeImage = animeImage;
    }
}
