package model;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private int id;
    private String albumTitle;
    private String artist;
    private List<Track> trackList;
    
    public Album() {
        this.id = 0;
        this.albumTitle = "Unknown";
        this.artist = "Unknown";
        this.trackList = new ArrayList<>();     
    }

    public Album(String albumTitle, String artist) {
        this.id = 0;
        this.albumTitle = albumTitle;
        this.artist = artist;
        this.trackList = new ArrayList<>();
    }

    public Album(String albumTitle, String artist, List<Track> trackList) { 
        this.id = 0;               
        this.albumTitle = albumTitle;
        this.artist = artist;
        this.trackList = trackList;
    }
    
   public Album(int id, String albumTitle, String artist) {
       this.id = id;        
       this.albumTitle = albumTitle;
       this.artist = artist;
       this.trackList = new ArrayList<>();
   }    

    public Album(int id, String albumTitle, String artist, List<Track> trackList) {
        this.id = id;
        this.albumTitle = albumTitle;
        this.artist = artist;
        this.trackList = trackList;
    }
    
    public int getId() {
        return this.id;
    }       

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbumTitle() {
        return this.albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<Track> getTrackList() {
        return this.trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }
    
    public void addTrack(Track track) {
        this.trackList.add(track);
    }             
    
    @Override
    public String toString() {
        return "\nAlbum Id: " + Integer.toString(this.id) + " - Title: " + this.albumTitle +            
                " - Artist: " + this.artist + "\nTracks: " + this.trackList + "\n";
    } 
}
