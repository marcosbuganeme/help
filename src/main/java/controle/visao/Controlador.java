package controle.visao;

import java.io.Serializable;
import java.util.ResourceBundle;

import controle.bundle.ResourceBundleArquitetura;
import controle.bundle.ResourceBundleArquiteturaFactory;

/**
 * <p>
 * <b>Título:</b> Controlador.java
 * </p>
 *
 * <p>
 * <b>Descrição:</b> Classe abstrata responsável pelo fluxo de navegação do sistema.
 * </p>
 *
 * Data de criação: 02/11/2014
 *
 * @author marcosbuganeme
 *
 * @version 1.0.0
 */
public abstract class Controlador implements Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8634185887361500541L;

	/**
	 * Método responsável por navegar para a página inicial de um ECDU selecionado.
	 *
	 * @author marcosbuganeme
	 * 
	 * @return <i>página inicial</i>.
	 */
	public abstract String abreIniciar();

	/**
	 * Método responsável por resetar os dados de uma entidade selecionada.
	 *
	 * @author marcosbuganeme
	 */
	public abstract void limparDadosFormulario();

	/**
	 * Método responsável por retornar a mensagem vinculada a chave passada como parâmetro.
	 * 
	 * @author marcosbuganeme
	 * 
	 * @param keyMessage
	 *            - Chave da mensagem.
	 * 
	 * @return <i>mensagem a ser exibida na tela</i>.
	 */
	public String getMessage(final String keyMessage) {

		return this.getBundle().getString(keyMessage);
	}

	/**
	 * Retorna o valor do atributo bundle.
	 * 
	 * @return <code>ResourceBundle</code> Arquivo de mensagens da arquitetura.
	 */
	protected ResourceBundle getBundle() {

		return ResourceBundleArquiteturaFactory.getBundle(this.getResourceBundleFactory());
	}

	/**
	 * Obtem o ResourceBundleFactory para o controlador
	 * 
	 * @return ResourceBundleFactory para o controlador
	 * 
	 * @see ResourceBundleArquiteturaFactory
	 */
	protected Class<? extends ResourceBundleArquiteturaFactory> getResourceBundleFactory() {

		return ResourceBundleArquitetura.class;
	}
}
