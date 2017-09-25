package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import DO.Book;
import DO.Publisher;
import DO.BookRequest;
import DO.LibraryCard;
import DO.User;
import loader.PublisherLoader;
import loader.BookLoader;
import loader.BookRequestLoader;
import loader.LibraryCardLoader;
import loader.UserLoader;
import repository.PublisherRepository;
import repository.BookRepository;
import repository.BookRequestRepository;
import repository.LibraryCardRepository;
import repository.UserRepository;
import translator.PublisherTranslator;
import translator.BookRequestTranslator;
import translator.BookTranslator;
import translator.LibraryCardTranslator;
import translator.UserTranslator;
import VO.PublisherValueObject;
import VO.BookRequestValueObject;
import VO.BookValueObject;
import VO.LibraryCardValueObject;
import VO.UserValueObject;

public class LibraryService {

    private static LibraryService instance = new LibraryService();

    private LibraryService(){}

    public static LibraryService getInstance(){
        if (instance == null)
            instance = new LibraryService();
        return instance;
    }




    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    //private HashMap<Long, Timer> timers = new HashMap<>();
    private static Logger logger = LoggerFactory.getLogger(LibraryService.class);

    public DateFormat getDateFormat() {
        return dateFormat;
    }
// Repositories

    private UserRepository userRepository = new UserRepository();
    private BookRepository bookRepository = new BookRepository();
    private PublisherRepository publisherRepository = new PublisherRepository();
    private BookRequestRepository bookRequestRepository = new BookRequestRepository();
    private LibraryCardRepository libraryCardRepository = new LibraryCardRepository();

// Translators

    private UserTranslator userTranslator = new UserTranslator();
    private BookTranslator bookTranslator = new BookTranslator();
    private BookRequestTranslator bookRequestTranslator = new BookRequestTranslator();
    private PublisherTranslator publisherTranslator = new PublisherTranslator();
    private LibraryCardTranslator libraryCardTranslator = new LibraryCardTranslator();

// Loaders

    private UserLoader userLoader = new UserLoader(userRepository,userTranslator);
    private PublisherLoader publisherLoader = new PublisherLoader(publisherRepository,publisherTranslator);
    private BookLoader bookLoader = new BookLoader(bookRepository,bookTranslator,publisherLoader);
    private BookRequestLoader bookRequestLoader = new BookRequestLoader(bookRequestRepository,bookRequestTranslator, bookLoader);
    private LibraryCardLoader libraryCardLoader = new LibraryCardLoader(libraryCardRepository,libraryCardTranslator,userLoader,bookRequestLoader);

    public UserRepository getUserRepository()
    {
        return userRepository;
    }

    public BookRepository getBookRepository()
    {
        return bookRepository;
    }

    public PublisherRepository getPublisherRepository()
    {
        return publisherRepository;
    }

    public BookRequestRepository getBookRequestRepository()
    {
        return bookRequestRepository;
    }

    public LibraryCardRepository getLibraryCardRepository()
    {
        return libraryCardRepository;
    }

    public UserTranslator getUserTranslator()
    {
        return userTranslator;
    }

    public BookTranslator getBookTranslator()
    {
        return bookTranslator;
    }

    public BookRequestTranslator getBookRequestTranslator()
    {
        return bookRequestTranslator;
    }

    public PublisherTranslator getPublisherTranslator()
    {
        return publisherTranslator;
    }

    public LibraryCardTranslator getLibraryCardTranslator()
    {
        return libraryCardTranslator;
    }

    public UserLoader getUserLoader()
    {
        return userLoader;
    }

    public BookLoader getBookLoader()
    {
        return bookLoader;
    }

    public PublisherLoader getPublisherLoader()
    {
        return publisherLoader;
    }

    public BookRequestLoader getBookRequestLoader()
    {
        return bookRequestLoader;
    }

    public LibraryCardLoader getLibraryCardLoader()
    {
        return libraryCardLoader;
    }


    // Operations with Publisher

    public Publisher getPublisherById(Long id){
        return publisherLoader.getEntity(id);
    }

    public PublisherValueObject createPublisher(int pages,String genre, String publisher, int year){
        PublisherValueObject publisherValueObject = new PublisherValueObject(publisherRepository.getMaxId() + 1,publisher,year);
        publisherRepository.incMaxId();
        publisherRepository.add(publisherValueObject);

        return publisherValueObject;
    }

    public void updatePublisher(PublisherValueObject publisherValueObject){
        publisherRepository.update(publisherValueObject);
    }

    public void deletePublisherById(Long id){
        ArrayList<Long> bookIDs = new ArrayList<>();
        for (BookValueObject bookValueObject : bookRepository.getAll()) {
            if (bookValueObject.publisherName == id){
                bookIDs.add(bookValueObject.id);
            }
        };

        for (Long bookId : bookIDs){
            deleteBookById(bookId);
        }

        publisherRepository.delete(id);
    }

    public Collection<Publisher> getAllPublishers(){
        return publisherLoader.getAllEntities(publisherRepository);
    }

    // Operations with User

    public User getUserById(Long id){
        return userLoader.getEntity(id);
    }

    public UserValueObject createUser(String fio, String phoneNumber, String address){
        UserValueObject userValueObject = new UserValueObject(userRepository.getMaxId() + 1, fio,
                phoneNumber, address);
        userRepository.incMaxId();
        userRepository.add(userValueObject);

        return userValueObject;
    }

    public void updateUser(UserValueObject user){
        userRepository.update(user);
    }

    public void deleteUserById(Long id){
        ArrayList<Long> cardIDs = new ArrayList<>();
        for (LibraryCardValueObject libraryCardValueObject : libraryCardRepository.getAll()) {
            if (libraryCardValueObject.user == id){
                cardIDs.add(libraryCardValueObject.id);
            }
        };

        for (long idCard  : cardIDs){
            deleteLibraryCardById(idCard);
        }

        userRepository.delete(id);
    }

    public Collection<User> getAllUsers(){
        return userLoader.getAllEntities(userRepository);
    }


    // Operations with Book

    public Book getBookById(Long id){
        return bookLoader.getEntity(id);
    }

    public BookValueObject createBook(String name, String author, Long publisherId){
        BookValueObject bookValueObject = new BookValueObject(bookRepository.getMaxId() + 1,
                name, author, publisherId);
        bookRepository.incMaxId();
        bookRepository.add(bookValueObject);

        return bookValueObject;
    }

    public void updateBook(BookValueObject book){
        bookRepository.update(book);
    }

    public void deleteBookById(Long id){
        ArrayList<Long> requestIDs = new ArrayList<>();
        for (BookRequestValueObject lotValueObject : bookRequestRepository.getAll()){
            if (lotValueObject.book == id){
                requestIDs.add(lotValueObject.id);
            }
        };

        for (long request  : requestIDs){
            deleteBookRequestById(request);
        }

        bookRepository.delete(id);
    }

    public Collection<Book> getAllBooks(){
        return bookLoader.getAllEntities(bookRepository);
    }


    // Operations with Library Card

    public LibraryCard getLibraryCardById(Long id){
        return libraryCardLoader.getEntity(id);
    }

    public LibraryCardValueObject createLibraryCard(long userId) {

        boolean i = false;
        for(LibraryCardValueObject card:libraryCardRepository.getAll())
        {
            if (card.user == userId)
            {
                i = true;
                break;
            }
        }
        if (i)
        {
            logger.error("User already has a library card!");
            return  null;
        }
        Collection<Long> requestCollection= new ArrayList<>();
        LibraryCardValueObject cardValueObject = new LibraryCardValueObject(libraryCardRepository.getMaxId() + 1, userId, requestCollection);

        return cardValueObject;
    }

    public void deleteLibraryCardById(Long id) {
        libraryCardRepository.delete(id);
    }

    public void updateLibraryCard(LibraryCardValueObject card){
        libraryCardRepository.update(card);
    }

    public Collection<LibraryCard> getAllLibraryCards(){
        return libraryCardLoader.getAllEntities(libraryCardRepository);
    }

    // Operations with BookRequest

    public BookRequest getBookRequestById(Long id){
        return bookRequestLoader.getEntity(id);
    }

    public BookRequestValueObject createBookRequest(Date date, long bookId){
        BookRequestValueObject bookRequestValueObject = new BookRequestValueObject(bookRequestRepository.getMaxId() + 1, date, bookId);
        bookRequestRepository.incMaxId();
        bookRequestRepository.add(bookRequestValueObject);
        return bookRequestValueObject;
    }

    public void deleteBookRequestById(Long id){

        for(LibraryCardValueObject card: libraryCardRepository.getAll())
        {
            for(long requestID:card.bookRequests)
                if(requestID == id)
                {
                    getLibraryCardById(card.id).getBookRequests().remove(getBookRequestById(id));
                }

        }

        bookRequestRepository.delete(id);
    }

    public void updateBookRequest(BookRequestValueObject requestValueObject){
        bookRequestRepository.update(requestValueObject);
    }

    public Collection<BookRequest> getAllBookRequests(){
        return bookRequestLoader.getAllEntities(bookRequestRepository);
    }
}


