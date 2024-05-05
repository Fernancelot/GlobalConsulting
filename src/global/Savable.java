package global;

/**
 * The Savable interface represents objects that can be saved.
 * Classes that implement this interface provide a method to save the object's state.
 */
public interface Savable {

    /**
     * Saves the state of the specified object.
     *
     * @param obj the object whose state needs to be saved
     * @return true if the save operation was successful, false otherwise
     */
    boolean save(Object obj);
}

