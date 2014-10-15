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
                <td class="lbl-concept">Origen: </td>
                <td class="lbl-value"><span><s:property value="desOriCam" /><span></td>
                <td class="lbl-concept">Campaña: </td>
                <td class="lbl-value"><span><s:property value="desCam" /><span></td>
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
    </s:if>
</s:if>
</div>
<div class="d-content-grilla" style="min-width: 660px;">
    <form id="frm_princ" method="POST" action="<s:property value="baseURL" /><s:property value="urlPaginacion" />">
    <s:property value="datosOblig" escape="false" />
    <s:hidden name="idOriCam" id="idOriCam_h1" />
    <s:hidden name="idCam" id="idCam_h1" />
    
    <div class="d-grilla" style="overflow: hidden;">
        <div class="d-content-grilla-head" style="">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <tr class="tr-head">
                    <td style="width: 300px;">
                        Marca
                    </td>
                    <td style="width: 400px;">
                        Modelo
                    </td>
                    <td style="">
                        Versión
                    </td>
                    <td style="text-align: center; width: 70px;">
                        Estado
                    </td>
                </tr>
                <tr class="tr-head">
                    <td style="width: 300px;"></td>
                    <td style="width: 400px;"></td>
                    <td style=""></td>
                    <td style="width: 70px;"></td>
                </tr>
            </table>
        </div>
        <div class="d-content-grilla-body">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <s:iterator value="listCondicionCampania">
                <tr>
                    <td style="width: 300px;">
                        <s:property value="desMarExt" />
                    </td>
                    <td style="width: 400px;">
                        <s:property value="desModExt" />
                    </td>
                    <td style="">
                        <s:property value="desVerExt" />
                    </td>
                    <td style=" text-align: center; width:70px;">
                        <input type="checkbox" id="chk_edocam" <s:if test='%{edoConCam=="A"}'> checked="checked" class="chk_edocam check_grid_on" </s:if><s:else> class="chk_edocam" </s:else> />
                        <input type="hidden" value="<s:property value="idMar" />" />
                        <input type="hidden" value="<s:property value="idModMar" />" />
                        <input type="hidden" value="<s:property value="idVerMod" />" />
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
<div id="DIVchgEstado" title="<s:property value="titleDialog" />" class="alerta"></div>
<div id="DIVversiones" title="<s:property value="titleDialog" />"></div>
<div id="DIVgrabar" title="<s:property value="titleDialog" />"></div>
        
<script type="text/javascript">
    $(document).ready(function() {
        /*Paginacion de grilla*/
        <s:property value="jsPaginacion" escape="false" />
        
        $('#btn_search').css('visibility','hidden');
    
        $('#DIVversiones').dialog({
            autoOpen: false,
            width: 650,
            height: 580,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false
        });
        
        $('#DIVchgEstado').dialog({
            autoOpen: false,
            width: 400,
            height: 200,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false
        });
        
        $('#DIVgrabar').dialog({
            autoOpen: false,
            width: 400,
            height: 200,
            modal: true,
            closeOnEscape: false,
            buttons:
            {
                "Aceptar":function(){
                    $('#DIVgrabar').dialog("close");
                    $('.overlay').animate({'opacity':'0'},250,'swing',function(){
                        $('.overlay').css({'z-index':'-1'});
                    });
                }
            },
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false
        });
       
        $('#btn-add').click(function(){
            post(
                '<s:property value="baseURL" /><s:url namespace="condicionCampania" includeContext="false" action="listVersionesCondicionCampania" />',
                $('#frm_princ').serialize(),
                function(resultado){
                    $('#DIVversiones').dialog({
                        buttons:{
                            "Grabar":function(){
                                $('#DIVversiones').dialog('close');
                            
                                post(
                                    '<s:property value="baseURL" /><s:url namespace="condicionCampania" includeContext="false" action="grabarCondicionCampania" />',
                                    $('#frm_princ_pu').serialize(),
                                    function(resultado){
                                        var _error = validaRespuestaAjax(resultado);
                    
                                        if(_error != -1) {
                                            $('#DIVgrabar').html(resultado);
                                            $('#DIVgrabar').dialog('open');
                                        } else {
                                            $('#frm_princ').submit();
                                        }
                                    },
                                    4
                                );
                            },
                            "Cancelar":function(){
                                $('#DIVversiones').dialog("close");
                                hideOverlay(function(){});
                            }
                        }
                    });
                    $('#DIVversiones').html(resultado);
                    $('#DIVversiones').dialog('open');
                },
                2       
            );
        });
        
        $('.chk_edocam').click(function(){
            var _edo = 'A';
            var _chk = $(this);
            var _idmar = $(this).next().val();
            var _idmodmar = $(this).next().next().val();
            var _idvermod = $(this).next().next().next().val();
            
            if($(this).hasClass('check_grid_on'))
                _edo = 'D';
            
            if($(this).hasClass('check_grid_on'))
                $(this).removeClass('check_grid_on');
            else
                $(this).addClass('check_grid_on');
            
            post(
                '<s:property value="baseURL" /><s:url namespace="condicionCampania" includeContext="false" action="actualizarEstadoCondicionCampania" />',
                {
                    idOriCam:$('#idOriCam_h1').val(),
                    idCam:$('#idCam_h1').val(),
                    idMar:_idmar,
                    idModMar:_idmodmar,
                    idVerMod:_idvermod,
                    edoConCam:_edo
                },
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1) {
                        $('#DIVgrabar').html(resultado);
                        $('#DIVgrabar').dialog({
                            buttons:{
                                "Aceptar":function(){
                                    $('#DIVgrabar').dialog("close");
                                    $('.overlay').animate({'opacity':'0'},250,'swing',function(){
                                       $('.overlay').css({'z-index':'-1'}); 
                                    });
                                }
                            }
                        });
                        
                        displayOverlay(function(){
                            $('#DIVgrabar').dialog('open');
                            
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