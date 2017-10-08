<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'fuerzaPolitica.label', default: 'FuerzaPolitica')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-fuerzaPolitica" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-fuerzaPolitica" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <div class="row">
                <div class="col-md-6">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>
                                    Fuerza Política
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <g:each in="${fuerzasPoliticas}" var="fuerzaPolitica">
                            <tr>
                                <th style="background: #${fuerzaPolitica?.color ? Long.toHexString(fuerzaPolitica?.color) : 'FFFFFF'};">
                                    <g:link action="show" id="${fuerzaPolitica.id}">
                                        ${fuerzaPolitica.nombre}
                                    </g:link>
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