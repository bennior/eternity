package options;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

import help.FileHelp;
import options.audio.OAudio;
import options.graphics.OGraphics;
import options.keybinds.OKeyBinds;

public class GameFiles {
	
	private static String fileName = "options";
	

	public void createOptions() { 
		File file = new File("options.txt");
		
		if(!file.exists()) {
			try {
				FileHelp.createFile(fileName);
				FileHelp.writeArray(fileName, append(merge(OGraphics.graphics, OAudio.audios, OKeyBinds.keybinds)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public static String initOptions(int linenumber, String title) throws IOException {
		String output = null;
			try {
				output = FileHelp.readFile(fileName, linenumber).substring(title.length() + 2);
			} catch (IOException | StringIndexOutOfBoundsException e) {
				FileHelp.clearFile(fileName);
				FileHelp.writeArray(fileName, append(merge(OGraphics.graphics, OAudio.audios, OKeyBinds.keybinds)));
				output = FileHelp.readFile(fileName, linenumber).substring(title.length() + 2);
			}
		return output;
	}
	
	public static void saveOptions() {
		try {
			FileHelp.clearFile(fileName);
			//graphics
			FileHelp.writeFile(fileName, append(OGraphics.graphics[0]) + OGraphics.lighting.getOption());
			FileHelp.writeFile(fileName, append(OGraphics.graphics[1]) + OGraphics.animations.getOption());
			FileHelp.writeFile(fileName, append(OGraphics.graphics[2]) + OGraphics.shadows.getOption());
			FileHelp.writeFile(fileName, append(OGraphics.graphics[3]) + OGraphics.res.getOption());
			FileHelp.writeFile(fileName, append(OGraphics.graphics[4]) + OGraphics.grass.getOption());
			//audio
			FileHelp.writeFile(fileName, append(OAudio.audios[0]) + OAudio.music.getPos());
			FileHelp.writeFile(fileName, append(OAudio.audios[1]) + OAudio.sounds.getPos());
			FileHelp.writeFile(fileName, append(OAudio.audios[2]) + OAudio.ambient.getPos());
			FileHelp.writeFile(fileName, append(OAudio.audios[3]) + OAudio.creatures.getPos());
			//keybinds
			FileHelp.writeFile(fileName, append(OKeyBinds.keybinds[0]) + OKeyBinds.up.getKey());
			FileHelp.writeFile(fileName, append(OKeyBinds.keybinds[1]) + OKeyBinds.left.getKey());
			FileHelp.writeFile(fileName, append(OKeyBinds.keybinds[2]) + OKeyBinds.right.getKey());
			FileHelp.writeFile(fileName, append(OKeyBinds.keybinds[3]) + OKeyBinds.down.getKey());
			FileHelp.writeFile(fileName, append(OKeyBinds.keybinds[4]) + OKeyBinds.inventory.getKey());
			FileHelp.writeFile(fileName, append(OKeyBinds.keybinds[5]) + OKeyBinds.destroy.getKey());
			FileHelp.writeFile(fileName, append(OKeyBinds.keybinds[6]) + OKeyBinds.place.getKey());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String[] merge(String[] array1, String[] array2, String[] array3) {
		return Stream.of(array1, array2, array3)
                .flatMap(Stream::of)        
                .toArray(String[]::new);
	}
	
	private static String[] append(String[] array) {
		for(int i = 0; i < array.length; i++) {
			array[i] += ": ";
		}
		return array;
	}
	
	private static String append(String option) {
		option += ": ";
		return option;
	}
	
}
