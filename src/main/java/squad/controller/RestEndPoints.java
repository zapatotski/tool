package squad.controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sun.jersey.multipart.FormDataParam;

import squad.model.bizmodel.Game;
import squad.model.bizmodel.LastGame;
import squad.model.dao.GameDAO;
import squad.model.service.Parser;

@Path("/api")
public class RestEndPoints {

	
	@GET
	@Path("/refresh")
	@Produces({ "application/xml", "application/json" })
	public List<Game> getGames() {
		return new GameDAO().getGames();
	}
	
	@GET
	@Path("/adminrefresh")
	public String getGames2() {
		new Parser().parse();
		return "Ok";
	}
	
	
	@GET
	@Path("/refreshlastgame")
	public void getLastGames() {
		Map <Integer,List<LastGame>> m=new Parser().parseLastGame();
	}
	
	@GET
	@Path("/team/{value}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getTeam(@PathParam("value") String id) {
		try {
			int idval=Integer.valueOf(id);
			Document doc=null;
			int popitka=0;
			while(true) {
				try {
					doc=Jsoup.connect("http://www.goalserve.com/getfeed/0f753737311c4e49a6c86f26264d4ae9/soccerstats/team/"+idval).get();
					if(doc.getElementsByTag("image").size()>0)
						doc.getElementsByTag("image").first().remove();
					if(doc.getElementsByTag("venue_image").size()>0)
						doc.getElementsByTag("venue_image").first().remove();
					break;
				} catch (IOException e) {
					popitka++;
					if(popitka==3) {
						e.printStackTrace();
						break;
					}
					continue;
				}
			}
			return Response.ok().entity(doc.html()).build();
		}
		catch(Exception e) {
			return Response.ok().build();
		}
	}
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response getSquad(@FormDataParam("file") InputStream uploadedInputStream){
		String uploadedFileLocation = new Date().getTime()+".csv";
		writeToFile(uploadedInputStream, uploadedFileLocation);
		return Response.status(200).build();
	}
	
	File logFile=new File("logStart.data");
	
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
		List<String> log=new ArrayList();
		if(logFile.exists())
			log=(List<String>) deserialization(logFile);
		
		String str="";
		BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(uploadedInputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                str+=line+"\n";
            }
        } catch (IOException e) {
            System.out.println("Oshibka");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // log warning
                }
            }
        }
		
		log.add((new Date().getHours()+3)+":"+new Date().getMinutes()+":"+new Date().getSeconds()+" Prinyal fail: "+str);
		String strout="";
        String[] arg=str.split("\n");
		String tournament="";
		String team1name="";
		String team2name="";
		String date="";
		String timestart="";
		int team1Id=-1;
		int team2Id=-1;
		String pos="";
		String start="";
		String number="";
		String birthday="";
		String team="";
		String playername="";
		List<String> lineup1=new ArrayList();
		List<String> lineup2=new ArrayList();
		List<String> subs1=new ArrayList();
		List<String> subs2=new ArrayList();
		for(String df:arg) {
			String[] strarg=df.split(";");
			if(strarg.length==8) {
				tournament=strarg[0]+":"+strarg[1];
				team1name=strarg[2];
				team2name=strarg[3];
				date=strarg[4];
				timestart=strarg[5];
				try {
					team1Id=Integer.valueOf(strarg[6].trim());
					team2Id=Integer.valueOf(strarg[7].trim());
				}
				catch(Exception except) {
					System.out.println("2");
					System.out.println(strarg[6]+" "+strarg[7]);
					except.printStackTrace(System.out);
					log.add("Oshibka pri preobrazovaniy ID v chislo: "+tournament+" "+team1name+"("+team1Id+") "+team2name+"("+team2Id+")");
					return;
				}
				strout+="<tournament name=\""+tournament+"\"><match status=\"Not Started\" date=\""+date+"\" timestart=\""+timestart+"\"><localteam name=\""+team1name+"\" id=\""+team1Id+"\"/><visitorteam name=\""+team2name+"\" id=\""+team2Id+"\"/>";
			}
			else {
				if(strarg.length==6) {
					team=strarg[0];
					pos=strarg[1];
					start=strarg[2];
					number=strarg[3].trim();
					birthday=strarg[4].replace("\\/", ".");
					playername=strarg[5].replace(",", "");
					if("1".equals(team)) {
						if("f".equals(start)) {
							lineup1.add("<player number=\""+number+"\" name=\""+playername+"\" pos=\""+pos+"\" birth=\""+birthday+"\"/>");
						}
						else {
							subs1.add("<player number=\""+number+"\" name=\""+playername+"\" pos=\""+pos+"\" birth=\""+birthday+"\"/>");
						}
					}
					else {
						if("f".equals(start)) {
							lineup2.add("<player number=\""+number+"\" name=\""+playername+"\" pos=\""+pos+"\" birth=\""+birthday+"\"/>");
						}
						else {
							subs2.add("<player number=\""+number+"\" name=\""+playername+"\" pos=\""+pos+"\" birth=\""+birthday+"\"/>");
						}
					}	
				}
			}
		}
		strout+="<teams><localteam>";
		for(String st:lineup1)
			strout+=st;
		strout+="</localteam>";
		strout+="<visitorteam>";
		for(String st:lineup2)
			strout+=st;
		strout+="</visitorteam></teams><substitutes><localteam>";
		for(String st:subs1)
			strout+=st;
		strout+="</localteam><visitorteam>";
		for(String st:subs2)
			strout+=st;
		strout+="</visitorteam></substitutes></match></tournament>";
		
		try {
			FileWriter writer = new FileWriter(uploadedFileLocation, false);
            writer.write(strout);
            writer.flush();
            writer.close();
			} catch (IOException e) {
				log.add("!!!Oshibka pri zapisi fayla!!!!");
				e.printStackTrace();
			}
		
		log.add((new Date().getHours()+3)+":"+new Date().getMinutes()+":"+new Date().getSeconds()+" Sohranil fail: ");
		//Sohranaem log startov
		serialization(logFile,log);
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
