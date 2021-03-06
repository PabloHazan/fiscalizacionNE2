<div id="asignarFiscal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="AsignarFiscal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="asignarFiscalModalTitle"><g:message code="default.confirmar.title" default="Asignar fiscal"/></h3>
            </div>

            <g:form controller='fiscal' action='asignarFiscal' method="POST">
                <div class="modal-body">
                    <div class="row">
                        <g:hiddenField name="idFiscalSeleccionado" id="idFiscalSeleccionado"/>
                        <div class="row">
                            <div class="col-md-6">
                                <label for="tipoFiscalSeleccionado"><g:message code="fiscal.seleccionarTipo.message"
                                                                               default="Asigne un tipo de fiscal"/></label>
                                <g:select id="tipoFiscalSeleccionado" name="tipoFiscal" from="${fiscalizacionne.TipoFiscalEnum}"
                                          optionValue="nombre"
                                          optionKey="authority"
                                          onChange="changedTipo()"
                                          noSelection="['':'-Seleccione un tipo-']"
                                />
                            </div>
                            <div id="seccionGeneral" class="col-md-6">

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <div id="seccionMesa" class="col-md-6"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">

                    <g:submitButton name="demorarGuia" class="btn btn-primary"
                                    value='${message(code:"default.modal.button.accept",default:"Aceptar")}'>
                    </g:submitButton>

                    <button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">
                        <g:message code="default.button.cancel.label" default="Cancel"/>
                    </button>
                </div>
            </g:form>
        </div>
    </div>
</div>

<script type="application/javascript">
    $(document).ready(function(){

    });

    function asignarTipoFiscal(idFiscal){
        $('#idFiscalSeleccionado').val(idFiscal);
        iniciarModalAsignacionFiscal()
    }

    function iniciarModalAsignacionFiscal() {
        changedTipo();
        $('#asignarFiscal').modal('show');

    }

    function changedTipo(){
        var tipoSeleccionado = $('#tipoFiscalSeleccionado').val();
        if(tipoSeleccionado == '${fiscalizacionne.TipoFiscalEnum.GENERAL.authority}'){
            obtenerEscuelas(tipoSeleccionado);
            mostrarSeccionGeneral();

        } else if (tipoSeleccionado == '${fiscalizacionne.TipoFiscalEnum.MESA.authority}'){
            obtenerEscuelas(tipoSeleccionado);
            mostrarSeccionGeneral();
            changeEscuela();
        } else {
            ocultarTodo()
        }
    }

    function mostrarSeccionGeneral(){
        $('#seccionMesa').hide();
        $('#seccionGeneral').show();
    }

    function ocultarTodo() {
        $('#seccionMesa').hide();
        $('#seccionGeneral').hide();
    }

    function obtenerEscuelas(tipoSeleccionado){
        var url = "${createLink(controller: 'fiscal', action: 'getEscuelasPorTipoFiscal')}";
        $.ajax({
            url: url,
            data: {
                authority: tipoSeleccionado
            },
            success: function (data) {
                $("#seccionGeneral").html(data);
                $("#seccionGeneral").show();
            }
        });
    }

    function changeEscuela() {
        var tipoSeleccionado = $('#tipoFiscalSeleccionado').val();
        if (tipoSeleccionado == '${fiscalizacionne.TipoFiscalEnum.MESA.authority}'){
            var url = "${createLink(controller: 'fiscal', action: 'getMesasPorEscuela')}";
            var escuelaId = $("#escuelaSeleccionada").val();
            if (escuelaId){
                $.ajax({
                    url: url,
                    data: {
                        escuelaId: escuelaId
                    },
                    success: function (data) {
                        $("#seccionMesa").html(data);
                        $("#seccionMesa").show();
                    }
                });
            }
        }
    }

</script>