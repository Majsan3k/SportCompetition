//Maja Lund och Max Bertilsson (malu9669 och mobe2864)

import java.util.ArrayList;

public class Participant {

	private ArrayList<Result> participantResult = new ArrayList<Result>();

	private String firstName;
	private String lastName;
	private Team team;
	private int startNumber;
	private static int counter = 100;

	public ArrayList<Result> getParticipantResult(){
		return participantResult;
	}

	public void addResult(Result r){
		participantResult.add(r);
	}

	public Participant(String firstName, String lastName, Team team){
		this.firstName = firstName;
		this.lastName = lastName;
		this.team = team;
		this.startNumber = counter++; 
	}

	public boolean searchResult(Participant p, Event event){
		boolean tooManyAttempts = false;

		for(int i=0; i<participantResult.size(); i++){
			if(participantResult.get(i).getParticipant() == p && participantResult.get(i).getEvent() == event){

				if(participantResult.get(i).checkFullList(p, event) == true)
					tooManyAttempts = true;
			}
		}
		return tooManyAttempts;
	}

	public Result getResultParticipant(Participant p, Event event){

		for(int i=0; i<participantResult.size(); i++){
			if(participantResult.get(i).getParticipant() == p && participantResult.get(i).getEvent() == event){
				return participantResult.get(i);
			}				
		}
		return null;
	}
	
	public void removeResult(Participant removedParticipant ){
		
		for(Result result : participantResult){
			result.getEvent().removeResultEvent(this);
		}
		participantResult.clear();
	}
	
	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public Team getTeam(){
		return team;
	}

	public int getStartNumber(){
		return startNumber;
	}		

	public static void setCounter(){
		counter = 100;
	}

	public double getBestResult(Event e){
		
		double result=0;
		for(int i=0; i<participantResult.size(); i++){
			if(participantResult.get(i).getEvent().getEventName() == e.getEventName()){

				String compare = participantResult.get(i).getEvent().getCompareResult();
				if(compare.equalsIgnoreCase("yes") || compare.equals("y") ){
					result = participantResult.get(i).sortBiggerResult();
				}else{
					result = participantResult.get(i).sortLowerResult();
				}
			}
		}return result;
	}
}
