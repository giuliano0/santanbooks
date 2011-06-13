package TestApps;

import Classes.Book;

public class IsbnTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String test;
		Book b = new Book();
		
		test = "0495011665";
		b.setISBN(test);
		System.out.println("Input string: " + test + ". Set property: " + b.getISBN() + ".");
		
		test = "04-950-1166-5";
		b.setISBN(test);
		System.out.println("Input string: " + test + ". Set property: " + b.getISBN() + ".");
		
		test = "0495011668";
		b.setISBN(test);
		System.out.println("Input string: " + test + ". Set property: " + b.getISBN() + ".");
		
		test = "04-950-1166-8";
		b.setISBN(test);
		System.out.println("Input string: " + test + ". Set property: " + b.getISBN() + ".");
		
		test = "978-04-950-1166-8";
		b.setISBN(test);
		System.out.println("Input string: " + test + ". Set property: " + b.getISBN() + ".");
	}

}
