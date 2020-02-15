package Library;

public class Song {
	
	private String name;
	private String artist;
	private String album;
	private int year;
	
	public Song(String name, String artist, String album, int year) {
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
	
	public int getYear() {
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
	
	public void setYear(int x) {
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
