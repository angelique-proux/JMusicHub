/*
* Name of class : Music
*
* Description   : Class which manages the sound
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/


// Our package
package util;

// Packages from java
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.util.Scanner;
import java.util.Collections;
import java.util.LinkedList;

public class Music {

	/**
  *	Method that plays the sound given as argument
	* Only for songs and audiobooks
  * @param sound
  *				Sound we wanted to listen to
	* @param i
	*				Int used to know if an extract must be played or the entire sound
  */
	public static void PlaySound(File sound, int i) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			System.out.println("\nAdjust your volume, the music is playing!");
			// An extract is played
			if (i==1) {
				// Test the length of the sound to adapt the length of the extract
				if (clip.getMicrosecondLength()>250_000_000) {
					// If the length is more than 25s, three extracts will be played
					clip.setMicrosecondPosition(24_000_000);
					clip.start();
					Thread.sleep(7000);
					clip.setMicrosecondPosition(100_000_000);
					Thread.sleep(7000);
					clip.setMicrosecondPosition(200_000_000);
					Thread.sleep(15000);
				} else {
					// If not, two extracts will be played
					clip.setMicrosecondPosition(30_000_000);
					clip.start();
					Thread.sleep(14000);
					clip.setMicrosecondPosition(51_000_000);
					Thread.sleep(14000);
				}
			// The sound is played
			} else if (i==0) {
				clip.start();
				Thread.sleep(clip.getMicrosecondLength()/1000);
			}
			clip.stop();
			clip.close();
		} catch (Exception e) {
		}
	}

	/**
  *	Method that plays the sound list given as argument
	* Only for albums and playlists
  * @param soundList
  *				List of sounds we wanted to listen to
	* @param i
	*				Int used to know if an extract must be played or the entire sound
  */
	public static void PlayListOfSound(LinkedList<File> soundList, int i) {
		try {
			System.out.println("\nAdjust your volume, the music is playing!");
			Collections.shuffle(soundList);
			Clip clip = AudioSystem.getClip();
			// An extract is played
			if (i==1) {
				// For each sound, one extract is played
				for (int j=0; j<soundList.size();j++) {
					clip.open(AudioSystem.getAudioInputStream(soundList.get(j)));
					clip.setMicrosecondPosition(50_000_000);
					clip.start();
					Thread.sleep(8000);
					clip.stop();
					clip.close();
				}
			// The sound is played
			} else if (i==0) {
				// For each sound, the sound is played
				for (int j=0; j<soundList.size();j++) {
					clip.open(AudioSystem.getAudioInputStream(soundList.get(j)));
					clip.start();
					Thread.sleep(clip.getMicrosecondLength()/1000);
					clip.stop();
					clip.close();
				}
			}
		} catch (Exception e) {
		}
	}


	/**
  *	Method that enables to make a choice between listen the entire sound or an extract
	* Only for songs and audiobooks
  * @param musicFilepath
  *				File where the sound is
	* @param sc
	*				Scanner
	* @see PlaySound
  */
	public static void listenToSomeMusic(String musicFilepath, Scanner sc) {
		File mus = new File(musicFilepath); // Warning : it must be a .wav
		String decision;
		System.out.printf("To listen an extract, write « extract » ;\nTo listen the entire audio, write « listen ».\n");
		decision = sc.nextLine();
		if (decision.compareTo("extract")==0) {
			PlaySound(mus, 1);
		} else if (decision.compareTo("listen")==0) {
			PlaySound(mus, 0);
		}
	}

	/**
	*	Method that enables to make a choice between listen the entire sound or an extract
	* Only for albums and playlists
	* @param listMusicFilepath
	*				List of files where the sounds are
	* @param sc
	*				Scanner
	* @see PlayListOfSound
	*/
	public static void listenToAListOfMusics(LinkedList<String> listMusicFilepath, Scanner sc) {
		LinkedList<File> musList = new LinkedList<File>();
		for (int ind=0; ind<listMusicFilepath.size(); ind++) {
			musList.add(new File(listMusicFilepath.get(ind)));
		}
		String decision;
		System.out.printf("To listen an extract, write « extract » ;\nTo listen the entire list, write « listen ».\n");
		decision = sc.nextLine();
		if (decision.compareTo("extract")==0) {
			PlayListOfSound(musList, 1);
		} else if (decision.compareTo("listen")==0) {
			PlayListOfSound(musList, 0);
		}
	}

}
