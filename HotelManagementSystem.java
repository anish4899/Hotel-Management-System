import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Room class definition
class Room {
    int roomNumber;
    boolean isAvailable;
    String guestName;
    String roomType;
    double roomPrice;
    ArrayList<String> foodOrders;
    double totalBill;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isAvailable = true;
        this.guestName = "";
        this.roomType = "";
        this.roomPrice = 0.0;
        this.foodOrders = new ArrayList<>();
        this.totalBill = 0.0;
    }

    public void bookRoom(String guestName, String roomType, double roomPrice) {
        if (isAvailable) {
            this.isAvailable = false;
            this.guestName = guestName;
            this.roomType = roomType;
            this.roomPrice = roomPrice;  
            this.totalBill = roomPrice;  
            System.out.println("Room " + roomNumber + " (" + roomType + ") successfully booked for " + guestName);
        } else {
            System.out.println("Room " + roomNumber + " is already booked.");
        }
    }

    public void freeRoom() {
        if (!isAvailable) {
            this.isAvailable = true;
            this.guestName = "";
            this.roomType = "";
            this.roomPrice = 0.0;
            this.foodOrders.clear();
            this.totalBill = 0.0;
            System.out.println("Room " + roomNumber + " is now free.");
        } else {
            System.out.println("Room " + roomNumber + " is already free.");
        }
    }

    public void displayDetails() {
        if (isAvailable) {
            System.out.println("Room " + roomNumber + " is available.");
        } else {
            System.out.println("Room " + roomNumber + " (" + roomType + ") is booked by " + guestName);
            if (!foodOrders.isEmpty()) {
                System.out.println("Food orders: " + foodOrders);
            }
            System.out.println("Room Price: Rs" + roomPrice);
            System.out.println("Total bill(Including GST): Rs" + getTotalBill());
        }
    }

    public void orderFood(String foodItem, double price) {
        if (!isAvailable) {
            foodOrders.add(foodItem + " (Rs" + price + ")");
            totalBill += price + (price * 0.05); // Adding 5% GST for food
            System.out.println(foodItem + " has been added to the order for " + guestName);
        } else {
            System.out.println("Room is not booked. Cannot order food.");
        }
    }

    public double getTotalBill() {
        double roomGST = calculateRoomGST();
        return totalBill + roomGST; // Total bill includes room GST
    }

    private double calculateRoomGST() {
        double gstRate;
        if (roomPrice < 1000) {
            gstRate = 0.0;
        } else if (roomPrice >= 1001 && roomPrice <= 2499) {
            gstRate = 0.12;
        } else {
            gstRate = 0.18;
        }
        return roomPrice * gstRate; // GST for room
    }
}

// Hotel class definition
class Hotel {
    ArrayList<Room> rooms = new ArrayList<>();
    HashMap<Integer, String> foodMenu = new HashMap<>();
    HashMap<Integer, Double> foodPrices = new HashMap<>();
    HashMap<String, Double> roomTypePrices = new HashMap<>();

    public Hotel(int numberOfRooms) {
        for (int i = 1; i <= numberOfRooms; i++) {
            rooms.add(new Room(i));
        }
        initializeFoodMenu();
        initializeRoomPrices();
    }

    private void initializeFoodMenu() {
        foodMenu.put(1, "Veg Kabab");
        foodPrices.put(1, 120.0);
        foodMenu.put(2, "Chicken kabab");
        foodPrices.put(2, 220.0);
        foodMenu.put(3, "Dahi Chaat");
        foodPrices.put(3, 140.0);
        foodMenu.put(4, "Paneer Tikka");
        foodPrices.put(4, 249.0);
        foodMenu.put(5, "Chana Chilli");
        foodPrices.put(5, 189.0);
        foodMenu.put(6, "Baby Corn");
        foodPrices.put(6, 199.0);
        foodMenu.put(7, "Tandoori Chicken");
        foodPrices.put(7, 499.0);
        foodMenu.put(8, "Mutton Kabab");
        foodPrices.put(8, 399.0);
        foodMenu.put(9, "Fried Rice");
        foodPrices.put(9, 139.0);
        foodMenu.put(10, "Butter Chicken");
        foodPrices.put(10, 260.0);
        foodMenu.put(11, "Paneer Butter Masala");
        foodPrices.put(11, 230.0);
        foodMenu.put(12, "Palak paneer");
        foodPrices.put(12, 230.0);
        foodMenu.put(13, "Mutton Rogan Josh");
        foodPrices.put(13, 359.0);
        foodMenu.put(14, "Kadhai Chicken");
        foodPrices.put(14, 240.0);
        foodMenu.put(15, "Chicken Biryani");
        foodPrices.put(15, 299.0);
        foodMenu.put(16, "dal Makhni");
        foodPrices.put(16, 199.0);
        foodMenu.put(17, "Mix Veg");
        foodPrices.put(17, 179.0);
        foodMenu.put(18, "Shahi paneer");
        foodPrices.put(18, 240.0);
        foodMenu.put(19, "plain naan");
        foodPrices.put(19, 40.0);
        foodMenu.put(20, "Butter naan");
        foodPrices.put(20, 50.0);
        foodMenu.put(21, "Tawa roti");
        foodPrices.put(21, 20.0);
        foodMenu.put(22, "Tandoori Roti");
        foodPrices.put(22, 30.0);
        foodMenu.put(23, "Bread Basket");
        foodPrices.put(23, 259.0);
        foodMenu.put(24, "Gulab Jamun");
        foodPrices.put(24, 120.0);
        foodMenu.put(25, "Rasmalai");
        foodPrices.put(25, 99.0);
        foodMenu.put(26, "Ice Cream");
        foodPrices.put(26, 129.0);
        foodMenu.put(27, "Pastry");
        foodPrices.put(27, 79.0);
        foodMenu.put(28, "Cheese Cake");
        foodPrices.put(28, 139.0);
        foodMenu.put(29, "Chocolate Brownie");
        foodPrices.put(29, 169.0);
        foodMenu.put(30, "Ice Cream Sundae");
        foodPrices.put(30, 210.0);
        foodMenu.put(31, "Cold Drink");
        foodPrices.put(31, 40.0);
        foodMenu.put(32, "Tea");
        foodPrices.put(32, 39.0);
        foodMenu.put(33, "Coffee");
        foodPrices.put(33, 79.0);
        foodMenu.put(34, "Fresh Lime Soda");
        foodPrices.put(34, 99.0);
        foodMenu.put(35, "Oreo Shake");
        foodPrices.put(35, 120.0);
        foodMenu.put(36, "Chocolate Shake");
        foodPrices.put(36, 139.0);
        foodMenu.put(37, "Virgin Mojito");
        foodPrices.put(37, 110.0);
    }

    private void initializeRoomPrices() {
        roomTypePrices.put("Single Bed", 1200.0);
        roomTypePrices.put("Double Bed", 1800.0);
        roomTypePrices.put("Deluxe Room", 2400.0);
        roomTypePrices.put("Executive Suite", 4800.0);
        roomTypePrices.put("Presidential Suite", 7999.0);
    }

    public void bookRoom(int roomNumber, String guestName, String roomType) {
        if (roomNumber > 0 && roomNumber <= rooms.size()) {
            if (roomTypePrices.containsKey(roomType)) {
                rooms.get(roomNumber - 1).bookRoom(guestName, roomType, roomTypePrices.get(roomType));
            } else {
                System.out.println("Invalid room type.");
            }
        } else {
            System.out.println("Invalid room number.");
        }
    }

    public void freeRoom(int roomNumber) {
        if (roomNumber > 0 && roomNumber <= rooms.size()) {
            rooms.get(roomNumber - 1).freeRoom();
        } else {
            System.out.println("Invalid room number.");
        }
    }

    public void checkAvailability(int roomNumber) {
        if (roomNumber > 0 && roomNumber <= rooms.size()) {
            rooms.get(roomNumber - 1).displayDetails();
        } else {
            System.out.println("Invalid room number.");
        }
    }

    public void showAllRooms() {
        for (Room room : rooms) {
            room.displayDetails();
        }
    }

    public void displayFoodMenu() {
        System.out.println("Food Menu:");
        System.out.println("Starters:");
        for (int id = 1; id <=9; id++) {
            System.out.println(id + ". " + foodMenu.get(id) + " - Rs" + foodPrices.get(id));
        }

        System.out.println("Main Course:");
        for (int id = 10; id <=23; id++) {
            System.out.println(id + ". " + foodMenu.get(id) + " - Rs" + foodPrices.get(id));
        }

        System.out.println("Desserts:");
        for (int id = 24; id <=30; id++) {
            System.out.println(id + ". " + foodMenu.get(id) + " - Rs" + foodPrices.get(id));
        }

        
        System.out.println("Beverages:");
        for (int id = 31; id <=37; id++) {
            System.out.println(id + ". " + foodMenu.get(id) + " - Rs" + foodPrices.get(id));
        }
    }

    public void orderFood(int roomNumber, int[] foodIds) {
        if (roomNumber > 0 && roomNumber <= rooms.size()) {
            Room room = rooms.get(roomNumber - 1);
            for (int foodId : foodIds) {
                if (foodMenu.containsKey(foodId) && foodPrices.containsKey(foodId)) {
                    room.orderFood(foodMenu.get(foodId), foodPrices.get(foodId));
                } else {
                    System.out.println("Invalid food item: " + foodId);
                }
            }
        } else {
            System.out.println("Invalid room number.");
        }
    }

    public void calculateTotalFoodBill(int roomNumber) {
        if (roomNumber > 0 && roomNumber <= rooms.size()) {
            double totalBill = rooms.get(roomNumber - 1).getTotalBill();
            System.out.println("The total bill (including room charges and food with GST) for room " + roomNumber + " is: Rs" + totalBill);
        } else {
            System.out.println("Invalid room number.");
        }
    }
}

// Main class for user interaction
public class HotelManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel(10); // Create a hotel with 10 rooms

        while (true) {
            System.out.println("\n----------xxxxxxxxxxx----------Hotel Management System----------xxxxxxxxxx----------");
            System.out.println("1. Book a room");
            System.out.println("2. Vacant a room");
            System.out.println("3. Check room availability");
            System.out.println("4. Show all rooms");
            System.out.println("5. Order food");
            System.out.println("6. Display food menu");
            System.out.println("7. Total Bill Amount Including GST");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter room number to book: ");
                    int roomNumber = sc.nextInt();
                    sc.nextLine();  // Consume newline

                    System.out.print("Enter guest name: ");
                    String guestName = sc.nextLine();

                    System.out.println("Room Types:");
                    System.out.println("1. Single Bed - Rs-1200");
                    System.out.println("2. Double Bed - Rs-1800");
                    System.out.println("3. Deluxe Room - Rs-2400");
                    System.out.println("4. Executive Suite - Rs-4800");
                    System.out.println("5. Presidential Suite - Rs-7999");

                    System.out.print("Enter room type (1-5): ");
                    int roomTypeChoice = sc.nextInt();
                    String roomType = "";
                    switch (roomTypeChoice) {
                        case 1: roomType = "Single Bed"; break;
                        case 2: roomType = "Double Bed"; break;
                        case 3: roomType = "Deluxe Room"; break;
                        case 4: roomType = "Executive Suite"; break;
                        case 5: roomType = "Presidential Suite"; break;
                        default: System.out.println("Invalid room type."); continue;
                    }

                    hotel.bookRoom(roomNumber, guestName, roomType);
                    break;

                case 2:
                    System.out.print("Enter room number to Vacant: ");
                    int freeRoomNumber = sc.nextInt();
                    hotel.freeRoom(freeRoomNumber);
                    break;

                case 3:
                    System.out.print("Enter room number to check availability: ");
                    int checkRoomNumber = sc.nextInt();
                    hotel.checkAvailability(checkRoomNumber);
                    break;

                case 4:
                    hotel.showAllRooms();
                    break;

                case 5:
                    System.out.print("Enter room number to order food: ");
                    int roomForFood = sc.nextInt();
                    hotel.displayFoodMenu();
                    System.out.print("Enter the food item numbers to order (separate by space): ");
                    sc.nextLine();  // Consume newline
                    String[] foodIdsInput = sc.nextLine().split(" ");
                    int[] foodIds = new int[foodIdsInput.length];
                    for (int i = 0; i < foodIdsInput.length; i++) {
                        foodIds[i] = Integer.parseInt(foodIdsInput[i]);
                    }
                    hotel.orderFood(roomForFood, foodIds);
                    break;

                case 6:
                    hotel.displayFoodMenu();
                    break;

                case 7:
                    System.out.print("Enter room number to calculate food bill: ");
                    int roomForBill = sc.nextInt();
                    hotel.calculateTotalFoodBill(roomForBill);
                    break;

                case 8:
                    System.out.println("Thank You For Visiting Our Hotel . Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
