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
    <div class="d-header-labels">
        <table>
            <tr>
                <td class="lbl-concept">Marca: </td>
                <td class="lbl-value"><span><s:property value="desMar" /><span></td>
                <td class="lbl-concept">Modelo: </td>
                <td class="lbl-value"><span><s:property value="desModMar" /><span></td>
            </tr>
        </table>
    </div>
</div>

<form method="POST" id="frm_versiones_back" action="<s:property value="backURL" />">
    <s:hidden name="idMar" id="idMar" />
    <s:hidden name="idModMar" id="idModMar"/>
    <s:hidden name="desVerMod_f" id="desVerMod_f" value="" />    
    <s:hidden name="varReturn" id="varReturn_f" />
</form>

<div class="d-content-form">
    <s:form id="frm_versiones" action='javascript:void(0)' theme="simple">
        <s:hidden name="idMar" id="idMar" />
        <s:hidden name="idModMar" id="idModMar"/>
        
        <table border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                        <tr>
                            <td style="width: 110px;">
                                Código<span class="required">*</span>
                            </td>
                            <td style="">
                                <s:if test='%{opcion=="A"}'>
                                    <s:textfield name="idVerMod" cssClass="element-form" cssStyle="width: 40px;" maxLength="4"
                                                 onkeypress="return isNumberIntegerKey(event)" />
                                </s:if>
                                <s:elseif test='%{opcion=="M"}'>
                                    <s:textfield name="idVerMod" cssClass="element-form" cssStyle="width: 40px;" maxLength="4" disabled="true" />
                                    <s:hidden name="idVerMod" />
                                </s:elseif>    
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 110px;">
                                Descripción<span class="required">*</span>
                            </td>
                            <td style="">
                                <s:textfield name="desVerMod" cssClass="element-form" cssStyle="width: 350px;" maxLength="50" />
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 110px;">
                                Katashiki<span class="required">*</span>
                            </td>
                            <td style="">
                                <s:textfield name="desKat" cssClass="element-form" cssStyle="width: 170px;" maxLength="25" />
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 110px;">
                                Prefijo de Chasis<span class="required">*</span>
                            </td>
                            <td>
                                <s:textfield name="desPreCha" cssClass="element-form" cssStyle="width: 150px;" maxLength="20" />
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 110px;height: 35px;">
                                Prefijo de Motor<span class="required">*</span>
                            </td>
                            <td style="">
                                <s:textfield name="desPreMot" cssClass="element-form" cssStyle="width: 80px;" maxLength="10" />
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 135px;">
                                Categoría de vehículo<span class="required">*</span>
                            </td>
                            <td>
                                <s:select name="idCatVeh" id="idCatVeh" list="listCatVehiculo" listKey="idCatVeh" listValue="desCatVeh"
                                          headerKey="0" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width: 200px;" />
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 135px;">
                                Clase de vehículo<span class="required">*</span>
                            </td>
                            <td>
                                <s:select name="idClaVeh" id="idClaVeh" list="listClaVehiculo" listKey="idClaVeh" listValue="desClaVeh" 
                                          headerKey="0" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width: 200px;" />
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 135px;">
                                Tipo de carrocería<span class="required">*</span>
                            </td>
                            <td>
                                <s:select name="idTipCar" id="idTipCar" list="listTipCarroceria" listKey="idTipCar" listValue="desTipCar"
                                          headerKey="0" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width: 200px;" />
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 135px;">
                                Tipo de combustible<span class="required">*</span>
                            </td>
                            <td>
                                <s:select name="idTipCom" id="idTipCom" list="listTipCombustible" listKey="idTipCom" listValue="desTipCom"
                                          headerKey="0" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width: 200px;" />
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 135px;">
                                Tipo de transmisión<span class="required">*</span>
                            </td>
                            <td> 
                                <s:select name="idTipTras" id="idTipTras" list="listTipTransmision" listKey="idTipTras" listValue="desTipTras"
                                          headerKey="0" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width: 200px;" />
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 135px;">
                                Tipo de tracción<span class="required">*</span>
                            </td>
                            <td> 
                                <s:select name="idTipTrac" id="idTipTrac" list="listTipTraccion" listKey="idTipTrac" listValue="desTipTrac"
                                          headerKey="0" headerValue="-Seleccione-" cssClass="element-form" cssStyle="width: 200px;" />
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td style="padding-top: 10px;">
                                <button id="btn_grabar">
                                    Grabar
                                </button>
                                <span class="required">(*) campos obligatorios</span>
                            </td>
                        </tr>
                    </table>
                </td>
                <td style="vertical-align: top; padding-left: 50px;">
                    <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                        <tr>
                            <td style="width: 125px;">
                                Potencia de motor<span class="required">*</span>
                            </td>
                            <td>
                                <s:textfield name="desPotMot" cssClass="element-form" cssStyle="width: 190px;" maxLength="25" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <table border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td style="width: 215px;">
                                            <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                                <tr>
                                                    <td style="width: 125px;">
                                                        Cilindrada<span class="required">*</span>
                                                    </td>
                                                    <td>
                                                        <s:textfield name="numCilVeh" cssClass="element-form" cssStyle="width: 60px;" maxLength="6"
                                                                     onkeypress="return isNumberKey(event)" />
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td style="">
                                            <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                                <tr>
                                                    <td style="width: 115px;" title="Número de cilindros">
                                                        Nro. de cilindros<span class="required">*</span>
                                                    </td>
                                                    <td>
                                                        <s:textfield name="numCil" cssClass="element-form" cssStyle="width: 40px;" maxLength="2"
                                                                     onkeypress="return isNumberIntegerKey(event)" />
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <table border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td style="width: 215px;">
                                            <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                                <tr>
                                                    <td style="width: 125px;" title="Número de asientos">
                                                        Nro. de asientos<span class="required">*</span>
                                                    </td>
                                                    <td>
                                                        <s:textfield name="numAsiVeh" cssClass="element-form" cssStyle="width: 40px;" maxLength="2"
                                                                     onkeypress="return isNumberIntegerKey(event)" />
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td style="">
                                            <table border="0" cellpadding="0" cellspacing="0" class="table-form">
                                                <tr> 
                                                    <td style="width: 115px;" title="Número de pasajeros">
                                                        Nro. de pasajeros<span class="required">*</span>
                                                    </td>
                                                    <td>
                                                        <s:textfield name="numPasVeh" cssClass="element-form" cssStyle="width: 40px;" maxLength="2"
                                                                     onkeypress="return isNumberIntegerKey(event)" />
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            
                        </tr>
                        <tr>
                            <td style="width: 125px;" title="Número de puertas">
                                Nro. de puertas<span class="required">*</span>
                            </td>
                            <td>
                                <s:textfield name="numPueVeh" cssClass="element-form" cssStyle="width: 40px;" maxLength="2"
                                             onkeypress="return isNumberIntegerKey(event)" />
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 125px;" title="Número de ejes">
                                Nro. de Ejes<span class="required">*</span>
                            </td>
                            <td>
                                <s:textfield name="numEjeVeh" cssClass="element-form" cssStyle="width: 40px;" maxLength="2"
                                             onkeypress="return isNumberIntegerKey(event)" />
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 125px;" title="Número de ruedas">
                                Nro. de ruedas<span class="required">*</span>
                            </td>
                            <td>
                                <s:textfield name="numRueVeh" cssClass="element-form" cssStyle="width: 40px;" maxLength="2"
                                             onkeypress="return isNumberIntegerKey(event)" />
                            </td>
                        </tr>
                        <tr> 
                            <td style="width: 125px;">
                                Distancia entre ejes<span class="required">*</span>
                            </td>
                            <td>
                                <s:textfield name="numDisEje" cssClass="element-form" cssStyle="width: 60px;" maxLength="5"
                                             onkeypress="return isNumberKey(event)" /> mm
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 125px;">
                                Medida Largo<span class="required">*</span>
                            </td>
                            <td>
                                <s:textfield name="numMedLar" cssClass="element-form" cssStyle="width: 60px;" maxLength="5"
                                             onkeypress="return isNumberKey(event)" /> mm
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 125px;">
                                Medida Ancho<span class="required">*</span>
                            </td>
                            <td>
                                <s:textfield name="numMedAnc" cssClass="element-form" cssStyle="width: 60px;" maxLength="5"
                                             onkeypress="return isNumberKey(event)" /> mm
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 125px;">
                                Medida Alto<span class="required">*</span>
                            </td>
                            <td>
                                <s:textfield name="numMedAlt" cssClass="element-form" cssStyle="width: 60px;" maxLength="5"
                                             onkeypress="return isNumberKey(event)" /> mm
                            </td>
                        </tr>
                        <tr> 
                            <td style="width: 125px;">
                                Peso Neto<span class="required">*</span>
                            </td>
                            <td>
                                <s:textfield name="numPesNet" cssClass="element-form" cssStyle="width: 60px;" maxLength="6"
                                             onkeypress="return isNumberKey(event)" /> Kg
                            </td>
                        </tr>
                        <tr>
                            <td style="width: 125px;">
                                Peso Bruto<span class="required">*</span>
                            </td>
                            <td>
                                <s:textfield name="numPesBru" cssClass="element-form" cssStyle="width: 60px;" maxLength="6"
                                             onkeypress="return isNumberKey(event)" /> Kg
                            </td>
                        </tr>
                        <tr>
                            <tr>
                                <td style="width: 125px;">
                                    Cargo Util<span class="required">*</span>
                                </td>
                                <td style="width: 390px;">
                                    <s:textfield name="numCarUti" cssClass="element-form" cssStyle="width: 60px;" maxLength="6"
                                                 onkeypress="return isNumberKey(event)" /> Kg
                                </td>
                            </tr>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <s:iterator value="errores">
            <s:property />
        </s:iterator>
    </s:form>
</div>
<div id="DIVgrabar" title="<s:property value="titleDialog" />" class="alerta"></div>


<script type="text/javascript">
    $(document).ready(function() {
        resizeContForm();

        $('#DIVgrabar').dialog({
            autoOpen: false,
            width: 400,
            height: 200,
            modal: true,
            closeOnEscape: false,
            buttons:{
                "Aceptar": function() {
                    $('#DIVgrabar').dialog("close");
                    $('.overlay').animate({'opacity': '0'}, 250, 'swing', function() {
                        $('.overlay').css({'z-index': '-1'});
                    });
                }
            },
            open: function(event, ui) {
                $(".ui-dialog-titlebar-close").hide();
            },
            draggable: false,
            resizable: false
        });

        $('a.back').click(function() {
            $('#frm_versiones_back').submit();
        });

        $('#btn_grabar').click(function() {
            post(
                '<s:property value="formURL" />',
                $('#frm_versiones').serialize(),
                function(resultado) {
                    resultado = $.trim(resultado);
                    var _error = resultado.indexOf("error");
                    if (_error != -1)
                    {
                        $('#DIVgrabar').html(resultado);
                        $('#DIVgrabar').dialog('open');
                    }
                    else
                    {   
                        $('#frm_versiones_back').submit();
                    }
                },
                1
            );
        });
    });
</script>