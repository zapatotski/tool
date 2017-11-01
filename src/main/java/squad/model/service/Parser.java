package squad.model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import squad.model.bizmodel.*;
import squad.model.dao.LastGameDao;

public class Parser {
	
	String[] hrefs = {
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/argentina.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/argentina_b.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/australia.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/austria.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/belgium.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/brazil_a.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/brazil_b.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/championship.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/champleague.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/epl.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/eredivisie.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/europaleague.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/facup.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/france.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/france_cup.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/france_league2.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/france_league_cup.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/germany.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/germany_cup.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/germany_liga2.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/greece.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/holland_cup.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/holland_division2.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/italy.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/italy_cup.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/italy_serieb.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/japan.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/mexico.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/mls.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/paraguay.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/peru.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/portugal.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/rsa.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/russia.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/scotland.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/scotland_cup.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/scotland_division2.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/scotland_league_cup.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/spain.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/spain_cup.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/spain_segunda.xml",
				"http://www.goalserve.com/getfeed/ff0aa5cf9aca4b778bef284498fe9907/commentaries/spain_supercup.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/sweden.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/switzerland.xml",
				"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/commentaries/turkey.xml"
			};
	
	String[] hrefs2 = {
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/argentina/Primera",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/argentina/primerab",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/argentina/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/argentina/SuperFinal",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/australia/A-League",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/austria/Bundesliga",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/austria/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/brazil/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/brazil/SerieA",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/brazil/SerieB",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/belgium/cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/belgium/ProLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/belgium/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/chile/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/chile/Primera",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/chile/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/denmark/SuperLiga",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/denmark/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/england/Championship",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/england/FACup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/england/PremierLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/england/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/england/LeagueOne",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/england/LeagueTwo",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/england/FATrophy",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/england/FLTrophy",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/england/CarlingCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/eurocups/champleague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/eurocups/europaleague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/eurocups/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/finland/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/finland/Ykkonen",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/france/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/france/LeagueCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/france/Ligue1",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/france/Ligue2",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/france/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/germany/Bundesliga",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/germany/Bundesliga2",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/germany/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/germany/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/greece/cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/greece/SuperLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/greece/BEthiniki",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/holland/cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/holland/EersteDivisie",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/holland/Eredivisie",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/holland/Playoffs",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/holland/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/intl/ClubFriendlies",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/intl/Libertadores",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/ireland/LeagueCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/ireland/faicup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/ireland/PremierLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/italy/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/italy/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/italy/SerieA",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/italy/SerieB",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/japan/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/japan/J-League",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/mexico/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/mexico/PrimeraA",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/norway/Adeccoligaen",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/norway/cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/norway/Eliteserien",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/portugal/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/portugal/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/portugal/LeagueCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/romania/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/romania/LigaI",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/romania/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/russia/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/russia/PremierLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/scotland/PremierLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/scotland/FACup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/scotland/LeagueCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/scotland/Division1",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/spain/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/spain/Primera",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/spain/Segunda",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/spain/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/sweden/cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/sweden/Superettan",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/switzerland/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/switzerland/SuperLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/turkey/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/turkey/SuperLig",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/usa/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/usa/MLS",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/wales/PremierLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/wales/FACup"
			};
	
	
	
	public List<Game> parse(){
				
		List<Game> result=new ArrayList();
		
		for (String href : hrefs) {
			System.out.println(href);
			try {
				Document doc = Jsoup.connect(href).get();

				Elements matches = doc.select("match");
				if (matches.isEmpty())
					continue;
				
                mark:
				for (int j = 0; j < matches.size(); j++) {
					String date ="";
					String time ="";
					String status ="";
					Integer team1id=0;
					String team1name="";
					Integer team2id=0;
					String team2name="";
					
					date = matches.get(j).attr("date");
					time = matches.get(j).attr("time");
					status = matches.get(j).attr("status");
					
					if(!"Not Started".equals(status))
						continue;
					
					Elements team1Elements = matches.get(j).select("localteam");
					Elements team2Elements = matches.get(j).select("visitorteam");
					
					if (team1Elements.isEmpty() || team2Elements.isEmpty())
						continue;
					
					try {
						team1id=Integer.valueOf(team1Elements.get(0).attr("id"));
						team1name=team1Elements.get(0).attr("name");
						team2id=Integer.valueOf(team2Elements.get(0).attr("id"));
						team2name=team2Elements.get(0).attr("name");
					}
					catch(Exception e) {
						continue mark;
					}
					
					Elements startteams = matches.get(j).select("teams");
					Elements substeams = matches.get(j).select("substitutes");
					
					if (startteams.isEmpty() || substeams.isEmpty())
						continue mark;
					
					Elements team1Start = startteams.get(0).select("localteam");
					Elements team2Start = startteams.get(0).select("visitorteam");
					
					Elements team1Subs = substeams.get(0).select("localteam");
					Elements team2Subs = substeams.get(0).select("visitorteam");
					
					if (team1Start.isEmpty() || team2Start.isEmpty())
						continue mark;
					
					if (team1Subs.isEmpty() || team2Subs.isEmpty())
						continue mark;
					
					Elements playerstart1=team1Start.get(0).select("player");
					Elements playerstart2=team2Start.get(0).select("player");
					Elements playersubs1=team1Subs.get(0).select("player");
					Elements playersubs2=team2Subs.get(0).select("player");
					
					if ((playerstart1.size()!=11) || (playerstart2.size()!=11))
						continue mark;
					
					List<Integer> start1=new ArrayList();
					List<Integer> start2=new ArrayList();
					
					for(Element el:playerstart1) {
						try {
							int a=Integer.valueOf(el.attr("id"));
							start1.add(a);
						}
						catch(Exception e) {
							continue mark;
						}		
					}
					
					
					for(Element el:playerstart2) {
						try {
							int a=Integer.valueOf(el.attr("id"));
							start2.add(a);
						}
						catch(Exception e) {
							continue mark;
						}		
					}
					
					List<Integer> subs1=new ArrayList();
					List<Integer> subs2=new ArrayList();
					
					for(Element el:playersubs1) {
						try {
							int a=Integer.valueOf(el.attr("id"));
							subs1.add(a);
						}
						catch(Exception e) {
							continue mark;
						}		
					}
					
					
					for(Element el:playersubs2) {
						try {
							int a=Integer.valueOf(el.attr("id"));
							subs2.add(a);
						}
						catch(Exception e) {
							continue mark;
						}		
					}
					
					
					
					List<Player> listPlayer=new ArrayList();
					try {
						Document doc2 = Jsoup.connect("http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerstats/team/"+team1id).get();
						Elements squad = doc2.select("squad");
						if(squad.isEmpty())
							continue;
						
						Elements players=squad.get(0).select("player");
						if(players.isEmpty())
							continue mark;
						
						
						PriorityQueue<Player> pr=new PriorityQueue<Player>(new Comparator<Player>() {
							public int compare(Player t, Player t1) {
								if (t.minutes < t1.minutes)
									return 1;
								if (t.minutes > t1.minutes)
									return -1;
								else
									return 0;
							}
						});
						
						for(Element elem:players) {
							try {
								Integer id=Integer.valueOf(elem.attr("id"));
								String name=elem.attr("name");
								String number=elem.attr("number");
								String age=elem.attr("age");
								String position=elem.attr("position");
								Integer minutes=Integer.valueOf(elem.attr("minutes"));
								String appearences=elem.attr("appearences");
								String lineups=elem.attr("lineups");
								String substitute_in=elem.attr("substitute_in");
								String substitute_out=elem.attr("substitute_out");
								String substitutes_on_bench=elem.attr("substitutes_on_bench");
								String goals=elem.attr("goals");
								String assists=elem.attr("assists");
								String yellowcards=elem.attr("yellowcards");
								String yellowred=elem.attr("yellowred");
								String redcards=elem.attr("redcards");
								
								pr.add(new Player(id,name,number,age,position,minutes,appearences,lineups,substitute_in,substitute_out,substitutes_on_bench,goals, assists,yellowcards, yellowred, redcards));
							}
							catch(Exception e) {
								continue mark;
							}
						}
						
						int n=pr.size();
						for (int k = 0; k < n; k++) 
							listPlayer.add(pr.poll());
					}
					catch(Exception e) {
						continue mark;
					}
					Team team1=new Team(team1name,team1id,listPlayer,getLastGame(team1id));
					

					List<Player> listPlayer2=new ArrayList();
					try {
						Document doc2 = Jsoup.connect("http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerstats/team/"+team2id).get();
						Elements squad = doc2.select("squad");
						if(squad.isEmpty())
							continue mark;
											
						Elements players=squad.get(0).select("player");
						if(players.isEmpty())
							continue mark;
						
						
						PriorityQueue<Player> pr=new PriorityQueue<Player>(new Comparator<Player>() {
							public int compare(Player t, Player t1) {
								if (t.minutes < t1.minutes)
									return 1;
								if (t.minutes > t1.minutes)
									return -1;
								else
									return 0;
							}
						});
						
						for(Element elem:players) {
							try {
								Integer id=Integer.valueOf(elem.attr("id"));
								String name=elem.attr("name");
								String number=elem.attr("number");
								String age=elem.attr("age");
								String position=elem.attr("position");
								Integer minutes=Integer.valueOf(elem.attr("minutes"));
								String appearences=elem.attr("appearences");
								String lineups=elem.attr("lineups");
								String substitute_in=elem.attr("substitute_in");
								String substitute_out=elem.attr("substitute_out");
								String substitutes_on_bench=elem.attr("substitutes_on_bench");
								String goals=elem.attr("goals");
								String assists=elem.attr("assists");
								String yellowcards=elem.attr("yellowcards");
								String yellowred=elem.attr("yellowred");
								String redcards=elem.attr("redcards");
								
								pr.add(new Player(id,name,number,age,position,minutes,appearences,lineups,substitute_in,substitute_out,substitutes_on_bench,goals, assists,yellowcards, yellowred, redcards));
							}
							catch(Exception e) {
								continue mark;
							}
						}
						
						int n=pr.size();
						for (int k = 0; k < n; k++) 
							listPlayer2.add(pr.poll());
					}
					catch(Exception e) {
						continue mark;
					}
					Team team2=new Team(team2name,team2id,listPlayer2,getLastGame(team2id));
					
				    result.add(new Game(date,time,status,team1,team2,start1,start2,subs1,subs2));														
				}
			}
			catch(Exception e) {
				System.out.println("ERROR: "+e.toString());
			}
		}
		
		return result;
	}
	
	
	
	private LastGameDao DAO=new LastGameDao();
	
	public Map<Integer,List<LastGame>> parseLastGame() {
		Map<Integer, PriorityQueue<LastGame>> mapGame = new HashMap();
		for (String href : hrefs2) {
			System.out.println(href);
			try {
				Document doc = Jsoup.connect(href).get();
				
				Elements tournamentElements = doc.select("tournament");
				if (tournamentElements.isEmpty())
					continue;

				Element tournamentElement = tournamentElements.first();
				String compName = tournamentElement.attr("league");
				
				
				Elements matchElements = doc.select("match");
				if (matchElements.isEmpty())
					continue;

				String status = "";
				String date = "";
				int team1Id = 0;
				String team1Name = "";
				int team2Id = 0;
				String team2Name = "";

				
				for (int j = 0; j < matchElements.size(); j++) {
				    
					status = matchElements.get(j).attr("status");
				    
				    if(!"FT".equals(status))
				    	continue;

				    
					date = matchElements.get(j).attr("date");
					int[] dataArr = toIntArray(date);
					if (dataArr.length != 3)
						continue;
					
					
					Elements team1Elements = matchElements.get(j).select("localteam");
					Elements team2Elements = matchElements.get(j).select("visitorteam");

					try {
						team1Id = Integer.valueOf(team1Elements.first().attr("id"));
						team1Name = team1Elements.first().attr("name");
						team2Id = Integer.valueOf(team2Elements.first().attr("id"));
						team2Name = team2Elements.first().attr("name");
					} catch (Exception e) {
						continue;
					}
					
					
					Elements startteams = matchElements.get(j).select("lineups");
					Elements substeams = matchElements.get(j).select("substitutions");
					
					if (startteams.isEmpty() || substeams.isEmpty())
						continue;
					
					
					Elements team1Start = startteams.get(0).select("localteam");
					Elements team2Start = startteams.get(0).select("visitorteam");
					
					Elements team1Subs = substeams.get(0).select("localteam");
					Elements team2Subs = substeams.get(0).select("visitorteam");
					
					if (team1Start.isEmpty() || team2Start.isEmpty())
						continue;
					
					if (team1Subs.isEmpty() || team2Subs.isEmpty())
						continue;
					
					Elements playerstart1=team1Start.get(0).select("player");
					Elements playerstart2=team2Start.get(0).select("player");
					Elements playersubs1=team1Subs.get(0).select("substitution");
					Elements playersubs2=team2Subs.get(0).select("substitution");
					
					if ((playerstart1.size()!=11) || (playerstart2.size()!=11))
						continue;
					
					List<Integer> start1=new ArrayList();
					List<Integer> start2=new ArrayList();
					
					for(Element el:playerstart1) {
						try {
							int a=Integer.valueOf(el.attr("id"));
							start1.add(a);
						}
						catch(Exception e) {
							System.out.println("Oshibka pri preobrazovaniy v int id player");
						}		
					}
					
					
					for(Element el:playerstart2) {
						try {
							int a=Integer.valueOf(el.attr("id"));
							start2.add(a);
						}
						catch(Exception e) {
							System.out.println("Oshibka pri preobrazovaniy v int id player");
						}		
					}
					
					List<Integer> subs1=new ArrayList();
					List<Integer> subs2=new ArrayList();
					
					for(Element el:playersubs1) {
						try {
							int a=Integer.valueOf(el.attr("player_in_id"));
							subs1.add(a);
						}
						catch(Exception e) {
							System.out.println("Oshibka pri preobrazovaniy v int id player");
						}		
					}
					
					
					for(Element el:playersubs2) {
						try {
							int a=Integer.valueOf(el.attr("player_in_id"));
							subs2.add(a);
						}
						catch(Exception e) {
							System.out.println("Oshibka pri preobrazovaniy v int id player");
						}		
					}


					// vozmozen Exception v new Date();
					LastGame g1 = new LastGame(team1Name+"-"+team2Name, compName, new Date(dataArr[2] - 1900, dataArr[1] - 1, dataArr[0]),start1,subs1);
					LastGame g2 = new LastGame(team1Name+"-"+team2Name, compName, new Date(dataArr[2] - 1900, dataArr[1] - 1, dataArr[0]),start2,subs2);

					if (!mapGame.containsKey(team1Id))
						mapGame.put(team1Id, new PriorityQueue<LastGame>(new Comparator<LastGame>() {
							public int compare(LastGame t, LastGame t1) {
								if (t.date.getTime() < t1.date.getTime())
									return 1;
								if (t.date.getTime() > t1.date.getTime())
									return -1;
								else
									return 0;
							}
						}));

					if (!mapGame.containsKey(team2Id))
						mapGame.put(team2Id, new PriorityQueue<LastGame>(new Comparator<LastGame>() {
							public int compare(LastGame t, LastGame t1) {
								if (t.date.getTime() < t1.date.getTime())
									return 1;
								if (t.date.getTime() > t1.date.getTime())
									return -1;
								else
									return 0;
							}
						}));

									
					mapGame.get(team1Id).add(g1);
					mapGame.get(team2Id).add(g2);
				}


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		Map<Integer,List<LastGame>> result=new HashMap();
		for (Integer i : mapGame.keySet()) {
			PriorityQueue<LastGame> gl = mapGame.get(i);
			int n = gl.size();
			List<LastGame> lst=new ArrayList();
			for (int k = 0; k < n; k++) {
				LastGame tg = gl.poll();
				lst.add(tg);
				if(k==2)
					break;
			}
			result.put(i, lst);
		}
		
		//serializuem
		DAO.setGames(result);		
		
		return result;
		
	}
	
	public List<LastGame> getLastGame(int idTeam){
		return DAO.getGames().get(idTeam);
	}
	
	private int[] toIntArray(String s) {
		String[] str = s.split("\\.");
		int[] arr = new int[str.length];
		try {
			for (int i = 0; i < str.length; i++)
				arr[i] = Integer.valueOf(str[i]);
		} catch (Exception e) {
		}
		return arr;
	}

}
