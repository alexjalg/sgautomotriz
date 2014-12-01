<%@taglib uri="/struts-tags" prefix="s" %>
<!-- cabecera de pagina -->
<div class="d-header">
    <div class="d-title-header">
        <s:property value="tituloOpc" />
        <div class="d-subtitle-header">
            <s:property value="accion" />
        </div>
    </div>
    <div class="d-subheader">
        <div class="d-back">
            <a href="javascript:void(0)" class="back">Volver</a>
        </div>
    </div>
</div>

<form method="POST" id="frm_veh_back" action="<s:property value="backURL" />">
    <s:hidden name="varReturn" />
</form>

<div class="d-content-form">
    <table border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td style="">
                <div class="d-block-form">
                    <div class="text-block">Datos del vehículo</div>
                    <table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
                        <tr>
                            <td>
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="width: 108px;">Serie:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="idVeh" />
                                            </span>
                                        </td>   
                                    </tr>
                                    <tr>
                                        <td>Marca:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desMar" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Modelo:</td>
                                        <td> 
                                            <span style="font-weight: bold;">
                                                <s:property value="desModMar" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Versión:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desVerMod" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Color exterior:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desColExt" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Color interior:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desColInt" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Año de fabricación:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="numAnoFab" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Año Modelo:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="numAnoMod" />
                                            </span>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td style="vertical-align: top; padding-left: 50px;">
                                <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                    <tr>
                                        <td style="width: 108px;">Nº Chasis:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desNumCha" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Nº Motor:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desNumMot" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Precio de venta:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="impPreVenAsi" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Kilometraje:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="numKimAct" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Ambiente:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desAmbUbi" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Ubicación:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desUbiAmb" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Situación:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="desSitVeh" />
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Fecha de situación:</td>
                                        <td>
                                            <span style="font-weight: bold;">
                                                <s:property value="fecSitVeh" />
                                            </span>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
            <td style="width: 300px; padding-left: 60px; vertical-align: top;">
                <div class="d-block-form">
                    <div class="text-block">Datos del proveedor</div>
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form" style="width: 100%" >
                        <tr>
                            <td style="width: 136px;">Poliza de importación:</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="desPolImp" />
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td>Nº Factura:</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="desNumFacPrv" />
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td>Fecha Factura:</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="fecFacPrv" />
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td>Moneda:</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="desMonFacPrv" />
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td>Tipo de cambio:</td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="impTipCamFP" />
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td title="Importe moneda local">
                                Imp. moneda local:
                            </td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="impMonLoc" />
                                </span>
                            </td>
                        </tr>
                        <tr>
                            <td title="Importe moneda extanjera">
                                Imp. moneda extranjera:
                            </td>
                            <td>
                                <span style="font-weight: bold;">
                                    <s:property value="impMonExt" />
                                </span>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="vertical-align: top; padding-top: 20px;">
                <table border="0" cellpadding="0" cellspacing="0" >
                    <tr>
                        <td style="width: 660px;">
                            <div class="d-block-form">
                                <div class="text-block">Datos de venta y entrega</div>
                                <table border="0" cellpadding="0" cellspacing="0" style="width: 100%">
                                    <tr>
                                        <td>
                                            <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                                <tr>
                                                    <td style="width: 87px;">Situación:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:property value="desSitVen" />
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Fec. situación:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:property value="fecSitVen" />
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Modalidad:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:if test="%{idModVen!=0}">
                                                                <s:property value="desModVen" />
                                                            </s:if>
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Cliente:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:if test='%{idCli!="" && idCli!="9999999999"}'>
                                                                <s:property value="idCli" /> - <s:property value="desCli" />
                                                            </s:if>
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td title="">Tipo doc. venta:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:property value="desTipDocVen" />
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Nº doc. venta:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:property value="desNumDocVen" />
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Fec. emisión:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:property value="fecEmiDocVen" />
                                                        </span>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td style="vertical-align: top; padding-left: 50px;">
                                            <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                                <tr>
                                                    <td style="width: 84px;">Fecha entrega:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:property value="fecEntCli" />
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Nº de placa:</td>
                                                    <td>
                                                        <span style="font-weight: bold;">
                                                            <s:property value="desNumPla" />
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td style="vertical-align: top; padding-top: 6px;">Observación:</td>
                                                    <td>
                                                        <span style="font-weight: bold; display: inline-block; width: 300px; height: 100px; margin-top: 6px; margin-left: 10px;">
                                                            <s:property value="desObs" />
                                                        </span>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <ul>
    <s:iterator value="errores">
        <li><s:property /></li>
    </s:iterator>
    </ul>
</div>                   

<script type="text/javascript">
    $(document).ready(function() {
        $('a.back').click(function() {
            $('#frm_veh_back').submit();
        });
    });
</script>