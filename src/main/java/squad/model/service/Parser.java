package squad.model.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import squad.model.dao.GameDAO;
import squad.model.dao.LastGameDao;

public class Parser {
	
	String[] hrefs = {
			    "http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1.xml",
			    "http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1005.xml",//LC
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1007.xml",//LE
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1425.xml",//Turkey
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1422.xml",//Turkey1
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1204.xml",//EPL
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1399.xml",//SPain Primera
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1398.xml",//Spain Segunda
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1397.xml",//Spain CDR
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1229.xml",//Bundeslia
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1225.xml",//Bundesliga2
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1226.xml",//Germany Cup
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1221.xml",//France Liga1
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1217.xml",//France Liga2
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1218.xml",//France Coupe de France
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1322.xml",//Holland Eredivisie
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1205.xml",//England-Championship
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1197.xml",//England - League Two
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1198.xml",//England FA Cup
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1199.xml",//England Carling Cup
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1352.xml",//Portugal Primera
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1344.xml",//Poland Ekstraklasa
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1361.xml",//Romania Liga1
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1269.xml",//Italy Serie A
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1265.xml",//Italy Serie B
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1086.xml",//Australia A League
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1081.xml",//Argentina Primera
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1093.xml",//Austria Bundesliga
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1090.xml",//Austria Erste
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1102.xml",//Belgium Jupiler
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1232.xml",//Grece Super
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1308.xml",//Mexico Primera
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1154.xml",//Bulgaria
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1163.xml",//China
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1440.xml",//USA MLS
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1457.xml",//Russia PL
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1185.xml",//Denmark Superliga
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1159.xml",//Chile Primera
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1271.xml",//Japan
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1049.xml",//Asia World Cup
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1050.xml",//CONCANCAF
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1051.xml",//Europe World Cup
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1054.xml",//South America World Cup
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1335.xml",//Norway Eliteserien
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1407.xml",//Sweden Allsvenskan
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1040.xml",//South America Copa Libertadores
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/commentaries/1368.xml"//Saudi Arabia				
			};
			
	
	String[] hrefs2 = {
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/Apertura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/ArgentinoA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/ArgentinoB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/Clausura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/Primera",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/primerab",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/SuperFinal",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/armenia/FirstLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/armenia/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/1-Liga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Bundesliga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/azerbaidjan/BirinciDasta",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/azerbaidjan/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/azerbaidjan/PremyerLiqasi",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belarus/Division1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belarus/Division2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belarus/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/ProLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/SecondDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bosnia/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bosnia/PremierLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/SerieA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/SerieB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bulgaria/APFG",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bulgaria/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/canada/CSL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/canada/PCSL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/chile/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/chile/Primera",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/chile/PrimeraB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/chile/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/china/FaCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/china/LeagueOne",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/china/SuperLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/chinese_taipei/NationalLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/colombia/Apertura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/colombia/Clausura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/colombia/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/colombia/PrimeraB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/costa_rica/Apertura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/costa_rica/Clausura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/costa_rica/LigadeAscenso",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/croatia/2HNL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/croatia/cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/croatia/HNL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/cyprus/1-division",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/cyprus/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/cyprus/Division2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/czechia/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/czechia/CzechLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/czechia/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/1stDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/SuperLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ecuador/apertura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ecuador/clausura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ecuador/PrimeraB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/egypt/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/BlueSquareNorth",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/BlueSquareSouth",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/CarlingCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/Championship",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/Conference",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/ConferencePlayoff",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/FACup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/FATrophy",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/FLTrophy",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/LeagueOne",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/LeagueTwo",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/estonia/Esiliiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/estonia/Meistriliiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/champleague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/euro",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/europaleague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/EuroQualification",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/EuroU21",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/euro_qualification",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/finland/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/finland/Veikkausliiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/finland/Ykkonen",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/LeagueCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/Ligue1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/Ligue2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/National",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/friendly/intl_friendly",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/georgia/PirveliLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/georgia/UmaglesiLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/3Liga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Bundesliga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Bundesliga2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Playoffs12",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Playoffs23",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/greece/BEthiniki",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/greece/cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/greece/SuperLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/holland/cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/holland/EersteDivisie",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/holland/Eredivisie",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/holland/Playoffs",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/holland/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hungary/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hungary/NBI",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iceland/1DelidKarla",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iceland/2DelidKarla",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iceland/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iceland/LeagueCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iceland/Urvalsdeild",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/AFCChallengeCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/AFCChampionsLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/AFCCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/AfricanNationsChampionship",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/ASEANCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/AsianCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/AsianCupQualification",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/CAFChampionsLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/CAFConfederationsCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/CaribbeanCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/CECAFACup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/ClubFriendlies",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/CONCACAFChampionsLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/ConcacafGoldCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/ConfederationsCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/CopaAmerica",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/CopaCentroamericana",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/CopaSudamericana",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/CupOfNations",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/CupOfNationsQualification",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/EAFFEastAsianCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/Friendlies",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/GulfCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/ICC",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/Libertadores",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/O-League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/OFCNationsCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/Olympics",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/SAFFChampionship",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ireland/1stDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ireland/faicup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ireland/LeagueCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ireland/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/israel/LigaLeumit",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/israel/LigatAl",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/israel/StateCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/israel/TotoCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/japan/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/japan/J-League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/japan/J2-League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/japan/J3League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/japan/NabiscoCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/kazakhstan/1stDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/kazakhstan/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/korea/ChallengersLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/korea/FaCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/korea/K-League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/korea/K2League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/korea/LeagueCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/mexico/Apertura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/mexico/Clausura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/mexico/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/mexico/PrimeraA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/moldova/DivisiaA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/moldova/NationalDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/montenegro/DrugaLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/montenegro/PrvaLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/morocco/GNEF1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/morocco/GNEF2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/northern_ireland/Championship1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/northern_ireland/IFAPremiership",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/norway/2Divisjon-Avdeling-1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/norway/2Divisjon-Avdeling-2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/norway/2Divisjon-Avdeling-3",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/norway/2Divisjon-Avdeling-4",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/norway/Adeccoligaen",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/norway/cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/norway/Eliteserien",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/paraguay/Apertura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/paraguay/Clausura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/paraguay/DivisionIntermedia",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/peru/CopaInca",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/peru/PrimeraDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/peru/SegundaDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/poland/Division2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/poland/Ekstraklasa",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/poland/PolandCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/Division2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/LeagueCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/PortugueseLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/LigaI",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/LigaII-Seria1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/russia/1-division",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/russia/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/russia/Playoffs",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/russia/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/saudi_arabia/ChampionsCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/saudi_arabia/CrownPrinceCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/saudi_arabia/Division1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/saudi_arabia/SaudiProfessionalLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/scotland/ChallengeCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/scotland/FACup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/scotland/LeagueCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/scotland/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/serbia/PrvaLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/serbia/SrpskaLiga-Belgrade",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/serbia/SrpskaLiga-East",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/serbia/SrpskaLiga-Vojvodina",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/serbia/SrpskaLiga-West",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/serbia/SuperLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/slovakia/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/slovakia/ILiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/slovakia/SuperLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/slovania/1-SNL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/slovania/cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/slovenia/2SNL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/slovenia/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/south_africa/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Primera",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Segunda",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/Allsvenskan",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/Superettan",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/switzerland/ChallengeLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/switzerland/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/switzerland/SuperLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/turkey/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/turkey/LigA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/turkey/SuperLig",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/uae/Division1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/uae/EmiratesCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/uae/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ukraine/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ukraine/PershaLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ukraine/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/uruguay/Apertura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/uruguay/Clausura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/uruguay/SegundaDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/usa/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/usa/MLS",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/usa/NASL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/usa/PDL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/usa/USL1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/usa/USL2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/wales/FACup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/wales/FeederLeagues-CymruAlliance",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/wales/FeederLeagues-WelshLeagueDivision1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/wales/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/worldcup/ClubWorldCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/worldcup/wc_Intercontinental_playoffs",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/worldcup/wc_qualification_africa",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/worldcup/wc_qualification_asia",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/worldcup/wc_qualification_concacaf",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/worldcup/wc_qualification_europe",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/worldcup/wc_qualification_oceania",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/worldcup/wc_qualification_southamerica",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/worldcup/WorldCup",
			};
	
	
	File f=new File("kashstart.data");
	File logFile=new File("logStart.data");
	
	
	public void parseCSV(Map<Integer,Game> hashstart,PriorityQueue<Game> prListGame,List<String> log){
		File dir = new File(".");
		File[] files = dir.listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.toLowerCase().endsWith(".csv");
		    }
		});
		for(File fd:files) {
			try {
			log.add("Chitayu fail "+fd.getName());
			Document doc = Jsoup.parse(fd,"UTF-8");
			Elements category=doc.select("tournament");
			if (category.isEmpty()) {
				fd.delete();
				continue;
			}
			for(Element cat:category) {
				//BEREM MATCH
				Elements matches = cat.select("match");
				//ESLI MATCH PUSTOY, TO PROPUSKAEM
				if (matches.isEmpty()) {
					continue;
				}
				
				//BEREM ATRIBUTE V CATEGORIY IMYA TOURNAMENTA 
				String tournament=cat.attr("name");
				log.add("S faylom "+fd.getName()+" doshli do "+tournament);
				//DLYA KAZDOGO MATCHA
				mark:
					for (int j = 0; j < matches.size(); j++) {
						String date ="";
						String timestart ="";
						String status ="";
						Integer team1id=0;
						String team1name="";
						Integer team2id=0;
						String team2name="";
						Integer idgame=-1;
						
						//BEREM STATUS
						status = matches.get(j).attr("status");							
					
						date = matches.get(j).attr("date");
						timestart = matches.get(j).attr("timestart");

						//BEREM[] LOCALTEAM I VISITORTEAM
						Elements team1Elements = matches.get(j).select("localteam");
						Elements team2Elements = matches.get(j).select("visitorteam");
						
						//ESLI LOCALTEAM ILI VISITORTEAM PUSTOY, TO PROPUSKAEM MATCH
						if (team1Elements.isEmpty() || team2Elements.isEmpty()) {
							continue mark;
						}
						
						//BEREM TEAM1 I TEAM2 IX ID I NAME, ESLI OSHIBKA, TO PROPUSKAEM MATCH
						try {
							team1id=Integer.valueOf(team1Elements.get(0).attr("id"));
							team1name=team1Elements.get(0).attr("name");
							team2id=Integer.valueOf(team2Elements.get(0).attr("id"));
							team2name=team2Elements.get(0).attr("name");
							}
						catch(Exception e) {
							continue mark;
						}
						
						//esli match uze imet start, a ne predvarilovku, to berem ego s hashstart
						if(hashstart.containsKey(team1id) && (!"0".equals(hashstart.get(team1id).feed))) {
							if(prListGame.contains(hashstart.get(team1id))) {
								log.add("Fayl "+fd.getName()+" uze v keshe");
								continue;
							}
							else {
								log.add("Fayl "+fd.getName()+" uze v keshe");
								prListGame.add(hashstart.get(team1id));
								continue;
							}
						}
						
						
						//BEREM [] TEAMS I SUBSTITUTES(DLY STARTA I ZAMEN)
						Elements startteams = matches.get(j).select("teams");
						Elements substeams = matches.get(j).select("substitutes");
						
						//ESLI ONI PUSTIE, TO PROPUSKAEM MATCH
						if (startteams.isEmpty() || substeams.isEmpty()) {
							continue mark;
						}
						
						//BEREM V STARTTEAM [] LOCALTEAM I VISITORTEAM
						Elements team1Start = startteams.get(0).select("localteam");
						Elements team2Start = startteams.get(0).select("visitorteam");
				
						//BEREM V SUBSTEAM [] LOCALTEAM I VISITORTEAM
						Elements team1Subs = substeams.get(0).select("localteam");
						Elements team2Subs = substeams.get(0).select("visitorteam");
						
						//ESLI NET lOCALTEAM ILI VISITORTEAM V START1 ILI START2, TO PROPUSKAEM MATCH
						if (team1Start.isEmpty() || team2Start.isEmpty()) {
							continue mark;
						}
						
						//ESLI NET lOCALTEAM ILI VISITORTEAM V START1 ILI START2, TO PROPUSKAEM MATCH
						if (team1Subs.isEmpty() || team2Subs.isEmpty()) {
							continue mark;
						}
						
						//BEREM PLAYERS V START1,START2,SUBS1,SUBS2
						Elements playerstart1=team1Start.get(0).select("player");
						Elements playerstart2=team2Start.get(0).select("player");
						Elements playersubs1=team1Subs.get(0).select("player");
						Elements playersubs2=team2Subs.get(0).select("player");
						
						//ESLI V START1 ILI START2 KLK PLAYERS NE 11, TO PROPUSKAEM MATCH
						if ((playerstart1.size()!=11) || (playerstart2.size()!=11)) {
							log.add("Fayl "+fd.getName()+" ne imeet 11 igrokov v starte");
							continue mark;
						}
						
						
						//esli match uze imeet predvarilovku, a starta eshe net, to berem ego s hashstart
						if(hashstart.containsKey(team1id) && ("0".equals(hashstart.get(team1id).feed)) && ((playersubs1.size()==0) || (playersubs2.size()==0))) {
							if(prListGame.contains(hashstart.get(team1id))) {
								log.add("Fayl "+fd.getName()+" uze v keshe");
								continue;
							}
							else {
								log.add("Fayl "+fd.getName()+" uze v keshe");
								prListGame.add(hashstart.get(team1id));
								continue;
							}
						}
						
						log.add("S faylom "+fd.getName()+" doshli do sozdaniya spiska igrokov");
						
						//SOZDAEM SPISOK IGROKOV START1 I START2
						List<StartPlayer> start1=new ArrayList();
						List<StartPlayer> start2=new ArrayList();
						
						//ZAPOLNIAEM SPIOK IGROKOV START1
						for(Element el:playerstart1) {
							try {
								int a=-1;
								int b=Integer.valueOf(el.attr("number"));
								String namePl=el.attr("name");
								start1.add(new StartPlayer(a,b,namePl,-1,-1));
								}
							catch(Exception ex) {
								continue mark;
							}												
						}
						
						//ZAPOLNYAEM SPISOK IGROKOV START2
						for(Element el:playerstart2) {
							try {
								int a=-1;
								int b=Integer.valueOf(el.attr("number"));
								String namePl=el.attr("name");
								start2.add(new StartPlayer(a,b,namePl,-1,-1));
								}
							catch(Exception ex) {
								continue mark;
							}							
						}
						
						//SOZDAEM SPISOK IGROKOV SUBS1 I SUBS2
						List<StartPlayer> subs1=new ArrayList();
						List<StartPlayer> subs2=new ArrayList();
						
						//ZAPOLNYAEM SPISOK SUBS1
						for(Element el:playersubs1) {
							try {
								int a=-1;
								int b=Integer.valueOf(el.attr("number"));
								String namePl=el.attr("name");
								subs1.add(new StartPlayer(a,b,namePl,-1,-1));
								}
							catch(Exception ex) {
								continue mark;
							}							
								
						}
						
						//ZAPOLNYAEM SPISOK SUBS2
						for(Element el:playersubs2) {
							try {
								int a=-1;
								int b=Integer.valueOf(el.attr("number"));
								String namePl=el.attr("name");
								subs2.add(new StartPlayer(a,b,namePl,-1,-1));
								}
							catch(Exception ex) {
								continue mark;
							}									
						}
						
						log.add("Doshli s"+fd.getName()+" do zapisi statistiki");
						//SOZDAEM I ZAPOLNYAEM STATISTIKU IGROKOV DLYA TEAM1
						List<Player> listPlayer=new ArrayList();
						listPlayer=getPlayerStats(listPlayer,team1id);
						//SOZDAEM TEAM1
						Team team1=new Team(team1name,team1id,listPlayer,getLastGame(team1id));
						
						//DYA TEAM2 TOZE SAMOE
						List<Player> listPlayer2=new ArrayList();
						listPlayer2=getPlayerStats(listPlayer2,team2id);
						Team team2=new Team(team2name,team2id,listPlayer2,getLastGame(team2id));
												
						
						//esli match uze imeet predvarilovku i na zamene uze ne 0 igrokov, to berem  s hashstart predvarilovku i zapisivaem start
						if(hashstart.containsKey(team1id) && ("0".equals(hashstart.get(team1id).feed)) && ((playersubs1.size()!=0) || (playersubs2.size()!=0))) {
                            //zapolnyaem predvarilovku v atributi								
							Game predg=hashstart.get(team1id);
							int q=0;
							for(StartPlayer s:start1) {
								s.idpred=predg.start1.get(q).id;
								s.numpred=predg.start1.get(q).num;
								q++;
							}
							q=0;
							for(StartPlayer s:start2) {
								s.idpred=predg.start2.get(q).id;
								s.numpred=predg.start2.get(q).num;
								q++;
							}
							//esli v pervom feed, to feed=1. a esli vo vtorom, to feed=2
							Game g=new Game(date,timestart,status,tournament, team1,team2,start1,start2,subs1,subs2,"3",new Date());
							prListGame.add(g);
							hashstart.remove(team1id);
							hashstart.put(team1id, g);
							log.add((new Date().getHours()+3)+":"+new Date().getMinutes()+":"+new Date().getSeconds()+" Dobavil game v kesh s predvarilovkoy: "+g.tournam+" "+g.team1.name+" "+g.team2.name);
							continue mark;
						}
						
						
						//esli v pervom feed, to feed=1. a esli vo vtorom, to feed=2
						Game g=new Game(date,timestart,status,tournament, team1,team2,start1,start2,subs1,subs2,"3",new Date());
						prListGame.add(g);
						hashstart.put(team1id, g);
						log.add((new Date().getHours()+3)+":"+new Date().getMinutes()+":"+new Date().getSeconds()+" Dobavil game v kesh: "+g.tournam+" "+g.team1.name+" "+g.team2.name);
					}
				}
			
			}
			catch(Exception excep) {
				
			}
			fd.delete();
		}
	}
	
	public List<Game> parse(){		
		
		List<String> log=new ArrayList();
		List<Game> result=new ArrayList();
		// vremya poyavlenia matcha
		Map<Integer,Game> hashstart=new HashMap();
				
		PriorityQueue<Game> prListGame=new PriorityQueue<Game>(new Comparator<Game>() {
			public int compare(Game t, Game t1) {
				if (t.startTime.getTime() < t1.startTime.getTime())
					return 1;
				if (t.startTime.getTime() > t1.startTime.getTime())
					return -1;
				else
					return 0;
				}
			}
		);
		
		
		if (f.exists())
			hashstart = (Map<Integer,Game>)deserialization(f);
		
		if(logFile.exists())
			log=(List<String>) deserialization(logFile);
		
		int knthr=0;
		for (String href : hrefs) {
			System.out.println(href);
			try {
				Document doc = Jsoup.connect(href).get();
				Elements category=null;
				
				// BEREM CATEGORIYU ILI TOURNAMENT
				// v feed1 i feed po ligam tournament v raznih mestah
				if(knthr==0) {
					category=doc.select("category");
				}
				else {
					category=doc.select("tournament");
				}
				
				//ESLI CATEGORIYA ILI TOURNAMENT PUSTIE, TO PROPUSKAYEM
				if (category.isEmpty())
					continue;
				
				//DLY KAZDOY CATEGORIYI
				for(Element cat:category) {
					//BEREM MATCH
					Elements matches = cat.select("match");
					//ESLI MATCH PUSTOY, TO PROPUSKAEM
					if (matches.isEmpty())
						continue;
					
					//BEREM ATRIBUTE V CATEGORIY IMYA TOURNAMENTA 
					String tournament=cat.attr("name");
					
					//DLYA KAZDOGO MATCHA
					mark:
						for (int j = 0; j < matches.size(); j++) {
							String date ="";
							String time ="";
							String status ="";
							Integer team1id=0;
							String team1name="";
							Integer team2id=0;
							String team2name="";
							Integer idgame=-1;
							
							//BEREM ID_GAME, ESLI OSHIBKA TO PROPUSKAEM MATCH
							try {
								if(knthr==0) {
									idgame=Integer.valueOf(matches.get(j).attr("fix_id"));
								}
								else {
									idgame=Integer.valueOf(matches.get(j).attr("id"));
								}
							}
							catch(Exception e) {
								continue mark;
							}
							

					
							//BEREM STATUS
							status = matches.get(j).attr("status");							
							//v feed1 i po ligam status ne nachavshihsy matchey po raznomu
							if(knthr==0) {
								if(! (status.split(":").length==2)) {
									continue mark;	
								}
							}
							else
								if(! ("Not Started".equals(status)))
									continue mark;						
							
							
							
							//BEREM DATU I VREMYA NACHALA MATCHA
							if(knthr==0)
								date = matches.get(j).attr("formatted_date");
							else
								date = matches.get(j).attr("date");
							
							time = matches.get(j).attr("time");

							//BEREM[] LOCALTEAM I VISITORTEAM
							Elements team1Elements = matches.get(j).select("localteam");
							Elements team2Elements = matches.get(j).select("visitorteam");
							
							//ESLI LOCALTEAM ILI VISITORTEAM PUSTOY, TO PROPUSKAEM MATCH
							if (team1Elements.isEmpty() || team2Elements.isEmpty())
								continue mark;
							
							//BEREM TEAM1 I TEAM2 IX ID I NAME, ESLI OSHIBKA, TO PROPUSKAEM MATCH
							try {
								team1id=Integer.valueOf(team1Elements.get(0).attr("id"));
								team1name=team1Elements.get(0).attr("name");
								team2id=Integer.valueOf(team2Elements.get(0).attr("id"));
								team2name=team2Elements.get(0).attr("name");
								}
							catch(Exception e) {
								continue mark;
							}
							
							//esli match uze imet start, a ne predvarilovku, to berem ego s hashstart
							if(hashstart.containsKey(team1id) && (!"0".equals(hashstart.get(team1id).feed))) {
								if(prListGame.contains(hashstart.get(team1id)))
									continue;
								else {
									prListGame.add(hashstart.get(team1id));
									continue;
								}
							}
							
							
							//BEREM [] TEAMS I SUBSTITUTES(DLY STARTA I ZAMEN)
							Elements startteams = matches.get(j).select("teams");
							Elements substeams = matches.get(j).select("substitutes");
							
							//ESLI ONI PUSTIE, TO PROPUSKAEM MATCH
							if (startteams.isEmpty() || substeams.isEmpty())
								continue mark;
							
							//BEREM V STARTTEAM [] LOCALTEAM I VISITORTEAM
							Elements team1Start = startteams.get(0).select("localteam");
							Elements team2Start = startteams.get(0).select("visitorteam");
					
							//BEREM V SUBSTEAM [] LOCALTEAM I VISITORTEAM
							Elements team1Subs = substeams.get(0).select("localteam");
							Elements team2Subs = substeams.get(0).select("visitorteam");
							
							//ESLI NET lOCALTEAM ILI VISITORTEAM V START1 ILI START2, TO PROPUSKAEM MATCH
							if (team1Start.isEmpty() || team2Start.isEmpty())
								continue mark;
							
							//ESLI NET lOCALTEAM ILI VISITORTEAM V START1 ILI START2, TO PROPUSKAEM MATCH
							if (team1Subs.isEmpty() || team2Subs.isEmpty())
								continue mark;
							
							//BEREM PLAYERS V START1,START2,SUBS1,SUBS2
							Elements playerstart1=team1Start.get(0).select("player");
							Elements playerstart2=team2Start.get(0).select("player");
							Elements playersubs1=team1Subs.get(0).select("player");
							Elements playersubs2=team2Subs.get(0).select("player");
							
							//ESLI V START1 ILI START2 KLK PLAYERS NE 11, TO PROPUSKAEM MATCH
							if ((playerstart1.size()!=11) || (playerstart2.size()!=11))
								continue mark;
							
							
							//esli match uze imeet predvarilovku, a starta eshe net, to berem ego s hashstart
							if(hashstart.containsKey(team1id) && ("0".equals(hashstart.get(team1id).feed)) && ((playersubs1.size()==0) || (playersubs2.size()==0))) {
								if(prListGame.contains(hashstart.get(team1id)))
									continue;
								else {
									prListGame.add(hashstart.get(team1id));
									continue;
								}
							}
							

							//SOZDAEM SPISOK IGROKOV START1 I START2
							List<StartPlayer> start1=new ArrayList();
							List<StartPlayer> start2=new ArrayList();
							
							//ZAPOLNIAEM SPIOK IGROKOV START1
							for(Element el:playerstart1) {
								try {
									int a=Integer.valueOf(el.attr("id"));
									int b=-1;
									String namePl=el.attr("name");
									start1.add(new StartPlayer(a,b,namePl,-1,-1));
									}
								catch(Exception e) {
									try {
										int a=-1;
										int b=Integer.valueOf(el.attr("number"));
										String namePl=el.attr("name");
										start1.add(new StartPlayer(a,b,namePl,-1,-1));
										}
									catch(Exception ex) {
										continue mark;
									}							
								}		
								
							}
							
							//ZAPOLNYAEM SPISOK IGROKOV START2
							for(Element el:playerstart2) {
								try {
									int a=Integer.valueOf(el.attr("id"));
									int b=-1;
									String namePl=el.attr("name");
									start2.add(new StartPlayer(a,b,namePl,-1,-1));
									}
								catch(Exception e) {
									try {
										int a=-1;
										int b=Integer.valueOf(el.attr("number"));
										String namePl=el.attr("name");
										start2.add(new StartPlayer(a,b,namePl,-1,-1));
										}
									catch(Exception ex) {
										continue mark;
									}							
								}		
							}
							
							//SOZDAEM SPISOK IGROKOV SUBS1 I SUBS2
							List<StartPlayer> subs1=new ArrayList();
							List<StartPlayer> subs2=new ArrayList();
							
							//ZAPOLNYAEM SPISOK SUBS1
							for(Element el:playersubs1) {
								try {
									int a=Integer.valueOf(el.attr("id"));
									int b=-1;
									String namePl=el.attr("name");
									subs1.add(new StartPlayer(a,b,namePl,-1,-1));
									}
								catch(Exception e) {
									try {
										int a=-1;
										int b=Integer.valueOf(el.attr("number"));
										String namePl=el.attr("name");
										subs1.add(new StartPlayer(a,b,namePl,-1,-1));
										}
									catch(Exception ex) {
										continue mark;
									}							
								}		
							}
							
							//ZAPOLNYAEM SPISOK SUBS2
							for(Element el:playersubs2) {
								try {
									int a=Integer.valueOf(el.attr("id"));
									int b=-1;
									String namePl=el.attr("name");
									subs2.add(new StartPlayer(a,b,namePl,-1,-1));
									}
								catch(Exception e) {
									try {
										int a=-1;
										int b=Integer.valueOf(el.attr("number"));
										String namePl=el.attr("name");
										subs2.add(new StartPlayer(a,b,namePl,-1,-1));
										}
									catch(Exception ex) {
										continue mark;
									}							
								}		
							}
							
							//SOZDAEM I ZAPOLNYAEM STATISTIKU IGROKOV DLYA TEAM1
							List<Player> listPlayer=new ArrayList();
							listPlayer=getPlayerStats(listPlayer,team1id);
							//SOZDAEM TEAM1
							Team team1=new Team(team1name,team1id,listPlayer,getLastGame(team1id));
							
							//DYA TEAM2 TOZE SAMOE
							List<Player> listPlayer2=new ArrayList();
							listPlayer2=getPlayerStats(listPlayer2,team2id);
							Team team2=new Team(team2name,team2id,listPlayer2,getLastGame(team2id));
													
							
							//esli match uze imeet predvarilovku i na zamene uze ne 0 igrokov, to berem  s hashstart predvarilovku i zapisivaem start
							if(hashstart.containsKey(team1id) && ("0".equals(hashstart.get(team1id).feed)) && ((playersubs1.size()!=0) || (playersubs2.size()!=0))) {
                                //zapolnyaem predvarilovku v atributi								
								Game predg=hashstart.get(team1id);
								int q=0;
								for(StartPlayer s:start1) {
									s.idpred=predg.start1.get(q).id;
									s.numpred=predg.start1.get(q).num;
									q++;
								}
								q=0;
								for(StartPlayer s:start2) {
									s.idpred=predg.start2.get(q).id;
									s.numpred=predg.start2.get(q).num;
									q++;
								}
								//esli v pervom feed, to feed=1. a esli vo vtorom, to feed=2
								if(knthr==0) {
									Game g=new Game(date,time,status,tournament, team1,team2,start1,start2,subs1,subs2,"1",new Date());
									prListGame.add(g);
									hashstart.remove(team1id);
									hashstart.put(team1id, g);
									continue mark;
								}
								else {
									Game g=new Game(date,time,status,tournament, team1,team2,start1,start2,subs1,subs2,"2",new Date());
									prListGame.add(g);
									hashstart.remove(team1id);
									hashstart.put(team1id, g);
									continue mark;
								}
							}
							
							
							//esli feed=0, to eto predvarilovka
							//esli na zamene ne 0 igrokov to eto ne predvarilovka
							if((playersubs1.size()!=0) || (playersubs2.size()!=0)) {
								//esli v pervom feed, to feed=1. a esli vo vtorom, to feed=2
								if(knthr==0) {
									Game g=new Game(date,time,status,tournament, team1,team2,start1,start2,subs1,subs2,"1",new Date());
									prListGame.add(g);
									hashstart.put(team1id, g);
								}
								else {
									Game g=new Game(date,time,status,tournament, team1,team2,start1,start2,subs1,subs2,"2",new Date());
									prListGame.add(g);
									hashstart.put(team1id, g);
								}
							}
							else {
								Game g=new Game(date,time,status,tournament, team1,team2,start1,start2,subs1,subs2,"0",new Date(0,0,0,0,0,0));
								prListGame.add(g);
								hashstart.put(team1id, g);							
								}
							
						}
					}
				}
			catch(Exception e) {
				System.out.println("ERROR: "+e.toString());
			}
		knthr++;
		}
		
		parseCSV(hashstart,prListGame,log);
		
		//SOHRANAEM HASHSTART
		serialization(f,hashstart);
		
		//Sohranaem log startov
		serialization(logFile,log);
		
		//iz priorQueue v List
		int n=prListGame.size();
		for (int k = 0; k < n; k++) 
			result.add(prListGame.poll());
		
		//SOHRANAYEM LIST RESULT V FAIL
		new GameDAO().setGames(result);
		return result;
	}
	
	private List<Player> getPlayerStats(List<Player> l,int teamid){
		try {
			Document doc2 = Jsoup.connect("http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerstats/team/"+teamid).get();
			//BEREM SQUAD
			Elements squad = doc2.select("squad");
			//ESLI SQUAD KOMANDI PUSTOY, TO PROPUSKAEM MATCH 
			if(squad.isEmpty())
				throw new Exception("squad is empty");
			
			//BEREM PLAYERS V SQUAD
			Elements players=squad.get(0).select("player");
			if(players.isEmpty())
				throw new Exception("players i empty");
			
			//SOZDAEM PRIORITETNUYU OCHERED DLYA SORTIROVKI PO MINUTE
			PriorityQueue<Player> pr=new PriorityQueue<Player>(new Comparator<Player>() {
				public int compare(Player t, Player t1) {
					if (t.minutes < t1.minutes)
						return 1;
					if (t.minutes > t1.minutes)
						return -1;
					else
						return 0;
					}
				}
			);
			
			//ZAPOLNYAEM STATU IGROKOV
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
					continue;
				}
			}
			
			//PEREVODIM PRIORITETNUYU OCHERED V LIST
			int n=pr.size();
			for (int k = 0; k < n; k++) 
				l.add(pr.poll());
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return l;
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
		if (new File("team_lastgames.data").exists())
			return DAO.getGames().get(idTeam);
		else {
			parseLastGame();
			return DAO.getGames().get(idTeam);
		}
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
	
	private void serialization(File f, Object m) {
		FileOutputStream fOut = null;
		ObjectOutputStream oOut = null;
		try {
			fOut = new FileOutputStream(f);
			oOut = new ObjectOutputStream(fOut);
			oOut.writeObject(m);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				fOut.close();
				oOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Object deserialization(File f) {
		FileInputStream fInput = null;
		ObjectInputStream oInput = null;
		Object result = null;
		try {
			fInput = new FileInputStream(f);
			oInput = new ObjectInputStream(fInput);
			result = oInput.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				fInput.close();
				oInput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
