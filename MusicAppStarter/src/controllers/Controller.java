package controllers;

import java.util.List;

import helpers.InputHelper;
import model.Album;
import model.Track;
import repositories.Repository;


public class Controller {
    private Repository repository;
    
    public Controller() {
        repository = new Repository();    	
    }    
    
    public void run() {
        boolean finished = false;
        InputHelper inputHelper = new InputHelper();
        do {        
            System.out.print("\nA. List Albums");
            System.out.print("\tB. Add Album");        
            System.out.print("\tC. Add Track");  
            System.out.print("\tQ. Quit\n");              
            char choice = inputHelper.readCharacter("Enter choice", "ABCQ");    
            switch (choice) {
                case 'A': 
                    listAlbums();
                    break;
                case 'B':  
                    addAlbum();
                    break;
                case 'C': 
                    addTrackToAlbum();
                    break;                   
                case 'Q': 
                    finished = true;
            }
        } while (!finished);
    } 
    
    public void listAlbums() {
        System.out.println("List Albums");
        System.out.println("===========");      	
        List<Album> albumsList = repository.getItems();
        System.out.println(albumsList);
    }     
    
    public void addAlbum() {
        System.out.println("Add Album");
        System.out.println("=========");    
        InputHelper inputHelper = new InputHelper();
        String albumTitle = inputHelper.readString("Enter new album title");
        String artist = inputHelper.readString("Enter artist");        
        Album newAlbum = new Album(albumTitle, artist);
        repository.add(newAlbum); 	
    }
    
    public void addTrackToAlbum() {
        System.out.println("Add Track");
        System.out.println("=========");       
        InputHelper inputHelper = new InputHelper();
        Album requiredAlbum = findAlbum();

        if (requiredAlbum != null) {
            System.out.println("Album\n==========\n" + requiredAlbum);   
            int trackNo = inputHelper.readInt("Enter track number", 100, 1);        
            String trackTitle = inputHelper.readString("Enter track title");
            int trackLength = inputHelper.readInt("Enter track length (in seconds)", 1200, 0);        
            Track newTrack = new Track(trackNo, trackTitle, trackLength, requiredAlbum.getId());      
            repository.addTrack(newTrack);
        }
    }	    
        
    private Album findAlbum() {
        InputHelper inputHelper = new InputHelper();             
        Album requiredAlbum=null;
        int albumId;        
        do {
            albumId = inputHelper.readInt("Enter Album Id (0 to Quit)");
            if (albumId != 0) {
                requiredAlbum = repository.getItem(albumId);           
                if (requiredAlbum != null)
                    return requiredAlbum;
                else
                    System.out.println("Album Not Found");
            }
        } while (albumId != 0);
        return null;
    }        

}
