package modelo.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import modelo.Entidade;

/**
 * <p>
 * <b>Título:</b> EntidadeHelp.java
 * </p>
 *
 * <p>
 * <b>Descrição:</b> Classe abstrata responsável por implementar a interface <code>Entidade</code> e propagar os atributos em comuns as demais entidades.
 * </p>
 *
 * Data de criação: 01/11/2014
 *
 * @author marcosbuganeme
 *
 * @version 1.0.0
 */
@MappedSuperclass
public class EntidadeHelp implements Entidade {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4665623078570622661L;

	/** Atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** Atributo dataCriacao. */
	@Temporal(TemporalType.DATE)
	@Column(name = "dt_criacao")
	private Date dataCriacao;

	/** Atributo dataModificacao. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_modificacao")
	private Date dataModificacao;

	/**
	 * Método responsável por executar o ciclo de vida da JPA e persistir as datas de acordo com a regra de negócio.
	 *
	 * @author marcosbuganeme
	 */
	@PrePersist
	@PreUpdate
	private void executar() {

		if (this.dataCriacao == null) {

			this.dataCriacao = new Date();
		}

		this.dataModificacao = new Date();
	}

	@Override
	public Serializable getId() {

		return this.id;
	}

	@Override
	public boolean isNovoRegistro() {

		return this.getId() == null;
	}

	/**
	 * Retorna o valor do atributo <code>dataCriacao</code>
	 *
	 * @return <code>Date</code>
	 */
	public Date getDataCriacao() {

		return dataCriacao;
	}

	/**
	 * Retorna o valor do atributo <code>dataModificacao</code>
	 *
	 * @return <code>Date</code>
	 */
	public Date getDataModificacao() {

		return dataModificacao;
	}

}
