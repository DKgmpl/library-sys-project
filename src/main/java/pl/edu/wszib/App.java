package pl.edu.wszib;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        boolean run = true;
        while(run) {
            System.out.println("1. List all books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("0. Exit");

            Scanner scanner = new Scanner(System.in);
            String choose = scanner.nextLine();
            System.out.println(choose);


            switch (choose) {
                case "1":
                    System.out.println("Listowanie książek");
                    break;
                case "2":
                    System.out.println("Wypożyczanie książek");
                    break;
                case "3":
                    System.out.println("Zwracanie książek");
                    break;
                case "0":
                    System.out.println("Wyjście");
                    run = false;
                default:
                    System.out.println("Error!");
                    break;
            }
        }
    }
}
