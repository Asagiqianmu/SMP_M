$(function() {
/*	console.log(type);
	console.log(index);*/
	if(type==2 && index >8){
		var a = index - 2;
	}else{
		var a = index -1;
	}
	
	var element=$(".side-menu ul li").eq(a);
	//alert($(element).html());
	element.addClass('on');	
})
