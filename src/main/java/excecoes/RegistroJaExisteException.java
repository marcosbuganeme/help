package excecoes;

/**
 * <p>
 * <b>Título:</b> RegistroJaExisteException.java
 * </p>
 *
 * <p>
 * <b>Descrição:</b> Classe responsável por tratar as exceções que quebrem as regras de registros já existentes.
 * </p>
 *
 * Data de criação: 26/02/2015
 *
 * @author marcosbuganeme
 *
 * @version 1.0.0
 */
public class RegistroJaExisteException extends NegocioException {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 7030680613281457859L;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public RegistroJaExisteException() {

	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param mensagem
	 */
	public RegistroJaExisteException( final String mensagem ) {

		super(mensagem);
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param mensagem
	 * 
	 * @param causa
	 */
	public RegistroJaExisteException( final String mensagem, final Throwable causa ) {

		super(mensagem, causa);
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param causa
	 */
	public RegistroJaExisteException( final Throwable causa ) {

		super(causa);
	}
}
