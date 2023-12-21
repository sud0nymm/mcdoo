package lab6;
import java.util.ArrayList;
import java.io.*;

public class PhoneBook {

	private ArrayList<Person> listOfNumbers;

	public PhoneBook() {
		listOfNumbers = new ArrayList<Person>();
	}
	
	public String printList() {
		String s = "";
		for (int i = 0; i < listOfNumbers.size(); i++) {
			s += listOfNumbers.get(i).getFullName() + " " + listOfNumbers.get(i).getPhoneNumber() +"\n";
		}
		return s;
	}
	
	public String Load(String arg) {
		
		try {
			File file = new File(arg);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = "";
			
			while (line!=null) {
				line = br.readLine();
				if (line == null) {
					break;
				}
				String[]s = line.split(" ");
				Person p = new Person(s[0], s[1], Integer.parseInt(s[2]));
				listOfNumbers.add(p);
			}	
			
			br.close();
		} catch (IOException ie) {
			return "Try again";
		}
		
		return "Phone Book Loaded";
	}
	
	public ArrayList<Person> search(String arg) {
		
		ArrayList<Person> returnlist = new ArrayList<Person>();
		int number = 0;
		// IF STATEMENT FOR CHECKING IF ARG CAN BE AN INT
		try {
			number = Integer.parseInt(arg);
		}catch (NumberFormatException e){
			;
		}
		
		for (int i = 0; i < listOfNumbers.size(); i++) {
			if (listOfNumbers.get(i).getSurname().equals(arg)) {
				returnlist.add(listOfNumbers.get(i));
			} else if (number!=0){
				if (listOfNumbers.get(i).getPhoneNumber()==number) {
					returnlist.add(listOfNumbers.get(i));
				}
			}
		}
		
		return returnlist;
	}
	
	public String deletePerson (String arg1, int arg2) {
		
		String returner = "Person does not exist";
		ArrayList<Person> test = new ArrayList<Person>();
		
		if (search(arg1)!=null && search(Integer.toString(arg2))!=null) {
			test = search(Integer.toString(arg2));
			for (int i = 0; i < listOfNumbers.size(); i++) {
				if (test.get(0).getSurname().equals(listOfNumbers.get(i).getSurname())){
					listOfNumbers.remove(i);
					returner = "person deleted\n";
				}
			}
		}
		
		return returner;
	}
	
	public boolean addPerson(String arg1, int arg2) {
		
		String[] s = arg1.split(" ");
		if (s.length!=2) {
			return false;
		}
		
		Person p = new Person(s[0], s[1], arg2);
		ArrayList<Person> p1 = new ArrayList<Person>();
		p1 = search(p.getSurname());
		if (p1 == null) {
			p1 = search(Integer.toString(p.getPhoneNumber()));
		}
		if (p1!=null ) {
			listOfNumbers.add(p);
			return true;
		}
		return false;
	}
	
	public String save(String arg) throws IOException {
		
	    File file = new File(arg);
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    writer.write(printList());
		writer.close();
		
		return "Saved " + listOfNumbers.size() + " people to the file";
	}
}






