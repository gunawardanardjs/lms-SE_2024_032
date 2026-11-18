package lms.model;

import lms.exception.ItemNotAvailableException;
import lms.exception.MemberNotFoundException;

public abstract class LibraryItem implements Loanable{
    private String id;
    private String title;
    private boolean isBorrowed;
    private Member borrower;

    public LibraryItem(String id, String title){
        setId(id);
        setTitle(title);
        this.isBorrowed = false;
    }

    public boolean validateText(String text){
        if(text == null || text.isBlank()){
            return false;
        }else{
            return true;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if(!validateText(id)){
            throw new IllegalArgumentException("ID cannot be empty");
        }else{
            this.id = id;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(!validateText(title)){
            throw new IllegalArgumentException("Title cannot be empty");
        }else{
            this.title = title;
        }
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowItem() {
        isBorrowed = true;
    }

    public Member getBorrower(){
       return borrower;
    }

    public abstract double calculateLateFee(int daysLate);

    @Override
    public void loan(Member member) throws ItemNotAvailableException {
        if(isBorrowed){
            throw new ItemNotAvailableException(getTitle() + " is already on loan");
        }else if(member.getBorrowedCount() >= member.getMaxBorrowLimit()){
            throw new MemberNotFoundException(member.getName() + " has reached the limit of " + member.getMaxBorrowLimit() + " items");
        }else{
            isBorrowed = true;
            borrower = member;
            member.incrementBorrowedCount();
            System.out.println("\"" + getTitle() + "\" loaned to " + member.getName());
        }
    }

    @Override
    public void returnItem() throws ItemNotAvailableException {
        if(!isBorrowed){
            throw new ItemNotAvailableException(getTitle() + " is not on loan");
        }
        isBorrowed = false;
        borrower.decrementBorrowedCount();
        borrower = null;
        System.out.println("\"" + getTitle() + "\" returned");
    }

    @Override
    public String toString() {
        return "LibraryItem{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", isBorrowed=" + isBorrowed + '}';
    }
}
