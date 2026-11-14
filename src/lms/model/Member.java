package lms.model;

public class Member implements Comparable<Member>{
    private String memberId;
    private String name;
    private int borrowedCount;
    private int maxBorrowLimit;

    public Member(String memberId, String name, int maxBorrowLimit){
        setMemberId(memberId);
        setName(name);
        setMaxBorrowLimit(maxBorrowLimit);
        this.borrowedCount = 0;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        if(memberId == null || memberId.isBlank()){
            throw new IllegalArgumentException("memberId cannot be Empty");
        }else{
            this.memberId = memberId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be Empty");
        }else{
            this.name = name;
        }
    }

    public int getMaxBorrowLimit() {
        return maxBorrowLimit;
    }

    public void setBorrowedCount(int borrowedCount) {
        this.borrowedCount = borrowedCount;
    }

    public void setMaxBorrowLimit(int maxBorrowLimit) {
        if(maxBorrowLimit < 0){
            throw new IllegalArgumentException("Max borrowed count cannot be below than 0");
        }else{
            this.maxBorrowLimit = maxBorrowLimit;
        }
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void incrementBorrowedCount(){
        setBorrowedCount(borrowedCount+1);
    }

    public void decrementBorrowedCount(){
        if(borrowedCount <= 0){
            throw new IllegalStateException("Borrowed count cannot be below 0");
        }
        setBorrowedCount(borrowedCount-1);
    }

    @Override
    public int compareTo(Member other) {
        return Integer.compare(other.borrowedCount, this.borrowedCount);
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", borrowedCount=" + borrowedCount +
                '}';
    }
}
