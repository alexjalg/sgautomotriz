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
                <td class="lbl-concept">Concesionario: </td>
                <td class="lbl-value"><span><s:property value="desCon" /><span></td>
            </tr>
        </table>
    </div>
    <div class="d-header-filters">
        <table>
            <tr>
                <td class="">Marca: </td>
                <td class="">
                    <s:select name="idMar" id="idMar_f1" list="listMarcas" listKey="idMar" listValue="desMar"
                              cssClass="element-form" cssStyle="width: 120px;"/>
                </td>
                <td class="" style="padding-left: 20px;">Modelo: </td>
                <td class="" id="td_modelos">
                    <s:select name="idModMar" id="idModMar_f1" list="listModelos" listKey="idModMar" listValue="desModMar"
                              cssClass="element-form" cssStyle="width: 180px;" />
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
        </s:if>
    </s:if>
</div>
<div class="d-content-grilla" style="min-width: 660px;">
    <form id="frm_princ" method="POST" action="<s:property value="baseURL" /><s:property value="urlPaginacion" />">
    <s:hidden name="idCon" id="idCon_h1" />
    <s:hidden name="idMar" id="idMar_h1" />
    <s:hidden name="idModMar" id="idModMar_h1" />
    <s:property value="datosOblig" escape="false" />
    
    <div class="d-grilla" style="overflow: hidden;">
        <div class="d-content-grilla-head" style="">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td style="width:50px; text-align: center;">
                        C�digo
                    </td>
                    <td style="">
                        Descripci�n
                    </td>
                    <td style="width: 54px; text-align: center;">
                        Activo
                    </td>
                </tr>
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </div>
        <div class="d-content-grilla-body">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <s:iterator value="listCortesias">
                <tr>
                    <td style="width: 24px;">
                        <input type="radio" name="idCorMar" id="rbt_idMar" value="<s:property value="idCorMar" />" class="select_rec" />
                    </td>
                    <td style="width: 50px; text-align: center;">
                        <s:property value="idCorMar" />
                    </td>
                    <td style="">
                        <s:property value="desCorMar" />
                    </td>
                    <td style="width: 54px; text-align: center;">
                        <input type="checkbox" id="chk_edocor" <s:if test='%{edoCorMar=="A"}'> checked="checked" class="chk_edocormar check_grid_on" </s:if><s:else> class="chk_edocormar" </s:else> />
                        <input type="hidden" value="<s:property value="idCorMar" />" />
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
        
        $('#btn_search').css('visibility','hidden');
        
        $('#idMar_f1').change(function(){
            $('#idMar_h1').val($(this).val());
            $('#idModMar_h1').val('');
            
            $('#frm_princ').submit();
        });
        
        $('#idModMar_f1').change(function(){
            $('#idModMar_h1').val($(this).val());
            $('#frm_princ').submit();
        });
    
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
            
            var _varret = $('#nivBandeja_f').val()+'%'+href+'%'+$('#mtu_h1').val()+'%'+$('#mmo_h1').val()+'%'+$('#mop_h1').val()+'%'+$('#mni_h1').val()+'%'+$('#mod_h1').val()+'%'+$('#curPag_f').val()+'%'+$('#idCon_h1').val()+'%'+$('#idMar_h1').val()+'%'+$('#idModMar_h1').val()+'|';
            $('#varReturn_f').val($('#varReturn_f').val()+_varret);
            
            $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="cortesias" includeContext="false" action="adicionarCortesia" />');
            $('#frm_princ').submit();
        });
        
        $('#btn-edit').click(function() {
            post(
                '<s:property value="baseURL" /><s:url namespace="cortesias" includeContext="false" action="vrfSeleccionCortesia" />',
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
                        
                        var _varret = $('#nivBandeja_f').val()+'%'+href+'%'+$('#mtu_h1').val()+'%'+$('#mmo_h1').val()+'%'+$('#mop_h1').val()+'%'+$('#mni_h1').val()+'%'+$('#mod_h1').val()+'%'+$('#curPag_f').val()+'%'+$('#idCon_h1').val()+'%'+$('#idMar_h1').val()+'%'+$('#idModMar_h1').val()+'|';
                        $('#varReturn_f').val($('#varReturn_f').val()+_varret);
                        
                        $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="cortesias" includeContext="false" action="modificarCortesia" />');
                        $('#frm_princ').submit();
                    }
                },
                1
            );
        });
        
        $('#btn-delete').click(function(){
            post(
                '<s:property value="baseURL" /><s:url namespace="cortesias" includeContext="false" action="vrfSeleccionCortesia" />',
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
                            '<s:property value="baseURL" /><s:url namespace="cortesias" includeContext="false" action="eliminarCortesia" />',
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
                                                    '<s:property value="baseURL" /><s:url namespace="cortesias" includeContext="false" action="eliminarCortesia" />',
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
                                                            $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="cortesias" includeContext="false" action="Cortesia" />');
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
        
        $('.chk_edocormar').click(function(){
            var _edo = 'A';
            var _chk = $(this);
            var _idcormar = $(this).next().val();
            
            if($(this).hasClass('check_grid_on'))
                _edo = 'D';
            
            if($(this).hasClass('check_grid_on'))
                $(this).removeClass('check_grid_on');
            else
                $(this).addClass('check_grid_on');
            
            post(
                '<s:property value="baseURL" /><s:url namespace="cortesias" includeContext="false" action="actualizarEstadoCortesia" />',
                {
                    idCon:$('#idCon_h1').val(),
                    idMar:$('#idMar_h1').val(),
                    idModMar:$('#idModMar_h1').val(),
                    idCorMar:_idcormar,
                    edoCorMar:_edo
                },
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVeliminar').html(resultado);
                        $('#DIVeliminar').dialog({
                            buttons:{
                                "Aceptar":function(){
                                    $('#DIVeliminar').dialog("close");
                                    $('.overlay').animate({'opacity':'0'},250,'swing',function(){
                                       $('.overlay').css({'z-index':'-1'}); 
                                    });
                                }
                            }
                        });
                        
                        displayOverlay(function(){
                            $('#DIVeliminar').dialog('open');
                            
                            if($(_chk).hasClass('check_grid_on'))
                            {
                                $(_chk).removeClass('check_grid_on');
                                $(_chk).prop('checked',false);
                            }
                            else
                            {
                                $(_chk).addClass('check_grid_on');
                                $(_chk).prop('checked',true);
                            }
                        });
                    }
                },
                1
            );
        });
        
        $('.d-back a').click(function(){
            $('#frm_back').submit();
        });
    });
    
    //Redimensionar el alto del contenedor de grilla si fuese necesario
    resizeContGrilla(<s:property value="regPag" />);    
</script>