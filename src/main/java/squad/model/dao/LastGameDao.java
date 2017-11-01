package squad.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import squad.model.bizmodel.LastGame;


public class LastGameDao {
	
	private File f = new File("team_lastgames.data");

	public Map<Integer, List<LastGame>> getGames() {
		return (Map<Integer, List<LastGame>>) deserialization(f);
	}

	public void setGames(Map<Integer, List<LastGame>> m) {
		serialization(f, m);
	}

	private void serialization(File f, Object m) {
		FileOutputStream fOut = null;
		ObjectOutputStream oOut = null;
		try {
			fOut = new FileOutputStream(f);
			oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(m);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Object deserialization(File f) {
		FileInputStream fInput = null;
		ObjectInputStream oInput = null;
		Object result = null;
		try {
			fInput = new FileInputStream(f);
			oInput = new ObjectInputStream(fInput);
			result = oInput.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
}
