# datepicker-cordovaplugin
android date picker widget for cordova application.

# install
this could be installed easily for now just clone the project and open terminal with the project folder and execute
$: cordova add <path to the phisical folder you cloned it from here>

# use
```
        var temp = {
           "year":1994,
           "month":3,
           "day":16
        };
        
        window.plugins.showDatePicker(temp, function (s) {
            console.log(s);
            document.getElementById("dateselected").innerHTML = s.date;
        }, function (e) {
            console.log(e);
        });
```
        
