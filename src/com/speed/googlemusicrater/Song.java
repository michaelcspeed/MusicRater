package com.speed.googlemusicrater;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Michael Speed
 * 
 */
public class Song {

	public String id;
	public String album;
	public String artist;
	public String track;
	public int rating;
	public int idOfLastSongRated;
	public long timeLastPlayed;

	/**
	 * Checks if it is appropriate to rate a song yet. It must have been an hour
	 * since the last rating, and the last song it was rated against must be
	 * different to the last time.
	 * 
	 * @param id
	 *            of the last song that was rated.
	 * @return appropriateness true or false.
	 */
	public boolean readyToRate(int lastSongId) {
		if (System.currentTimeMillis() - timeLastPlayed > (1000 * 60 * 60)) {
			if (lastSongId != idOfLastSongRated) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Constructor
	 */
	public Song(String album, String artist, String track, int rating,
			int idOfLastSongRated, long timeLastPlayed) {
		
		super();
		this.id = getID(album,artist,track);
		this.album = album;
		this.artist = artist;
		this.track = track;
		this.rating = rating;
		this.idOfLastSongRated = idOfLastSongRated;
		this.timeLastPlayed = timeLastPlayed;
	}

	public String getID(String album2, String artist2, String track2) {
		return md5(album2, artist2, track2);
	}
	
	private String md5(String album2, String artist2, String track2) {
		
		String s = album2+artist2+track2;
		
	    try {
	        // Create MD5 Hash
	        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();

	        // Create Hex String
	        StringBuffer hexString = new StringBuffer();
	        for (int i=0; i<messageDigest.length; i++)
	            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
	        return hexString.toString();

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return "";
	}

}
