jQuery('.showDate').datetimepicker({
	format: 'YYYY-MM-DD H:mm'
});
jQuery('.movieDate').datetimepicker({
	format: 'YYYY-MM-DD'
});

var editModal = function() {
		$('#editModal').modal('toggle');
}