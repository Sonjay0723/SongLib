package display;

import java.io.*;
import java.util.*;

import Library.Song;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SongContoller {
	
ArrayList<Song> songs;
	
	private ObservableList<Song> songList= FXCollections.observableArrayList();
	
	@FXML ListView<Song> listView;
	
	
	public void start(Stage primaryStage) {
        primaryStage.setTitle("Song List View");        
        
        listView.setItems(songList);
        listView.getSelectionModel().select(0);
        
        //listView.setPrefSize(200, 250);
        //listView.setEditable(true);
	}
	
	public void add(String name, String artist, String album, int year){
		
		//Creating Song object
		Song newSong = new Song(name,artist,album,year);
		
		//checking if fields are entered incorrectly
		//TODO FXML pop up
		if(name.isEmpty() && artist.isEmpty()) {
			
		}
		if(name.isEmpty()) {
			
		}
		if(artist.isEmpty()) {
			
		}
			
		if(songList == null) {
			songList.add(newSong);
			
		}
		
		else if (!inList(newSong)) {
			for(int i=0; i<songList.size(); i++) {
				if(songList.get(i).getName().compareTo(name) == 0) {
					if(songList.get(i).getArtist().compareTo(artist)<0) {
						if(i+1>=songList.size())
							songList.add(newSong);
						else
							songList.add(i+1, newSong);
						
					}
					else if(songList.get(i).getArtist().compareTo(artist)>0) {
						songList.add(i, newSong);
						
					}
				}
				else if(songList.get(i).getName().compareTo(name) > 0) {
					songList.add(i, newSong);
					
				}
				else if (songList.get(i).getName().compareTo(name) < 0) {
					if(i+1>=songList.size())
						songList.add(newSong);
					else
						songList.add(i+1, newSong);
					
					
				}
			}
			
			//TODO select added song at end
		}
		
		
	}
	
	public void edit(Song currentSong) {
		
		//TODO take in changes to current selected song
		String name = "";
		String artist = "";
		int year = 0;
		String album = "";
		
		if(!inList(currentSong)) {
			//TODO update song
		}
		
	}
		
	public boolean inList(Song search){
		for(int i=0; i<songList.size(); i++) {
			if(songList.get(i).compareTo(search) == 0) {
				//POP UP MESSAGE ALREADY EXISTS
				return false;
			}
		}
		return true;
	}
	
}
