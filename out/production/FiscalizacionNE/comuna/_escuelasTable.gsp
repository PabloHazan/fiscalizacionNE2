<table class="table table-striped" >
    <thead>
    <tr></tr>
    <tr>
        <th>Escuela<button id="addEscuela" onclick="addEscuela()"><i class="glyphicon glyphicon-plus"/></button></th>
        <th>Mesa</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${escuelas}" var="escuela" status="i">
        <g:render template="escuelaRow" model="[escuela:escuela, index:i+1]"/>
    </g:each>
    </tbody>
    <g:hiddenField name="escuelas" value="${escuelas?.size() ?: 0}"/>
</table>

<script type="application/javascript">
    function addEscuela(){

    }
</script>