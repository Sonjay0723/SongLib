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
	
	ObservableList<Song> songList= null;
	
	public boolean add(String name, String artist, String album, int year){
		
		//Creating Song object
		Song newSong = new Song(name,artist,album,year);
		
		if(songList == null) {
			songList.add(newSong);
		}
		else if (songList.getName().compareTo(name)==) {
			
		}
		
			
