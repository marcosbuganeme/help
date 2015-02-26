package excecoes;

/**
 * <p>
 * <b>Título:</b> RegistroNaoExisteException.java
 * </p>
 *
 * <p>
 * <b>Descrição:</b> Classe responsável por tratar as exceções de registros que não existem.
 * </p>
 *
 * Data de criação: 02/11/2014
 *
 * @author marcosbuganeme
 *
 * @version 1.0.0
 */
public class RegistroNaoExisteException extends NegocioException {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8260935911915999440L;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public RegistroNaoExisteException() {

		super();
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param mensagem
	 */
	public RegistroNaoExisteException( final String mensagem ) {

		super(mensagem);
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param causa
	 */
	public RegistroNaoExisteException( final Throwable causa ) {

		super(causa);
	}

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param mensagem
	 * @param causa
	 */
	public RegistroNaoExisteException( final String mensagem, final Throwable causa ) {

		super(causa);
	}
}
