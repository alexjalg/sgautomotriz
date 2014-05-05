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
    <!--<s:form id="frm_usuario" action="adicionarUsuario">
        <s:hidden name="idUsu" id="idUsu_h1" />
        <s:hidden name="opcion" id="opcion_h1" />
        <s:hidden name="backURL" id="backURL_h1" />
    </s:form>-->
</div>

<!-- modelo de grilla -->
<div class="d-content-grilla" style="min-width: 660px;">
    <div class="d-content-grilla-options" style="">
        <div class="d-buttons-head">
            <button class="boton-bandeja filter-button" id="btn-filter">
                <span>Filtrar</span>
            </button>
            <button class="boton-bandeja add-button" id="btn-add">
                <span>Adicionar</span>
            </button>
            <button class="boton-bandeja edit-button" id="btn-edit">
                <span>Modificar</span>
            </button>
            <button class="boton-bandeja delete-button" id="btn-delete">
                <span>Eliminar</span>
            </button>

            <div id="d-filtros" class="d-more-options">
                <div id="d-filtros-float" class="d-more-opt-float">
                    <form id="frm_usu_princ" method="POST" action="<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="Usuario" />">
                        <s:hidden name="idUsu" id="idUsu_h1" />
                        <s:hidden name="opcion" id="opcion_h1" />
                        <s:hidden name="backURL" id="backURL_h1" />
                        
                        <s:hidden name="curPag" id="curPag_f" />
                        <table border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td style="padding-left: 10px;">
                                    <table border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td><span class="lbl-filtros" style="padding-left: 0px;">DNI</span></td>
                                            <td>
                                                <s:textfield name="idUsu_f" id="idUsu_f" cssClass="element-form" cssStyle="width:70px;" />
                                            </td>
                                            <td><span class="lbl-filtros">Apellido&nbsp;y&nbsp;Nombres</span></td>
                                            <td>
                                                <s:textfield name="desUsu_f" id="desUsu_f" cssClass="element-form" cssStyle="width:300px;" />
                                            </td>
                                            <td><span class="lbl-filtros">Estado</span></td>
                                            <td>
                                                <div class="select-container">
                                                    <div class="arrow"></div>
                                                    <s:select 
                                                        headerKey="S" headerValue="-Seleccione-" 
                                                        list="#{'A':'Activo', '':'Inactivo'}" 
                                                        name="edoUsu_f" id="edoUsu_f" />
                                                </div>
                                            </td>
                                        </tr>
                                    </table>	
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <table border="0" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td>
                                                <button id="btn-filtrar" class="boton-bandeja accept-button">
                                                    <span>Aceptar</span>
                                                </button>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
        
        <div class="d-paging">
            <s:if test='%{curPag==1 || curPag==0}'>
                <img src="<s:property value="baseURL" />images/icons/first-icon-disabled.png" style="cursor: default;" />
                <img src="<s:property value="baseURL" />images/icons/prev-icon-disabled.png" style="cursor: default;" />    
            </s:if>
            <s:else>
                <img id="first_pag" src="<s:property value="baseURL" />images/icons/first-icon.png" title="inicio" />
                <img id="prev_pag" src="<s:property value="baseURL" />images/icons/prev-icon.png" title="anterior" />
            </s:else>
            
            <span style="padding: 0 4px;">Pag. <s:property value="curPag" /> de <s:property value="ultPag" /></span>
            <s:hidden name="ultPag" id="ultPag" />
            
            <s:if test='%{curPag==0 || curPag==ultPag}'>
                <img src="<s:property value="baseURL" />images/icons/next-icon-disabled.png" style="cursor: default;" />
                <img src="<s:property value="baseURL" />images/icons/last-icon-disabled.png" style="cursor: default;" />
            </s:if>
            <s:else>
                <img id="next_pag" src="<s:property value="baseURL" />images/icons/next-icon.png" title="siguiente" />
                <img id="last_pag" src="<s:property value="baseURL" />images/icons/last-icon.png" title="final" />
            </s:else>
        </div>
    </div>
    <div class="d-grilla" style="overflow: hidden;">
        <div class="d-content-grilla-head" style="">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <tr class="color-grilla-head tr-head">
                    <td style="width: 24px;"></td>
                    <td style="width:90px; text-align: center;">
                        DNI
                    </td>
                    <td style="">
                        Apellidos y Nombres
                    </td>
                    <td style="width:54px; text-align: center;">
                        Activo
                    </td>
                </tr>
            </table>
        </div>
        <div class="d-content-grilla-body">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <s:iterator value="listUsuarios">
                <tr class="color-grilla-body-impar">
                    <td style="width: 24px;">
                        <div class="rb_usu radio_grid_off"></div>
                        <s:hidden name="idUsu" />
                    </td>
                    <td style="width:90px; text-align: center;">
                        <s:property value="idUsu" />
                    </td>
                    <td style="">
                        <s:property value="desUsu" />
                    </td>
                    <td style="width:54px;">
                        <div class="chk_edousu check_grid_off <s:if test='%{edoUsu=="A"}'>check_grid_on</s:if>"></div>
                        <s:hidden name="idUsu" />
                    </td>
                </tr>
                </s:iterator>
            </table>
        </div>
    </div>
</div>
<div id="DIVverif" title="<s:property value="titleDialog" />" class="alerta"></div>
<div id="DIVeliminar" title="<s:property value="titleDialog" />" class="alerta"></div>
        
<script type="text/javascript">
    $(document).ready(function() {
        $('#DIVverif').dialog({
            autoOpen: false,
            width: 340,
            height: 150,
            modal: true,
            closeOnEscape: false,
            buttons:
            {
                "Aceptar":function(){
                    $('#DIVverif').dialog("close");
                    $('.overlay').animate({'opacity':'0'},250,'swing',function(){
                        $('.overlay').css({'z-index':'-1'});
                    });
                }
            },
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false
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
        
        $('#btn-filter').click(function(e) {
            e.stopPropagation();
            hideMenu();
            if ($(this).hasClass('boton-bandeja-click'))
            {
                $('.boton-bandeja-click').removeClass('boton-bandeja-click');
                $('#d-filtros-float').css('display', 'none');
            }
            else
            {
                $('.boton-bandeja-click').removeClass('boton-bandeja-click');
                $(this).addClass('boton-bandeja-click');
                var posicion = $(this).position();
                $('#d-filtros-float').css({"top": posicion.top, "left": posicion.left + 6});
                $('#d-filtros-float').css('display', 'block');
            }
        });
        
        $('#btn-filtrar').click(function(){
            $('#curPag_f').val(1);
            $("#idUsu_h1").val('');
            $("#opcion_h1").val('');
            $("#backURL_h1").val('');
            
            $('#frm_usu_princ').attr('action','<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="Usuario" />');
            $('#frm_usu_princ').submit();
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
            $('#frm_usu_princ').attr('action','<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="adicionarUsuario" />');
            $('#frm_usu_princ').submit();
        });
        
        $('#btn-edit').click(function() {
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
                        $('#opcion_h1').val('M');
                        var href = $(location).attr('href');
                        $('#backURL_h1').val(href);
                        $('#frm_usu_princ').attr('action','<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="adicionarUsuario" />');
                        $('#frm_usu_princ').submit();
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
        
        /*Paginacion de grilla*/
        $('#first_pag').click(function(){
            $('#curPag_f').val(1);
            $('#frm_usu_princ').attr('action','<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="Usuario" />');
            $('#frm_usu_princ').submit();
        });
        
        $('#prev_pag').click(function(){
            $('#curPag_f').val(parseInt($('#curPag_f').val())-1);
            $('#frm_usu_princ').attr('action','<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="Usuario" />');
            $('#frm_usu_princ').submit();
        });
        
        $('#next_pag').click(function(){
            $('#curPag_f').val(parseInt($('#curPag_f').val())+1);
            $('#frm_usu_princ').attr('action','<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="Usuario" />');
            $('#frm_usu_princ').submit();
        });
        
        $('#last_pag').click(function(){
            $('#curPag_f').val(parseInt($('#ultPag').val()));
            $('#frm_usu_princ').attr('action','<s:property value="baseURL" /><s:url namespace="usuarios" includeContext="false" action="Usuario" />');
            $('#frm_usu_princ').submit();
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