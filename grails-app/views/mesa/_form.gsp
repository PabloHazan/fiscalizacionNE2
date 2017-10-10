<div class="row">
    <div class="col-md-4">
        <label for="numero">Mesa N°</label>
        <g:field type="number" id="numero"
                 name="numero" value="${mesa?.numero}"
                 disabled="${!modoEdicion}"/>
    </div>
    <div class="col-md-4">
        <label for="escuela">Escuela N°</label>
        <g:select name="escuelaId"
                  id="escuela"
                  from="${fiscalizacionne.Escuela.all}"
                  disabled="${!modoEdicion}"
                  value="${mesa?.escuela?.id}"
                  optionKey="id"
                  optionValue="numero"
                  noSelection="['': '-- Elija una escuela --']"
        />
    </div>
    <div class="col-md-4">
        <label for="fiscal">Fiscal</label>
        <g:if test="${modoEdicion}">
            <g:select name="fiscalId"
                      id="fiscal"
                      from="${fiscales}"
                      disabled="${!modoEdicion}"
                      value="${mesa?.fiscal?.id}"
                      optionKey="id"
                      optionValue="username"
                      noSelection="['': '-- Elija un fiscal --']"
            />
        </g:if>
        <g:else>
            <span>${mesa?.fiscal?.username}</span>
        </g:else>
    </div>
</div>