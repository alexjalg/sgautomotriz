<%@taglib uri="/struts-tags" prefix="s" %>
<ul class="ul-modulos">
<s:if test='%{#session.ses_indmencad!="V" && #session.ses_indclares!="R"}'>
    <s:iterator value="#session.ses_listmodumaster" status="stat_modu">
        <li><s:property value="desModu" escape="false" />
        <s:if test='%{cantOpcDep>0}'>
            <ul class="ul-opciones ul-opc">
            <s:iterator value="listOpc" status="stat_opc">
                <s:if test='%{cantOpcDep>0}'>
                <li>
                    <span class="cont-opciones opc"><s:property value="desOpc" escape="false" /></span>
                    <ul class="ul-opciones ul-subopc">
                        <s:iterator value="listSubOpc" status="stat_subopc">
                        <li>
                            <a class="subopc" href="<s:property value="baseURL" /><s:property value="desUrlOpc" />?mtu=<s:property value="idTipUsu" />&mmo=<s:property value="idModu" />&mop=<s:property value="idOpc" />&mni=<s:property value="numNivOpc" />&mod=<s:property value="idOpcDep" />">
                                <s:property value="desOpc" escape="false" />
                            </a>
                        </li>
                        </s:iterator>
                    </ul>
                </li>    
                </s:if>
                <s:else>
                <li class="sin-opciones">
                    <a class="opc" href="<s:property value="baseURL" /><s:property value="desUrlOpc" />?mtu=<s:property value="idTipUsu" />&mmo=<s:property value="idModu" />&mop=<s:property value="idOpc" />&mni=<s:property value="numNivOpc" />&mod=<s:property value="idOpcDep" />">
                        <s:property value="desOpc" escape="false" />
                    </a>
                </li>
                </s:else>
            </s:iterator>    
            </ul>
        </s:if>
        </li>
    </s:iterator>
</s:if>
</ul>
<div class="d-datos-user ui-state-default">
    <span class="name-user" style="padding-right: 15px;">
        T.C.:&nbsp;&nbsp;<s:property value="#session.ses_tipcam" />
    </span>
    <span class="ui-icon ui-icon-person"></span> 
    <span class="name-user"><s:property value="#session.ses_desusu" /></span>
    <div id="d-option-user">
        <span class="ui-icon ui-icon-triangle-1-s option-user-icon"></span>
        
        <ul class="ul-options-user">
            <li>
                <div id="chg-pwd" class="elem-option-user">
                    <span class="ui-icon ui-icon-transferthick-e-w"></span>
                    <span class="text-option-user">
                        Cambiar&nbsp;contraseña
                    </span>
                    <input type="hidden" value="<s:property value="baseURL" /><s:url includeContext="false" namespace="usuarios" action="updPasswordUsuario" />" />
                </div>
            </li>
            <li>
                <div id="upd-contact" class="elem-option-user">
                    <span class="ui-icon ui-icon-contact"></span>
                    <span class="text-option-user">
                        Actualizar&nbsp;datos&nbsp;personales
                    </span>
                </div>
            </li>
            <li>
                <div id="close-session" class="elem-option-user">
                    <span class="ui-icon ui-icon-power"></span>
                    <span class="text-option-user">
                        Cerrar sesión
                    </span>
                    <input type="hidden" value="<s:property value="baseURL" />salirLogin" />
                </div>
            </li>
        </ul>
    </div>
</div>