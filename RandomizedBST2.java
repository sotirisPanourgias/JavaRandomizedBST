/*
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


class RandomizedBST1 implements TaxEvasionInterface {
    private static TreeNode root;
    private int size;

    PQ q = new PQ();

    private  TreeNode rotateLeft( TreeNode pivot) {

        TreeNode parent = pivot.getParent();
        TreeNode child = pivot.getRight();

        if (parent == null) {
            root = child;
        }
        else if (parent.getLeft() == pivot) {
            parent.setLeft(child);
        }
        else {
            parent.setRight(child);
        }
        child.setParent(pivot.getParent());
        pivot.setParent(child);
        pivot.setRight(child.getLeft());
        if (child.getLeft() != null) {
            child.getLeft().setParent(pivot);
        }
        child.setLeft(pivot);
        return child;
    }
    private TreeNode rotateRight(TreeNode pivot) {

        TreeNode parent = pivot.getParent();
        TreeNode child = pivot.getLeft();

        if (parent == null) {
            root = child;
        }
        else if (parent.getLeft() == pivot) {
            parent.setLeft(child);
        }
        else {
            parent.setRight(child);
        }

        child.setParent(pivot.getParent());
        pivot.setParent(child);
        pivot.setLeft(child.getRight());
        if (child.getRight() != null) {
            child.getRight().setParent(pivot);
        }
        child.setRight(pivot);
        return child;
    }



    public void insert(LargeDepositor item) {
        root = rootInsert(root, item,null);
        q.insert(item);
    }
    private TreeNode rootInsert(TreeNode root, LargeDepositor element, TreeNode parent) {
        if (root == null) {
            TreeNode node = new TreeNode(element);
            node.setParent(parent);
            ++size;
            return node;
        }
        int result = element.compareTo(root.getitem());

        if (result == 0) {
            return root;
        }

        if (result < 0) {
            TreeNode leftSubtreeRoot = this.rootInsert(root.getLeft(), element, root);
            root.setLeft(leftSubtreeRoot);
            root = this.rotateRight(root);

        }
        else {
            TreeNode rightSubtreeRoot = this.rootInsert(root.getRight(), element, root);
            root.setRight(rightSubtreeRoot);

            root = this.rotateLeft(root);
        }


        return root;
    }

    public void load(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(" ");
                int AFM = Integer.parseInt(tokens[0]);
                String firstName = tokens[1];
                String lastName = tokens[2];
                double savings = Double.parseDouble(tokens[3]);
                double taxedIncome = Double.parseDouble(tokens[4]);
                insert(new LargeDepositor(AFM, firstName, lastName, savings, taxedIncome));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void updateSavings(int AFM, double savings) {
        LargeDepositor node = searchByAFM(AFM);
        if (node != null) {
            node.setSavings(savings);
        } else {
            System.out.println("Ο υποψήφιος με ΑΦΜ " + AFM + " δεν βρέθηκε στο δέντρο.");
        }
    }


    public LargeDepositor searchByAFM(int AFM) {
        TreeNode currentNode = root;
        while (currentNode != null) {
            if (AFM == currentNode.item.getAFM()) {
                LargeDepositor foundDepositor = currentNode.item;
                System.out.println("Ονοματεπώνυμο: " + foundDepositor.getFirstName()+" "+foundDepositor.getLastName());
                System.out.println("ΑΦΜ: " + foundDepositor.getAFM());
                System.out.println("Καταθέσεις: " + foundDepositor.getSavings());
                System.out.println("Δηλωμένο Εισόδημα: " + foundDepositor.getTaxedIncome());
                return foundDepositor;
            } else if (AFM < currentNode.item.getAFM()) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        System.out.println("Ο υποψήφιος με ΑΦΜ " + AFM + " δεν βρέθηκε στο δέντρο.");
        return null;
    }


    public PQ searchByLastName(String last_name) {
        TreeNode currentNode = root;
        PQ s = new PQ();
        while (currentNode != null) {
            int result = last_name.compareTo(currentNode.item.getLastName());
            if (result == 0) {
                LargeDepositor foundDepositor1 = currentNode.item;
                s.insert(foundDepositor1);
                System.out.println("Ονοματεπώνυμο: " + foundDepositor1.getFirstName()+" "+foundDepositor1.getLastName());
                System.out.println("ΑΦΜ: " + foundDepositor1.getAFM());
                System.out.println("Καταθέσεις: " + foundDepositor1.getSavings());
                System.out.println("Δηλωμένο Εισόδημα: " + foundDepositor1.getTaxedIncome());
                return s;
            } else if (result < 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        System.out.println("Ο υποψήφιος με ΑΦΜ " + last_name + " δεν βρέθηκε στο δέντρο.");
        return s;
    }

    public void remove(int AFM) {
        for (int i = 0; i < q.size(); i++) {
            if (q.getFirst() == AFM) {
                root = removee(root,AFM);
                q.removeFirst();

            }
        }
    }

    private  TreeNode removee(TreeNode node, int AFM) {
        if (node == null) {
            return null;
        }

        if (AFM < node.item.getAFM()) {
            node.left= removee(node.getLeft(), AFM);
        } else if (AFM > node.item.getAFM()) {
            node.right = removee(node.right, AFM);
        } else {
            if (node.left == null) {
                return node.getRight();
            } else if (node.right == null) {
                return node.left;
            }


            node.item = minValue(node.right);


            node.right = removee(node.right, node.item.getAFM());
        }

        if (size(node.getLeft()) < size(node.getRight()) + 1) {
            node = rotateRight(node);
        } else if (size(node.getRight()) > size(node.getLeft()) + 1) {
            node = rotateLeft(node);
        }

        node.n = 1 + size(node.getLeft()) + size(node.getRight());

        return node;
    }

    private static LargeDepositor minValue(TreeNode node) {
        LargeDepositor minValue = node.item;
        while (node.left != null) {
            minValue = node.left.item;
            node = node.left;
        }
        return minValue;
    }

    private static int size(TreeNode node) {
        return (node == null) ? 0 : size(node.getLeft()) + size(node.getRight()) + 1;
    }


    public double getMeanSavings() {
        TotalSavings totalSavings = calculateTotalSavings(root);
        int totalDepositors = totalSavings.totalDepositors;

        if (totalDepositors == 0) {
            return 0; // Αποφεύγουμε τη διαίρεση με μηδέν
        }

        System.out.println( totalSavings.totalSavings / (double) totalDepositors);
        return totalSavings.totalSavings / (double) totalDepositors;
    }

    static class TotalSavings {
        double totalSavings;
        int totalDepositors;

        public TotalSavings(double totalSavings, int totalDepositors) {
            this.totalSavings = totalSavings;
            this.totalDepositors = totalDepositors;
        }
    }

    private static TotalSavings calculateTotalSavings(TreeNode node) {
        if (node == null) {
            return new TotalSavings(0, 0);
        }

        TotalSavings leftSavings = calculateTotalSavings(node.left);
        TotalSavings rightSavings = calculateTotalSavings(node.right);

        double totalSavings = leftSavings.totalSavings + rightSavings.totalSavings + node.item.getSavings();
        int totalDepositors = leftSavings.totalDepositors + rightSavings.totalDepositors + 1;

        return new TotalSavings(totalSavings, totalDepositors);
    }
    public void printΤopLargeDepositors(int k) {
        StringDoubleEndedQueueImpl topSuspectsQueue = new StringDoubleEndedQueueImpl();
        System.out.println(q.size());
        topSuspectsQueue.addLast(root.item.getAFM());
        traverseForTopSuspects(topSuspectsQueue, k);
        */
/*if(k>=topSuspectsQueue.size()){
            printQ(root);
        }*//*

        for (int i=0;i<k;i++){
            int currentAFM = topSuspectsQueue.removeFirst();
            LargeDepositor currentDepositor = searchByAFM(currentAFM);
            System.out.println("Ονοματεπώνυμο: " + currentDepositor.getFirstName()+" "+currentDepositor.getLastName());
            System.out.println("ΑΦΜ: " + currentDepositor.getAFM());
            System.out.println("Καταθέσεις: " + currentDepositor.getSavings());
            System.out.println("Δηλωμένο Εισόδημα: " + currentDepositor.getTaxedIncome());
        }
    }
    //StringDoubleEndedQueueImpl topSuspectsQueue1 = new StringDoubleEndedQueueImpl();
    double susp;
    public double suspScore(LargeDepositor ld){
        if(ld.getTaxedIncome()<8000){
            susp =  Double.MAX_VALUE;
        }else {
            susp = ld.getSavings()- ld.getTaxedIncome();
        }
        //topSuspectsQueue1.addLast(ld.getAFM());
        return susp;
    }

    double minscore = -Double.MAX_VALUE;

  */
/*  private void traverseForTopSuspects( StringDoubleEndedQueueImpl topSuspectsQueue, int k) {
        for (int i=0;i<=q.size();i++){
            LargeDepositor cr = searchByAFM(q.getFirst());
            if(suspScore(cr)>minscore){
                minscore = suspScore(cr);
                topSuspectsQueue.addFirst(q.getFirst());
            }
        }
    }*//*


    public void printByAFM() {
        printQ(root);
    }
    private void printQ(TreeNode node) {
        if (node != null) {
            printQ(node.left);
            LargeDepositor currentDepositor = node.item;
            System.out.println("AFM: " + currentDepositor.getAFM());
            System.out.println("Name: " + currentDepositor.getFirstName() + " " + currentDepositor.getLastName());
            System.out.println("Savings: " + currentDepositor.getSavings());
            System.out.println("Taxed Income: " + currentDepositor.getTaxedIncome());
            System.out.println();
            printQ(node.right);
        }
    }

    public static void main(String[] args) {
        RandomizedBST1 bst = new RandomizedBST1();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu:");
            System.out.println("1. Insert LargeDepositor");
            System.out.println("2. Remove LargeDepositor");
            System.out.println("3. Search by AFM");
            System.out.println("4. Search by Last Name");
            System.out.println("5. Update savings");
            System.out.println("6. Print Top LargeDepositors");
            System.out.println("7. Print the mean savings");
            System.out.println("8. Print by AFM");
            System.out.println("9. load txt");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter first name: ");
                    String firstName = scanner.next();

                    System.out.print("Enter last name: ");
                    String lastName = scanner.next();

                    System.out.print("Enter AFM: ");
                    int afm = scanner.nextInt();

                    System.out.print("Enter savings: ");
                    double savings = scanner.nextDouble();

                    System.out.print("Enter taxed income: ");
                    double taxedIncome = scanner.nextDouble();

                    LargeDepositor newDepositor = new LargeDepositor(afm,firstName, lastName, savings, taxedIncome);
                    bst.insert(newDepositor);
                    break;
                case 2:
                    System.out.print("Enter AFM: ");
                    afm = scanner.nextInt();
                    bst.remove(afm);
                    break;
                case 3:
                    System.out.print("Enter AFM: ");
                    afm = scanner.nextInt();
                    bst.searchByAFM(afm);
                    break;
                case 4:
                    System.out.print("Enter Last Name: ");
                    String LastN = scanner.next();
                    bst.searchByLastName(LastN);
                    break;
                case 5:
                    System.out.print("Enter AFM: ");
                    afm = scanner.nextInt();
                    System.out.print("Enter savings: ");
                    int sav = scanner.nextInt();
                    bst.updateSavings(afm,sav);
                    break;
                case 6:
                    System.out.print("Enter the number of depositors to print: ");
                    int k = scanner.nextInt();
                    bst.printΤopLargeDepositors(k);
                    break;
                case 7:
                    bst.getMeanSavings();
                    break;
                case 8:
                    bst.printByAFM();
                    break;
                case 9:
                    System.out.print("Enter txt: ");
                    String txt = scanner.next();
                    bst.load(txt);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }

}
*/
