package lms.service;

import lms.exception.ItemNotAvailableException;
import lms.exception.MemberNotFoundException;
import lms.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library{
    private final List<LibraryItem> items = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();

    public void addItem(LibraryItem item){
        items.add(item);
        System.out.println("Added the item " + item.getTitle());
    }

    public void addBook(Book book){
        items.add(book);
        System.out.println("Added the book " + book.getTitle());
    }

    public void addItem(DVD dvd){
        items.add(dvd);
        System.out.println("Added the DVD " + dvd.getTitle());
    }

    public void streamIfPossible(LibraryItem item){
        if(item instanceof DVD){
            DVD dvd = (DVD) item;
            System.out.println("Streaming \"" + dvd.getTitle() + "\" (" + dvd.getDurationMinutes() + " mins)" );
        }else{
            System.out.println("\"" + item.getTitle() + "\" cannot be streamed - it isn't a DVD");
        }
    }

    public void addMember(Member member){
        members.add(member);
    }

    public void listItems(){
        System.out.println("\n-----Items in the Library-----");
        for(LibraryItem item : items){
            System.out.println(item);
        }
    }

    public void listmembers(){
        System.out.println("\n-----Registered Members-----");
        for(Member m : members){
            System.out.println(m);
        }
    }

    public List<LibraryItem> getItems(){
        return items;
    }

    public List<Member> getMembers(){
        return members;
    }
//
//    public Book findBookByIsbn(String isbn) {
//        for (LibraryItem item : items) {
//            if (item instanceof Book) {
//                Book book = (Book) item;
//                if (isbn != null && isbn.equals(book.getIsbn())) {
//                    System.out.println("Book found");
//                    return book;
//                }
//            }
//        }
//        System.out.println("Book Not Found");
//        return null;
//    }

    public void printAllLateFees(int daysLate) {
        System.out.println("\n-----Late fees if " + daysLate + " days late-----");
        for (LibraryItem item : items) {
            System.out.println(item.getTitle() + " : " + item.calculateLateFee(daysLate));
            item.getClass().getSimpleName();
        }
        System.out.println();
    }

    public void lend(Loanable thing, Member member) throws ItemNotAvailableException {
        thing.loan(member);
    }

    public void reserveIfPossible(LibraryItem item, Member member){
        if(item instanceof Reservable reservable){
            reservable.reserve(member);
        }else{
            System.out.println(item.getTitle() + " cannot be reserved");
        }
    }

    public void printMembersByActivity(){
        List<Member> sorted = new ArrayList<>(members);
        Collections.sort(sorted);
        System.out.println("\n-----Members sorted by Activity-----");
        for(Member m : sorted){
            System.out.println(m.getName() + " (" + m.getBorrowedCount() + " borrowed)");
        }
    }

    public Member findMember(String memberId){
        for(Member member : members){
            if(member.getMemberId() == memberId){
                return member;
            }
        }
        throw new MemberNotFoundException(memberId + " Not Found");
    }

    public LibraryItem findItem(String itemId) throws ItemNotAvailableException{
        for(LibraryItem item : items){
            if(item.getId() == itemId){
                return item;
            }
        }
        throw new ItemNotAvailableException(itemId + " not found");
    }

    public void borrowItem(String itemId, String memberId) throws ItemNotAvailableException{
        LibraryItem item = findItem(itemId);
        Member m = findMember(memberId);
        item.loan(m);
    }

    public void returnItem(String itemId) throws ItemNotAvailableException{
        findItem(itemId).returnItem();
    }

}