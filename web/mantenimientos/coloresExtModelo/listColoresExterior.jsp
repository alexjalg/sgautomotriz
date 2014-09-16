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
                    <td style="width: 40px; text-align: center;">Cod.</td>
                    <td style="<s:if test='%{modeloColorExterior.desColExt_f!=""}'> background-color: #B5CCED; </s:if>">
                        Color
                    </td>
                </tr>
                <tr class="tr-head">
                    <td></td>
                    <td></td>
                    <td style="<s:if test='%{modeloColorExterior.desColExt_f!=""}'> background-color: #B5CCED; </s:if>">
                        <s:textfield name="modeloColorExterior.desColExt_f" cssClass="element-form-grid" cssStyle="width: 200px;" maxLength="50" />
                    </td>
                </tr>
            </table>
        </div>
        <div class="d-content-grilla-body d-content-grilla-body-popup">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <s:iterator value="listColoresExterior">
                <tr>
                    <td style="width: 24px;">
                        <input type="radio" name="idColExt" id="rbt_idColExt" value="<s:property value="idColExt" />" class="select_rec" />
                    </td>
                    <td style="width: 40px; text-align: center;">
                        <s:property value="idColExt" />
                    </td>
                    <td style="">
                        <s:property value="desColExt" />
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
    resizeContGrillaPopUp(<s:property value="regPag" />);    
</script>