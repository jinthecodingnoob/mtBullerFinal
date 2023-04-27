public class Accommodation {

    private int accNo;
    private String type;
    private double pricePerDay;
    private boolean availability = true;
    static int nextId = 1;

    public Accommodation() {}

    public Accommodation (String type, double pricePerDay) {
        this.type = type;
        this.pricePerDay = pricePerDay;
        accNo = nextId++;
    }
    public int getAccNo () {
        return accNo;
    }

    public String getType () {
        return type;
    }

    public double getPricePerDay () {
        return pricePerDay;
    }

    public boolean getAvailability () {
        return availability;
    }

    public void setType (String type) {
        this.type = type;
    }

    public void setPricePerDay (double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setAvailability (boolean availability) {
        this.availability = availability;
    }

    public String toString () {
        return "Accommodation #: " + accNo + ", type: " + type + ", price per day: " + pricePerDay + ", available?  " + availability;
    }

}