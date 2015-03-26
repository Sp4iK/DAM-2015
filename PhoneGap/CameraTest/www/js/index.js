//document.DOMContentLoaded = function() {
//document.onDeviceReady = function() {

    var btnFoto = document.getElementById('btnFoto');
    var btnVideo = document.getElementById('btnVideo');
    var btnBorrar = document.getElementById('btnBorrar');
    var btnGaleria = document.getElementById('btnGaleria');
    var media = document.getElementById('media');
    var imagen = document.getElementById('imagen');
    var video = document.getElementById('video');

    var canvasWidth = media.width - 10;
    var canvasHeight = media.height - 10;

    btnFoto.addEventListener('click', sacarFoto);
    btnVideo.addEventListener('click', grabarVideo);
    btnGaleria.addEventListener('click', galeria);
    btnBorrar.addEventListener('click', limpiarFoto);

    function sacarFoto() {
        navigator.camera.getPicture(onSuccess, onFail, {quality: 75,
                                                        destinationType: Camera.DestinationType.FILE_URI,
                                                        sourceType: Camera.PictureSourceType.CAMERA,
                                                        allowEdit: false,
                                                        encodingType: Camera.EncodingType.JPEG,
                                                        saveToPhotoAlbum: false});
    }

    function grabarVideo() {
        navigator.device.capture.captureVideo(captureSuccess, onFail);
    }

    function galeria() {
        navigator.camera.getPicture(onSuccess, onFail, {destinationType: Camera.DestinationType.FILE_URI,
                                                        sourceType: Camera.PictureSourceType.PHOTOLIBRARY,
                                                        mediaType: Camera.MediaType.ALLMEDIA});
    }

    function onSuccess(data) {
        console.log(data);
        imagen.src = data;
//        imagen.src = "data:image/jpeg;base64," + data;
        imagen.className = "";
        imagen.width = canvasWidth;
    }

    function captureSuccess(mediaFiles) {
        var mediaFileData = mediaFiles[0].getFormatData();
        console.log(mediaFileData);

//        alert("Nombre: "+mediaFiles[0].name+"\n"+
//              "Tipo: "+mediaFiles[0].type+"\n"+
//              "Tamaño: "+mediaFiles[0].size+"\n"+
//              "Codec: "+mediaFiles[0].codecs);

        video.src = "file:///storage/sdcard1/DCIM/Camera/"+mediaFiles[0].name;
//        video.src = mediaFiles[0].fullPath;
        video.className = "";
        video.width = canvasWidth;
    }

    function onFail(message) {
        alert('Failed because: ' + message);
    }

    function limpiarFoto() {
        imagen.src = video.src = "";
        imagen.className = video.className = "hidden";
    }

//};
