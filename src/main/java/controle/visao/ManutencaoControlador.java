package controle.visao;

import modelo.Entidade;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.jsf.UtilJSF;
import excecoes.NegocioException;

/**
 * <p>
 * <b>Título:</b> ManutencaoControlador.java
 * </p>
 *
 * <p>
 * <b>Descrição:</b> Classe responsável por prover funções pré estabelecidadas para recuperação e persistência de dados.
 * </p>
 *
 * Data de criação: 02/11/2014
 *
 * @author marcosbuganeme
 * 
 * @author leides
 * 
 * @param <E>
 *            - Entidade que será manipulada pela classe concreta.
 *
 * @version 1.0.0
 */
public abstract class ManutencaoControlador<E extends Entidade> extends ConsultaControlador<E> {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -3930469472566435845L;

	/** Constante LOG. */
	private static final Log LOG = LogFactory.getLog(ManutencaoControlador.class.getSimpleName());

	/** Constante MSG_SALVAR_SUCESSO. */
	private static final String MSG_SALVAR_SUCESSO = "registro.salvo.sucesso";

	/** Constante MSG_SALVAR_SUCESSO. */
	private static final String MSG_ALTERAR_SUCESSO = "registro.editado.sucesso";

	/** Constante MSG_EXCLUIR_SUCESSO. */
	private static final String MSG_EXCLUIR_SUCESSO = "registro.removido.sucesso";

	/**
	 * Método responsável por navegar para a página de inclusão de um ECDU.
	 *
	 * @author marcosbuganeme
	 *
	 * @author leides
	 * 
	 * @return <i>página de inclusão</i>.
	 */
	public abstract String abreIncluir();

	/**
	 * Método responsável por persistir o objeto parametrizado na base de dados.
	 *
	 * @author marcosbuganeme
	 * 
	 * @author leides
	 *
	 * @param entidade
	 *            - objeto que será persistido.
	 * 
	 * @throws NegocioException
	 */
	public void salvar() {

		try {

			this.getService().salvar(this.getFormulario().getEntidade());

			final String mensagemSalvar = this.getMessage(ManutencaoControlador.MSG_SALVAR_SUCESSO);

			UtilJSF.addMensagemInfo(mensagemSalvar);

			this.limparDadosFormulario();

		} catch (final NegocioException negocioException) {

			UtilJSF.addMensagemError(this.getMessage(negocioException.getMessage()));

			ManutencaoControlador.LOG.error("ERRO MÉTODO SALVAR() " + this.getClass().getSimpleName(), negocioException.getCause());
		}
	}

	/**
	 * Método responsável por mesclar um objeto (Salvar e/ou alterar). <br>
	 * Merge: Verifica se o objeto existe na base de dados, caso exista, ele altera, caso não exista, ele salva.
	 *
	 * @author marcosbuganeme
	 * 
	 * @author leides
	 *
	 * @param entidade
	 *            - objeto que será persistido/alterado.
	 */
	public void mesclar() {

		try {

			this.getService().mesclar(this.getFormulario().getEntidade());

			final String mensagemMesclar = this.getMessage(ManutencaoControlador.MSG_ALTERAR_SUCESSO);

			UtilJSF.addMensagemInfo(mensagemMesclar);

			this.limparDadosFormulario();

		} catch (final NegocioException negocioException) {

			UtilJSF.addMensagemError(this.getMessage(negocioException.getMessage()));

			ManutencaoControlador.LOG.error("ERRO MÉTODO MESCLAR()" + this.getClass().getSimpleName(), negocioException.getCause());
		}
	}

	/**
	 * Método responsável por remover um objeto da base de dados.
	 *
	 * @author marcosbuganeme
	 * 
	 * @author leides
	 *
	 * @param entidade
	 *            - objeto que será persistido/alterado.
	 */
	public void remover() {

		try {

			this.getService().remover(this.getFormulario().getEntidade());

			this.getFormulario().getColecaoEntidades().remove(this.getFormulario().getEntidade());

			final String mensagemExcluir = this.getMessage(ManutencaoControlador.MSG_EXCLUIR_SUCESSO);

			UtilJSF.addMensagemInfo(mensagemExcluir);

		} catch (final NegocioException negocioException) {

			UtilJSF.addMensagemError(this.getMessage(negocioException.getMessage()));

			ManutencaoControlador.LOG.error("ERRO MÉTODO REMOVER()" + this.getClass().getSimpleName(), negocioException.getCause());
		}
	}

}
