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
                    <tiles:insertAttribute name="body" />		
                    
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
    </html>

    <script type="text/javascript">
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
        
        $(document).ready(function() {
        });
    </script>
    </s:else>
</s:else>