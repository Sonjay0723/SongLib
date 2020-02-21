package Display;

import java.io.*;
import java.util.*;

import application.Song;
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
	
	private ObservableList<Song> songList = FXCollections.observableArrayList();
	
	@FXML ListView<Song> listView;
	
	@FXML private Button deleteBtn;
	@FXML private Button addBtn;
	@FXML private Button editBtn;
	
	@FXML private TextField nameTxt;
	@FXML private TextField artistTxt;
	@FXML private TextField albumTxt;
	@FXML private TextField yearTxt;
	
	public void start(Stage primaryStage) throws Exception {    
		primaryStage.setTitle("Song List View");
		
		File fp = new File("songs.txt");
		fp.createNewFile();
		
		songList.addAll(read(fp));
		
		listView
	        .getSelectionModel()
	        .selectedIndexProperty()
	        .addListener((obs, oldVal, newVal) -> whatInfo());

		if(!songList.isEmpty()) {
			listView.setItems(songList);
			listView.getSelectionModel().select(0);
			whatInfo();
		}

		addBtn.setOnAction(event->{
			if(agreeOrDisagree(primaryStage, "Would you like to add this song to the playlist?")) {
				add(nameTxt.getText(), artistTxt.getText(), albumTxt.getText(), yearTxt.getText(), primaryStage);
				try {
					write(songList, fp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		editBtn.setOnAction(event->{
			if(agreeOrDisagree(primaryStage, "Would you like edit this song?")){
				if (!songList.isEmpty()){
					Song currSong = songList.get((listView.getSelectionModel().getSelectedIndex()));
					edit(currSong,primaryStage);
					try {
						write(songList, fp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		deleteBtn.setOnAction(event->{
			if(agreeOrDisagree(primaryStage, "Would you like to remove this song from the playlist?")){
				if (!songList.isEmpty()){
					Song currSong = songList.get((listView.getSelectionModel().getSelectedIndex()));
					delete(currSong);
					try {
						write(songList, fp);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	
	public void add (String name, String artist, String album, String year, Stage primaryStage){
		
		//Creating Song object
		Song newSong = new Song(name,artist,album,year);
		
		//checking if fields are entered incorrectly
		if(name.isEmpty() || artist.isEmpty()) {
			popUpMessage(primaryStage, "The Name or Artist Fields are Empty!");
			return;
		}
		
		if(songList.isEmpty()) {
			songList.add(newSong);
			//select song
			listView.setItems(songList);
			listView.getSelectionModel().select(0);
			whatInfo();
			return;
		}
		
		//adding in appropriate location
		else if (!inList(newSong, primaryStage)) {
			for(int i=0; i<songList.size(); i++) {
				if(songList.get(i).compareTo(newSong) > 0) {
					if(i==0) {
						songList.add(0,newSong);
						//select song
						listView.setItems(songList);
						listView.getSelectionModel().select(0);
						whatInfo();
						return;
					}
					else if(i>=songList.size()) {
						songList.add(newSong);
						//select song
						listView.setItems(songList);
						listView.getSelectionModel().select(songList.size()-1);
						whatInfo();
						return;
					}
					else {
						songList.add(i, newSong);
						//select song
						listView.setItems(songList);
						listView.getSelectionModel().select(i);
						whatInfo();
						return;
					}
				}
				else if(songList.get(i).compareTo(newSong) < 0) {
					continue;
				}
			}
			songList.add(newSong);
			//select song
			listView.setItems(songList);
			listView.getSelectionModel().select(songList.size()-1);
			whatInfo();
			return;
		}
		
		return;
	}
	
	
	public void edit(Song currentSong, Stage primaryStage) {
			
		//find out which entries to edit
		String name,artist,album,year;
		name = nameTxt.getText();
		artist = artistTxt.getText();
		album = albumTxt.getText();
		year = yearTxt.getText();

		//add edited version after removing un-edited version
		if(name.isBlank()|| artist.isBlank()) {
			popUpMessage(primaryStage, "Name or Artist is Blank");
			return;
		}
		
		//create updated song and delete old one
		Song newSong = new Song(name,artist,album,year);
		delete(currentSong);
		
		//if the newSong does not exist in the playlist, add it to the playlist, otherwise add the original song back in
		if(!inList(newSong, primaryStage))
			add(name,artist,album,year,primaryStage);
		else
			add(currentSong.getName(),currentSong.getArtist(),currentSong.getAlbum(),currentSong.getYear(),primaryStage);
		   
		return;
	}
	
	public void delete(Song currentSong){
		
		//delete current song
		int currIndex = listView.getSelectionModel().getSelectedIndex();
		songList.remove(currIndex);
		listView.setItems(songList);
		
		//select next song in List
		if(!songList.isEmpty()) {
			if(songList.size() <= currIndex) {
				listView.getSelectionModel().select(currIndex-1);
				whatInfo();
			}
			else {
				listView.getSelectionModel().select(currIndex);
				whatInfo();
			}
		}
		else {
			nameTxt.clear();
			artistTxt.clear();
			albumTxt.clear();
			yearTxt.clear();
		}
		return;
		
	}
	
	//method to check if same name+artist is already in songList
	public boolean inList(Song search, Stage primaryStage){
		for(int i=0; i<songList.size(); i++) {
			if(songList.get(i).compareTo(search) == 0) {
				popUpMessage(primaryStage, "This Entry Already Exists in the List!");
				return true;
			}
		}
		return false;
	}
	
	//method to display values
	public void whatInfo() {
		int currentIndex = listView.getSelectionModel().getSelectedIndex();
		Song currSong = songList.get(currentIndex);
		nameTxt.setText(currSong.getName());
		artistTxt.setText(currSong.getArtist());
		albumTxt.setText(currSong.getAlbum());
		yearTxt.setText(currSong.getYear());
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
		sayYes.initOwner(primaryStage);
		sayYes.setContentText(displayText);
		
		ButtonType yesButton = new ButtonType("Continue");
		ButtonType noButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		
		sayYes.getButtonTypes().setAll(yesButton, noButton);
		
		Optional<ButtonType> result = sayYes.showAndWait();
		if (result.get() == yesButton)  {
			return true;
		}
		return false;
	}
	
	private void write(ObservableList<Song> songs, File fp) throws IOException {
		if (fp.delete()) {
			fp.createNewFile();
		}
		
		if (songs.size() == 0)
			return;
		
		PrintWriter  w1 = new PrintWriter(fp);
		for (int i = 0; i < songs.size(); i++) {
			String str = songs.get(i).toText();
			w1.write(str);
		}
		w1.close();
		return;
	}
	
	private ArrayList<Song> read(File fp) throws FileNotFoundException, IOException, ClassNotFoundException {
		Scanner r1 = new Scanner(fp);
		ArrayList<Song> res = new ArrayList<Song>();
		
		while (r1.hasNextLine()) {
			String name = r1.nextLine();
			String artist = r1.nextLine();
			String album = r1.nextLine();
			String year = r1.nextLine();
			
			res.add(new Song(name, artist, album, year));
		}
		return res;
	}

}
