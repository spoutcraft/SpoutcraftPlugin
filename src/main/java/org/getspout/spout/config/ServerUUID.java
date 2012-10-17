package org.getspout.spout.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.UUID;

import org.getspout.spout.Spout;

public class ServerUUID {
	
	private static String uuid;
	
	/**
	 * fetches the uuid set for the server, or creates one if it doesnt exist
	 * @return
	 */
	public static String getId() {
		
		if (uuid != null) return uuid;
		
		File uuidFile = new File(Spout.getInstance().getDataFolder(), "server.uuid");
		
		if (uuidFile.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(uuidFile));
				uuid = br.readLine().trim();
				br.close();
				
				if (!uuid.isEmpty()) {
					return uuid;
				}
				
				return createUUID();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return createUUID();
			}
		}
		return createUUID();
	}
	
	/**
	 * creates a new uuid for the server
	 * @return
	 */
	private static String createUUID() {
		File uuidFile = new File(Spout.getInstance().getDataFolder(), "server.uuid");
		
		if (uuidFile.exists()) {
			uuidFile.delete();
		}
		
		String newUuid = UUID.randomUUID().toString();
		
		try {
			PrintStream out = new PrintStream(new FileOutputStream(uuidFile));
			out.print(newUuid);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return newUuid;
		}
		return newUuid;
	}
	
}
