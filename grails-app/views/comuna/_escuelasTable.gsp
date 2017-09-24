<table class="table table-striped" >
    <thead>
    <tr>
        <th>
            <g:if test="${modoEdicion}">
                <button id="addEscuela"
                        type="button"
                        class="btn btn-primary"
                        onclick="addEscuelaRow()">
                    <i class="glyphicon glyphicon-plus"></i>
                </button>
            </g:if>
              Escuela
        </th>
        <th></th>
    </tr>
    </thead>
    <tbody id="tableBody">
        <g:each in="${escuelas}" var="escuela" status="i">
            <g:render template="escuelaRow" model="[escuela:escuela, index:i+1, modoEdicion: modoEdicion]"/>
        </g:each>
    </tbody>
    <g:hiddenField id="countEscuelas" name="countEscuelas" value="${escuelas?.size() ?: 0}"/>
</table>

<script type="application/javascript">
    function addEscuelaRow(){
        var url = document.location.origin + "/comuna/escuelaRow";
        var indexRow = parseInt($('#countEscuelas').val())+1;
        $('#countEscuelas').val(indexRow);
        $.ajax({
            url: url,
            data: {
                index: indexRow
            },
            success: function (data) {
                $("#tableBody").append(data);
            }
        });
    }

    function deleteRow(index) {
        $("#escuelaRow"+index).remove();
    }
</script>