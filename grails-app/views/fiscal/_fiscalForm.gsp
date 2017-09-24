<g:hiddenField name="id" value="${fiscal?.id}"/>
<g:hiddenField name="versionValue" value="${fiscal?.versionValue}"/>
<div class="row">
    <fieldset class="form">
        <div class="col-md-4">
            <label for="username">Nombre de usuiario</label>
            <g:if test="${modoEdicion && !fiscal?.id}">
                <g:textField id="username" name="username" value="${this.fiscal?.username}"/>
            </g:if><g:else>
                <g:hiddenField name="username" value="${this.fiscal?.username}"/>
                <span id="username">${this.fiscal?.username}</span>
            </g:else>
        </div>
        <div class="col-md-4">
            <label for="mail">Email</label>
            <g:if test="${modoEdicion}">
                <g:field type="email" id="mail" name="mail" value="${this.fiscal?.mail}"/>
            </g:if><g:else>
                <span id="mail">${this.fiscal?.mail}</span>
            </g:else>
        </div>
        <div class="col-md-4">
        <label for="tipo">Tipo</label>
        %{--<g:if test="${modoEdicion}">--}%
            %{--<g:select id="tipo" name="tipoFiscal" from="${fiscalizacionne.TipoFiscalEnum}"--}%
                      %{--optionValue="nombre"--}%
            %{--/>--}%
        %{--</g:if><g:else>--}%
            <span id="tipo">HARDCODEADO</span>
        %{--</g:else>--}%
        </div>
    </fieldset>
</div>