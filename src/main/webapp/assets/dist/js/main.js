$('[id^="btn-delete"]').click( () => {
    let code = $(this).attr('data-code');
    let text = $(this).attr('data-text');
    $('#id').val(code);
    $('#text-delete').text(text);
});
