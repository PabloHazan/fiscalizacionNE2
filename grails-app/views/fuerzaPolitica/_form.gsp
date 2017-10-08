<div class="row">
    <div class="col-md-4">
        <label for="nombre" >Nombre</label>
        <g:if test="${modoEdicion}">
            <g:textField id="nombre" name="nombre" value="${fuerzaPolitica.nombre}"/>
        </g:if>
        <g:else>
            <span id="nombre">${fuerzaPolitica.nombre}</span>
        </g:else>
    </div>
    <div class="col-md-4">
        <label for="color">Color</label>
        <span id="color">
            <input type="color"
                   id="inputColor"
                   name="color"
                   onchange="onChangeColor()"
                   value='#${fuerzaPolitica?.color ? Long.toHexString(fuerzaPolitica?.color) : "FFFFFF"}'
                ${modoEdicion ? "" : 'disabled="disabled"'}
            />
            <g:hiddenField id="hfColor" name="color" value="${fuerzaPolitica?.color}"/>
        </span>
    </div>
</div>

<script type="application/javascript">
    function onChangeColor(){
        var hexaColor = $("#inputColor").val();
        var colorNumero = parseInt(hexaColor);
        $("#hfColor").val(colorNumero);
    }
</script>