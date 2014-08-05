<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<s:if test='#session.ses_estado!="A"'>
    <div class="ui-state-error ui-corner-all" style="padding: 0 .7em; padding-top: 5px;">
        <span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
        <span style="color: #C60200; font-weight: bold; font-size: 13px;">Error</span>
        <s:hidden value="errorSesion" />
        <span style="display: block; color: #C60200; margin-top: 10px; margin-bottom: 20px;">
            Su sesión ha caducado. Redireccionando...
        </span>
    </div>
    <script type="text/javascript">
        $(document).ready(function() {
            $(".ui-dialog-buttonpane button").attr("disabled", true).addClass("ui-state-disabled");
        
            setTimeout("redireccionar()", 4000);
        });
        
        function redireccionar()
        {
            $(".ui-dialog-buttonpane button").removeAttr("disabled").removeClass("ui-state-disabled");
        
            window.location.href = '<s:property value="baseURL" />redirectInicio.action';
        }
    </script>
</s:if>
<s:else>
    <s:if test='%{indErrParm!=""}'>
    <!-- ADICIONAR ACCIONES SI SE PRESENTA EL SIGUIENTE ERROR :
    NO SE ENVIARON LOS PARAMETROS CORRECTOS -->
    
    </s:if>
    <s:else>		
        <tiles:insertAttribute name="body" />

        <script type="text/javascript">
            $(document).ready(function() {

            });
        </script>
    </s:else>
</s:else>