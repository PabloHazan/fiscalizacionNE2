<div class="row">
    <div class="col-md-4">
        <label for="comunaNumero">Numero</label>
        <g:if test="${modoEdicion && !comuna.id}">
            <g:textField id="comunaNumero" name="numero" value="${comuna?.numero}"/>
        </g:if>
        <g:else>
            <span id="comunaNunero">${comuna?.numero}</span>
            <g:hiddenField name="numero" value="${comuna?.numero}"/>
        </g:else>
    </div>
    <div class="col-md-4">
        <label for="comunaAdminUsuario">Administrador</label>
        <g:if test="${modoEdicion && !comuna.id}">
            <g:textField id="comunaAdminUsuario" name="admin.username" value="${comuna?.admin?.username}"/>
        </g:if>
        <g:else>
            <span id="comunaAdminUsuario">${comuna?.admin?.username}</span>
        </g:else>
    </div>
    <div class="col-md-4">
        <label for="comunaAdminMail">Mail</label>
        <g:if test="${modoEdicion && !comuna.id}">
            <g:field type="mail" id="comunaAdminMail" name="admin.mail" value="${comuna?.admin?.mail}"/>
        </g:if>
        <g:else>
            <span id="comunaAdminMail">${comuna?.admin?.mail}</span>
        </g:else>
    </div>
    <g:hiddenField name="id" value="${comuna?.id}"/>
    <g:hiddenField name="versionValue" value="${comuna?.versionValue}"/>
    <g:hiddenField name="admin.id" value="${comuna?.admin?.id}"/>
    <g:hiddenField name="admin.versionValue" value="${comuna?.admin?.versionValue}"/>
</div>
<div class="row">
    <div class="col-md-6">
        <g:render template="escuelasTable" model="[escuelas: comuna?.escuelas, modoEdicion: modoEdicion]"/>
    </div>
    <div class="col-md-4 pull-right">
        <g:render template="fuerzasPoliticasTable" model="[fuerzasPoliticasDeComuna: comuna.fuerzasPoliticas, modoEdicion: modoEdicion]"/>
    </div>
</div>