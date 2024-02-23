public class PQ {
    private LargeDepositor[] heap;
    private int size;
    private static final int DEFAULT_CAPACITY = 4;
    private static final int AUTOGROW_SIZE = 4;


    public PQ() {
        this.heap = new LargeDepositor[DEFAULT_CAPACITY + 1];
        this.size = 0;
    }

    public LargeDepositor[] getHeap() {
        return heap;
    }

    public int size(){
        return size;

    }

    private void grow() {
        LargeDepositor[] newHeap = new LargeDepositor[heap.length + AUTOGROW_SIZE];

        for (int i = 0; i <= size; i++) {
            newHeap[i] = heap[i];
        }

        heap = newHeap;
    }
    private void swap(int i, int j) {
        LargeDepositor tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }
    public boolean isEmpty(){
        return size() ==0;
    }
    public void insert(LargeDepositor x){

        if (size == heap.length - 1)
            grow();
        size++;
        heap[size] = x;


        swim(size);
    }
    public LargeDepositor min(){
        if (heap.length==0){
            return null;
        }
        return heap[1];

    }
    /*public LargeDepositor getmin(){
        LargeDepositor min = min();
        if(min==null){
            return null;
        }
        int id = min.getID();
        remove(id);
        return min;
    }*/
    /*public LargeDepositor remove(int id) {
        for (int i = 1; i <= size; i++) {
            if (heap[i].getID() == id) {
                City removed = heap[i];

                swap(i, size);
                size--;

                sink(i);

                return removed;
            }
        }
        return null;
    }*/

    public void swim(int i){
        if(i ==1){
            return;
        }
        int parent = i/2;
        while (i != 1 && heap[i].compareTo(heap[parent])  > 0) {
            swap(i, parent);
            i = parent;
            parent = i / 2;
        }
    }
    public void sink(int i){
        int left = 2 * i;
        int right = left + 1;
        if (left > size){
            return;
        }
        while (left <= size) {
            int max = left;
            if (right <= size) {
                if (heap[left].compareTo(heap[right]) < 0)
                    max = right;
            }

            if (heap[i].compareTo(heap[max]) >= 0)
                return;
            else {
                swap(i, max);
                i = max;
                left = i * 2;
                right = left + 1;
            }
        }
    }

}
