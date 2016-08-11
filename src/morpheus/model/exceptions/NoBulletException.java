package morpheus.model.exceptions;

/**
 * 
 * @author jacopo
 *
 */
public class NoBulletException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2643686702336723663L;

	/**
	 * Print on the console the error message.
	 */
	public void errorMessage() {
		System.out.println("You haven't bullet");
	}
	
	/**
	 * Returns the error Message.
	 * @return
	 * 		the error message
	 */
	public String getErrorMessage() {
		return "You haven't bullet";
	}
}
