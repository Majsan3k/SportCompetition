//Maja Lund och Max Bertilsson (malu9669 och mobe2864)

import java.util.ArrayList;
import java.util.Collections;

public class Event {

	//	Input inputInstance = new Input();
	private ArrayList<Result> eventResult = new ArrayList<Result>();
	private ArrayList<Participant> goldMedal = new ArrayList<Participant>();
	private ArrayList<Participant> silverMedal = new ArrayList<Participant>();
	private ArrayList<Participant> bronzeMedal = new ArrayList<Participant>();
	private ArrayList<Participant> noMedal = new ArrayList<Participant>();

	private String eventName;
	private int attemptsAllowed;
	private String compareResult;

	public void removeResultEvent(Participant p){

		for(int i=0; i<eventResult.size(); i++){
			if(eventResult.get(i).getParticipant() == p){
				eventResult.remove(i);
			}				
		}
	}

	public void addResult(Result r){
		eventResult.add(r);
	}

	public ArrayList<Participant> getGoldMedal() {
		return goldMedal;
	}

	public ArrayList<Participant> getSilverMedal() {
		return silverMedal;
	}

	public ArrayList<Participant> getBronzeMedal() {
		return bronzeMedal;
	}

	public ArrayList<Participant> getNoMedal() {
		return noMedal;
	}

	public Event(String eventName, int attemptsAllowed, String compareResult){
		this.eventName = eventName;
		this.attemptsAllowed = attemptsAllowed;
		this.compareResult = compareResult;
	}

	public String getEventName(){
		return eventName;
	}

	public int getAttemptsAllowed(){
		return attemptsAllowed;
	}

	public String getCompareResult(){
		return compareResult;
	}

	public boolean sameParticipant(Participant p, int result){
		boolean participantExist = false;

		for(int i=0; i<result; i++){
			if(eventResult.get(i).getParticipant() == p){
				participantExist = true;
			}
		}
		return participantExist;
	}

	public void printResult(){
		getEventResult();

		for(Participant p : goldMedal){
			double result= p.getBestResult(this);
			String participantName = p.getFirstName() + " " + p.getLastName();
			System.out.println("1. " + result + " " + participantName);
		}
		for(Participant p : silverMedal){
			double result= p.getBestResult(this);
			String participantName = p.getFirstName() + " " + p.getLastName();
			System.out.println("2. " + result + " " + participantName);
		}
		for(Participant p : bronzeMedal){
			double result= p.getBestResult(this);
			String participantName = p.getFirstName() + " " + p.getLastName();
			System.out.println("3. " + result + " " + participantName);
		}
		Participant prevParticipant = null;
		int rowNumber = goldMedal.size() + silverMedal.size() + bronzeMedal.size() + 1;
		int placement = 1;
		for(Participant p : noMedal){

			double result= p.getBestResult(this);
			if(prevParticipant!=null){
				double prevResult = prevParticipant.getBestResult(this);
				if(result!=prevResult){
					rowNumber = rowNumber + placement;
					placement = 1;
				}else{
					placement++;
				}
			}
			String participantName = p.getFirstName() + " " + p.getLastName();
			System.out.println(rowNumber + ". " + result + " " + participantName);
			prevParticipant = p;
		}
	}

	public void getEventResult(){
		goldMedal = new ArrayList<Participant>();
		silverMedal = new ArrayList<Participant>();
		bronzeMedal = new ArrayList<Participant>(); 
		noMedal = new ArrayList<Participant>();

		int placement= 1;
		int placeInList = 1;
		Collections.sort(eventResult);

		for(int i=0; i<eventResult.size(); i++){
			double result= eventResult.get(i).getParticipant().getBestResult(eventResult.get(i).getEvent());
			Participant participant = eventResult.get(i).getParticipant();

			if(sameParticipant(participant, i) == false){
				if(i>0){
					double prevResult = eventResult.get(i-1).getParticipant().getBestResult(eventResult.get(i-1).getEvent());

					if(prevResult!=result){
						placement = placeInList;
					}
				}
				if(placement==1){
					goldMedal.add(participant);
				}else if(placement==2){
					silverMedal.add(participant);
				}else if(placement==3){
					bronzeMedal.add(participant);
				}else if(placement>3){
					noMedal.add(participant);
				}
				placeInList++;
			}		
		}
	}
}



