package display;

import java.io.*;
import java.util.*;

import Library.Song;

public class SongContoller {
	
	public class newSong<Song> {
		
		Song anotherSong;
		newSong<Song> next;
		
		newSong (Song anotherSong, newSong<Song> next) {
			this.anotherSong = anotherSong;
			this.next = next;
		}
	}
	
	public newSong<Song> songList = null;
	
	public boolean add(String name, String artist, String album, String year){
		
		//Creating Song object
		Song addingSong = new Song(name,artist,album,year);
		
		//creating node to add to songList
		newSong<Song> temp= new newSong(addingSong,null);
		
		if(songList == null) {
			songList = temp;
		}
		else if (songList.getName().compareTo(name)==) {
			
		}
		
			
		
		
		return true;
	}
}
