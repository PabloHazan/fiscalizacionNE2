<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'fiscal.label', default: 'Fiscal')}" />
    <title><g:message code="default.edit.label" args="[entityName]" /></title>
</head>
<body>
<a href="#edit-fiscal" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="edit-fiscal" class="content scaffold-edit" role="main">
    <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
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
    <g:form method="POST" action="updatePass">
        <g:render template="fiscalForm" model="[fiscal:fiscal, modoEdicion:false]"/>
        <label for="oldPass">Password anterior</label>
        <g:passwordField id="oldPass" name="oldPass"/>
        <label for="newPass">Password nuevo</label>
        <g:passwordField id="newPass" name="newPass"/>
        <label for="repeatPass">Repita nuevo password</label>
        <g:passwordField id="repeatPass" name="repeatPass"/>
        <fieldset class="buttons">
            <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
