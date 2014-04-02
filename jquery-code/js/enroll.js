$(document)
		.ready(
				function() {
					
					$('#customerFirstName').upperFirstAll();
					$('#customerLastName').upperFirstAll();
					$('#homeCity').upperFirstAll();
					$('#homeState').upperCase();
					
					
					$('#submit')
							.click(
									function() {
										valid = true;
										var msg = "Please enter in all required data.";
										if (valid
												&& !validateRequired($(
														'#customerFirstName')
														.val())) {
											valid = false;
										}
										if (valid
												&& !validateRequired($(
														'#customerLastName')
														.val())) {
											valid = false;
										}
										if (valid
												&& !validateRequired($(
														'#homeAddress').val())) {
											valid = false;
										}
										if (valid
												&& !validateRequired($(
														'#homeCity').val())) {
											valid = false;
										}
										if (valid
												&& !validateRequired($(
														'#homeState').val())) {
											valid = false;
										}
										if (valid
												&& !validateRequired($(
														'#homeZipCd').val())) {
											valid = false;
										}
										if (valid
												&& !validateRequired($(
														'#primaryEmail').val())) {
											valid = false;
										}

										if (valid
												&& !validateRequired($(
														'#homePhoneNumber')
														.val())
												&& !validateRequired($(
														'#workPhoneNumber')
														.val())
												&& !validateRequired($(
														'#cellPhoneNumber')
														.val())) {
											valid = false;
										}
										var badphone = "Invalid phone number.";

										var phone = $
												.trim($('#homePhoneNumber')
														.val());
										if (valid && phone.length != 0
												&& !validatePhone(phone)) {
											msg = badphone;
											valid = false;
										}
										phone = $.trim($('#workPhoneNumber')
												.val());
										if (valid && phone.length != 0
												&& !validatePhone(phone)) {
											msg = badphone;
											valid = false;
										}
										phone = $.trim($('#cellPhoneNumber')
												.val());
										if (valid && phone.length != 0
												&& !validatePhone(phone)) {
											msg = badphone;
											valid = false;
										}

										var zipcd = $.trim($('#homeZipCd')
												.val());

										if (valid && zipcd.length != 0
												&& !validateZipcode(zipcd)) {
											msg = "Invalid zip code.";
											valid = false;
										}

										var email = $.trim($('#primaryEmail')
												.val());
										if (valid && !validateEmail(email)) {
											msg = "Invalid email.";
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