<%@taglib uri="/struts-tags" prefix="s" %>
<div id="aux"></div>
<div class="d-content-grilla" style="min-width: 550px;">
    <form id="frm_princ_pu" method="POST" action="<s:property value="baseURL" /><s:property value="urlPaginacion" />">
    <s:hidden name="idTipUsu" />
    <s:hidden name="opcion" />

    <s:hidden name="curPagVis" id="curPag_f_pu" />
    
    <div class="d-grilla" style="overflow: hidden;">
        <div class="d-content-grilla-head" style="">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <tr class="tr-head">
                    <td style="width: 24px;"></td>
                    <td>
                        Módulo
                    </td>
                </tr>
                <tr class="tr-head">
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </div>
        <div class="d-content-grilla-body d-content-grilla-body-popup">
            <table border="0" cellpadding="0" cellspacing="0" style="">
                <s:iterator value="listModulos">
                <tr>
                    <td style="width: 24px;">
                        <input type="radio" name="idModu" id="rbt_idModu" value="<s:property value="idModu" />" class="select_rec" />
                    </td>
                    <td>
                        <s:property value="desModu" />
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
        /*Paginacion de grilla*/
        <s:property value="jsPaginacionPopUp" escape="false" />;
        $('#btn_search_pu').css('display','none');        
                
        $('#prev_pag_pu').click(function(){
            $.post(
                $('#frm_princ_pu').attr('action'),
                $('#frm_princ_pu').serialize(),
                function(resultado){
                    $('#DIVmodulos').html(resultado);
                }
            );
        });
        
        $('#next_pag_pu').click(function(){
            $.post(
                $('#frm_princ_pu').attr('action'),
                $('#frm_princ_pu').serialize(),
                function(resultado){
                    $('#DIVmodulos').html(resultado);
                }
            );
        });
    });
    
    //Redimensionar el alto del contenedor de grilla si fuese necesario
    resizeContGrillaPopUp(<s:property value="regPag" />);    
</script>