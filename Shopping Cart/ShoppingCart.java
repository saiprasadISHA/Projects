import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

class Item {
    String name;
    int quantity;
    double price;

    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public double getTotal() {
        return quantity * price;
    }

    @Override
    public String toString() {
        return name + " [ Quantity : " + quantity + ", Price : " + price + ", Total : " + getTotal() + " ]";
    }
}

public class ShoppingCart {

    public static void main(String[] args) {
        LinkedList<Item> cart = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("***********************************");
        System.out.println("   Mini Project - Shopping Cart System");
        System.out.println("***********************************");

        while (true) {
            System.out.println("\nShopping Cart Menu:");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Update Quantity and Price");
            System.out.println("4. View First Added Item");
            System.out.println("5. View Last Added Item");
            System.out.println("6. View All Items & Total Bill");
            System.out.println("7. Clear the Cart");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.print("Enter Item name: ");
                    String name = scanner.nextLine().trim();

                    if (name.isEmpty()) {
                        System.out.println("Item name cannot be empty.");
                        break;
                    }

                    int quantity;
                    try {
                        System.out.print("Enter quantity: ");
                        quantity = Integer.parseInt(scanner.nextLine().trim());
                        if (quantity <= 0) {
                            System.out.println("Quantity must be greater than 0.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid quantity entered.");
                        break;
                    }

                    double price;
                    try {
                        System.out.print("Enter price: ");
                        price = Double.parseDouble(scanner.nextLine().trim());
                        if (price < 0) {
                            System.out.println("Price cannot be negative.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid price entered.");
                        break;
                    }

                    cart.add(new Item(name, quantity, price));
                    System.out.println(name + " added successfully.");
                    break;

                case 2:
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty.");
                        break;
                    }

                    System.out.print("Enter Item name to remove: ");
                    String itemToRemove = scanner.nextLine().trim();

                    boolean isRemoved = false;
                    Iterator<Item> itr = cart.iterator();
                    while (itr.hasNext()) {
                        if (itr.next().name.equalsIgnoreCase(itemToRemove)) {
                            itr.remove();
                            isRemoved = true;
                        }
                    }

                    if (isRemoved) {
                        System.out.println(itemToRemove + " removed from the cart.");
                    } else {
                        System.out.println(itemToRemove + " not found in the cart.");
                    }
                    break;

                case 3:
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty.");
                        break;
                    }

                    System.out.print("Enter item name to update: ");
                    String updateName = scanner.nextLine().trim();
                    boolean isFound = false;

                    for (Item item : cart) {
                        if (item.name.equalsIgnoreCase(updateName)) {
                            try {
                                System.out.print("Enter updated quantity: ");
                                int newQty = Integer.parseInt(scanner.nextLine().trim());
                                if (newQty <= 0) {
                                    System.out.println("Quantity must be greater than 0. Update cancelled.");
                                    break;
                                }

                                System.out.print("Enter updated price: ");
                                double newPrice = Double.parseDouble(scanner.nextLine().trim());
                                if (newPrice < 0) {
                                    System.out.println("Price cannot be negative. Update cancelled.");
                                    break;
                                }

                                item.quantity = newQty;
                                item.price = newPrice;
                                System.out.println("Item updated successfully.");
                                isFound = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number entered. Update cancelled.");
                            }
                            break;
                        }
                    }

                    if (!isFound) {
                        System.out.println("Item not found.");
                    }
                    break;

                case 4:
                    if (!cart.isEmpty()) {
                        System.out.println("First added item is: " + cart.getFirst());
                    } else {
                        System.out.println("Cart is empty.");
                    }
                    break;

                case 5:
                    if (!cart.isEmpty()) {
                        System.out.println("Last added item is: " + cart.getLast());
                    } else {
                        System.out.println("Cart is empty.");
                    }
                    break;

                case 6:
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty.");
                    } else {
                        System.out.println("---- Items in Cart ----");
                        double totalBill = 0;
                        for (Item item : cart) {
                            System.out.println(item);
                            totalBill += item.getTotal();
                        }
                        System.out.println("------------------------");
                        System.out.println("Total Bill: " + totalBill);
                    }
                    break;

                case 7:
                    cart.clear();
                    System.out.println("All items are cleared from the cart.");
                    break;

                case 8:
                    System.out.println("Thank you for Shopping ...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice entered. Please try again...");
            }
        }
    }
}
