//Maja Lund och Max Bertilsson (malu9669 och mobe2864)

import java.util.Collections;

public class Competition {

	private ParticipantList pListInstance = new ParticipantList();
	private EventList eventListInstance = new EventList();
	private Input inputInstance = new Input();
	private TeamList teamListInstance = new TeamList();

	//metoder		

	private void addEvent(){

		String eventName = inputInstance.inputEventName();
		eventListInstance.searchEvent(eventName);
		while(eventName.isEmpty()){
			System.out.println("Names can't be empty!");
			String inputEventName2 = inputInstance.inputEventName();
			eventName = inputEventName2;
		}

		if(eventListInstance.searchEvent(eventName)==true){
			System.out.println(eventName +" has already been added");
			printMenu();
		}

		int attempts = inputInstance.inputAttempts();			
		while(attempts<1){
			System.out.println("The number needs to be higher than 1. Try again.");
			System.out.println("");
			int attempts2 = inputInstance.inputAttempts();
			attempts = attempts2;
		}

		String compareResult = inputInstance.inputCompareResult();
		compareCommand(compareResult);

		Event eventInstance = new Event(eventName, attempts, compareResult);
		eventListInstance.getEventList().add(eventInstance);
		System.out.println(eventName + " added");
	}

	public void compareCommand(String compareResult){

		switch(compareResult){
		case "yes":
			break;
		case "y":
			break;
		case "no":
			break;
		case "n":
			break;
		default: 
			System.out.println("The command doesn't exist, try again");
			compareCommand(inputInstance.inputCompareResult());
		}
	}	

	private void addParticipant(){
		int i = pListInstance.getParticipantList().size();

		String firstName = inputInstance.inputFirstName();		
		while(firstName.isEmpty()){
			System.out.println("Names can't be empty!");
			String firstName2 = inputInstance.inputFirstName();
			firstName = firstName2;	
		}

		String lastName = inputInstance.inputLastName();
		while(lastName.isEmpty()){
			System.out.println("Names can't be empty!");
			String lastName2 = inputInstance.inputLastName();
			lastName = lastName2;	
		}

		String team = inputInstance.inputTeam();

		while(team.isEmpty()){
			System.out.println("Names can't be empty!");
			String team2 = inputInstance.inputTeam();
			team = team2;	
		}

		Team temp = teamListInstance.searchTeam(team);

		if(temp == null){
			Team newTeam = new Team(team);
			Participant participantInstance = new Participant(firstName, lastName, newTeam);
			newTeam.addTeamMember(participantInstance);
			pListInstance.getParticipantList().add(participantInstance);

			teamListInstance.addTeam(newTeam);
		}else{
			Participant participantInstance = new Participant(firstName, lastName, temp);
			pListInstance.getParticipantList().add(participantInstance);
			temp.addTeamMember(participantInstance);
		}

		Participant p = pListInstance.getParticipantList().get(i);
		System.out.println(p.getFirstName() + " " + p.getLastName() + " from " + p.getTeam().getTeamName() + " with number " + p.getStartNumber() + " added");
	}

	private void removeParticipant(){
		int inputParticipantNumber = inputInstance.inputInt("Number: ");;
		Participant removedParticipant = pListInstance.getIndexParticipant(inputParticipantNumber);
		
		boolean participantExist = pListInstance.removeParticipant(inputParticipantNumber);
		
		if(participantExist==true){
			
			String fullName = removedParticipant.getFirstName()+ " " + removedParticipant.getLastName();
			Team team = removedParticipant.getTeam();
			String teamName = team.getTeamName();
			System.out.println(fullName + " from " + teamName + " with number " + inputParticipantNumber + " removed");
			
		Team removeTeam = removedParticipant.getTeam();
		teamListInstance.removeTeam(removeTeam);
		}
		else{
			System.out.println("No participant with number " + inputParticipantNumber  +  " exists");
		}
	}

	private void addResult(){

		int startNumber = inputInstance.inputStartNumber();
		if(pListInstance.searchStartNumber(startNumber)==false){
			System.out.println("There is no participant with that number");
			printMenu();	
		}

		String eventName = inputInstance.inputEventNameResult();

		if(eventListInstance.searchEvent(eventName) == false){
			System.out.println("There is no such event");
			printMenu();
		}

		Participant temp = pListInstance.getIndexParticipant(startNumber);

		String firstName = temp.getFirstName();
		String lastName = temp.getLastName();
		Team team = temp.getTeam();
		String teamName = temp.getTeam().getTeamName();
		Event event = eventListInstance.searchEvent2(eventName);

		if(temp.searchResult(temp, event) == true){
			System.out.println("Too many attempts!");
			printMenu();
		}else{

			double result = inputInstance.inputDouble("Results for " + firstName + " " + lastName +  " from " + teamName + " in " +  eventName + ": ");
			while(result<0){
				System.out.println("Must be greater than or equal to zero!");
				double result2 = inputInstance.inputDouble("Results for " + firstName + " " + lastName +  " from " + teamName + " in " +  eventName + ": ");
				result = result2;
			}

			Result existingResult = temp.getResultParticipant(temp, event);

			if(existingResult != null){
				existingResult.addResult(result);
				event.removeResultEvent(temp);
				event.addResult(existingResult);
				
			}else{
				Result newResult = new Result(temp, team, event);
				newResult.addResult(result);
				temp.addResult(newResult);
				event.addResult(newResult);
			} 
		}
	}

	private void getResultParticipant(){

		int startNumber = inputInstance.inputStartNumber();
		Participant participant = pListInstance.getIndexParticipant(startNumber);
		if(participant == null){
			System.out.println("No participant with number " + startNumber);
			printMenu();
		}
		for(int i=0; i<eventListInstance.getEventList().size(); i++){
			if(eventListInstance.getEventList().get(i) != null){
				Event event = eventListInstance.getEventList().get(i);
				Result result = participant.getResultParticipant(participant, event);
				
				if(participant.getResultParticipant(participant, event) != null){
					String firstName = result.getParticipant().getFirstName();
					String lastName = result.getLastName();
					String eventName = event.getEventName();
					System.out.print("Result for " + firstName + " " + lastName +" in " + eventName + ":");
					result.getList();
				}
			}
		}
	}

	private void getResultEvent(String event){
		
		Event existingEvent = eventListInstance.searchEvent2(event); //kollar om event finns
		System.out.println("Result for " + event);
		existingEvent.printResult(); //hämtar och skriver ut listan med result
	}

	private void getResultTeam(){ 
		teamListInstance.clearTeamMedals();
		int numberOfTeams = teamListInstance.getTeamList().size();

		if (numberOfTeams == 0){
			System.out.println("No teams available");
			printMenu();
		}

		for(Event event : eventListInstance.getEventList()){ //kollar varje event
			event.getEventResult(); //tilldelar medaljer för varje event
			for(Participant participant : event.getGoldMedal()){
				participant.getTeam().addGoldMedal(); //lägger till medalj i team
			}
			for(Participant participant : event.getSilverMedal()){
				participant.getTeam().addSilverMedal();
			}
			for(Participant participant : event.getBronzeMedal()){
				participant.getTeam().addBronzeMedal();
			}
		}

		Collections.sort(teamListInstance.getTeamList());

		System.out.println("1st    2nd    3rd    Team name");
		System.out.println("************************************");
		for(int i= 0; i<numberOfTeams; i++){

			System.out.println(teamListInstance.getTeamList().get(i).getGoldMedal() + "       " + teamListInstance.getTeamList().get(i).getSilverMedal() + "      " + teamListInstance.getTeamList().get(i).getBronzeMedal() + "      " + teamListInstance.getTeamList().get(i).getTeamName());
		}
	}

	private String message(String text){
		int message = text.indexOf("message");

		if(message == -1){
			return text;
		}else{
			String result = "";
			System.out.println("");
			for(int i=message+7; i<text.length(); i++){
				result = result + text.charAt(i);
			}
			int spaces = 57-result.length();
			for(int blank=0; blank<spaces; blank++){
				result = result + " ";
			}

			for(int i=0; i<60; i++){
				System.out.print('#');
			}
			System.out.println();

			for (int i=0; i<60; i++){
				if(i==0 || i==59){
					System.out.print('#');
				}
				else{
					System.out.print(" ");
				}
			}
			System.out.println();

			for (int i=0; i<60; i++){
				if(i==0){
					System.out.print('#');
				}
				if(i==2){
					for(int m=0; m<57; m++){
						System.out.print(result.toUpperCase().charAt(m));
					}
				}
				if(i==58){ 
					System.out.print(" ");
				}
				if(i==59){	
					System.out.println('#');
				}

			}for (int i=0; i<60; i++){
				if(i==0 || i==59){
					System.out.print('#');
				}
				else{
					System.out.print(" ");
				}
			}
			System.out.println();

			for(int i=0; i<60; i++){
				System.out.print('#');
			}
			System.out.println();

			return "message";
		}
	}

	private void deleteData(){
		pListInstance.getParticipantList().clear();
		teamListInstance.getTeamList().clear();
		Participant.setCounter();
		String text = " ALL DATA HAS BEEN REMOVED                               ";
		for(int i=0; i<60; i++){
			System.out.print('#');
		}
		System.out.println();
		for (int i=0; i<60; i++){
			if(i==0 || i==59){
				System.out.print('#');
			}
			else{
				System.out.print(" ");
			}
		}
		System.out.println();
		for (int i=0; i<60; i++){
			if(i==0){
				System.out.print('#');
			}
			if(i==2){
				System.out.print(text);
			}
			if(i==58){
				System.out.print(" ");
			}
			if(i==59){	
				System.out.println('#');
			}
		}for (int i=0; i<60; i++){
			if(i==0 || i==59){
				System.out.print('#');
			}
			else{
				System.out.print(" ");
			}
		}
		System.out.println();
		for(int i=0; i<60; i++){
			System.out.print('#');
		}
		System.out.println();
	}

	private void exit(){
		System.out.println("Good bye! Welcome back anytime!\n\nProgram created by Maja Lund and Max Bertilsson");
		System.exit(0);
	}

	private void initiate(){
		System.out.println("Welcome!");
	}

	private void printMenu(){
		String command = inputInstance.readCommand();
		if(eventListInstance.searchEvent(command) == true){
			getResultEvent(command);
			System.out.println("");
			printMenu();
		}
		String command1 = message(command);
		performCommand(command1);
		System.out.println("");
		printMenu();
	}

	private void performCommand(String command){
		switch(command){
		case "add event":
			addEvent();
			break;
		case "add participant":
			addParticipant();
			break;
		case "remove participant":
			removeParticipant();
			break;
		case "add result":
			addResult();
			break;
		case "participant":
			getResultParticipant();
			break;
		case "teams":
			getResultTeam();
			break;
		case "message":
			break;
		case "reinitialize":
			deleteData();
			break;
		case "exit":
			exit();
			break;
		default:
			System.out.println("Unknown command \"" + command + "\"");
			printMenu();
		}		
	}

	public static void main(String[] args){

		Competition runProgram = new Competition();

		runProgram.initiate();
		runProgram.printMenu();
	}
}
