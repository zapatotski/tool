package squad.model.bizmodel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement
public class Game implements Serializable{
	public String date;
	public String time;
	public String status;
	public String tournam;
	public Team team1;
	public Team team2;
	public List<StartPlayer> start1;
	public List<StartPlayer> start2;
	public List<StartPlayer> zamena1;
	public List<StartPlayer> zamena2;
	public String feed;
	@XmlTransient 
	public Date startTime;
	@XmlElement(name = "startTime")
	public String startTimep;
	
	public Game() {};
	
	public Game(String date,String time, String status, String tournam, Team team1, Team team2, List<StartPlayer> start1,List<StartPlayer> start2, List<StartPlayer> zamena1, List<StartPlayer> zamena2,String feed,Date startTime) {
		this.date=date;
		String[] timearr=time.split(":");
		if(timearr.length>1) {
			try {
				timearr[0]=(Integer.valueOf(timearr[0])+3) + "";
			}
			catch(Exception e) {}
			time=timearr[0]+":"+timearr[1];
		}
		this.time=time;
		
		this.status=status;
		this.tournam=tournam;
		this.team1=team1;
		this.team2=team2;
		this.start1=start1;
		this.start2=start2;
		this.zamena1=zamena1;
		this.zamena2=zamena2;
		this.feed=feed;
		this.startTime=startTime;
		this.startTimep=(startTime.getHours()+3)+":"+startTime.getMinutes()+":"+startTime.getSeconds();
	}
		
}

