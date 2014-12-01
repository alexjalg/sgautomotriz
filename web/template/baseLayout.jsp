<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<s:if test='#session.ses_estado!="A"'>
    <meta http-equiv="Refresh" content="0;url=<s:property value="baseURL" />redirectInicio.action">
</s:if>
<s:else>
    <s:if test='%{indErrParm!=""}'>
    <meta http-equiv="Refresh" content="0;url=<s:property value="inicioURL" />">    
    </s:if>
    <s:else>
    <!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
    <!--[if IE 7]>         <html class="no-js lt-ie7"> <![endif]-->
    <!--[if IE 8]>         <html class="no-js lt-ie8"> <![endif]-->
    <!--[if IE 9]>         <html class="no-js lt-ie9"> <![endif]-->
    <!--[if gt IE 9]><!--> <html class="no-js"> <!--<![endif]-->
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title><tiles:insertAttribute name="title" /></title>

            <link rel="stylesheet" type="text/css" href="<s:property value="baseURL" />css/styles.css" />
            <link rel="stylesheet" type="text/css" href="<s:property value="baseURL" />css/jquery-ui-1.10.4.custom.min.css" />
            <script type="text/javascript" src="<s:property value="baseURL" />js/jquery.js"></script>
            <script type="text/javascript" src="<s:property value="baseURL" />js/scripts.js"></script>
            <script type="text/javascript" src="<s:property value="baseURL" />js/jquery-ui-1.10.4.custom.min.js"></script>
            
            <link type="text/css" rel="stylesheet" href="<s:property value="baseURL" />css/overlay.css" />
            <!--[if lt IE 10]><link type="text/css" rel="stylesheet" href="<s:property value="baseURL" />css/overlay-ie.css" /><![endif]-->
        </head>
        <body>
            <div class="d-main">
                <div class="d-cont-menu">
                    <tiles:insertAttribute name="header" />
                </div>		
                <div class="d-content">	
                    <s:if test='%{indErrAcc!=""}'>
                        <div class="ui-state-error ui-corner-all" style="padding: 0 .7em; padding-top: 5px; width: 300px; margin: auto; margin-top: 40px;">
                            <span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
                            <span style="color: #C60200; font-weight: bold; font-size: 12px;">Error</span>
                            <span style="display: block; color: #C60200; margin-top: 10px; margin-bottom: 20px;">
                                No cuenta con permisos para realizar esta acción.
                                <br />
                                <br />
                                Será redireccionado a la pagina anterior
                            </span>
                        </div>
                    </s:if>
                    <s:else>
                        <tiles:insertAttribute name="body" />		
                    </s:else>
                </div>
                <div class="d-footer">
                    <table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
                        <tr>
                            <td style="width: 350px;">
                                &copy; <s:property value="#session.ses_descon" />. Todos los derechos reservados.
                            </td>
                            <td></td>
                            <td style="width: 350px; text-align: right">
                                Desarrollado por 3P Soluciones Integrales
                            </td>
                        </tr>
                    </table>
                    
                </div>
            </div>
            <div class="overlay">
                <div class="load-container">
                    <div class="load-table">
                        <img src='<s:property value="baseURL" />images/load-big.gif' />
                    </div>
                </div>
            </div>

            <div id="DIVverif" title="<s:property value="titleDialog" />" class="alerta"></div>
            <div id="DIVchgPwd" title="<s:property value="titleDialog" />" class="alerta"></div>
            <div id="DIVupdDtsPer" title="<s:property value="titleDialog" />" class="alerta"></div>
            <!--DIV para errores genericos como por ejemplo: caducidad ed sesion, falta de permisos para accion, 
            entre otros -->
            <div id="DIVerroresGen" title="<s:property value="titleDialog" />" class="alerta"></div>
        </body>
    </html>

    <script type="text/javascript">
        <s:if test='%{indErrAcc!=""}'>
            setTimeout(function(){ history.back(-10); },4000);
        </s:if>
            
        $('.d-paging').css('width',$('.d-grilla').outerWidth()-18);
            
        $('button').button();
            
        $('#DIVverif').dialog({
            autoOpen: false,
            width: 380,
            height: 180,
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
        
        $('#DIVchgPwd').dialog({
            autoOpen: false,
            width: 366,
            height: 220,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false,
            buttons:{
                "Aceptar":function(){
                    $('#DIVchgPwd').dialog('close');
                    $('#opcion_h2').val('G');
                    
                    post(
                        '<s:property value="baseURL" /><s:url includeContext="false" namespace="usuarios" action="updPasswordUsuario" />',
                        $('#frmUpdPassword').serialize(),
                        function(resultado){
                            var _error = resultado.indexOf('error');
                            
                            if(_error != -1)
                            {
                                $('#DIVerroresGen').dialog({
                                    buttons:{
                                        "Aceptar":function(){
                                            $('#DIVerroresGen').dialog('close');
                                            $('#DIVerroresGen').html('');
                                            $('#otrClaUsu').val('');
                                            $('#otrNueClaUsu').val('');
                                            $('#otrNueClaUsu2').val('');
                                            $('#DIVchgPwd').dialog('open');
                                        }
                                    }
                                });
                                $('#DIVerroresGen').html(resultado);
                                $('#DIVerroresGen').dialog('open');
                            }
                            else
                            {   
                                $('#DIVchgPwd').dialog('close');
                                
                                post(
                                    '<s:property value="baseURL" /><s:url includeContext="false" namespace="usuarios" action="updVarSesionCaducClaUsuario" />',
                                    {},
                                    function(resultado){
                                        location.href = $(location).attr('href');
                                    },
                                    4
                                );
                            }
                        },
                        3
                    );
                },
                "Cancelar":function(){
                    <s:if test='%{#session.ses_indclares=="R" || #session.ses_indmencad=="V"}'>
                    $.post(
                        '<s:property value="baseURL" /><s:url includeContext="false" namespace="/" action="salirLogin" />',
                        {},
                        function(resultado){
                            location.href = $(location).attr('href');
                        }
                    ); 
                    </s:if>
                    <s:else>
                        $('#DIVchgPwd').dialog('close');
                        hideOverlay(null);
                    </s:else>
                }
            }
        });
        
        //Si la contraseña del usuario ha caducado o se le haya reseteado su contraseña,se mostrará 
        //un popup con un formulario el cual le permitirá cambiar su contraseña, si no cambiase 
        //su contraseña y simplemente cerrara el popup, la aplicacion terminará su sesión y lo redireccionará 
        //a la pagina de acceso a la aplicación
        <s:if test='%{#session.ses_indmencad=="V" || #session.ses_indclares=="R"}'>
        post(
            '<s:property value="baseURL" /><s:url includeContext="false" namespace="usuarios" action="updPasswordUsuario" />',
            {
                opcion:'F'
            },
            function(resultado)
            {
                $('#DIVchgPwd').html(resultado);
                $('#DIVchgPwd').dialog('open');
            },
            2
        );
        </s:if>
        
        $('#DIVupdDtsPer').dialog({
            autoOpen: false,
            width: 340,
            height: 200,
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
        
        $('#DIVerroresGen').dialog({
            autoOpen: false,
            width: 380,
            height: 200,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false,
            buttons:{
                "Aceptar":function(){
                    $('#DIVerroresGen').dialog('close');
                    hideOverlay(function(){});
                }
            }
        });
        
        $(document).ready(function() {
            
        });
    </script>
    </s:else>
</s:else>