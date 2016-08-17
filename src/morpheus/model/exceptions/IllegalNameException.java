package morpheus.model.exceptions;

public class IllegalNameException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 6772850846525197424L;
    
    /**
     * Print on the console the error message.
     */
    public void errorMessage() {
            System.out.println("This name is already present");
    }
    
    /**
     * Returns the error Message.
     * @return
     *              the error message
     */
    public String getErrorMessage() {
            return "This name is already present";
    }
}
