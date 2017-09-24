<div class="row">
    <table class="table table-striped" >
        <thead>
            <tr>
                <th>Usuario</th>
                <th>Mail</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <g:each in="${fiscales}" var="fiscal" status="i">
                <g:set var="css_class" value="${ (i % 2) == 0 ? 'a' : 'b'}" />
                <g:render template="fiscalRow" model="[fiscal:fiscal, class: css_class]"/>
            </g:each>
        </tbody>
    </table>
</div>