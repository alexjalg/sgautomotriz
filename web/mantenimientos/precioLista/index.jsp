<%@taglib uri="/struts-tags" prefix="s" %>

<!-- cabecera de pagina -->
<div class="d-header">
    <div class="d-title-header">
        <s:property value="tituloOpc" />
        <div class="d-subtitle-header"></div>
    </div>
</div>
<div class="d-header-filters">
    <table>
        <tr>
            <td class="">Marca: </td>
            <td class="">
                <s:select name="idMar" id="cbo_Marca" list="listMarcas" listKey="idMar" listValue="desMar"
                          cssClass="element-form" cssStyle="width: 120px;"/>
            </td>
            <td class="">Modelo: </td>
            <td id="td_Modelo">
                <s:select name="idModMar" id="cbo_Modelo" list="listModelo" listKey="idModMar" listValue="desModMar" headerKey="0" headerValue="-Seleccione-"
                          cssClass="element-form" cssStyle="width:200px;"/>
            </td>
            <td class="">A�o: </td>
            <td class="">
                <s:select name="numAnoLis" id="cbo_Anio" list="listAnios" listKey="idAnio" listValue="idAnio"
                          cssClass="element-form" cssStyle="width: 100px;"/>
            </td>
        </tr>
    </table>
</div>
<!-- modelo de grilla -->
<div class="d-buttons-grid">
    <s:if test='%{perm=="M" || perm=="V"}'>
        <s:if test='%{perm=="M"}'>
            <button id="btn-edit">
                Modificar
            </button>
        </s:if>
    </s:if>
</div>
<div class="d-content-grilla" style="min-width: 660px;">
    <form id="frm_princ" method="POST" action="<s:property value="baseURL" /><s:property value="urlPaginacion" />">
        <s:hidden name="idMar" id="idMar_h1" />
        <s:hidden name="idModMar" id="idModMar_h1" />
        <s:hidden name="numAnoLis" id="numAnoLis_h1" />
        <s:property value="datosOblig" escape="false" />

        <div class="d-grilla" style="overflow: hidden;">
            <div class="d-content-grilla-head" style="">
                <table border="0" cellpadding="0" cellspacing="0" style="">
                    <tr class="tr-head">
                        <td style="width: 24px;"></td>
                        <td style="width:60px; text-align: center;">
                            C�digo
                        </td>
                        <td style="">
                            Versi�n
                        </td>
                        <td style="width:100px; text-align: center;">
                            Lista
                        </td>
                        <td style="width:100px; text-align: center;">
                            Minimo
                        </td>
                        <td style="width:100px; text-align: center;">
                            Flota
                        </td>
                    </tr>
                    <tr class="tr-head">
                        <td style="width: 24px;"></td>
                        <td style="width:60px; text-align: center;">
                        </td>
                        <td style="">
                        </td>
                        <td style="width:100px; text-align: center;">
                        </td>
                        <td style="width:100px; text-align: center;">
                        </td>
                        <td style="width:100px; text-align: center;">
                        </td>
                    </tr>
                </table>
            </div>
            <div class="d-content-grilla-body">
                <table border="0" cellpadding="0" cellspacing="0" style="">
                    <s:iterator value="listPrecioLista">
                        <tr>
                            <td style="width: 24px;">
                                <input type="radio" name="idVerMod" id="rbt_idVerMod" value="<s:property value="idVerMod" />" class="select_rec" />
                            </td>
                            <td style="width:60px; text-align: center;">
                                <s:property value="idVerMod" />
                            </td>
                            <td style="">
                                <s:property value="desVerMod" />
                            </td>
                            <td style="width:100px; text-align: center;">
                                <s:property value="impPreLis" />
                            </td>
                            <td style="width:100px; text-align: center;">
                                <s:property value="impPreMin" />
                            </td>
                            <td style="width:100px; text-align: center;">
                                <s:property value="impPreFlo" />
                            </td>     
                        </tr>
                    </s:iterator>
                </table>
            </div>
            <div class="d-content-grilla-footer">
                <div class="d-paging">
                    <s:property value="paginacion" escape="false" />
                </div>
            </div>
        </div>
    </form>
</div>
<div id="DIVeliminar" title="<s:property value="titleDialog" />" class="alerta"></div>

<script type="text/javascript">
    $(document).ready(function() {
        /*Paginacion de grilla*/
    <s:property value="jsPaginacion" escape="false" />
        $('#btn-edit').button();
        $('#btn_search').css('visibility', 'hidden');

        $('#DIVeliminar').dialog({
            autoOpen: false,
            width: 400,
            height: 200,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) {
                $(".ui-dialog-titlebar-close").hide();
            },
            draggable: false,
            resizable: false
        });

        $('#cbo_Marca').change(function() {
            var _idMar = $(this).val();
            $('#idMar_h1').val($(this).val());
			$('#idModMar_h1').val("");
            $.post("populateModeloPrecioLista.action",
                    {
                        idMar: _idMar
                    }, function(resultado) {
                $('#td_Modelo').css('text-align', 'left');
                $('#td_Modelo').html(resultado);
            });
        });


        $('#cbo_Modelo').change(function() {
            $('#idModMar_h1').val($(this).val());
            $('#frm_princ').submit();
        });

        $('#cbo_Anio').change(function() {
            $('#numAnoLis_h1').val($(this).val());
            $('#frm_princ').submit();
        });


        $('#btn-add').click(function() {
            $('#opcion_h1').val('A');
            var href = $(location).attr('href');
            var _varret = $('#nivBandeja_f').val() + '%' + href + '%' + $('#mtu_h1').val() + '%' + $('#mmo_h1').val() + '%' + $('#mop_h1').val() + '%' + $('#mni_h1').val() + '%' + $('#mod_h1').val() + '%' + $('#curPag_f').val() + '%' + $('#idMar_h1').val() + '%' + $('#idModMar_h1').val() + '%' + $('#numAnoLis_h1').val() + '|';
            $('#varReturn_f').val($('#varReturn_f').val() + _varret);
            $('#frm_princ').attr('action', '<s:property value="baseURL" /><s:url namespace="precioLista" includeContext="false" action="adicionarPrecioLista" />');
            $('#frm_princ').submit();
        });

        $('#btn-edit').click(function() {
            idMar = $('#cbo_Marca').val();
            idModMar = $('#cbo_Modelo').val();
            post(
                    '<s:property value="baseURL" /><s:url namespace="precioLista" includeContext="false" action="vrfSeleccionPrecioLista" />',
                    $('#frm_princ').serialize(),
                    function(resultado) {
                        var _error = validaRespuestaAjax(resultado);

                        if (_error != -1)
                        {
                            $('#DIVverif').html(resultado);
                            $('#DIVverif').dialog('open');
                        }
                        else
                        {
                            $('#opcion_h1').val('M');
                            var href = $(location).attr('href');

                            var _varret = $('#nivBandeja_f').val() + '%' + href + '%' + $('#mtu_h1').val() + '%' + $('#mmo_h1').val() + '%' + $('#mop_h1').val() + '%' + $('#mni_h1').val() + '%' + $('#mod_h1').val() + '%' + $('#curPag_f').val() + '%' + $('#idMar_h1').val() + '%' + $('#idModMar_h1').val() + '%' + $('#numAnoLis_h1').val() + '|';
                            $('#varReturn_f').val($('#varReturn_f').val() + _varret);

                            $('#frm_princ').attr('action', '<s:property value="baseURL" /><s:url namespace="precioLista" includeContext="false" action="modificarPrecioLista" />');
                            $('#frm_princ').submit();
                        }
                    },
                    1
                    );
        });

        $('#btn-delete').click(function() {
            post(
                    '<s:property value="baseURL" /><s:url namespace="tipoLocal" includeContext="false" action="vrfSeleccionTipoLocal" />',
                    $('#frm_princ').serialize(),
                    function(resultado) {
                        var _error = validaRespuestaAjax(resultado);

                        if (_error != -1)
                        {
                            $('#DIVverif').html(resultado);
                            $('#DIVverif').dialog('open');
                        }
                        else
                        {
                            $('#opcion_h1').val('C');
                            post(
                                    '<s:property value="baseURL" /><s:url namespace="precioLista" includeContext="false" action="eliminarPrecioLista" />',
                                    $('#frm_princ').serialize(),
                                    function(resultado1)
                                    {
                                        var _error1 = validaRespuestaAjax(resultado1);

                                        if (_error1 != -1)
                                        {
                                            $('#DIVerroresGen').html(resultado1);
                                            $('#DIVerroresGen').dialog('open');
                                        }
                                        else
                                        {
                                            $('#DIVeliminar').dialog({
                                                buttons: {
                                                    "Confirmar": function() {
                                                        $('#DIVeliminar').dialog('close');
                                                        $('#opcion_h1').val('E');
                                                        post(
                                                                '<s:property value="baseURL" /><s:url namespace="tipoLocal" includeContext="false" action="eliminarTipoLocal" />',
                                                                $('#frm_princ').serialize(),
                                                                function(resultado2)
                                                                {
                                                                    var _error2 = validaRespuestaAjax(resultado2);

                                                                    if (_error2 != -1)
                                                                    {
                                                                        $('#DIVerroresGen').html(resultado2);
                                                                        $('#DIVerroresGen').dialog('open');
                                                                    }
                                                                    else
                                                                    {
                                                                        $('#DIVeliminar').dialog('close');
                                                                        $('#frm_princ').attr('action', '<s:property value="baseURL" /><s:url namespace="precioLista" includeContext="false" action="PrecioLista" />');
                                                                        $('#frm_princ').submit();
                                                                    }
                                                                },
                                                                4
                                                                );
                                                    },
                                                    "Cancelar": function() {
                                                        $('#DIVeliminar').dialog("close");
                                                        hideOverlay(function() {
                                                        });
                                                    }
                                                }
                                            });
                                            $('#DIVeliminar').html(resultado1);
                                            $('#DIVeliminar').dialog('open');
                                        }
                                    },
                                    3
                                    );
                        }
                    },
                    2
                    );
        });
    });
    //Redimensionar el alto del contenedor de grilla si fuese necesario
    resizeContGrilla(<s:property value="regPag" />);

    function hideOptGrilla()
    {
        $('.boton-bandeja-click').removeClass('boton-bandeja-click');
        $('#d-filtros-float').css('display', 'none');
    }
</script>