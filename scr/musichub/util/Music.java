/*
* Name of class : Music
*
* Description   : Class which manages the sound
*
* Date          : 03/01/2021
*
* Copyright     : Manelle & Angélique
*/

package musichub.util;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.util.Scanner; //pour choisir ce qu'on veut écouter
import java.util.Collections; //pour mélanger les audios
import java.util.LinkedList; //pour avoir des listes d'audios

public class Music {

	public static void PlaySound(File sound, int i) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			System.out.println("\nAdjust your volume, the music is playing!");
			if (i==1) {
				if (clip.getMicrosecondLength()>250_000_000) {
					clip.setMicrosecondPosition(24_000_000);
					clip.start();
					Thread.sleep(7000);
					clip.setMicrosecondPosition(100_000_000);
					Thread.sleep(7000);
					clip.setMicrosecondPosition(200_000_000);
					Thread.sleep(15000);
				} else {
					clip.setMicrosecondPosition(30_000_000);
					clip.start();
					Thread.sleep(14000);
					clip.setMicrosecondPosition(51_000_000);
					Thread.sleep(14000);
				}
			} else if (i==0) {
				clip.start();
				Thread.sleep(clip.getMicrosecondLength()/1000);
			}
			clip.stop();
			clip.close();
		} catch (Exception e) {
		}
	}

	public static void PlayListOfSound(LinkedList<File> soundList, int i) {
		try {
			System.out.println("\nAdjust your volume, the music is playing!");
			Collections.shuffle(soundList);
			Clip clip = AudioSystem.getClip();
			if (i==1) {
				for (int j=0; j<soundList.size();j++) {
					clip.open(AudioSystem.getAudioInputStream(soundList.get(j)));
					clip.setMicrosecondPosition(50_000_000);
					clip.start();
					Thread.sleep(8000);
					clip.stop();
					clip.close();
				}
			} else if (i==0) {
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


	public static void listenToSomeMusic(String musicFilepath, Scanner sc) {
		File mus = new File(musicFilepath); //Warting : it must be a .wav
		String decision;
		System.out.printf("To listen an extract, write « extract » ;\nTo listen the entire audio, write « listen ».\n");
		decision = sc.nextLine();
		if (decision.compareTo("extract")==0) {
			PlaySound(mus, 1);
		} else if (decision.compareTo("listen")==0) {
			PlaySound(mus, 0);
		}
	}

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
