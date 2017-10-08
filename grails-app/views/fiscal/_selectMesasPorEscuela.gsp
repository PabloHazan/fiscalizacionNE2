<label for="mesaSeleccionada">Mesa: </label>
<g:select name="idMesa" id="mesaSeleccionada" from="${mesas}"
          optionValue="numero"
          optionKey="id"
          noSelection="['':'-Seleccione una mesa-']"
/>