<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fiscal.label', default: 'Fiscal')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-fiscal" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-fiscal" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.fiscal}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.fiscal}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.fiscal}" method="POST" controller="fiscal" action="save">
                <fieldset class="form">
                    <div class="col-md-4">
                        <label for="username">Nombre de usuiario</label>
                        <g:textField id="username" name="username" value="${this.fiscal?.username}"/>
                    </div>
                    <div class="col-md-4">
                        <label for="mail">Email</label>
                        <g:field type="email" id="mail" name="mail" value="${this.fiscal?.mail}"/>
                    </div>
                    <div class="col-md-4">
                        <label for="tipo">Tipo</label>
                        <g:select name="tipoFiscal" from="${fiscalizacionne.TipoFiscalEnum}"
                                  optionValue="nombre"
                        />
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
