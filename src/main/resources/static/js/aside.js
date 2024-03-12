  function quickFunction(){
            $(".moveScrollTop").click(function(e) {
                e.preventDefault();
                $("body, html").stop().animate({scrollTop:0}, 500);
            });
            $(".moveScrollBottom").click(function(e) {
                e.preventDefault();
                $("body, html").stop().animate({scrollTop:$('body').height()}, 500);
            });
        }