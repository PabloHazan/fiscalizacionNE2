<tr id="mesaRow${index}">
    <g:hiddenField name="mesas.${index}.id" value="${mesa?.id}"/>
    <g:hiddenField name="mesas.${index}.versionValue" value="${mesa?.versionValue}"/>
    <th>
        <g:if test="${modoEdicion}">
            <g:field type="number" name="mesas.${index}.numero" value="${mesa?.numero}" />
        </g:if>
        <g:else>
            <span>${mesa?.numero}</span>
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