
public class Main {
    public static void main(String[] args) {
        DateFrame dateFrame =  new DateFrame();
        long diff= dateFrame.DifferenceEnglishAndNepaliDates("1944-01-01","2024-07-01");
        System.out.println(dateFrame.addDifferencetoNepaliDate(diff));

    }
}
