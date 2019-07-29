package Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FinalizarCadastroExamePage extends BasePage {

	public FinalizarCadastroExamePage(WebDriver driver) {
		super(driver);
	}

	// Instancia��o de biblioteca para a��es JS (Scroll lock utilizado no teste)
	JavascriptExecutor js = (JavascriptExecutor) driver;

	// Campo para altera��o do nome do Exame (caso desejar)
	public FinalizarCadastroExamePage nomeSimulado(String nomeSimulado) {
		aguardaCarregarElemento(By.id("mock_name")).sendKeys(nomeSimulado);
	return this;
	}

	// Permite ocultar a caixa de pesquisa de satisfa��o e exibila quandoinibida
	// Por padr�o da aplica��o a mesma � axibida e o primeiro click ir�
	// ocult�-la
	public FinalizarCadastroExamePage exibeOcultaPesquisaSatisfacao(){
		js.executeScript("window.scrollBy(0,600)");
		aguardaCarregarElemento(
				By.cssSelector("a[class='_hj-f5b2a1eb-9b07_widget_open_close _hj-f5b2a1eb-9b07_action_toggle_widget']"))
				.click();
	return this;
	}

	// Altera a data de in�cio e termino
	// Abre o calend�rio da data de in�cio
	public FinalizarCadastroExamePage calendarioDataInicio() {
		// Desce um pouco a tela antes de clicar no campo para exibir o
		// calend�rio
		js.executeScript("window.scrollBy(0,500)");
		// Clica no campo para abrir o calend�rio
		aguardaCarregarElemento(By.id("start_time__date")).click();

		// Clica na lista dos meses
		aguardaCarregarElemento(By.cssSelector(
				"#start_time__date_root > div > div > div > div > div.picker__calendar-container > div > select.picker__select--month.browser-default"))
				.click();

		// Seleciona o m�s
		aguardaCarregarElemento(By.cssSelector(
				"#start_time__date_root > div > div > div > div > div.picker__calendar-container > div > select.picker__select--month.browser-default > option:nth-child(7)"))
				.click();

		// Seleciona o dia
		aguardaCarregarElemento(By.cssSelector("#start_time__date_table > tbody > tr:nth-child(1) > td:nth-child(2) > div"))
				.click();

		// Clica em fechar o calend�rio
		aguardaCarregarElemento(By.cssSelector(
				"#start_time__date_root > div > div > div > div > div.picker__footer > button.btn-flat.picker__close"))
				.click();
	return this;
	}

	// Seleciona a data de termino
	// Abre o calend�rio da data de termino
	public FinalizarCadastroExamePage calendarioDataTermino() throws InterruptedException {
		Thread.sleep(1000);
		// Clica para exibir o calend�rio
		aguardaCarregarElemento(By.id("end_time__date")).click();

		// Clica para abrir a lista dos meses
		aguardaCarregarElemento(By.cssSelector(
				"#end_time__date_root > div > div > div > div > div.picker__calendar-container > div > select.picker__select--month.browser-default"))
				.click();

		// Seleciona o m�s
		aguardaCarregarElemento(By.cssSelector(
				"#end_time__date_root > div > div > div > div > div.picker__calendar-container > div > select.picker__select--month.browser-default > option:nth-child(8)"))
				.click();

		// Seleciona o dia
		aguardaCarregarElemento(By.cssSelector("#end_time__date_table > tbody > tr:nth-child(1) > td:nth-child(5) > div"))
				.click();

		// Clica no bot�o fechar (para fechar o calend�rio)
		aguardaCarregarElemento(By.cssSelector(
				"#end_time__date_root > div > div > div > div > div.picker__footer > button.btn-flat.picker__close"))
				.click();
	return this;
	}

	// Tempo para realiza��o da atividade (Horas e minutos)
	public FinalizarCadastroExamePage tempoDuracaoExame(int hora, int minutos) {
		aguardaCarregarElemento(By.name("duration_hours")).clear();
		aguardaCarregarElemento(By.name("duration_hours")).sendKeys(String.valueOf(hora));
		aguardaCarregarElemento(By.name("duration_minutes")).clear();
		aguardaCarregarElemento(By.name("duration_minutes")).sendKeys(String.valueOf(minutos));
	return this;
	}

	// Marca/Desmarca o check Gostaria de exibir para o aluno as quest�es
	// corrigidas
	public FinalizarCadastroExamePage checkExibirQuestoesCorrigidasParaOsAlunos() {
		aguardaCarregarElemento(By.cssSelector("#activities-finish-form > div:nth-child(7) > div > div > p > label"))
				.click();
	return this;
	}

	public FinalizarCadastroExamePage contadorQuestoesMarcadas(String quantidadeQuestoes){
		js.executeScript("window.scrollBy(0,800)");
		WebElement contadorQuestoes = driver.findElement(By.cssSelector(
				"#activities-finish-form > div.row.activities-finish--row.course-info--col > div.col.s12.m6.l8.course-info__item--large.activities-finish--col > div > div.added-questions__title"));
		String quantidadeQuestoesMarcadas = contadorQuestoes.getText();
		Assert.assertEquals(quantidadeQuestoes , quantidadeQuestoesMarcadas);
	return this;
	}

	// Bot�o para salvar o exame como rascunho
	public FinalizarCadastroExamePage salvarComoRascunho() {
		aguardaCarregarElemento(By.id("save_as_draft_button")).click();
	return this;
	}

	// Bot�o para publica��o do exame
	public FinalizarCadastroExamePage publicarExame() {
		aguardaCarregarElemento(By.id("publish_button")).click();
	return this;
	}

	public FinalizarCadastroExamePage popUpRascunhoSalvoComSucesso(String mensagemParam){
		WebElement mensagemPopUp = driver.findElement(By.id("toast-container"));
		String mensagem = mensagemPopUp.getText();
		Assert.assertEquals(mensagemParam, mensagem);
	return this;
	}
}
