
class LargeDepositor {
    public int getAFM() {
        return AFM;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public double getTaxedIncome() {
        return taxedIncome;
    }

    public void setTaxedIncome(double taxedIncome) {
        this.taxedIncome = taxedIncome;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAFM(int AFM) {
        this.AFM = AFM;
    }

    private int AFM; // ΑΦΜ (ελληνικός)
    private String firstName; // Όνομα
    private String lastName; // Επώνυμο
    private double savings; // Συνολικό ύψος γνωστών καταθέσεων σε άλλες

    private double taxedIncome; //Συνολικό δηλωμένο εισόδημα στην Ελλάδα

    //την τελευταία 3ετία
    int key() {
        return AFM;
    } //μέθοδος για πρόσβαση στο κλειδί
    public int compareTo(LargeDepositor otherDep) {
        // Συγκρίνετε τους μεγάλους καταθέτες βάσει του συνολικού φορολογημένου εισοδήματος
        // Μπορείτε να προσαρμόσετε τη σύγκριση ανάλογα με τα δικά σας κριτήρια
        if (this.AFM < otherDep.getAFM()) {
            return -1;
        } else if (this.AFM > otherDep.getAFM()) {
            return 1;
        } else {
            return 0;
        }
    }
    public LargeDepositor(int AFM, String firstName, String lastName, double savings, double taxedIncome) {
        this.AFM = AFM;
        this.firstName = firstName;
        this.lastName = lastName;
        this.savings = savings;
        this.taxedIncome = taxedIncome;
    }



}