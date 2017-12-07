package squad.controller;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.BufferedReader;

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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response getSquad(@FormDataParam("file") InputStream uploadedInputStream){
		String uploadedFileLocation = new Date().getTime()+".csv";
		writeToFile(uploadedInputStream, uploadedFileLocation);
		return Response.status(200).build();
	}
	
	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
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
		
        
        String strout="";
        String[] arg=str.split("\n");
		int knt=0;
		String tournament="";
		String team1name="";
		String team2name="";
		String date="";
		String timestart="";
		String team1Id="";
		String team2Id="";
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
			if(knt==0) {
				String[] strarg=df.split(";");
				if(strarg.length!=8)
					continue;
				tournament=strarg[1];
				team1name=strarg[2];
				team2name=strarg[3];
				date=strarg[4];
				timestart=strarg[5];
				team1Id=strarg[6];
				team2Id=strarg[7];
				strout+="<tournament name=\""+tournament+"\"><match status=\"Not Started\" date=\""+date+"\" timestart=\""+timestart+"\"><localteam name=\""+team1name+"\" id=\""+team1Id+"\"/><visitorteam name=\""+team2name+"\" id=\""+team2Id+"\"/>";
			}
			else {
				String[] strarg=df.split(";");
				if(strarg.length!=6)
					continue;
				team=strarg[0];
				pos=strarg[1];
				start=strarg[2];
				number=strarg[3];
				birthday=strarg[4];
				playername=strarg[5];
				if("1".equals(team)) {
					if("f".equals(start)) {
						lineup1.add("<player number=\""+number+"\" name=\""+playername+"\" pos=\""+pos+"\" />");
					}
					else {
						subs1.add("<player number=\""+number+"\" name=\""+playername+"\" pos=\""+pos+"\" />");
					}
				}
				else {
					if("f".equals(start)) {
						lineup2.add("<player number=\""+number+"\" name=\""+playername+"\" pos=\""+pos+"\" />");
					}
					else {
						subs2.add("<player number=\""+number+"\" name=\""+playername+"\" pos=\""+pos+"\" />");
					}
				}				
			}
			knt++;
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
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	
}
