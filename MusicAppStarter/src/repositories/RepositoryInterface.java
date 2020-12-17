package repositories;

import java.util.List;
import model.Album;
import model.Track;

public interface RepositoryInterface {

    void add(Album item);

    Album getItem(int id);

    List<Album> getItems();

    void addTrack(Track track);
}
