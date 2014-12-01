<%@taglib uri="/struts-tags" prefix="s" %>
<div id="aux"></div>
<div class="d-content-grilla" style="min-width: 550px;">
    <form id="frm_princ_pu" method="POST" action="<s:property value="baseURL" /><s:property value="urlPaginacion" />">
    <s:hidden name="curPagVis" id="curPag_f_pu" />
    
    <div class="d-grilla" style="overflow: hidden;">
        <div class="d-content-grilla-head" style="">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td style="width: 90px; text-align: center;">Cod.</td>
                    <td style="<s:if test='%{desCli_f!=""}'> background-color: #B5CCED; </s:if>">
                        Cliente
                    </td>
                </tr>
                <tr class="tr-head">
                    <td></td>
                    <td></td>
                    <td style="<s:if test='%{desCli_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="desCli_f" cssClass="element-form-grid" cssStyle="width: 200px;" maxLength="50" />
                    </td>
                </tr>
            </table>
        </div>
        <div class="d-content-grilla-body d-content-grilla-body-popup">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <s:iterator value="listClientes">
                <tr>
                    <td style="width: 24px;">
                        <input type="radio" name="idCli" id="rbt_idCli" value="<s:property value="idCli" />" class="select_rec" />
                    </td>
                    <td style="width: 90px; text-align: center;">
                        <s:property value="idCli" />
                    </td>
                    <td style="">
                        <s:property value="desCli" />
                    </td>
                </tr>
                </s:iterator>
            </table>
        </div>
        
        <div class="d-content-grilla-footer">
            <div class="d-paging">
                <s:property value="paginacionPopUp" escape="false" />
            </div>
        </div>
    </div>
    </form>
</div>
        
<script type="text/javascript">
    $(document).ready(function() {
        /*Paginacion de grilla popup */
        <s:property value="jsPaginacionPopUp" escape="false" />;    
    });
    
    //Redimensionar el alto del contenedor de grilla si fuese necesario
    resizeContGrillaPopUp(<s:property value="regPag" />,'');    
</script>