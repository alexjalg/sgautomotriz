<%@taglib uri="/struts-tags" prefix="s" %>

<!-- cabecera de pagina -->
<div class="d-header">
    <div class="d-title-header">
        <s:property value="tituloOpc" />
        <div class="d-subtitle-header"></div>
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
    <button id="btn-permisos">
        Permisos
    </button>    
</s:if>
</div>
<div class="d-content-grilla" style="min-width: 660px;">
    <form id="frm_princ" method="POST" action="<s:property value="baseURL" /><s:property value="urlPaginacion" />">
    <s:property value="datosOblig" escape="false" />

    <s:hidden name="curPagVis" id="curPag_f" />
    
    <s:hidden name="varReturn" id="varReturn_f" />
    <s:hidden name="nivBandeja" id="nivBandeja_f" />
    
    <div class="d-grilla" style="overflow: hidden;">
        <div class="d-content-grilla-head" style="">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td style="width:60px; text-align: center;">
                        C�digo
                    </td>
                    <td style="">
                        Tipo de Usuario
                    </td>
                </tr>
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </div>
        <div class="d-content-grilla-body">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <s:iterator value="listTipoUsuario">
                <tr>
                    <td style="width: 24px;">
                        <input type="radio" name="idTipUsu" id="rbt_idTipUsu" value="<s:property value="idTipUsu" />" class="select_rec" />
                    </td>
                    <td style="width:60px; text-align: center;">
                        <s:property value="idTipUsu" />
                    </td>
                    <td style="">
                        <s:property value="desTipUsu" />
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
        $('#btn-permisos').button();
        
        //$('#btn-provincias').button('disable');
        $('#btn_search').css('visibility','hidden');
    
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
            
            var _varret = $('#nivBandeja_f').val()+'%'+href+'%'+$('#mtu_h1').val()+'%'+$('#mmo_h1').val()+'%'+$('#mop_h1').val()+'%'+$('#mni_h1').val()+'%'+$('#mod_h1').val()+'%'+$('#curPag_f').val()+'|';
            $('#varReturn_f').val($('#varReturn_f').val()+_varret);
                        
            $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="tipoUsuario" includeContext="false" action="adicionarTipoUsuario" />');
            $('#frm_princ').submit();
        });
        
        $('#btn-edit').click(function() {
            post(
                '<s:property value="baseURL" /><s:url namespace="tipoUsuario" includeContext="false" action="vrfSeleccionTipoUsuario" />',
                $('#frm_princ').serialize(),
                function(resultado){
                    resultado = $.trim(resultado);
                    var _error = resultado.indexOf("error");
                    if(_error != -1)
                    {
                        $('#DIVverif').html(resultado);
                        $('#DIVverif').dialog('open');
                    }
                    else
                    {
                        $('#opcion_h1').val('M');
                        var href = $(location).attr('href');
                        
                        var _varret = $('#nivBandeja_f').val()+'%'+href+'%'+$('#mtu_h1').val()+'%'+$('#mmo_h1').val()+'%'+$('#mop_h1').val()+'%'+$('#mni_h1').val()+'%'+$('#mod_h1').val()+'%'+$('#curPag_f').val()+'|';
                        $('#varReturn_f').val($('#varReturn_f').val()+_varret);
                        
                        $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="tipoUsuario" includeContext="false" action="adicionarTipoUsuario" />');
                        $('#frm_princ').submit();
                    }
                },
                1
            );
        });
        
        $('#btn-delete').click(function(){
            post(
                '<s:property value="baseURL" /><s:url namespace="tipoUsuario" includeContext="false" action="vrfSeleccionTipoUsuario" />',
                $('#frm_princ').serialize(),
                function(resultado){
                    resultado = $.trim(resultado);
                    var _error = resultado.indexOf("error");
                    if(_error != -1)
                    {
                        $('#DIVverif').html(resultado);
                        $('#DIVverif').dialog('open');
                    }
                    else
                    {
                        $('#opcion_h1').val('C');
                        post(
                            '<s:property value="baseURL" /><s:url namespace="tipoUsuario" includeContext="false" action="eliminarTipoUsuario" />',
                            $('#frm_princ').serialize(),
                            function(resultado1)
                            {
                                resultado1 = $.trim(resultado1);
                                var _error1 = resultado1.indexOf("error");
                                if(_error1 != -1)
                                {
                                    $('#DIVeliminar').html(resultado1);
                                    $('#DIVeliminar').dialog('open');
                                }
                                else
                                {
                                    $('#DIVeliminar').dialog({
                                        buttons:{
                                            "Confirmar":function(){
                                                $('#DIVeliminar').dialog('close');
                                                $('#opcion_h1').val('E');
                                                post(
                                                    '<s:property value="baseURL" /><s:url namespace="tipoUsuario" includeContext="false" action="eliminarTipoUsuario" />',
                                                    $('#frm_princ').serialize(),
                                                    function(resultado2)
                                                    {
                                                        resultado2 = $.trim(resultado2);
                                                        var _error2 = resultado2.indexOf("error");
                                                        if(_error2 != -1)
                                                        {
                                                            $('#DIVeliminar').html(resultado2);
                                                            $('#DIVeliminar').dialog({
                                                                buttons:{
                                                                    "Aceptar":function(){
                                                                        $('#DIVeliminar').dialog('close');
                                                                        hideOverlay(function(){});
                                                                    }
                                                                }
                                                            });
                                                            $('#DIVeliminar').dialog('open');
                                                        }
                                                        else
                                                        {
                                                            $('#DIVeliminar').dialog('close');
                                                            $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="tipoUsuario" includeContext="false" action="TipoUsuario" />');
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
        
        $('#btn-permisos').click(function(){
            post(
                '<s:property value="baseURL" /><s:url namespace="tipoUsuario" includeContext="false" action="vrfSeleccionTipoUsuario" />',
                $('#frm_princ').serialize(),
                function(resultado){
                    resultado = $.trim(resultado);
                    var _error = resultado.indexOf("error");
                    if(_error != -1)
                    {
                        $('#DIVverif').html(resultado);
                        $('#DIVverif').dialog('open');
                    }
                    else
                    {
                        var href = $(location).attr('href');

                        var _varret = $('#nivBandeja_f').val()+'%'+href+'%'+$('#mtu_h1').val()+'%'+$('#mmo_h1').val()+'%'+$('#mop_h1').val()+'%'+$('#mni_h1').val()+'%'+$('#mod_h1').val()+'%'+$('#curPag_f').val()+'|';
                        $('#varReturn_f').val($('#varReturn_f').val()+_varret);

                        $('#curPag_f').val(1);
                    
                        $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="permisos" includeContext="false" action="modulosTipUsuPermisos" />');
                        $('#frm_princ').submit();
                    }
                },
                1
            );
        });
    });
    
    //Redimensionar el alto del contenedor de grilla si fuese necesario
    resizeContGrilla(<s:property value="regPag" />);
</script>