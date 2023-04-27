import java.time.LocalDate;

public class MtBullerAdmin {

    public static void main(String[] args) {
        MtBullerResort jp = new MtBullerResort();
        jp.populateLists();

        LocalDate startDate = LocalDate.now();
        jp.run(startDate);
    }
}