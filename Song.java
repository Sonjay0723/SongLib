package application;

public class Song {
	private String name;
	private String artist;
	private String album;
	private String year;
	
	public Song(String name, String artist, String album, String year) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public String getName() {
		return name;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setName(String x) {
		this.name = x;
	}
	
	public void setArtist(String x) {
		this.artist = x;
	}
	
	public void setAlbum(String x) {
		this.album = x;
	}
	
	public void setYear(String x) {
		this.year = x;
	}
	
	public String toString() {
		return (this.name + " - " + this.artist);
	}
	
	public int compareTo(Song x) {
		int a = this.name.toLowerCase().compareTo(x.name.toLowerCase());
		
		if (a == 0)
			return this.artist.toLowerCase().compareTo(x.artist.toLowerCase());
		else
			return a;
	}
}
