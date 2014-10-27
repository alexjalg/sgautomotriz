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

<form method="POST" id="frm_ambienteubicacion_back" action="<s:property value="backURL" />">
    <!--s:hidden name="varReturn" id="varReturn_f" />!-->
    <s:property value="datosOblig" escape="false" />
</form>

<div class="d-content-form">
    <s:form id="frm_ambienteubicacion" action='javascript:void(0)' theme="simple">
        <s:hidden name="idAmbUbi" />
        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
            <tr>
                <td style="width: 70px;">Descripci�n<span class="required">*</span></td>
                <td>
                    <s:textfield name="desAmbUbi" cssClass="element-form" cssStyle="width:350px;" maxLength="60" />
                </td>
            </tr>
            <tr>
                <td style="width: 130px;">Tipo de Ambiente<span class="required">*</span></td>
                <td>
                    <s:select name="codTipAmbUbi" list="listTipAmbUbi" listKey="idTipAmbUbi" listValue="desTipAmbUbi"
                              cssClass="element-form" />
                </td>
            </tr>
            <tr>
                <td style="width: 130px;">Descripci�n Corta<span class="required">*</span></td>
                <td>
                    <s:textfield name="desCorAmbUbi" cssClass="element-form" cssStyle="width:100px;" maxLength="12" />
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
    $(document).ready(function() {
        resizeContForm();

        $('#DIVgrabar').dialog({
            autoOpen: false,
            width: 400,
            height: 200,
            modal: true,
            closeOnEscape: false,
            buttons:
                    {
                        "Aceptar": function() {
                            $('#DIVgrabar').dialog("close");
                            $('.overlay').animate({'opacity': '0'}, 250, 'swing', function() {
                                $('.overlay').css({'z-index': '-1'});
                            });
                        }
                    },
            open: function(event, ui) {
                $(".ui-dialog-titlebar-close").hide();
            },
            draggable: false,
            resizable: false
        });

        $('a.back').click(function() {
            $('#frm_ambienteubicacion_back').submit();
        });

        $('#btn_grabar').click(function() {
            post(
                    '<s:property value="formURL" />',
                    $('#frm_ambienteubicacion').serialize(),
                    function(resultado) {
                        resultado = $.trim(resultado);
                        var _error = resultado.indexOf("error");
                        if (_error != -1)
                        {
                            $('#DIVgrabar').html(resultado);
                            $('#DIVgrabar').dialog('open');
                        }
                        else
                        {
                            $('#frm_ambienteubicacion_back').submit();
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
        setTimeout('mywindow.close()', 7000);
    }
</script>