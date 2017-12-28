$(document).ready(function(){
    var date_input=$('#dateS'); //our date input has the name "date"
    var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    var options={
        format: 'dd/mm/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
    };
    date_input.datepicker(options);
})

$(document).ready(function(){
    var date_input=$('#dateE'); //our date input has the name "date"
    var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    var options={
        format: 'dd/mm/yyyy',
        container: container,
        todayHighlight: true,
        autoclose: true,
    };
    date_input.datepicker(options);
})

$( function() {
    $( "#slider-range" ).slider({
        range: true,
        min: 0,
        max: 500,
        values: [ 75, 300 ],
        slide: function( event, ui ) {
            $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
        }
    });
    $( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
        " - $" + $( "#slider-range" ).slider( "values", 1 ) );
    
} );

$("#slider-range").click(function(){
	$("#MinPrice").val($( "#slider-range" ).slider( "values", 0 ));
    $("#MaxPrice").val($( "#slider-range" ).slider( "values", 1 ));
});

var defaults = {
		calendarWeeks: true,
		showClear: true,
		showClose: true,
		allowInputToggle: true,
		useCurrent: false,
		ignoreReadonly: true,
		minDate: new Date(),
		toolbarPlacement: 'top',
		locale: 'nl',
		icons: {
			time: 'fa fa-clock-o',
			date: 'fa fa-calendar',
			up: 'fa fa-angle-up',
			down: 'fa fa-angle-down',
			previous: 'fa fa-angle-left',
			next: 'fa fa-angle-right',
			today: 'fa fa-dot-circle-o',
			clear: 'fa fa-trash',
			close: 'fa fa-times'
		}
	};

	$(function() {
		var optionsDatetime = $.extend({}, defaults, {format:'DD-MM-YYYY HH:mm'});
		var optionsDate = $.extend({}, defaults, {format:'DD-MM-YYYY'});
		var optionsTime = $.extend({}, defaults, {format:'HH:mm'});
		
		$('.datepicker').datetimepicker(optionsDate);
		$('.timepicker').datetimepicker(optionsTime);
		$('.datetimepicker').datetimepicker(optionsDatetime);
	});
