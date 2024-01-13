function getSectors() {
    $.ajax({
        type: "GET",
        url: "/sectors",
        success: function(items) {
            $('#sectorsDropdown').empty();
            items.forEach(function(item) {
                $('#sectorsDropdown').append($('<option>', {
                    value: item.optionValue,
                    text: item.name
                }));
            });
        },
        error: function(jqXHR, textStatus, errorThrown, error) {
            console.error("Error calling Spring Boot controller:", error);
            window.location.href = "/error.html";
        }
    });
}
//window.onload = refillForm;

$(document).ready(function() {
    getSectors();
    //refillForm();
});

//refillForm();

function refillForm() {
    var form = document.getElementById('sectorsForm');
    var storedFormData = localStorage.getItem('sessionData');

    if (storedFormData) {
        var formData = JSON.parse(storedFormData);

        for (var i = 0; i < form.elements.length; i++) {
            var element = form.elements[i];
            if (element.id == 'name') {
                element.value = formData.name;
            }
            if (element.id == 'agreement' && formData.agreement == 'on') {
                //element.setAttribute('checked', true);
                element.checked = true;
                //$(checkbox).prop('checked', true);
            }
            if (element.id == 'sectorsDropdown') {
                dropdown = element;
                for (var i = 0; i < formData.sectors.length; i++) {
                var sector = formData.sectors[i];
                    for (var j = 0; j < dropdown.options.length; j++) {
                        if (dropdown.options[j].value == sector) {
                        dropdown.options[j].selected = true;
                        }
                    }
                }
            }
        }
    }
}

function addDataToLocalStorage(){
    var uid = localStorage.getItem('ip');
    var name = document.getElementById('name').value;
    var selectedOptions = $('#sectorsDropdown').val();
    var agreement = document.getElementById('agreement').value;
    var sessionData = {
                uid: uid,
                name: name,
                sectors: selectedOptions,
                agreement: agreement
        };
var sessionDetails = JSON.stringify(sessionData);
localStorage.setItem('sessionData', sessionDetails);

  var storedValue = localStorage.getItem('sessionData');
  document.body.innerHTML += "<p>Stored Value: " + storedValue + "</p>";
}

function addSessionData() {
addDataToLocalStorage();
    var uid = localStorage.getItem('ip');
    var sessionData = localStorage.getItem('sessionData');
    var parsedJsonObject = JSON.parse(sessionData);

    var name = parsedJsonObject.name;
    var uid = parsedJsonObject.uid;
    var agreement = parsedJsonObject.agreement;
    var sectors = parsedJsonObject.sectors;

        var jsonData = {
                uid: uid,
                name: name,
                agreement: agreement,
                sectors: sectors
        };

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/sectors/form-data/",
        data: JSON.stringify(jsonData),
        success: function(response) {
             console.log("Success:", response);
        },
        error: function(jqXHR, textStatus, errorThrown, error) {
             console.error("Error:", error);
             window.location.href = "/error.html";
        }
    });
}

function text(url) {
  return fetch(url).then(res => res.text());
}

text('https://www.cloudflare.com/cdn-cgi/trace').then(data => {
  let ipRegex = /[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}/
  let ipValue = data.match(ipRegex)[0];
  key="ip";
  value = ipValue
  localStorage.setItem(key, value);
  var storedValue = localStorage.getItem(key);
  document.body.innerHTML += "<p>Stored Value: " + storedValue + "</p>";
});