package homework2P1;

public class SecurityCheck {
	private Line LineA;
	private Line LineB;
	
	public SecurityCheck() {
		//create lines and both lines should be empty before adding or removing people
		LineA = new Line();
		LineB = new Line();
	}
	
	public void addPerson(Person p) {
		//if both lines are originally empty or of equal length, add person to line A 
		if((LineA == null && LineB == null) || (LineA.getListLength() == LineB.getListLength())) {
			LineA.addPerson(p.getName(), p.getTicketNo());
			System.out.println(p.getName() + " has been added");
		} else {
			LineB.addPerson(p.getName(), p.getTicketNo());
			System.out.println(p.getName() + " has been added");
		}
	}
	
	public void removePerson(Person p) throws personNotFoundException {
		try {
			LineA.removePerson(p.getTicketNo());
			System.out.println("Person has been removed");
		} catch (personNotFoundException ex) {
			try {
				LineB.removePerson(p.getTicketNo());
				System.out.println("Person has been removed");
			} catch (personNotFoundException ex1) {
				throw new personNotFoundException("Person with ticketNo "+ p.getTicketNo() + " not found on either line!");
			}
		}
	}
	
	public void balance() { //removes tail from one line and adds to the other line
		if(LineA.getListLength() - LineB.getListLength() > 1) {
			LineB.addPerson(LineA.getTail().getName(), LineA.getTail().getTicketNo());
			try {
				LineA.removePerson(LineA.getTail().getTicketNo());
			} catch (personNotFoundException ex) {
				System.out.println(ex.getMessage());
			}
		} else if(LineB.getListLength() - LineA.getListLength() > 1) {
			LineA.addPerson(LineB.getTail().getName(), LineB.getTail().getTicketNo());
			try {
				LineB.removePerson(LineB.getTail().getTicketNo());
			} catch (personNotFoundException ex1) {
				System.out.println(ex1.getMessage());
			}
		} else {
			System.out.println("Lines are balanced");
		}
	}
	
	public boolean isBalanced() {
		if((LineA.getListLength() - LineB.getListLength() > 1) || (LineB.getListLength() - LineA.getListLength() > 1)) {
			return false;
		} else {
			return true;
		}
	}
	
	public void printSecurityCheck() {
		System.out.print("LineA: ");
		LineA.printLine();
		System.out.print("\nLineB: ");
		LineB.printLine();
	}
	
	public void printSecurityCheckReverse() {
		System.out.print("LineA: ");
		LineA.printLineReverse();
		System.out.print("\nLineB: ");
		LineB.printLineReverse();
	}
}
