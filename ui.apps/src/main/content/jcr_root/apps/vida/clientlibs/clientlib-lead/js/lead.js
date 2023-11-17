            /* Code for changing active 
            link on clicking */
            var btns = 
                $("#navigation .navbar-nav .nav-link");
  
            for (var i = 0; i < btns.length; i++) {
                btns[i].addEventListener("click",
                                      function () {
                    var current = document
                        .getElementsByClassName("active");
  
                    current[0].className = current[0]
                        .className.replace(" active", "");
  
                    this.className += " active";
                });
            }
  
            /* Code for changing active 
            link on Scrolling */
            $(window).scroll(function () {
                var distance = $(window).scrollTop();
                $('.page-section').each(function (i) {

                    if ($(this).position().top 
                        <= distance + 240) {
                          
                            $('.navbar-nav a.active')
                                .removeClass('active');
  
                            $('.navbar-nav a').eq(i)
                                .addClass('active');
                    }
                });
            }).scroll();

			/*loadGeoData()
			function loadGeoData() {
				$.each(data.states, function (index, obj) {
                    $("#states").append('<option value="'+obj.value+'">'+obj.label+'</option>');
				});
			}

			$('#states').on('change', function(){
				var cities = getCities();
				$('#cities').html('<option value="000">-Select City-</option>');
				var stateId = $(this).val();
				$.each(cities, function (index, value) {	
				if (value.stateId == stateId) {				
					//console.log(index, value.cityName, stateId);
					$("#cities").append('<option value="'+value.cityId +'">'+value.cityName+'</option>');
				}
				});
			});

			function getCities() {
				var cities_data = [];	
				for(var i = 0; i < data.states.length; i++) {
					var state = data.states[i];					
					var cities = state.cities;
					for(var j = 0; j < cities.length; j++) {		
						var city = cities[j];
						cities_data.push({
							stateId: state.value,
							stateName: state.label,
							cityId: city.value,
							cityName: city.label
						});
					}
				}
				return cities_data;
			}*/

$('#submit-btn-lead').click(function(e) {
	if (!$("#g-recaptcha-response").val()) {
		$("#captchavalidation").css("display", "block")
    }
    if (document.getElementById("formdata").checkValidity()) {
        if ($("#g-recaptcha-response").val()) {
		  $("#submit-btn-lead").css("display", "none")
          $(".spin").css("display", "inline-block")
          $.ajax(window.appConfig.leadCreationUrl, {
            method: 'POST',
            data:JSON.stringify({
                fullName: $("#name").val() || "",
                FirstName: $("#firstname").val() || "",
                LastName: $("#lastname").val() || "",
                Email: $("#email").val() || "",
                MobilePhone: $("#mobile").val() || "",
                dmpl__State__c: $("#states").val() || "",
                dmpl__City__c: $("#cities").val() || "",
                SubSources__c: $("#networkcodes").val() || "",
                gRecaptchaResponse: $("#g-recaptcha-response").val() || ""
            }),
           // processData: false,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success(data) {
              $(".spin").css("display", "none")
              $("#submit-btn-lead").css("display", "inline-block")
              if(data && data[0] && data[0].errorCode == "DUPLICATES_DETECTED"){
				$("#submissionerror-duplicate").css("display", "block")
                setTimeout(() => { 
                    $("#submissionerror-duplicate").css("display", "none")
                }, "5000");
          	  }
              else if(data && data.success){
				$(".submit-success-heading-lead").css("display", "block")
                $(".submit-success-message-lead").css("display", "block")
                $("#formdata").css("display", "none")
                setTimeout(() => { 
                    $("#submissionsuccess-lead").css("display", "none")
                }, "5000");
          	  }
              else{
              	$("#submissionerror").css("display", "block")
                setTimeout(() => { 
                    $("#submissionerror").css("display", "none")
                }, "5000");
          	  }
              /*if(data && data.status){
                $("#submissionsuccess").css("display", "block")
                $("#name").val("")
                $("#email").val("")
                $("#mobile").val("")
                $("#states").val("")
                $("#cities").val("")
                $(".submit-success-heading").css("display", "block")
                $(".submit-success-message").css("display", "block")
                $("#formdata").css("display", "none")
                setTimeout(() => { 
                    $("#submissionsuccess").css("display", "none")
                }, "5000");
              }
              else{
                $("#submit-btn").css("display", "inline-block")
                $(".spin").css("display", "none")
                $("#submissionerror").css("display", "block")
                setTimeout(() => { 
                    $("#submissionerror").css("display", "none")
                }, "5000");
              }
              console.log('Upload success', data);*/
            },
            error() {
              console.log('Upload error');
              $("#submit-btn").css("display", "inline-block")
              $(".spin").css("display", "none")
              $("#submissionerror").css("display", "block")
              setTimeout(() => { 
                $("#submissionerror").css("display", "none")
              }, "5000");
            },
          });
        } else {
            $("#captchavalidation").css("display", "block")
        }
    }
});

(function () {
  'use strict'
  var forms = document.querySelectorAll('.needs-validation')
  Array.prototype.slice.call(forms)
  .forEach(function (form) {
	form.addEventListener('submit', function (event) {
	  if (!form.checkValidity()) {
		event.preventDefault()
        event.stopPropagation()
      }
      form.classList.add('was-validated')
    }, false)
  })

})()

function onRecaptchaSuccess() {
  console.log("===onRecaptchaSuccess")
  $("#captchavalidation").css("display", "none")
} 

function onRecaptchaError() {
  console.log("===onRecaptchaError")
  $("#captchavalidation").css("display", "block")
} 

function onRecaptchaResponseExpiry() {
  console.log("===onRecaptchaResponseExpiry")
  $("#captchavalidation").css("display", "block")
}

$( document ).ready(function() {
    let select = document.getElementById("networkcodes");
    select.innerHTML = ""
    let firstItem = document.createElement("option");
    firstItem.textContent = "Select Your Dealer Code";
    firstItem.value = "";
    select.appendChild(firstItem);
    window.appConfig.networkCodes.map(networkcode => {
          let opt = networkcode;
          let el = document.createElement("option");
          el.textContent = opt.id + " - " + opt.value;
          el.value = opt.id + " - " + opt.value;
          select.appendChild(el);
    })

  const input = document.getElementById('customFile');


  input.addEventListener('change', (event) => {
    event.preventDefault();
    const image = event.target.files[0];
    const  fileType = image['type'];
    const validImageTypes = ['image/gif', 'image/jpeg', 'image/png'];
    const size = (image['size'] / 1024 / 1024).toFixed(2);
    console.log(image);
    console.log(size);
    if (image && validImageTypes.includes(fileType) && size < 3) {
        $("#imagefailure").css("display", "none")
        const reader = new FileReader(); 
        reader.readAsDataURL(image);
        reader.addEventListener('load', () => {
          localStorage.setItem('custom-File', reader.result);
          console.log('Image data saved to localStorage.');
        });
        reader.addEventListener('error', (error) => {
          console.error('Error reading the file:', error);
        });
    } else {
        console.error('No image selected.');
        $("#imagefailure").css("display", "block")
    }
  });   
  localStorage.removeItem('custom-File');
});