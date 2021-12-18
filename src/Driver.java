import service.PropertyListingService;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        PropertyListingService propertyListingService = new PropertyListingService();

        Scanner sc = new Scanner(System.in);

        while(true) {
            String command = sc.nextLine();
            String[] cargs = command.split(" ");

            try {
                switch (cargs[0]) {
                    case "REGISTER": {
                        switch (cargs[1]) {
                            case "USER":
                                propertyListingService.registerUser(cargs[2]);
                                break;
                            case "PROPERTY":
                                propertyListingService.registerProperty(cargs[2],cargs[3],cargs[4],cargs[5],cargs[6], cargs[7], cargs[8]);
                                break;
                            default:
                                System.out.println("Invalid command");
                                break;
                        }
                        break;
                    }
                    case "SEARCH":
                        propertyListingService.searchProperty(cargs[1], cargs[2],cargs[3],cargs[4]);
                        break;
                    case "SHORTLIST":
                        propertyListingService.shortListProperty(cargs[1],cargs[2]);
                        break;
                    case "VIEW_SHORTLISTED":
                        propertyListingService.viewShortListedProperty(cargs[1]);
                        break;
                    case "MARK_SOLD":
                        propertyListingService.markPropertySold(cargs[1]);
                        break;
                    default:
                        System.out.println("Invalid command");
                        break;
                }
            }
            catch (Exception e) {
                System.out.println("invalid input");
            }
        }

    }
}
