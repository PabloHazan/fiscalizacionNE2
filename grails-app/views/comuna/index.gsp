<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'comuna.label', default: 'Comuna')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-comuna" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-comuna" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <div class="row">
                <div class="col-md-6">
                    <table class="table table-striped" >
                        <thead>
                        <tr>
                            <th>Comuna</th>
                            <th>Aministrador</th>
                            <th>Escuelas</th>
                        </tr>
                        </thead>
                        <tbody id="tableBody">
                        <g:each in="${comunas}" var="comuna" status="i">
                            <tr>
                                <th><g:link action="show" id="${comuna.id}"><span>${comuna.numero}</span></g:link></th>
                                <th><span>${comuna.admin.username}</span></th>
                                <th>
                                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#escuelasComuna${comuna.numero}" aria-expanded="false" aria-controls="escuelasComuna${comuna.numero}">
                                        <i class="glyphicon glyphicon-chevron-down"></i>
                                    </button>
                                    <ul class="list-group collapse" id="escuelasComuna${comuna.numero}">
                                        <g:each in="${comuna.escuelas}" var="escuela">
                                            <li class="list-group-item">${escuela.numero}</li>
                                        </g:each>
                                    </ul>
                                </th>
                            </tr>
                        </g:each>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </body>
</html>