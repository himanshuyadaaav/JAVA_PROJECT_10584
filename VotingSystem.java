import java.util.*;

class Candidate {
    private String name;
    private int votes;

    public Candidate(String name) {
        this.name = name;
        this.votes = 0;
    }

    public void addVote() {
        votes++;
    }

    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }
}

// Main Class
public class VotingSystem {

    static Scanner sc = new Scanner(System.in);
    static HashSet<Integer> votedIds = new HashSet<>();
    static ArrayList<Candidate> candidates = new ArrayList<>();

    // Add candidates
    public static void setupCandidates() {
        candidates.add(new Candidate("RAJ"));
        candidates.add(new Candidate("SATYAM"));
        candidates.add(new Candidate("NITISH"));
    }

    // Menu Design
    public static void showMenu() {
        System.out.println("\n====================================");
        System.out.println("     ONLINE VOTING SYSTEM");
        System.out.println("====================================");
        System.out.println("1. Vote");
        System.out.println("2. View Results");
        System.out.println("3. Exit");
        System.out.println("====================================");
        System.out.print("Enter your choice: ");
    }

    // Voting Method
    public static void vote() {
        System.out.print("\nEnter Voter ID: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid Input!");
            sc.next();
            return;
        }

        int id = sc.nextInt();

        // Duplicate check
        if (votedIds.contains(id)) {
            System.out.println("You have already voted!");
            return;
        }

        // Show candidates
        System.out.println("\nChoose Candidate:");
        for (int i = 0; i < candidates.size(); i++) {
            System.out.println((i + 1) + ". " + candidates.get(i).getName());
        }

        System.out.print("Enter choice: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid choice!");
            sc.next();
            return;
        }

        int choice = sc.nextInt();

        if (choice < 1 || choice > candidates.size()) {
            System.out.println("Invalid choice!");
            return;
        }

        // Vote cast
        candidates.get(choice - 1).addVote();
        votedIds.add(id);

        System.out.println("Vote Cast Successfully!");
    }

    // Show Results
    public static void showResults() {
        System.out.println("\n=========== RESULTS ===========");

        int maxVotes = -1;
        Candidate winner = null;

        for (Candidate c : candidates) {
            System.out.println(c.getName() + " : " + c.getVotes() + " votes");

            if (c.getVotes() > maxVotes) {
                maxVotes = c.getVotes();
                winner = c;
            }
        }

        if (winner != null) {
            System.out.println("\nWinner: " + winner.getName());
        }

        System.out.println("===============================");
    }

    // Main Method
    public static void main(String[] args) {

        setupCandidates();

        int choice = 0; 

        do {
            showMenu();

            if (!sc.hasNextInt()) {
                System.out.println("Enter valid number!");
                sc.next();
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    vote();
                    break;
                case 2:
                    showResults();
                    break;
                case 3:
                    System.out.println("\nThank You!");
                    break;
                default:
                    System.out.println("Invalid option!");
            }

        } while (choice != 3);

        sc.close();
    }
}
