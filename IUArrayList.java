import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * An array based implementation of IndexedUnsortedList interface  
 * 
 * @param <T>  - type of elements held in this collection
 * @author Evan Wallace
 */
public class IUArrayList<T> implements IndexedUnsortedList<T> {
    private T[] array;
    private int rear;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;
    public static void main(String[] args) {
        IUArrayList listTest = new IUArrayList<Integer>(3);
        listTest.addToRear(1);
        listTest.addToRear(3);
        listTest.addToRear(2);
        listTest.printTest();
        listTest.addToFront(5);
        listTest.addToFront(10);
        listTest.printTest();
        listTest.addAfter(69, 10);
        listTest.printTest();
        System.out.println(listTest.toString());

        
    }

    public void printTest(){
        for(int i = 0; i<this.rear; i++){
            System.out.println(this.array[i]);
        }
        System.out.println("array length: " + this.array.length);
    }

    /**
     * Helper method to double the capacity of arrays
     */
    private void arrayCapacityDoubler(T[] list){
        int newArrayCapacity = list.length * 2;
        this.array = (T[]) new Object[newArrayCapacity];
        for(int i = 0; i<this.rear; i++){
            this.array[i] = list[i];
        }
    }

    /**
     * Default constructor
     * creates an array of size DEFAULT_CAPACITY which is 10
     * sets size = 0
     */
    public IUArrayList(){
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
        this.rear = 0;
    }

    /**
     * Custom Capacity Constructor
     * @param capacity the capacity of the created arrayList
     * sets size = 0
     */
    public IUArrayList(int capacity){
        if(capacity <= 0){
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }
        this.array = (T[]) new Object[capacity];
        this.rear = 0;
    }

    /**  
     * Adds the specified element to the front of this list. 
     *
     * @param element the element to be added to the front of this list    
     */
    public void addToFront(T element) {
        if(this.rear == this.array.length){
            arrayCapacityDoubler(this.array);
        }
        T hold;
        for (int i = this.rear; i > 0; i--) {
            this.array[i] = this.array[i-1];
        }
        this.array[0] = element;
        this.rear++;
        this.modCount++;
    }

    /**  
     * Adds the specified element to the rear of this list. 
     *
     * @param element the element to be added to the rear of this list    
     */
    @Override
    public void addToRear(T element) {
        if(this.rear == this.array.length){
            arrayCapacityDoubler(this.array);
        }
        this.array[this.rear] = element;
        rear++;
        this.modCount++;
    }

    /**  
     * Adds the specified element to the front of this list. 
     *
     * @param element the element to be added to the front of this list    
     */
    @Override
    public void add(T element) {
        addToFront(element);
    }

    /**  
     * Adds the specified element after the first element of the list matching the specified target. 
     *
     * @param element the element to be added after the target
     * @param target  the target is the item that the element will be added after
     * @throws NoSuchElementException if target element is not in this list
     */
    @Override
    public void addAfter(T element, T target) {
        int targetIndex = -1;
        for (int i = 0; i < this.rear; i++) {
            if (this.array[i].equals(target)) { 
                targetIndex = i;
                break; 
            }
        }
        if(targetIndex == -1){
            throw new NoSuchElementException("Target element not found");
        }
        if(this.rear == this.array.length){
            arrayCapacityDoubler(this.array);
        }
        for(int j = this.rear; j > targetIndex + 1; j--){
            this.array[j] = this.array[j-1];
        }
        this.array[targetIndex + 1]= element;
        
        //update counters
        this.modCount++;
        this.rear++;
    }

    /**  
     * Inserts the specified element at the specified index. 
     * 
     * @param index   the index into the array to which the element is to be inserted.
     * @param element the element to be inserted into the array
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size)
     */
    @Override
    public void add(int index, T element) {

        this.modCount++;
        this.rear++;
    }

    /**  
     * Removes and returns the first element from this list. 
     * 
     * @return the first element from this list
     * @throws NoSuchElementException if list contains no elements
     */
    @Override
    public T removeFirst() {
        // TODO Auto-generated method stub
        return null;
    }

    /**  
     * Removes and returns the last element from this list. 
     *
     * @return the last element from this list
     * @throws NoSuchElementException if list contains no elements
     */
    @Override
    public T removeLast() {
        // TODO Auto-generated method stub
        return null;
    }
    
     /**  
     * Removes and returns the first element from the list matching the specified element.
     *
     * @param element the element to be removed from the list
     * @return removed element
     * @throws NoSuchElementException if element is not in this list
     */
    @Override
    public T remove(T element) {
        // TODO Auto-generated method stub
        return null;
    }

    /**  
     * Removes and returns the element at the specified index. 
     *
     * @param index the index of the element to be retrieved
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public T remove(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    /**  
     * Replace the element at the specified index with the given element. 
     *
     * @param index   the index of the element to replace
     * @param element the replacement element to be set into the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public void set(int index, T element) {
        if(index < 0 || index >= this.rear) {
            throw new IndexOutOfBoundsException();
        }
        this.array[index] = element;       
    }

    /**  
     * Returns a reference to the element at the specified index. 
     *
     * @param index  the index to which the reference is to be retrieved from
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public T get(int index) {
        if(index < 0 || index >= this.rear) {
            throw new IndexOutOfBoundsException();
        }
        return this.array[index];
    }

    /**  
     * Returns the index of the first element from the list matching the specified element. 
     *
     * @param element  the element for the index is to be retrieved
     * @return the integer index for this element or -1 if element is not in the list    
     */
    @Override
    public int indexOf(T element) {
        for(int i = 0; i<this.rear; i++){
            if(this.array[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    /**  
     * Returns a reference to the first element in this list. 
     *
     * @return a reference to the first element in this list
     * @throws NoSuchElementException if list contains no elements
     */
    @Override
    public T first() {
        if(this.rear == 0){
            throw new NoSuchElementException();
        }
        return this.array[0];
    }

    /**  
     * Returns a reference to the last element in this list. 
     *
     * @return a reference to the last element in this list
     * @throws NoSuchElementException if list contains no elements
     */
    @Override
    public T last() {
        if(this.rear == 0){
            throw new NoSuchElementException();
        }
        return this.array[this.rear-1];
    }

    /**  
     * Returns true if this list contains the specified target element. 
     *
     * @param target the target that is being sought in the list
     * @return true if the list contains this element, else false
     */
    @Override
    public boolean contains(T target) {
        for(int i = 0; i<this.rear; i++){
            if(this.array[i] == target){
                return true;
            }
        }
        return false;
    }

    /**  
     * Returns true if this list contains no elements. 
     *
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        if(this.rear == 0){
            return true;
        }
        return false;
    }

    /**  
     * Returns the number of elements in this list. 
     *
     * @return the integer representation of number of elements in this list
     */
    @Override
    public int size() {
        return this.rear;
    }

    /**  
     * Returns a string representation of this list. 
     *
     * @return a string representation of this list
     */
    @Override
    public String toString(){
        String formattedString = "Array List: \n";
        for(int i = 0; i<this.rear; i++){
            formattedString = formattedString + this.array[i] + " ";
        }
        return formattedString;
    }

    /**  
     * Returns an Iterator for the elements in this list. 
     *
     * @return an Iterator over the elements in this list
     */
    @Override
    public Iterator<T> iterator() {
        
        return new IUArrayIterator();
    }

    /**  
     * Returns a ListIterator for the elements in this list. 
     *
     * @return a ListIterator over the elements in this list
     *
     * @throws UnsupportedOperationException if not implemented
     */
    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("listIterator() is not implemented");
    }

        /**  
     * Returns a ListIterator for the elements in this list, with
     * the iterator positioned before the specified index. 
     *
     * @return a ListIterator over the elements in this list
     *
     * @throws UnsupportedOperationException if not implemented
     */
    @Override
    public ListIterator<T> listIterator(int startingIndex) {
        throw new UnsupportedOperationException("listIterator() is not implemented");
    }

    private class IUArrayIterator implements Iterator<T> {
        private int curIndex;
        private int lastReturnedIndex = -1;
        private int expectedModCount;

        public IUArrayIterator() {
            this.curIndex = 0;
            this.expectedModCount = modCount;

        }

        private void checkForConcurrentModification(){
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        public boolean hasNext() {
            checkForConcurrentModification();
            return curIndex < rear;

        }

        public T next() {
            checkForConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T element = array[curIndex];
            lastReturnedIndex = curIndex;
            return element;
        }

        @Override
        public void remove() {
            checkForConcurrentModification();
            if(lastReturnedIndex < 0){
                throw new IllegalStateException("next() has not been called or remove() was called after the last call to next()");
            }
            for(int i = lastReturnedIndex; i<rear-1; i++){
                array[i] = array[i+1];
            }
            array[rear-1] = null; //just incase 
            rear--;
            curIndex = lastReturnedIndex;
            lastReturnedIndex = -1;
            modCount++;
            expectedModCount++;
            
        }
    }






    
}