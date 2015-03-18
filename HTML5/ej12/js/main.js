window.onload = function() {
    "use strict";

    var dragSrcEl = null;

    function handleDragStart(e) {
        console.log("DragStart event");

        dragSrcEl = this;
        e.dataTransfer.effectAllowed = 'move';
        e.dataTransfer.setData('text/html', this.innerHTML);
        $("#drop").get(0).style.background = "yellow";
    }

    function handleDrop(e) {
        console.log("Drop event: " + dragSrcEl + " | " + this);

        // this/e.target is current target element.
        if (e.stopPropagation) {
            e.stopPropagation(); // Stops some browsers from redirecting.
        }

        // Don't do anything if dropping the same column we're dragging.
        if (dragSrcEl != this) {
            // Set the source column's HTML to the HTML of the columnwe dropped on.
            dragSrcEl.innerHTML = this.innerHTML;
            this.innerHTML = e.dataTransfer.getData('text/html');
        }
        return false;
    }

    function handleDragEnd(e) {
        console.log("DragEnd event");

        // this/e.target is the source node.
        [].forEach.call(productos, function (producto) {
            producto.classList.remove('over');
        });

        $("#drop").get(0).style.background = "#efefef";
    }

    var productos = document.querySelectorAll('.cont_products .product');
    [].forEach.call(productos, function(producto) {
        producto.addEventListener('dragstart', handleDragStart, false);
    //    producto.addEventListener('drop', handleDrop, false);
        producto.addEventListener('dragend', handleDragEnd, false);
    });

    $("#drop").addEventListener('drop', handleDrop, false);

    //var cols = document.querySelectorAll('#columns .column');
    //[].forEach.call(cols, function(col) {
    //    col.addEventListener('dragstart', handleDragStart, false);
    //    col.addEventListener('dragenter', handleDragEnter, false);
    //    col.addEventListener('dragover', handleDragOver, false);
    //    col.addEventListener('dragleave', handleDragLeave, false);
    //    col.addEventListener('drop', handleDrop, false);
    //    col.addEventListener('dragend', handleDragEnd, false);
    //});
};
