package squad.model.service;

public class Matchi {
	public int id;
	public int league_id;
	public int season_id;
	public int stage_id;
	public int round_id;
	public int localteam_id;
	public int visitorteam_id;
	public Time time;
	//public Lineup lineup;
	//public Bench bench;
	
	public class Time{
		public String status;
		public Starting_at starting_at;
		
		public class Starting_at{
			public String date;
			public String time;
			public int timestamp;
		}
	}
	

}
