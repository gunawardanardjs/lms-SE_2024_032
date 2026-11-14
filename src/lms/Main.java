package lms;

import lms.model.*;
import lms.service.Library;

public class Main {
    void main(String[] args) {
        Library library = new Library();

        Book b1 = new Book("B001","Clean Code", "Robert C. Martin", "978-0132350884");
        DVD d1 = new DVD("D001", "Introduction to Algorithms", 120);
        Magazine mag1 = new Magazine("M001", "Software Engineering", 10032);
        library.addItem(b1);
        library.addItem(d1);
        library.addItem(mag1);

        Member m1 = new Student("M001", "Kasun");
        Member m2 = new Staff("M002", "Mr. Sahan");
        library.addMember(m1);
        library.addMember(m2);

        //library.listItems();
        //library.listmembers();

        library.printAllLateFees(3);

        library.lend(b1, m1);
        library.lend(b1, m2);
        library.lend(d1, m2);
        library.lend(mag1, m2);

        library.reserveIfPossible(b1, m2);
        library.reserveIfPossible(d1, m2);

        library.printMembersByActivity();

        //library.streamIfPossible(d1);
        //library.streamIfPossible(b1);

        /*try{
            library.addItem(new Book("B003","Clean Code", "", "978-0132350884"));
        }catch(IllegalArgumentException e){
            System.out.println("\nRejected Invalid Book : " + e.getMessage());
        }

        try{
            library.addMember(new Member("M001", "Kasun", -4));
        }catch(IllegalArgumentException e){
            System.out.println("Rejected Invalid Member : " + e.getMessage());
        }

        System.out.print("\nFinding a book via ISBM : ");
        System.out.print(library.findBookByIsbn("978-0132350884"))*/


    }
}