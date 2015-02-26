package repositorio.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import modelo.Entidade;
import modelo.enuns.EnumStatus;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import repositorio.facade.DAO;
import excecoes.NegocioException;

/**
 * <p>
 * <b>Título:</b> HibernateDAO.java
 * </p>
 *
 * <p>
 * <b>Descrição:</b> Classe abstrata responsável por implementar os métodos da interface pai <code>DAO</code>, provendo algumas funcionalidades CRUD.
 * </p>
 *
 * Data de criação: 01/11/2014
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
@SuppressWarnings("unchecked")
public abstract class HibernateDAO<E extends Entidade> implements DAO<E> {

	/** Atributo manager. */
	@Inject
	private EntityManager manager;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 */
	public HibernateDAO() {

	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.coffeework.persistencia.dao.DAO#obter(java.io.Serializable)
	 */
	@Override
	public E obter(final Serializable identificador) {

		if (identificador != null) {

			final Class<E> tipoEntidade = this.obterEntidadePersistente();

			return this.getManager().find(tipoEntidade, identificador);
		}

		return null;
	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.coffeework.persistencia.dao.DAO#salvar(br.com.coffeework.modelo.entidade.Entidade)
	 */
	@Override
	public void salvar(final E entidade) throws NegocioException {

		this.getManager().persist(entidade);
	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.coffeework.persistencia.dao.DAO#mesclar(br.com.coffeework.modelo.entidade.Entidade)
	 */
	@Override
	public void mesclar(final E entidade) throws NegocioException {

		this.getManager().merge(entidade);
	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.coffeework.persistencia.dao.DAO#remover(br.com.coffeework.modelo.entidade.Entidade)
	 */
	@Override
	public void remover(final E entidade) throws NegocioException {

		this.getManager().remove(entidade);
	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.coffeework.persistencia.dao.DAO#consultar(br.com.coffeework.modelo.entidade.Entidade)
	 */
	@Override
	public Collection<E> consultar(final E entidade) {

		final Criteria criteria = this.obterCriteria();

		if (entidade != null) {

			final Example example = Example.create(entidade);

			example.enableLike(MatchMode.START);

			example.excludeZeroes();

			criteria.add(example);
		}

		return this.consultar(criteria);
	}

	/**
	 * Efetua a consulta de um criteria.
	 * 
	 * @author marcosbuganeme
	 * 
	 * @param criteria
	 *            Critéria que será executada.
	 * 
	 * @return Coleção de entidades.
	 */
	protected Collection<E> consultar(final Criteria criteria) {

		final Collection<E> colecao = criteria.list();

		return colecao;
	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.coffeework.persistencia.dao.DAO#listar()
	 */
	@Override
	public Collection<E> listar() {

		final Criteria criteria = this.obterCriteria();

		criteria.add(Restrictions.eq("status", EnumStatus.ATIVO));

		return criteria.list();
	}

	/**
	 * Descrição Padrão: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see br.com.coffeework.persistencia.dao.DAO#atualizar(br.com.coffeework.modelo.entidade.Entidade)
	 */
	@Override
	public void atualizar(final E entidade) {

		this.getManager().refresh(entidade);
	}

	/**
	 * Método responsável por obter um criteria para uma classe persistente.
	 *
	 * @author marcosbuganeme
	 * 
	 * @author leides
	 *
	 * @return <code>um novo criteria para a classe persistente</code>.
	 */
	protected Criteria obterCriteria() {

		final Class<E> classePersistente = this.obterEntidadePersistente();

		final Criteria criteria = this.getSession().createCriteria(classePersistente);

		return criteria;
	}

	/**
	 * Método responsável por obter a classe persistente.
	 *
	 * @author marcosbuganeme
	 * 
	 * @author leides
	 *
	 * @return <code>classe persistente</code>
	 */
	private Class<E> obterEntidadePersistente() {

		final Type[] tipoEntidade = ( (ParameterizedType) this.getClass().getGenericSuperclass() ).getActualTypeArguments();

		return (Class<E>) tipoEntidade[0];
	}

	/**
	 * Retorna o valor do atributo <code>manager</code>
	 *
	 * @return <code>EntityManager</code>
	 */
	protected EntityManager getManager() {

		return this.manager;
	}

	/**
	 * Retorna o valor do atributo <code>session</code>
	 *
	 * @return <code>Session</code>
	 */
	protected Session getSession() {

		return this.getManager().unwrap(Session.class);
	}

}
