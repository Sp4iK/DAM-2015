if (Modernizr.input.placeholder) {
    console.log("placeholder disponible");
} else {
    console.log("placeholder no disponible");
}

Modernizr.load({
    test: Modernizr.input.placeholder,
    nope: [
        'css/placeholder_polyfill.min.css',
        'js/lib/placeholder_polyfill.jquery.min.combo.js'
    ]
});
