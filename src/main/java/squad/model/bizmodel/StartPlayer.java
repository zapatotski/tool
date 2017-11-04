package squad.model.bizmodel;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pl")
public class StartPlayer {
	
	public int num;
	public int id;
	
	public StartPlayer() {}
	
	public StartPlayer(int id, int num) {		
		this.id=id;
		this.num=num;
	}
	
}
