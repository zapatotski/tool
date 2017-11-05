package squad.model.bizmodel;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pl")
public class StartPlayer implements Serializable{
	
	public int num;
	public int id;
	
	public StartPlayer() {}
	
	public StartPlayer(int id, int num) {		
		this.id=id;
		this.num=num;
	}
	
}
