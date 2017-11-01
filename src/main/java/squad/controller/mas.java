package squad.controller;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import squad.model.bizmodel.LastGame;

@XmlRootElement
class mas implements Serializable{
	public List<LastGame> game;
	public mas(List<LastGame> game) {
		this.game=game;
	}
	public mas() {}
}
