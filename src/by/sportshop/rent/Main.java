package by.sportshop.rent;

import by.sportshop.rent.entity.RentUnit;
import by.sportshop.rent.entity.Shop;
import by.sportshop.rent.entity.SportEquipment;
import by.sportshop.rent.interfaces.InventoryProvider;
import by.sportshop.rent.interfaces.RentStore;
import by.sportshop.rent.utils.CSVInventoryProvider;
import by.sportshop.rent.utils.SerializationRentStore;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import static by.sportshop.rent.constants.FilesConstants.INIT_FILE_NAME;
import static by.sportshop.rent.constants.FilesConstants.RENT_FILE_NAME;

/**
 * Created by Olya on 26.03.2017.
 */
public class Main {

    private static void getAvailableEquipment(Shop shop) {
        System.out.println("List of available sport equipments: ");
        for (Map.Entry<SportEquipment, Integer> entry : shop.getAvailableGoods().entrySet()) {
            SportEquipment key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key.getCategory() + " " + key.getTitle() + " " + key.getPrice() + " " + value);
        }
    }

    private static void showCart(RentUnit cart) {
        System.out.println("Cart:");
        for (int i = 0; i < cart.getCount(); i++) {
            System.out.println(cart.getUnits()[i].getCategory() + " " +
                    cart.getUnits()[i].getTitle() + " " +
                    cart.getUnits()[i].getPrice());
        }
    }

    private static void getListAllRentedEquipments(Shop shop) {
        System.out.println("List of rented out sport equipments: ");
        for (RentUnit unit : shop.getRentUnits()) {
            for (int i = 0; i < unit.getCount(); i++) {
                System.out.println(unit.getUnits()[i].getCategory() + " " +
                        unit.getUnits()[i].getTitle() + " " +
                        unit.getUnits()[i].getPrice());
            }
        }
    }

    private static void addProductToCart(RentUnit cart, Shop shop) {
        if (cart.getCount() == 3) {
            System.out.println("Sorry, You can't rent more than three units");
        } else {
            System.out.println("Please, write sport equipment name: ");
            Scanner in2 = new Scanner(System.in);
            String productName = in2.nextLine();
            Boolean flag = false;
            for (Map.Entry<SportEquipment, Integer> entry : shop.getAvailableGoods().entrySet()) {
                SportEquipment key = entry.getKey();

                if (productName.equals(key.getTitle())) {
                    cart.addItem(entry.getKey());
                    flag = true;
                }
            }
            if (!flag) {
                System.out.println("Error sport equipment name");
            }
        }
    }

    public static void main(String[] args) {
        try {
            RentUnit cart = new RentUnit();
            InventoryProvider provider = new CSVInventoryProvider(new File(INIT_FILE_NAME));
            RentStore rentStore = new SerializationRentStore(RENT_FILE_NAME);
            Shop shop = new Shop(provider, rentStore);

            while (true) {

                System.out.println("1 - List of available sport equipments \n" +
                        "2 - List of all rented sport equipments \n" +
                        "3 - Show cart \n" +
                        "4 - Add a product to cart\n" +
                        "5 - Clear cart\n" +
                        "6 - Checkout\n" + // TODO: call rent on shop. Catch InvalidRentUnit and print a message
                        "0 - Exit the terminal"); // TODO: save shop state

                Scanner in = new Scanner(System.in);
                try {
                    System.out.println("Choose option: ");
                    Integer op = in.nextInt();
                    switch (op) {
                        case 1:
                            getAvailableEquipment(shop);
                            break;
                        case 2:
                            getListAllRentedEquipments(shop);
                            break;
                        case 3:
                            showCart(cart);
                            break;
                        case 4:
                            getAvailableEquipment(shop);
                            addProductToCart(cart, shop);
                            break;
                        case 5:
                            System.out.println("Cart cleared");
                            cart.clearCart();
                            break;
                        case 6:
                            if (shop.rent(cart)) {
                                System.out.println("Success");
                            } else {
                                System.out.println("Error, try again");
                            }
                            cart.clearCart();
                            break;
                        case 0:
                            System.exit(0);
                        default:
                            System.out.println("Data not valid; Try again!");
                            break;
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Data not valid; Try again!");
                }

            }

        } catch (IOException e) {
            System.out.println("Incorrect file name; Try again!");
        }

    }
}
