<%@taglib uri="/struts-tags" prefix="s" %>
<div id="aux"></div>
<div class="d-header-filters">
    <table>
        <tr>
            <td class="">Marca: </td>
            <td class="">
                <s:select name="idMar" id="cbo_Marca" list="listMarcas" listKey="idMar" listValue="desMar"
                          cssClass="element-form" cssStyle="width: 120px;"/>
            </td>
            <td></td>
            <td class="">Modelo: </td>
            <td id="td_Modelo">
                <s:select name="idModMar" id="cbo_Modelo" list="listModelo" listKey="idModMar" listValue="desModMar"
                          cssClass="element-form" cssStyle="width:200px;"/>
            </td>

            <td class="">Año Fab.: </td>
            <td class="">
                <s:select name="numAnoFab" id="numAnoFab_f1" list="listAnios" listKey="idAnio" listValue="idAnio"
                          cssClass="element-form" cssStyle="width: 70px;"/>
            </td>
        </tr>
    </table>
</div>
<br>
<div class="d-content-grilla" style="min-width: 550px;">
    <form id="frm_princ_pu" method="POST" action="<s:property value="baseURL" /><s:property value="urlPaginacion" />">
        <s:hidden name="idOriCam" />
        <s:hidden name="idCam" />
        <s:hidden name="idMar" id="idMar_h1" />
        <s:hidden name="idModMar" id="idModMar_h1"/>
        <s:hidden name="numAnoFab" id="numAnoFab_h1"/>

        <s:hidden name="curPagVis" id="curPag_f_pu" />

        <div class="d-grilla" style="overflow: hidden;">
            <div class="d-content-grilla-head" style="">
                <table border="0" cellpadding="0" cellspacing="0" style="">
                    <tr class="tr-head">
                        <td style="width: 24px;"></td>
                        <td style="width: 50px;">
                            Código
                        </td>
                        <td>
                            Versión
                        </td>
                    </tr>
                    <tr class="tr-head">
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
            </div>
            <div class="d-content-grilla-body-multiple d-content-grilla-body-popup">
                <table border="0" cellpadding="0" cellspacing="0" style="">
                    <s:iterator value="listVersion">
                        <tr>
                            <td style="width: 24px;">
                                <input type="checkbox" name="listIdVerMod" id="rbt_idVerMod" value="<s:property value="idVerMod" />" class="select_rec" />
                            </td>
                            <td style="width: 50px;">
                                <s:property value="idVerMod" />
                            </td>
                            <td>
                                <s:property value="desVerMod" />
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
        /*Paginacion de grilla popup*/
    <s:property value="jsPaginacionPopUp" escape="false" />;
        $('#btn_search_pu').css('display', 'none');

        $('#cbo_Marca').change(function() {
            $('#idMar_h1').val($(this).val());
            $('#idModMar_h1').val('');

            $.post(
                    "listVersionesCondicionCampania.action",
                    $('#frm_princ_pu').serialize(),
                    function(resultado) {
                        $('#DIVversiones').html(resultado);
                    }
            );
        });

        $('#cbo_Modelo').change(function() {
            $('#idMar_h1').val($('#cbo_Marca').val());
            $('#idModMar_h1').val($(this).val());

            $.post(
                    "listVersionesCondicionCampania.action",
                    $('#frm_princ_pu').serialize(),
                    function(resultado) {
                        $('#DIVversiones').html(resultado);
                    }
            );
        });

        $('#numAnoFab_f1').change(function() {
            $('#numAnoFab_h1').val($(this).val());
        });

    });


    //Redimensionar el alto del contenedor de grilla si fuese necesario
    resizeContGrillaPopUp(<s:property value="regPag" />);
</script>