$(document).ready(function() {
    changePageAndSize();
});

function changePageAndSize() {
    $('#pageSizeSelect').change(function(evt) {
        window.location.replace("/files/view/?pageSize=" + this.value + "&page=1");
    });
}
