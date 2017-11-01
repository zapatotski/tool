package squad.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.annotation.XmlRootElement;

import squad.model.bizmodel.Game;
import squad.model.bizmodel.LastGame;
import squad.model.service.Parser;

@Path("/api")
public class RestEndPoints {

	
	@GET
	@Path("/refresh")
	@Produces({ "application/xml", "application/json" })
	public List<Game> getGames() {
		return new Parser().parse();
	}
	
	/*
	@GET
	@Path("/refreshlastgame")
	@Produces({ "application/xml", "application/json" })
	public List<mas> getLastGames() {
		Map <Integer,List<LastGame>> m=new Parser().parseLastGame();
		List<mas> ls=new ArrayList();
		for(Integer i:m.keySet())
			ls.add(new mas(m.get(i)));
		return ls;
	}
	*/
	
	@GET
	@Path("/refreshlastgame")
	public void getLastGames() {
		Map <Integer,List<LastGame>> m=new Parser().parseLastGame();
	}
	
}
