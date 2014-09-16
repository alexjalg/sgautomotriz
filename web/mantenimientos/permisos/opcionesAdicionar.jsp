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
            <form method="POST" id="frm_permisosOpcion_back" action="<s:property value="backURL" />">
                <s:hidden name="varReturn" />
            </form>
        </div>
    </div>
    <div class="d-header-labels">
        <table>
            <tr>
                <td class="lbl-concept">Tipo de Usuario: </td>
                <td class="lbl-value"><span><s:property value="desTipUsu" /><span></td>
                <td class="lbl-concept">Módulo: </td>
                <td class="lbl-value"><span><s:property value="desModu" /><span></td>
            </tr>
        </table>
    </div>
</div>
     
<div class="d-content-form">
    <s:form id="frm_permisosOpcion" action='javascript:void(0)' theme="simple">
        <s:hidden name="idTipUsu" />
        <s:hidden name="idModu" />
        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
            <tr>
                <td style="width: 101px;">Opción<span class="required">*</span></td>
                <td>
                    <s:hidden name="idOpc1" id="opcionIdOpc" />
                    <s:textfield name="modeloOpcion.desOpc" id="opcionDesOpc" cssClass="element-form" cssStyle="width:350px;" maxLength="60" 
                                  disabled="true" />
                    <s:if test='%{opcion!="M"}'>
                    <button id="btn_opciones">...</button>    
                    </s:if>
                </td>
            </tr>
            <tr>
                <td style="width: 101px;">Nº orden<span class="required">*</span></td>
                <td>
                <s:if test='%{modeloOpcion.numOrdVis=="0"}'>
                    <s:textfield name="modeloOpcion.numOrdVis" cssClass="element-form" cssStyle="width:32px;" maxLength="2" 
                                 value="" onkeypress="return isNumberIntegerKey(event)" />
                </s:if>
                <s:else>
                    <s:textfield name="modeloOpcion.numOrdVis" cssClass="element-form" cssStyle="width:32px;" maxLength="2"
                                 onkeypress="return isNumberIntegerKey(event)" />
                </s:else>
                </td>
            </tr>
            <tr>
                <td style="width: 101px;">Tipo de Permiso<span class="required">*</span></td>
                <td>
                    <s:select name="modeloOpcion.codPerOpc" list="#{'V':'Visualización', 'M':'Mantenimiento'}" 
                              headerKey="" headerValue="-Seleccione-"
                              cssClass="element-form" cssStyle="width: 150px;" />
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
<div id="DIVopciones" title="<s:property value="titleDialog" />"></div>
                    
<script type="text/javascript">
    $('#btn_grabar').button();
    $('#btn_opciones').button();
    
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
        
        $('#DIVopciones').dialog({
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
            $('#frm_permisosOpcion_back').submit();
        });
        
        $('#btn_opciones').click(function(){
            post(
                '<s:property value="baseURL" /><s:url namespace="permisos" includeContext="false" action="listOpcionesPermisos" />',
                {},
                function(resultado){
                    $('#DIVopciones').dialog({
                        buttons:{
                            "Aceptar":function(){
                                $('#opcionIdOpc').val($('.select_rec:checked').val());
                                $('#opcionDesOpc').val($.trim($('.select_rec:checked').parent().next().next().html()));
                                $('#DIVopciones').dialog("close");
                                hideOverlay(function(){});
                            },
                            "Cancelar":function(){
                                $('#DIVopciones').dialog("close");
                                hideOverlay(function(){});
                            }
                        }
                    });
                    $('#DIVopciones').html(resultado);
                    $('#DIVopciones').dialog('open');
                },
                2       
            );
        });
        
        $('#btn_grabar').click(function(){
            post(
                '<s:property value="formURL" />',
                $('#frm_permisosOpcion').serialize(),
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
                        $('#frm_permisosOpcion_back').submit();
                    }
                },
                1
            );
        });
    });
</script>