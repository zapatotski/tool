package main.java.injury;

import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import main.java.injury.Eventes;
import main.java.injury.Matchi;
import main.java.injury.Stat;
import main.java.injury.Stat.Statistics.Season.Value;
import main.java.live.Game;
import main.java.live.Player;

public class Parser {
	
	
	   static File f=new File("kesh.xml");
	
	   public static Map<String,List<Game>> parse(URL url) {
		      LinkedHashMap<String, List<Game>> map = new LinkedHashMap();
 
		      try {
		    	  
		         HttpURLConnection e = (HttpURLConnection)url.openConnection();
		         int responseCode = e.getResponseCode();
		         String jsontext = "";
		         int i;
		         
		         if(responseCode == 200) {
		            BufferedInputStream gson = new BufferedInputStream(e.getInputStream());
		            ArrayList m = new ArrayList();

		            for(i = gson.read(); i != -1; i = gson.read()) {
		               m.add(Character.valueOf((char)i));
		            }

		            char[] key = new char[m.toArray().length];
		            int list = 0;

		            for(Iterator id = m.iterator(); id.hasNext(); ++list) {
		               Object j = id.next();
		               key[list] = ((Character)j).charValue();
		            }

		            jsontext = new String(key);
		         } else {
		            System.out.println("someproblem1");
		         }
                 
		         Gson var64 = new Gson();
		         Matchi var65 = (Matchi)var64.fromJson(jsontext, Matchi.class);
		         
		         
	             HashMap<Integer,Game> kesh = new HashMap();
	             Map<Integer,Game> keshout=new HashMap();
                     if(f.exists())
                       keshout=deserialization(f);
                 
		         for(i = 0; i < var65.sportItem.tournaments.length; ++i) {
		        	 
		            String var66 = var65.sportItem.tournaments[i].category.name + " " + var65.sportItem.tournaments[i].tournament.name;
		            ArrayList var67 = new ArrayList();
                   
		            
		            for(int var68 = 0; var68 < var65.sportItem.tournaments[i].events.length; ++var68) {
		               int var69 = var65.sportItem.tournaments[i].events[var68].id;		               	              
		               boolean stat = var65.sportItem.tournaments[i].hasEventPlayerStatistics;
		               String time = var65.sportItem.tournaments[i].events[var68].startTime;
		               String team1 = var65.sportItem.tournaments[i].events[var68].homeTeam.name;
		               String team2 = var65.sportItem.tournaments[i].events[var68].awayTeam.name;
		               String htScore = var65.sportItem.tournaments[i].events[var68].homeScore.current + "-" + var65.sportItem.tournaments[i].events[var68].awayScore.current;
		               String ftScore = var65.sportItem.tournaments[i].events[var68].homeScore.normaltime + "-" + var65.sportItem.tournaments[i].events[var68].awayScore.normaltime;
		               if("null-null".equals(ftScore)) {
		                  ftScore = "-";
		               }

		               if("null-null".equals(htScore)) {
		                  htScore = "-";
		               }

		               HashMap<Integer,Player> myel1 = new HashMap();
		               HashMap<Integer,Player> myelred1 = new HashMap();
		               HashMap<Integer,Player> mred1 = new HashMap();
		               HashMap<Integer,Player> msubs1 = new HashMap();
		               HashMap<Integer,Player> myel2 = new HashMap();
		               HashMap<Integer,Player> myelred2 = new HashMap();
		               HashMap<Integer,Player> mred2 = new HashMap();
		               HashMap<Integer,Player> msubs2 = new HashMap();
		               


		               System.out.println(var66+" "+new Date((new Date()).getYear(), (new Date()).getMonth(), (new Date()).getDate()).getTime()+" "+var65.sportItem.tournaments[i].events[var68].startTimestamp * 1000L+" "+new Date((new Date()).getYear(), (new Date()).getMonth(), (new Date()).getDate(), 23, 59, 60).getTime());
                       if(stat && (var65.sportItem.tournaments[i].events[var68].startTimestamp * 1000L > (new Date((new Date()).getYear(), (new Date()).getMonth(), (new Date()).getDate())).getTime()) && (var65.sportItem.tournaments[i].events[var68].startTimestamp * 1000L < (new Date((new Date()).getYear(), (new Date()).getMonth(), (new Date()).getDate(), 23, 59, 60)).getTime())) {
		                  
		            	   Game var70=new Game(time, team1, team2, ftScore, ftScore, myel1, myelred1, mred1, msubs1, myel2, myelred2, mred2, msubs2);
			               
		            	   if((("finished".equals(var65.sportItem.tournaments[i].events[var68].status.type)) && (((new Date().getTime()/1000)-var65.sportItem.tournaments[i].events[var68].startTimestamp)>10000L) && keshout.containsKey(var69))  || "notstarted".equals(var65.sportItem.tournaments[i].events[var68].status.type)){ 
			            	   if(keshout.containsKey(var69))
			            		   var70=keshout.get(var69);
                                           
			            		   
			               }
			               else {
			               
			            	  URL url2 = new URL("https://www.sofascore.com/event/" + var69 + "/json?_=" + (new Date()).getTime() / 10000L);
	                          
			                  try {
			                     e = (HttpURLConnection)url2.openConnection();
			                     responseCode = e.getResponseCode();
			                     String g = "";
			                     if(responseCode == 200) {
			                        BufferedInputStream in = new BufferedInputStream(e.getInputStream());
			                        ArrayList str = new ArrayList();
	
			                        for(int b = in.read(); b != -1; b = in.read()) {
			                           str.add(Character.valueOf((char)b));
			                        }
	
			                        char[] chars = new char[str.toArray().length];
			                        int x = 0;
	
			                        for(Iterator ev = str.iterator(); ev.hasNext(); ++x) {
			                           Object gson2 = ev.next();
			                           chars[x] = ((Character)gson2).charValue();
			                        }
	
			                        g = new String(chars);
			                        Gson var71 = new Gson();
			                        Eventes var72 = (Eventes)var71.fromJson(g, Eventes.class);
	
			                        for(int h = 0; h < var72.incidents.length; ++h) {
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
			                              if("substitution".equals(var72.incidents[h].incidentType) && var72.incidents[h].injury) {
			                                 url3 = new URL("https://www.sofascore.com/player/" + var72.incidents[h].playerOut.id + "/statistics/json?_=" + (new Date()).getTime() / 10000L);
			                                 mt = 0;
			                                 ms = 0;
			                                 min = 0;
			                                 goal = 0;
			                                 yel = 0;
			                                 yelred = 0;
			                                 red = 0;
	
			                                 try {
			                                    e1 = (HttpURLConnection)url3.openConnection();
			                                    responseCode = e1.getResponseCode();
			                                    jsontext3 = "";
			                                    if(responseCode == 200) {
			                                       in3 = new BufferedInputStream(e1.getInputStream());
			                                       str3 = new ArrayList();
	
			                                       for(b3 = in3.read(); b3 != -1; b3 = in3.read()) {
			                                          str3.add(Character.valueOf((char)b3));
			                                       }
	
			                                       chars3 = new char[str3.toArray().length];
			                                       d = 0;
	
			                                       for(st = str3.iterator(); st.hasNext(); ++d) {
			                                          gson3 = st.next();
			                                          chars3[d] = ((Character)gson3).charValue();
			                                       }
	
			                                       jsontext3 = new String(chars3);
			                                       var73 = new Gson();
			                                       var74 = (Stat)var73.fromJson(jsontext3, Stat.class);
	
			                                       try {
			                                          var56 = var74.statistics[0].seasons[0].statistics;
			                                          var55 = var74.statistics[0].seasons[0].statistics.length;
	
			                                          for(var54 = 0; var54 < var55; ++var54) {
			                                             e2 = var56[var54];
			                                             if("Matches".equals(e2.groupName)) {
			                                                mt = e2.matchesTotal;
			                                                ms = e2.matchesStarting;
			                                                min = e2.minutesPerGame * mt;
			                                             }
	
			                                             if("Goals".equals(e2.groupName)) {
			                                                goal = e2.goals;
			                                             }
	
			                                             if("Cards".equals(e2.groupName)) {
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
	
			                                 if(var72.incidents[h].playerTeam == 1) {
			                                    msubs1.put(Integer.valueOf(var72.incidents[h].playerOut.id), new Player(var72.incidents[h].playerOut.id, var72.incidents[h].playerOut.name, "", min, mt, ms, goal, yel, yelred, red));
			                                 } else {
			                                    msubs2.put(Integer.valueOf(var72.incidents[h].playerOut.id), new Player(var72.incidents[h].playerOut.id, var72.incidents[h].playerOut.name, "", min, mt, ms, goal, yel, yelred, red));
			                                 }
			                              } else if("card".equals(var72.incidents[h].incidentType)) {
			                                 url3 = new URL("https://www.sofascore.com/player/" + var72.incidents[h].player.id + "/statistics/json?_=" + (new Date()).getTime() / 10000L);
			                                 mt = 0;
			                                 ms = 0;
			                                 min = 0;
			                                 goal = 0;
			                                 yel = 0;
			                                 yelred = 0;
			                                 red = 0;
	
			                                 try {
			                                    e1 = (HttpURLConnection)url3.openConnection();
			                                    responseCode = e1.getResponseCode();
			                                    jsontext3 = "";
			                                    if(responseCode == 200) {
			                                       in3 = new BufferedInputStream(e1.getInputStream());
			                                       str3 = new ArrayList();
	
			                                       for(b3 = in3.read(); b3 != -1; b3 = in3.read()) {
			                                          str3.add(Character.valueOf((char)b3));
			                                       }
	
			                                       chars3 = new char[str3.toArray().length];
			                                       d = 0;
	
			                                       for(st = str3.iterator(); st.hasNext(); ++d) {
			                                          gson3 = st.next();
			                                          chars3[d] = ((Character)gson3).charValue();
			                                       }
	
			                                       jsontext3 = new String(chars3);
			                                       var73 = new Gson();
			                                       var74 = (Stat)var73.fromJson(jsontext3, Stat.class);
	
			                                       try {
			                                          var56 = var74.statistics[0].seasons[0].statistics;
			                                          var55 = var74.statistics[0].seasons[0].statistics.length;
	
			                                          for(var54 = 0; var54 < var55; ++var54) {
			                                             e2 = var56[var54];
			                                             if("Matches".equals(e2.groupName)) {
			                                                mt = e2.matchesTotal;
			                                                ms = e2.matchesStarting;
			                                                min = e2.minutesPerGame * mt;
			                                             }
	
			                                             if("Goals".equals(e2.groupName)) {
			                                                goal = e2.goals;
			                                             }
	
			                                             if("Cards".equals(e2.groupName)) {
			                                                yel = e2.yellowCards;
			                                                yelred = e2.yellowRedCards;
			                                                red = e2.redCards;
			                                             }
			                                          }
			                                       } catch (Exception var57) {
			                                          //System.err.println(var72.incidents[h].player.id);
			                                       }
			                                    }
			                                 } catch (Exception var58) {
			                                    throw new RuntimeException(var58);
			                                 }
	
			                                 if(var72.incidents[h].playerTeam == 1) {
			                                    if("Yellow".equals(var72.incidents[h].type)) {
			                                       myel1.put(Integer.valueOf(var72.incidents[h].player.id), new Player(var72.incidents[h].player.id, var72.incidents[h].player.name, "", min, mt, ms, goal, yel, yelred, red));
			                                    } else if("YellowRed".equals(var72.incidents[h].type)) {
			                                       myelred1.put(Integer.valueOf(var72.incidents[h].player.id), new Player(var72.incidents[h].player.id, var72.incidents[h].player.name, "", min, mt, ms, goal, yel, yelred, red));
			                                    } else if("Red".equals(var72.incidents[h].type)) {
			                                       mred1.put(Integer.valueOf(var72.incidents[h].player.id), new Player(var72.incidents[h].player.id, var72.incidents[h].player.name, "", min, mt, ms, goal, yel, yelred, red));
			                                    }
			                                 } else if("Yellow".equals(var72.incidents[h].type)) {
			                                    myel2.put(Integer.valueOf(var72.incidents[h].player.id), new Player(var72.incidents[h].player.id, var72.incidents[h].player.name, "", min, mt, ms, goal, yel, yelred, red));
			                                 } else if("YellowRed".equals(var72.incidents[h].type)) {
			                                    myelred2.put(Integer.valueOf(var72.incidents[h].player.id), new Player(var72.incidents[h].player.id, var72.incidents[h].player.name, "", min, mt, ms, goal, yel, yelred, red));
			                                 } else if("Red".equals(var72.incidents[h].type)) {
			                                    mred2.put(Integer.valueOf(var72.incidents[h].player.id), new Player(var72.incidents[h].player.id, var72.incidents[h].player.name, "", min, mt, ms, goal, yel, yelred, red));
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
			                  var70 = new Game(time, team1, team2, ftScore, ftScore, myel1, myelred1, mred1, msubs1, myel2, myelred2, mred2, msubs2);
			                  }
			               
			                  var67.add(var70);
			                  kesh.put(var69,var70);
			               
			               }
		               
		               }

		            if(var67.size() != 0) {
		               map.put(var66, var67);
		            }
		         }
		         
		         serialization(kesh,f);
		         
		         return map;
		      } catch (Exception var63) {
		         throw new RuntimeException(var63);
		      }
		   }
	   
	   
	    public static void serialization(Map<Integer,Game> m, File f){
	        FileOutputStream fOut=null;
	        ObjectOutputStream oOut=null;
	        try{
	            fOut=new FileOutputStream(f);
	            oOut=new ObjectOutputStream(fOut);
	            oOut.writeObject(m);
	        }
	        catch(Exception e){
	            throw new RuntimeException(e);
	        }
	        finally{
	        	try{
	        		oOut.close();
	        	}
	        	catch(Exception e){
	        		throw new RuntimeException(e);
	        	}
	        }
	    }
	    
	    public static Map<Integer,Game> deserialization(File f){
	    	FileInputStream fInput=null;
	    	ObjectInputStream oInput=null;
	    	Map<Integer,Game> q=null;
	    	try{
	    		fInput=new FileInputStream(f);
	    		oInput=new ObjectInputStream(fInput);
	    		q=(Map<Integer,Game>) oInput.readObject();
	    	}
	    	catch(Exception e){
	    		throw new RuntimeException(e);
	    	}
	    	return q;
	    }
	   
}
