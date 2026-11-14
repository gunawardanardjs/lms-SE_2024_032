package lms.model;

public class Book extends LibraryItem implements Reservable{
    private String author;
    private String isbn;
    private Member reservedBy;

    public Book(String id, String title, String author, String isbn){
        super(id, title);
        setAuthor(author);
        setIsbn(isbn);
    }

    public String getTitle() {
        return super.getTitle();
    }

    public void setTitle(String title) {
        if(!checkValidate(title)){
            throw new IllegalArgumentException("Title cannot be empty");
        }else{
            super.setTitle(title);
        }
    }

    public void setAuthor(String author) {
        if(!checkValidate(author)){
            throw new IllegalArgumentException("Author cannot be empty");
        }else{
            this.author = author;
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if(!checkValidate(isbn)){
            throw new IllegalArgumentException("ISBN cannot be empty");
        }else{
            this.isbn = isbn;
        }
    }

    public boolean checkValidate(String text){
        if(text == null || text.isBlank()){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public double calculateLateFee(int daysLate) {
        if(daysLate < 0){
            System.out.println("Overdue days cannot br below than 0");
            return 0.0;
        }else{
            return daysLate*10.0;
        }
    }

    @Override
    public void reserve(Member m) {
        if(reservedBy != null){
            System.out.println(getTitle() + " is already reserved " + reservedBy.getName());
        }else{
            reservedBy = m;
            System.out.println("\"" + getTitle() + "\" reserved for " + m.getName());
        }
    }

    @Override
    public void cancelReservation() {
        reservedBy = null;
        System.out.println("Cancelled reservation for " + getTitle());
    }

    @Override
    public boolean isReserved() {
        return reservedBy != null;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + getTitle() + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", isBorrowed=" + isBorrowed() +
                '}';
    }
}