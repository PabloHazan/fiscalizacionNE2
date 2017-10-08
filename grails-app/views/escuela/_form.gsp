<%@ page import="fiscalizacionne.Comuna" %>
<g:hiddenField name="id" value="${escuela?.id}"/>
<g:hiddenField name="versionValue" value="${escuela?.versionValue}"/>
<div class="row">
    <div class="col-md-4">
        <label for="numero">Numero</label>
        <g:if test="${modoEdicion}">
            <g:field id="numero" type="number" name="numero" value="${escuela?.numero}"/>
        </g:if>
        <g:else>
            <span id="numero">${escuela?.numero}</span>
        </g:else>
    </div>
    <usr:isAdmin>
        <div class="col-md-4">
            <label for="comunaId">Comuna</label>
            <g:select name="comunaId"
                      id="comunaId"
                      from="${fiscalizacionne.Comuna.all}"
                      optionKey="id"
                      optionValue="numero"
                      value="${escuela?.comuna?.id}" noSelection="['': '- Elegir Comuna -']"
                      disabled="${!modoEdicion || escuela.id}"
            />
        </div>
    </usr:isAdmin>

    <div class="col-md-4" id="divFuerzaPolitica">
        <g:set var="nameFuerzaPoliticaSelect" value="fuerzaPoliticaId"/>
        <g:set var="idFuerzaPoliticaSelect" value="fuerzaPoliticaId"/>
        <g:render template="/fuerzaPolitica/fuerzaPoliticaSelect"
                  model="[
                          modoEdicion: modoEdicion,
                          fuerzaPoliticaId: escuela?.fuerzaPolitica?.id,
                          comunaId: escuela?.comuna?.id,
                          name: nameFuerzaPoliticaSelect,
                          id: idFuerzaPoliticaSelect
                  ]"/>
    </div>
</div>
<div class="row">
    <div class="col-md-4">
        <g:render template="mesasTable" model="[mesas: escuela?.mesas, modoEdicion: modoEdicion]"/>
    </div>
</div>

<script type="application/javascript">
    $(document).ready(function () {
        $("#comunaId").change(function () {
            var comunaId = parseInt($("#comunaId").val());
            var url = document.location.origin + "/fuerzaPolitica/getSelectFuerzasByComuna";
            $.ajax({
                url: url,
                data: {
                    comunaId: comunaId,
                    modoEdicion: ${modoEdicion},
                    name: "${nameFuerzaPoliticaSelect}",
                    id: "${idFuerzaPoliticaSelect}"
                },
                success: function (data) {
                    $("#divFuerzaPolitica").html(data)
                }
            });
        })
    })
</script>