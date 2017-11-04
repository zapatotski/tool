package squad.model.bizmodel;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Game implements Serializable{
	public String date;
	public String time;
	public String status;
	public Team team1;
	public Team team2;
	public List<StartPlayer> start1;
	public List<StartPlayer> start2;
	public List<StartPlayer> zamena1;
	public List<StartPlayer> zamena2;
	
	public Game() {};
	
	public Game(String date,String time, String status, Team team1, Team team2, List<StartPlayer> start1,List<StartPlayer> start2, List<StartPlayer> zamena1, List<StartPlayer> zamena2) {
		this.date=date;
		this.time=time;
		this.status=status;
		this.team1=team1;
		this.team2=team2;
		this.start1=start1;
		this.start2=start2;
		this.zamena1=zamena1;
		this.zamena2=zamena2;
	}
}
