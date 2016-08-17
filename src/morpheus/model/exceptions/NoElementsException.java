package morpheus.model.exceptions;

/**
 * 
 * @author jacopo
 *
 */
public class NoElementsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2643686702336723663L;

	/**
	 * Print on the console the error message.
	 */
	public void errorMessage() {
		System.out.println("No such Elements");
	}
	
	/**
	 * Returns the error Message.
	 * @return
	 * 		the error message
	 */
	public String getErrorMessage() {
		return "No such Elements";
	}
}
