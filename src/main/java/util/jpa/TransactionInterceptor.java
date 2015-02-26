package util.jpa;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * <b>Título:</b> TransactionInterceptor.java
 * </p>
 *
 * <p>
 * <b>Descrição:</b> Classe responsável pela interceptação de transações.
 * </p>
 *
 * Data de criação: 02/11/2014
 *
 * @author marcosbuganeme
 *
 * @version 1.0.0
 */
@Interceptor
@Transacao
@Priority(Interceptor.Priority.APPLICATION + 1)
public class TransactionInterceptor implements Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -1495886130448620233L;

	/** Constante LOG. */
	private static final Log LOG = LogFactory.getLog(TransactionInterceptor.class.getSimpleName());

	/** Atributo manager. */
	@Inject
	private EntityManager manager;

	/**
	 * Método responsável por interceptar em tempo de execução uma chamada de contexto que envolve persistência.
	 *
	 * @author marcosbuganeme
	 *
	 * @param context
	 *            - contexto invocado que contêm a árvore de objetos.
	 * 
	 * @return <i>a árvore de valores do próximo método que será executado</i>.
	 * 
	 * @throws Exception
	 */
	@AroundInvoke
	public Object invocarContexto(final InvocationContext context) throws Exception {

		final EntityTransaction entidadeTransacional = this.manager.getTransaction();

		boolean criador = false;

		try {

			if (!entidadeTransacional.isActive()) {

				entidadeTransacional.begin();

				entidadeTransacional.rollback();

				entidadeTransacional.begin();

				criador = true;
			}

			return context.proceed();

		} catch (final Exception excecao) {

			if (entidadeTransacional != null && criador) {

				entidadeTransacional.rollback();
			}

			TransactionInterceptor.getLog().error(" CAUSA DA EXCEÇÃO ::: " + excecao.getCause());

			throw excecao;

		} finally {

			if (entidadeTransacional != null && entidadeTransacional.isActive() && criador) {

				entidadeTransacional.commit();
			}
		}
	}

	/**
	 * Retorna o valor do atributo <code>log</code>.
	 *
	 * @return <i>Log</i>
	 */
	private static final Log getLog() {

		return TransactionInterceptor.LOG;
	}

}
