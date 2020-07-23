function startTime(s) {
	s.datepicker({
		defaultDate : "+1w",
		changeYear : true,
		changeMonth : true,
		dateFormat : "yy-mm-dd",
		onClose : function(selectedDate) {
			$("#datetime2").datepicker("option", "minDate", selectedDate);
		}
	});
}

function endTime(d) {
	d.datepicker({
		defaultDate : "+1w",
		changeYear : true,
		changeMonth : true,
		dateFormat : "yy-mm-dd",
		onClose : function(selectedDate) {
			$("#datetime1").datepicker("option", "maxDate", selectedDate);
		}
	});
}