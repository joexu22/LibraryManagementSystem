/**
 * 
 */
package com.gcit.project.lms.menu;

import com.gcit.project.lms.entity.Author;
import com.gcit.project.lms.entity.Book;
import com.gcit.project.lms.entity.BookLoan;
import com.gcit.project.lms.entity.Borrower;
import com.gcit.project.lms.entity.Branch;
import com.gcit.project.lms.entity.Genre;
import com.gcit.project.lms.entity.Publisher;
import com.gcit.project.lms.service.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


/**
 * @author joe
 *
 */
public class EntryPoint {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// entry point of the program
		startUp();
	}
	
	public static void startUp() throws SQLException {
		System.out.println("");
		System.out.println("Welcome to the GCIT Library Management System. Which category of a user are you?\n");
		System.out.println("1) Librarian");
		System.out.println("2) Administrator");
		System.out.println("3) Borrower");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\nInput: ");
		int selection = sc.nextInt();

		if (selection == 1) {
			startLibrarianMenu();
		} else if (selection == 2) {
			startAdminMenu();
		} else if (selection == 3) {
			startBorrowerMenu();
		}
	}

	private static void startLibrarianMenu() throws SQLException {
		System.out.println("");
		System.out.println("1) Select Branch to Manage");
		System.out.println("2) Quit to Previous");
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\nInput: ");
		int selection = sc.nextInt();
		if (selection == 1) {
			startLibrarianBranchMenu();
		} else if (selection == 2) {
			startUp();
		}
	}
	

	private static void startLibrarianBranchMenu() throws SQLException {
		//Create Menu
		LibrarianService ls = new LibrarianService();
		ArrayList<Branch> branch_list = new ArrayList<>();
		branch_list = (ArrayList<Branch>) ls.readBranch();
		
		System.out.println("");
		for (int i=0; i<branch_list.size(); i++) {
			System.out.println(i + ") " + branch_list.get(i).getBranchName() + ", " + branch_list.get(i).getBranchAddress());
		}
		System.out.println(branch_list.size() + ") Quit to Previous");
		System.out.println("\nInput: ");
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		
		if (selection<branch_list.size()) {
			startBranchMenu(branch_list.get(selection));
		} else {
			startLibrarianMenu();
		}
	}
	
	private static void startBranchMenu(Branch branch) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("1) Update the details of the Library");
		System.out.println("2) Add copies of Book to the Branch");
		System.out.println("3) Quit to Previous");
		
		System.out.println("\nInput: ");
		int selection = sc.nextInt();

		if (selection == 1) {
			updateLibraryBranch(branch);
		} else if (selection == 2) {
			addCopiesOfBookToBranch(branch);
		} else if (selection == 3) {
			startLibrarianBranchMenu();
		}
	}
	
	private static void updateLibraryBranch(Branch branch) throws SQLException {
		Branch newBranch = new Branch();
		newBranch.setBranchId(branch.getBranchId());
		
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.print("You have chosen to update the Branch with Branch Id: ");
		System.out.print(branch.getBranchId() + " and Branch Name: ");
		System.out.println(branch.getBranchName() + ".");
		System.out.println("Enter 'quit' at any promt to cancel operation.");
		
		String inputName = branch.getBranchName();
		System.out.println("\nPlease enter new branch name or enter N/A for no change:");
		inputName = sc.nextLine();
		if (inputName.equals("quit")) {
			startBranchMenu(branch);
		} else if (inputName.equals("N/A")) {
			newBranch.setBranchName(branch.getBranchName());
		} else {
			newBranch.setBranchName(inputName);
		}
				
		String inputAddress = branch.getBranchAddress();
		System.out.println("Please enter new branch address or enter N/A for no change:");
		inputAddress = sc.nextLine();
		if (inputName.equals("quit")) {
			startBranchMenu(branch);
		} else if (inputName.equals("N/A")) {
			newBranch.setBranchAddress(branch.getBranchAddress());
		} else {
			newBranch.setBranchAddress(inputAddress);
		}
		
		LibrarianService ls = new LibrarianService();
		ls.editBranch(newBranch);
		startBranchMenu(newBranch);
	}
	
	private static void addCopiesOfBookToBranch(Branch branch) throws SQLException {
		LibrarianService ls = new LibrarianService();
		ArrayList<Book> book_list = new ArrayList<>();
		book_list = (ArrayList<Book>) ls.viewBooks();
		
		//TODO: Potentially Fix The Index
		System.out.println("");
		for (int i=0; i<book_list.size(); i++) {
			System.out.print(i + ") " + book_list.get(i).getTitle() + " by ");
			List<String> authorNames = new ArrayList<>();
			for (Author author : book_list.get(i).getAuthors()) {
				authorNames.add(author.getAuthorName());
			}
			System.out.println(String.join(",",authorNames));
		}
		System.out.println(book_list.size() + ") Quit to Previous");
		System.out.println("\nInput: ");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection < book_list.size()) {
			//Get Number Of Copies
			int numberOfCopies = ls.viewNumOfBookCopies(book_list.get(selection), branch);
			//Display It
			System.out.println("");
			System.out.println("Existing number of copies: " + numberOfCopies);
			//Insert Number Of Copies
			System.out.print("Enter new number of copies: ");
			int newNoOfCopies = sc.nextInt();
			ls.changeNumOfBookCopies(book_list.get(selection), branch, newNoOfCopies);
			//Going Back
			startBranchMenu(branch);
		} else {
			startBranchMenu(branch);
		}		
	}

	private static void startAdminMenu() throws SQLException {
		System.out.println("");
		System.out.println("What Whould You Like To Do?\n");
		System.out.println("1) Manage Books");
		System.out.println("2) Manage Authors");
		System.out.println("3) Manage Genres");
		System.out.println("4) Manage Publishers");
		System.out.println("5) Manage Library Branches");
		System.out.println("6) Manage Borrowers");
		System.out.println("7) Over-ride Due Date for a Book Loan");
		System.out.println("8) Exit And Back");
		System.out.println("\nInput: ");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();		
		if (selection == 1) {
			adminBook();
		} else if (selection == 2) {
			adminAuthor();
		} else if (selection == 3) {
			adminGenre();
		} else if (selection == 4) {
			adminPublisher();
		} else if (selection == 5) {
			adminLibraryBranch();
		} else if (selection == 6) {
			adminBorrower();
		} else if (selection == 7) {
			adminDueDate();
		} else {
			startUp();
		}		
	}
	
	private static void adminBook() throws SQLException {
		System.out.println("");
		System.out.println("Book Menu\n");
		System.out.println("1) Add Title");
		System.out.println("2) Edit Book Menu");
		System.out.println("3) Delete Title");
		System.out.println("4) View All Titles");
		System.out.println("5) Go To Previous Menu");
		System.out.println("\nInput: ");
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();		
		if (selection == 1) {
			addBook();
		} else if (selection == 2) {
			updateBookMenu();
		} else if (selection == 3) {
			deleteBook();
		} else if (selection == 4) {
			readBook();
		} else {
			startAdminMenu();
		}		
	}
	
	private static void addBook() throws SQLException {
		System.out.println("");
		System.out.println("Adding Book Menu\n");
		
		AdminService as = new AdminService();
		Book book = new Book();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Book Title: ");
		String bookTitle = sc.nextLine();
		
		System.out.println("Select Publisher\n");
		List<Publisher> publisher_list = as.readPublisher();
		for (int i=0;i<publisher_list.size();i++) {
			System.out.println(i + ") " + publisher_list.get(i).getPubName());
		}
		System.out.println(publisher_list.size() + ") Enter New Publisher");
		System.out.println("\nInput:");
		Scanner sc2 = new Scanner(System.in);
		int selection = sc2.nextInt();
		if (selection < publisher_list.size()) {
			book.setPublisher(publisher_list.get(selection));
		} else {
			addPublisher();
		}
		book.setTitle(bookTitle);
		as.addBook(book);
		System.out.println("More Book Options: (y/n)");
		Scanner sc3 = new Scanner(System.in);
		String answer =	sc3.nextLine();
		if (answer.contains("y")) {
			editBook(book);
		} else {
			adminBook();			
		}
	}

	private static void updateBookMenu() throws SQLException {
		AdminService as = new AdminService();
		List<Book> list = new ArrayList<>();
		list = as.readBook();
		
		System.out.println("");
		System.out.println("Select Book To Edit\n");
		
		for (int i=0; i<list.size();i++) {
			System.out.print(i + ") " + list.get(i).getTitle());
			System.out.print(" by ");
			List<String> authorNames = new ArrayList<>();
			for (Author author : list.get(i).getAuthors()) {
				authorNames.add(author.getAuthorName());
			}
			System.out.println(String.join(",",authorNames));
		}
		System.out.println(list.size() + ") Back to Previous");
		System.out.println("\nInput:");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection < list.size()) {
			editBook(list.get(selection));
		} else {
			adminBook();
		}
	}
	
	private static void editBook(Book book) throws SQLException {
		System.out.println("");
		System.out.println("Edit Book Menu\n");
		System.out.println("1) Change Title");
		System.out.println("2) Change Publisher");
		System.out.println("3) Edit Authors");
		System.out.println("4) Edit Genres");
		System.out.println("5) Go To Previous Menu");
		System.out.println("\nInput: ");
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();		
		if (selection == 1) {
			changeBookTitle(book);
		} else if (selection == 2) {
			changeBookPublisher(book);
		} else if (selection == 3) {
			changeBookAuthors(book);
		} else if (selection == 4) {
			changeBookGenres(book);
		} else {
			startAdminMenu();
		}		
	}	
	
	private static void changeBookTitle(Book book) throws SQLException {
		System.out.println("");
		System.out.println("Changing Book Title\n");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter new book title: ");
		String bookTitle = sc.nextLine();
			
		book.setTitle(bookTitle);
		AdminService as = new AdminService();
		as.updateBook(book);
			
		System.out.println("Edit Success");
		System.out.println("More Book Options: (y/n)");
		Scanner sc3 = new Scanner(System.in);
		String answer =	sc3.nextLine();
		if (answer.contains("y")) {
			editBook(book);
		} else {
			adminBook();			
		}
	}
	
	private static void changeBookPublisher(Book book) throws SQLException {
		System.out.println("");
		System.out.println("Changing Book Publisher\n");

		AdminService as = new AdminService();

		System.out.println("Select Publisher\n");
		List<Publisher> publisher_list = as.readPublisher();
		for (int i=0;i<publisher_list.size();i++) {
			System.out.println(i + ") " + publisher_list.get(i).getPubName());
		}
		System.out.println(publisher_list.size() + ") Enter New Publisher");
		System.out.println("\nInput:");
		Scanner sc2 = new Scanner(System.in);
		int selection = sc2.nextInt();
		if (selection < publisher_list.size()) {
			book.setPublisher(publisher_list.get(selection));
		} else {
			addPublisher();
		}

		as.updateBookPubId(book);

		System.out.println("More Book Options: (y/n)");
		Scanner sc3 = new Scanner(System.in);
		String answer =	sc3.nextLine();
		if (answer.contains("y")) {
			editBook(book);
		} else {
			adminBook();			
		}
	}
	
	private static void changeBookAuthors(Book book) throws SQLException {
		System.out.println("");
		System.out.println("Edit Authors For Book\n");
		System.out.println("1) Add Authors");
		System.out.println("2) Remove Authors");
		System.out.println("3) Go To Previous Menu");
		System.out.println("\nInput: ");
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();		
		if (selection == 1) {
			addBookAuthors(book);
		} else if (selection == 2) {
			removeBookAuthors(book);
		} else {
			editBook(book);
		}
	}
	
	private static void addBookAuthors(Book book) throws SQLException {
		System.out.println("");
		System.out.println("Adding Authors to Book\n");

		AdminService as = new AdminService();

		System.out.println("Select Authors\n");
		List<Author> all_author_list = as.readAuthor();
		HashSet<Author> book_author_list = new HashSet<>();
		Optional.ofNullable(book.getAuthors()).ifPresent(book_author_list::addAll);
		
		for (int i=0;i<all_author_list.size();i++) {
			System.out.println(i + ") " + all_author_list.get(i).getAuthorName());
		}
		System.out.println(all_author_list.size() + ") Finish Adding Authors");
		Scanner sc2 = new Scanner(System.in);
		
		List<String> authorNames = new ArrayList<>();
		for (Author author : book_author_list) {
			authorNames.add(author.getAuthorName());
		}
		String nameList = String.join(",",authorNames);		
		
		System.out.println("\nCurrent Authors: [" + nameList + "]");
		System.out.println("Input:");
		int selection = sc2.nextInt();
		while (selection < all_author_list.size()) {
			book_author_list.add(all_author_list.get(selection)); //add to list
			//Display List
			List<String> newAuthorNames = new ArrayList<>();
			for (Author author : book_author_list) {
				newAuthorNames.add(author.getAuthorName());
			}
			nameList = String.join(",",newAuthorNames);
			System.out.println("\nAuthors: [" + nameList + "]");
			System.out.println("Input:");
			selection = sc2.nextInt();
		}
		
		for (Author a : book_author_list) {
			as.updateBookAuthor(book, a);
		}

		System.out.println("More Book Options: (y/n)");
		Scanner sc3 = new Scanner(System.in);
		String answer =	sc3.nextLine();
		if (answer.contains("y")) {
			editBook(book);
		} else {
			adminBook();			
		}
	}
	
	private static void removeBookAuthors(Book book) throws SQLException {
		System.out.println("");
		System.out.println("Removing Authors from Book\n");

		AdminService as = new AdminService();

		System.out.println("Select Authors to Remove\n");
		List<Author> available_authors = as.readAuthorOfBook(book);
		HashSet<Author> authors_to_remove = new HashSet<>();

		for (int i=0;i<available_authors.size();i++) {
			System.out.println(i + ") " + available_authors.get(i).getAuthorName());
		}
		System.out.println(available_authors.size() + ") Finish Removing Authors");
		
		Scanner sc2 = new Scanner(System.in);
		System.out.println("\nInput:");
		int selection = sc2.nextInt();
		while (selection < available_authors.size() && !available_authors.isEmpty()) {
			authors_to_remove.add(available_authors.get(selection));
			// Display List
			List<String> authorNames = new ArrayList<>();
			for (Author author : authors_to_remove) {
				authorNames.add(author.getAuthorName());
			}
			String nameList = String.join(",",authorNames);
			System.out.println("[" + nameList + "]");
			System.out.println("\nInput:");
			selection = sc2.nextInt();
		}
		
		for (Author a : authors_to_remove) {
			as.deleteBookAuthor(book, a);
		}

		System.out.println("More Book Options: (y/n)");
		Scanner sc3 = new Scanner(System.in);
		String answer =	sc3.nextLine();
		if (answer.contains("y")) {
			editBook(book);
		} else {
			adminBook();			
		}
	}
	
	private static void changeBookGenres(Book book) throws SQLException {
		System.out.println("");
		System.out.println("Edit Genres For Book\n");
		System.out.println("1) Add Genre");
		System.out.println("2) Remove Genre");
		System.out.println("3) Go To Previous Menu");
		System.out.println("\nInput: ");
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();		
		if (selection == 1) {
			addBookGenres(book);
		} else if (selection == 2) {
			removeBookGenres(book);
		} else {
			editBook(book);
		}
	}
	
	private static void addBookGenres(Book book) throws SQLException {
		System.out.println("");
		System.out.println("Adding Genres to Book\n");

		AdminService as = new AdminService();

		System.out.println("Select Genres\n");
		List<Genre> all_genre_list = as.readGenre();
		HashSet<Genre> book_genre_list = new HashSet<>();
		Optional.ofNullable(book.getGenres()).ifPresent(book_genre_list::addAll);
		
		for (int i=0;i<all_genre_list.size();i++) {
			System.out.println(i + ") " + all_genre_list.get(i).getGenreName());
		}
		System.out.println(all_genre_list.size() + ") Finish Adding Genres");
		Scanner sc2 = new Scanner(System.in);
		
		List<String> genreNames = new ArrayList<>();
		for (Genre genre : book_genre_list) {
			genreNames.add(genre.getGenreName());
		}
		String nameList = String.join(",",genreNames);		
		
		System.out.println("\nCurrent Genres: [" + nameList + "]");
		System.out.println("Input:");
		int selection = sc2.nextInt();
		while (selection < all_genre_list.size()) {
			book_genre_list.add(all_genre_list.get(selection));
			List<String> newGenreNames = new ArrayList<>();
			for (Genre genre : book_genre_list) {
				newGenreNames.add(genre.getGenreName());
			}
			nameList = String.join(",",newGenreNames);
			System.out.println("\nGenres: [" + nameList + "]");
			System.out.println("Input:");
			selection = sc2.nextInt();
		}
		
		for (Genre g : book_genre_list) {
			as.updateBookGenre(book, g);
		}

		System.out.println("More Book Options: (y/n)");
		Scanner sc3 = new Scanner(System.in);
		String answer =	sc3.nextLine();
		if (answer.contains("y")) {
			editBook(book);
		} else {
			adminBook();			
		}
	}

	private static void removeBookGenres(Book book) throws SQLException {
		System.out.println("");
		System.out.println("Removing Genres from Book\n");

		AdminService as = new AdminService();

		System.out.println("Select Genres to Remove\n");
		List<Genre> available_genres = as.readGenreOfBook(book);
		HashSet<Genre> genres_to_remove = new HashSet<>();

		for (int i=0;i<available_genres.size();i++) {
			System.out.println(i + ") " + available_genres.get(i).getGenreName());
		}
		System.out.println(available_genres.size() + ") Finish Removing Genres");
		
		Scanner sc2 = new Scanner(System.in);
		System.out.println("\nInput:");
		int selection = sc2.nextInt();
		while (selection < available_genres.size() && !available_genres.isEmpty()) {
			genres_to_remove.add(available_genres.get(selection));
			// Display List
			List<String> genreNames = new ArrayList<>();
			for (Genre genre : genres_to_remove) {
				genreNames.add(genre.getGenreName());
			}
			String nameList = String.join(",",genreNames);
			System.out.println("[" + nameList + "]");
			System.out.println("\nInput:");
			selection = sc2.nextInt();
		}
		
		for (Genre a : genres_to_remove) {
			as.deleteBookGenre(book, a);
		}

		System.out.println("More Book Options: (y/n)");
		Scanner sc3 = new Scanner(System.in);
		String answer =	sc3.nextLine();
		if (answer.contains("y")) {
			editBook(book);
		} else {
			adminBook();			
		}
	}
	private static void deleteBook() throws SQLException {
		AdminService as = new AdminService();
		List<Book> list = new ArrayList<>();
		list = as.readBook();
		System.out.println("");
		System.out.println("Delete Books:\n");
		
		for (int i=0; i<list.size();i++) {
			System.out.println(i + ") " + list.get(i).getTitle());
		}
		System.out.println(list.size() + ") Back to Previous");
		System.out.println("\nInput:");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection == list.size()) {
			adminBook();
		} else {
			System.out.println("");
			as.deleteBook(list.get(selection));
			System.out.println("Delete Success");
			adminBook();
		}
	}
	
	private static void readBook() throws SQLException {
		AdminService as = new AdminService();
		List<Book> list = new ArrayList<>();
		list = as.readBook();
		System.out.println("");
		System.out.println("View Books:\n");
		for (Book b : list) {
			System.out.print(b.getTitle());
			System.out.print(" by ");
			List<String> authorNames = new ArrayList<>();
			for (Author author : b.getAuthors()) {
				authorNames.add(author.getAuthorName());
			}
			System.out.println(String.join(",",authorNames));
		}
		System.out.println("\nEnter 0 to go back\nInput:");
		Scanner sc = new Scanner(System.in);
		int selection = -1;
		while (selection != 0) {
			selection = sc.nextInt();
			adminBook();
		}
	}
	
	private static void adminAuthor() throws SQLException {
		System.out.println("");
		System.out.println("Author Menu\n");
		System.out.println("1) Add Authors");
		System.out.println("2) Edit Authors");
		System.out.println("3) Delete Authors");
		System.out.println("4) View Authors");
		System.out.println("5) Go To Previous Menu");
		System.out.println("\nInput: ");
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();		
		if (selection == 1) {
			addAuthor();
		} else if (selection == 2) {
			updateAuthor();
		} else if (selection == 3) {
			deleteAuthor();
		} else if (selection == 4) {
			readAuthor();
		} else {
			startAdminMenu();
		}		
	}
	
	private static void addAuthor() throws SQLException {
		System.out.println("");
		System.out.println("Adding Author:\n");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Author Name: ");
		String authorName = sc.nextLine();

		Author author = new Author();
		author.setAuthorName(authorName);
		
		AdminService as = new AdminService();
		as.addAuthor(author);
		
		adminAuthor();
	}
	
	private static void updateAuthor() throws SQLException {
		AdminService as = new AdminService();
		List<Author> list = new ArrayList<>();
		list = as.readAuthor();
		
		System.out.println("");
		System.out.println("Edit Author:\n");
		
		for (int i = 0; i<list.size(); i++) {
			System.out.println(i + ") " + list.get(i).getAuthorName());
		}
		System.out.println(list.size() + ") Back to Previous");
		System.out.println("\nInput:");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection == list.size()) {
			adminAuthor();
		} else {
			System.out.println("");
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Enter new author name: ");
			String newName = sc2.nextLine();
			
			Author na = new Author();
			na.setAuthorName(newName);
			na.setAuthorId(list.get(selection).getAuthorId());
			as.updateAuthor(na);
			
			System.out.println("Edit Success");
			adminAuthor();
		}
	}
	
	private static void deleteAuthor() throws SQLException {
		AdminService as = new AdminService();
		List<Author> list = new ArrayList<>();
		list = as.readAuthor();
		System.out.println("");
		System.out.println("Delete Author:\n");
		
		for (int i=0; i<list.size();i++) {
			System.out.println(i + ") " + list.get(i).getAuthorName());
		}
		System.out.println(list.size() + ") Back to Previous");
		System.out.println("\nInput:");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection == list.size()) {
			adminAuthor();
		} else {
			System.out.println("");
			as.deleteAuthor(list.get(selection));
			System.out.println("Delete Success");
			adminAuthor();
		}
	}
	
	private static void readAuthor() throws SQLException {
		AdminService as = new AdminService();
		List<Author> list = new ArrayList<>();
		list = as.readAuthor();
		System.out.println("");
		System.out.println("View Authors\n");
		for (Author a : list) {
			System.out.println(a.getAuthorName());
		}
		System.out.println("\nEnter 0 to go back\nInput:");
		Scanner sc = new Scanner(System.in);
		int selection = -1;
		while (selection != 0) {
			selection = sc.nextInt();
			adminAuthor();
		}
	}
	
	private static void adminGenre() throws SQLException {
		System.out.println("");
		System.out.println("Genre Menu\n");
		System.out.println("1) Add Genre");
		System.out.println("2) Edit Genre");
		System.out.println("3) Delete Genre");
		System.out.println("4) Read Genre");
		System.out.println("5) Go To Previous Menu");
		System.out.println("\nInput: ");
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();		
		if (selection == 1) {
			addGenre();
		} else if (selection == 2) {
			updateGenre();
		} else if (selection == 3) {
			deleteGenre();
		} else if (selection == 4) {
			readGenre();
		} else {
			startAdminMenu();
		}	
	}
	
	private static void addGenre() throws SQLException {
		System.out.println("");
		System.out.println("Adding Genre Menu\n");
		Scanner sc = new Scanner(System.in);
		System.out.println("Genre Name: ");
		String name = sc.nextLine();
		Genre genre = new Genre();
		genre.setGenreName(name);
		AdminService as = new AdminService();
		as.addGenre(genre);
		adminGenre();
	}
	
	private static void updateGenre() throws SQLException {
		AdminService as = new AdminService();
		List<Genre> list = new ArrayList<>();
		list = as.readGenre();
		
		System.out.println("");
		System.out.println("Edit Genres:\n");
		
		for (int i=0; i<list.size();i++) {
			System.out.println(i + ") " + list.get(i).getGenreName());			
		}
		System.out.println(list.size() + ") Back to Previous");
		System.out.println("\nInput:");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection == list.size()) {
			adminGenre();
		} else {
			System.out.println("");
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Enter new genre name: ");
			String newGenre = sc2.nextLine();
			
			Genre ng = new Genre();
			ng.setGenreName(newGenre);
			ng.setGenreId(list.get(selection).getGenreId());
			as.updateGenre(ng);
			
			System.out.println("Edit Success");
			adminGenre();
		}
	}
	
	private static void deleteGenre() throws SQLException {
		AdminService as = new AdminService();
		List<Genre> list = new ArrayList<>();
		list = as.readGenre();
		System.out.println("");
		System.out.println("Delete Genres:\n");
		
		for (int i=0; i<list.size();i++) {
			System.out.println(i + ") " + list.get(i).getGenreName());			
		}
		System.out.println(list.size() + ") Back to Previous");
		System.out.println("\nInput:");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection == list.size()) {
			adminGenre();
		} else {
			System.out.println("");
			as.deleteGenre(list.get(selection));
			System.out.println("Delete Success");
			adminGenre();
		}
	}
	
	private static void readGenre() throws SQLException {
		AdminService as = new AdminService();
		List<Genre> list = new ArrayList<>();
		list = as.readGenre();
		System.out.println("");
		System.out.println("View Genres:\n");
		for (Genre g : list) {
			System.out.println(g.getGenreName());
		}
		System.out.println("\nEnter 0 to go back\nInput:");
		Scanner sc = new Scanner(System.in);
		int selection = -1;
		while (selection != 0) {
			selection = sc.nextInt();
			adminGenre();
		}
	}

	private static void adminPublisher() throws SQLException {
		System.out.println("");
		System.out.println("Publisher Menu\n");
		System.out.println("1) Add Publisher");
		System.out.println("2) Edit Publisher");
		System.out.println("3) Delete Publisher");
		System.out.println("4) View Publisher");
		System.out.println("5) Go To Previous Menu");
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();		
		if (selection == 1) {
			addPublisher();
		} else if (selection == 2) {
			updatePublisher();
		} else if (selection == 3) {
			deletePublisher();
		} else if (selection == 4) {
			readPublisher();
		} else {
			startAdminMenu();
		}	
	}
	
	private static void addPublisher() throws SQLException {
		System.out.println("");
		System.out.println("Adding Publisher:\n");
		Scanner sc = new Scanner(System.in);
		System.out.println("Publisher Name: ");
		String name = sc.nextLine();
		System.out.println("Publisher Address: ");
		String address = sc.nextLine();
		System.out.println("Publisher Phone: ");
		String phone = sc.nextLine();
		Publisher publisher = new Publisher();
		publisher.setPubName(name);
		publisher.setPubAddress(address);
		publisher.setPubPhone(phone);
		AdminService as = new AdminService();
		as.addPublisher(publisher);
		adminPublisher();
	}
	
	private static void updatePublisher() throws SQLException {
		AdminService as = new AdminService();
		List<Publisher> list = new ArrayList<>();
		list = as.readPublisher();
		System.out.println("");
		System.out.println("Edit Publisher:\n");
		
		for (int i=0; i<list.size();i++) {
			System.out.println(i + ") " + list.get(i).getPubName());
		}
		System.out.println(list.size() + ") Back to Previous");
		System.out.println("\nInput:");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection == list.size()) {
			adminPublisher();
		} else {
			System.out.println("");
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Enter new publisher name: ");
			String newName = sc2.nextLine();
			System.out.println("Enter new publisher address: ");
			String newAddress = sc2.nextLine();
			System.out.println("Enter new publisher phone: ");
			String newPhone = sc2.nextLine();
			
			Publisher np = new Publisher();
			np.setPubName(newName);
			np.setPubAddress(newAddress);
			np.setPubPhone(newPhone);
			np.setPublisherId(list.get(selection).getPublisherId());
			as.updatePublisher(np);
			
			System.out.println("Edit Success");
			adminPublisher();
		}
	}
	
	private static void deletePublisher() throws SQLException {
		AdminService as = new AdminService();
		List<Publisher> list = new ArrayList<>();
		list = as.readPublisher();
		System.out.println("");
		System.out.println("Delete Publishers:\n");
		for (int i=0; i<list.size();i++) {
			System.out.println(i + ") " + list.get(i).getPubName());
		}
		System.out.println(list.size() + ") Back to Previous");
		System.out.println("\nInput:");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection == list.size()) {
			adminPublisher();
		} else {
			System.out.println("");
			as.deletePublisher(list.get(selection));
			System.out.println("Delete Success");
			adminPublisher();
		}
	}
	
	private static void readPublisher() throws SQLException {
		AdminService as = new AdminService();
		List<Publisher> list = new ArrayList<>();
		list = as.readPublisher();
		System.out.println("");
		System.out.println("View Publishers:\n");
		for (Publisher p : list) {
			System.out.print(p.getPubName() + " | ");
			System.out.print(p.getPubAddress() + " | ");
			System.out.println(p.getPubPhone());
		}		
		System.out.println("\nEnter 0 to go back\nInput:");
		Scanner sc = new Scanner(System.in);
		int selection = -1;
		while (selection != 0) {
			selection = sc.nextInt();
			adminPublisher();
		}
	}
	
	private static void adminLibraryBranch() throws SQLException {
		System.out.println("");
		System.out.println("Library Branch Menu\n");
		System.out.println("1) Add Library Branch");
		System.out.println("2) Update Library Info");
		System.out.println("3) Delete Branch");
		System.out.println("4) View Branch Info");
		System.out.println("5) Go To Previous Menu");
		System.out.println("\nInput: ");
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();		
		if (selection == 1) {
			addLibraryBranch();
		} else if (selection == 2) {
			updateLibraryBranch();
		} else if (selection == 3) {
			deleteLibraryBranch();
		} else if (selection == 4) {
			readLibraryBranch();
		} else {
			startAdminMenu();
		}	
	}
	
	private static void addLibraryBranch() throws SQLException {
		System.out.println("");
		System.out.println("Adding Branch:\n");
		Scanner sc = new Scanner(System.in);
		System.out.println("Name: ");
		String name = sc.nextLine();
		System.out.println("Address: ");
		String address = sc.nextLine();
		Branch branch = new Branch();
		branch.setBranchName(name);
		branch.setBranchAddress(address);
		AdminService as = new AdminService();
		as.addBranch(branch);
		adminLibraryBranch();
	}

	private static void updateLibraryBranch() throws SQLException {
		AdminService as = new AdminService();
		List<Branch> list = new ArrayList<>();
		list = as.readBranch();
		System.out.println("");
		System.out.println("Edit Library Branch:\n");
		
		for (int i=0; i<list.size();i++) {
			System.out.println(i + ") " + list.get(i).getBranchName());
		}
		System.out.println(list.size() + ") Back to Previous");
		System.out.println("\nInput:");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection == list.size()) {
			adminLibraryBranch();
		} else {
			System.out.println("");
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Enter new branch name: ");
			String newName = sc2.nextLine();
			System.out.println("Enter new branch address: ");
			String newAddress = sc2.nextLine();
			
			Branch nb = new Branch();
			nb.setBranchName(newName);
			nb.setBranchAddress(newAddress);
			nb.setBranchId(list.get(selection).getBranchId());
			as.updateBrach(nb);
			
			System.out.println("Edit Success");
			adminLibraryBranch();
		}
	}

	private static void deleteLibraryBranch() throws SQLException {
		AdminService as = new AdminService();
		List<Branch> list = new ArrayList<>();
		list = as.readBranch();
		System.out.println("");
		System.out.println("Delete Branch:\n");
		for (int i=0; i<list.size();i++) {
			System.out.println(i + ") " + list.get(i).getBranchName());
		}
		System.out.println(list.size() + ") Back to Previous");
		System.out.println("\nInput:");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection == list.size()) {
			adminLibraryBranch();
		} else {
			System.out.println("");
			as.deleteBranch(list.get(selection));
			System.out.println("Delete Success");
			adminLibraryBranch();
		}
	}

	private static void readLibraryBranch() throws SQLException {
		AdminService as = new AdminService();
		List<Branch> list = new ArrayList<>();
		list = as.readBranch();
		System.out.println("");
		System.out.println("View Library Branches:\n");
		for (Branch b : list) {
			System.out.print(b.getBranchName() + " | ");
			System.out.println(b.getBranchAddress());
		}

		System.out.println("\nEnter 0 to go back\nInput:");
		Scanner sc = new Scanner(System.in);
		int selection = -1;
		while (selection != 0) {
			selection = sc.nextInt();
			adminLibraryBranch();
		}
	}

	private static void adminBorrower() throws SQLException {
		System.out.println("");
		System.out.println("Admin Borrower Menu\n");
		System.out.println("1) Add Borrower");
		System.out.println("2) Edit Borrower");
		System.out.println("3) Delete Borrower");
		System.out.println("4) View Borrower");
		System.out.println("5) Go To Previous Menu");
		System.out.println("\nInput: ");

		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();		
		if (selection == 1) {
			addBorrower();
		} else if (selection == 2) {
			updateBorrower();
		} else if (selection == 3) {
			deleteBorrower();
		} else if (selection == 4) {
			readBorrower();
		} else {
			startAdminMenu();
		}	
	}
	
	private static void addBorrower() throws SQLException {
		System.out.println("");
		System.out.println("Adding Borrower");
		Scanner sc = new Scanner(System.in);
		System.out.println("Name: ");
		String name = sc.nextLine();
		System.out.println("Address: ");
		String address = sc.nextLine();
		System.out.println("Phone: ");
		String phone = sc.nextLine();
		Borrower borrower = new Borrower();
		borrower.setName(name);
		borrower.setAddress(address);
		borrower.setPhone(phone);
		AdminService as = new AdminService();
		as.addBorrower(borrower);
		adminBorrower();
	}

	private static void updateBorrower() throws SQLException {
		AdminService as = new AdminService();
		List<Borrower> list = new ArrayList<>();
		list = as.readBorrower();
		System.out.println("");
		System.out.println("Edit Library Branch:\n");
		
		for (int i=0; i<list.size();i++) {
			System.out.println(i + ") " + list.get(i).getName());
		}
		System.out.println(list.size() + ") Back to Previous");
		System.out.println("\nInput:");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection == list.size()) {
			adminBorrower();
		} else {
			System.out.println("");
			Scanner sc2 = new Scanner(System.in);
			System.out.println("Enter new borrower name: ");
			String newName = sc2.nextLine();
			System.out.println("Enter new borrower address: ");
			String newAddress = sc2.nextLine();
			System.out.println("Enter new borrower phone: ");
			String newPhone = sc2.nextLine();
			
			Borrower nbw = new Borrower();
			nbw.setName(newName);
			nbw.setAddress(newAddress);
			nbw.setPhone(newPhone);
			nbw.setCardNo(list.get(selection).getCardNo());
			as.updateBorrower(nbw);
			
			System.out.println("Edit Success");
			adminBorrower();
		}
	}

	private static void deleteBorrower() throws SQLException {
		AdminService as = new AdminService();
		List<Borrower> list = new ArrayList<>();
		list = as.readBorrower();
		System.out.println("");
		System.out.println("Delete Borrower:\n");
		for (int i=0; i<list.size();i++) {
			System.out.println(i + ") " + list.get(i).getName());
		}
		System.out.println(list.size() + ") Back to Previous");
		System.out.println("\nInput:");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection == list.size()) {
			adminBorrower();
		} else {
			System.out.println("");
			as.deleteBorrower(list.get(selection));
			System.out.println("Delete Success");
			adminBorrower();
		}
	}

	private static void readBorrower() throws SQLException {
		AdminService as = new AdminService();
		List<Borrower> list = new ArrayList<>();
		list = as.readBorrower();
		System.out.println("");
		System.out.println("View Borrowers:\n");
		for (Borrower bw : list) {
			System.out.print(bw.getName() + " | ");
			System.out.print(bw.getAddress() + " | ");
			System.out.println(bw.getPhone());
		}

		System.out.println("\nEnter 0 to go back\nInput:");
		Scanner sc = new Scanner(System.in);
		int selection = -1;
		while (selection != 0) {
			selection = sc.nextInt();
			adminBorrower();
		}		
	}

	private static void adminDueDate() throws SQLException {
		AdminService as = new AdminService();
		List<BookLoan> list = new ArrayList<>();
		list = as.viewDueDates();
		System.out.println("");
		System.out.println("Select Book To Override:\n");
		for (int i = 0; i < list.size(); i++) {
			System.out.print(i + ") ");
			System.out.print(list.get(i).getBookName() + " | ");
			System.out.print(list.get(i).getBranchName() + " | ");
			System.out.print(list.get(i).getBorrowerName() + " | ");
			System.out.println(list.get(i).getDateDue());
		}
		System.out.println(list.size() + ") Back to Previous");
		System.out.println("\nInput: ");
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection >= list.size()) {
			startAdminMenu();
		} else {
			overrideDueDate(list.get(selection));
		}
	}

	private static void overrideDueDate(BookLoan bookLoan) throws SQLException {
		System.out.println("Enter New Date: ");
		Scanner sc = new Scanner(System.in);
		String newDate = sc.nextLine();
		AdminService as = new AdminService();
		as.overrideDueDate(bookLoan, newDate);
		startAdminMenu();
	}

	private static void startBorrowerMenu() throws SQLException {
		System.out.println("Enter your card Number: ");
		Scanner sc = new Scanner(System.in);
		int cardNumber = sc.nextInt();
		
		BorrowerService bs = new BorrowerService();
		List<Borrower> borrowersList = bs.readAllBorrowerId();
		for (Borrower b : borrowersList) {
			if (b.getCardNo() == cardNumber) {
				startBorrowerOptions(b);
			}
		}
		startBorrowerMenu();
	}
	
	private static void startBorrowerOptions(Borrower borrower) throws SQLException {
		System.out.println("");
		System.out.println("1) Checkout book");
		System.out.println("2) Return book");
		System.out.println("3) Quit to Previous");
		System.out.println("\nInput: ");

		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();

		if (selection == 1) {
			startBorrowerCheckout(borrower);
		} else if (selection == 2) {
			startBorrowerReturn(borrower);
		} else if (selection == 3) {
			startUp();
		}
	}

	private static void startBorrowerCheckout(Borrower borrower) throws SQLException {
		System.out.println("");
		System.out.println("Pick the branch you want to check out from:\n");
		
		// TODO: ***currently reuses the librarian service***
		LibrarianService ls = new LibrarianService();
		ArrayList<Branch> branch_list = new ArrayList<>();
		branch_list = (ArrayList<Branch>) ls.readBranch();
		
		//TODO: 0 based-index vs 1-based
		for (int i=0; i<branch_list.size(); i++) {
			System.out.println(i + ") " + branch_list.get(i).getBranchName() + ", " + branch_list.get(i).getBranchAddress());
		}
		System.out.println(branch_list.size() + ") Quit to Previous");
		System.out.println("\nInput: ");
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		
		if (selection<branch_list.size()) {
			checkoutBook(branch_list.get(selection), borrower);
		} else {
			startBorrowerOptions(borrower);
		}
	}
	
	private static void checkoutBook(Branch branch, Borrower borrower) throws SQLException {
		System.out.println("");
		System.out.println("What book do you want to check out?");
		
		BorrowerService bs = new BorrowerService();
		List<Book> book_list = bs.viewBooksAvailableForCheckout(branch);
		for (int i=0; i<book_list.size(); i++) {
			System.out.print(i + ") ");
			System.out.print(book_list.get(i).getTitle());
			System.out.print(" by ");
			List<String> authorNames = new ArrayList<>();
			for (Author author : book_list.get(i).getAuthors()) {
				authorNames.add(author.getAuthorName());
			}
			System.out.println(String.join(",",authorNames));
		}
		System.out.println(book_list.size() + ") Quit to Previous");
		System.out.println("\nInput: ");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection >= book_list.size()) {
			startBorrowerCheckout(borrower);
		} else {
			bs.checkoutBook(branch, book_list.get(selection), borrower);
			startBorrowerOptions(borrower);
		}
	}
	
	private static void startBorrowerReturn(Borrower borrower) throws SQLException {
		System.out.println("");
		System.out.println("Pick The Branch You Will Return Books To:");
		BorrowerService bs = new BorrowerService();
		List<Branch> branch_list = bs.getBranchOfCheckedOutBooks(borrower);
		for (int i=0;i<branch_list.size();i++) {
			System.out.print(i + ") ");
			System.out.println(branch_list.get(i).getBranchName());
		}
		System.out.println(branch_list.size() + ") Quit to Previous");
		System.out.println("\nInput: ");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection >= branch_list.size()) {
			startBorrowerOptions(borrower);
		} else {
			returnBook(branch_list.get(selection), borrower);
		}
	}
	
	private static void returnBook(Branch branch, Borrower borrower) throws SQLException {
		System.out.println("");
		System.out.println("What book to return?");
		BorrowerService bs = new BorrowerService();
		
		List<Book> book_list = bs.viewBooksAvailableForReturn(branch, borrower);
		for (int i=0; i<book_list.size(); i++) {
			System.out.print(i + ") ");
			System.out.print(book_list.get(i).getTitle());
			System.out.print(" by ");
			List<String> authorNames = new ArrayList<>();
			for (Author author : book_list.get(i).getAuthors()) {
				authorNames.add(author.getAuthorName());
			}
			System.out.println(String.join(",",authorNames));
		}
		System.out.println(book_list.size() + ") Quit to Previous");
		System.out.println("\nInput: ");
		
		Scanner sc = new Scanner(System.in);
		int selection = sc.nextInt();
		if (selection >= book_list.size()) {
			startBorrowerReturn(borrower);
		} else {
			bs.returnBook(branch, book_list.get(selection), borrower);
			startBorrowerOptions(borrower);
		}
	}
}
