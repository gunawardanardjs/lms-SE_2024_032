package lms.model;

public abstract class LibraryItem implements Loanable{
    private String id;
    private String title;
    private boolean isBorrowed;
    private Member borrowedBy;

    public LibraryItem(String id, String title){
        setId(id);
        setTitle(title);
        this.isBorrowed = false;
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

    public boolean validateText(String text){
        if(text == null || text.isBlank()){
            return false;
        }else{
            return true;
        }
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

    public abstract double calculateLateFee(int daysLate);

    @Override
    public void loan(Member member) {
        if(isBorrowed){
            System.out.println(title + " is already on loan.");
        }else if(member.getBorrowedCount() >= member.getMaxBorrowLimit()){
            System.out.println("\"" + member.getName() + "\" has reached the maximum limit");
        }else{
            isBorrowed = true;
            borrowedBy = member;
            member.incrementBorrowedCount();
            System.out.println("\"" + getTitle() + "\" loaned to " + member.getName());
        }
    }

    @Override
    public void returnItem() {
        if(!isBorrowed){
            System.out.println("Cannot return \"" + getTitle() + "\": it is not currently on loan.");
            return;
        }
        isBorrowed = false;
        borrowedBy.decrementBorrowedCount();
        borrowedBy = null;
        System.out.println("\"" + getTitle() + "\" returned");
    }

    @Override
    public String toString() {
        return "LibraryItem{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", isBorrowed=" + isBorrowed + '}';
    }
}
