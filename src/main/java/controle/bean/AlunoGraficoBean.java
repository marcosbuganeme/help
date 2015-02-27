package controle.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 * <p>
 * <b>Título:</b> AlunoGraficoBean.java
 * </p>
 *
 * <p>
 * <b>Descrição:</b>
 * </p>
 *
 * Data de criação: 26/02/2015
 *
 * @author marcosbuganeme
 *
 * @version 1.0.0
 */
@ManagedBean
public class AlunoGraficoBean implements Serializable {

	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -4262663703321813733L;

	/** Atributo modeloGraficoLinha. */
	private LineChartModel modeloGraficoLinha;

	/**
	 * Método responsável por iniciar os dados do controlador.
	 *
	 * @author marcosbuganeme
	 */
	@PostConstruct
	public void iniciarDados() {

		this.criarGraficoLinha();
	}

	private void criarGraficoLinha() {

		this.modeloGraficoLinha = this.preencherDadosGrafico();
		this.modeloGraficoLinha.setTitle("Quantidade de cursos vendidos por mês");
		this.modeloGraficoLinha.setLegendPosition("e");
		this.modeloGraficoLinha.setShowPointLabels(true);
		this.modeloGraficoLinha.getAxes().put(AxisType.X, new CategoryAxis("Meses"));
		final Axis yAxis = this.modeloGraficoLinha.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade de cursos");
		yAxis.setMin(0);
		yAxis.setMax(20);
	}

	private LineChartModel preencherDadosGrafico() {

		final LineChartModel grafico = new LineChartModel();

		final ChartSeries javaOO = new ChartSeries();
		javaOO.setLabel("Java Orientado a Objetos");
		javaOO.set(new String("Novembro - 2014"), 1);
		javaOO.set(new String("Dezembro - 2014"), 4);
		javaOO.set(new String("Janeiro - 2015"), 5);
		javaOO.set(new String("Fevereiro - 2015"), 3);
		javaOO.set(new String("Março - 2015"), 2);

		final ChartSeries javaEE = new ChartSeries();
		javaEE.setLabel("Java EE");
		javaEE.set(new String("Novembro - 2014"), 4);
		javaEE.set(new String("Dezembro - 2014"), 5);
		javaEE.set(new String("Janeiro - 2015"), 12);
		javaEE.set(new String("Fevereiro - 2015"), 9);
		javaEE.set(new String("Março - 2015"), 6);

		final ChartSeries logicaProgramacao = new ChartSeries();
		logicaProgramacao.setLabel("Lógica de programação");
		logicaProgramacao.set(new String("Novembro - 2014"), 6);
		logicaProgramacao.set(new String("Dezembro - 2014"), 5);
		logicaProgramacao.set(new String("Janeiro - 2015"), 5);
		logicaProgramacao.set(new String("Fevereiro - 2015"), 4);
		logicaProgramacao.set(new String("Março - 2015"), 3);

		final ChartSeries infraestrutura = new ChartSeries();
		infraestrutura.setLabel("Infra estrutura");
		infraestrutura.set(new String("Novembro - 2014"), 2);
		infraestrutura.set(new String("Dezembro - 2014"), 1);
		infraestrutura.set(new String("Janeiro - 2015"), 4);
		infraestrutura.set(new String("Fevereiro - 2015"), 2);
		infraestrutura.set(new String("Março - 2015"), 1);

		grafico.addSeries(javaOO);
		grafico.addSeries(javaEE);
		grafico.addSeries(infraestrutura);
		grafico.addSeries(logicaProgramacao);

		return grafico;
	}

	/**
	 * Retorna o valor do atributo <code>modeloGraficoLinha</code>
	 *
	 * @return <code>LineChartModel</code>
	 */
	public LineChartModel getModeloGraficoLinha() {

		return this.modeloGraficoLinha;
	}

}
