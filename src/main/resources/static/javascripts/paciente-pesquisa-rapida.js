Saude = Saude || {};

Saude.PesquisaRapidaPaciente = (function() {
	
	function PesquisaRapidaPaciente() {
		this.pesquisaRapidaPacientesModal = $('#pesquisaRapidaPacientes');
		this.nomeInput = $('#nomePacienteModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-paciente-btn'); 
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaPacientes');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-paciente').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaPaciente.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaPacientesModal.on('shown.bs.modal',onModalShow.bind(this));

	}
	
	function onModalShow() {
		this.nomeInput.focus();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaPacientesModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeInput.val()
			}, 
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onPesquisaConcluida(resultado) {
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		this.mensagemErro.addClass('hidden');
		
		var tabelaPacientePesquisaRapida = new Saude.TabelaPacientePesquisaRapida(this.pesquisaRapidaPacientesModal);
		tabelaPacientePesquisaRapida.iniciar();
	} 
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaPaciente;
	
}());

Saude.TabelaPacientePesquisaRapida = (function() {
	
	function TabelaPacientePesquisaRapida(modal) {
		this.modalPaciente = modal;
		this.paciente = $('.js-paciente-da-pesquisa');
	}
	
	TabelaPacientePesquisaRapida.prototype.iniciar = function(){
		this.paciente.on('click',onPacienteSelecionado.bind(this));
	}
	
	function onPacienteSelecionado(evento) {
		this.modalPaciente.modal('hide');
		
		var pacienteSelecionado = $(evento.currentTarget);
		$('#paciente').val(pacienteSelecionado.data('nome'));
		$('#idPaciente').val(pacienteSelecionado.data('id'));

	}
	
	return TabelaPacientePesquisaRapida;
}());

$(function() {
	var pesquisaRapidaPaciente = new Saude.PesquisaRapidaPaciente();
	pesquisaRapidaPaciente.iniciar();
});