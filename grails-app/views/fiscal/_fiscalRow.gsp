<tr>
    <th>
        <g:link action="show" id="$fiscal.id">${fiscal.username}</g:link>
    </th>
    <th>${fiscal.mail}</th>
    <th>
        <g:if test="${fiscal.tipo != null}">

        </g:if><g:else>
            <button onclick="asignarTipoFiscal(${fiscal.id})">Agregar fiscalizacion</button>
        </g:else>
    </th>
</tr>