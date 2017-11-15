package squad.model.bizmodel;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="pl")
public class StartPlayer implements Serializable{
	
	public int num;
	public int id;
	public String name;
	@XmlAttribute
	public int numpred;
	@XmlAttribute
	public int idpred;
	
	public StartPlayer() {}
	
	public StartPlayer(int id, int num, String name, int numpred, int idpred) {		
		this.id=id;
		this.num=num;
		this.name=name;
		this.numpred=numpred;
		this.idpred=idpred;
	}
	
}
