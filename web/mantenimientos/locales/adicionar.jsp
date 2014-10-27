<%@taglib uri="/struts-tags" prefix="s" %>
<!-- cabecera de pagina -->
<div class="d-header">
    <div class="d-title-header">
        <s:property value="tituloOpc" />
        <div class="d-subtitle-header">
            <s:property value="accion" />
        </div>
    </div>
    <div class="d-subheader">
        <div class="d-back">
            <a href="javascript:void(0)" class="back">Volver</a>
            <form method="POST" id="frm_local_back" action="<s:property value="backURL" />">
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
</div>
        
<div class="d-content-form">
    <s:form id="frm_local" action='javascript:void(0)' theme="simple">
        <s:hidden name="idCon" />
        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
            <tr>
                <td style="width: 130px;">Nombre<span class="required">*</span></td>
                <td>
                    <s:if test='%{opcion=="A"}'>
                        <s:textfield name="desLocCon" cssClass="element-form" cssStyle="width:380px;" maxLength="40" />
                    </s:if>
                    <s:elseif test='%{opcion=="M"}'>
                        <s:textfield name="desLocCon" cssClass="element-form" cssStyle="width:380px;" maxLength="40" />
                        <s:hidden name="idLocCon" id="idLocCon" />
                    </s:elseif>
                </td>
            </tr>
            <tr>
                <td>Nombre comercial<span class="required">*</span></td>
                <td>
                    <s:textfield name="desNomCom" cssClass="element-form" cssStyle="width:380px;" maxLength="40" />
                </td>
            </tr>
            <tr>
                <td>Nombre corto<span class="required">*</span></td>
                <td>
                    <s:textfield name="desCorta" cssClass="element-form" cssStyle="width:180px;" maxLength="12" />
                </td>
            </tr>
            <tr>
                <td>Tipo de local<span class="required">*</span></td>
                <td>
                    <s:select name="idTipLoc" id="idTipLoc" list="listTipoLocales" listKey="idTipLoc" listValue="desTipLoc"
                              headerKey="0" headerValue="-Seleccione-" cssClass="element-form"
                              cssStyle="min-width: 200px; max-width: 200px;" />
                </td>
            </tr>
            <tr>
                <td>Direcci�n<span class="required">*</span></td>
                <td>
                    <s:textfield name="otrDirLoc" cssClass="element-form" cssStyle="width:500px;" maxLength="100" />
                </td>
            </tr>
            <tr>
                <td>Departamento<span class="required">*</span></td>
                <td>
                    <s:select name="idDep" id="idDep" list="listDepartamentos" listKey="idDep" listValue="desDep"
                              headerKey="0" headerValue="-Seleccione-" cssClass="element-form"
                              cssStyle="min-width: 180px; max-width: 180px;" />
                </td>
            </tr>
            <tr>
                <td>Provincia<span class="required">*</span></td>
                <td class="td-provincias">
                    <s:select name="idPrvDep" id="idPrvDep" list="listProvincias" listKey="idPrvDep" listValue="desProv"
                              headerKey="0" headerValue="-Seleccione-" cssClass="element-form"
                              cssStyle="min-width: 240px; max-width: 240px;" />
                </td>
            </tr>
            <tr>
                <td>Distrito<span class="required">*</span></td>
                <td class="td-distritos">
                    <s:select name="idDisPrv" id="idDisPrv" list="listDistritos" listKey="idDisPrv" listValue="desDis"
                              headerKey="0" headerValue="-Seleccione-" cssClass="element-form"
                              cssStyle="min-width: 240px; max-width: 240px;" />
                </td>
            </tr>
            <tr>
                <td>RUC<span class="required">*</span></td>
                <td>
                    <s:textfield name="numRuc" cssClass="element-form" cssStyle="width:140px;" maxLength="11"
                                 onkeypress="return isNumberIntegerKey(event)" />
                </td>
            </tr>
            <tr>
                <td>Dato de Equivalencia</td>
                <td>
                    <s:textfield name="otrDatEqu1" cssClass="element-form" cssStyle="width:120px;" maxLength="10" />
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
<div id="DIVgrabar" title="<s:property value="titleDialog" />" class="alerta"></div>
                    
                    
<script type="text/javascript">
    $(document).ready(function(){
        resizeContForm();
    
        $('#DIVgrabar').dialog({
            autoOpen: false,
            width: 400,
            height: 260,
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
        
        $('a.back').click(function(){
            $('#frm_local_back').submit();
        });
        
        $('#btn_grabar').click(function(){
            post(
                '<s:property value="formURL" />',
                $('#frm_local').serialize(),
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
                        $('#frm_local_back').submit();
                    }
                },
                1
            );
        });
        
        $('#idDep').change(function(){
            $.post(
                '<s:property value="baseURL" /><s:url includeContext="false" namespace="locales" action="getProvinciasLocal" />',
                {
                    idDep:$('#idDep').val()
                },
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVerroresGen').html(resultado);
                        $('#DIVerroresGen').dialog('open');
                    }
                    else
                    {
                        $('.td-provincias').html(resultado);
                    }
                }
            );
                
            /*$.post(
                '<s:property value="baseURL" /><s:url includeContext="false" namespace="locales" action="getDistritosLocal" />',
                {},
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVerroresGen').html(resultado);
                        $('#DIVerroresGen').dialog('open');
                    }
                    else
                    {*/
                        $('.td-distritos').html('<select style="min-width: 240px; max-width: 240px;" class="element-form" id="idDisPrv" name="idDisPrv"><option selected="selected" value="0">-Seleccione-</option></select>');
                    /*}
                }
            );*/
        });
        
        $('.td-provincias').delegate('#idPrvDep','change',function(){
            $.post(
                '<s:property value="baseURL" /><s:url includeContext="false" namespace="locales" action="getDistritosLocal" />',
                {
                    idDep:$('#idDep').val(),
                    idPrvDep:$('#idPrvDep').val()
                },
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVerroresGen').html(resultado);
                        $('#DIVerroresGen').dialog('open');
                    }
                    else
                    {
                        $('.td-distritos').html(resultado);
                    }
                }
            );
        });
    });
    
    function abrir_ventana(url)
    {	
        //var opc = "location=0,directories=0,titlebar=0,toolbar=0,location=1,status=0,menubar=0,scrollbars=1,width=" + screen.availWidth + ",height=" + screen.availHeight;
        var opc = "location=0,directories=0,titlebar=0,toolbar=0,location=1,status=0,menubar=0,scrollbars=1,width=300,height=150";
        mywindow = window.open(url, "", opc);
        mywindow.moveTo(100, 100);
        setTimeout('mywindow.close()',7000);
    }
</script>