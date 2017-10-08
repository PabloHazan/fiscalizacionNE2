<tr id="fuerzaPoliticaRow${index}">
    <g:hiddenField name="fuerzasPoliticas.${index}.id" value="${fuerzaPolitica?.id}"/>
    <g:hiddenField name="fuerzasPoliticas.${index}.version" value="${fuerzaPolitica?.version}"/>
    <g:hiddenField name="fuerzasPoliticas.${index}.color" value="${fuerzaPolitica?.color}"/>

    <g:set var="isNew" value="${!fuerzaPolitica?.id}"/>
    <th
        style="background: #${fuerzaPolitica?.color ? Long.toHexString(fuerzaPolitica?.color) : 'FFFFFF'};">
        <g:if test="${isNew}">
            <g:checkBox name="fuerzasPoliticas.${index}.cbPertenece" checked="${true}" disabled="${true}"/>
            <g:textField  name="fuerzasPoliticas.${index}.nombre" value="${fuerzaPolitica?.nombre}"/>
            <g:hiddenField name="fuerzasPoliticas.${index}.cbPertenece" value="on"/>
            <button type="button" onclick="deleteRowFuerzaPolitica(${index})" class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i></button>
        </g:if>
        <g:else>
            <g:hiddenField name="fuerzasPoliticas.${index}.nombre" value="${fuerzaPolitica?.nombre}" />
            <g:checkBox name="fuerzasPoliticas.${index}.cbPertenece" checked="${isChecked}" disabled="${!modoEdicion}"/>
            <span>${fuerzaPolitica?.nombre}</span>
        </g:else>
    </th>
</tr>