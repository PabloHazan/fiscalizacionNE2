<tr id="escuelaRow${index}">
    <g:hiddenField name="escuelas.${index}.id" value="${escuela?.id}"/>
    <g:hiddenField name="escuelas.${index}.versionValue" value="${escuela?.versionValue}"/>
    <th>
        <g:if test="${modoEdicion}">
            <g:field type="number" name="escuelas.${index}.numero" value="${escuela?.numero}" />
        </g:if>
        <g:else>
            <span>${escuela?.numero}</span>
        </g:else>
    </th>
    <th>
        <g:if test="${modoEdicion}">
            <button class="btn btn-danger"
                    type="button"
                    onclick="deleteRow(${index})"
            >
                <i class="glyphicon glyphicon-remove"></i>
            </button>
        </g:if>
    </th>
</tr>