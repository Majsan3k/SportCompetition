//Maja Lund och Max Bertilsson (malu9669 och mobe2864)

import java.util.Scanner;

public class Input {

	private Scanner keyboard = new Scanner(System.in);

	public String inputText(String text){
		System.out.print(text);
		return keyboard.nextLine().toLowerCase(); 
	}

	public int inputInt(String number){
		System.out.print(number);
		int temp = keyboard.nextInt();
		keyboard.nextLine();
		return temp;
	}

	public double inputDouble(String number){
		System.out.print(number);
		double temp = keyboard.nextDouble();
		keyboard.nextLine();
		return temp;
	}


	private String format(String text){
		if(text.isEmpty()==false){
			return (text.trim().toUpperCase().charAt(0) +  text.trim().substring(1).toLowerCase()).trim();
		}else{
			return ("");
		}
	}

	//input event

	public String inputEventName(){
		return format(inputText("Event name: "));
	}

	public int inputAttempts(){
		return inputInt("Attempts allowed: ");
	}

	public String inputCompareResult(){
		return inputText("Bigger better? (Y/N): ").trim();
	}

	//input participant
	public String inputFirstName(){
		return format(inputText("First name: "));
	}

	public String inputLastName(){
		return format(inputText("Last name: "));
	}

	public String inputTeam(){
		return format(inputText("Team: "));	
	}

	//input result
	public int inputStartNumber(){
		return inputInt("Number: ");
	}

	public String inputEventNameResult(){
		return inputText("Event: ");
	}

	//Input menu
	public String readCommand(){
		return inputText("Command >").trim().toLowerCase();		
	}
}