$(document).ready(function() {

	$('#submit').click(function() {
		valid = true;
		var msg = "";
		if (valid && !validateRequired($('#vin').val())) {
			msg = "Please input VIN";
			valid = false;
		}
		if (valid && $('#vin').val().length != 17) {
			msg = "VIN is 17 char.";
			valid = false;
		}

		if (valid) {
			$('#errorMsg').hide();
		} else {
			$('#errorMsg').text(msg);
			$('#errorMsg').show();
		}

		return valid;
	});
});
