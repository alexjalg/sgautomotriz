<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <script type="text/javascript" src="<s:property value="baseURL" />js/jquery.js"></script>
        <title>Imprimiendo...</title>
    </head>
    <body>
        <ul>
        <s:iterator value="errores">
            <li><s:property /></li>
        </s:iterator>
        </ul>
        <input type="button" value="Posicion" id="btnPosicion" />
        <input type="button" value="PDF" id="btnPDF" />
        <applet name="nomApplet" code="hiddenPrint.class"
                codebase="<s:property value="baseURL" />applets"
                archive="hiddenPrint.jar,/lib/PDFRenderer-0.9.1.jar" width="0" height="0">
            <param value="/sgautomotriz/pdf/Prueba.pdf" name="urlService">
        </applet>
    </body>

    
<!--<applet name="nomApplet" code="applet1.class"
        codebase="<s:property value="baseURL" />" 
            width="400" height="100">
    </applet>-->
</html>
    <script type="text/javascript">
        $(document).ready(function(){
            ejecutoAppletPDF('/sgautomotriz/pdf/Prueba.pdf');

            $('#btnPosicion').click(function(){
                ejecutoAppletPosicion();
            });

            $('#btnPDF').click(function(){
                ejecutoAppletPDF('/sgautomotriz/pdf/Prueba.pdf');
            });
        });

        function ejecutoAppletPDF(archivo) {
            //aqui hay que mandarle el nombre que le dimos al applet
            var Myapplet = document.applets['nomApplet'];
            var p = Myapplet.imprimePDFJavascript(archivo);
            //alert(p); //si el metodo del applet retorna algo con esto podemos ver el resultado

            //window.setInterval(function(){window.close()},5000);
        }

        function ejecutoAppletPosicion() {
            //aqui hay que mandarle el nombre que le dimos al applet
            var Myapplet = document.applets['nomApplet'];
            var p = Myapplet.imprimeFacturaJavascript();
            //alert(p); //si el metodo del applet retorna algo con esto podemos ver el resultado

            //window.setInterval(function(){window.close()},5000);
        }
    </script>
