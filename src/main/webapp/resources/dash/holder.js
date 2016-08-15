$(document).ready(function(){ 
    $(".row li a").click(function(){
        $(this).parent("li").addClass("active");
        $(this).parent("li").siblings().removeClass("active");
    });
});