<%@taglib uri="/struts-tags" prefix="s" %>

<!-- cabecera de pagina -->
<div class="d-header">
    <div class="d-title-header">
        <s:property value="tituloOpc" />
        <div class="d-subtitle-header"></div>
    </div>
    <div class="d-header-filters">
        <table>
            <tr>
                <td class="">Origen: </td>
                <td class="">
                    <s:select name="idOriCam" id="idOriCam_f1" list="listTipOri" listKey="idOriCam" listValue="desOriCam"
                              cssClass="element-form" cssStyle="width: 120px;"/>
                </td>
            </tr>
        </table>
    </div>
</div>

<!-- modelo de grilla -->
<div class="d-buttons-grid">
    <s:if test='%{perm=="M" || perm=="V"}'>
        <s:if test='%{perm=="M"}'>
            <button id="btn-add">
                Adicionar
            </button>
            <button id="btn-edit">
                Modificar
            </button>
            <button id="btn-delete">
                Eliminar
            </button>
            <button id="btn-condiciones">
                Condiciones
            </button>
        </s:if>
    </s:if>
</div>
<div class="d-content-grilla" style="min-width: 660px;">
    <form id="frm_princ" method="POST" action="<s:property value="baseURL" /><s:property value="urlPaginacion" />">
        <s:hidden name="idOriCam" id="idOriCam_h1" />
        <s:property value="datosOblig" escape="false" />

        <div class="d-grilla" style="overflow: hidden;">
            <div class="d-content-grilla-head" style="">
                <table border="0" cellpadding="0" cellspacing="0" style="">
                    <tr class="tr-head">
                        <td style="width: 24px;"></td>
                        <td style="width:60px; text-align: center;">
                            Código
                        </td>
                        <td style="">
                            Descripción
                        </td>
                        <td style="width:220px; text-align: center;">
                            Descripción para Impresión
                        </td>
                        <td style="width:60px; text-align: center;">
                            Moneda
                        </td>
                        <td style="width:90px; text-align: center;">
                            Importe
                        </td>
                    </tr>
                    <tr class="tr-head">
                        <td style="width: 24px;"></td>
                        <td style="width:60px; text-align: center;"></td>
                        <td style=""></td>
                        <td style="width:220px; text-align: center;"></td>
                        <td style="width:60px; text-align: center;"></td>
                        <td style="width:90px; text-align: center;"></td>
                    </tr>
                </table>
            </div>
            <div class="d-content-grilla-body">
                <table border="0" cellpadding="0" cellspacing="0" style="">
                    <s:iterator value="listCam">
                        <tr>
                            <td style="width: 24px;">
                                <input type="radio" name="idCam" id="rbt_idCam" value="<s:property value="idCam" />" class="select_rec" />
                            </td>
                            <td style="width:60px; text-align: center;">
                                <s:property value="idCam" />
                            </td>
                            <td style="">
                                <s:property value="desCam" />
                            </td>
                            <td style="width:220px; text-align: center;">
                                <s:property value="desCamImp" />
                            </td>
                            <td style="width:60px; text-align: center;">
                                <s:if test='%{codMonCam==1}'>
                                    Soles
                                </s:if>
                                <s:if test='%{codMonCam==2}'>
                                    Dolares
                                </s:if>
                            </td>
                            <td style="width:90px; text-align: center;">
                                <s:property value="impRelCam" />
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

        $('#btn-add').button();
        $('#btn-edit').button();
        $('#btn-delete').button();
        $('#btn-condiciones').button();

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

        $('#idOriCam_f1').change(function() {
            $('#idOriCam_h1').val($(this).val());
            $('#frm_princ').submit();
        });

        $('#btn-add').click(function() {
            $('#opcion_h1').val('A');
            var href = $(location).attr('href');
            var _varret = $('#nivBandeja_f').val() + '%' + href + '%' + $('#mtu_h1').val() + '%' + $('#mmo_h1').val() + '%' + $('#mop_h1').val() + '%' + $('#mni_h1').val() + '%' + $('#mod_h1').val() + '%' + $('#curPag_f').val() + '%' + $('#idOriCam_h1').val() + '|';
            $('#varReturn_f').val($('#varReturn_f').val() + _varret);
            $('#frm_princ').attr('action', '<s:property value="baseURL" /><s:url namespace="campania" includeContext="false" action="adicionarCampania" />');
            $('#frm_princ').submit();
        });

        $('#btn-edit').click(function() {
            post(
                    '<s:property value="baseURL" /><s:url namespace="campania" includeContext="false" action="vrfSeleccionCampania" />',
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

                            var _varret = $('#nivBandeja_f').val() + '%' + href + '%' + $('#mtu_h1').val() + '%' + $('#mmo_h1').val() + '%' + $('#mop_h1').val() + '%' + $('#mni_h1').val() + '%' + $('#mod_h1').val() + '%' + $('#curPag_f').val() + '%' + $('#idOriCam_h1').val() + '|';
                            $('#varReturn_f').val($('#varReturn_f').val() + _varret);

                            $('#frm_princ').attr('action', '<s:property value="baseURL" /><s:url namespace="campania" includeContext="false" action="modificarCampania" />');
                            $('#frm_princ').submit();
                        }
                    },
                    1
                    );
        });

        $('#btn-delete').click(function() {
            post(
                    '<s:property value="baseURL" /><s:url namespace="campania" includeContext="false" action="vrfSeleccionCampania" />',
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
                                    '<s:property value="baseURL" /><s:url namespace="campania" includeContext="false" action="eliminarCampania" />',
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
                                                                '<s:property value="baseURL" /><s:url namespace="campania" includeContext="false" action="eliminarCampania" />',
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
                                                                        $('#frm_princ').attr('action', '<s:property value="baseURL" /><s:url namespace="campania" includeContext="false" action="Campania" />');
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
        $('#btn-condiciones').click(function() {
            post(
                    '<s:property value="baseURL" /><s:url namespace="campania" includeContext="false" action="vrfSeleccionCampania" />',
                    $('#frm_princ').serialize(),
                    function(resultado) {
                        resultado = $.trim(resultado);
                        var _error = resultado.indexOf("error");
                        if (_error != -1)
                        {
                            $('#DIVverif').html(resultado);
                            $('#DIVverif').dialog('open');
                        }
                        else
                        {
                            var href = $(location).attr('href');
                            var _varret = $('#nivBandeja_f').val() + '%' + href + '%' + $('#mtu_h1').val() + '%' + $('#mmo_h1').val() + '%' + $('#mop_h1').val() + '%' + $('#mni_h1').val() + '%' + $('#mod_h1').val() + '%' + $('#curPag_f').val() + '%' + $('#idOriCam_h1').val() + '|';
                            $('#varReturn_f').val($('#varReturn_f').val() + _varret);
                            $('#curPag_f').val(1);
                            $('#frm_princ').attr('action', '<s:property value="baseURL" /><s:url namespace="condicionCampania" includeContext="false" action="CondicionCampania" />');
                            $('#frm_princ').submit();
                        }
                    },
                    1
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