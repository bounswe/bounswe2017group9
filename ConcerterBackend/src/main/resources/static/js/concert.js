$( document ).ready(function() {
    var concerts = $(".concert-listing-container").has(".concert-comment");
    for (var i = 0; i < concerts.length; i++){
    	var currentConcert = concerts.eq(i);
    	var comments = currentConcert.find(".concert-comment");
    	console.log(comments);
    	for (var j = 0; j < comments.length; j++){
	         var currentComment = comments.eq(j);
			 var commentCategory = currentComment.find(".comment-category");
			 var comparedString = commentCategory.children("p").get(0).innerHTML;
			 console.log(commentCategory.children("p").get(0).innerHTML);
			 var colorPicker = currentComment.find(".comment-category-color");
	        if( comparedString === "Artist"){
	        	colorPicker.append("<img src='https://imgur.com/S9rpeNo.png' style='width:25px'></img>");
	        	console.log("here");
	        } else if( comparedString === "Costume"){
                colorPicker.append("<img src='https://imgur.com/nQXGGLI.png' style='width:25px'></img>");
	        } else if( comparedString === "Place"){
                colorPicker.append("<img src='https://imgur.com/BV02u5l.png' style='width:25px'></img>");
	        } else if( comparedString === "Transportation"){
                colorPicker.append("<img src='https://imgur.com/EwB8kf4.png'style='width:25px'></img>");
	        } else if( comparedString === "Price"){
                colorPicker.append("<img src='https://imgur.com/efmHlbD.png' style='width:25px'></img>");
            }
	        commentCategory.children("p").get(0).style.display = "none";
    	}
    }
});

/*
--------- Star Rating Section
*/

var $star_rating = $('.star-rating .fa');

var SetRatingStar = function() {
  return $star_rating.each(function() {
    if (parseInt($star_rating.siblings('input.rating-value').val()) >= parseInt($(this).data('rating'))) {
      return $(this).removeClass('fa-star-o').addClass('fa-star');
    } else {
      return $(this).removeClass('fa-star').addClass('fa-star-o');
    }
  });
};

$star_rating.on('click', function() {
  $star_rating.siblings('input.rating-value').val($(this).data('rating'));
  return SetRatingStar();
});

SetRatingStar();
$(document).ready(function() {

});

/*
 *	Semantic Search Section
 */
$(document).ready(function(){
	var semanticSearcher = $(".rate-and-tags");
	for (var i = 0; i < semanticSearcher.length; i ++){
		var currentSearcher = semanticSearcher.eq(i);
		var x = currentSearcher.find(".semantic-tag-searcher");
		var inputText = x.children().first();
		var inputButton = x.children().last();
		inputButton.click(function(){
			alert();
		});
	}
});


