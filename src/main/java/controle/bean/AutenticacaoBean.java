package controle.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * <p>
 * <b>Título:</b> AutenticacaoBean.java
 * </p>
 *
 * <p>
 * <b>Descrição:</b>
 * </p>
 *
 * Data de criação: 28/02/2015
 *
 * @author marcosbuganeme
 *
 * @version 1.0.0
 */
@ManagedBean
public class AutenticacaoBean implements Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -8229431929087353423L;

	/** Atributo email. */
	private String email;

	/** Atributo senha. */
	private String senha;

	public String autenticar() {

		if (!( this.email.equals("marcos.after@gmail.com") && this.senha.equals("123456") )) {

			final FacesContext context = FacesContext.getCurrentInstance();

			final FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Autenticação falhou", "E-mail e/ou senha inválidos");

			context.addMessage(null, fm);

			return null;
		}

		return "/paginas/restrito/index";
	}

	/**
	 * Retorna o valor do atributo <code>email</code>
	 *
	 * @return <code>String</code>
	 */
	public String getEmail() {

		return email;
	}

	/**
	 * Define o valor do atributo <code>email</code>.
	 *
	 * @param email
	 */
	public void setEmail(String email) {

		this.email = email;
	}

	/**
	 * Retorna o valor do atributo <code>senha</code>
	 *
	 * @return <code>String</code>
	 */
	public String getSenha() {

		return senha;
	}

	/**
	 * Define o valor do atributo <code>senha</code>.
	 *
	 * @param senha
	 */
	public void setSenha(String senha) {

		this.senha = senha;
	}

}
