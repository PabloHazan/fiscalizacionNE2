<table class="table table-striped" >
    <thead>
    <tr>
        <th>
            <g:if test="${modoEdicion}">
                <button id="addFuerzaPolitica"
                        type="button"
                        class="btn btn-primary"
                        onclick="addFuerzaPoliticaRow()">
                    <i class="glyphicon glyphicon-plus"></i>
                </button>
            </g:if>
            Fuerza Política
        </th>
    </tr>
    </thead>
    <tbody id="tableBodyFuerzaPolitica">
    <g:set var="fuerzasPoliticas" value="${fiscalizacionne.FuerzaPolitica.all}"/>
    <g:each in="${fuerzasPoliticas}" var="fuerzaPolitica" status="i">
        <g:set var="isChecked" value="${fuerzasPoliticasDeComuna.any {f -> f.id == fuerzaPolitica.id}}"/>
        <g:render template="fuerzaPoliticaRow"
                  model="[
                          fuerzaPolitica:fuerzaPolitica,
                          index:i+1,
                          modoEdicion: modoEdicion,
                          isChecked: isChecked
                  ]"
        />
    </g:each>
    </tbody>
    <g:hiddenField id="countFuerzasPoliticas" name="countFuerzasPoliticas" value="${fuerzasPoliticas?.size() ?: 0}"/>
</table>

<script type="application/javascript">
    function addFuerzaPoliticaRow(){
        var url = document.location.origin + "/comuna/fuerzaPoliticaRow";
        var indexRow = parseInt($('#countFuerzasPoliticas').val())+1;
        $('#countFuerzasPoliticas').val(indexRow);
        $.ajax({
            url: url,
            data: {
                index: indexRow
            },
            success: function (data) {
                $("#tableBodyFuerzaPolitica").append(data);
            }
        });
    }

    function deleteRowFuerzaPolitica(index) {
        $("#fuerzaPoliticaRow"+index).remove();
    }
</script>