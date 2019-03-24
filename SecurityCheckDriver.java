package homework2P1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SecurityCheckDriver {
	public static void main(String[] a) {
		Scanner input = new Scanner(System.in);
		SecurityCheck lines = new SecurityCheck();
		String initialStatement = "(A) - Add person to line \n(R) - Remove person from line(ticket number) \n(P) - Print the lines \n(PR) - Print lines in reverse order (RP) - Removes person by name\n(Q) Quit";
		System.out.println("Menu:");
		boolean keepGoing = true;
		while(keepGoing) {
			System.out.println(initialStatement);
			System.out.println("Please select an option:");
			String option = input.nextLine();
			
			switch(option) {
			case "A" :
				try {
					System.out.print("Enter the number of people you want to add: ");
					int number = Integer.parseInt(input.nextLine());
					//System.out.println();
					for(int i=0; i < number; i++) {
						System.out.print("Enter the person's name and ticketNo (seperated by a space): ");
						String response = input.nextLine();
						String [] responseSplit = response.split(" ");
						lines.addPerson(new Person(responseSplit[0], Integer.parseInt(responseSplit[1])));
					}
					
				} catch(InputMismatchException ex) {
					System.out.println("Please enter the name followed by a space followed by the ticketNo\n");
				}catch(IllegalArgumentException ex1) {
					System.out.println("Please enter the name followed by a space followed by the ticketNo\n");
				} catch(Exception ex) {
					System.out.println("Something went wrong, try again with instructions!");
				}
				
				break;
			case "R" :
				try {
					System.out.print("Enter the number of people you want to remove: ");
					int removePeople = Integer.parseInt(input.nextLine());
					for(int i=0; i < removePeople; i++) {
						System.out.print("Enter the tickNo of the person you want to delete: ");
						String answer = input.nextLine();
						//String[] splitAnswer = answer.split(" ");
						//this creates a temporary person object to compare with those in the line
						lines.removePerson(new Person(" ", Integer.parseInt(answer)));
						try {
							if(!lines.isBalanced()) {
								while(!lines.isBalanced()) {
									lines.balance();	
								}
							}
						} catch (NullPointerException ex) {
							System.out.println("Empty list");
						}
						
					}
				}catch (InputMismatchException ex) {
					System.out.println("Please enter integers!\n");
				} catch(personNotFoundException ex1) {
					System.out.println(ex1.getMessage());
				} catch(Exception ex) {
					System.out.println("Enter the name followed by ticketNo! Try again.");
				}
				
				break;
			case "P" :
				try {
					lines.printSecurityCheck();
				} catch (NullPointerException ex) {
					System.out.println("You cannot print a line if there is no one in it!");
				}
				
				break;
			case "PR":
				try {
					lines.printSecurityCheckReverse();
				} catch (NullPointerException ex) {
					System.out.println("You cannot reverse an empty line!");
				}
				
				break;
			case "Q":
				System.out.print("Good-bye");
				input.close();
				System.exit(0);
				break;
			default:
				System.out.println("Wrong input. Try again!");
			}//end of switch
		}
	}

}

//A
//5
//Bob 1
//Jon 8
//Joe 3
//John 9
//Kate 2
//P