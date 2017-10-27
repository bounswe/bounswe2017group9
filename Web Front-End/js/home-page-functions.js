    var imageElement = document.getElementsByClassName("card-image");
    for(var i = 0; i < imageElement.length; i++){
        imageElement[i].style.height = imageElement.style.width * 0.5;
        console.log(imageElement.style.width +" " + i);
    }


