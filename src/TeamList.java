//Maja Lund och Max Bertilsson (malu9669 och mobe2864)

import java.util.ArrayList;

public class TeamList {

	private ArrayList<Team> teamList = new ArrayList<Team>();

	public void addTeam(Team inputTeam){
		teamList.add(inputTeam);
	}	

	public Team searchTeam(String inputTeam){

		for(int i=0; i<(teamList.size()); i++){	
			if(inputTeam.equalsIgnoreCase(teamList.get(i).getTeamName())){
				return teamList.get(i);
			}
		}
		return null;
	}

	public void clearTeamMedals(){
		for(Team team : teamList){
			team.clearAllMedals();
		}
	}

	public ArrayList<Team> getTeamList(){
		return teamList;
	}

	public void removeTeam(Team team){

		if(team.getTeamMembers().size() == 0){
			for(int i=0; i<teamList.size(); i++){
//			for(Team teams : teamList){
				if(teamList.get(i) == team){
					teamList.remove(i);
				}
			}
		}
	}
}
