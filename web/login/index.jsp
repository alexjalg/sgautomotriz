<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Sistema de Gestión Automotriz</title>
        
        <link rel="stylesheet" type="text/css" href="<s:property value="baseURL" />css/jquery-ui-1.10.4.custom.min.css" />
        <link type="text/css" rel="stylesheet" href="css/overlay.css" />
        <!--[if lt IE 10]><link type="text/css" rel="stylesheet" href="css/overlay-ie.css" /><![endif]-->
        <link type="text/css" rel="stylesheet" href="css/login.css" />
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="<s:property value="baseURL" />js/jquery-ui-1.10.4.custom.min.js"></script>
        <script type="text/javascript" src="js/scripts.js"></script>
    </head>
    <body>
        <div style="opacity:1; width: 100%; height: 100%; position: fixed;">
            <div class="login-container">
                <div class="login-table">
                    <s:form id="frm-login">
                        <div class="login-form" style="font-size: 12px;">
                            <div class="login-form-header">
                                <div class="header-login">
                                    Sistema de Gestión Automotríz
                                </div>
                            </div>
                            <div class="d-form-fisrt">
                                <table border="0" cellpadding="0" cellspacing="0" style="width: 100%; margin: 0 auto;">
                                    <!--<tr>
                                        <td style="height: 18px">Usuario</td>
                                    </tr>-->
                                    <tr>
                                        <td>
                                            <input type="text" name="idUsu" id="idUsu" class="element-form" style="" />
                                        </td>
                                    </tr>
                                    <!--<tr>
                                        <td style="padding-top: 10px; height: 18px;">Contraseña</td>
                                    </tr>-->
                                    <tr>
                                        <td>
                                            <input type="password" name="otrPwdUsu" id="otrPwdUsu" class="element-form" style="" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 20px;">
                                            <button type="button" id="btn-login" style="width: 230px; height: 32px;">
                                                Ingresar
                                            </button>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <!--<div class="d-form-second">
                                <table border="0" cellpadding="0" cellspacing="0" style="width: 100%;">
                                    <tr>
                                        <td style="text-align: right;">
                                            <button type="button" class="boton-bandeja" id="btn-login" style="height: 28px; margin-left: 0px; 
                                                    padding: 4px 10px; font-size: 12px; font-weight: bold;">
                                                Ingresar
                                            </button>
                                        </td>
                                    </tr>
                                </table>
                            </div>-->
                            <div class="both"></div>
                        </div>
                    </s:form>
                </div>
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
    </body>
    <script type="text/javascript">
        $(document).ready(function() {    
            $('#btn-login').button();
        
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
                        closeDialog();
                    }
                },
                open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
                draggable: false,
                resizable: false
            });
	    
	    var InputText = "Text in grau...";
	 
	    $('#idUsu').val('Usuario');
            $('#idUsu').addClass('focus');
            $('#otrPwdUsu').val('Contraseña');
            $('#otrPwdUsu').attr('type','text');
            $('#otrPwdUsu').addClass('focus');
	   
	    $('#idUsu').bind('focus',function(){
	        if($(this).val()=='Usuario'){
                    $(this).removeClass('focus');
	            $(this).val('');
	        }
	    }).bind('blur',function(){
	        if($(this).val()==""){
	            $(this).addClass('focus');
                    $(this).val('Usuario');
	        }
	    });
            
            $('#otrPwdUsu').bind('focus',function(){
	        if($(this).val()=='Contraseña'){
                    $(this).removeClass('focus');
                    $('#otrPwdUsu').attr('type','password');
	            $(this).val('');
	        }
	    }).bind('blur',function(){
	        if($(this).val()==""){
	            $(this).addClass('focus');
                    $(this).attr('type','text');
                    $(this).val('Contraseña');
	        }
	    });
        
            $('#idUsu, #otrPwdUsu').keyup(function(e){
                if(e.which == 13) 
                {
                    $('#btn-login').click();
                }
            });
            
            
            $('#btn-login').click(function() {
                if($.trim($('#idUsu').val())!='' && $.trim($('#otrPwdUsu').val())!='')
                {
                    var _datos = $('#frm-login').serialize();
                    post(
                        'verificaLogin',
                        _datos,
                        function(resultado){
                            resultado = $.trim(resultado);
                            if(resultado=='')
                            {
                                location.href = '<s:property value="baseURL" />';
                            }
                            else
                            {
                                $('#DIVverif').html(resultado);
                                $('#DIVverif').dialog('open');
                                //$('.login-error').html(resultado);
                            }
                        },
                        1
                    );
                }
            });
            
            $('#idUsu').focus();
        });
    </script>
</html>
