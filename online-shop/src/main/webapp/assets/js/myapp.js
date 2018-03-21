$(function(){
	//solving the active menu problem
	switch(menu){
	case 'home':
		$('#home').addclass('active');
		break;
	case 'about':
		$('#about').addclass('active');
		break;	
	case 'All Products':
		$('#listProducts').addclass('active');
		break;
	case 'contact':
		$('#contact').addclass('active');
		break;
	default:
		$('#listProducts').addclass('active');
		$('#a_'+menu).addClass('active');
		break;
	}
});