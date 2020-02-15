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

public class SongController {
	
	@FXML private ListView<Song> songs;
	@FXML private Button deleteBtn;
	@FXML private Button addBtn;
	@FXML private Button editBtn;
	
	@FXML private TextField nameTxt;
	@FXML private TextField artistTxt;
	@FXML private TextField albumTxt;
	@FXML private TextField yearTxt;
	
	ObservableList<Song> songList = FXCollections.observableArrayList();
	
	public boolean add(String name, String artist, String album, int year){
		
		//Creating Song object
		Song newSong = new Song(name,artist,album,year);
		
		//checking if fields are entered incorrectly
		//TODO FXML pop up
		if(name.isEmpty() || artist.isEmpty()) {
			return false;
		}
			
		if(songList == null) {
			songList.add(newSong);
			return true;
		}
		
		else if (!inList(newSong)) {
			for(int i=0; i<songList.size(); i++) {
				if(songList.get(i).getName().compareTo(name) == 0) {
					if(songList.get(i).getArtist().compareTo(artist)<0) {
						if(i+1>=songList.size())
							songList.add(newSong);
						else
							songList.add(i+1, newSong);
						return true;
					}
					else if(songList.get(i).getArtist().compareTo(artist)>0) {
						songList.add(i, newSong);
						return true;
					}
				}
				else if(songList.get(i).getName().compareTo(name) > 0) {
					songList.add(i, newSong);
					return true;
				}
				else if (songList.get(i).getName().compareTo(name) < 0) {
					if(i+1>=songList.size())
						songList.add(newSong);
					else
						songList.add(i+1, newSong);
					
					return true;
				}
			}
			
			//TODO select added song at end
		}
		
		return false;
	}
	
	public void delete(String name, String artist) {
		
		Song temp = new Song(name, artist, "", 0);
		
		for (int i = 0; i < songList.size(); i++) {
			if (songList.get(i).compareTo(temp) == 0) {
				songList.remove(i);
				return;
			}
		}
	}
		
	public boolean inList(Song search){
		for(int i=0; i<songList.size(); i++) {
			if(songList.get(i).compareTo(search) == 0) {
				//POP UP MESSAGE
				return false;
			}
		}
		return true;
	}
	
}
