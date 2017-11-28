var $form = $("#comment-form");


function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    indexed_array.category =$("#comment-category").val();
    indexed_array.concert_id = $(".current-concert").id;
    return indexed_array;
}

$("#comment-button").click(
    function(){
        var data = getFormData($form);

        //sending comment to the backend
        $.post( "ajax-new-comment", data)
            .done(function () {
                //here a url needs to be given to extract the submitted comment from the database
                $.get("url",function(receivingData){
                    var string = "<div class=\"container\">\n" +
                        "                                <div class=\"row\">\n" +
                        "                                    <div class=\"col-1\">\n" +
                        "                                        <img src=\"https://scontent-otp1-1.xx.fbcdn.net/v/t1.0-1/p32x32/11998819_10207644722858852_961965001166011171_n.jpg?oh=2ad334cbb8563c259ec13dfb45477ec3&oe=5AA50A53\">\n" +
                        "                                    </div>\n" +
                        "                                    <div class=\"col-11\">\n" +
                        "                                        <div>\n" +
                        "                                            <a href=\"#\">"+ receivingData.name+  " </a>\n" +
                        "                                            <p> "+ receivingData.comment+ " </p>\n" +
                        "                                        </div>\n" +
                        "                                        <div style=\"text-align: right\">\n" +
                        "                                            <div class=\""+receivingData.category +"\"></div>\n" +
                        "                                        </div>\n" +
                        "                                    </div>\n" +
                        "                                </div>\n" +
                        "                            </div>\n" +
                        "                            <div class=\"card-line\">\n" +
                        "                                <div class=\"card-last-line\">\n" +
                        "                                </div>\n" +
                        "                            </div>";
                    $(".concert-comment").append(string);
                })
            })

    }
)