<%@taglib uri="/struts-tags" prefix="s" %>

<!-- cabecera de pagina -->
<div class="d-header">
    <div class="d-title-header">
        <s:property value="tituloOpc" />
        <div class="d-subtitle-header"></div>
    </div>
    <div class="d-subheader">
        <div class="d-back">
            <a href="javascript:void(0)" class="back">Volver</a>
            <form id="frm_back" action="<s:property value="backURL" />" method="post">
                <s:hidden name="varReturn" />
            </form>
        </div>
    </div>
    <div class="d-header-labels">
        <table>
            <tr>
                <td class="lbl-concept">Marca: </td>
                <td class="lbl-value"><span><s:property value="desMar" /><span></td>
                <td class="lbl-concept">Modelo: </td>
                <td class="lbl-value"><span><s:property value="desModMar" /><span></td>
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
    </s:if>
</s:if>
</div>
<div class="d-content-grilla" style="min-width: 660px;">
    <form id="frm_princ" method="POST" action="<s:property value="baseURL" /><s:property value="urlPaginacion" />">
    <s:hidden name="idMar" id="idMar_h1" />
    <s:hidden name="idModMar" id="idModMar_h1" />
    <s:property value="datosOblig" escape="false" />
    
    <div class="d-grilla" style="overflow: hidden;">
        <div class="d-content-grilla-head" style="">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td style="width: 50px; text-align: center;">
                        Código
                    </td>
                    <td style="">
                        Versión
                    </td>
                    <td style="width: 220px;">
                        Katashiki
                    </td>
                    <td style="width: 180px; text-align: center;" title="Prefijo de chasis">
                        Prefijo de Chasis
                    </td>
                    <td style="width: 110px; text-align: center;" title="Prefijo de motor">
                        Prefijo de Motor
                    </td>
                </tr>
                <tr class="tr-head">
                    <td style=""></td>
                    <td style=""></td>
                    <td style=""></td>
                    <td style=""></td>
                    <td style=""></td>
                    <td style=""></td>
                </tr>
            </table>
        </div>
        <div class="d-content-grilla-body">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <s:iterator value="listVersiones">
                <tr>
                    <td style="width: 24px;">
                        <input type="radio" name="idVerMod" id="rbt_idVerMod" value="<s:property value="idVerMod" />" class="select_rec" />
                    </td>
                    <td style="width: 50px; text-align: center;">
                        <s:property value="idVerMod" />
                    </td>
                    <td style="">
                        <s:property value="desVerMod" />
                    </td>
                    <td style="width: 220px;">
                        <s:property value="desKat" />
                    </td>
                    <td style="width: 180px; text-align: center;">
                        <s:property value="desPreCha" />
                    </td>
                    <td style="width: 110px; text-align: center;">
                        <s:property value="desPreMot" />
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
<s:iterator value="errores">
    <s:property />
</s:iterator>

<script type="text/javascript">
    $(document).ready(function() {
        /*Paginacion de grilla*/
        <s:property value="jsPaginacion" escape="false" />

        $('#btn-add').button();
        $('#btn-edit').button();
        $('#btn-delete').button();
        
        $('#DIVeliminar').dialog({
            autoOpen: false,
            width: 400,
            height: 200,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false
        });
        
        $('#btn-add').click(function() {
            $('#opcion_h1').val('A');
            var href = $(location).attr('href');
            
            var _varret = $('#nivBandeja_f').val()+'%'+href+'%'+$('#mtu_h1').val()+'%'+$('#mmo_h1').val()+'%'+$('#mop_h1').val()+'%'+$('#mni_h1').val()+'%'+$('#mod_h1').val()+'%'+$('#curPag_f').val()+'%'+$('#idMar_h1').val()+'%'+$('#idModMar_h1').val()+'|';
            $('#varReturn_f').val($('#varReturn_f').val()+_varret);
            
            $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="versiones" includeContext="false" action="adicionarVersion" />');
            $('#frm_princ').submit();
        });
        
        $('#btn-edit').click(function() {   
            post(
                '<s:property value="baseURL" /><s:url namespace="versiones" includeContext="false" action="vrfSeleccionVersion" />',
                $('#frm_princ').serialize(),
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);                    
                    if(_error != -1)
                    {
                        $('#DIVverif').html(resultado);
                        $('#DIVverif').dialog('open');
                    }
                    else
                    {
                        $('#opcion_h1').val('M');
                        var href = $(location).attr('href');                        
                        var _varret = $('#nivBandeja_f').val()+'%'+href+'%'+$('#mtu_h1').val()+'%'+$('#mmo_h1').val()+'%'+$('#mop_h1').val()+'%'+$('#mni_h1').val()+'%'+$('#mod_h1').val()+'%'+$('#curPag_f').val()+'%'+$('#idMar_h1').val()+'%'+$('#idModMar_h1').val()+'|';
                        $('#varReturn_f').val($('#varReturn_f').val()+_varret);
                                                
                        $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="versiones" includeContext="false" action="modificarVersion" />');
                        $('#frm_princ').submit();
                    }
                },
                1
            );
        });
                
        $('#btn-delete').click(function(){
            post(
                '<s:property value="baseURL" /><s:url namespace="versiones" includeContext="false" action="vrfSeleccionVersion" />',
                $('#frm_princ').serialize(),
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVverif').html(resultado);
                        $('#DIVverif').dialog('open');
                    }
                    else
                    {
                        $('#opcion_h1').val('C');
                        post(
                            '<s:property value="baseURL" /><s:url namespace="versiones" includeContext="false" action="eliminarVersion" />',
                            $('#frm_princ').serialize(),
                            function(resultado1)
                            {
                                var _error1 = validaRespuestaAjax(resultado1);
                                
                                if(_error1 != -1)
                                {
                                    $('#DIVerroresGen').html(resultado1);
                                    $('#DIVerroresGen').dialog('open');
                                }
                                else
                                {
                                    $('#DIVeliminar').dialog({
                                        buttons:{
                                            "Confirmar":function(){
                                                $('#DIVeliminar').dialog('close');
                                                $('#opcion_h1').val('E');
                                                post(
                                                    '<s:property value="baseURL" /><s:url namespace="versiones" includeContext="false" action="eliminarVersion" />',
                                                    $('#frm_princ').serialize(),
                                                    function(resultado2)
                                                    {
                                                        var _error2 = validaRespuestaAjax(resultado2);
                                                        
                                                        if(_error2 != -1)
                                                        {
                                                            $('#DIVerroresGen').html(resultado2);
                                                            $('#DIVerroresGen').dialog('open');
                                                        }
                                                        else
                                                        {
                                                            $('#DIVeliminar').dialog('close');
                                                            $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="versiones" includeContext="false" action="Version" />');
                                                            $('#frm_princ').submit();
                                                        }
                                                    },
                                                    4
                                                );
                                            },
                                            "Cancelar":function(){
                                                $('#DIVeliminar').dialog("close");
                                                hideOverlay(function(){});
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

        $('.d-back a').click(function(){
            $('#frm_back').submit();
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