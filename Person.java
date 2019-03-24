package homework2P1;

public class Person {
	private String name;
	private int ticketNo;
	private Person nextPerson;
	private Person prevPerson;
	
	public Person(String newName, int ticket) {
		this.name = newName;
		this.ticketNo = ticket;
		nextPerson = null;
		prevPerson = null;
	}
	public String getName() {
		return name;
	}
	
	public int getTicketNo() {
		return ticketNo;
	}
	
	public Person getNext() {
		return nextPerson;
	}
	
	public Person getPrev() {
		return prevPerson;
	}
	
	public void setNext(Person a) {
		nextPerson = a;
	}
	
	public void setPrev(Person b) {
		prevPerson = b;
	}
}
