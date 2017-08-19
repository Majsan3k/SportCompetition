//Maja Lund och Max Bertilsson (malu9669 och mobe2864)

import java.util.ArrayList;

public class ParticipantList {

	private ArrayList<Participant> participantList = new ArrayList<Participant>();

	public boolean removeParticipant(int inputParticipantNumber){
		boolean equalParticipantNumber = false;

		for(int i=0; i<participantList.size(); i++){
			if(inputParticipantNumber==(participantList.get(i).getStartNumber())){
				participantList.get(i).removeResult(participantList.get(i));
				participantList.get(i).getTeam().removeTeamMember(inputParticipantNumber);
				participantList.remove(i);
				
				equalParticipantNumber = true;
			}		
		}
		return equalParticipantNumber;
	}

	public boolean searchStartNumber(int startNumberResult){
		boolean equalStartNumber = false;

		for(int i=0; i<participantList.size(); i++){
			if(startNumberResult == (participantList.get(i).getStartNumber())){
				equalStartNumber = true;
			}
		}		
		return equalStartNumber;
	}

	public Participant getIndexParticipant(int number){		
		for(int i=0; i<participantList.size(); i++){
			if(number == participantList.get(i).getStartNumber()){
				return participantList.get(i);
			}		
		}
		return null;
	}

	public ArrayList<Participant> getParticipantList(){
		return participantList;
	}
}
