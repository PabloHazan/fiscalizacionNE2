<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'comuna.label', default: 'Comuna')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-comuna" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-comuna" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.comuna}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.comuna}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save" method="POST">
                <fieldset class="form">

                    <div class="row">
                        <div class="col-md-4">
                            <label for="comunaNumero">Numero</label>
                            <g:textField id="comunaNumero" name="numero" value="${comuna?.numero}"/>
                        </div>
                        <div class="col-md-4">
                            <label for="comunaAdminUsuario">Administrador de comuna</label>
                            <g:textField id="comunaAdminUsuario" name="admin.username" value="${comuna?.admin?.username}"/>
                        </div>
                        <div class="col-md-4">
                            <label for="comunaAdminMail">Numero</label>
                            <g:field type="mail" id="comunaAdminMail" name="admin.mail" value="${comuna?.admin?.mail}"/>
                        </div>
                        <g:hiddenField name="comuna.id" value="${comuna?.id}"/>
                        <g:hiddenField name="comuna.versionValue" value="${comuna?.versionValue}"/>
                        <g:hiddenField name="admin.id" value="${comuna?.admin?.id}"/>
                        <g:hiddenField name="admin.versionValue" value="${comuna?.admin?.versionValue}"/>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <g:render template="escuelasTable" model="[escuelas: comuna?.escuelas]"/>
                        </div>
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
