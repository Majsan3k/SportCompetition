//Maja Lund och Max Bertilsson (malu9669 och mobe2864)

import java.util.Arrays;

public class Result implements Comparable<Result>{

	private Participant participant;
	private Event event;
	private double[] resultList;

	public void emptyList(){
		for(int i=0; i<resultList.length; i++){
			resultList[i] = -1;
		}
	}

	public Result(Participant participant, Team team, Event event){
		this.participant = participant;
		this.event = event;
		resultList = new double[event.getAttemptsAllowed()];
		Arrays.fill(resultList, -1);
	}	

	public void setResultList(double[] newResultList){
		this.resultList = newResultList;
	}
	
	public double[] getResultList(){
		return resultList;
	}

	public Event getEvent(){
		return event;
	}

	public Participant getParticipant(){
		return participant;
	}

	public String getFirstName(){
		return participant.getFirstName();
	}

	public String getLastName(){
		return participant.getLastName();
	}

	public String getFullName(){
		return getFirstName() + " " + getLastName();
	}

	public void getList(){
		String str = ("");
		for(int i=0; i<resultList.length; i++){
			if(resultList[i] >= 0.0){
				str += (" " + resultList[i] + ",");
			}
		}
		str = str.substring(0, str.length()-1);
		System.out.println(str);
	}

	public boolean checkFullList(Participant p, Event event){
		boolean fullList = true;

		int i = 0;
		while(i<resultList.length){
			if(resultList[i] == -1){
				return fullList=false;
			}else{
				i++;
			}
		}
		return fullList;
	}

	public void addResult(double newResult){

		for(int i=0; i<resultList.length; i++){
			if(resultList[i] == -1){
				resultList[i]=newResult;
				return;
			}
		}
	}

	public double sortBiggerResult(){
		boolean resultSwitch;

		do{
			resultSwitch = false;
			for(int i=1; i<resultList.length; i++){
				if(resultList[i]>resultList[i-1]){
					Double temp = resultList[i];
					resultList[i] = resultList[i-1];
					resultList[i-1] = temp;
					resultSwitch = true;
				}
			}
		}while(resultSwitch);
		return resultList[0];
	}

	public double sortLowerResult(){
		boolean resultSwitch;

		do{
			resultSwitch = false;

			for(int i=1; i<resultList.length; i++){
				if(resultList[i]<resultList[i-1]){
					Double temp = resultList[i];
					resultList[i] = resultList[i-1];
					resultList[i-1] = temp;
					resultSwitch = true;
				}
			}
		}while(resultSwitch);
		return resultList[0];
	}

	public double getBestResult(Event event){

		double bestResult = 0;
		if(event.getCompareResult().equalsIgnoreCase("yes") || event.getCompareResult().equalsIgnoreCase("y")){
			bestResult = sortBiggerResult();
		}
		else if(event.getCompareResult().equalsIgnoreCase("no") || event.getCompareResult().equalsIgnoreCase("n")){
			bestResult = sortLowerResult();
		}
		return bestResult;
	}

	public int compareTo(Result anotherResult){

		if(event.getCompareResult().equalsIgnoreCase("yes") || event.getCompareResult().equalsIgnoreCase("y")){
			if(sortBiggerResult() > anotherResult.sortBiggerResult()){
				return -1;
			}
			else if(sortBiggerResult() < anotherResult.sortBiggerResult()){
				return 1;
			}
		}
		else{
			if(sortLowerResult() < anotherResult.sortLowerResult()){
				return -1;
			}
			else if(sortLowerResult() > anotherResult.sortLowerResult()){
				return 1;
			}
		}
		return 0;
	}

}