package squad.model.bizmodel;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class Player implements Serializable{
	
	public Integer id;
	public String name;
	
	@XmlElement(name = "num")
	public String number;
	public String age;
	
	@XmlElement(name = "pos")
	public String position;
	
	@XmlElement(name = "min")
	public Integer minutes;
	
	@XmlElement(name = "app")
	public String appearences;
	
	@XmlElement(name = "lnu")
	public String lineups;
	
	@XmlElement(name = "sin")
	public String substitute_in;
	
	@XmlElement(name = "sout")
	public String substitute_out;
	
	@XmlElement(name = "sob")
	public String substitutes_on_bench;
	public String goals;
	
	@XmlElement(name = "ass")
	public String assists;
	
	@XmlElement(name = "yc")
	public String yellowcards;
	
	@XmlElement(name = "yr")
	public String yellowred;
	
	@XmlElement(name = "rc")
	public String redcards;
	
	public Player() {};
	
	public Player(Integer id, String name, String number, String age, String position, Integer minutes, String appearences, String lineups, String substitute_in,
			String substitute_out, String substitutes_on_bench, String goals, String assists, String yellowcards, String yellowred, String redcards) {
		this.id=id;
		this.name=name;
		this.number=number;
		this.age=age;
		this.position=position;
		this.minutes=minutes;
		this.appearences=appearences;
		this.lineups=lineups;
		this.substitute_in=substitute_in;
		this.substitute_out=substitute_out;
		this.substitutes_on_bench=substitutes_on_bench;
		this.goals=goals;
		this.assists=assists;
		this.yellowcards=yellowcards;
		this.yellowred=yellowred;
		this.redcards=redcards;
	}
}
