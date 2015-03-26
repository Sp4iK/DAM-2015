//document.DOMContentLoaded = function() {

    var canvasWidth = window.innerWidth - 10;
    var canvasHeight = window.innerHeight;

    var btnFoto = document.getElementById('btnFoto');
    var btnVideo = document.getElementById('btnVideo');
    var btnBorrar = document.getElementById('btnBorrar');
    var btnGaleria = document.getElementById('btnGaleria');
    var image = document.getElementById('myImage');
    var video = document.getElementById('video');

    btnFoto.addEventListener('click', sacarFoto);
    btnVideo.addEventListener('click', grabarVideo);
    btnBorrar.addEventListener('click', limpiarFoto);
    btnGaleria.addEventListener('click', galeria);

//};

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
        navigator.camera.getPicture(onSuccess, onFail, {destinationType: Camera.DestinationType.DATA_URL,
                                                        sourceType: Camera.PictureSourceType.PHOTOLIBRARY,
                                                        mediaType: Camera.MediaType.PICTURE});
    }

    function onSuccess(imageData) {
        console.log(imageData);
        image.src = imageData;
//        image.src = "data:image/jpeg;base64," + imageData;
        image.width = canvasWidth;
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
//        video.src = "data:video/mp4" + mediaFiles[0].fullPath;
    }

    function onFail(message) {
        alert('Failed because: ' + message);
    }

    function limpiarFoto() {
        image.src = "";
    }
