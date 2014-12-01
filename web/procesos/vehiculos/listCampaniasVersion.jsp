<%@taglib uri="/struts-tags" prefix="s" %>
<div id="aux"></div>
<div class="d-content-grilla" style="min-width: 550px;">
    <form id="frm_princ_camp" method="POST" action="<s:property value="baseURL" /><s:property value="urlPaginacion" />">
    <s:hidden name="curPagVis" id="curPag_f_pu" />
    
    <div class="d-grilla" style="overflow: hidden;">
        <div class="d-content-grilla-head" style="">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td style="width: 100px; text-align: center;">Origen</td>
                    <td style="">
                        Campaña
                    </td>
                </tr>
                <tr class="tr-head">
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </div>
        <div class="d-content-grilla-body d-content-grilla-body-popupCamp">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <s:iterator value="listCampanias">
                <tr>
                    <td style="width: 24px;">
                        <input type="radio" name="idCamStr" id="rbt_idCamStr" value="<s:property value="idCamStr" />" class="select_rec" />
                    </td>
                    <td style="width: 100px; text-align: center;">
                        <s:property value="desOriCam" />
                    </td>
                    <td style="">
                        <s:property value="desCam" />
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
                <s:iterator value="errores">
                    <s:property />
                </s:iterator>
</div>
        
<script type="text/javascript">
    $(document).ready(function() {
        $('#btn_search_puCamp').css('visibility','hidden');
    
        /*Paginacion de grilla popup */
        <s:property value="jsPaginacionPopUp" escape="false" />;    
    });
    
    //Redimensionar el alto del contenedor de grilla si fuese necesario
    resizeContGrillaPopUp(<s:property value="regPag" />,'Camp');    
</script>