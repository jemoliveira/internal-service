function myFunction() {
  var timerId = 0;
  var ctr=0;
  var max=10;
  
  timerId = setInterval(function () {
    // interval function
    ctr++;
    $('#blips > .progress-bar').attr("style","width:" + ctr*max + "%");
    
    // max reached?
    if (ctr==max){
      clearInterval(timerId);
    }
    
  }, 500);
};

var stops = [25,55,85,100];
$.each(stops, function(index, value){
    setTimeout(function(){
        $( ".progress-bar" ).css( "width", value + "%" ).attr( "aria-valuenow", value ); 
    }, index * 1500);
});