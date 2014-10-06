<%@taglib uri="/struts-tags" prefix="s" %>
<style type="text/css">
    
    .clslabel{
        
        font-weight: bold;
    }
    
</style>
<!-- cabecera de pagina -->
<div class="d-header">
    <div class="d-title-header">
        <s:property value="tituloOpc" />
        <div class="d-subtitle-header"></div>
    </div>
    <div class="d-subheader">
        <div class="d-back">
            <a href="javascript:void(0)" class="back">Volver</a>
        </div>
    </div>
</div>
            
<form method="POST" id="frm_cli_back" action="<s:property value="backURL" />">
    <s:hidden name="varReturn" />
</form>
        
<div class="d-content-form">
    <s:form id="frm_cli" action='javascript:void(0)' theme="simple">
        <table border="0" cellpadding="0" cellspacing="0" class="table-form" >
            <tr>
                <!-- datos Vehiculo   -->
                <td style="vertical-align: top;">
                    <table>
                      <tr>
                       <td style="vertical-align: top"> 
                        <table border="0" cellpadding="0" cellspacing="0" >
                        <tr>
                            <td style="padding-top:10px; font-size: 12px; color: #4282B7; font-weight: bold;" colspan="2">Datos del Vehículo</td>
                        </tr>
                        <!--tr>
                            <td>
                               <table>
                                <tr>
                                   <td style="width: 50px;">Serie<span class="required">*</span></td>
                                   <td>
                                   <!s:textfield id="idVeh" name="idVeh" cssClass="element-form" readonly="true" cssStyle="width:150px;text-transform: uppercase;" maxLength="70" />
                                   </td>   
                                </tr>
                               </table> 
                            </td>
                        </tr-->
                      <tr>
                          <td style="vertical-align: top">
                              <table border="0" cellpadding="0" cellspacing="2">
                                 <tr>
                                 <td>
                                 <table border="0" cellpadding="0" cellspacing="0">
                                 <tr>
                                   <td style="">Serie<span class="required">*</span></td>
                                   <td>
                                       <s:if test='%{opcion=="A"}'>
                                           <s:textfield id="idVeh" name="idVeh" cssClass="element-form"  cssStyle="width:120px;text-transform: uppercase;" maxLength="10" />
                                       </s:if>
                                       <s:else>
                                           <s:textfield id="idVeh" name="idVeh" cssClass="element-form"  cssStyle="width:120px;text-transform: uppercase;" maxLength="10" disabled="true" />
                                           <s:hidden name="idVeh" />
                                       </s:else>
                                      
                                   </td>   
                                </tr>    
                                 <tr>
                                    <td>Marca <span class="required">*</span></td>
                                    <td><s:select id="idMar" theme="simple" list="listMarcas"  name="idMar" listKey="idMar" listValue="desMar" cssClass="element-form" headerKey="0" headerValue="Seleccione" /></td>
                                 </tr>
                                 <tr>
                                    <td>Modelo <span class="required">*</span></td>
                                    <td class="tdModelos"> <s:select id="idModMar"  cssClass="element-form" name="idModMar" list="listModelos" listKey="idModMar" listValue="desModMar" headerKey="0" theme="simple"
    headerValue="Seleccione" onkeypress="return event.keyCode!=13" cssStyle="width:150px; max-width:150px; margin-right:10px;" /> </td>
                                </tr>
                                <tr>
                                    <td>Version <span class="required">*</span></td>
                                    <td class="tdVersiones" > <s:select id="idVerMod" name="idVerMod" theme="simple" cssClass="element-form" list="listVersiones" listKey="idVerMod" listValue="desVerMod" headerKey="0" headerValue="Seleccione"  /> </td>
                                </tr>
                                <tr>
                                    <td>Col Exterior <span class="required">*</span></td>
                                    <td class="tdColExt"><s:select id="idColExt"  cssClass="element-form" name="idColExt" list="listColoExt" listKey="idColExt" listValue="desColExt" headerKey="0" theme="simple"
            accesskey="" headerValue="Seleccione" onkeypress="return event.keyCode!=13" cssStyle="width:150px; max-width:150px; margin-right:10px;" /> </td>
                                </tr>
                                <tr>
                                    <td>Col Interior <span class="required">*</span></td>
                                    <td><s:select id="idColInt"  cssClass="element-form" name="idColInt" list="listColorInt" listKey="idColInt" listValue="desColInt" headerKey="0" theme="simple"
            accesskey="" headerValue="Seleccione" onkeypress="return event.keyCode!=13" cssStyle="width:150px; max-width:150px; margin-right:10px;" /></td>
                                </tr>
                                <tr>
                                    <td>Ambiente</td>
                                    <td><s:select id="idAmbUbi" name="idAmbUbi" theme="simple" list="listAmbientes" listKey="idAmbUbi"   listValue="desAmbUbi"  headerKey="" headerValue="Seleccione"  cssClass="element-form" /> </td>
                                </tr>
                                
                              
                                
                              </table>
                          </td>
                          <td style="vertical-align: top;padding-left: 25px"  >
                                              <table border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td>Num Chasis <span class="required">*</span></td>
                                                    <td> <s:textfield id="desNumCha" name="desNumCha" cssClass="element-form"  maxlength="20" cssStyle="width:150px;text-transform: uppercase;"   /> </td>
                                                </tr>  
                                                <tr>
                                                    <td>Num Año Fab <span class="required">*</span></td>
                                                    <td> <s:textfield name="numAnoFab" id="numAnoFab" maxLength="4" cssClass="element-form" cssStyle="width:60px" onkeypress="return isNumberIntegerKey(event)"  /> </td>
                                                </tr>
                                                <tr>
                                                    <td>Año Modelo <span class="required">*</span></td>
                                                    <td><s:textfield name="numAnoMod" id="numAnoMod" maxLength="4" cssClass="element-form" cssStyle="width:60px" onkeypress="return isNumberIntegerKey(event)" /> </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td>Num Motor <span class="required">*</span></td>
                                                    <td><s:textfield name="desNumMot" id="desNumMot" maxLength="15" cssClass="element-form" cssStyle="width:110px" /> </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td>Num Km </td>
                                                    <td><s:textfield name="numKimAct" id="numKimAct" maxLength="6" cssClass="element-form" cssStyle="width:70px" onkeypress="return isNumberKey(event)" /> </td>
                                                </tr>
                                                <!--tr>
                                                   <td>Sit Veh.</td><td><s:select theme="simple" name="codSitVeh" list="#{'':'Seleccione','L':'Libre','B':'Bloqueado','X':'Exhibición','S':'Separado','E':'Endoso','K':'Booking','V':'Vendido'}" cssClass="element-form" /></td>
                                                </tr-->
                                                <!--tr>
                                                    <td>Fec Sit</td><td><s:textfield  name="fecSitVeh" id="fecSitVeh" cssClass="element-form" cssStyle="width:84px"  /></td>
                                                </tr-->
                                                 <tr>
                                                  <td>Prec Venta Asig</td>
                                                  <td><s:textfield name="impPreVenAsi" id="impPreVenAsi" cssClass="element-form" maxLength="10" cssStyle="width:110px" onkeypress="return isNumberKey(event)" readonly="true" /></td>
                                                 </tr>
                                                 <tr>
                                                    <td>Ubicación</td>
                                                    <td class="clsUbic"><s:select id="idUbiAmb" theme="simple" name="idUbiAmb" list="listUbicaciones" listKey="idUbiAmb" listValue="desUbiAmb" cssClass="element-form" headerKey="0" headerValue="Seleccione" /> </td>
                                                </tr>

                                              </table>
                                      </td>
                                  </tr>
                         </table>
                       </td>
                     </tr>
                     </table>
                     </td>
                            
                     <td style="vertical-align: top;padding-left: 50px">
                         <table border="0" cellpadding="0" cellspacing="0">
                             <tr>
                                 <td>
                                   <table border="0" cellpadding="0" cellspacing="0">
                                <tr>
                                    <td style="padding-top:10px; font-size: 12px; color: #4282B7; font-weight: bold;" colspan="2">Datos del Proveedor</td>
                                </tr>
                                <tr>
                                  <td>
                                        <table border="0" cellpadding="0" cellspacing="0">
                                               <tr>
                                                    <td>Poliza de Impt </td>
                                                    <td> <s:textfield name="desPolImp" id="desPolImp" maxLength="25" cssClass="element-form" cssStyle="width:180px" /></td>
                                                </tr>
                                             <tr>
                                                <td style="">Num Fact</td>
                                                <td>
                                                  <s:textfield id="desNumFacPrv" name="desNumFacPrv" cssClass="element-form"  cssStyle="width:120px;text-transform: uppercase;" maxLength="20" />
                                                </td>
                                             </tr>
                                             <tr>
                                                <td style="">
                                                  Fec Factura  
                                                </td>
                                                <td>
                                                  <s:textfield id="fecFacPrv" name="fecFacPrv" cssClass="element-form"  cssStyle="width:100px;text-transform: uppercase;" maxLength="10" />
                                                </td>
                                             </tr>
                                             <tr>
                                                  <td>Moneda</td>
                                                  <td> <s:select id="codMonFacPrv" name="codMonFacPrv"  cssClass="element-form" list="#{'1':'Soles','2':'Dolares'}" /> </td>
                                             </tr>
                                             <tr>
                                                  <td>Imprt Cambio</td>
                                                  <td><s:textfield name="impTipCamFP" id="impTipCamFP" maxlength="5" onkeypress="return isNumberKey(event)" cssClass="element-form" />  </td>
                                             </tr>
                                              <tr>
                                                  <td>Imprt Moneda Local</td>
                                                  <td><input type="text" name="impMonLoc_v" id="impMonLoc" class="element-form" maxLength="10" value="<s:property value="impMonLoc" />" onkeypress="return isNumberKey(event)"  />  
                                                      <s:hidden name="impMonLoc" id="impMonLoc_h" />
                                                  </td>
                                             </tr>
                                             <tr>
                                                 <td>Imprt Moneda Ext</td>
                                                 <td><input type="text" name="impMonExt_v" id="impMonExt" class="element-form" maxLength="10" value="<s:property value="impMonExt" />"  onkeypress="return isNumberKey(event)" />
                                                     <s:hidden name="impMonExt" id="impMonExt_h" />
                                                 </td>
                                             </tr>
                                            
                                            </table> 
                                         </td>
                                     </tr>
                                   </table> 
                                 </td>
                             </tr>
                         </table>      
                      </td>
                    </tr>
                 </table>
                    
                </td>
                
            </tr>

            <s:if test='%{opcion=="M"}'>
                
            
            <tr style="vertical-align: top">
                <td>
                     <table border="0" cellpadding="0" cellspacing="0">
                         <tr>
                             <td>
                            <table>
                                <tr>
                                    <td>
                                        <table border="0" cellpadding="0" cellspacing="0">
                                            <tr>
                                                <td style="padding-top:10px; font-size: 12px; color: #4282B7; font-weight: bold;" colspan="2">Datos de Registro</td> 
                                            </tr>
                                            <tr>
                                                <td style="vertical-align: top">
                                                    <table border="0" cellpadding="0" cellspacing="0">
                                                        <tr>
                                                            <td style="vertical-align: top;">
                                                                <table border="0" cellpadding="0" cellspacing="0">
                                                                    
                                                                    <tr>
                                                                          <td>Fecha Reg</td>
                                                                          <td><span class="clslabel"> <s:property value="fecReg" /> </span> </td>
                                                                    </tr>
                                                                     
                                                                    <tr>
                                                                        <td>Conces</td><td>
                                                                            <span class="clslabel"><s:property value="desCon" /> </span> 
                                                                            <s:hidden id="idCon" name="idCon"  />
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>Local</td>
                                                                        <td class="clsLocal">
                                                                            <span class="clslabel"> <s:property value="desLocCon" /></span> 
                                                                            <s:hidden    id="idLocCon" name="idLocCon"      /></td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                                <td style="vertical-align: top">
                                                     
                                                        <table border="0" cellpadding="0" cellspacing="0">
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td></td>
                                                        </tr>
                                                        <tr>
                                                            <td>Cod Usuario</td>
                                                            <td><span class="clslabel"><s:property value="idUsu" />  </span>  </td>
                                                        </tr>
                                                        <tr>
                                                            <td>Usuario</td>
                                                            <td><span class="clslabel"><s:property value="desUsu" /></span></td>
                                                        </tr>
                                                    </table>
                                                     
                                                    
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                     </td>
                          </tr>
                     </table>
                </td>
            </tr>
          </s:if>
            
            <s:if test='%{opcion=="M"}'>
            <tr>
             <td style="vertical-align: top">
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                <table border="0" cellpadding="0" cellspacing="0">
                                    <tr>
                                       <td style="padding-top:10px; font-size: 12px; color: #4282B7; font-weight: bold;padding-bottom: 10px" colspan="2">Datos de la Venta</td> 
                                    </tr>
                                    <tr>
                                        <!--td style="vertical-align: top">
                                            <table border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td>Sit Venta.</td>
                                                    <td><s:select id="codSitVen" name="codSitVen" cssClass="element-form"  list="#{'':'Seleccione','D':'Disponible','E':'Endosado','V':'Vendido','A':'Activado'}" /> </td>
                                                </tr>
                                                <tr>
                                                    <td>Fec Sit Venta</td>
                                                    <td><!s:textfield   name="fecSitVen" id="fecSitVen" cssClass="element-form" maxLength="10" cssStyle="width:100px;" /></td>
                                                </tr>
                                                <tr>
                                                    <td>Cod Cliente</td><td style="vertical-align: top"><!s:textfield   name="idCli" id="idCli" cssClass="element-form" maxlength="15" cssStyle="width:110px;" onkeypress="return isNumberIntegerKeyWithIntro(event)"  /> <span><button id="btn_clientes">...</button></span></td>
                                                </tr>
                                                <tr>
                                                    <td>Nombre</td><td><!s:textfield  name="desCli" id="desCli" cssClass="element-form" readonly="true" cssStyle="width:260px; text-transform: uppercase;" disabled="true" /></td>
                                                </tr>
                                               <!s:if test='%{opcion=="M"}'>
                                                <tr>
                                                    <td>Tip Doc Venta </td>
                                                    <td>
                                                        <s:select id="cboTipDocVenta" cssClass="element-form"  list="#{'':'Seleccione','0':'Sin Documento','1':'Factura','2':'Boleta'}"
                                                       name="idTipDocVen"    />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>Num Doc Venta </td>
                                                    <td><!s:textfield   name="desNumDocVen" id="desNumDocVen" cssClass="element-form" cssStyle="width:120px" maxlength="20" /></td>
                                                </tr>
                                                
                                                
                                                 <tr>
                                                      <td>Num Doc Venta </td>
                                                      <td><!s:property value="desModVen" /></td>
                                                 </tr>
                                                 <tr>
                                                      <td>Num Reg Venta </td>
                                                      <td><!s:property value="idNumIntRV" /></td>
                                                 </tr>
                                       
                                                <!/s:if>
                                                 
         
                                            </table>
                                        </td-->
                                        
                                        <td style="vertical-align: top;"> 
                                            <table border="0" cellpadding="0" cellspacing="0">
                                                
                                                <tr>
                                                    <td>Placa</td><td><span class="clslabel"><s:property   value="desNumPla"  /></span></td>
                                                </tr>
                                                <tr>
                                                    <td style="padding-right: 10px">Fec Entreg </td>
                                                    <td><span class="clslabel"><s:property value="fecEntCli"  /></span></td>
                                                </tr>
     
                                            </table>
                                        </td>
                                        <td style="padding-left: 15px;vertical-align: top">
                                            
                                            <table border="0" cellpadding="0" cellspacing="0">
                                                <tr>
                                                    <td style="padding-right: 15px;vertical-align: top">Obs</td><td><s:textarea  cssStyle="width:206px; height:51px; resize:none;" name="desObs" id="desObs" cssClass="element-form" readonly="true" disabled="true"   /></td>
                                                </tr>
                                            </table>
                                        </td>
                                        
                                        
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </s:if>
            <tr>
                 
                <td style="padding-top: 10px;">
                    <button id="btn_grabar">
                        Grabar
                    </button>
                    <span class="required">(*) campos obligatorios</span>
                </td>
            </tr>
        </table>
    </s:form>
</div>
<div id="DIVgrabar" title="<s:property value="titleDialog" />" class="alerta"></div>
<div id="DIVclientes" title="<s:property value="titleDialog" />" class="alerta"></div>                    
                    
<script type="text/javascript">
    $(document).ready(function(){
        
        $('.indVeh').change(function(){
          
           if(this.checked) {
              $(this).val('A');
          }else{
              $(this).val('D');
          }   
          
          
    });
    
     $('.edoVeh').change(function(){
          
          if(this.checked) {
              $('.edoVeh').val('A');
          }else{
              $('.edoVeh').val('D');
          }   
          
    });
        
     $('#codMonFacPrv').change();   
       //<----- DatePicker's  ------>
        $('#fecSitVeh,#fecFacPrv,#fecSitVen,#fecVenVeh,#fecEntCli').datepicker({ 
            monthNames: ["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"], 
            monthNamesShort: ["Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"],
            dayNamesMin: ["Do", "Lu", "Ma", "Mi", "Ju", "Vi", "Sa"],
            firstDay: 1,
            dateFormat: "yy-mm-dd"
        });        
        
        $('#btn_grabar').button();
    
        $('#DIVgrabar').dialog({
            autoOpen: false,
            width: 400,
            height: 200,
            modal: true,
            closeOnEscape: false,
            buttons:
            {
                "Aceptar":function(){
                    $('#DIVgrabar').dialog("close");
                    $('.overlay').animate({'opacity':'0'},250,'swing',function(){
                        $('.overlay').css({'z-index':'-1'});
                    });
                }
            },
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false
        });
        
         $('#DIVclientes').dialog({
            autoOpen: false,
            width: 580,
            height: 500,
            modal: true,
            closeOnEscape: false,
            open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); },
            draggable: false,
            resizable: false
        });
        
        $('a.back').click(function(){
            $('#frm_cli_back').submit();
        });
        
        $('#btn_grabar').click(function(){
            post(
                '<s:property value="formURL" />',
                $('#frm_cli').serialize(),
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVgrabar').html(resultado);
                        $('#DIVgrabar').dialog('open');
                    }
                    else
                    {
                        $('#frm_cli_back').submit();
                    }
                },
                1
            );
        });
    });
    
    
   $('#idMar').change(function(){
       
       $.post("listaModelosVehiculo",
       {
         idMar:$('#idMar').val()    
       },function(resultado){
           $('#idModMar').change();
           $('#idVerMod').change();  
           $('.tdModelos').html(resultado);
       });
   
   }); 
    
  $('#idAmbUbi').change(function(){
     
     $.post("listaUbicacionesVehiculo",{
         idAmbUbi:$('#idAmbUbi').val()
     },function(resultado){
         $('.clsUbic').html(resultado);
     });
     
  });  
  
  $('#idCon').change(function(){
     
     $.post("listaLocalesVehiculo",{
         idCon:$('#idCon').val()
     },function(resultado){
         $('.clsLocal').html(resultado);
     });
     
  });

  $('#idDpto').change(function(){
        
        var _iddep = $(this).val();
        
        $('#tdProv').css('text-align','center');
        $('#tdProv').html('<img src="<s:property value="baseURL" />images/ajax-loader.gif" />')
        $('#tdDist').css('text-align','center');
        $('#tdDist').html('<img src="<s:property value="baseURL" />images/ajax-loader.gif" />')
        $.post("populateProvCliente.action", { idDep:_iddep }, 
            function(resultado){
                $('#tdProv').css('text-align','left');
                $('#tdProv').html(resultado);
                $('#tdDist').css('text-align','left');
                $('#tdDist').html('<select name="idDisPrv" class="element-form"  style="width:150px; max-width:150px;"><option value="0">Seleccione</option></select>')
                
            }
        )
        
    });
    
    $("#idProv").change(function(){
        var _iddep = $('#idDpto').val();
        var _idprv = $("#idProv").val();
        $('#tdDist').css('text-align','center');
        $('#tdDist').html('<img src="<s:property value="baseURL" />images/ajax-loader.gif" />')
        $.post("populateDistCliente.action", { idDep:_iddep,idPrvDep:_idprv }, 
            function(resultado){
                $('#tdDist').css('text-align','left');
                $('#tdDist').html(resultado);
            }
        )
    });
    
    
    $('#btn_clientes').click(function(){
        
         post(
                '<s:property value="baseURL" /><s:url namespace="vehiculos" includeContext="false" action="listVehClientesVehiculo" />',
                {},
                function(resultado){
                    var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVerroresGen').html(resultado);
                        $('#DIVerroresGen').dialog('open');
                    }
                    else
                    {
                        $('#DIVclientes').dialog({
                            buttons:{
                                "Aceptar":function(){
                                    //$('#colorIdColExt').val($('.select_rec:checked').val());
                                    $('#idCli').val($('.select_rec:checked').val());
                                    $('#desCli').val($.trim($('.select_rec:checked').parent().next().next().html()));
                                    $('#DIVclientes').dialog("close");
                                    hideOverlay(function(){});
                                },
                                "Cancelar":function(){
                                    $('#DIVclientes').dialog("close");
                                    hideOverlay(function(){});
                                }
                            }
                        });
                        $('#DIVclientes').html(resultado);
                        $('#DIVclientes').dialog('open');
                    }
                },
                2       
            );
        
        
    });
    
    
    $('#idCli').keypress(function(e){
    
         if(e.which==13){
            
           post("obtndescliVehiculo"
           ,{
               idCli_f:$('#idCli').val()
           },
           function(resultado){
               //alert(resultado)
               var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVerroresGen').html(resultado);
                        $('#DIVerroresGen').dialog('open');
                    }else{
                        $('#desCli').val(resultado); 
                    }
           },1); 
            
         }    
    });
    
    $('#codMonFacPrv').change(function(){
           
          if( $(this).val()=='1' ){
             $('#impMonLoc').removeAttr('disabled');
             $('#impMonExt').attr('disabled','disabled');
          }else{
             $('#impMonExt').removeAttr('disabled');
             $('#impMonLoc').attr('disabled','disabled');  
          }    
           
    });
    
    $('#impMonExt').keyup(function(event){
        
         var mondext=parseFloat($('#impMonExt').val());
         var tipcambio=parseFloat($('#impTipCamFP').val());
         var imptMonLoc=(mondext*tipcambio);
         
         imptMonLoc=imptMonLoc.toFixed(2);
         if(isNaN(imptMonLoc)){
             imptMonLoc=""
         }
         $('#impMonLoc').val(imptMonLoc);
         $('#impMonLoc_h').val( imptMonLoc );
         
         $('#impMonExt_h').val( $(this).val());
    });
    
    
    $('#impMonLoc').keyup(function(){
          
          
         var mondlocal=parseFloat($('#impMonLoc').val());
         var tipcambio=parseFloat($('#impTipCamFP').val());
         var imptMonExt=(mondlocal/tipcambio);
         
         imptMonExt=imptMonExt.toFixed(2);
         if(isNaN(imptMonExt)){
             imptMonExt=""
         }
         $('#impMonExt').val(imptMonExt);
         $('#impMonExt_h').val(imptMonExt);
        
        $('#impMonLoc_h').val( $(this).val() );
    });
    
    $('#impTipCamFP').keyup(function(){
         
         var mondlocal=parseFloat($('#impMonLoc').val());
         var tipcambio=parseFloat($('#impTipCamFP').val());
         var imptMonExt=(mondlocal*tipcambio);
         
         imptMonExt=imptMonExt.toFixed(2);
         if(isNaN(imptMonExt)){
             imptMonExt=""
         }
         $('#impMonExt').val(imptMonExt);
        
    });
    
   
    $('#numAnoFab').keyup(function(){
       
         $.post("obtPrecVentAsiVehiculo",
         {
             idMar:$('#idMar').val(),
             idModMar:$('#idModMar').val(),
             idVerMod:$('#idVerMod').val(),
             numAnoFab:$('#numAnoFab').val()
         }
         ,function(resultado){
             
              var _error = validaRespuestaAjax(resultado);
                    
                    if(_error != -1)
                    {
                        $('#DIVerroresGen').html(resultado);
                        $('#DIVerroresGen').dialog('open');
                    }
                    else
                    {
                        $('#impPreVenAsi').val($.trim(resultado));  
                    }    
             
         })  
    });
    
    
    
    
    
    function abrir_ventana(url)
    {	
        //var opc = "location=0,directories=0,titlebar=0,toolbar=0,location=1,status=0,menubar=0,scrollbars=1,width=" + screen.availWidth + ",height=" + screen.availHeight;
        var opc = "location=0,directories=0,titlebar=0,toolbar=0,location=1,status=0,menubar=0,scrollbars=1,width=300,height=150";
        mywindow = window.open(url, "", opc);
        mywindow.moveTo(100, 100);
        setTimeout('mywindow.close()',7000);
    }
</script>