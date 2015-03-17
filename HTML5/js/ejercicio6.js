/*globals $, window*/

window.onload = function() {
    "use strict";

    var video = $("#video").get(0);

    video.addEventListener('canplay', function(e) {
        this.volume = 0.5;
    }, false);

    video.addEventListener('timeUpdate', function(e) {
//        $("#progress")
        $("#progress").val(this.currentTime);
    }, false);

    var play = function() {
        video.play();
    };

    var pause = function() {
        video.pause();
    };

    var stop = function() {
        video.pause();
        video.currentTime = 0;
    };

    var ffw = function() {
        video.currentTime += 15;
    };

    var rw = function() {
        video.currentTime -= 15;
    };

    var start = function() {
        video.currentTime = 0;
    };

    var end = function() {
        video.currentTime = video.duration;
    };

//    var seek = function(time) {
//        console.log(time);
//        video.currentTime = time;
//    };

    var fullscreen = function() {
        if (video.requestFullScreen) {
            video.requestFullScreen();
        } else if (video.mozRequestFullScreen) {
            video.mozRequestFullScreen();
        } else if (video.webkitRequestFullScreen) {
            video.webkitRequestFullScreen();
        }
    };

    var volume = function() {
        var vol = $("#volume").val();
        video.volume = vol/10;
    };

    var playlist = function() {
        var vid = $("#playlist").val();

    };

    $("#play").on('click', play);
    $("#pause").on('click', pause);
    $("#stop").on('click', stop);
    $("#ffw").on('click', ffw);
    $("#rw").on('click', rw);
    $("#start").on('click', start);
    $("#end").on('click', end);
    $("#fullscreen").on('click', fullscreen);
    $("#volume").on('change', volume);
    $("#playlist").on('change', playlist);
};
