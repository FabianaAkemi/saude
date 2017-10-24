Saude = Saude || {};

Saude.PesquisaRapidaMedico = (function() {
	
	function PesquisaRapidaMedico() {
		this.pesquisaRapidaMedicosModal = $('#pesquisaRapidaMedicos');
		this.nomeInput = $('#nomeMedico');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-medico-btn'); 
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaMedicos');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-medico').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaMedico.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaMedicosModal.on('shown.bs.modal',onModalShow.bind(this));
	}
	
	function onModalShow() {
		this.nomeInput.focus();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaMedicosModal.find('form').attr('action'),
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
		
		var tabelaMedicoPesquisaRapida = new Saude.TabelaMedicoPesquisaRapida(this.pesquisaRapidaMedicosModal);
		tabelaMedicoPesquisaRapida.iniciar();
	} 
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaMedico;
	
}());

Saude.TabelaMedicoPesquisaRapida = (function() {
	
	function TabelaMedicoPesquisaRapida(modal) {
		this.modalMedico = modal;
		this.medico = $('.js-medico-da-pesquisa');
	}
	
	TabelaMedicoPesquisaRapida.prototype.iniciar = function(){
		this.medico.on('click',onMedicoSelecionado.bind(this));
	}
	
	function onMedicoSelecionado(evento) {
		this.modalMedico.modal('hide');
		
		var medicoSelecionado = $(evento.currentTarget);
		$('#medico').val(medicoSelecionado.data('nome'));
		$('#idMedico').val(medicoSelecionado.data('id'));

	}
	
	return TabelaMedicoPesquisaRapida;
}());

$(function() {
	var pesquisaRapidaMedico = new Saude.PesquisaRapidaMedico();
	pesquisaRapidaMedico.iniciar();
});