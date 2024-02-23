import java.util.List;

public interface TaxEvasionInterface {
    void insert(LargeDepositor item);
    void load(String filename);
    void updateSavings(int AFM, double savings);
    LargeDepositor searchByAFM(int AFM);
    PQ searchByLastName(String last_name);
    void remove(int AFM);
    double getMeanSavings();
    void printΤopLargeDepositors(int k);
    void printByAFM();

}
