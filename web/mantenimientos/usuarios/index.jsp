<%@taglib uri="/struts-tags" prefix="s" %>

<!-- cabecera de pagina -->
<div class="d-header">
    <div class="d-title-header">
        <s:property value="tituloOpc" />
        <div class="d-subtitle-header"></div>
    </div>
    <!--<div class="d-subheader">
        <div class="d-back">
            <a href="javascript:void(0)" class="back">Volver</a>
        </div>
    </div>-->
</div>

        
<!-- modelo de grilla -->
<div class="d-buttons-grid">
    <button id="btn-add">
        Adicionar
    </button>
    <button id="btn-edit">
        Modificar
    </button>
    <button id="btn-delete">
        Eliminar
    </button>
</div>
<div class="d-content-grilla" style="min-width: 660px;">
    <form id="frm_princ" method="POST" action="<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="Usuario" />">
    <s:hidden name="idUsu" id="idUsu_h1" />
    <s:hidden name="opcion" id="opcion_h1" />
    <s:hidden name="backURL" id="backURL_h1" />

    <s:hidden name="curPag" id="curPag_f" />
    <div class="d-grilla" style="overflow: hidden;">
        <div class="d-content-grilla-head" style="">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td style="width:90px; text-align: center; <s:if test='%{idUsu_f!=""}'> background-color: #B5CCED; </s:if>">
                        DNI
                    </td>
                    <td style="<s:if test='%{desUsu_f!=""}'> background-color: #B5CCED; </s:if>">
                        Apellidos y Nombres
                    </td>
                    <td style="width:54px; text-align: center;">
                        Activo
                    </td>
                </tr>
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td style="width:90px; text-align: center; <s:if test='%{idUsu_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="idUsu_f" cssClass="element-form-grid" cssStyle="width: 80px;" />
                    </td>
                    <td style="<s:if test='%{desUsu_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="desUsu_f" cssClass="element-form-grid" cssStyle="width: 400px;" />
                    </td>
                    <td style="width:54px; text-align: center;">
                        
                    </td>
                </tr>
            </table>
        </div>
        <div class="d-content-grilla-body">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <s:iterator value="listUsuarios">
                <tr>
                    <td style="width: 24px;">
                        <input type="radio" name="rbt_idUsu" id="rbt_idUsu" value="<s:property value="idUsu" />" class="select_rec" />
                    </td>
                    <td style="width:90px; text-align: center;">
                        <s:property value="idUsu" />
                    </td>
                    <td style="">
                        <s:property value="desUsu" />
                    </td>
                    <td style="width:54px; text-align: center;">
                        <input type="checkbox" id="chk_edousu" <s:if test='%{edoUsu=="A"}'> checked="checked" </s:if> />
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
<s:iterator value="errores">
    <s:property /><br /> 
</s:iterator>
        
<script type="text/javascript">
    $(document).ready(function() {
        /*Paginacion de grilla*/
        <s:property value="jsPaginacion" escape="false" />
                
        $('#btn-add').button();
        $('#btn-edit').button();
        $('#btn-delete').button();
    
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
    
        $('.rb_usu').click(function(){
            if(!$(this).hasClass('radio_grid_on'))
            {
                $('.rb_usu').removeClass('radio_grid_on');
                $(this).addClass('radio_grid_on');
                $('#idUsu_h1').val($(this).next().val());
            }
        });

        $('#btn-add').click(function() {
            $('#idUsu_h1').val('');
            $('#opcion_h1').val('A');
            var href = $(location).attr('href');
            $('#backURL_h1').val(href);
            $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="adicionarUsuario" />');
            $('#frm_princ').submit();
        });
        
        $('#btn-edit').click(function() {
            /*Pasamos el codigo de la fila seleccionada (si existiera)*/
            $('#idUsu_h1').val($('#rbt_idUsu:checked').val());
            
            post(
                '<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="vrfSeleccionUsuario" />',
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
                        $('#backURL_h1').val(href);
                        $('#frm_princ').attr('action','<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="adicionarUsuario" />');
                        $('#frm_princ').submit();
                    }
                },
                1
            );
        });
        
        $('#btn-delete').click(function(){
            post(
                '<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="vrfSeleccionUsuario" />',
                $('#frm_usu_princ').serialize(),
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
                            '<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="eliminarUsuario" />',
                            $('#frm_usu_princ').serialize(),
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
                                                $('#opcion_h1').val('E');
                                                post(
                                                    '<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="eliminarUsuario" />',
                                                    $('#frm_usu_princ').serialize(),
                                                    function(resultado2)
                                                    {
                                                        resultado2 = $.trim(resultado2);
                                                        var _error2 = resultado2.indexOf("error");
                                                        if(_error2 != -1)
                                                        {
                                                            $('#DIVeliminar').html(resultado2);
                                                            $('#DIVeliminar').dialog('open');
                                                        }
                                                        else
                                                        {
                                                            $('#frm_usu_princ').attr('action','<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="Usuario" />');
                                                            $('#frm_usu_princ').submit();
                                                        }
                                                    },
                                                    4
                                                );
                                            },
                                            "Cancelar":function(){
                                                $('#DIVeliminar').dialog("close");
                                                $('.overlay').animate({'opacity':'0'},250,'swing',function(){
                                                   $('.overlay').css({'z-index':'-1'}); 
                                                });
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
        
        $('.chk_edousu').click(function(){
            var _edo = '';
            var _chk = $(this);
            var _idusu = $(this).next().val();
            
            if(!$(this).hasClass('check_grid_on'))
                _edo = 'A';
            
            if($(this).hasClass('check_grid_on'))
                $(this).removeClass('check_grid_on');
            else
                $(this).addClass('check_grid_on');
            
            $.post(
                '<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="actEstadoUsuario" />',
                {
                    idUsu:_idusu,
                    edoUsu:_edo
                },
                function(resultado){
                    resultado = $.trim(resultado);
                    var _error = resultado.indexOf("error");
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
                        
                        dsplyOverlay(function(){
                            $('#DIVeliminar').dialog('open');
                            if($(_chk).hasClass('check_grid_on'))
                                $(_chk).removeClass('check_grid_on');
                            else
                                $(_chk).addClass('check_grid_on');
                        });
                    }
                }
            );
        });
    });
    
    //Redimensionar el alto del contenedor de grilla si fuese necesario
    resizeContGrilla(<s:property value="regPag" />);    

    function hideOptGrilla()
    {
        $('.boton-bandeja-click').removeClass('boton-bandeja-click');
        $('#d-filtros-float').css('display', 'none');
    }
</script>