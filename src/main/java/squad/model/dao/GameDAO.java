package squad.model.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import squad.model.bizmodel.Game;
import squad.model.bizmodel.LastGame;

public class GameDAO {
	private File f = new File("games.data");

	public List<Game> getGames() {
		return (List<Game>) deserialization(f);
	}

	public void setGames(List<Game> result) {
		serialization(f, result);
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
