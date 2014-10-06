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
            <form method="POST" id="frm_back" action="<s:property value="backURL" />">
                <s:hidden name="varReturn" />
            </form>
        </div>
    </div>
    <div class="d-header-labels">
        <table>
            <tr>
                <td class="lbl-concept">Cliente: </td>
                <td class="lbl-value"><span><s:property value="desCli" /><span></td>
            </tr>
        </table>
    </div>
</div>
        
<div class="d-content-form">
    <s:form id="frm_adjuntoCliente" action='javascript:void(0)' theme="simple">
        <s:hidden name="idCli" />
        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
            <tr>
                <td style="width: 120px;">Nro de Documento<span class="required">*</span></td>
                <td>
                    <s:textfield name="idAdjCli" cssClass="element-form" cssStyle="width:120px;" maxLength="15"
                                 onkeypress="return isNumberIntegerKey(event)" />
                </td>
            </tr>
            <tr>
                <td style="width: 120px;">Tipo de Adjunto<span class="required">*</span></td>
                <td>
                    <s:select name="codTipAdj" list="#{'C':'Conyugue','P':'Copropietario'}" headerKey="" headerValue="-Seleccione-"
                              cssClass="element-form" cssStyle="width: 110px;" />
                </td>
            </tr>
            <tr>
                <td style="width: 120px;">Apellidos<span class="required">*</span></td>
                <td>
                    <s:textfield name="desApeAdj" cssClass="element-form" cssStyle="width:350px;" maxLength="40"
                                 onkeypress="return isCharacterKey(event)" />
                </td>
            </tr>
            <tr>
                <td style="width: 120px;">Nombres<span class="required">*</span></td>
                <td>
                    <s:textfield name="desNomAdj" cssClass="element-form" cssStyle="width:350px;" maxLength="30"
                                 onkeypress="return isCharacterKey(event)" />
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
        $('#btn_grabar').button();
    
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
        
        $('a.back').click(function(){
            $('#frm_back').submit();
        });
        
        $('#btn_grabar').click(function(){
            post(
                '<s:property value="formURL" />',
                $('#frm_adjuntoCliente').serialize(),
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
                        $('#frm_back').submit();
                    }
                },
                1
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