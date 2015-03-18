$(document).ready(function() {
    var coords = null;

    // Obtiene la posición actual
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            coords = position.coords;
            showInfo();
            showMap();
        });
    }

    // Obtiene la localización cada 2s.
    navigator.geolocation.watchPosition(function (geodata) {
        var speed = geodata.coords.speed;

        if (speed === null || speed === 0) {
            console.log("You're standing still!");
        } else {
            // speed is in metres per second
            console.log(speed + "m/s.");
        }

    }, function () {
        console.log("Unable to determine speed :-(");
    },
    { enableHighAccuracy: true, timeout: 2000, maximumAge: 0}
    );

    function showInfo() {
        $("#status").get(0).innerHTML = "Latitud: "+coords.latitude+" | Longitud: "+coords.longitude+" | Precisión: "+coords.accuracy+" metros.";
    }

    // Calcular posición
    function showMap() {
        var mapcanvas = document.createElement('div');
        mapcanvas.id = 'mapcanvas';
        mapcanvas.style.height = '400px';
        mapcanvas.style.width = '560px';

        document.querySelector('article').appendChild(mapcanvas);

        var latlng = new google.maps.LatLng(coords.latitude, coords.longitude);
        var myOptions = {
            zoom: 15,
            center: latlng,
            mapTypeControl: false,
            navigationControlOptions: {style: google.maps.NavigationControlStyle.SMALL},
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        var map = new google.maps.Map(document.getElementById("mapcanvas"), myOptions);

        var marker = new google.maps.Marker({
            position: latlng,
            map: map,
            title: "¡Usted está aquí!"
        });
    }
});
