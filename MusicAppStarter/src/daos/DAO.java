package daos;

import java.util.List;

import model.Album;
import model.Track;

public abstract class DAO {
    public abstract void addAlbum(Album album);
    public abstract void addTrack(Track track);	
    public abstract Album getAlbum(int albumId);
    public abstract List<Album> getAlbums();
}