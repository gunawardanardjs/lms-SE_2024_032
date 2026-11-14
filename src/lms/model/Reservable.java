package lms.model;

public interface Reservable {
    void reserve(Member m);
    void cancelReservation();
    boolean isReserved();

    default String reservationLabel() {
        return isReserved() ? "Reserved" : "Free";
    }

}
