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
            <form method="POST" id="frm_permisosModulo_back" action="<s:property value="backURL" />">
                <s:hidden name="varReturn" id="varReturn_f" />
            </form>
        </div>
    </div>
    <div class="d-header-labels">
        <table>
            <tr>
                <td class="lbl-concept">Tipo de Usuario: </td>
                <td class="lbl-value"><span><s:property value="desTipUsu" /><span></td>
            </tr>
        </table>
    </div>
</div>
<div class="d-content-form">
    <s:form id="frm_permisosModulo" action='javascript:void(0)' theme="simple">
        <s:hidden name="idTipUsu" />
        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
            <tr>
                <td style="width: 66px;">Módulo<span class="required">*</span></td>
                <td>
                    <s:hidden name="idModu" id="moduloIdModu" />
                    <s:textfield name="modeloModulo.desModu" id="moduloDesModu" cssClass="element-form" cssStyle="width:350px;" maxLength="40" 
                                  disabled="true" />
                    <s:if test='%{opcion!="M"}'>
                    <button id="btn_modulos">...</button>    
                    </s:if>
                </td>
            </tr>
            <tr>
                <td style="width: 66px;">Nº orden<span class="required">*</span></td>
                <td>
                <s:if test='%{modeloModulo.numOrdVis=="0"}'>
                    <s:textfield name="modeloModulo.numOrdVis" cssClass="element-form" cssStyle="width:32px;" maxLength="2" 
                                 value="" onkeypress="return isNumberIntegerKey(event)" />
                </s:if>
                <s:else>
                    <s:textfield name="modeloModulo.numOrdVis" cssClass="element-form" cssStyle="width:32px;" maxLength="2"
                                 onkeypress="return isNumberIntegerKey(event)" />
                </s:else>
                </td>
            </tr>
            <tr>
                <td></td>
                <td style="padding-top: 10px;">
                    <button id="btn_grabar">
                        Grabar
                    </button>
                    <span class="required">(*) campos obligatorios</span>
                </td>
            </tr>
        </table>
    </s:form>
</div>
<div id="DIVgrabar" title="<s:property value="titleDialog" />"></div>
<div id="DIVmodulos" title="<s:property value="titleDialog" />"></div>
                    
<script type="text/javascript">
    $('#btn_grabar').button();
    $('#btn_modulos').button();
    
    $(document).ready(function(){
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
        
        $('#DIVmodulos').dialog({
            autoOpen: false,
            width: 580,
            height: 500,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false
        });
        
        $('a.back').click(function(){
            $('#frm_permisosModulo_back').submit();
        });
        
        $('#btn_modulos').click(function(){
            post(
                '<s:property value="baseURL" /><s:url namespace="permisos" includeContext="false" action="listModulosPermisos" />',
                {},
                function(resultado){
                    $('#DIVmodulos').dialog({
                        buttons:{
                            "Aceptar":function(){
                                $('#moduloIdModu').val($('.select_rec:checked').val());
                                $('#moduloDesModu').val($.trim($('.select_rec:checked').parent().next().html()));
                                $('#DIVmodulos').dialog("close");
                                hideOverlay(function(){});
                            },
                            "Cancelar":function(){
                                $('#DIVmodulos').dialog("close");
                                hideOverlay(function(){});
                            }
                        }
                    });
                    $('#DIVmodulos').html(resultado);
                    $('#DIVmodulos').dialog('open');
                },
                2       
            );
        });
        
        $('#btn_grabar').click(function(){
            post(
                '<s:property value="formURL" />',
                $('#frm_permisosModulo').serialize(),
                function(resultado){
                    resultado = $.trim(resultado);
                    var _error = resultado.indexOf("error");
                    if(_error != -1)
                    {
                        $('#DIVgrabar').html(resultado);
                        $('#DIVgrabar').dialog('open');
                    }
                    else
                    {
                        $('#frm_permisosModulo_back').submit();
                    }
                },
                1
            );
        });
    });
</script>