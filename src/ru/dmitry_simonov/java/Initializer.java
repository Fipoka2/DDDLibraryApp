
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import DO.Book;
import DO.Publisher;
import DO.BookRequest;
import DO.LibraryCard;
import DO.User;
import repository.PublisherRepository;
import repository.BookRepository;
import repository.BookRequestRepository;
import repository.LibraryCardRepository;
import repository.UserRepository;
import service.LibraryService;
import VO.PublisherValueObject;
import VO.BookRequestValueObject;
import VO.BookValueObject;
import VO.LibraryCardValueObject;
import VO.UserValueObject;

public class Initializer
{
    private static ObjectMapper mapper = new ObjectMapper();
    private static LibraryService service = LibraryService.getInstance();
    private static Logger logger = LoggerFactory.getLogger(Initializer.class);

    public static void main(String[] args) throws IOException
    {

        logger.info("Application has started.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            System.out.println("----------------------------------------------------");
            System.out.println("1. Add entity.");
            System.out.println("2. Delete entity.");
            System.out.println("3. Update entity.");
            System.out.println("4. Show entity.");
            System.out.println("5. Show all entities.");
            System.out.println("6. Exit.");
            System.out.println("----------------------------------------------------");
            int choice;
            try
            {
                choice = Integer.parseInt(reader.readLine());
                switch (choice)
                {
                    case 1:
                        addExistedEntity();
                        break;
                    case 2:
                        deleteEntity();
                        break;
                    case 3:
                        updateEntity();
                        break;
                    case 4:
                        showEntity();
                        break;
                    case 5:
                        showEntities();
                        break;
                    case 6:
                        logger.info("Application has stopped.");
                        return;
                    default:
                        logger.warn("Incorrect choice");
                }
            } catch (NumberFormatException | ParseException | IOException ex)
            {
                logger.error(ex.getMessage());
            }
        }
    }

    public static void showEntities() throws IOException, ParseException, NumberFormatException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1. Show books.");
        System.out.println("2. Show publishers.");
        System.out.println("3. Show bookRequests.");
        System.out.println("4. Show libraryCards.");
        System.out.println("5. Show users.");
        System.out.println("6. Main menu.");
        System.out.println("----------------------------------------------------");
        int choice = Integer.parseInt(reader.readLine());
        switch(choice){
            case 1:
                for(Book book : service.getAllBooks()){
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(book));
                }
                break;
            case 2:
                for(Publisher publisher : service.getAllPublishers()){
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(publisher));
                }
                break;
            case 3:
                for(BookRequest request : service.getAllBookRequests()){
                    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(request));
                }
                break;
            case 4:
                for(LibraryCard card : service.getAllLibraryCards()){
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(card));
                }
                break;
            case 5:
                for(User user : service.getAllUsers()){
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));
                }
                break;
            default:
                logger.warn("Incorrect choice.");
        }
    }

    public static void showEntity() throws IOException, ParseException, NumberFormatException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter entity ID: ");
        Long id = Long.parseLong(reader.readLine());
        System.out.println("1. Show book.");
        System.out.println("2. Show publisher.");
        System.out.println("3. Show user.");
        System.out.println("4. Show bookRequest.");
        System.out.println("5. Show libraryCard.");
        System.out.println("6. Main menu.");
        System.out.println("----------------------------------------------------");
        int choice2 = Integer.parseInt(reader.readLine());
        switch(choice2){
            case 1 :
                Book book = service.getBookById(id);
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(book));
                System.out.println();
                break;
            case 2 :
                Publisher publisher = service.getPublisherById(id);
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(publisher));
                break;
            case 3 :
                User user = service.getUserById(id);
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user));
                break;
            case 4 :
                BookRequest bookRequest = service.getBookRequestById(id);
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bookRequest));
                break;
            case 5 :
                LibraryCard libraryCard = service.getLibraryCardById(id);
                System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(libraryCard));
                break;
            case 6 :
                break;
            default:
                logger.warn("Incorrect choice.");
        }
    }

    public static void deleteEntity() throws IOException, NumberFormatException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1. Delete book.");
        System.out.println("2. Delete publisher.");
        System.out.println("3. Delete user.");
        System.out.println("4. Delete bookRequest.");
        System.out.println("5. Delete libraryCard.");
        System.out.println("6. Main menu.");
        System.out.println("----------------------------------------------------");
        int choice = Integer.parseInt(reader.readLine());
        Long id;
        switch(choice){
            case 1 :
                System.out.print("Enter book ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteBookById(id);
                logger.info("Book with id = " + id + " was deleted.");
                break;
            case 2 :
                System.out.print("Enter publisher ID: ");
                id = Long.parseLong(reader.readLine());
                service.deletePublisherById(id);
                logger.info("Publisher with id = " + id + " was deleted.");
                break;
            case 3 :
                System.out.print("Enter user ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteUserById(id);
                logger.info("User with id = " + id + " was deleted.");
                break;
            case 4 :
                System.out.print("Enter bookRequest ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteBookRequestById(id);
                logger.info("BookRequestValueObject with id = " + id + " was deleted.");
                break;
            case 5 :
                System.out.print("Enter libraryCard ID: ");
                id = Long.parseLong(reader.readLine());
                service.deleteLibraryCardById(id);
                logger.info("LibraryCardValueObject with id = " + id + " was deleted.");
                break;
            case 6 :
                break;
            default:
                logger.warn("Incorrect choice.");
        }
    }

    public static void updateEntity() throws IOException, ParseException, NumberFormatException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter file path: ");
        String filePath = reader.readLine();
        System.out.println("1. Update book.");
        System.out.println("2. Update publisher.");
        System.out.println("3. Update user.");
        System.out.println("4. Update bookRequest.");
        System.out.println("5. Update libraryCard.");
        System.out.println("6. Main menu.");
        System.out.println("----------------------------------------------------");
        int choice = Integer.parseInt(reader.readLine());
        switch(choice) {
            case 1:
                BookValueObject book = mapper.readValue(new FileInputStream(filePath), BookValueObject.class);
                System.out.println(book);

                do {
                    System.out.println("1. Edit name.");
                    System.out.println("2. Edit author.");
                    System.out.println("3. Set info.");
                    System.out.println("4. Main menu.");
                    choice = Integer.parseInt(reader.readLine());
                    switch (choice) {
                        case 1:
                            System.out.print("New name: ");
                            book.name = reader.readLine();
                            service.updateBook(book);
                            logger.info("Book with ID = " + book.id + " was updated.");
                            break;
                        case 2:
                            System.out.print("New author: ");
                            book.author = reader.readLine();
                            service.updateBook(book);
                            logger.info("Book with ID = " + book.id + " was updated.");
                            break;
                        case 3:
                            System.out.print("New info ID: ");
                            book.publisherName= Long.parseLong(reader.readLine());
                            service.updateBook(book);
                            logger.info("Book with ID = " + book.id + " was updated.");
                            break;
                        case 4:
                            break;
                        default:
                            logger.warn("Incorrect choice.");
                    }
                } while (choice != 4);

                mapper.writeValue(new FileOutputStream(filePath), book);
                logger.info("File with book ID = " + book.id + " was rewrote.");
                break;
            case 2:
                PublisherValueObject publisher = mapper.readValue(new FileInputStream(filePath), PublisherValueObject.class);
                System.out.println(publisher);

                do {
                    System.out.println("1. Edit publisher name.");
                    System.out.println("2. Edit year.");
                    System.out.println("3. Exit");
                    choice = Integer.parseInt(reader.readLine());
                    switch (choice) {
                        case 1:
                            System.out.print("New publisher name : ");
                            publisher.publisherName = reader.readLine();
                            service.updatePublisher(publisher);
                            logger.info("Publisher with ID = " + publisher.id + " was updated.");
                            break;

                        case 2:
                            System.out.print("New year: ");
                            publisher.year = Integer.parseInt(reader.readLine());
                            service.updatePublisher(publisher);
                            logger.info("Publisher with ID = " + publisher.id + " was updated.");
                            break;
                        case 3:
                            break;
                        default:
                            logger.warn("Incorrect choice.");
                    }
                } while (choice != 3);

                mapper.writeValue(new FileOutputStream(filePath), publisher);
                logger.info("File with publisher ID = " + publisher.id + " was rewrote.");
                break;
            case 3:
                UserValueObject user = mapper.readValue(new FileInputStream(filePath), UserValueObject.class);
                System.out.println(user);

                do {
                    System.out.println("1. Edit fio.");
                    System.out.println("2. Edit phone number.");
                    System.out.println("3. Edit address.");
                    System.out.println("4. Main menu.");
                    choice = Integer.parseInt(reader.readLine());
                    switch (choice) {
                        case 1:
                            System.out.print("New fio: ");
                            user.fio = reader.readLine();
                            service.updateUser(user);
                            logger.info("User with ID = " + user.id + " was updated.");
                            break;
                        case 2:
                            System.out.print("new phone number ");
                            user.phoneNumber = reader.readLine();
                            service.updateUser(user);
                            logger.info("User with ID = " + user.id + " was updated.");
                            break;
                        case 3:
                            System.out.print("New address: ");
                            user.address = reader.readLine();
                            service.updateUser(user);
                            logger.info("User with ID = " + user.id + " was updated.");
                            break;
                        case 4:
                            break;
                        default:
                            logger.warn("Incorrect choice.");
                    }
                } while (choice != 4);

                mapper.writeValue(new FileOutputStream(filePath), user);
                logger.info("File with user ID = " + user.id + " was rewrote.");
                break;
            case 4:
                BookRequestValueObject bookRequest = mapper.readValue(new FileInputStream(filePath), BookRequestValueObject.class);
                System.out.println(bookRequest);

                do {
                    System.out.println("1. Edit issue date.");
                    System.out.println("2. Edit book.");
                    System.out.println("3. Main menu.");
                    choice = Integer.parseInt(reader.readLine());
                    switch (choice) {
                        case 1:
                            System.out.print("Enter new issue date: ");
                            String newDate = reader.readLine();
                            bookRequest.issueDate = service.getDateFormat().parse(newDate);
                            service.updateBookRequest(bookRequest);
                            logger.info("BookRequest with ID = " + bookRequest.id + " was updated.");
                            break;
                        case 2:
                            System.out.print("Enter new book id: ");
                            bookRequest.book = Long.parseLong(reader.readLine());
                            service.updateBookRequest(bookRequest);
                            logger.info("BookRequest with ID = " + bookRequest.id + " was updated.");
                            break;
                        case 3:
                            break;
                        default:
                            logger.warn("Incorrect choice.");
                    }
                } while(choice != 3);

                mapper.writeValue(new FileOutputStream(filePath), bookRequest);
                logger.info("File with bookRequest ID = " + bookRequest.id + " was rewrote.");
                break;
            case 5:
                LibraryCardValueObject libraryCard = mapper.readValue(new FileInputStream(filePath), LibraryCardValueObject.class);
                System.out.println(libraryCard);

                do{
                    System.out.println("1. Edit book request collection : ");
                    System.out.println("2. Edit user : ");
                    System.out.println("3. Main menu.");
                    choice = Integer.parseInt(reader.readLine());
                    switch(choice){
                        case 1:
                            System.out.print("Enter new book request collection : ");
                            Collection<Long> collection = new ArrayList<>();
                            int myChoice;
                            do{
                                System.out.println(" 1: new book request id");
                                System.out.println(" 2: exit");
                                myChoice = Integer.parseInt(reader.readLine());
                                switch(myChoice)
                                    {
                                    case 1:
                                        {
                                            long id = Long.parseLong(reader.readLine());
                                            collection.add(id);
                                        }
                                    default:break;
                                    }
                            } while (myChoice == 2);
                            libraryCard.bookRequests = collection;
                            logger.info("LibraryCard with ID = " + libraryCard.id + " was updated.");
                            break;
                        case 2:
                            System.out.print("Enter new user id : ");
                            libraryCard.user= Long.parseLong(reader.readLine());
                            service.updateLibraryCard(libraryCard);
                            logger.info("LibraryCard with ID = " + libraryCard.id + " was updated.");
                            break;
                        case 3:
                            break;
                        default:
                            logger.warn("Incorrect choice.");
                    }
                } while (choice != 3);

                mapper.writeValue(new FileOutputStream(filePath), libraryCard);
                logger.info("File with libraryCard ID = " + libraryCard.id + " was rewrote.");
                break;
            case 6:
                break;
        }
    }
    private static void addExistedEntity() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter file path: ");
        String filePath = reader.readLine();
        System.out.println("Enter type of entity (1-Book, 2-Publisher, 3-User, 4-BookRequest, 5-LibraryCard):");
        int choice = Integer.parseInt(reader.readLine());
        switch (choice) {
            case 1:
                BookValueObject bookValueObject =
                        mapper.readValue(new FileInputStream(filePath), BookValueObject.class);
                BookRepository bookRepository = service.getBookRepository();
                if (bookRepository.contains(bookValueObject.id)){
                    logger.warn("Book with id = " + bookValueObject.id + " already exists.");
                    return;
                }
                bookRepository.add(bookValueObject);
                bookRepository.incMaxId();
                logger.info("Book with id = " + bookValueObject.id + " was added to repository.");
                break;
            case 2:
                PublisherValueObject publisherValueObject =
                        mapper.readValue(new FileInputStream(filePath), PublisherValueObject.class);
                PublisherRepository publisherRepository = service.getPublisherRepository();
                if (publisherRepository.contains(publisherValueObject.id)){
                    logger.warn("Publisher with id = " + publisherValueObject.id + " already exists.");
                    return;
                }
                publisherRepository.add(publisherValueObject);
                publisherRepository.incMaxId();
                logger.info("Publisher with id = " + publisherValueObject.id + " was added to repository.");
                break;
            case 3:
                UserValueObject userValueObject =
                        mapper.readValue(new FileInputStream(filePath), UserValueObject.class);
                UserRepository userRepository = service.getUserRepository();
                if (userRepository.contains(userValueObject.id)){
                    logger.warn("Publisher with id = " + userValueObject.id + " already exists.");
                    return;
                }
                userRepository.add(userValueObject);
                userRepository.incMaxId();
                logger.info("User with id = " + userValueObject.id + " was added to repository.");
                break;
            case 4:
                BookRequestValueObject bookRequestValueObject =
                        mapper.readValue(new FileInputStream(filePath), BookRequestValueObject.class);
                BookRequestRepository bookRequestRepository = service.getBookRequestRepository();
                if (bookRequestRepository.contains(bookRequestValueObject.id)){
                    logger.warn("BookRequest with id = " + bookRequestValueObject.id + " already exists.");
                    return;
                }
                bookRequestRepository.add(bookRequestValueObject);
                bookRequestRepository.incMaxId();
                logger.info("BookRequest with id = " + bookRequestValueObject.id + " was added to repository.");
                break;
            case 5:
                LibraryCardValueObject libraryCardValueObject =
                        mapper.readValue(new FileInputStream(filePath), LibraryCardValueObject.class);
                LibraryCardRepository libraryCardRepository = service.getLibraryCardRepository();
                if (libraryCardRepository.contains(libraryCardValueObject.id)){
                    logger.warn("LibraryCard with id = " + libraryCardValueObject.id + " already exists.");
                    return;
                }
                libraryCardRepository.add(libraryCardValueObject);
                libraryCardRepository.incMaxId();
                logger.info("LibraryCard with id = " + libraryCardValueObject.id + " was added to repository.");
                break;
            default:
                logger.warn("Incorrect choice.");
        }



    }
}