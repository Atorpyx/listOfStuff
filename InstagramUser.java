package nicholas;
import java.util.ArrayList;
public class Student {
	private ArrayList<Student> following;
	private ArrayList<Student> followers;
	private ArrayList<Student> requestedFollowers;
	private ArrayList<Posts> posts;
	private String name;
	private boolean privateAccount;
	public Student(String name, boolean privateAccount){
		this.name = name;
		this.privateAccount = privateAccount;
		following = new ArrayList<Student>();
		followers = new ArrayList<Student>();
		requestedFollowers = new ArrayList<Student>();
		posts = new ArrayList<Posts>();
	}	
	public void follow(Student name) {
		if(name.isPrivate()) {
			name.addToReq(this);
			System.out.println("Requested to follow " + name.getName() + ".");
		}
		else {
			name.addToFollowers(this);
			following.add(name);
			System.out.println("Followed " + name.getName());
		}
	}
	public void acceptReq(Student name) {
			if(requestedFollowers.indexOf(name) != -1) {
				requestedFollowers.remove(requestedFollowers.indexOf(name));
				followers.add(name);
				name.addToFollowing(this);
				System.out.println("Approved " + name.getName() + " to your followers");
			}
			else
				System.out.println("Did not find " + name.getName() + " in your list of unapproved followers");
			
	}
	private void addToReq(Student name) {
		requestedFollowers.add(name);
	}
	private void addToFollowers(Student name) {
		followers.add(name);
	}
	private void addToFollowing(Student name) {
		following.add(name);
	}
	public String getName() {
		return name;
	}
	public void changeName(String name) {
		this.name = name;
	}
	public void changeAccountPrivitivity() {
		privateAccount = !privateAccount;
	}
	public boolean isPrivate() {
		return privateAccount;
	}
	public void createPost(String title, String link) {
		Posts p1 = new Posts(title, link);
		posts.add(p1);
	}
	public void getListOfFollowers() {
		if(followers.size() == 0)
			System.out.print(name + " has no followers");
		else if(followers.size() == 1) {
			System.out.print("List of followers: ");
			for(Student i: followers)
				System.out.print(i.getName() + ".");
		}
		else if(followers.size() == 2) {
			System.out.print("List of followers: ");
			for(Student i: followers) {
				
				if(followers.indexOf(i) == followers.size()-1)
					System.out.print(" and " + i.getName() + ".");
				else
					System.out.print(i.getName());
			}
		}
		else {
			System.out.print("List of followers: ");
			for(Student i: followers) {
				
				if(followers.indexOf(i) == followers.size()-1)
					System.out.print("and " + i.getName() + ".");
				else {
					System.out.print(i.getName() + ", ");
				}
			}
		}
		System.out.println();
	}
	public void getListOfFollowing() {
		if(following.size() == 0)
			System.out.print(name + " has no people they are following");
		else if(following.size() == 1) {
			System.out.print("List of people you follow: ");
			for(Student i: following)
				System.out.print(i.getName() + ".");
		}
		else if(following.size() == 2) {
			System.out.print("List of people you follow: ");
			for(Student i: following) {
				if(following.indexOf(i) == following.size()-1)
					System.out.print(" and " + i.getName() + ".");
				else 
					System.out.print(i.getName());
			}
		}
		else {
			System.out.print("List of people you follow: ");
			for(Student i: following) {
				if(following.indexOf(i) == following.size()-1)
					System.out.print("and " + i.getName() + ".");
				else {
					System.out.print(i.getName() + ", ");
				}
			}
		}
		System.out.println();
	}
	public void getListOfRequestedFollowers() {
		if(requestedFollowers.size() == 0)
			System.out.print(name + " has no people requesting to follow them");
		else if(requestedFollowers.size() == 1) {
			System.out.print("List of people wanting to follow them: ");
			for(Student i: requestedFollowers)
				System.out.print(i.getName() + ".");
		}
		else if(requestedFollowers.size() == 2) {
			System.out.print("List of people wanting to follow them: ");
			for(Student i: requestedFollowers) {
				if(requestedFollowers.indexOf(i) == requestedFollowers.size()-1)
					System.out.print(" and " + i.getName() + ".");
				else 
					System.out.print(i.getName());
			}
		}
		else {
			System.out.print("List of people wanting to follow them: ");
			for(Student i: requestedFollowers) {
				if(requestedFollowers.indexOf(i) == requestedFollowers.size()-1)
					System.out.print("and " + i.getName() + ".");
				else {
					System.out.print(i.getName() + ", ");
				}
			}
		}
		if(!privateAccount)
			System.out.print("(probably because their account isn't private)");
		System.out.println();
	}
	
	
}
