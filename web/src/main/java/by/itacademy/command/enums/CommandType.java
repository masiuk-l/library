package by.itacademy.command.enums;

import by.itacademy.command.Controller;
import by.itacademy.command.impl.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Project KR. Created by masiuk-l on 12.08.2017.
 *
 * contains available commands
 */
@Getter
@AllArgsConstructor
public enum CommandType {

    ADD_BOOK("admin/addBook.jsp", "addbook", new AddBookController()),
    ADD_AUTHOR("", "addauthor", new AddAuthorController()),
    CATALOG("catalog/main.jsp", "catalog", new CatalogController()),
    SEARCH_CATALOG("catalog/main.jsp", "searchcatalog", new SearchCatalogController()),
    BAN_READER_AJAX("", "banReader", new BanReaderController()),
    BOOK("catalog/book.jsp", "book", new BookController()),
    EDIT_BOOK("admin/editBook.jsp", "editBook", new EditBookController()),
    DELETE_BOOK("catalog/book.jsp", "deleteBook", new DeleteBookController()),
    EDIT_READER("cabinet/editReader.jsp", "editreader", new EditReaderController()),
    ERROR("error/error.jsp", "error", new ErrorController()),
    LOGIN("login.jsp", "login", new LoginController()),
    LOGIN_LIB("login.jsp", "loginlib", new LoginLibController()),
    LOGOUT("", "logout", new LogoutController()),
    MAIN("main/main.jsp", "main", new MainController()),
    MY_BOOKS("cabinet/myBooks.jsp", "mybooks", new MyBooksController()),
    NOT_FOUND("error/404.jsp", "404", new ErrorController()),
    ABOUT("about/main.jsp", "about", new AboutController()),
    READERS("admin/readers.jsp", "readers", new ReadersController()),
    RESERVE_BOOK_AJAX("", "reserveBook", new ReserveBookController()),
    RETURN_BOOK_AJAX("", "returnBook", new ReturnBookController()),
    SIGN_UP("login.jsp", "signup", new SignUpController());



    private String pagePath;
    private String pageName;
    private Controller controller;

    /**
     * @param page page name
     * @return matching command type or MAIN by default
     */
    public static CommandType getByPageName(String page) {
        for (CommandType type : CommandType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
        return MAIN;
    }
}
