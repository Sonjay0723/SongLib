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
	
	private ObservableList<Song> songList = FXCollections.observableArrayList();
	
	@FXML ListView<Song> listView;
	
	@FXML private Button deleteBtn;
	@FXML private Button addBtn;
	@FXML private Button editBtn;
	
	@FXML private TextField nameTxt;
	@FXML private TextField artistTxt;
	@FXML private TextField albumTxt;
	@FXML private TextField yearTxt;
	
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Song List View");        

		listView.setItems(songList);
		listView.getSelectionModel().select(0);

		addBtn.setOnAction(event->{
			if(agreeOrDisagree(primaryStage, "Would you like to add this song to the playlist?"))
				add(nameTxt.getText(), artistTxt.getText(), albumTxt.getText(), yearTxt.getText(), primaryStage);
		});

		editBtn.setOnAction(event->{
			if(agreeOrDisagree(primaryStage, "Would you like edit this song?")){
				if (!songList.isEmpty()){
					Song currSong = songList(listView.getSelectionModel().getSelectedIndex());
					edit(currSong,primaryStage);
				}
			}
		});
		
		deleteBtn.setOnAction(event->{
			if(agreeOrDisagree(primaryStage, "Would you like to remove this song from the playlist?")){
				if (!songList.isEmpty()){
					Song currSong = songList(listView.getSelectionModel().getSelectedIndex());
					delete(currSong);
				}
			}
		});
	}
	
	public void add(String name, String artist, String album, String year, Stage primaryStage){
		
		//Creating Song object
		Song newSong = new Song(name,artist,album,year);
		
		//checking if fields are entered incorrectly
		if(name.isEmpty() || artist.isEmpty()) {
			popUpMessage(primaryStage, "The Name or Artist Fields are Empty!");	
			nameTxt.clear();
			artistTxt.clear();
			albumTxt.clear();
			yearTxt.clear();
			return;
		}
			
		if(songList.isEmpty()) {
			songList.add(newSong);
			//select song
			listView.setItems(songList);
			listView.getSelectionModel().select(0);
		}
		
		//adding in appropriate location
		else if (!inList(newSong, primaryStage)) {
			for(int i=0; i<songList.size(); i++) {
				if(songList.get(i).compareTo(newSong) > 0) {
					if(i+1>=songList.size()) {
						songList.add(newSong);
						//select song
						listView.setItems(songList);
						listView.getSelectionModel().select(songList.size()-1);
					}
					else {
						songList.add(i+1, newSong);
						//select song
						listView.setItems(songList);
						listView.getSelectionModel().select(i+1);
					}
				}
				else if(songList.get(i).compareTo(newSong) < 0) {
					continue;
				}
			}
		}
		
		nameTxt.clear();
		artistTxt.clear();
		albumTxt.clear();
		yearTxt.clear();
		return;
	}
	
	public void edit(Song currentSong, Stage primaryStage) {
			
		//find out which ites to edit
		String name,artist,album,year;
		if(!nameTxt.getText().isEmpty())
			name = nameTxt.getText();
		else
			name = currentSong.getName();
		if(!artistTxt.getText().isEmpty())
			artist = artistTxt.getText();
		else
			artist = currentSong.getArtist();
		if(!albumTxt.getText().isEmpty())
			album = albumTxt.getText();
		else
			album = currentSong.getAlbum();
		if(!yearTxt.getText().isEmpty())
			year = yearTxt.getText();
		else
			year = currentSong.getYear();

		//add edited version after removing un-edited version
		Song newSong = new Song(name,artist,album,year);
		if(!inList(newSong, primaryStage){
			delete(currentSong);
			add(name,artist,album,year,primaryStage);
		}
		   
		return;
	}
	
	public void delete(Song currentSong){
		
		//delete current song
		int currIndex = listView.getSelectionModel().getSelectedIndex();
		songList.remove(currIndex);
		listView.setItems(songList);
		
		//select next song in List
		if(songList.size() <= currIndex)
			listView.getSelectionModel().select(currIndex-1);
		else 
			listView.getSelectionModel().select(selectIndex);
		
		//clear text
		nameTxt.clear();
		artistTxt.clear();
		albumTxt.clear();
		yearTxt.clear();
		return;
		
	}
	
	//method to check if same name+artist is already in songlist
	public boolean inList(Song search, Stage primaryStage){
		for(int i=0; i<songList.size(); i++) {
			if(songList.get(i).compareTo(search) == 0) {
				popUpMessage(primaryStage, "This Entry Already Exists in the List!");
				nameTxt.clear();
				artistTxt.clear();
				albumTxt.clear();
				yearTxt.clear();
				return true;
			}
		}
		return false;
	}
	
	//method for warning signature
	public void popUpMessage(Stage primaryStage, String displayText) {
		Alert warning = new Alert(AlertType.WARNING);
		warning.initOwner(primaryStage);
		warning.setTitle("We ran into an issue...");
		warning.setHeaderText(displayText);
		warning.showAndWait();
	}
	
	//method to allow user to back out of decision
	public boolean agreeOrDisagree(Stage primaryStage, String displayText) {
		Alert sayYes = new Alert(AlertType.CONFIRMATION);
		confirmation.initOwner(primaryStage);
		confirmation.setContentText(displayText);
		
		ButtonType yesButton = new ButtonType("Continue");
		ButtonType noButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		
		confirmation.getButtonTypes().setAll(yesButton, cancelButton);
		
		Optional<ButtonType> result = confirmation.showAndWait();
		if (result.get() == yesButton)  {
			return true;
		}
		return false;
	}
	
}
