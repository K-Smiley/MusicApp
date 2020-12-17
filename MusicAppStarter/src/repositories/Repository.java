package repositories;

import daos.DAO;
import daos.SQLDAO;
//import daos.TestDAO;

import java.util.List;
import model.Album;
import model.Track;


public class Repository implements RepositoryInterface {
	private DAO dao;
	
    public Repository() { 
    	//dao = new TestDAO(); 
    	dao = new SQLDAO();     	
    }
    
    @Override
    public List<Album> getItems() {       	
        return dao.getAlbums();
    }
    
    @Override
    public void add(Album item) {
        dao.addAlbum(item);
    }
    
    @Override
    public void addTrack(Track track) {
    	dao.addTrack(track);
    }    
       
    @Override
    public Album getItem(int id) {
        return dao.getAlbum(id);
    }
           
}
