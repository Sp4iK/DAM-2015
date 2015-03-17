if (Modernizr.input.placeholder) {
    console.log("placeholder disponible");
} else {
    console.log("placeholder no disponible");
}

Modernizr.load({
    test: Modernizr.input.placeholder,
    nope: [
        'js/lib/placeholder-polyfill.js'
    ]
});
