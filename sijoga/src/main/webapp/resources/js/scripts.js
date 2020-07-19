$(function () {

    function onCepComplete(cep) {
        cep = cep.replace(/\D/g, '');
        var fields = $('[class^="zip-code-"]');
        $(fields).prop('disabled', true).val('');
        $.ajax({
            method: 'GET',
            url: `https://viacep.com.br/ws/${cep}/json`,
            success(response) {
                $('.zip-code-street').val(response.logradouro);
                $('.zip-code-state').val(response.uf);
                $('.zip-code-city').val(response.localidade);
                $('.zip-code-number').focus();
            },
            complete() {
              $(fields).prop('disabled', false)
            }
        });
    }

    $('.mask-cpf').mask('000.000.000-00', {reverse: true});
    $('.mask-date').mask('00/00/0000');
    $('.mask-cep').mask('00000-000', { onComplete: onCepComplete });
    $('.mask-int').mask('000.000');

    $('.datepicker').datepicker({
        format: 'dd/mm/yyyy'//,
        //endDate: '-18y'
    });

    $('[name="role"]').change(function() {
        var role = $(this).val();
        var oabField = $('#oabField');
        if (role === 'ADVOGADO') {
            $(oabField).show();
        } else {
            $(oabField).hide();
        }
    });

});
