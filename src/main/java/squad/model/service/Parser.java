package squad.model.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
			    "http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/albania/KategoriaSuperiore",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/albania/League1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/albania/League2-GroupA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/albania/League2-GroupB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/algeria/Division-1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/algeria/Division2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/andorra/2aDivisio",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/andorra/PrimeraDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/angola/Girabola",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/Apertura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/ArgentinoA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/ArgentinoB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/Clausura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/PrimBMetro",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/Primera",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/primerab",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/PrimeraCMetro",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/PrimeraDMetro",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/argentina/SuperFinal",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/armenia/FirstLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/armenia/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/aruba/DivisiondiHonor",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/australia/A-League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/australia/Brisbane",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/australia/CapitalTerritory",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/australia/NewSouthWales",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/australia/NorthernNsw",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/australia/Nothern",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/australia/Queensland",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/australia/SouthAustralian",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/australia/Tasmania-North",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/australia/Tasmania-South",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/australia/TLeague",
			    "http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/australia/Victorian",
			    "http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/australia/WesternAustralia",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/1-Liga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Bundesliga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Landesliga-Burgenland",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Landesliga-Karnten",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Landesliga-Niederosterreich",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Landesliga-Oberosterreich",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Landesliga-Salzburg",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Landesliga-Steiermark",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Landesliga-Tirol",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Landesliga-Vorarlbergliga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Landesliga-Wien",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Regionalliga-Mitte",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Regionalliga-Ost",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/austria/Regionalliga-West",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/azerbaidjan/BirinciDasta",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/azerbaidjan/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/azerbaidjan/PremyerLiqasi",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bahrain/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bangladesh/BLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/barbados/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belarus/Division1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belarus/Division2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belarus/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Divison3-ACFF",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Divison3-GroupA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Divison3-GroupB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Playoffs",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Playoffs23",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/ProLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Promotion-GroupA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Promotion-GroupB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Promotion-GroupC",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Promotion-GroupD",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Provincial-Antwerpen",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Provincial-Brabant",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Provincial-Hainaut",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Provincial-Liege",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Provincial-Limburg",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Provincial-Luxembourg",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Provincial-Namur",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Provincial-Oost-Vlaanderen",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/Provincial-West-Vlaanderen",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/SecondDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belgium/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/belize/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/benin/ChampionnatNational",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bermuda/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bhutan/ADivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bolivia/PrimeraDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bosnia/1Liga-FBiH",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bosnia/1Liga-RS",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bosnia/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bosnia/PremierLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/botswana/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Acreano",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Alagoano",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Amapaense",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Amazonense",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Baiano1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Baiano2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Brasiliense",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/CampeonatoNordeste",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Capixaba",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Carioca1-TacaGuanabara",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Carioca1-TacaRio",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Carioca2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Catarinense",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Cearense",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Cearense2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Gaucho1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Gaucho2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Goiano",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Maranhense",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Matogrossense",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Mineiro1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Mineiro2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Paraense",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Paraibano",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Paranaense1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Paranaense2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/PaulistaA1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/PaulistaA2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/PaulistaA3",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Pernambucano",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Piauiense",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Potiguar",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Rondoniense",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Roraimense",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Sergipano",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/SerieA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/SerieB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/SerieC",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/SerieD",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/SulMatogrossense",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brazil/Tocantinense",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/brunei/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bulgaria/APFG",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bulgaria/BPFG-East",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bulgaria/BPFG-West",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bulgaria/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bulgaria/VAFG-Northeast",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bulgaria/VAFG-Northwest",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bulgaria/VAFG-Southeast",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/bulgaria/VAFG-Southwest",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/burkina_faso/1stDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/cambodia/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/cameroon/EliteOne",
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
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/congo_dr/SuperLigue",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/costa_rica/Apertura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/costa_rica/Clausura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/costa_rica/LigadeAscenso",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/croatia/2HNL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/croatia/3HNL-Istok",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/croatia/3HNL-Jug",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/croatia/3HNL-Sjever",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/croatia/3HNL-Sredite",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/croatia/3HNL-Zapad",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/croatia/cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/croatia/HNL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/cyprus/1-division",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/cyprus/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/cyprus/Division2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/cyprus/Division3",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/czechia/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/czechia/CzechLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/czechia/Liga2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/czechia/Liga3-CFL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/czechia/Liga3-MSFL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/czechia/Liga4-DivisionA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/czechia/Liga4-DivisionB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/czechia/Liga4-DivisionC",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/czechia/Liga4-DivisionD",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/czechia/Liga4-DivisionE",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/czechia/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/1stDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/2ndDivision-East",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/2ndDivision-Group1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/2ndDivision-Group2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/2ndDivision-Group3",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/2ndDivision-West",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/Denmarksserien-Group1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/Denmarksserien-Group2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/Denmarksserien-Group3",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/Denmarksserien-Group4",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/denmark/SuperLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/dominican_republic/LigaMayor",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ecuador/apertura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ecuador/clausura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ecuador/PrimeraB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/egypt/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/egypt/SecondDivison-Group1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/egypt/SecondDivison-Group2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/egypt/SecondDivison-Group3",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/egypt/SecondDivison-Group4",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/egypt/SecondDivison-Group5",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/egypt/SecondDivison-Group6",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/el_salvador/PrimeraDivision",
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
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/NonLeagueDivOne-IsthmianNorth",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/NonLeagueDivOne-IsthmianSouth",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/NonLeagueDivOne-NorthernNorth",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/NonLeagueDivOne-NorthernSouth",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/NonLeagueDivOne-SouthernCentral",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/NonLeagueDivOne-SouthernSW",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/NonLeaguePremier-Isthmian",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/NonLeaguePremier-Northern",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/NonLeaguePremier-Southern",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/england/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/estonia/Esiliiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/estonia/Meistriliiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ethiopia/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/champleague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/euro",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/europaleague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/EuroQualification",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/EuroU21",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/euro_qualification",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/eurocups/YouthLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/faroe_islands/1Delid",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/faroe_islands/Formuladeildin",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/fiji/NationalFootballLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/finland/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/finland/Kakkonen-LohkoA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/finland/Kakkonen-LohkoB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/finland/Kakkonen-LohkoC",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/finland/Kakkonen-LohkoD",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/finland/Veikkausliiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/finland/Ykkonen",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/CFA-GroupA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/CFA-GroupB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/CFA-GroupC",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/CFA-GroupD",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/CFA2-GroupA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/CFA2-GroupB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/CFA2-GroupC",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/CFA2-GroupD",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/CFA2-GroupE",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/CFA2-GroupF",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/CFA2-GroupG",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/CFA2-GroupH",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/LeagueCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/Ligue1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/Ligue2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/National",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/france/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/friendly/intl_friendly",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/gabon/ChampionnatD1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/georgia/PirveliLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/georgia/UmaglesiLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/3Liga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Bundesliga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Bundesliga2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Baden-Wurttemberg",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Bayern-Nord",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Bayern-Sud",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Bayern",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Bremen",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Hamburg",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Hessen",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Mittelrhein",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Niederrhein",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Niedersachsen",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Nordost-Nord",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Nordost-Sud",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-NRW",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Rheinland",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Schleswig-Holstein",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Sudwest",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Oberliga-Westfalen",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Playoffs12",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Playoffs23",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Regionalliga-Bayern",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Regionalliga-Nord",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Regionalliga-Nordost",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Regionalliga-Sud",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Regionalliga-SudWest",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/Regionalliga-West",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/germany/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ghana/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/greece/BEthiniki",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/greece/CEthniki-Group1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/greece/CEthniki-Group2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/greece/CEthniki-Group3",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/greece/CEthniki-Group4",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/greece/CEthniki-Group5",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/greece/CEthniki-Group6",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/greece/CEthniki-Notios",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/greece/CEthniki-Voreios",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/greece/cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/greece/SuperLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/grenada/PremierDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/guadeloupe/DivisionDHonneur",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/guatemala/LigaNacional",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/guatemala/PrimeraDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/guinea/Ligue1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/haiti/ChampionnatNational",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/holland/cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/holland/EersteDivisie",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/holland/Eredivisie",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/holland/Playoffs",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/holland/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/holland/Topklasse-Saturday",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/holland/Topklasse-Sunday",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/honduras/LigaNacional",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hong_kong/Hkfa1stDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hong_kong/Hkfa2ndDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hungary/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hungary/NBI",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hungary/NbII-East",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hungary/NbII-West",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hungary/NbIII-Alfold",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hungary/NbIII-Bakony",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hungary/NbIII-Drava",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hungary/NbIII-Duna",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hungary/NbIII-Matra",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hungary/NbIII-Nyugati",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/hungary/NbIII-Tisza",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iceland/1DelidKarla",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iceland/2DelidKarla",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iceland/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iceland/LeagueCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iceland/Urvalsdeild",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/india/ILeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/india/SanthoshTrophy",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/india/Superleague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/indonesia/ISL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/indonesia/SuperLiga",
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
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/intl/WomenOlympics",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iran/AzadeganLeague-GroupA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iran/AzadeganLeague-GroupB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iran/AzadeganLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iran/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iraq/IraqiLeague-North",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iraq/IraqiLeague-South",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/iraq/IraqiLeague",
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
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieC1-A",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieC1-B",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieC1-C",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieC2-A",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieC2-B",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieC2-C",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieD-A",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieD-B",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieD-C",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieD-D",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieD-E",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieD-F",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieD-G",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieD-H",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SerieD-I",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/italy/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ivory_coast/Ligue1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/jamaica/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/japan/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/japan/J-League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/japan/J2-League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/japan/J3League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/japan/NabiscoCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/jordan/League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/kazakhstan/1stDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/kazakhstan/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/kenya/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/korea/ChallengersLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/korea/FaCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/korea/K-League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/korea/K2League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/korea/LeagueCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/kuwait/Division1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/kuwait/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/kyrgyzstan/TopLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/latvia/1Liga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/latvia/Virsliga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/lebanon/League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/libya/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/lithuania/1Lyga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/lithuania/ALyga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/luxembourg/NationalDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/macedonia/FirstLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/macedonia/SecondLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/malawi/SuperLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/malaysia/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/malaysia/SuperLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/mali/PremiereDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/malta/FirstDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/malta/PremierDivision",
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
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/namibia/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/nepal/ADivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/new_zealand/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/nicaragua/1aDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/nigeria/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/northern_ireland/Championship1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/northern_ireland/IFAPremiership",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/norway/2Divisjon-Avdeling-1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/norway/2Divisjon-Avdeling-2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/norway/2Divisjon-Avdeling-3",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/norway/2Divisjon-Avdeling-4",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/norway/Adeccoligaen",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/norway/cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/norway/Eliteserien",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/oman/League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/pakistan/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/panama/PrimeraDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/paraguay/Apertura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/paraguay/Clausura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/paraguay/DivisionIntermedia",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/peru/CopaInca",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/peru/PrimeraDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/peru/SegundaDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/philippines/UFL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/poland/Division2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/poland/Ekstraklasa",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/poland/IILiga-East",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/poland/IILiga-West",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/poland/PolandCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/Division2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/IIDivisao-Centro",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/IIDivisao-GroupA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/IIDivisao-GroupB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/IIDivisao-GroupC",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/IIDivisao-GroupD",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/IIDivisao-GroupE",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/IIDivisao-GroupF",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/IIDivisao-GroupG",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/IIDivisao-GroupH",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/IIDivisao-Norte",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/IIDivisao-Sul",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/LeagueCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/PortugueseLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/portugal/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/qatar/League2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/qatar/StarsLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/LigaI",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/LigaII-Seria1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/LigaII-Seria2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/LigaIII-Seria1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/LigaIII-Seria2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/LigaIII-Seria3",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/LigaIII-Seria4",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/LigaIII-Seria5",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/LigaIII-Seria6",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/romania/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/russia/1-division",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/russia/2Division-Center",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/russia/2Division-East",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/russia/2Division-South",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/russia/2Division-Ural-Povolzhye",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/russia/2Division-West",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/russia/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/russia/Playoffs",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/russia/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/russia/YouthLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/rwanda/NationalSoccerLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/san_marino/Campionato",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/saudi_arabia/ChampionsCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/saudi_arabia/CrownPrinceCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/saudi_arabia/Division1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/saudi_arabia/SaudiProfessionalLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/scotland/ChallengeCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/scotland/Division1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/scotland/Division2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/scotland/Division3",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/scotland/FACup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/scotland/LeagueCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/scotland/Playoffs12",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/scotland/Playoffs23",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/scotland/Playoffs34",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/scotland/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/senegal/Ligue1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/serbia/PrvaLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/serbia/SrpskaLiga-Belgrade",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/serbia/SrpskaLiga-East",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/serbia/SrpskaLiga-Vojvodina",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/serbia/SrpskaLiga-West",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/serbia/SuperLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/singapore/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/singapore/SLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/slovakia/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/slovakia/IILiga-Bratislava",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/slovakia/IILiga-Center",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/slovakia/IILiga-Vychod",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/slovakia/IILiga-Zapad",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/slovakia/ILiga-Eeast",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/slovakia/ILiga-West",
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
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/SegundaB-Group1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/SegundaB-Group2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/SegundaB-Group3",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/SegundaB-Group4",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/SegundaB-Playoff",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/SuperCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group10",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group11",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group12",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group13",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group14",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group15",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group16",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group17",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group18",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group3",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group4",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group5",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group6",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group7",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group8",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/spain/Tercera-Group9",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sudan/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/surinam/Hoofdklasse",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/Allsvenskan",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/Damallsvenskan",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/Division1-Norra",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/Division1-Sodra",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/Division2-NorraGotaland",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/Division2-NorraSvealand",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/Division2-Norrland",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/Division2-SodraGotaland",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/Division2-SodraSvealand",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/Division2-VastraGotaland",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/playoffs12",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/playoffs23",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/sweden/Superettan",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/switzerland/ChallengeLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/switzerland/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/switzerland/SuperLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/syria/1stDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/tanzania/LigikuuBara",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/thailand/FACup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/thailand/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/thailand/ThaiDivision1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/trinidad_and_tobago/TnTProLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/tunisia/Ligue1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/turkey/3Lig-Group1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/turkey/3Lig-Group2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/turkey/3Lig-Group3",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/turkey/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/turkey/LigA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/turkey/LigB-Beyaz",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/turkey/LigB-Kirmizi",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/turkey/LigB-Playoff",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/turkey/SuperLig",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/uae/Division1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/uae/EmiratesCup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/uae/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ukraine/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ukraine/PershaLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ukraine/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ukraine/SecondLeague-GroupA",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/ukraine/SecondLeague-GroupB",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/uruguay/Apertura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/uruguay/Clausura",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/uruguay/SegundaDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/usa/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/usa/MLS",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/usa/NASL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/usa/PDL",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/usa/USL1",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/usa/USL2",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/uzbekistan/OliyLiga",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/venezuela/PrimeraDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/venezuela/SegundaDivision",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/vietnam/Cup",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/vietnam/VLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/vietnam/VLeague2",
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
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/yemen/League",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/zambia/PremierLeague",
				"http://www.goalserve.com/getfeed/4bc1263ae1554993aaf098ee804c88e9/soccerfixtures/zimbabwe/PremierSoccerLeague"
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
*/
	File f=new File("kashstart.data");
	
	public List<Game> parse(){		
		
		List<Game> result=new ArrayList();
		//v kakom feed poyavilsy start ranshe
		Map<Integer,String> map=new HashMap();
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
					
							//ESLI V MAP(V KAKOM FEED MATCH POYAVILSA RANSHE(ESLI V 1, TO NE SMOTRIM V LIGAH)) EST ID_GAME, TO PROPUSKAEM MATCH
							if(map.containsKey(idgame))
								continue mark;
							
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
							
							
							//ESLI V MAP HASHTART EST ID_GAME, TO DOBAVLAEM GAME V RESULT S MAP I IDEM K SLEDUYUSHEMU MATCHU
							if(hashstart.containsKey(idgame)) {
								result.add(hashstart.get(idgame));
								continue mark;
							}
					

							
							//BEREM DATU I VREMYA NACHALA MATCHA
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
								idgame=Integer.valueOf(matches.get(j).attr("id"));
								}
							catch(Exception e) {
								continue mark;
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
							

							//SOZDAEM SPISOK IGROKOV START1 I START2
							List<StartPlayer> start1=new ArrayList();
							List<StartPlayer> start2=new ArrayList();
							
							//ZAPOLNIAEM SPIOK IGROKOV START1
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
							
							//ZAPOLNYAEM SPISOK IGROKOV START2
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
							
							//SOZDAEM SPISOK IGROKOV SUBS1 I SUBS2
							List<StartPlayer> subs1=new ArrayList();
							List<StartPlayer> subs2=new ArrayList();
							
							//ZAPOLNYAEM SPISOK SUBS1
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
							
							//ZAPOLNYAEM SPISOK SUBS2
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
							
							//SOZDAEM I ZAPOLNYAEM STATISTIKU IGROKOV DLYA TEAM1
							List<Player> listPlayer=new ArrayList();
							listPlayer=getPlayerStats(listPlayer,team1id);
							//SOZDAEM TEAM1
							Team team1=new Team(team1name,team1id,listPlayer,getLastGame(team1id));
							
							//DYA TEAM2 TOZE SAMOE
							List<Player> listPlayer2=new ArrayList();
							listPlayer2=getPlayerStats(listPlayer2,team2id);
							Team team2=new Team(team2name,team2id,listPlayer2,getLastGame(team2id));
							
							//ESLI NA ZAMENE V ODNOY IZ KOMAND NE 0 IGROKOV, TO DOBAVLAEM V MAP V KAKOM FEED POYAVILSA START
							if((playersubs1.size()!=0) || (playersubs2.size()!=0))
								if(knthr==0)
									map.put(idgame,"1");
								else
									map.put(idgame,"2");
							
							//ESLI FEED=0, TO NA ZAMENE NET IGROKOV, ESLI FEED=1, TO POYAVILSA START V OBSHEM FEED, ESLI FEED=2, TO PO LIGAM  
							String feed="0";
							if(map.containsKey(idgame))
								feed=map.get(idgame);
							
							//ESLI NA ZAMENE EST IGROKI, TO SOZDAEM GAME I DOBAVLAEM V HASHSTART MATCH S VREMENEM POYAVLENIYA STARTA I V RESULT
							if(!"0".equals(feed)) {
								Game g=new Game(date,time,status,tournament, team1,team2,start1,start2,subs1,subs2,feed,new Date());
								hashstart.put(idgame, g);
								prListGame.add(g);
							}
							else {
								prListGame.add(new Game(date,time,status,tournament, team1,team2,start1,start2,subs1,subs2,feed,new Date(0,0,0,0,0,0)));
							}
						}
					}
				}
			catch(Exception e) {
				System.out.println("ERROR: "+e.toString());
			}
		knthr++;
		}
		//SOHRANAEM HASHSTART
		serialization(f,hashstart);
		
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
		return result;
	}

}
