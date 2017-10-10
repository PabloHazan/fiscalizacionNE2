<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'mesa.label', default: 'Mesa')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-mesa" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-mesa" class="content scaffold-list" role="main">
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
                                Mesa N°
                            </th>
                            <th>
                                Escuela N°
                            </th>
                            <th>
                                Fiscal
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${mesas}" var="mesa">
                            <tr>
                                <th>
                                    <g:link action="show" id="${mesa.id}">
                                        ${mesa.numero}
                                    </g:link>
                                </th>
                                <th>
                                    <g:link controller="escuela" action="show" id="${mesa.escuela.id}">
                                        ${mesa.escuela.numero}
                                    </g:link>
                                </th>
                                <th>
                                    ${mesa.fiscal?.username}
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