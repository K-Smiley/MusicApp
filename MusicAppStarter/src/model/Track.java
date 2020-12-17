package model;

public class Track {
    
    private int id;
    private int trackNo;
    private String trackTitle;
    private int trackLength;
    private int albumId;

    public Track() {
        this.id = 0;
        this.trackNo = 0;	
        this.trackTitle = "Unknown";
        this.trackLength = 0;
        this.albumId = 0;        
    }
    
   public Track(int trackNo, String trackTitle, int trackLength, int albumId) {  
       this.id = 0;
       this.trackNo = trackNo;	
       this.trackTitle = trackTitle;
       this.trackLength = trackLength;
       this.albumId = albumId;
   }    

    public Track(int id, int trackNo, String trackTitle, int trackLength, int albumId) {
        this.id = id;
    	this.trackNo = trackNo;    	
        this.trackTitle = trackTitle;
        this.trackLength = trackLength;
        this.albumId = albumId;        
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getTrackNo() {
        return this.trackNo;
    }

    public void setTrackNo(int trackNo) {
        this.trackNo = trackNo;
    }    

    public String getTrackTitle() {
        return this.trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public int getTrackLength() {
        return this.trackLength;
    }

    public void setTrackLength(int trackLength) {
        this.trackLength = trackLength;
    }    
    
    public int getAlbumId() {
        return this.albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }       
    
    private String getFormattedTrackLength() {
        return Integer.toString(this.trackLength / 60) + ":" + Integer.toString(this.trackLength % 60);
    }
    
    @Override
    public String toString() {
        return "\nId: " + Integer.toString(this.id) + " No: " + Integer.toString(trackNo) + " trackTitle: " + trackTitle + ", Length: " + getFormattedTrackLength() + " album: " + Integer.toString(albumId);
    }   
}
