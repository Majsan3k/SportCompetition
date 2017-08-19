//Maja Lund och Max Bertilsson (malu9669 och mobe2864)

import java.util.ArrayList;

public class EventList {

	private ArrayList<Event> eventList = new ArrayList<Event>();

	public boolean searchEvent(String inputEventName){
		boolean equalEventName = false;

		for(int i=0; i<eventList.size(); i++){
			if(inputEventName.equalsIgnoreCase(eventList.get(i).getEventName())){
				equalEventName = true;
			}
		}	
		return equalEventName;
	}

	public Event searchEvent2(String inputEvent){

		for(int i=0; i<(eventList.size()); i++){	
			if(inputEvent.equalsIgnoreCase(eventList.get(i).getEventName())){
				return eventList.get(i);
			}
		}
		return null;
	}

	public int searchAttemptsAllowed(String eventName){
		int attemptsAllowed = searchEvent2(eventName).getAttemptsAllowed();
		return attemptsAllowed;
	}

	public ArrayList<Event> getEventList(){
		return eventList;
	}

}
