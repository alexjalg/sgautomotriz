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
</div>
<div class="d-content-form">
    <form id="frm_cargaDatosClientes" target="upload_target" action='<s:property value="formURL" />'  method="post" enctype="multipart/form-data">    
        <s:hidden name="fileload" id="fileload_h1"/>  
        <table border="0" cellpadding="0" cellspacing="0" class="table-form">
            <tr>
                <td style="width: 60px;">Archivo<span class="required">*</span></td>
                <td>                       
                    <button type="button" id="fileupButton">
                        Seleccione un archivo...
                    </button>
                    <s:file name="fileUpload" size="40" id="fileup" style="visibility: hidden;" />                    
                </td>
            </tr>
            <tr>
                <td></td>
                <td style="padding-top: 10px;">
                    <button id="btn_grabar">
                        Grabar
                    </button>
                    <span class="required">(*) campos obligatorios</span>
                    <!-- <span id="img_load" style="display: none;"><img src="../images/load-small.gif" /> procesando...</span> -->
                </td>
            </tr>
        </table>
    </form>
    <center>
        <div id="DivGrafico">
            <input type="button" id="btn_return" style="display: none;" />  
            <s:hidden name="file" id="file_h1" />
            <s:hidden name="filea" id="filea_h1" />            
            <iframe width="85%" frameborder="0"   height="10" id='frameDemo' name="upload_target"></iframe>            
        </div>  
    </center>
</div>
<div id="DIVgrabar" title="<s:property value="titleDialog" />" class="alerta"></div>

<script type="text/javascript">
    $(document).ready(function() {

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
        
        $('#fileupButton').click(function(){
            $('#fileup').click();
        });
        
        $('#btn_grabar').click(function() {
            $('#fileload_h1').val($('#fileup').val());
            post(
                    '<s:property value="baseURL" /><s:url namespace="cargaDatosClientes" includeContext="false" action="verificarCargaDatosClientes" />',
                    $('#frm_cargaDatosClientes').serialize(),
                    function(resultado)
                    {
                        resultado = $.trim(resultado);
                        var _error = resultado.indexOf("error");
                        if (_error !== -1)
                        {
                            $('#DIVgrabar').html(resultado);
                            $('#DIVgrabar').dialog('open');
                        } else {
                            $('#frm_cargaDatosClientes').submit();
                        }
                    },
                    1
                    );
        });

    });
</script>