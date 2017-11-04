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
			/*
			    "http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1005.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1007.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1425.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1422.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1204.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1399.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1398.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1397.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1229.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1225.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1226.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1221.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1217.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1218.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1322.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1205.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1197.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1198.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1199.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1352.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1344.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1361.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1269.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1265.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1086.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1081.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1093.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1090.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1102.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1232.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1308.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1154.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1163.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1440.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1457.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1185.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1159.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1271.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1049.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1050.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1051.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1054.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1335.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1407.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1040.xml",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1368.xml"
				*/
			"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1.xml"
			};
	
	String[] hrefs2 = {
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/argentina/Primera",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/argentina/primerab",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/argentina/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/argentina/SuperFinal",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/australia/A-League",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/austria/1-Liga",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/austria/Bundesliga",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/austria/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/brazil/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/brazil/SerieA",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/brazil/SerieB",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/belarus/PremierLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/belarus/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/belgium/cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/belgium/ProLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/belgium/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/belgium/SecondDivision",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/bulgaria/APFG",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/bulgaria/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/chile/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/chile/Primera",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/chile/PrimeraB",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/chile/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/china/FaCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/china/LeagueOne",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/china/SuperLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/croatia/cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/croatia/HNL",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/croatia/2HNL",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/cyprus/1-division",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/cyprus/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/czechia/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/czechia/CzechLiga",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/czechia/Liga2",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/czechia/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/denmark/SuperLiga",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/denmark/1stDivision",
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
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/hungary/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/hungary/NBI",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/iceland/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/iceland/LeagueCup",
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
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/korea/FaCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/korea/K-League",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/korea/K2League",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/korea/LeagueCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/mexico/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/mexico/PrimeraA",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/norway/Adeccoligaen",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/norway/cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/norway/Eliteserien",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/poland/Ekstraklasa",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/poland/PolandCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/poland/Division2",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/portugal/PortugueseLiga",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/portugal/Division2",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/portugal/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/portugal/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/portugal/LeagueCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/romania/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/romania/LigaI",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/romania/SuperCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/russia/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/russia/PremierLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/russia/1-division",
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
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/sweden/Allsvenskan",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/switzerland/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/switzerland/SuperLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/turkey/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/turkey/SuperLig",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/turkey/LigA",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/uae/Division1",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/uae/EmiratesCup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/uae/PremierLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/ukraine/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/ukraine/PershaLiga",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/ukraine/PremierLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/usa/Cup",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/usa/MLS",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/usa/NASL",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/wales/PremierLeague",
			"http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerfixtures/wales/FACup"
			};
	
	
	/*
	public List<Game> parseAPI(){
		URL url=new URL("https://soccer.sportmonks.com/api/v2.0/livescores?api_token=nLWkworLYu7G9pHcdTnjSpnt1bD1c28Fjrni4RpIi4wGbeT8qrNh54RiLqUN&include=lineup,bench,localTeam,visitorTeam");
		try {

			HttpURLConnection e = (HttpURLConnection) url.openConnection();
			int responseCode = e.getResponseCode();
			String jsontext = "";
			int i;

			if (responseCode == 200) {
				BufferedInputStream gson = new BufferedInputStream(e.getInputStream());
				ArrayList m = new ArrayList();

				for (i = gson.read(); i != -1; i = gson.read()) {
					m.add(Character.valueOf((char) i));
				}

				char[] key = new char[m.toArray().length];
				int list = 0;

				for (Iterator id = m.iterator(); id.hasNext(); ++list) {
					Object j = id.next();
					key[list] = ((Character) j).charValue();
				}

				jsontext = new String(key);
			} else {
				System.out.println("someproblem1");
			}

			Gson var64 = new Gson();
			Matchi var65 = (Matchi) var64.fromJson(jsontext, Matchi.class);

			int knt=0;
			int kntpr=0;
			for (i = 0; i < var65.sportItem.tournaments.length; ++i) {

				String var66 = var65.sportItem.tournaments[i].category.name + " "
						+ var65.sportItem.tournaments[i].tournament.name;
				ArrayList var67 = new ArrayList();
				
	
				
				for (int var68 = 0; var68 < var65.sportItem.tournaments[i].events.length; ++var68) {
					int var69 = var65.sportItem.tournaments[i].events[var68].id;
					boolean stat = var65.sportItem.tournaments[i].hasEventPlayerStatistics;
					String hourfortime = "";
					String minfortime = "";
					int hft = new Date(
							(var65.sportItem.tournaments[i].events[var68].startTimestamp * 1000L + 10800000L))
									.getHours();
					int mft = new Date(
							(var65.sportItem.tournaments[i].events[var68].startTimestamp * 1000L + 10800000L))
									.getMinutes();
					if (hft < 10) {
						hourfortime = "0" + hft;
					} else {
						hourfortime = "" + hft;
					}
					if (mft < 10) {
						minfortime = "0" + mft;
					} else {
						minfortime = "" + mft;
					}
					String time = hourfortime + ":" + minfortime;
					String team1 = var65.sportItem.tournaments[i].events[var68].homeTeam.name;
					String team2 = var65.sportItem.tournaments[i].events[var68].awayTeam.name;
					String htScore = var65.sportItem.tournaments[i].events[var68].homeScore.current + "-"
							+ var65.sportItem.tournaments[i].events[var68].awayScore.current;
					String ftScore = var65.sportItem.tournaments[i].events[var68].homeScore.normaltime + "-"
							+ var65.sportItem.tournaments[i].events[var68].awayScore.normaltime;
					if ("null-null".equals(ftScore)) {
						ftScore = "-";
					}

					if ("null-null".equals(htScore)) {
						htScore = "-";
					}

					HashMap<Integer, Player> myel1 = new HashMap();
					HashMap<Integer, Player> myelred1 = new HashMap();
					HashMap<Integer, Player> mred1 = new HashMap();
					HashMap<Integer, Player> msubs1 = new HashMap();
					HashMap<Integer, Player> myel2 = new HashMap();
					HashMap<Integer, Player> myelred2 = new HashMap();
					HashMap<Integer, Player> mred2 = new HashMap();
					HashMap<Integer, Player> msubs2 = new HashMap();

					System.out.println("Pered filtrom:"+var69);
					if (stat && ((var65.sportItem.tournaments[i].events[var68].startTimestamp * 1000L
							+ 10800000L) > (new Date((new Date()).getYear(), (new Date()).getMonth(),
									(new Date()).getDate())).getTime())
							&& ((var65.sportItem.tournaments[i].events[var68].startTimestamp * 1000L
									+ 10800000L) < (new Date((new Date()).getYear(), (new Date()).getMonth(),
											(new Date()).getDate(), 23, 59, 60)).getTime())) {
						
						System.out.println(kntpr+") Proshel filtr:"+var69);
						kntpr++;
						
						Game var70 = new Game(time, team1, team2, ftScore, ftScore, myel1, myelred1, mred1, msubs1,
								myel2, myelred2, mred2, msubs2);

						if ((("finished".equals(var65.sportItem.tournaments[i].events[var68].status.type))
								&& (((new Date().getTime() / 1000)
										- var65.sportItem.tournaments[i].events[var68].startTimestamp) > 10000L)
								&& keshout.containsKey(var69))
								|| "notstarted".equals(var65.sportItem.tournaments[i].events[var68].status.type)) {
							
							if (keshout.containsKey(var69)) {
								System.out.println("V keshe: "+knt+") "+var69);
								var70 = keshout.get(var69);
								knt++;
							}

						} else {

							URL url2 = new URL("https://www.sofascore.com/event/" + var69 + "/json?_="
									+ (new Date()).getTime() / 10000L);

							try {
								e = (HttpURLConnection) url2.openConnection();
								responseCode = e.getResponseCode();
								String g = "";
								if (responseCode == 200) {
									BufferedInputStream in = new BufferedInputStream(e.getInputStream());
									ArrayList str = new ArrayList();

									for (int b = in.read(); b != -1; b = in.read()) {
										str.add(Character.valueOf((char) b));
									}

									char[] chars = new char[str.toArray().length];
									int x = 0;

									for (Iterator ev = str.iterator(); ev.hasNext(); ++x) {
										Object gson2 = ev.next();
										chars[x] = ((Character) gson2).charValue();
									}

									g = new String(chars);
									Gson var71 = new Gson();
									Eventes var72 = (Eventes) var71.fromJson(g, Eventes.class);

									for (int h = 0; h < var72.incidents.length; ++h) {
										try {
											URL url3;
											int mt;
											int ms;
											int min;
											int goal;
											int yel;
											int yelred;
											int red;
											HttpURLConnection e1;
											String jsontext3;
											BufferedInputStream in3;
											ArrayList str3;
											int b3;
											char[] chars3;
											int d;
											Object gson3;
											Iterator st;
											Value e2;
											int var54;
											int var55;
											Value[] var56;
											Gson var73;
											Stat var74;
											if ("substitution".equals(var72.incidents[h].incidentType)
													&& var72.incidents[h].injury) {
												url3 = new URL("https://www.sofascore.com/player/"
														+ var72.incidents[h].playerOut.id + "/statistics/json?_="
														+ (new Date()).getTime() / 10000L);
												mt = 0;
												ms = 0;
												min = 0;
												goal = 0;
												yel = 0;
												yelred = 0;
												red = 0;

												try {
													e1 = (HttpURLConnection) url3.openConnection();
													responseCode = e1.getResponseCode();
													jsontext3 = "";
													if (responseCode == 200) {
														in3 = new BufferedInputStream(e1.getInputStream());
														str3 = new ArrayList();

														for (b3 = in3.read(); b3 != -1; b3 = in3.read()) {
															str3.add(Character.valueOf((char) b3));
														}

														chars3 = new char[str3.toArray().length];
														d = 0;

														for (st = str3.iterator(); st.hasNext(); ++d) {
															gson3 = st.next();
															chars3[d] = ((Character) gson3).charValue();
														}

														jsontext3 = new String(chars3);
														var73 = new Gson();
														var74 = (Stat) var73.fromJson(jsontext3, Stat.class);

														try {
															var56 = var74.statistics[0].seasons[0].statistics;
															var55 = var74.statistics[0].seasons[0].statistics.length;

															for (var54 = 0; var54 < var55; ++var54) {
																e2 = var56[var54];
																if ("Matches".equals(e2.groupName)) {
																	mt = e2.matchesTotal;
																	ms = e2.matchesStarting;
																	min = e2.minutesPerGame * mt;
																}

																if ("Goals".equals(e2.groupName)) {
																	goal = e2.goals;
																}

																if ("Cards".equals(e2.groupName)) {
																	yel = e2.yellowCards;
																	yelred = e2.yellowRedCards;
																	red = e2.redCards;
																}
															}
														} catch (Exception var59) {
															;
														}
													}
												} catch (Exception var60) {
													throw new RuntimeException(var60);
												}

												if (var72.incidents[h].playerTeam == 1) {
													msubs1.put(Integer.valueOf(var72.incidents[h].playerOut.id),
															new Player(var72.incidents[h].playerOut.id,
																	var72.incidents[h].playerOut.name, "", min, mt, ms,
																	goal, yel, yelred, red));
												} else {
													msubs2.put(Integer.valueOf(var72.incidents[h].playerOut.id),
															new Player(var72.incidents[h].playerOut.id,
																	var72.incidents[h].playerOut.name, "", min, mt, ms,
																	goal, yel, yelred, red));
												}
											} else if ("card".equals(var72.incidents[h].incidentType)) {
												url3 = new URL("https://www.sofascore.com/player/"
														+ var72.incidents[h].player.id + "/statistics/json?_="
														+ (new Date()).getTime() / 10000L);
												mt = 0;
												ms = 0;
												min = 0;
												goal = 0;
												yel = 0;
												yelred = 0;
												red = 0;

												try {
													e1 = (HttpURLConnection) url3.openConnection();
													responseCode = e1.getResponseCode();
													jsontext3 = "";
													if (responseCode == 200) {
														in3 = new BufferedInputStream(e1.getInputStream());
														str3 = new ArrayList();

														for (b3 = in3.read(); b3 != -1; b3 = in3.read()) {
															str3.add(Character.valueOf((char) b3));
														}

														chars3 = new char[str3.toArray().length];
														d = 0;

														for (st = str3.iterator(); st.hasNext(); ++d) {
															gson3 = st.next();
															chars3[d] = ((Character) gson3).charValue();
														}

														jsontext3 = new String(chars3);
														var73 = new Gson();
														var74 = (Stat) var73.fromJson(jsontext3, Stat.class);

														try {
															var56 = var74.statistics[0].seasons[0].statistics;
															var55 = var74.statistics[0].seasons[0].statistics.length;

															for (var54 = 0; var54 < var55; ++var54) {
																e2 = var56[var54];
																if ("Matches".equals(e2.groupName)) {
																	mt = e2.matchesTotal;
																	ms = e2.matchesStarting;
																	min = e2.minutesPerGame * mt;
																}

																if ("Goals".equals(e2.groupName)) {
																	goal = e2.goals;
																}

																if ("Cards".equals(e2.groupName)) {
																	yel = e2.yellowCards;
																	yelred = e2.yellowRedCards;
																	red = e2.redCards;
																}
															}
														} catch (Exception var57) {

														}
													}
												} catch (Exception var58) {
													throw new RuntimeException(var58);
												}

												if (var72.incidents[h].playerTeam == 1) {
													if ("Yellow".equals(var72.incidents[h].type)) {
														myel1.put(Integer.valueOf(var72.incidents[h].player.id),
																new Player(var72.incidents[h].player.id,
																		var72.incidents[h].player.name, "", min, mt, ms,
																		goal, yel, yelred, red));
													} else if ("YellowRed".equals(var72.incidents[h].type)) {
														myelred1.put(Integer.valueOf(var72.incidents[h].player.id),
																new Player(var72.incidents[h].player.id,
																		var72.incidents[h].player.name, "", min, mt, ms,
																		goal, yel, yelred, red));
													} else if ("Red".equals(var72.incidents[h].type)) {
														mred1.put(Integer.valueOf(var72.incidents[h].player.id),
																new Player(var72.incidents[h].player.id,
																		var72.incidents[h].player.name, "", min, mt, ms,
																		goal, yel, yelred, red));
													}
												} else if ("Yellow".equals(var72.incidents[h].type)) {
													myel2.put(Integer.valueOf(var72.incidents[h].player.id),
															new Player(var72.incidents[h].player.id,
																	var72.incidents[h].player.name, "", min, mt, ms,
																	goal, yel, yelred, red));
												} else if ("YellowRed".equals(var72.incidents[h].type)) {
													myelred2.put(Integer.valueOf(var72.incidents[h].player.id),
															new Player(var72.incidents[h].player.id,
																	var72.incidents[h].player.name, "", min, mt, ms,
																	goal, yel, yelred, red));
												} else if ("Red".equals(var72.incidents[h].type)) {
													mred2.put(Integer.valueOf(var72.incidents[h].player.id),
															new Player(var72.incidents[h].player.id,
																	var72.incidents[h].player.name, "", min, mt, ms,
																	goal, yel, yelred, red));
												}
											}
										} catch (NullPointerException var61) {
											;
										}
									}
								} else {
									System.out.println("someproblem2");
								}
							} catch (Exception var62) {
								throw new RuntimeException(var62);
							}
							var70 = new Game(time, team1, team2, ftScore, ftScore, myel1, myelred1, mred1, msubs1,
									myel2, myelred2, mred2, msubs2);
						}

						var67.add(var70);
						kesh.put(var69, var70);

					}

				}

				if (var67.size() != 0) {
					map.put(var66, var67);
				}
			}

			serialization(kesh, f);

			return map;
		} catch (Exception var63) {
			throw new RuntimeException(var63);
		}
	}

	public static void serialization(Map<Integer, Game> m, File f) {
		FileOutputStream fOut = null;
		ObjectOutputStream oOut = null;
		try {
			fOut = new FileOutputStream(f);
			oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(m);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				oOut.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	*/
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
					
					
					if(! (status.split(":").length==2))
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
					
					List<StartPlayer> start1=new ArrayList();
					List<StartPlayer> start2=new ArrayList();
					
					for(Element el:playerstart1) {
						try {
							int a=Integer.valueOf(el.attr("id"));
							int b=-1;
							start1.add(new StartPlayer(a,b));
						}
						catch(Exception e) {
							try {
								int a=-1;
								int b=Integer.valueOf(el.attr("number"));
								start1.add(new StartPlayer(a,b));
							}
							catch(Exception ex) {
								continue mark;
							}							
						}		
					}
					
					
					for(Element el:playerstart2) {
						try {
							int a=Integer.valueOf(el.attr("id"));
							int b=-1;
							start2.add(new StartPlayer(a,b));
						}
						catch(Exception e) {
							try {
								int a=-1;
								int b=Integer.valueOf(el.attr("number"));
								start2.add(new StartPlayer(a,b));
							}
							catch(Exception ex) {
								continue mark;
							}							
						}		
					}
					
					List<StartPlayer> subs1=new ArrayList();
					List<StartPlayer> subs2=new ArrayList();

					for(Element el:playersubs1) {
						try {
							int a=Integer.valueOf(el.attr("id"));
							int b=-1;
							subs1.add(new StartPlayer(a,b));
						}
						catch(Exception e) {
							try {
								int a=-1;
								int b=Integer.valueOf(el.attr("number"));
								subs1.add(new StartPlayer(a,b));
							}
							catch(Exception ex) {
								continue mark;
							}							
						}		
					}
					
					
					for(Element el:playersubs2) {
						try {
							int a=Integer.valueOf(el.attr("id"));
							int b=-1;
							subs2.add(new StartPlayer(a,b));
						}
						catch(Exception e) {
							try {
								int a=-1;
								int b=Integer.valueOf(el.attr("number"));
								subs2.add(new StartPlayer(a,b));
							}
							catch(Exception ex) {
								continue mark;
							}							
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
