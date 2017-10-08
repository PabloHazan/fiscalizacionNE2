<div class="row">
    <div class="col-md-4">
        <label for="color">Color</label>
        <span id="color">
            <input type="color"
                   id="inputColor"
                   name="color"
                   onchange="onChangeColor()"
                   value='#${partido?.color ? Long.toHexString(partido?.color) : "FFFFFF"}'
                ${modoEdicion ? "" : 'disabled="disabled"'}
            />
            <g:hiddenField id="hfColor" name="color" value="${partido?.color}"/>
        </span>
    </div>
</div>
<g:if test="${modoEdicion}">
    <f:all bean="partido" except="color"/>
</g:if>
<g:else>
    <f:display bean="partido" except="color"/>
</g:else>


<script type="application/javascript">
    function onChangeColor(){
        var hexaColor = $("#inputColor").val();
        var colorNumero = parseInt(hexaColor);
        $("#hfColor").val(colorNumero);
    }
</script>