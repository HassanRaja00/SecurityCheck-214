package homework2P1;

public class Line {
	private Person head;
	private Person tail; 
	private Person cursor;
	private int length; //used to keep track of the amount of people in a line
	
	public Line() {
		head = null;
		tail = null;
		cursor= null;
	}
	
	public void addPerson(String name, int ticketNo) {
		Person newPerson = new Person(name, ticketNo);
		//if list is empty, add person as head, tail and cursor
		if(head == null) {
			head = newPerson;
			tail = newPerson;
			cursor = newPerson;
			//if newPerson is going as first element, add before head
		} else if(newPerson.getTicketNo() < head.getTicketNo()) {
			head.setPrev(newPerson);
			newPerson.setNext(head);
			head = newPerson;
			//if newPerson is last element, add to tail
		} else if(newPerson.getTicketNo() > tail.getTicketNo()) {
			tail.setNext(newPerson);
			newPerson.setPrev(tail);
			tail = newPerson;
		} else {
			cursor = head;
			while(newPerson.getTicketNo() > cursor.getTicketNo()) {
				cursor = cursor.getNext();
			}
			//if the new ticketNo is greater than cursor, add after cursor (middle of list)
			newPerson.setPrev(cursor.getPrev());
			newPerson.setNext(cursor);
			cursor.getPrev().setNext(newPerson);
			cursor.setPrev(newPerson);
		}
		length++;	
	}
	
	public void removePerson(String name) throws personNotFoundException{
		//checking if list is empty
				cursor = head;
				if(head == null) {
			throw new personNotFoundException("The person with ticket number " + name + " is not on line");
		} else if(cursor.getPrev() == null && cursor.getNext() == null && cursor.getName().equals(name)) {
			//if there is only one element, make list head, tail, cursor null
			head = null;
			tail = null;
			cursor = null;
		} else if (name.equals(head.getName())){
			//if removing head
			head = head.getNext();
			head.setPrev(null);
		} else if (name.equals(tail.getName())) {
			tail = tail.getPrev();
			tail.setNext(null);
		} else {
			//traverse through list to find person
			while(cursor != null && !(cursor.getName().equals(name))) {
				cursor = cursor.getNext();
			}
			if(cursor == null) {
				throw new personNotFoundException("The person with ticket number " + name + " is not on line");
			}
			//if the ticket numbers match, set the previous persons link to the next person, vice versa
			cursor.getPrev().setNext(cursor.getNext());
			cursor.getNext().setPrev(cursor.getPrev());
		}
		//decrement length after removal
		length--;	
	}
	
	public void removePerson(int ticketNo) throws personNotFoundException{
		//checking if list is empty
		cursor = head;
		if(head == null) {
			throw new personNotFoundException("The person with ticket number " + ticketNo + " is not on line");
		} else if(cursor.getPrev() == null && cursor.getNext() == null && cursor.getTicketNo() == ticketNo) {
			//if there is only one element, make list head, tail, cursor null
			head = null;
			tail = null;
			cursor = null;
		} else if (ticketNo == head.getTicketNo()){
			//if removing head
			head = head.getNext();
			head.setPrev(null);
		} else if (ticketNo == tail.getTicketNo()) {
			tail = tail.getPrev();
			tail.setNext(null);
		} else {
			//traverse through list to find person
			while(cursor != null && cursor.getTicketNo() != ticketNo) {
				cursor = cursor.getNext();
			}
			if(cursor == null) {
				throw new personNotFoundException("The person with ticket number " + ticketNo + " is not on line");
			}
			//if the ticket numbers match, set the previous persons link to the next person, vice versa
			cursor.getPrev().setNext(cursor.getNext());
			cursor.getNext().setPrev(cursor.getPrev());
		}
		//decrement length after removal
		length--;	
}

			
	
	public int getListLength() { //checks the number of people in the line for balancing condition
		return this.length;
	}

	public Person getTail() {
		return this.tail;
	}
	public Person getHead() {
		return this.head;
	}
	
	public void printLine() {
		cursor = head;
		while(cursor != null) {
			System.out.print("[name: " + cursor.getName() + ", ticketNo:" + cursor.getTicketNo() + "]");
			cursor = cursor.getNext();
			if(cursor != null) {
				System.out.print(", ");
			}
		}
		System.out.print("\n");
	}
	
	public void printLineReverse() {
		cursor = tail;
		while(cursor != null) {
			System.out.print("[name: " + cursor.getName() + ", ticketNo:" + cursor.getTicketNo() + "]");
			cursor = cursor.getPrev();
			if(cursor != null) {
				System.out.print(", ");
			}
		}
		System.out.print("\n");
	}
	
}

class personNotFoundException extends Exception{
	public personNotFoundException(String message) {
		super(message);
	}
}