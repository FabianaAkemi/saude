/**
 * Created by akemi on 05/03/17.
 */
var Saude = Saude || {};

Saude.MaskPhoneNumber = (function() {

    function MaskPhoneNumber() {
        this.inputPhoneNumber = $('.js-phone-number');
    }

    MaskPhoneNumber.prototype.enable = function() {
        var maskBehavior = function (val) {
            return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
        };

        var options = {
            onKeyPress: function(val, e, field, options) {
                field.mask(maskBehavior.apply({}, arguments), options);
            }
        };

        this.inputPhoneNumber.mask(maskBehavior, options);
    }

    return MaskPhoneNumber;

}());

Saude.MaskDate = (function() {
	function MaskDate() {
		this.inputDate = $('.js-date');
	}
	
	MaskDate.prototype.enable = function() {
		this.inputDate.mask('00/00/0000');
		this.inputDate.datepicker({
			orientation: 'bottom',
			language: 'pt-BR',
			autoclose: true
		});
	}
	
	return MaskDate;

}());

Saude.Security = (function() {
	
	function Security() {
		this.token = $('input[name=_csrf]').val();
		this.header = $('input[name=_csrf_header]').val();
	}
	
	Security.prototype.enable = function() {
		$(document).ajaxSend(function(event, jqxhr, settings) {
			jqxhr.setRequestHeader(this.header, this.token);
		}.bind(this));
	}
	
	return Security;
	
}());


$(function() {
    var maskPhoneNumber = new Saude.MaskPhoneNumber();
    maskPhoneNumber.enable();
    
    var maskDate = new Saude.MaskDate;
	maskDate.enable();
	
	var security = new Saude.Security();
	security.enable();

});

/**mascaras */
$('.cpf').mask('000.000.000-00');

$('.cep').mask('00.000-000');

$('.crm').mask('AA 00.000');