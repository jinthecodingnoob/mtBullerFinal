
import java.io.*;
import java.time.*;

public class Package implements Serializable {

    private int packageId;
    private int custId;
    private int accNo;
    private LocalDate startDate;
    private int duration;

    private double liftPassCost;

    static int nextID = 10;


    public Package (int custId, LocalDate startDate, int duration) {
        this.custId = custId;
        this.startDate = startDate;
        this.duration = duration;
        packageId= nextID++;
    }

    public Package (int custId,  int duration) {
        this.custId = custId;
        this.duration = duration;
        packageId = nextID++;
    }
    public int getPackageId() {
        return packageId;
    }
    public int getCustId () {
        return custId;
    }
    public LocalDate getDate () {
        return startDate;
    }
    public int getDuration () {
        return duration;
    }

    public int getAccNo() {return accNo;}

    private double liftPassCostPerDay;

    public double getLiftPassCostPerDay() {
        return liftPassCostPerDay;
    }

    public void setLiftPassCostPerDay(double liftPassCostPerDay) {
        this.liftPassCostPerDay = liftPassCostPerDay;
    }

    public void getCost() {
    }
    public void setLiftPassCost(double liftPassCost) {
        this.liftPassCost = liftPassCost;
    }

    public double getLiftPassCost() {
        return liftPassCost;
    }

        private double lessonCost;

        public double getLessonCost() {
            return lessonCost;
        }

        public void setLessonCost(double lessonCost) {
            this.lessonCost = lessonCost;
        }


    public void setAccNo(int accNo) {
        this.accNo = accNo;
    }

    private double totalCost;

}
