package modelo.enuns;

/**
 * <p>
 * <b>Título:</b> EnumStatus.java
 * </p>
 *
 * <p>
 * <b>Descrição:</b> Enumerator responsável por representar o status do registro no sistema.
 * </p>
 *
 * Data de criação: 01/11/2014
 *
 * @author marcosbuganeme
 *
 * @version 1.0.0
 */
public enum EnumStatus {

	/** Constante ATIVO. */
	ATIVO(1, "ativo"),

	/** Constante INATIVO. */
	INATIVO(2, "inativo");

	/** Atributo codigo. */
	private final int codigo;

	/** Atributo descricao. */
	private final String descricao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param codigo
	 * @param descricao
	 */
	private EnumStatus( final int codigo, final String descricao ) {

		this.codigo = codigo;

		this.descricao = descricao;
	}

	/**
	 * Retorna o valor do atributo <code>codigo</code>
	 *
	 * @return <code>int</code>
	 */
	public final int getCodigo() {

		return this.codigo;
	}

	/**
	 * Retorna o valor do atributo <code>descricao</code>
	 *
	 * @return <code>String</code>
	 */
	public final String getDescricao() {

		return this.descricao;
	}

}
