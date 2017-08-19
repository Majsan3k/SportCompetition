//Maja Lund och Max Bertilsson (malu9669 och mobe2864)

import java.util.ArrayList;

public class ResultList {

	private ArrayList<Result> resultList = new ArrayList<Result>(); 

	public ArrayList<Result> getResultList(){
		return resultList;
	}

	public void addResult(Result newResult){
		resultList.add(newResult);
	}

	public void getResultTeam(){
		for(int i=0; i<resultList.size(); i++){
		}
	}
}

