import java.util.Arrays;

/**
 * An array-based implementation of a list.
 */
public class ArrayList<T> {
    private static final int INITIAL_SIZE = 16;
    private static final int GROWTH_FACTOR = 2;
    
    private Object[] data;
    private int size;
    
    public ArrayList() {
        data = new Object[INITIAL_SIZE];
        size = 0;
    }
    
    public ArrayList(T arr[]) {
    	data = arr;
    	size = arr.length;
    }
    
    /**
     * Ensures that the backing array has sufficient capacity to hold
     * additional elements, growing the array by a multiplicative
     * factor if it is full.
     * 
     * pre: data.length * GROWTH_FACTOR <= Integer.MAX_INTEGER
     */
    public void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * GROWTH_FACTOR);
        }
    }
    
    /**
     * Adds the given element to the end of the list.
     * @param v the element to add.
     */
    public void add(T v) {
        ensureCapacity();
        data[size++] = v;
    }
    
    /**
     * @return the length of the list.
     */
    public int size() { return size; }
    
    /**
     * @param v the element to search for
     * @return the index of the first occurrence of v in the list or -1 if v
     *         is not found in the list.
     */
    public int indexOf(T v) {
        for (int i = 0; i < size; i++) {
            if (v.equals(data[i])) { return i; }
        }
        return -1;
    }
    
    /**
     * @return the element at the ith index of the list
     * @throws IndexOutOfBounds if i is not a valid index into the list
     */
    public T get(int i) {
    	if (i < 0 || i >= size) { throw new IllegalArgumentException(); }
    	@SuppressWarnings("unchecked")
    	T ret = (T) data[i];
        return ret;
    }
    
    /**
     * @param x the value to be inserted
     * @param index the place where x should be inserted
     */
    public void insert(T x, int index) {
    	
    	if (index < 0 || index >= size) { 
    		throw new IndexOutOfBoundsException();
    	} else {
    		ensureCapacity();
    		for (int i = size; i > index; i--) {
                data[i] = data[i-1];
            }
    		data[index] = x;
    		size++;
    	}
    	
    	
    }

    /**
     * @param i the index of the element to remove from the list
     * @throws IndexOutOfBounds if i is not a valid index into the list
     * @return the value that was deleted
     */
    public T remove(int index) {
        if (0 <= index && index < size) {
        	@SuppressWarnings("unchecked")
        	T ret = (T) data[index];
            for (int i = index; i < size; i++) {
                data[i] = data[i+1];
            }
            size--;
            return ret;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    
    /**
     * Clears data and sets size to 0.
     */
    public void clear() {
    	data = new Object[INITIAL_SIZE];
    	size = 0;
    }
    
    /**
     * @return the string representation of this list
     */
    public String toString() {
        String ret = "[";
        if (size == 1) {
            ret += data[0];
        } else if (size > 1) {
            ret += data[0];
            for (int i = 1; i < size; i++) {
                ret += ", " + data[i];
            }
        }
        return ret + "]";
    }
}
