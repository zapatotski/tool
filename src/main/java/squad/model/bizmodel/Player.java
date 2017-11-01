package squad.model.bizmodel;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Player implements Serializable{
	
	public Integer id;
	public String name;
	public String number;
	public String age;
	public String position;
	public Integer minutes;
	public String appearences;
	public String lineups;
	public String substitute_in;
	public String substitute_out;
	public String substitutes_on_bench;
	public String goals;
	public String assists;
	public String yellowcards;
	public String yellowred;
	public String redcards;
	
	public Player() {};
	
	public Player(	Integer id, String name, String number, String age, String position, Integer minutes, String appearences, String lineups, String substitute_in,
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
