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


$(function() {
    var maskPhoneNumber = new Saude.MaskPhoneNumber();
    maskPhoneNumber.enable();

});