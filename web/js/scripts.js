$(document).ready(function() {
    /* Redimensiona el ancho de la lista de opciones del usuarios para mantener el icono y el texto en una sola línea */
    var _h_ou = $('.ul-options-user').outerWidth();
    $('.ul-options-user').width(_h_ou+26);
    
    $('html').click(function(e) {
        hideMenu();
        hideOptUser();

        /*Manejar errores si la pagina actual no define la funcion para controlar divs flotantes de cabecera de grilla */
        try
        {
            hideOptGrilla();
        }
        catch (err) {
        }
    });

    $('.ul-modulos > li').click(function() {
        if(!$(this).hasClass('menu-click'))
        {
            $('li.menu-click').removeClass('menu-click');
            $('.ul-modulos li .ul-opciones li').children('a.menu-click-opc,span.menu-click-opc').removeClass('menu-click-opc');
        }
    });

    $('.ul-modulos > li').click(function(e) {
        e.stopPropagation();
        hideOptUser();
        
        /*Manejar errores si la pagina actual no define la funcion para controlar divs flotantes de cabecera de grilla */
        try
        {
            hideOptGrilla();
        }
        catch (err)
        {
        }
        
        if(!$(this).hasClass('menu-click'))
        {
            $(this).addClass('menu-click');
            $('.ul-modulos li .ul-opciones').css('display', 'none');
            $(this).children('ul').css('display', 'block');
        }
    });

    $('.ul-modulos li > .ul-opciones').click(function(e) {
        e.stopPropagation();
        hideOptUser();
        /*Manejar errores si la pagina actual no define la funcion para controlar divs flotantes de cabecera de grilla */
        try
        {
            hideOptGrilla();
        }
        catch (err)
        {
        }
    });

    $('.ul-modulos li .ul-opciones li').hover(function() {
        $(this).children('a,span').addClass('menu-hover-opc');
    }, function() {
        $(this).children('a,span').removeClass('menu-hover-opc');
        /*$(this).parent().children('li').children('a.menu-hover-opc,span.menu-hover-opc').removeClass('menu-hover-opc');*/
    });

    $('.ul-modulos > li > .ul-opciones > li').click(function(e) {
        $(this).parent().children('li.menu-click').removeClass('menu-click');
    });

    //$('.ul-modulos li .ul-opciones li a, .ul-modulos li .ul-opciones li span').mousedown(function(e) {
    //$('.ul-modulos li .ul-opciones li a.opc, .ul-modulos li .ul-opciones li span.opc').mousedown(function(e) {
    $('.ul-opciones li a.opc, .ul-opciones li span.opc').mousedown(function(e) {
        e.stopPropagation();
        hideOptUser();
        
        if(!$(this).hasClass('indic_dsply'))
        {
            $('.ul-opciones li a.opc, .ul-opciones li span.opc').removeClass('indic_dsply');
            $('.ul-modulos li .ul-opciones li .ul-opciones').css('display', 'none');
            $('.ul-opc li').children('a.menu-click-opc,span.menu-click-opc').removeClass('menu-click-opc');
            $(this).addClass('menu-click-opc');
        }
    });
    
    $('.ul-modulos li .ul-opciones li a.opc, .ul-modulos li .ul-opciones li span.opc').mouseup(function(e) {
        e.stopPropagation();
        hideOptUser();
        
        if($(this).not('.indic_dsply'))
        {
            $(this).addClass('indic_dsply');
            $(this).parent().children('ul').css('left', $(this).parent().outerWidth()+1);
            $(this).parent().children('ul').css('top', '0px');
            $(this).parent().children('ul').css('display', 'block');
        }
    });
    
    $('.ul-opciones li a.subopc, .ul-opciones li span.subopc').mousedown(function(e) {
        e.stopPropagation();
        hideOptUser();
        
        $('.ul-subopc li').children('a.menu-click-opc,span.menu-click-opc').removeClass('menu-click-opc');
        $(this).addClass('menu-click-opc');
    });


    $('.d-more-opt-float').click(function(e) {
        e.stopPropagation();
    });
    
    /* Mostrar y ocultar las opciones del usuario */
    $('.option-user-icon').click(function(e){
        e.stopPropagation();
        hideMenu();
        
        if($(this).parent().hasClass('menu-click'))
        {
            $(this).parent().removeClass('menu-click');
            $('.ul-options-user').css('display','none');
        }
        else
        {
            $(this).parent().addClass('menu-click');
            $('.ul-options-user').css('display','block');
        }
    });
    
    /* al pasar sobre las opciones del usuario */
    $('.ul-options-user li div.elem-option-user').hover(function() {
        $(this).addClass('menu-hover-opc');
        $(this).children('a').addClass('color-text-opt-user-hover');
    }, function() {
        $(this).removeClass('menu-hover-opc');
        $(this).children('a').removeClass('color-text-opt-user-hover');
        /*$(this).children('a').css('color','#354C77');*/
    });
    
    /*Al dar click a una de las opciones del usuario*/
    $('.ul-options-user li').click(function(e){
        e.stopPropagation();
    });
    
    $('.elem-option-user').click(function(e){
        //alert($(this).children('a').html());
    });
    
    $('.elem-option-user').mousedown(function(e) {
        e.stopPropagation();
        hideMenu();
        
        $('.elem-option-user').removeClass('ui-state-active');
        $(this).addClass('ui-state-active');
        $('.elem-option-user').removeClass('menu-click-opc');
        $(this).addClass('menu-click-opc');
        $(this).addClass('color-text-opt-user-click');
    });
    
    $('.elem-option-user').mouseup(function(e) {
        e.stopPropagation();
        hideMenu();
    });
    
    /* Cambiar color de fila de grilla si el cursor esta sobre este y con otro color si se ha seleccionado */
    $('body').delegate('.d-content-grilla-body table tr','mouseenter',function(){
        $(this).addClass("on-over-tr");
    }).delegate('.d-content-grilla-body table tr','mouseleave',function(){
        $(this).removeClass("on-over-tr");
    });
    
    
    $('body').delegate('.d-content-grilla-body table tr','click',function(){
        $('.d-content-grilla-body table tr').removeClass("selected-tr");
        $(this).find('td:first-child').find('.select_rec').prop('checked',true);
        $(this).addClass("selected-tr");
    });  
    
    
    /*Funciones para controlar las opciones del usuario*/
    $('#close-session').click(function(){
        var _url = $(this).children('input').val();
        post(
            _url,
            {},
            function(){
                location.href = $(location).attr('href');
            },
            1
        );
    });
});

function hideMenu()
{
    $('.ul-modulos li .ul-opciones').css('display', 'none');
    $('.ul-modulos li.menu-click').removeClass('menu-click');
    $('.ul-modulos li .ul-opciones li').children('a.menu-click-opc,span.menu-click-opc').removeClass('menu-click-opc').removeClass('indic_dsply');
}

function hideOptUser()
{
    $('.d-datos-user .menu-click').removeClass('menu-click');
    $('.ul-options-user').css('display','none');
    $('.d-datos-user .menu-click-opc').removeClass('ui-state-active').removeClass('color-text-opt-user-click').removeClass('menu-click-opc');
}

/*Si la grilla muestra menos de la cantidad de registros configurada, se redimensiona el alto del div contenedor*/
function resizeContGrilla(cantReg)
{
    $('.d-grilla').css('overflow-x','hidden');
    var indic_resize = $('.d-grilla').outerWidth()-$('.d-content-grilla-head table').outerWidth();
    
    if(indic_resize<0)
    {
        $('.d-grilla').css('overflow-x','scroll');
        var cantcol=0;
        $('.d-content-grilla-head table tr').each(function (index) {
            if(index==0)
            {
                cantcol = $(this).children('td').size();
            }
        });
        var b_ancho = $('.d-content-grilla-head table').outerWidth();
        var ancho = b_ancho+cantcol*11-1;
        //alert(b_ancho+"    "+cantcol*11)
        
        $('.d-content-grilla-head table').css('width',ancho);
        $('.d-content-grilla-head').css('width',ancho);
        $('.d-content-grilla-body table').css('width',ancho);
        $('.d-content-grilla-body').css('width',ancho);
        $('.d-content-grilla-footer').css('width',ancho);
    }
    
    var _cantreggrilla = $('.d-content-grilla-body table tr').size();
    if(_cantreggrilla < cantReg)
    {
        $('.d-content-grilla-body').css('height',cantReg*23);
        
        if(indic_resize<0)
            $('.d-content-grilla-body').parent().css('height',24+27+cantReg*23+36+18);
        else
            $('.d-content-grilla-body').parent().css('height',24+27+cantReg*23+36);
    }
}

function resizeContGrillaPopUp(cantReg)
{
    var _cantreggrilla = $('.d-content-grilla-body-popup table tr').size();
    if(_cantreggrilla < cantReg)
    {
        $('.d-content-grilla-body-popup').css('height',cantReg*23);
        $('.d-content-grilla-body-popup').parent().css('height',24+27+cantReg*23+36);
    }
}

function displayOverlay(funcion)
{
    $('.load-container').css('display','table');
    $('.overlay').css({'z-index':'50'});
    $('.overlay').animate({'opacity':'1'},250,'swing',funcion);
}

function hideOverlay(funcion)
{
    $('.load-container').css('display','none');
    $('.overlay').animate({'opacity':'0'},250,'swing',function(){
        $('.overlay').css({'z-index':'-1'});
        funcion;
    });
}

//indAjax:  
//   1=envio normal (mostrara overlay y luego del proceso lo ocultará)
//   2=se envia normalmente y dentro de la funcion se llama a otro ajax (no se ocultara overlay)
//   3=envio normal pero no muestrara ni ocultara el overlay (es llamado desde de otro ajax, y llamara a otro)
//   4=envio normal, sin mostrar el overlay pero si esconde el overlay (es llamado desde otro ajax y no llamara a otro)
function post(url,datos,funcion,indAjax)  
{
    $('.load-container').css('display','table');
    
    if(indAjax==1 || indAjax==2)
    {
        $('.overlay').css({'z-index':'50'});
    }
    
    
    $('.overlay').animate({'opacity':'1'},250,'swing',function(){
        $.post(
            url,
            datos,
            function(resultado){
                resultado = $.trim(resultado);
                var _error_gen = resultado.indexOf("error");

                var opac = 1;
                if(indAjax==1 || indAjax==4)
                {
                    if(_error_gen<0)
                        opac=0;
                }

                if(indAjax==1 || indAjax==4)
                {
                    $('.overlay').animate(
                        {'opacity':opac},
                        250,
                        'swing',
                        function(){
                            try
                            {
                                funcion(resultado);
                                if(_error_gen<0)
                                    $('.overlay').css({'z-index':'-1'});
                                else
                                    $('.load-container').css('display','none');
                            }
                            catch (err)
                            {
                                if(_error_gen<0)
                                    $('.overlay').css({'z-index':'-1'});
                                else
                                    $('.load-container').css('display','none');
                            }
                        }
                    );
                }
                else
                {
                    $('.load-container').css('display','none');
                    funcion(resultado);
                }
            }
        );
    });
}

function closeDialog()
{
    $('.overlay').animate({'opacity':'0'},250,'swing',function(){
        $('.overlay').css({'z-index':'-1'});
    });
}

/*Funciones para validar ingreso de datos */

function isNumberKey(evt)  //Numeros con punto decimal
{
    var charCode = (evt.which) ? evt.which : window.event.keyCode;
    
    if((charCode >= 48 && charCode <= 57) || charCode===46 || charCode===8 || charCode===0)
        return true;
    
    return false;
}

function isNumberIntegerKey(evt) //Solo numeros (entero)
{
    var charCode = (evt.which) ? evt.which : window.event.keyCode;
    
    if((charCode >= 48 && charCode <= 57) || charCode===8  || charCode===0)
        return true;
    
    return false;
}
















