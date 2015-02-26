package servico.impl;

import java.io.Serializable;
import java.util.Collection;

import modelo.Entidade;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import repositorio.facade.DAO;
import servico.facade.ServiceFacade;
import util.jpa.Transacao;
import excecoes.NegocioException;
import excecoes.RegistroNaoExisteException;

/**
 * <p>
 * <b>Título:</b> Service.java
 * </p>
 *
 * <p>
 * <b>Descrição:</b> Classe abstrata responsável por implementar a interface <code>ServiceFacade</code>.
 * </p>
 *
 * Data de criação: 01/11/2014
 *
 * @author marcosbuganeme
 * 
 * @param <E>
 *            - Entidade que será manipulada pela classe concreta.
 *
 * @version 1.0.0
 */
public abstract class Service<E extends Entidade> implements ServiceFacade<E> {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -32108270759546135L;

	/** Constante LOG. */
	private static final Log LOG = LogFactory.getLog(Service.class);

	/** Constante MENSAGEM_REGISTRO_NAO_EXISTE. */
	private static final String MENSAGEM_REGISTRO_NAO_EXISTE = "validacao.registroinexistente";

	/**
	 * Método responsável por obter o dao da entidade.
	 *
	 * @author marcosbuganeme
	 * 
	 * @return <i>dao</i>.
	 */
	protected abstract DAO<E> getDao();

	@Override
	public E obter(final Serializable identificador) throws NegocioException {

		final E entidadeObtida = this.getDao().obter(identificador);

		if (entidadeObtida == null) {

			LOG.error("Registro não encontrado");

			throw new RegistroNaoExisteException(Service.MENSAGEM_REGISTRO_NAO_EXISTE);
		}

		return entidadeObtida;
	}

	@Transacao
	@Override
	public void salvar(final E entidade) throws NegocioException {

		if (entidade != null) {

			this.getDao().salvar(entidade);
		}
	}

	@Transacao
	@Override
	public void mesclar(final E entidade) throws NegocioException {

		if (entidade != null) {

			this.getDao().mesclar(entidade);
		}
	}

	@Transacao
	@Override
	public void remover(final E entidade) throws NegocioException {

		final E entidadeobtida = this.obter(entidade.getId());

		this.getDao().remover(entidadeobtida);
	}

	@Override
	public Collection<E> consultar(final E entidade) {

		return this.getDao().consultar(entidade);
	}

	@Override
	public Collection<E> listar() {

		return this.getDao().listar();
	}

	@Override
	public void atualizar(final E entidade) {

		this.getDao().atualizar(entidade);
	}

}
