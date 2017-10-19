<table class="table table-bordered" >
    <thead>
    <tr>
        <th>
            <g:if test="${modoEdicion}">
                <button id="addMesa"
                        type="button"
                        class="btn btn-primary"
                        onclick="addMesaRow()">
                    <i class="glyphicon glyphicon-plus"></i>
                </button>
            </g:if>
            Mesa
        </th>
        <th></th>
    </tr>
    </thead>
    <tbody id="tableBody">
    <g:each in="${mesas}" var="mesa" status="i">
        <g:render template="mesaRow" model="[mesa:mesa, index:i+1, modoEdicion: modoEdicion]"/>
    </g:each>
    </tbody>
    <g:hiddenField id="countMesas" name="countMesas" value="${mesas?.size() ?: 0}"/>
</table>

<script type="application/javascript">
    function addMesaRow(){
        var url = "${createLink(controller: 'escuela', action: 'mesaRow')}";
        var indexRow = parseInt($('#countMesas').val())+1;
        $('#countMesas').val(indexRow);
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
        $("#mesaRow"+index).remove();
    }
</script>