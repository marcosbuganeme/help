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
		this.modeloGraficoLinha.setAnimate(true);
		this.modeloGraficoLinha.setShowPointLabels(true);
		this.modeloGraficoLinha.getAxes().put(AxisType.X, new CategoryAxis("Meses"));
		final Axis yAxis = this.modeloGraficoLinha.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade de cursos");
		yAxis.setMin(0);
		yAxis.setMax(25);
	}

	private LineChartModel preencherDadosGrafico() {

		final LineChartModel grafico = new LineChartModel();

		final ChartSeries backEnd = new ChartSeries();
		backEnd.setLabel("BEND");
		backEnd.set(new String("Novembro - 2014"), new Double(1.0));
		backEnd.set(new String("Dezembro - 2014"), 2);
		backEnd.set(new String("Janeiro - 2015"), 3);
		backEnd.set(new String("Fevereiro - 2015"), 4);
		backEnd.set(new String("Março - 2015"), 5);

		final ChartSeries frontEnd = new ChartSeries();
		frontEnd.setLabel("FEND");
		frontEnd.set(new String("Novembro - 2014"), 3);
		frontEnd.set(new String("Dezembro - 2014"), 5);
		frontEnd.set(new String("Janeiro - 2015"), 7);
		frontEnd.set(new String("Fevereiro - 2015"), 9);
		frontEnd.set(new String("Março - 2015"), 11);

		final ChartSeries dba = new ChartSeries();
		dba.setLabel("DBA");
		dba.set(new String("Novembro - 2014"), 5);
		dba.set(new String("Dezembro - 2014"), 9);
		dba.set(new String("Janeiro - 2015"), 13);
		dba.set(new String("Fevereiro - 2015"), 17);
		dba.set(new String("Março - 2015"), 21);

		final ChartSeries infra = new ChartSeries();
		infra.setLabel("INFRA");
		infra.set(new String("Novembro - 2014"), 8);
		infra.set(new String("Dezembro - 2014"), 7);
		infra.set(new String("Janeiro - 2015"), 6);
		infra.set(new String("Fevereiro - 2015"), 5);
		infra.set(new String("Março - 2015"), 4);

		grafico.addSeries(backEnd);
		grafico.addSeries(dba);
		grafico.addSeries(frontEnd);
		grafico.addSeries(infra);

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
