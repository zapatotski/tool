package squad.model.bizmodel;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Team implements Serializable{

	public String name;
	public Integer id;
	public List<Player> players;
	public List<LastGame> lastGame;
	
	public Team() {}
	
	public Team(String name,Integer id,List<Player> players,List<LastGame> lastGame) {
		this.name=name;
		this.id=id;
		this.players=players;
		this.lastGame=lastGame;
	}
}
