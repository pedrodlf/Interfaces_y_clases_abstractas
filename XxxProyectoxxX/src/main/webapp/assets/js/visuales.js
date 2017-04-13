function  mostrarElemento(id) {

    document.getElementById(id).style.display = "inline";

}

function ocultarElemento(id) {
    document.getElementById(id).style.display = "none";

}
function modicarfechapordias(days) {
    milisegundos = parseInt(35 * 24 * 60 * 60 * 1000);

    fecha = new Date();
    day = fecha.getDate();
    month = fecha.getMonth() + 1;
    year = fecha.getFullYear();
    tiempo = fecha.getTime();
    milisegundos = parseInt(days * 24 * 60 * 60 * 1000);
    total = fecha.setTime(tiempo + milisegundos);
    day = fecha.getDate();
    month = fecha.getMonth() + 1;
    year = fecha.getFullYear();
    if (parseInt(month) < 10) {
        month = "0" + month;
    }
    if (parseInt(day) < 10) {
        day = "0" + day;
    }

    document.getElementById('factura-vencimiento').value = day + "/" + month + "/" + year;
}

var mostrarValor = function (x) {
    switch (x) {
        case "1":
            modicarfechapordias(0);
            break;
        case "2":
            modicarfechapordias(15);
            break;
        case "3":
            modicarfechapordias(30);
            break;
        case "4":
            modicarfechapordias(60);
            break;
        case "5":
            modicarfechapordias(90);
            break;
        case "6":
            modicarfechapordias(120);
            break;
    }

};



$(document).ready(function () {
    $('#selectconcepto').change(function (event) {
        var selectConcepto = $('#selectconcepto').val();
        $.post('PintarConceptos.do', {
            selectConcepto: selectConcepto

        }, function (responseText) {
            $('#pppp').html(responseText);
        });
    });
});

$(document).ready(function () {
    $('#aniadircon').click(function (event) {
        var nombreConcepto = $('#nombreConcepto').val();
        var cantidadpro = $('#cantidadpr').val();
        var basepro = $('#basepr').val();
        var ivapro = $('#ivapro').val();
        var descripccionpro = $('#descripccionconcep').val();
        var numeroFacturapro = $('#numeroFacturapro').val();
        var nickNamepro = $('#nickNamepro').val();
        $.post('CrearConcepto.do', {
            nombreConcepto: nombreConcepto,
            cantidadpro: cantidadpro,
            basepro: basepro,
            ivapro: ivapro,
            descripccionpro: descripccionpro,
            numeroFacturapro: numeroFacturapro,
            nickNamepro: nickNamepro
        }, function (responseText) {
            $('#tablaProductos').html(responseText);
        });
    });
}); 

$(document).ready(function () {
    $('#combo-Cliente').change(function (event) {
        var selectCliente = $('#combo-Cliente').val();
        var numeroFac = $('#demo-factura').val();
        var vencimiento = $('#factura-vencimiento').val();
        var fecha = $('#demo-name').val();
        
        $.post('AlmacenarrVariablesFactura.do', {
            selectCliente: selectCliente,
            numeroFac : numeroFac,
            vencimiento : vencimiento,
            fecha : fecha
            

        }, function (responseText) {
            $('#xxxx').html(responseText);
        });
    });
});

function ventanaPlantilla()
{
open('http://localhost:8084/XxxProyectoxxX/plantillas/example2/plantilla2.jsp', 'Sizewindow', 'width=740,height=780,scrollbars=no,toolbar=no');
}