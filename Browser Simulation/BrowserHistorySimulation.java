import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class BrowserHistorySimulation {

    private LinkedList<String> history = new LinkedList<>();
    private int currentIndex = -1; // points to current page's index in history

    // Visit a new page: clears all "forward" history beyond current point
    void visitPage(String url) {
        // Remove everything after the current index (forward history)
        while (history.size() > currentIndex + 1) {
            history.removeLast();
        }
        history.add(url);
        currentIndex++;
        System.out.println("Visited: " + url);
    }

    void goBack() {
        if (currentIndex > 0) {
            currentIndex--;
            System.out.println("Back to: " + history.get(currentIndex));
        } else {
            System.out.println("No previous page exists.");
        }
    }

    void goForward() {
        if (currentIndex < history.size() - 1) {
            currentIndex++;
            System.out.println("Forward to: " + history.get(currentIndex));
        } else {
            System.out.println("No forward page exists.");
        }
    }

    void showCurrentPage() {
        if (currentIndex == -1) {
            System.out.println("No page visited yet.");
        } else {
            System.out.println("Current Page: " + history.get(currentIndex));
        }
    }

    void showHistory() {
        if (history.isEmpty()) {
            System.out.println("History is empty.");
            return;
        }
        ListIterator<String> it = history.listIterator();
        System.out.println("---- Full History ----");
        int i = 0;
        while (it.hasNext()) {
            String page = it.next();
            String marker = (i == currentIndex) ? "  <-- current" : "";
            System.out.println(i + ". " + page + marker);
            i++;
        }
    }

    public static void main(String[] args) {
        BrowserHistorySimulation browser = new BrowserHistorySimulation();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*********************************");
            System.out.println("   Mini Project - Browser History Simulation");
            System.out.println("*********************************");
            System.out.println("\n------ Browser Menu------");
            System.out.println("1. Visit new page");
            System.out.println("2. Go Back");
            System.out.println("3. Go Forward");
            System.out.println("4. Show Current Page");
            System.out.println("5. Show Full History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter URL: ");
                    String url = scanner.nextLine().trim();
                    if (url.isEmpty()) {
                        System.out.println("URL cannot be empty.");
                    } else {
                        browser.visitPage(url);
                    }
                    break;
                case 2:
                    browser.goBack();
                    break;
                case 3:
                    browser.goForward();
                    break;
                case 4:
                    browser.showCurrentPage();
                    break;
                case 5:
                    browser.showHistory();
                    break;
                case 6:
                    System.out.println("Exiting browser. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please select between 1 and 6.");
            }
        }
    }
}
