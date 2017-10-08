<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'escuela.label', default: 'Escuela')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-escuela" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-escuela" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <div class="row">
                <div class="col-md-8">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>
                                Escuela
                            </th>
                            <th>
                                Fuerza responsable
                            </th>
                            <th>
                                Fiscal
                            </th>
                            <th>
                                Mesas
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${escuelas}" var="escuela">
                            <tr>
                                <th>
                                    <g:link action="show" id="${escuela.id}">
                                        ${escuela.numero}
                                    </g:link>
                                </th>
                                <th>
                                    ${escuela.fuerzaPolitica?.nombre}
                                </th>
                                <th>
                                    ${escuela.fiscal?.username}
                                </th>
                                <th>
                                    <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#mesasEscuela${escuela.numero}" aria-expanded="false" aria-controls="mesasEscuela${escuela.numero}">
                                        <i class="glyphicon glyphicon-info-sign"></i>
                                    </button>
                                    <ul class="list-group collapse" id="mesasEscuela${escuela.numero}">
                                        <g:each in="${escuela.mesas}" var="mesa">
                                            <li class="list-group-item">${mesa.numero}</li>
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