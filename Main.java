import org.jbibtex.*;

import java.io.*;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int userChoice;

    public static void main(String[] args) throws IOException, ParseException {
        File file = new File("C:\\Users\\oguzs\\IdeaProjects\\SE302_Practice\\src\\first.bib");

        BufferedReader reader = new BufferedReader(
                new FileReader(file)
        );

        String[] keys = {BibTeXEntry.KEY_KEY.getValue(), BibTeXEntry.KEY_AUTHOR.getValue(), BibTeXEntry.KEY_TITLE.getValue(), BibTeXEntry.KEY_JOURNAL.getValue(), BibTeXEntry.KEY_YEAR.getValue()};
        org.jbibtex.BibTeXDatabase bibTeXDatabase;

        try {
            org.jbibtex.BibTeXParser bibTeXParser = new org.jbibtex.BibTeXParser();
            bibTeXDatabase = bibTeXParser.parse(reader);
            Map<Key, BibTeXEntry> myMap = bibTeXDatabase.getEntries();

            Collection<BibTeXEntry> entries = bibTeXDatabase.getEntries().values();
            for (BibTeXEntry entry: entries) {

                // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ IMPORTANT PART @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
                Map<Key, Value> allFields = entry.getFields();
                allFields.forEach((key, value) -> System.out.println(key + " = " + value.toUserString()));


//            System.out.println(entry.getKey());
//            System.out.println(entry.getType().getValue() + "   " + BibTeXEntry.TYPE_ARTICLE.getValue());
//            if (entry.getType().getValue().toLowerCase().equals(BibTeXEntry.TYPE_ARTICLE.getValue()) || entry.getKey().getValue().equals(keySearch)) {
//                for (int i = 0; i < keys.length; i++) {
//                    Value value = entry.getField(new Key(keys[i]));
//                    if (value != null)
//                        System.out.println(value.toUserString());
//                }
//            }
                System.out.println();
            }
            reader.close();

        } catch (org.jbibtex.ParseException e) {
            System.out.println("The BibTex file format is not correct. Please check your file.");
        }

//        Key key = new Key("title");

        startMenu();

    }

    public static void startMenu() {
        System.out.println("Welcome to BibTex Manager Prototype");
        System.out.println("Please select what option you will be performing...");
        System.out.println("1. Creating a bib file.");
        System.out.println("2. Reading a bib file");
        System.out.println("3. Updating a bib file.");
        System.out.println("4. Deleting a bib file.");

        userInput();

        switch (userChoice) {
            case 1:
                createMenu();
                break;

//            case 2:
//                readMenu();
//                break;
//
//            case 3:
//                updateMenu();
//                break;
//
//            case 4:
//                deleteMenu();
//                break;

            default:
                System.out.println("\n\n Please enter a number in range!\n");
                startMenu();
                break;
        }
    }

    public static void createMenu() {
        System.out.println("Please select what your entry type will be...");
        System.out.println("1. Article");
        System.out.println("2. Book");
        System.out.println("3. Booklet");
        System.out.println("4. Conference");
        System.out.println("5. In Book");
        System.out.println("6. In Collection");
        System.out.println("7. In Proceedings");
        System.out.println("8. Manual");
        System.out.println("9. Masters Thesis");
        System.out.println("10. Misc");
        System.out.println("11. PhD Thesis");
        System.out.println("12. Proceedings");
        System.out.println("13. Tech Report");
        System.out.println("14. Unpublished");

        userInput();

        String author, title, journal, year, publisher, bookTitle;

        switch (userChoice) {
            case 1:
                System.out.print("Author: ");
                author = scanner.nextLine();
                System.out.println();

                System.out.print("Title: ");
                title = scanner.nextLine();
                System.out.println();

                System.out.print("Journal: ");
                journal = scanner.nextLine();
                System.out.println();

                System.out.print("Year: ");
                year = scanner.nextLine();
                System.out.println();

                createArticleBib(author, title, journal, year);
                break;

            case 2:
                System.out.print("Author (Editor): ");
                author = scanner.nextLine();
                System.out.println();

                System.out.print("Title: ");
                title = scanner.nextLine();
                System.out.println();

                System.out.print("Publisher: ");
                publisher = scanner.nextLine();
                System.out.println();

                System.out.print("Year: ");
                year = scanner.nextLine();
                System.out.println();

                createBookBib(author, title, publisher, year);
                break;

            case 3:
                System.out.print("Title: ");
                title = scanner.nextLine();
                System.out.println();

                createBookletBib(title);
                break;

            case 4:
                System.out.print("Author: ");
                author = scanner.nextLine();
                System.out.println();

                System.out.print("Title: ");
                title = scanner.nextLine();
                System.out.println();

                System.out.print("Book Title: ");
                bookTitle = scanner.nextLine();
                System.out.println();


                System.out.print("Year: ");
                year = scanner.nextLine();
                System.out.println();

                createConferenceBib(author, title, bookTitle, year);
                break;

            default:
                System.out.println("Only the first 4 options work for now.");
                createMenu();
                break;
        }
    }

    //String author, String title, String journal, String year
    public static void createArticleBib(String author, String title, String journal, String year) {
        try {
            File temporaryFile = new File("C:\\Users\\oguzs\\IdeaProjects\\SE302_Practice\\src\\new.bib");
            File file2 = new File("C:\\Users\\oguzs\\IdeaProjects\\SE302_Practice\\src\\tobewritten.bib");
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file2)
            );

            org.jbibtex.BibTeXDatabase database = new BibTeXDatabase();
            org.jbibtex.BibTeXFormatter formatter = new org.jbibtex.BibTeXFormatter();

            Key type = BibTeXEntry.TYPE_ARTICLE;
            Key key = BibTeXEntry.KEY_KEY;

            BibTeXEntry newEntry = new BibTeXEntry(type, key);

            newEntry.addField(BibTeXEntry.KEY_AUTHOR, new StringValue(author, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_TITLE, new StringValue(title, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_JOURNAL, new StringValue(journal, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_YEAR, new StringValue(year, StringValue.Style.BRACED));

            database.addObject(newEntry);

            formatter.format(database, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createBookBib(String author, String title, String publisher, String year) {
        try {
            File temporaryFile = new File("C:\\Users\\oguzs\\IdeaProjects\\SE302_Practice\\src\\new.bib");
            File file2 = new File("C:\\Users\\oguzs\\IdeaProjects\\SE302_Practice\\src\\tobewritten.bib");
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file2)
            );

            org.jbibtex.BibTeXDatabase database = new BibTeXDatabase();
            org.jbibtex.BibTeXFormatter formatter = new org.jbibtex.BibTeXFormatter();

            Key type = BibTeXEntry.TYPE_BOOK;
            Key key = BibTeXEntry.KEY_KEY;

            BibTeXEntry newEntry = new BibTeXEntry(type, key);

            newEntry.addField(BibTeXEntry.KEY_AUTHOR, new StringValue(author, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_TITLE, new StringValue(title, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_PUBLISHER, new StringValue(publisher, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_YEAR, new StringValue(year, StringValue.Style.BRACED));

            database.addObject(newEntry);

            formatter.format(database, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createBookletBib(String title) {
        try {
            File temporaryFile = new File("C:\\Users\\oguzs\\IdeaProjects\\SE302_Practice\\src\\new.bib");
            File file2 = new File("C:\\Users\\oguzs\\IdeaProjects\\SE302_Practice\\src\\tobewritten.bib");
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file2)
            );

            org.jbibtex.BibTeXDatabase database = new BibTeXDatabase();
            org.jbibtex.BibTeXFormatter formatter = new org.jbibtex.BibTeXFormatter();

            Key type = BibTeXEntry.TYPE_BOOKLET;
            Key key = BibTeXEntry.KEY_KEY;

            BibTeXEntry newEntry = new BibTeXEntry(type, key);

            newEntry.addField(BibTeXEntry.KEY_TITLE, new StringValue(title, StringValue.Style.BRACED));

            database.addObject(newEntry);

            formatter.format(database, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createConferenceBib(String author, String title, String bookTitle, String year) {
        try {
            File temporaryFile = new File("C:\\Users\\oguzs\\IdeaProjects\\SE302_Practice\\src\\new.bib");
            File file2 = new File("C:\\Users\\oguzs\\IdeaProjects\\SE302_Practice\\src\\tobewritten.bib");
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(file2)
            );

            org.jbibtex.BibTeXDatabase database = new BibTeXDatabase();
            org.jbibtex.BibTeXFormatter formatter = new org.jbibtex.BibTeXFormatter();

            Key type = BibTeXEntry.TYPE_CONFERENCE;
            Key key = BibTeXEntry.KEY_KEY;

            BibTeXEntry newEntry = new BibTeXEntry(type, key);

            newEntry.addField(BibTeXEntry.KEY_AUTHOR, new StringValue(author, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_TITLE, new StringValue(title, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_BOOKTITLE, new StringValue(bookTitle, StringValue.Style.BRACED));
            newEntry.addField(BibTeXEntry.KEY_YEAR, new StringValue(year, StringValue.Style.BRACED));

            database.addObject(newEntry);

            formatter.format(database, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void userInput() {
        while(true) {
            try {
                System.out.print("\nYour choice: ");
                userChoice = scanner.nextInt();
                scanner.nextLine();
                System.out.println();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Your input must be an integer within the range!");
            }
        }
    }
}
