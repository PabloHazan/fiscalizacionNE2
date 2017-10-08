<%@ page import="fiscalizacionne.Comuna" %>
<%@ page import="fiscalizacionne.FuerzaPolitica" %>
<g:if test="${comunaId}">
    <div class="row">
        <div class="col-md-12">
            <label>Fuerza</label>
            <g:select name="${name?:'fuerzaPoliticaId'}"
                      id="${id?:'fuerzaPoliticaId'}"
                      from="${Comuna.get(comunaId).fuerzasPoliticas}"
                      optionKey="id"
                      optionValue="nombre"
                      value="${fuerzaPoliticaId}" noSelection="['': '- Elegir Fuerza Política -']"
                      disabled="${!modoEdicion}"
            />
        </div>
    </div>
</g:if>