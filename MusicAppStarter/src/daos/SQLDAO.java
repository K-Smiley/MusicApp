package daos;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Album;
import model.Track;


public class SQLDAO extends DAO {
    static private String userName = "root";
    static private String password = "El13abeth*";		
    static private String url = "jdbc:mysql://localhost:3306/music";
    //static private String url = "jdbc:mariadb://localhost:3306/music";    
    static private Connection connection = null;
    static private Statement stmt = null;
    static private ResultSet rs = null;  
        
    public SQLDAO() {
        try {
            connection = DriverManager.getConnection(url, userName, password);
            stmt = connection.createStatement();
            System.out.println("Connected to database");            
        } catch (SQLException ex) { System.out.println(ex);} 
    }
    
    @Override
    public List<Album> getAlbums() {
        List<Album> albumsList = new ArrayList<>();
        try {
            //String queryString = "SELECT * FROM ALBUM ORDER BY ALBUMID";
            String queryString = "CALL GETALBUMS()";            
            rs = stmt.executeQuery(queryString);
            
            while (rs.next()) {
                int albumId = rs.getInt("ALBUMID");
                String albumTitle = rs.getString("ALBUMTITLE");
                String artist = rs.getString("ARTIST");                
                Album album = new Album(albumId, albumTitle, artist);
                
                List<Track> trackList = new ArrayList<>();
                //String queryString2 = "SELECT TRACKID, TRACKNO, TRACKTITLE, TRACKLENGTH FROM TRACK ";
                //queryString2 += "WHERE ALBUMID = " + albumId;
                String queryString2 = "CALL GETALBUMTRACKS(" + Integer.toString(albumId) + ")";
                Statement stmt2 = connection.createStatement();                
                ResultSet rs2 = stmt2.executeQuery(queryString2);
                while (rs2.next()) {
                    int trackId = rs2.getInt("TRACKID");                	
                    int trackNo = rs2.getInt("TRACKNO");
                    String trackTitle = rs2.getString("TRACKTITLE");
                    int trackLength = rs2.getInt("TRACKLENGTH");
                    Track track = new Track(trackId, trackNo, trackTitle, trackLength, albumId);
                    trackList.add(track);                    
                }
                album.setTrackList(trackList);                
                albumsList.add(album);
            }            
        } catch (SQLException ex) {}  

        return albumsList;
    }
    
    @Override
	public Album getAlbum(int albumId) {
        try {
            //String queryString = "SELECT * FROM ALBUM WHERE ALBUMID = " + Integer.toString(albumId);
            String queryString = "CALL GETALBUM(" + Integer.toString(albumId) + ")";
            rs = stmt.executeQuery(queryString);
            
            if (rs.next()) {
                
                String albumTitle = rs.getString("ALBUMTITLE");
                String artist = rs.getString("ARTIST");                
                Album album = new Album(albumId, albumTitle, artist);
                
                List<Track> trackList = new ArrayList<>();
                
                String queryString2 = "CALL GETALBUMTRACKS(" + Integer.toString(albumId) + ")";
                Statement stmt2 = connection.createStatement();                
                ResultSet rs2 = stmt2.executeQuery(queryString2);
                
                while (rs2.next()) {
                    int trackId = rs2.getInt("TRACKID");                	
                    int trackNo = rs2.getInt("TRACKNO");
                    String trackTitle = rs2.getString("TRACKTITLE");
                    int trackLength = rs2.getInt("TRACKLENGTH");
                    Track track = new Track(trackId, trackNo, trackTitle, trackLength, albumId);
                    trackList.add(track);                    
                }
                album.setTrackList(trackList);
                return album;                
            }
        } catch (SQLException ex) {System.out.println(ex);}       	
        return null;
    }
    
    @Override
    public void addAlbum(Album album) {
        //String queryString = "INSERT INTO ALBUM(ALBUMTITLE, ARTIST) VALUES('";
        String queryString = "CALL ADDALBUM('";
        queryString += album.getAlbumTitle() + "', '";        
        queryString += album.getArtist() + "')";
        System.out.println(queryString);
        try {
            stmt.executeUpdate(queryString);
        } catch (SQLException ex) { System.out.println(ex);}          
    }
    
    @Override
    public void addTrack(Track track) {
        String queryString = "CALL ADDTRACK(";
        //String queryString = "INSERT INTO TRACK(TRACKNO, TRACKTITLE, TRACKLENGTH, ALBUMID) VALUES(";
        queryString += track.getTrackNo() + ", '";
        queryString += track.getTrackTitle() + "', ";
        queryString += track.getTrackLength() + ", ";        
        queryString += track.getAlbumId() + ")";
        System.out.println(queryString);
        try {
            stmt.executeUpdate(queryString);
        } catch (SQLException ex) {System.out.println(ex);}    	   			        
	}
}	
