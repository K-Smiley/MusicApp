package daos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Album;
import model.Track;

public class TestDAO extends DAO {
	private List<Album> albumsList;

    public TestDAO() {
        albumsList = new ArrayList<>();
        Album album;
        Track track;
        album = new Album(1, "Close To The Edge", "Yes");
        track = new Track(1, 1, "Close To The Edge", 1110, 1);
    	album.addTrack(track);
        track = new Track(2, 2, "And You And I", 599, 1);
    	album.addTrack(track);
        track = new Track(3, 3, "Siberian Khatru", 536, 1);
    	album.addTrack(track);
        albumsList.add(album);
        album = new Album(2, "Florasongs", "The Decemberists");
        track = new Track(4, 1, "Why Would I Now?", 222, 2);
    	album.addTrack(track);
        track = new Track(5, 2, "Riverswim", 293, 2);
    	album.addTrack(track);
        track = new Track(6, 3, "Fits & Starts", 161, 2);
        album.addTrack(track);
        track = new Track(7, 4, "The Harrowed And The Haunted", 247, 2);
    	album.addTrack(track);
        track = new Track(8, 5, "Stateside", 205, 2);
    	album.addTrack(track);
        albumsList.add(album);
    }

    @Override
	public List<Album> getAlbums() {
        return albumsList;
    }

    @Override
	public Album getAlbum(int albumId) {
        Iterator<Album> it = albumsList.iterator();
        while (it.hasNext()) {
            Album album = (Album) it.next();
            if (album.getId() == albumId) {
                return album;
            }
        }
        return null;
    }

    @Override
	public void addAlbum(Album album) {
        int maxAlbumId = albumsList.stream().mapToInt(a -> a.getId()).max().getAsInt();
        album.setId(maxAlbumId+1);
    	albumsList.add(album);
    }

    @Override
	public void addTrack(Track track) {
        int maxTrackId = albumsList.stream().map(a -> a.getTrackList()).flatMap(x -> x.stream()).mapToInt(t -> t.getId()).max().getAsInt();
        Iterator<Album> it = albumsList.iterator();
        while (it.hasNext()) {
            Album album = (Album) it.next();
            if (album.getId() == track.getAlbumId()) {
                track.setId(maxTrackId+1);
                album.addTrack(track);
            }
        }
    }
}
