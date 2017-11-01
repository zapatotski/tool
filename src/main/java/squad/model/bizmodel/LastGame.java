package squad.model.bizmodel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LastGame implements Serializable{

	public String name;
	public String tournament;
	public Date date;
	public List<Integer> start;
	public List<Integer> zamena;
	
	public LastGame(String name, String tournament, Date date,List<Integer> start,List<Integer> zamena) {
		this.name=name;
		String shortname=tournament.substring(0, 3);
		String[] str=tournament.split(" ");
		if(str.length>1)
			shortname=str[0].substring(0, 1)+str[1].substring(0, 1);
		if(str.length>2)
			shortname=str[0].substring(0, 1)+str[1].substring(0, 1)+str[2].substring(0, 1);
		shortname=shortname+" "+date.getDate()+"."+(date.getMonth()+1);
		this.tournament=shortname;
		this.date=date;
		this.start=start;
		this.zamena=zamena;
	}
	
	public LastGame() {
		
	}
}
