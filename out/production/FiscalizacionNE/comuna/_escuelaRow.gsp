<tr>
    <g:hiddenField name="escuela.id" value="${escuela.id}"/>
    <g:hiddenField name="escuela.versionValue" value="${escuela.versionValue}"/>
    <th>
        ${index}
    </th>
    <th>${fiscal.mail}</th>
    <th>
        <g:if test="${fiscal.tipo != null}">

        </g:if><g:else>
        <button onclick="asignarTipoFiscal(${fiscal.id})">Agregar fiscalizacion</button>
    </g:else>
    </th>
</tr>