//Maja Lund och Max Bertilsson (malu9669 och mobe2864)

import java.util.ArrayList;

public class Team implements Comparable<Team> {

	private ArrayList <Participant> teamMembers = new ArrayList<Participant>(); 

	private String teamName;
	private int goldMedal;
	private int silverMedal;
	private int bronzeMedal;

	public ArrayList<Participant> getTeamMembers(){
		return teamMembers;

	}

	public void removeTeamMember(int removedParticipant){
		for(int i=0; i<teamMembers.size(); i++){
			if(removedParticipant == teamMembers.get(i).getStartNumber()){
				teamMembers.remove(i);
			}
		}
	}

	public String toString(){
		return teamName;
	}
	public void addGoldMedal(){
		goldMedal++;
	}

	public void clearAllMedals(){
		goldMedal = 0;
		silverMedal = 0;
		bronzeMedal = 0;
	}

	public void addSilverMedal(){
		silverMedal++;
	}

	public void addBronzeMedal(){
		bronzeMedal++;
	}

	public int getGoldMedal(){
		return goldMedal;
	}

	public int getSilverMedal(){
		return silverMedal;
	}

	public int getBronzeMedal(){
		return bronzeMedal;
	}

	public Team(String teamName){
		this.teamName = teamName;
	}

	public void addTeamMember(Participant participant){
		teamMembers.add(participant);
	}

	public String getTeamName(){
		return teamName;
	}

	public int compareTo(Team anotherTeam){

		if(getGoldMedal() > anotherTeam.getGoldMedal()){
			return -1;
		}else if(getGoldMedal() < anotherTeam.getGoldMedal()){
			return 1;
		}

		if(getGoldMedal() == anotherTeam.getGoldMedal()){
			if(getSilverMedal() > anotherTeam.getSilverMedal()){
				return -1;
			}else if(getSilverMedal() < anotherTeam.getSilverMedal()){
				return 1;
			}
		}
		if((getSilverMedal() == anotherTeam.getSilverMedal())){
			if(getBronzeMedal() > anotherTeam.getBronzeMedal()){
				return -1;
			}else if(getBronzeMedal() < anotherTeam.getBronzeMedal()){
				return 1;
			}
		}
		return 0;

	}

}
