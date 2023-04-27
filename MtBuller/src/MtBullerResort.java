import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.*;

public class MtBullerResort {
    private FileOutputStream fos;
    private ObjectOutputStream oos;

    private final ArrayList<Accommodation> accommodations;
    private final ArrayList<Customer> customers;
    private final ArrayList<Package> packages;
    private final ArrayList<Service> services;
    private String totalCost;

    public MtBullerResort() {
        accommodations = new ArrayList<>();
        customers = new ArrayList<>();
        packages = new ArrayList<>();
        services = new ArrayList<>();
    }

    public void populateLists() {
        Accommodation[] arrOfAccs = {new Accommodation("Single", 100), new Accommodation("Double", 200), new Accommodation("Suite", 500), new Accommodation("Deluxe Single", 180),
                new Accommodation("Twin", 200), new Accommodation("Deluxe Double", 300), new Accommodation("Double", 200), new Accommodation("Super Suite", 800), new Accommodation("Penthouse", 2000), new Accommodation("Party Room", 2000)};
        Customer[] arrOfCustomers = {new Customer("Kendrick"),
                new Customer("Cole"), new Customer("Tyler")};
        Collections.addAll(accommodations, arrOfAccs);
        customers.addAll(Arrays.asList(arrOfCustomers));
        services.add(new Service("LiftPass", 26));
        services.add(new Service("BeginnerLesson", 25));
        services.add(new Service("IntermediateLesson", 20));
        services.add(new Service("ExpertLesson", 15));
    }


    public void run(LocalDate startDate) {
        boolean flag = true;
        Scanner input = new Scanner(System.in);
        while (flag) {
            System.out.println("""
                    Mt Buller Resort Application options
                    ------------------------
                    1: Display all Accommodations
                    2: Display available Accommodations
                    3: Add customer
                    4: List customers
                    5: Create Package
                    6: Add a lift pass to package
                    7: Add lesson fees to package
                    8: List packages
                    9: Save package to file
                    10: Read package from file
                    11: Quit
                    """);

            System.out.print("Choose an option: ");
            int option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    displayAllAccommodations();
                    break;
                case 2:
                    displayAvailableAccommodations();
                    break;
                case 3:
                    addCustomer();
                    break;
                case 4:
                    listCustomers();
                    break;
                case 5:
                    createPackage(startDate);
                    break;
                case 6:
                    listPackage();
                    Scanner input1 = new Scanner(System.in);
                    System.out.println("Package ID? ");
                    int packageId = input1.nextInt();
                    input1.nextLine();
                    addLiftPass(packageId);
                    break;
                case 7:
                    listPackage();
                    Scanner input2 = new Scanner(System.in);
                    System.out.println("Package ID? ");
                    packageId = input2.nextInt();
                    input2.nextLine();
                    addLesson(packageId);
                    break;
                case 8:
                    listPackage();
                    break;
                case 9:
                    savePackage();
                    break;
                case 10:
                    readPackage();
                    break;
                case 11:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        }

    }

    public void displayAllAccommodations() {
        for (Accommodation r : accommodations) {
            System.out.println(r);
        }
    }

    public void displayAvailableAccommodations() {
        for (Accommodation r : accommodations) {
            if (r.getAvailability())
                System.out.println(r);
        }
    }

    public void addCustomer() {
        Scanner input = new Scanner(System.in);
        System.out.print("Customer name? ");
        String name = input.nextLine();
        Customer c = new Customer(name);
        customers.add(c);
    }

    public void listCustomers() {
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    public void listPackage() {
        for (Package b : packages) {
            System.out.println("Package ID: " + b.getPackageId());
            System.out.println(b);
        }
    }

    public void createPackage(LocalDate startDate) {
        Scanner input = new Scanner(System.in);
        System.out.print("Customer ID? ");
        int custId = input.nextInt();
        input.nextLine();
        System.out.print("Duration? ");
        int dur = input.nextInt();
        input.nextLine();

        System.out.print("Date in format yyyy-mm-dd? ");
        String dateStr = input.nextLine();
        try {
            startDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please try again.");
            return;
        }

        Package aPackage = new Package(custId, startDate, dur); // add default value for services
        boolean match = false;
        while (true) {
            System.out.print("Accommodation type (1: Single, 2: Double, 3: Suite, 4: Deluxe Single, 5: Twin, 6: Deluxe Double, 7: Double, 8: Super Suite, 9: Penthouse, 10: Party Room)? ");
            int accType = input.nextInt();
            input.nextLine();
            String[] accTypes = {"Single", "Double", "Suite", "Deluxe Single", "Twin", "Deluxe Double", "Double", "Super Suite", "Penthouse", "Party Room"};
            if (accType < 1 || accType > accTypes.length) {
                System.out.println("Invalid accommodation type.");
                return;
            }
            String selectedAccType = accTypes[accType - 1];
            for (Accommodation r : accommodations) {
                if (r.getType().equalsIgnoreCase(selectedAccType) && r.getAvailability()) {
                    r.setAvailability(false);
                    aPackage.setAccNo(r.getAccNo());
                    match = true;
                    break;
                }
            }
            if (match)
                break;
            System.out.println("Did not find a match.");

        }
        packages.add(aPackage);
        System.out.println("Package created successfully. Package ID: " + aPackage.getPackageId());
    }

    public void addLiftPass(int packageId) {
        Package aPackage = searchPackageByPackId(packageId);
        aPackage.setLiftPassCostPerDay(26);
        System.out.println(aPackage);
        System.out.println("Add Lift Pass?: Y/N");
        Scanner input = new Scanner(System.in);
        String liftPass = input.nextLine();
        if (liftPass.equalsIgnoreCase("y")) {
            System.out.println("Enter number of days: ");
            int days = input.nextInt();
            double liftPassCost = aPackage.getLiftPassCostPerDay() * days;
            if (days > 5) {
                liftPassCost *= 0.9;
            }
            aPackage.setLiftPassCost(liftPassCost);
            System.out.println("Lift Pass added. Cost: " + liftPassCost);
            System.out.println("Saved lift pass cost: " + aPackage.getLiftPassCost());
        } else {
            aPackage.setLiftPassCost(0);
        }
    }


    public void addLesson(int packageId) {
        Package aPackage = searchPackageByPackId(packageId);
        System.out.println(aPackage);
        System.out.println("Add Lesson?: Y/N");
        Scanner input = new Scanner(System.in);
        String addLesson = input.nextLine();
        if (addLesson.equalsIgnoreCase("y")) {
            System.out.println("Enter lesson level (1: Beginner, 2: Intermediate, 3: Expert): ");
            int level = input.nextInt();
            System.out.println("Enter number of lessons: ");
            int numLessons = input.nextInt();
            double lessonCost = 0;
            switch (level) {
                case 1:
                    lessonCost = 25 * numLessons;
                    break;
                case 2:
                    lessonCost = 20 * numLessons;
                    break;
                case 3:
                    lessonCost = 15 * numLessons;
                    break;
                default:
                    System.out.println("Invalid lesson level.");
                    return;
            }
            aPackage.setLessonCost(lessonCost);
            System.out.println("Lesson added. Cost: " + lessonCost);
        } else {

            aPackage.setLessonCost(0);
        }
    }


    public Package searchPackageByPackId(int packageId) {
        for (Package b : packages) {
            if (b.getPackageId() == packageId)
                return b;
        }
        return null;
    }

    public Accommodation searchAccByAccNo(int accNo) {
        for (Accommodation r : accommodations) {
            if (r.getAccNo() == accNo)
                return r;
        }
        return null;
    }

    public void savePackage() {
        System.out.println("Saving packages...");
        try {
            FileOutputStream fos = new FileOutputStream("package.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Package b : packages) {
                oos.writeObject(b);
            }
            fos.close();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void readPackage() {
        packages.clear();
        try {
            FileInputStream fis = new FileInputStream("package.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Scanner input = new Scanner(System.in);
            System.out.println("Enter package ID: ");
            int packageId = input.nextInt();

            while (true) {
                try {
                    Object object = ois.readObject();
                    Package b = (Package) object;

                    if (b.getPackageId() == packageId) {
                        int accNo = b.getAccNo();
                        Accommodation r = searchAccByAccNo(accNo);
                        r.setAvailability(false);

                        packages.add(b);
                        System.out.println("Customer ID: " + b.getCustId());
                        System.out.println("Duration: " + b.getDuration());
                        System.out.println("Date: " + b.getDate());
                        System.out.println("Accommodation Type: " + r.getType());
                        System.out.println("Accommodation Price: " + r.getPricePerDay());
                        double totalCost = r.getPricePerDay();
                        double accommodationFee = r.getPricePerDay() * b.getDuration();
                        System.out.println("Accommodation Fee: " + accommodationFee);
                        totalCost += accommodationFee;
                        if (b.getLiftPassCost() > 0) {
                            System.out.println("Lift Pass Cost: " + b.getLiftPassCost());
                            totalCost += b.getLiftPassCost();
                        }

                        if (b.getLessonCost() > 0) {
                            System.out.println("Lesson Cost: " + b.getLessonCost());
                            totalCost += b.getLessonCost();
                        }
                            System.out.println("Total cost: " + totalCost);
                    }

                } catch (EOFException eof) {
                    fis.close();
                    ois.close();
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }}