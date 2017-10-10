<label for="escuelaSeleccionada">Escuela: </label>
<g:select name="idEscuela" id="escuelaSeleccionada" from="${escuelas}"
          optionValue="numero"
          optionKey="id"
          noSelection="['':'-Seleccione una escuela-']"
          onChange="changeEscuela()"
/>