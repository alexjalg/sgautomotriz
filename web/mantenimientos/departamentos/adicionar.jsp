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
        </div>
    </div>
</div>
            
<form method="POST" id="frm_departamento_back" action="<s:property value="backURL" />">
    <s:hidden name="varReturn" />
</form>
        
<div class="d-content-form">
    <s:form id="frm_departamento" action='javascript:void(0)' theme="simple">
        <s:hidden name="idDep" />
        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
            <tr>
                <td style="width: 90px;">Departamento<span class="required">*</span></td>
                <td>
                    <s:textfield name="desDep" cssClass="element-form" cssStyle="width:250px;" maxLength="20" />
                </td>
            </tr>
            <tr>
                <td style="width: 90px;">Codigo Telf.<span class="required">*</span></td>
                <td>
                    <s:textfield name="codTel" cssClass="element-form" cssStyle="width:32px;" maxLength="3"
                                 onkeypress="return isNumberIntegerKey(event)" />
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
            $('#frm_departamento_back').submit();
        });
        
        $('#btn_grabar').click(function(){
            post(
                '<s:property value="formURL" />',
                $('#frm_departamento').serialize(),
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVgrabar').html(resultado);
                        $('#DIVgrabar').dialog('open');
                    }
                    else
                    {
                        $('#frm_departamento_back').submit();
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